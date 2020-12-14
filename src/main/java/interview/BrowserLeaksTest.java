package interview;

import interview.propertyFile.PropertyFile;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BrowserLeaksTest extends PageObject {

    private PropertyFile propertyFile;

    private static final String WEBGL_URL = "https://browserleaks.com/webgl";
    private static final String WEBGL_TITLE = "WebGL Browser Report, WebGL Fingerprinting, WebGL 2 Test - BrowserLeaks";
    private static final String CANVAS_URL = "https://browserleaks.com/canvas";
    private static final String CANVAS_TITLE = "Canvas Fingerprinting - BrowserLeaks";

    @FindBy(id = "webgl_fp_context")
    private WebElement webGLReportHashElement;
    @FindBy(id = "webgl_fp_img")
    private WebElement webGLImageHashElement;
    @FindBy(id = "crc")
    private WebElement canvasSignatureElement;
    @FindBy(id = "canvas_file_md5")
    private WebElement canvasPngHashElement;

    @Test
    public void webglTest() {
        driver.navigate().to(WEBGL_URL);
        assertThat(driver.getTitle(), is(WEBGL_TITLE));

        String webGLReportHash = webGLReportHashElement.getText();
        String webGLImageHash = webGLImageHashElement.getText();
        String webGLReportHashExpectedValue = PropertyFile.getPropertyFileValue("webGLReportHash");
        String webGLImageHashExpectedValue = PropertyFile.getPropertyFileValue("webGLImageHash");

        assertThat(webGLReportHash, is(not(webGLReportHashExpectedValue)));
        assertThat(webGLImageHash, is(not(webGLImageHashExpectedValue)));
        assertThat(webGLReportHash.length(), is(webGLReportHashExpectedValue.length()));
        assertThat(webGLImageHash.length(), is(webGLImageHashExpectedValue.length()));
    }

    @Test
    public void canvasTest() {
        driver.navigate().to(CANVAS_URL);
        assertThat(driver.getTitle(), is(CANVAS_TITLE));

        String canvasSignature = canvasSignatureElement.getText().split("\\s+")[1];
        String canvasPngHash = canvasPngHashElement.getText();
        String canvasSignatureExpectedValue = PropertyFile.getPropertyFileValue("canvasSignature");
        String canvasPngHashExpectedValue = PropertyFile.getPropertyFileValue("canvasPngHash");

        assertThat(canvasSignature, is(not(canvasSignatureExpectedValue)));
        assertThat(canvasPngHash, not(is(canvasPngHashExpectedValue)));
        assertThat(canvasSignature.length(), is(canvasSignatureExpectedValue.length()));
        assertThat(canvasPngHash.length(), is(canvasPngHashExpectedValue.length()));
    }
}
