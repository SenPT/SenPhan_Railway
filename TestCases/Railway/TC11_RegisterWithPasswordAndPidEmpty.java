package Railway;

import org.testng.Assert;
import org.testng.annotations.Test;

public class TC11_RegisterWithPasswordAndPidEmpty extends BaseTest {
    @Test
    public void TC11(){
        System.out.println("TC11 - User can't create account while password and PID fields are empty");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click tab Register");
        RegisterPage register = homePage.gotoRegister();
        RegisterPage reg = new RegisterPage();

        System.out.println("Verify user can not register with password and PID is empty");
        String actualmes1 = register.registerPage("abcn1@mailinator.com","","","").getMessageRegister();
        String actualMes2 = register.getMesPassRgister();
        String actualMes3 = register.getMsgPid();

        Assert.assertEquals(actualmes1,"There're errors in the form. Please correct the errors and try again.","not match");
        Assert.assertEquals(actualMes2,"Invalid password length","not match");
        Assert.assertEquals(actualMes3,"Invalid ID length","not match");
    }
}