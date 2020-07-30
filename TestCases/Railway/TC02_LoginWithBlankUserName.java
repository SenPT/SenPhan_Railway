package Railway;

import Constant.Constant;
import org.testng.Assert;
import org.testng.annotations.Test;

public class TC02_LoginWithBlankUserName extends BaseTest{
    @Test
    public void TC02(){
        System.out.println("TC02 - User can't login with blank \"Username\" textbox");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click on Login tab");
        LoginPage loginPage = homePage.gotoLoginPage();

        System.out.println("Step 3: Login with blank username");
        String actualMesg = loginPage.login("", Constant.PASSWORD).getBlankUserNameMsg();

        System.out.println("Verify user can not login sucessfully with blank username");
        Assert.assertEquals(actualMesg,"There was a problem with your login and/or errors exist in your form.","Message appears don't match");

    }
}