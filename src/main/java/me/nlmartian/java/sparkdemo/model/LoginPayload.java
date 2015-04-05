package me.nlmartian.java.sparkdemo.model;

/**
 * Created by nlmartian on 4/4/15.
 */
public class LoginPayload implements Validable {
  private String email;
  private String password;


  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public boolean isValid() {
    return email != null && !email.isEmpty()
        && password != null && !password.isEmpty();
  }
}
