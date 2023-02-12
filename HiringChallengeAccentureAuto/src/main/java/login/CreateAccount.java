
package login;

import java.io.FileInputStream;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
//import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;


public class CreateAccount {
    @Test
    public void testNGAsserts() throws Exception{
        // Source file (spreadsheet)
        FileInputStream fs = new FileInputStream("./src/test/resources/CreateAccount.xlsx");
        @SuppressWarnings("resource")
        XSSFWorkbook workbook = new XSSFWorkbook(fs);
        XSSFSheet sheet = workbook.getSheetAt(0);
        DataFormatter df = new DataFormatter();
        String userName = df.formatCellValue(sheet.getRow(1).getCell(0));
        String password = df.formatCellValue(sheet.getRow(1).getCell(1));
        String email = df.formatCellValue(sheet.getRow(1).getCell(2));
        String confirmPass = df.formatCellValue(sheet.getRow(1).getCell(3));
        String fName = df.formatCellValue(sheet.getRow(1).getCell(4));
        String lName = df.formatCellValue(sheet.getRow(1).getCell(5));
        String phone = df.formatCellValue(sheet.getRow(1).getCell(6));
        String city = df.formatCellValue(sheet.getRow(1).getCell(7));
        String address = df.formatCellValue(sheet.getRow(1).getCell(8));
        String state = df.formatCellValue(sheet.getRow(1).getCell(9));
        String postal = df.formatCellValue(sheet.getRow(1).getCell(10));


        
        
        
        // Chrome and Firefox to make it work for multiple browsers
        System.setProperty("webdriver.chrome.driver","./driver/chromedriver.exe");
        WebDriver driver = new ChromeDriver();  
       // System.setProperty("webdriver.gecko.driver","./driver/geckodriver.exe");
        //WebDriver driver2 = new FirefoxDriver();
        
        // ExtentReports
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter("CreateAccount.html");
        ExtentReports extent = new ExtentReports();
        extent.attachReporter(htmlReporter);
        ExtentTest logStep = extent.createTest("Create Account", "Validate Account Creation functionality");
        
        logStep.info("Step 1: Open website");
        driver.get("https://www.advantageonlineshopping.com/#/"); 
        driver.manage().window().maximize();
        String title = driver.getTitle();   //
        System.out.println("User landed successfully to " + title + " page.");
        Assert.assertEquals(title, " Advantage Shopping");
        Thread.sleep(1000);
    
        logStep.info("Step 2: Navigate to Account Creation page"); 
        String expText = "ACCOUNT DETAILS";
    driver.findElement(By.xpath("//*[@id='menuUser']")).click();
    Thread.sleep(3000);
    driver.findElement(By.xpath("/html/body/login-modal/div/div/div[3]/a[2]")).click();
    Thread.sleep(3000);
    String createAccount = driver.findElement(By.xpath("//h3[text()='ACCOUNT DETAILS']")).getText();
      Assert.assertEquals(createAccount, expText);
      System.out.println("User logged in successfully to " + createAccount + " page.");


    
    logStep.info("Step 3: Create an account");       
        driver.findElement(By.xpath("//*[@id='formCover']/div[1]/div[1]/sec-view[1]/div/input")).sendKeys(userName);
        driver.findElement(By.xpath("//*[@id='formCover']/div[1]/div[2]/sec-view[1]/div/input")).sendKeys(password);
        driver.findElement(By.xpath("//*[@id='formCover']/div[1]/div[1]/sec-view[2]/div/input")).sendKeys(email);
        driver.findElement(By.xpath("//*[@id='formCover']/div[1]/div[2]/sec-view[2]/div/input")).sendKeys(confirmPass);
        driver.findElement(By.xpath("//*[@id='formCover']/div[2]/div[1]/sec-view[1]/div/input")).sendKeys(fName);
        driver.findElement(By.xpath("//*[@id='formCover']/div[2]/div[1]/sec-view[2]/div/input")).sendKeys(lName);
        driver.findElement(By.xpath("//*[@id='formCover']/div[2]/div[2]/sec-view/div/input")).sendKeys(phone);
        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[1]/sec-view[1]/div/select")).click();
        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[1]/sec-view[1]/div/select/option[2]")).click();

        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[1]/sec-view[2]/div/input")).sendKeys(city);
        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[2]/sec-view[1]/div/input")).sendKeys(address);
        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[2]/sec-view[2]/div/input")).sendKeys(state);
        driver.findElement(By.xpath("//*[@id='formCover']/div[3]/div[3]/sec-view/div/input")).sendKeys(postal);
     
        driver.findElement(By.xpath("//*[@id='formCover']/sec-view/div/input")).click();
        driver.findElement(By.xpath("//*[@id='register_btnundefined']")).click();
        
        Thread.sleep(1000);
        
        
      

        logStep.info("Step 3: Asserting that page was created");     
        String expText2 = userName;
        String loggedIn = driver.findElement(By.xpath("//*[@id='menuUserLink']/span")).getText();
        Assert.assertEquals(loggedIn, expText2);
        System.out.println("User logged in successfully to " + loggedIn + ".");
      
      
       

     
        logStep.pass("Created Account successfully"); 
        driver.close();
      //driver2.close();
   
        logStep.info("End of the test");
    extent.flush();
    }
}