package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC04_RedirectLoginPageAfterLogin extends BaseTest {
    @Test
    public void TC04() {
        System.out.println("TC04 - User is redirected to Book ticket page after logging in");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Go to tab Book Ticket");
        homePage.gotoBookTicket();

        System.out.println("Step 3: Login with valid account ");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.gotoLoginPage();
        String actualMes = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getlblHomeHeader().getText();

        System.out.println("Verify redirected to Book ticket page after logging in");
      /*  String actualMes = loginPage.getlblHeaderLoginPage().getText();*/
        Assert.assertEquals(actualMes, "Login Page", "User is not directed to Login page");

        //verify form bookticket is displayed
        if (homePage.getFormBookTicket().isDisplayed() == true) {
            Assert.assertEquals("1","1");
        } else {
            Assert.assertEquals("1","2");
        }
    }
}