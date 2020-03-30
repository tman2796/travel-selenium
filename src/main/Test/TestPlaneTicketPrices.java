import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.*;
import org.testng.annotations.BeforeGroups;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import static org.testng.Assert.assertEquals;
import static org.testng.AssertJUnit.assertNotNull;

public class TestPlaneTicketPrices {
    private WebDriver driver;
    FlightAutomation flightAutomation = new FlightAutomation(this.driver);

    public TestPlaneTicketPrices() throws FileNotFoundException {
    }

    @BeforeGroups
    public void setup()throws NoSuchFieldException{
        flightAutomation.start();
    }

    public FlightAutomation getFlightAutomation() {
        return flightAutomation;
    }
    FlightAutomation flightPrice = getFlightAutomation();

    @Test
    public void priceArrayTest() throws FileNotFoundException {
        assertNotNull(flightPrice.priceListCancun);
        assertNotNull(flightPrice.priceListDenver);
        assertNotNull(flightPrice.priceListAmsterdam);
        assertNotNull(flightPrice.priceListLasVegas);
        assertNotNull(flightPrice.priceListMadrid);
        assertNotNull(flightPrice.priceListMilan);
        assertNotNull(flightPrice.priceListParis);
        assertNotNull(flightPrice.priceListRome);
        assertNotNull(flightPrice.priceListSingapore);
    }
    @Test
    public void lowestPriceTest(){
        ArrayList<Integer> lowList = new ArrayList<>();
        for(int i = 0; i <= 10; i++) {
            lowList.add(i);
        }
        flightPrice.determineLowestPrice(lowList);
        assertEquals(flightPrice.determineLowestPrice(lowList), 0);
    }
    @Test
    public void testReturnPrice(){
        for(int i = 0; i < flightPrice.priceListCancun.size(); i++){
            System.out.println(flightPrice.priceListCancun.get(i));
//            assertEquals(flightPrice.priceListCancun.get(0), );
        }
    }

}
