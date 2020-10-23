package ai.skillo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Factory;
import org.testng.annotations.Test;

public class RegistrationTests {

  private static WebDriver driver = null;

  private Account account = AccountFactory.getAccountForRegistration();

  @BeforeClass
  public void initBrowser() {
    System.setProperty("webdriver.chrome.driver", "src/chromedriver.exe");
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--ignore-ssl-errors=yes");
    options.addArguments("--ignore-certificate-errors");
    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
  }

  @Test(priority = 1)
  public void registrationSiteTest() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();
    Thread.sleep(1000);

    By registerButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[text() ='Register']");
    WebElement registerButtonElement = driver.findElement(registerButtonLoctor);
    registerButtonElement.click();

    By firstNameInputLocator = By.xpath("//input[@name = 'firstname']");
    WebElement firstNameInputElement = driver.findElement(firstNameInputLocator);
    firstNameInputElement.sendKeys(account.getName());

    By lastNameInputLocator = By.xpath("//input[@name = 'lastname']");
    WebElement lastNameInputElement = driver.findElement(lastNameInputLocator);
    lastNameInputElement.sendKeys(account.getLastname());

    By emailInputLocator = By.xpath("//input[@name = 'email']");
    WebElement emailInputElement = driver.findElement(emailInputLocator);
    emailInputElement.sendKeys(account.getUniqueEmail());
    Thread.sleep(2000);

    By telephoneInputLocator = By.xpath("//input[@name = 'telephone']");
    WebElement telephoneInputElement = driver.findElement(telephoneInputLocator);
    telephoneInputElement.sendKeys(account.getPhone());

    By passwordInputLocator = By.xpath("//input[@name = 'password']");
    WebElement passwordInputElement = driver.findElement(passwordInputLocator);
    passwordInputElement.sendKeys(account.getPassword());

    By confirmInputLocator = By.xpath("//input[@name = 'confirm']");
    WebElement cnfirmInputElement = driver.findElement(confirmInputLocator);
    cnfirmInputElement.sendKeys(account.getPassword());

    By privacyPolicyButtonLoctor = By.xpath("//input[@name = 'agree']");
    WebElement privacyPolicyButtonElement = driver.findElement(privacyPolicyButtonLoctor);
    privacyPolicyButtonElement.click();

    By continueButtonLoctor = By.xpath("//input[@value= 'Continue']");
    WebElement continueButtonElement = driver.findElement(continueButtonLoctor);
    continueButtonElement.click();

    By titleLocator = By.xpath("//div[@id = 'content']/h1");
    WebElement titleElement = driver.findElement(titleLocator);
    String actualTitleValue = titleElement.getText();
    Assert.assertEquals(actualTitleValue, "Your Account Has Been Created!",
        "Page title is not correct!");

    By continueAfterCreatedAccountLoctor = By
        .xpath("//*[@class = 'pull-right']//a[text() ='Continue']");
    WebElement continueAfterCreatedAccountnElement = driver
        .findElement(continueAfterCreatedAccountLoctor);
    continueAfterCreatedAccountnElement.click();

    By myAccountButtonLoctor1 = By.xpath(
        "//*[@class = 'list-inline']//a[@title = 'My Account']"); //ВОПРОС - если используем один и тот же локатор, то нужно ли его иначе называть или можно использовать повторно предыдущий
    WebElement myAccountButtonElement1 = driver.findElement(myAccountButtonLoctor1);
    myAccountButtonElement1.click();

