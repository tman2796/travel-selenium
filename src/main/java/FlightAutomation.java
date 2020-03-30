import org.openqa.selenium.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FlightAutomation {
	File priceFile = new File("PriceFile.txt");

	PrintWriter pw = new PrintWriter(priceFile);

	public ArrayList<Integer> priceListCancun = new ArrayList<>();
	public ArrayList<Integer> priceListLasVegas = new ArrayList<>();
	public ArrayList<Integer> priceListDenver = new ArrayList<>();
	public ArrayList<Integer> priceListRome = new ArrayList<>();
	public ArrayList<Integer> priceListMilan = new ArrayList<>();
	public ArrayList<Integer> priceListParis = new ArrayList<>();
	public ArrayList<Integer> priceListMadrid = new ArrayList<>();
	public ArrayList<Integer> priceListAmsterdam = new ArrayList<>();
	public ArrayList<Integer> priceListSingapore = new ArrayList<>();

	private int price;
	private WebDriver driver;
	
	public FlightAutomation(WebDriver driver) throws FileNotFoundException {
		System.out.println("Starting chrome");
		this.driver = driver;
	}
	
	public void start() {
		var weeks = Utils.getOneWeekIntervals(Main.StartDate, Main.EndDate);
		
		for (var city : Main.Cities) {
			var prices = new HashMap<String, Integer>();
			for (var dayPair : weeks) {
				System.out.println("Searching for flights to " + city + " leaving " + dayPair[0] + " and returning on " + dayPair[1]);
				driver.get(Main.URL);
				
				goToFlightPage();
				setLocations(city);
				setDates(dayPair[0], dayPair[1]);
				
				driver.findElement(By.xpath(Paths.SearchButton)).click();
				showHighestAndLowestPrice();
				getCheapestPrice(prices, dayPair[0]);
			}
			
			var lowestPair = prices.entrySet().stream().min(Comparator.comparing(Map.Entry::getValue)).get();
			System.out.println("Lowest price flight departs on " + lowestPair.getKey());
			pw.println("Lowest price flight departs on " + lowestPair.getKey());
			if(city.equals(Main.Cities[0])){
				pw.println("Lowest plane ticket price to " + Main.Cities[0] + " = $" + determineLowestPrice(priceListCancun));
				System.out.println("Lowest plane ticket price to " + Main.Cities[0] + " = $" + determineLowestPrice(priceListCancun));
			}
			if(city.equals(Main.Cities[1])){
				pw.println("Lowest plane ticket price to " + Main.Cities[1] + " = $" + determineLowestPrice(priceListLasVegas));
				System.out.println("Lowest plane ticket price to " + Main.Cities[1] + " = $" + determineLowestPrice(priceListLasVegas));
			}
			if(city.equals(Main.Cities[2])){
				pw.println("Lowest plane ticket price to " + Main.Cities[2] + " = $" + determineLowestPrice(priceListDenver));
				System.out.println("Lowest plane ticket price to " + Main.Cities[2] + " = $" + determineLowestPrice(priceListDenver));
			}
			if(city.equals(Main.Cities[3])){
				pw.println("Lowest plane ticket price to " + Main.Cities[3] + " = $" + determineLowestPrice(priceListRome));
				System.out.println("Lowest plane ticket price to " + Main.Cities[3] + " = $" + determineLowestPrice(priceListRome));
			}
			if(city.equals(Main.Cities[4])){
				pw.println("Lowest plane ticket price to " + Main.Cities[4] + " = $" + determineLowestPrice(priceListMilan));
				System.out.println("Lowest plane ticket price to " + Main.Cities[4] + " = $" + determineLowestPrice(priceListMilan));
			}
			if(city.equals(Main.Cities[5])){
				pw.println("Lowest plane ticket price to " + Main.Cities[5] + " = $" + determineLowestPrice(priceListParis));
				System.out.println("Lowest plane ticket price to " + Main.Cities[5] + " = $" + determineLowestPrice(priceListParis));
			}
			if(city.equals(Main.Cities[6])){
				pw.println("Lowest plane ticket price to " + Main.Cities[6] + " = $" + determineLowestPrice(priceListMadrid));
				System.out.println("Lowest plane ticket price to " + Main.Cities[6] + " = $" + determineLowestPrice(priceListMadrid));
			}
			if(city.equals(Main.Cities[7])){
				pw.println("Lowest plane ticket price to " + Main.Cities[7] + " = $" + determineLowestPrice(priceListAmsterdam));
				System.out.println("Lowest plane ticket price to " + Main.Cities[7] + " = $" + determineLowestPrice(priceListAmsterdam));
			}
			if(city.equals(Main.Cities[8])){
				pw.println("Lowest plane ticket price to " + Main.Cities[8] + " = $" + determineLowestPrice(priceListAmsterdam));
				System.out.println("Lowest plane ticket price to " + Main.Cities[8] + " = $" + determineLowestPrice(priceListAmsterdam));
			}
		}
		pw.close();
	}
	
	private void goToFlightPage() {
	    try {
            WebElement flightsButton = driver.findElement(By.xpath(Paths.FlightButton));
            flightsButton.click();
        }catch(StaleElementReferenceException e){
            System.out.println(e);
        }
	}
	
	private void setLocations(String arrival) {
	    try {
            var departLoc = driver.findElement(By.xpath(Paths.DepartureAirportDropdown));
            clearField(departLoc);
            departLoc.sendKeys(Main.DepartureCity);
            var arriveLoc = driver.findElement(By.xpath(Paths.ArrivalAirportDropdown));
            clearField(arriveLoc);
            arriveLoc.sendKeys(arrival);
        }catch(StaleElementReferenceException e){
            System.out.println(e);
        }
	}
	
	private void setDates(String start, String end) {
	    try {
            var departDate = driver.findElement(By.xpath(Paths.DepartureDateBox));
            clearField(departDate);
            departDate.sendKeys(start);
            var arriveDate = driver.findElement(By.xpath(Paths.ReturningDateBox));
            clearField(arriveDate);
            arriveDate.sendKeys(end);
        }catch(StaleElementReferenceException e){
            System.out.println(e);

        }
	}
	
	private void clearField(WebElement element) {
	    try {
            element.click();
            element.clear();
            element.sendKeys(Keys.CONTROL, "a", Keys.BACK_SPACE);
        } catch(StaleElementReferenceException e){
            System.out.println(e);
        }
	}
	private void addToSpecificList(int price){
		for (var city : Main.Cities) {
			if(city.equals("Cancun")){
				priceListCancun.add(price);
			}
			if(city.equals("Las Vegas")){
				priceListLasVegas.add(price);
			}
			if(city.equals("Denver")){
				priceListDenver.add(price);
			}
			if(city.equals("Rome")){
				priceListRome.add(price);
			}
			if(city.equals("Milan")){
				priceListMilan.add(price);
			}
			if(city.equals("Paris")){
				priceListParis.add(price);
			}
			if(city.equals("Madrid")){
				priceListMadrid.add(price);
			}
			if(city.equals("Amsterdam")){
				priceListAmsterdam.add(price);
			}
			if(city.equals("Singapore")){
				priceListSingapore.add(price);
			}
		}
	}

	public int determineLowestPrice(ArrayList<Integer> list){
		int lowest = list.get(0);
		for (var price : list) {
			if(price <= lowest){
				lowest = price;
			}
		}
		return lowest;
	}
	
	public void showHighestAndLowestPrice() {
	    try{
		var prices = driver.findElements(By.xpath(Paths.PRICES));
		if (prices.get(0).getText().contains("left")) {
            System.out.println("\tCheapest: " + prices.get(1).getText());
            String lowPrice = prices.get(1).getText().replaceAll("$", "").replaceAll(",","");
            price = Integer.parseInt(lowPrice.replace("$", ""));
            addToSpecificList(price);

		} else {
			System.out.println("\tCheapest: " + prices.get(0).getText());
			String lowPrice = prices.get(0).getText().replaceAll("$", "").replaceAll(",","");
			price = Integer.parseInt(lowPrice.replace("$", ""));
			addToSpecificList(price);
		}
	    }catch(StaleElementReferenceException e){
            System.out.println(e);
        }
//		System.out.println("\tMost Expensive: " + prices.get(prices.size() - 1).getText());
	}
	
	private void getCheapestPrice(HashMap<String, Integer> prices, String date) {
	    try {
            var webPrices = driver.findElements(By.xpath(Paths.PRICES));
            var lowest = webPrices.get(0).getText();
            if (lowest.contains("left")) {
                lowest = webPrices.get(1).getText();
            }
			lowest = lowest.substring(lowest.indexOf("$") + 1);
			lowest = lowest.replaceAll(",", "");
			lowest = lowest.trim();
			var lowestNumber = Integer.parseInt(lowest);
            prices.put(date, lowestNumber);
        }catch(StaleElementReferenceException e){
            System.out.println(e);
        }
	}
}
