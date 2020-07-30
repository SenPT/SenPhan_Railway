package Railway;

import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC06_RedirectHomePageAfterLogin extends BaseTest {
    @Test
    public  void TC06(){
        System.out.println("TC06 - User is redirected to Home page after logging out ");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Login with valid account");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Step 3: Click tab Contact");
        homePage.getTabContact().click();

        System.out.println("Step 4: Click tab Logout");
        loginPage.gotoLogoutPage();

        System.out.println("Verify user is redirected to Home page after logging out");
        String actualMes = homePage.getHomePageHeader();
        Assert.assertEquals(actualMes,"Welcome to Safe Railway","Don't match");
        try {
            homePage.getTextLogoutTab();
        }
        catch (NoSuchElementException exception)
        {
            Assert.assertEquals("1","1");
        }
    }
}