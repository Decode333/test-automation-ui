package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class CommonFunctions {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static void launchBrowser() throws IOException {
		String browser = getConfigProperty("browser");
		
		switch (browser.toLowerCase()) {
		case "chrome":
			driver = new ChromeDriver();
			
			break;
		case "edge":
			driver = new EdgeDriver();

			break;

		default:
			System.out.println("Enter Valid browser!!!");
			System.exit(0);
			break;
		}
		
	}
	public static void launchUrl(String url) {
		
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
		driver.get(url);
	}
	
	public static String getConfigProperty(String property) throws IOException {
		
		File file = new File("C:\\Users\\NITHIN RAJ E\\eclipse-workspace\\test-automation-ui\\src\\main\\resources\\Config.properties");
		FileInputStream input = new FileInputStream(file);
		
		Properties pro = new Properties();
		
		pro.load(input);
		
		System.out.println(pro.get(property));
		
		return pro.getProperty(property);
	}
	
	public static WebElement locateElement(String locator, String value) {
		WebElement element = null;
		if(locator=="id") {
			element = driver.findElement(By.id(value));
		}
		else if(locator=="tagname") {
			element = driver.findElement(By.tagName(value));
		}
		else if(locator=="classname") {
			element = driver.findElement(By.className(value));
		}
		else if(locator=="cssSelector") {
			element = driver.findElement(By.cssSelector(value));
		}
		else if(locator=="xpath") {
			element = driver.findElement(By.xpath(value));
		}
		else if(locator=="name") {
			element = driver.findElement(By.name(value));
		}
		return element;
	}
	
	public static List<WebElement> locateElements(String locator, String value) {
		List<WebElement> elements = null;
		if(locator=="id") {
			elements = driver.findElements(By.id(value));
		}
		else if(locator=="tagname") {
			elements = driver.findElements(By.tagName(value));
		}
		else if(locator=="classname") {
			elements = driver.findElements(By.className(value));
		}
		else if(locator=="cssSelector") {
			elements = driver.findElements(By.cssSelector(value));
		}
		else if(locator=="xpath") {
			elements = driver.findElements(By.xpath(value));
		}
		return elements;

	}
	
	public static WebElement waitForVisibility(String element) {
		new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement ele =  wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(element)));
        return ele;
    }
	
	public static void closeBrowser() throws InterruptedException{
//		Thread.sleep(3000);
		driver.quit();;
	}
	
	public String getCellData(String sheetName, int rowNum, int cellNum) throws IOException {
		File f = new File("C:\\Users\\NITHIN RAJ E\\eclipse-workspace\\MavenTrial\\Project Files\\Book1.xlsx");
		FileInputStream input = new FileInputStream(f);
		Workbook workbook = new XSSFWorkbook(input);
		Sheet sheet = workbook.getSheet(sheetName);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		
		CellType cellType = cell.getCellType();
		String value;
		if (cellType==cellType.STRING) {
			value = cell.getStringCellValue();
			return value;
		}
		else if(cellType==cellType.NUMERIC) {
	
			double c = cell.getNumericCellValue();
			long l = (long)c;
			value = String.valueOf(l);
		}
		else {
			Date date = cell.getDateCellValue();
			SimpleDateFormat format = new SimpleDateFormat("DD/MM/YYYY");
			String formatDate = format.format(date);
			value = String.valueOf(formatDate);
		}
		return value;
	}
	public StringBuffer readJSONData(String parentObj, int objIndex, String Key) throws IOException, ParseException {
		
		File f = new File("/test-automation-ui/src/test/resources/input_data/data.json");
		FileReader reader = new FileReader(f);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(reader);

		StringBuffer value = new StringBuffer("");
		if (obj.containsKey(parentObj)) {
			JSONArray arr = (JSONArray) obj.get(parentObj);
			JSONObject credobj = (JSONObject) arr.get(objIndex-1);
			String jsonvalue = (String) credobj.get(Key);
			value.append(jsonvalue);
		}
		else {
			System.out.println("The Parent Object \""+parentObj+"\" is not available in the JSON file");
		}
		return value;
	}

}
