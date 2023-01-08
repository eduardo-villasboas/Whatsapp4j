package it.auties.whatsapp.test;

import it.auties.whatsapp.github.GithubActions;
import it.auties.whatsapp.github.GithubSecrets;
import java.io.IOException;
import org.junit.jupiter.api.Test;

public class UpdateCITest {

  @Test
  public void updateCredentials() throws IOException {
    if (GithubActions.isActionsEnvironment()) {
      System.out.println("Skipping credentials update: detected non local environment");
      return;
    }
    System.out.println("Updating credentials...");
    GithubSecrets.updateCredentials();
    System.out.println("Updated credentials!");
  }
}