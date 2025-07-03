import com.microsoft.playwright.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class LoginTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;

    @BeforeClass
    public void setup() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        page = browser.newPage();
    }

    @AfterClass
    public void teardown() {
        browser.close();
        playwright.close();
    }

    @Test
    public void loginTest() {
        page.navigate("https://www.saucedemo.com/");
        page.locator("#user-name").fill("standard_user");
        page.locator("#password").fill("secret_sauce");
        page.locator("#login-button").click();
        Assert.assertTrue(page.url().contains("inventory.html"));
    }
}
