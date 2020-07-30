package Railway;

import Constant.Constant;
import org.testng.annotations.Test;

public class TC09_NewPassAndConfirmPassDifferent extends BaseTest{
    @Test
    public void TC09(){
        System.out.println("TC09 - User can't change password when \"New Password\" and \"Confirm Password\" are different.");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Go to Login tab and Login with valid account");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Step 3: Go to tab Change Password and Change password");
        ChangePassword changePass = homePage.gotoChangePassword();
        changePass.changePassword(Constant.PASSWORD, "222222222", "doesn't match");
    }
}