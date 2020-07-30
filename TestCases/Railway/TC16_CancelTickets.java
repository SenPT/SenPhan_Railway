package Railway;

import Common.Utilities;
import Constant.Constant;
import au.com.bytecode.opencsv.CSVReader;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.FileReader;
import java.io.IOException;

public class TC16_CancelTickets extends BaseTest {
    @Test
    public void TC16() throws IOException
    {
        System.out.println("TC16 - User can cancel a ticket");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Login with valid account");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Step 3: Go to Tab Book ticket and book ticket");
        BookTicket bookTicket = homePage.gotoBookTicket();
       /* bookTicket.bookTicket(Constant.DEPARTDATE,Constant.DEPARTFROM,Constant.ARRIVEAT, Constant.SEATTYPE, Constant.TICKETAMOUNT);*/

        CSVReader reader = new CSVReader(new FileReader(Utilities.getProjectPath()+"\\DataObjects\\Tickets.csv"));
        String[] cell;
        while ((cell = reader.readNext()) != null) {
            for (int i = 0; i < 1; i++) {
                String departDate = cell[i];
                String departFrom = cell[i + 1];
                String arriveAt = cell[i + 2];
                String seatType = cell[i + 3];
                String ticketAmount = cell[i + 4];
                String ticketPrice = cell[i + 5];
                String status = cell[i+6];

                bookTicket.bookTicket(departDate,departFrom,arriveAt, seatType, ticketAmount);

                System.out.println("Step 4: Cancel ticket");
                Myticket myticket = homePage.gotoMyticket();
                myticket.cancelTicket(departFrom,arriveAt);

                new WebDriverWait(Constant.WEBDRIVER, 15).until(ExpectedConditions.alertIsPresent());
                Constant.WEBDRIVER.switchTo().alert().accept();

                System.out.println("Verify user can cancel ticket");
                FilterTicket filter = new FilterTicket();
                filter.filterTicket(departFrom, arriveAt, departDate,status);

                String actualMess =  filter.getResultFilter().getText();
                Assert.assertEquals(actualMess,"No result found!", "Don't match");
            }
        }
    }
}