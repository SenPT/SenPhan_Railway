package Railway;

import Common.Utilities;
import Constant.Constant;
import au.com.bytecode.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TC07_CreateNewAccount extends BaseTest{

    @Test
    public void TC07() throws IOException
    {
        System.out.println("TC07 - User can create new account");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Click tab register");
        RegisterPage register = homePage.gotoRegister();

        System.out.println("Read data in file csv");
        CSVReader reader = new CSVReader(new FileReader(Utilities.getProjectPath()+"\\DataObjects\\SignUp.csv"));
        String[] cell;

        while ((cell = reader.readNext()) != null) {
            for (int i = 0; i < 1; i++) {

                String new_username = cell[i];
                String password = cell[i+1];
                String pID = cell[i+2];

                System.out.println("Verify user can create new account");
                String actualMes = register.registerPage(new_username, password, password, pID).getRegisterSucess();
                Assert.assertEquals(actualMes,"Registration Confirmed! You can now log in to the site.","Account is not registered sucessfully");
            }
        }
    }
}