package it.auties.whatsapp4j.listener;

import lombok.NonNull;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;

import javax.tools.JavaFileObject;
import javax.tools.StandardJavaFileManager;
import javax.tools.StandardLocation;
import javax.tools.ToolProvider;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

/**
 * A utility class to find all classes annotated with {@link RegisterListener}.
 */
@UtilityClass
public class RegisterListenerProcessor {
    /**
     * An instance of the class loader, used to query all candidate classes
     */
    private final ClassLoader CLASS_LOADER = ClassLoader.getSystemClassLoader();

    /**
     * An instance of Java's File Manager, used to load all the candidate classes and validate them
     */
    private final StandardJavaFileManager FILE_MANAGER = ToolProvider.getSystemJavaCompiler().getStandardFileManager(null, Locale.getDefault(), StandardCharsets.UTF_8);

    /**
     * The target location for Java's File Manager
     */
    private final StandardLocation CLASS_LOCATION = StandardLocation.CLASS_PATH;

    /**
     * Queries all classes annotated with {@link RegisterListener} and initializes them using a no args constructor
     *
     * @return a list of {@link WhatsappListener}
     */
    public @NonNull List<WhatsappListener> queryAllListeners() {
        return Arrays.stream(CLASS_LOADER.getDefinedPackages())
                .flatMap(RegisterListenerProcessor::findClassesInPackage)
                .filter(RegisterListenerProcessor::isListener)
                .map(RegisterListenerProcessor::cast)
                .map(RegisterListenerProcessor::newInstance)
                .toList();
    }

    @SneakyThrows
    private @NonNull Stream<Class<?>> findClassesInPackage(@NonNull Package pack){
        return StreamSupport.stream(FILE_MANAGER.list(CLASS_LOCATION, pack.getName(), Set.of(JavaFileObject.Kind.CLASS), true).spliterator(), true)
                .map(RegisterListenerProcessor::loadClassFromFile)
                .filter(Optional::isPresent)
                .map(Optional::get);
    }

    private @NonNull Optional<Class<?>> loadClassFromFile(@NonNull JavaFileObject file) {
        try {
            return Optional.of(Class.forName(FILE_MANAGER.inferBinaryName(CLASS_LOCATION, file), false, CLASS_LOADER));
        }catch (ClassNotFoundException | NoClassDefFoundError error) {
            return Optional.empty();
        }
    }

    private boolean isListener(@NonNull Class<?> clazz) {
        return clazz.isAnnotationPresent(RegisterListener.class);
    }

    private @NonNull Class<? extends WhatsappListener> cast(@NonNull Class<?> clazz){
        try{
            return clazz.asSubclass(WhatsappListener.class);
        }catch (ClassCastException ex){
            throw new RuntimeException("WhatsappAPI: Cannot initialize class %s, classes annotated with @RegisterListener should implement WhatsappListener".formatted(clazz.getName()));
        }
    }

    private @NonNull WhatsappListener newInstance(@NonNull Class<? extends WhatsappListener> clazz){
        try {
            return clazz.getDeclaredConstructor().newInstance();
        } catch (IllegalAccessException | InstantiationException | InvocationTargetException | NoSuchMethodException operationException) {
            throw new RuntimeException("WhatsappAPI: Cannot initialize class %s".formatted(clazz.getName()), operationException);
        }
    }
}
