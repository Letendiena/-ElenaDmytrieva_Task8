package ai.skillo;

import java.util.Arrays;
import java.util.List;
import org.testng.TestNG;

public class Runner {

  public static void main(String[] args) {
    TestNG testNG = new TestNG();
    List<String> file = Arrays.asList("src/suite.xml");
    testNG.setTestSuites(file);
    testNG.run();
  }
}
