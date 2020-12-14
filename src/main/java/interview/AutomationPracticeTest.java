package interview;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.io.File;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class AutomationPracticeTest extends PageObject {

    private static final String SUBJECT_HEADING = "Webmaster";
    private static final String EMAIL = "test@toptal.com";
    private static final String ORDER_REF = "R108";
    private static final String MESSAGE_TEXT = "Complaint about order R108";
    private static final String PAGE_URL = "http://automationpractice.com/index.php";
    private static final String PAGE_TITLE = "Contact us - My Store";
    private static final String SUCCESS_ALERT = "Your message has been successfully sent to our team.";

    @FindBy(xpath = "//a[@title='Contact Us']")
    private WebElement headerContactUs;
    @FindBy(id = "id_contact")
    private WebElement subjectHeadingSelect;
    @FindBy(id = "email")
    private WebElement emailInput;
    @FindBy(id = "id_order")
    private WebElement orderReference;
    @FindBy(id = "fileUpload")
    private WebElement attacheFileInput;
    @FindBy(id = "message")
    private WebElement messageInput;
    @FindBy(xpath = "//span[text()='Send']")
    private WebElement sendBtn;
    @FindBy(xpath = "//p[@class='alert alert-success']")
    private WebElement contactUsAlert;

    @Test
    public void automationPracticeTest() {
        driver.navigate().to(PAGE_URL);
        headerContactUs.click();
        assertThat(driver.getTitle(), is(PAGE_TITLE));

        subjectHeadingSelect.click();
        driver.findElement(By.xpath("//select[@id='id_contact']/option[text()='" + SUBJECT_HEADING + "']")).click();

        emailInput.click();
        emailInput.sendKeys(EMAIL);

        orderReference.click();
        orderReference.sendKeys(ORDER_REF);
        attacheFileInput.sendKeys(new File("image/qaImage.png").getAbsolutePath());

        messageInput.click();
        messageInput.sendKeys(MESSAGE_TEXT);

        sendBtn.click();

        assertThat(contactUsAlert.getText(), is(SUCCESS_ALERT));
    }
}
