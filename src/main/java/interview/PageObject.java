package interview;

import interview.propertyFile.PropertyFile;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;

public class PageObject {

    public ChromeDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
        driver = new ChromeDriver();
    }

    @After
    public void closeBrowser() {
        driver.close();
    }
}
