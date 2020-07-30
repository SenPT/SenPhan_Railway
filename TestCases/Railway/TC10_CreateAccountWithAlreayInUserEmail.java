package Railway;

import Common.Utilities;
import Constant.Constant;
import au.com.bytecode.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TC10_CreateAccountWithAlreayInUserEmail extends BaseTest {
    @Test
    public void TC10() throws IOException {

        System.out.println("TC10 - User can't create account with an already in-use email");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click tab Register");
        RegisterPage register = homePage.gotoRegister();

        System.out.println("Step3: register with already in user email and verify");
        CSVReader reader = new CSVReader(new FileReader(Utilities.getProjectPath()+"\\DataObjects\\SignUp.csv"));
        String[] cell;

        while ((cell = reader.readNext()) != null) {
            for (int i = 0; i < 1; i++) {
                String password = cell[i+1];
                String pID = cell[i+2];

                String actualMes =  register.registerPage(Constant.USERNAME, password, password, pID).getMessageRegister();
                Assert.assertEquals(actualMes,"This email address is already in use.","Don't match");
            }
        }
    }
}
