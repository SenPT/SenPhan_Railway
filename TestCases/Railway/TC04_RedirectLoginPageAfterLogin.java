package Railway;

import Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_RedirectLoginPageAfterLogin extends BaseTest {
    @Test
    public void TC04() {
        System.out.println("TC04 - User is redirected to Book ticket page after logging in");
        System.out.println("Step 1: ");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click tab Book Ticket");
        BookTicket bookTicket = homePage.gotoBookTicket();
        LoginPage login = new LoginPage();

        System.out.println("Verify user is directed to Login page");
        String actualMes = login.getlblHeaderLoginPage().getText();
        Assert.assertEquals(actualMes, "Login Page", "User is not directed to Login page");

        System.out.println("Step 3: Login with valid account");
        login.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Verify Book ticket page display");
        String actualMes1 = login.getlblHeaderLoginPage().getText();
        Assert.assertEquals(actualMes1, "Book ticket", "User is not directed to Book ticket");

        System.out.println("Verify form Book ticket open");
        if (homePage.getFormBookTicket().isDisplayed() == true) {
            Assert.assertEquals("1","1");
        } else {
            Assert.assertEquals("1","2");
        }

    }
}