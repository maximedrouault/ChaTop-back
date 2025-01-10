package org.chatop.chatopback.exception;

public class CustomUsernameNotFoundException extends RuntimeException {

  public CustomUsernameNotFoundException(String email) {
    super("User not found with email: " + email);
  }
}
