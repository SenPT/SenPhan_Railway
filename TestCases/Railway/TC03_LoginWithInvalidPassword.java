package Railway;

import Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC03_LoginWithInvalidPassword extends BaseTest{
    @Test
    public void TC03(){
        System.out.println("TC03 - User cannot log into Railway with invalid password ");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click on Login tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        System.out.println("Step 3: Login with invalid password");
        String actualMesg = loginPage.login(Constant.USERNAME, "invalid password").getBlankUserNameMsg();

        System.out.println("Verify user can not login sucessfully with invalid password");
        Assert.assertEquals(actualMesg,"Invalid username or password. Please try again.","Message appears don't match");
    }
}