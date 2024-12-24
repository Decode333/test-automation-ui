package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.collect.Table.Cell;

public class CommonFunctions {
	
	public static WebDriver driver;
	public static WebDriverWait wait;
	
	public static void launchBrowser() {
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(20,TimeUnit.SECONDS);
	}
	public static void launchUrl(String url) {
		driver.get(url);
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
	
	public static void waitForVisibility(String element) {
		new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfElementLocated(element));
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
	public String readJSONData(String parentObj, int objIndex, String Key) throws IOException, ParseException {
		
		File f = new File("/test-automation-ui/src/test/resources/input_data/data.json");
		FileReader reader = new FileReader(f);

		JSONParser parser = new JSONParser();
		JSONObject obj = (JSONObject) parser.parse(reader);
		String value = "";
		if (obj.containsKey(parentObj)) {
			JSONArray arr = (JSONArray) obj.get(parentObj);
			JSONObject credobj = (JSONObject) arr.get(objIndex-1);
			String jsonvalue = (String) credobj.get(Key);
			value = value.concat(jsonvalue);
		}
		else {
			System.out.println("The Parent Object is not available in the JSON file");
		}
		return value;

}
