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
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class TC14_BookTickets extends BaseTest {
    @Test
    public void TC14() throws IOException
    {
        System.out.println("TC14 - User can book many tickets at a time");

        System.out.println("Step 1: Navigate to QA Railway Website");
        HomePage homePage = new HomePage();
        homePage.open();

        System.out.println("Step 2: Login with valid account");
        LoginPage loginPage = homePage.gotoLoginPage();
        loginPage.login(Constant.USERNAME, Constant.PASSWORD);

        System.out.println("Step 3: Go to tab Book Ticket");
        BookTicket bookTicket = homePage.gotoBookTicket();

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

                /*bookTicket.bookTicket(Constant.DEPARTDATE, Constant.DEPARTFROM,Constant.ARRIVEAT, Constant.SEATTYPE, Constant.TICKETAMOUNT);*/
                System.out.println("Step 4: Book ticket");
                bookTicket.bookTicket(departDate, departFrom, arriveAt, seatType, ticketAmount);

                System.out.println("Verify book ticket sucessfully");
                String actualMes = bookTicket.getMesBookTicket();
                Assert.assertEquals(actualMes,"Ticket booked successfully!","Don't match");
                bookTicket.verifyBookTicket(departFrom, arriveAt,seatType,departDate,bookDate(),expiredDay(),ticketAmount,ticketPrice);

            }
        }
        Myticket myticket = homePage.gotoMyticket();
        myticket.cancelTicket("Nha Trang","Sài Gòn");

        new WebDriverWait(Constant.WEBDRIVER, 15).until(ExpectedConditions.alertIsPresent());
        Constant.WEBDRIVER.switchTo().alert().accept();
    }
}