    By logoutLoctor = By
        .xpath("//*[@class = 'dropdown-menu dropdown-menu-right']//a[contains(text(),'Logout')]");
    WebElement logoutElement = driver.findElement(logoutLoctor);
    logoutElement.click();
  }

  @Test(priority = 2)
  public void checkExcludingNotMatchingPasswords() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    Thread.sleep(2000);
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();

    By registerButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[text() ='Register']");
    WebElement registerButtonElement = driver.findElement(registerButtonLoctor);
    registerButtonElement.click();

    By firstNameInputLocator = By.xpath("//input[@name = 'firstname']");
    WebElement firstNameInputElement = driver.findElement(firstNameInputLocator);
    firstNameInputElement.sendKeys(account.getName());

    By lastNameInputLocator = By.xpath("//input[@name = 'lastname']");
    WebElement lastNameInputElement = driver.findElement(lastNameInputLocator);
    lastNameInputElement.sendKeys(account.getLastname());

    By emailInputLocator = By.xpath("//input[@name = 'email']");
    WebElement emailInputElement = driver.findElement(emailInputLocator);
    emailInputElement.sendKeys(account.getEmail());
    Thread.sleep(3000);

    By telephoneInputLocator = By.xpath("//input[@name = 'telephone']");
    WebElement telephoneInputElement = driver.findElement(telephoneInputLocator);
    telephoneInputElement.sendKeys(account.getPhone());

    By passwordInputLocator = By.xpath("//input[@name = 'password']");
    WebElement passwordInputElement = driver.findElement(passwordInputLocator);
    passwordInputElement.sendKeys(account.getPassword());

    By confirmInputLocator = By.xpath("//input[@name = 'confirm']");
    WebElement cnfirmInputElement = driver.findElement(confirmInputLocator);
    cnfirmInputElement.sendKeys(account.getUniquePassword());

    By privacyPolicyButtonLoctor = By.xpath("//input[@name = 'agree']");
    WebElement privacyPolicyButtonElement = driver.findElement(privacyPolicyButtonLoctor);
    privacyPolicyButtonElement.click();

    By continueButtonLoctor = By.xpath("//input[@value= 'Continue']");
    WebElement continueButtonElement = driver.findElement(continueButtonLoctor);
    continueButtonElement.click();

    By notMatchingPasswordLocator = By.xpath("//*[@class = 'text-danger']");
    WebElement notMatchingPasswordElement = driver.findElement(notMatchingPasswordLocator);
    String actualTitleValue = notMatchingPasswordElement.getText();
    Assert.assertEquals(actualTitleValue, "Password confirmation does not match password!",
        "Page title is not correct!");
  }

  @Test(priority = 3)
  public void checkExcludingDoubleEmails() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();

    By registerButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[text() ='Register']");
    WebElement registerButtonElement = driver.findElement(registerButtonLoctor);
    registerButtonElement.click();

    By firstNameInputLocator = By.xpath("//input[@name = 'firstname']");
    WebElement firstNameInputElement = driver.findElement(firstNameInputLocator);
    firstNameInputElement.sendKeys(account.getName());

    By lastNameInputLocator = By.xpath("//input[@name = 'lastname']");
    WebElement lastNameInputElement = driver.findElement(lastNameInputLocator);
    lastNameInputElement.sendKeys(account.getLastname());

    By emailInputLocator = By.xpath("//input[@name = 'email']");
    WebElement emailInputElement = driver.findElement(emailInputLocator);
    emailInputElement.sendKeys(account.getEmail());
    Thread.sleep(1000);

    By telephoneInputLocator = By.xpath("//input[@name = 'telephone']");
    WebElement telephoneInputElement = driver.findElement(telephoneInputLocator);
    telephoneInputElement.sendKeys(account.getPhone());

    By passwordInputLocator = By.xpath("//input[@name = 'password']");
    WebElement passwordInputElement = driver.findElement(passwordInputLocator);
    passwordInputElement.sendKeys(account.getPassword());

    By confirmInputLocator = By.xpath("//input[@name = 'confirm']");
    WebElement cnfirmInputElement = driver.findElement(confirmInputLocator);
    cnfirmInputElement.sendKeys(account.getPassword());

    By privacyPolicyButtonLoctor = By.xpath("//input[@name = 'agree']");
    WebElement privacyPolicyButtonElement = driver.findElement(privacyPolicyButtonLoctor);
    privacyPolicyButtonElement.click();

    By continueButtonLoctor = By.xpath("//input[@value= 'Continue']");
    WebElement continueButtonElement = driver.findElement(continueButtonLoctor);
    continueButtonElement.click();

    By emailAlreadyRegisteredLocator = By
        .xpath("//*[text() = ' Warning: E-Mail Address is already registered!']");
    WebElement emailAlreadyRegisteredElement = driver.findElement(emailAlreadyRegisteredLocator);
    String actualTitleValue = emailAlreadyRegisteredElement.getText();
    Assert.assertEquals(actualTitleValue, "Warning: E-Mail Address is already registered!",
        "Page title is not correct!");
  }

  @AfterClass
  public void closeBrowser() {
    driver.quit();
  }
}
