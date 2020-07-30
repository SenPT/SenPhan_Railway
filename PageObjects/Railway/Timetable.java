package Railway;
import Constant.Constant;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import java.util.ArrayList;
import java.util.List;

public class Timetable extends GeneralPage{
    public WebElement getlblTicketPageTitle(){
        return Constant.WEBDRIVER.findElement(By.xpath("//div[@id='content']/h1"));
    }
    public WebElement getlblPriceTableTitle(){
        return Constant.WEBDRIVER.findElement(By.xpath("//tr[@class='TableSmallHeader']/th"));
    }

    public void checkPrice(String departFrom, String arriveAt)
    {
        JavascriptExecutor jse = (JavascriptExecutor) Constant.WEBDRIVER;
        jse.executeScript("window.scrollTo(0,document.body.scrollHeight);");

        Constant.WEBDRIVER.findElement(By.xpath("//td[text()='"+ departFrom +"']//following-sibling::td[text()='"+ arriveAt +"']//following-sibling::td//a[contains(@href,'Price')]")).click();
    }

    public void verifyTicketPriceTable(final String hs, final String ss, final String ssc, final String hb, final String sb, final String sbc)
    {
        List<WebElement> ticketPriceTable = Constant.WEBDRIVER.findElements(By.xpath("//table//tr[@class = 'TableSmallHeader']//following-sibling::tr[2]//td"));

        List<String> expectedResult = new ArrayList<String>(){
            {
                add(hs);
                add(ss);
                add(ssc);
                add(hb);
                add(sb);
                add(sbc);
            }
        };

        //Compare list actual result price with expected result price
        for (int i=0; i<expectedResult.size(); i++)
        {
            String actualResult = ticketPriceTable.get(i).getText();
            Assert.assertEquals(actualResult,expectedResult.get(i),"Don't match");
        }

    }
}
