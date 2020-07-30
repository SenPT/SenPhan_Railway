package Railway;

import Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;


public class TC01_LoginValidAccount extends BaseTest{
    @Test
    public void TC01(){
        System.out.println("TC01 - User can log into Railway with valid username and password");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click on Login tab");
        LoginPage loginPage= homePage.gotoLoginPage();

        System.out.println("Step 3: Enter valid account");
        String actualMsg = loginPage.login(Constant.USERNAME, Constant.PASSWORD).getWelcomeMessage();

        System.out.println("Verify login sucessfully");
        String expectedMsg = "Welcome "+ Constant.USERNAME;
        Assert.assertEquals(actualMsg,expectedMsg, "Welcome message is not displayed as expected");
    }
}