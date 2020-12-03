package interview;

import interview.propertyFile.PropertyFile;
import org.junit.Test;
import org.openqa.selenium.By;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class BrowserLeaksTest extends PageObject {

    private PropertyFile propertyFile;

    @Test
    public void webglTest() {
        driver.navigate().to("https://browserleaks.com/webgl");
        assertThat(driver.getTitle(), is("WebGL Browser Report, WebGL Fingerprinting, WebGL 2 Test - BrowserLeaks"));
        String webGLReportHash = driver.findElement(By.id("webgl_fp_context")).getText();
        String webGLImageHash = driver.findElement(By.id("webgl_fp_img")).getText();
        String webGLReportHashExpectedValue = PropertyFile.getPropertyFileValue("webGLReportHash");
        String webGLImageHashExpectedValue = PropertyFile.getPropertyFileValue("webGLImageHash");

        assertThat(webGLReportHash, is(not(webGLReportHashExpectedValue)));
        assertThat(webGLImageHash, is(not(webGLImageHashExpectedValue)));
        assertThat(webGLReportHash.length(), is(webGLReportHashExpectedValue.length()));
        assertThat(webGLImageHash.length(), is(webGLImageHashExpectedValue.length()));
    }

    @Test
    public void canvasTest() {
        driver.navigate().to("https://browserleaks.com/canvas");
        assertThat(driver.getTitle(), is("Canvas Fingerprinting - BrowserLeaks"));
        String canvasSignature = driver.findElement(By.id("crc")).getText().split("\\s+")[1];
        String canvasPngHash = driver.findElement(By.id("canvas_file_md5")).getText();
        String canvasSignatureExpectedValue = PropertyFile.getPropertyFileValue("canvasSignature");
        String canvasPngHashExpectedValue = PropertyFile.getPropertyFileValue("canvasPngHash");

        assertThat(canvasSignature, is(not(canvasSignatureExpectedValue)));
        assertThat(canvasPngHash, not(is(canvasPngHashExpectedValue)));
        assertThat(canvasSignature.length(), is(canvasSignatureExpectedValue.length()));
        assertThat(canvasPngHash.length(), is(canvasPngHashExpectedValue.length()));
    }
}
