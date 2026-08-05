import com.microsoft.playwright.*;
import org.testng.annotations.*;

public class AuthenticationTests {
    private Playwright playwright;
    private Browser browser;
    private BrowserContext context;
    private Page page;

    @BeforeClass
    public void setUp() {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
    }

    @BeforeMethod
    public void createContextAndPage() {
        context = browser.newContext();
        page = context.newPage();
    }

    @Test
    public void testVerifyNavigationToLoginPage() {
        // Navigate to the login page
        page.navigate("https://emr.weva.ai/login");

        // Assert that the login page is loaded successfully
        page.waitForSelector("button.button", new Page.WaitForSelectorOptions().setState(WaitForSelectorState.VISIBLE));
        assert page.url().equals("https://emr.weva.ai/login");
    }

    @AfterMethod
    public void closeContext() {
        context.close();
    }

    @AfterClass
    public void tearDown() {
        browser.close();
        playwright.close();
    }
}