package ai.skillo;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTests {

  private static WebDriver driver = null;

  private Account account = AccountFactory.getAccountForLogin();

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
  public void loginSiteTest() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();

    By loginButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[text() = 'Login']");
    WebElement loginButtonElement = driver.findElement(loginButtonLoctor);
    loginButtonElement.click();

    By emailInputLocator = By.xpath("//input[@id = 'input-email']");
    WebElement emailInputElement = driver.findElement(emailInputLocator);
    emailInputElement.sendKeys(account.getEmail());

    By passwordInputLocator = By.xpath("//input[@id = 'input-password']");
    WebElement passwordInputElement = driver.findElement(passwordInputLocator);
    passwordInputElement.sendKeys(account.getPassword());

    By loginInputLocator = By.xpath("//input[@value = 'Login']");
    WebElement loginInputElement = driver.findElement(loginInputLocator);
    loginInputElement.click();

    By myOrdersExistLocator = By.xpath("//*[@id = 'content']//*[text() = 'My Orders']");
    WebElement myOrdersExistElement = driver.findElement(myOrdersExistLocator);
    String actualTitleValue = myOrdersExistElement.getText();
    Assert.assertEquals(actualTitleValue, "My Orders", "Page title is not correct!");
  }

  @Test(priority = 2)
  public void logoutSiteTest() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();

    By logoutLoctor = By
        .xpath("//*[@class = 'dropdown-menu dropdown-menu-right']//a[contains(text(),'Logout')]");
    WebElement logoutElement = driver.findElement(logoutLoctor);
    logoutElement.click();

    By accountLogoutLocator = By.xpath("//div[@id = 'content']/h1");
    WebElement accountLogoutElement = driver.findElement(accountLogoutLocator);
    String actualTitleValue = accountLogoutElement.getText();
    Assert.assertEquals(actualTitleValue, "Account Logout", "Page title is not correct!");
  }

  @Test(priority = 3)
  public void emptyCredentialsLoginTest() throws InterruptedException {
    driver.get("https://electonicstore.com/");
    By myAccountButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[@title = 'My Account']");
    WebElement myAccountButtonElement = driver.findElement(myAccountButtonLoctor);
    myAccountButtonElement.click();

    By loginButtonLoctor = By.xpath("//*[@class = 'list-inline']//a[text() = 'Login']");
    WebElement loginButtonElement = driver.findElement(loginButtonLoctor);
    loginButtonElement.click();

    By emailInputLocator = By.xpath("//input[@id = 'input-email']");
    WebElement emailInputElement = driver.findElement(emailInputLocator);
    emailInputElement.sendKeys("");

    By passwordInputLocator = By.xpath("//input[@id = 'input-password']");
    WebElement passwordInputElement = driver.findElement(passwordInputLocator);
    passwordInputElement.sendKeys("");

    By loginInputLocator = By.xpath("//input[@value = 'Login']");
    WebElement loginInputElement = driver.findElement(loginInputLocator);
    loginInputElement.click();

    By emptyCredentialsLocator = By
        .xpath("//*[text() = ' Warning: No match for E-Mail Address and/or Password.']");
    WebElement emptyCredentialsElement = driver.findElement(emptyCredentialsLocator);
    String actualTitleValue = emptyCredentialsElement.getText();
    Assert.assertEquals(actualTitleValue, "Warning: No match for E-Mail Address and/or Password.",
        "Page title is not correct!");
  }

  @AfterClass
  public void closeBrowser() {
    driver.quit();
  }
}

