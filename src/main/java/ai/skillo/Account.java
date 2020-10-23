package ai.skillo;

public class Account {

  private String uniqueEmail;
  private String password;
  private String email;
  private String name;
  private String lastname;
  private String phone;
  private String uniquePassword;

  public Account(String name, String lastname, String phone, String email, String password,
      String uniquePassword, String uniqueEmail) {
    this.uniqueEmail = uniqueEmail;
    this.password = password;
    this.email = email;
    this.name = name;
    this.lastname = lastname;
    this.phone = phone;
    this.uniquePassword = uniquePassword;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getUniquePassword() {
    return uniquePassword;
  }

  public void setUniquePassword(String uniquePassword) {
    this.uniquePassword = uniquePassword;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  public String getPhone() {
    return phone;
  }

  public String getUniqueEmail() {
    return uniqueEmail;
  }

  public void setUniqueEmail(String uniqueEmail) {
    this.uniqueEmail = uniqueEmail;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }
}

