package it.auties.whatsapp.socket;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicReference;

abstract class Handler {
  private static final int DEFAULT_CORES = 10;

  private final AtomicReference<ExecutorService> service;
  private final AtomicReference<ExecutorService> fallbackService;
  private final AtomicReference<CountDownLatch> latch;
  private final AtomicReference<Semaphore> semaphore;

  public Handler(){
    this.service = new AtomicReference<>();
    this.fallbackService = new AtomicReference<>();
    this.latch = new AtomicReference<>();
    this.semaphore = new AtomicReference<>();
  }

  protected void dispose() {
    var serviceValue = service.getAndSet(null);
    if (serviceValue != null) {
      serviceValue.shutdownNow();
    }

    var fallbackServiceValue = fallbackService.getAndSet(null);
    if (fallbackServiceValue != null) {
      fallbackServiceValue.shutdownNow();
    }
  }

  protected CountDownLatch getOrCreateLatch() {
    var value = latch.get();
    if (value != null) {
      return value;
    }
    var newValue = new CountDownLatch(1);
    latch.set(newValue);
    return newValue;
  }

  protected void completeLatch() {
    getOrCreateLatch().countDown();
  }

  protected void awaitLatch() {
    try {
      getOrCreateLatch().await();
    } catch (InterruptedException exception) {
      throw new RuntimeException("Cannot await latch", exception);
    }
  }

  protected ExecutorService getOrCreateService() {
    var value = service.get();
    if (value != null && !value.isShutdown()) {
      return value;
    }
    var newValue = Executors.newSingleThreadExecutor();
    service.set(newValue);
    return newValue;
  }

  protected ExecutorService getOrCreateFallbackService() {
    var value = fallbackService.get();
    if (value != null && !value.isShutdown()) {
      return value;
    }
    var newValue = Executors.newScheduledThreadPool(DEFAULT_CORES);
    fallbackService.set(newValue);
    return newValue;
  }

  protected ExecutorService getOrCreatePooledService() {
    var value = service.get();
    if (value != null && !value.isShutdown()) {
      return value;
    }
    var newValue = Executors.newFixedThreadPool(DEFAULT_CORES);
    service.set(newValue);
    return newValue;
  }

  protected ScheduledExecutorService getOrCreateScheduledService() {
    var value = service.get();
    if (value != null && !value.isShutdown()) {
      return (ScheduledExecutorService) value;
    }
    var newValue = Executors.newSingleThreadScheduledExecutor();
    service.set(newValue);
    return newValue;
  }

  protected AutoCloseable getOrCreateSemaphore() throws InterruptedException{
    var value = semaphore.get();
    if (value != null) {
      value.acquire();
      return value::release;
    }
    var newValue = new Semaphore(1);
    semaphore.set(newValue);
    return newValue::release;
  }
}
