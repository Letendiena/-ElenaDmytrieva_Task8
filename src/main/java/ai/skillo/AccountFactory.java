package ai.skillo;

import net.bytebuddy.utility.RandomString;

public class AccountFactory {

  private static RandomString random = new RandomString();
  private static final String UNIQEMAIL = "Elena" + random.make(2) + "@gmail.com";
  private static final String PASSWORD = "112233";
  private static final String UNIQPASSWORD = "112233" + random.make(2);
  private static final String EMAIL = "Elena@gmail.com";
  private static final String NAME = "Elena";
  private static final String LASTNAME = "Dmytrieva";
  private static final String PHONE = "1267185698";

  public static Account getAccountForRegistration() {
    Account account = new Account(NAME, LASTNAME, PHONE, EMAIL, PASSWORD, UNIQPASSWORD, UNIQEMAIL);
    return account;
  }

  public static Account getAccountForLogin() {
    Account account = new Account(NAME, LASTNAME, PHONE, EMAIL, PASSWORD, UNIQPASSWORD, UNIQEMAIL);
    return account;
  }
}

