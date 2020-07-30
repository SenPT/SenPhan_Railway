package Railway;

import Common.Utilities;
import Constant.Constant;
import au.com.bytecode.opencsv.CSVReader;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TC15_CheckPriceTickets extends BaseTest {

    @Test
    public void TC15() throws IOException
    {
        System.out.println("TC15 - \"Ticket price\" page displays with ticket details after clicking on \"check price\" link in \"Train timetable\" page");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Login with valid account");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Step 3: Click tab Timetable");
        Timetable timetb = homePage.gotoTimeTable();
        timetb.checkPrice("Đà Nẵng","Sài Gòn");

        System.out.println("Verify ticket page is loaded");
        String acutalMes1 = timetb.getlblTicketPageTitle().getText();
        Assert.assertEquals(acutalMes1,"Ticket Price","Don't match");

        String actualMes2 = timetb.getlblPriceTableTitle().getText();
        Assert.assertEquals(actualMes2,"Ticket price from Đà Nẵng to Sài Gòn","Don't match");

        System.out.println("Verify ticket price table");
        CSVReader reader = new CSVReader(new FileReader(Utilities.getProjectPath()+"\\DataObjects\\SeatType.csv"));
        String[] cell;
        while ((cell = reader.readNext()) != null){
            for (int i=0; i<1; i++){
                String hs = cell[i];
                String ss = cell[i+1];
                String ssc = cell[i+2];
                String hb = cell[i+3];
                String sb = cell[i+4];
                String sbc = cell[i+5];
                timetb.verifyTicketPriceTable(hs,ss,ssc,hb,sb,sbc);
            }
        }
    }
}