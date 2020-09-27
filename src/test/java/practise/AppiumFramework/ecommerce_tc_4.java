package practise.AppiumFramework;

import java.util.concurrent.TimeUnit;


import java.net.MalformedURLException;
import org.openqa.selenium.By;

import org.openqa.selenium.By.ByClassName;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Resources.TestData;
import io.appium.java_client.MobileBy;

import io.appium.java_client.TouchAction;

import io.appium.java_client.android.AndroidDriver;

import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.CheckoutPage;
import pageObjects.FormPage;
import pageObjects.itempage;

import static io.appium.java_client.touch.TapOptions.tapOptions;

import static io.appium.java_client.touch.offset.ElementOption.element;

import static 
io.appium.java_client.touch.LongPressOptions.longPressOptions;

import static java.time.Duration.ofSeconds;

import java.io.IOException;





public class ecommerce_tc_4 extends base{

@Test(dataProvider="inputData",dataProviderClass=TestData.class)
public void totalValidation(String input) throws IOException, InterruptedException {
//how not to hardcode the app name		-start
              //AndroidDriver<AndroidElement> driver=capabilities("Genearl-Store.apk");//this is hardcording and what if the version changes??
               //what if dev team upgrdaded the app  to version 2  Genearl-Store.apk2.0 
                 //solution-we will defie a global property
	//starting the service
	startServer(); //you need 5 jar file for this //see base class
	
	
	AndroidDriver<AndroidElement> driver=capabilities("GenearlStoreApp");//its a common string ,parametrized it with global.properties file
	FormPage form=new FormPage(driver);
    CheckoutPage checkout=new CheckoutPage(driver);
    itempage item=new itempage(driver);
    Utilities u=new  Utilities(driver);
    

//how not to hardcode the app name		-finish
     driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

     form.nameField.sendKeys(input);// parametrization 

     driver.hideKeyboard();

     form.Femalegender.click();

     form.CountrySelection.click();

     

     //driver.findElementByAndroidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Argentina\"));");
      u.ScrollText("Argentina");
  //   driver.findElement(MobileBy.AndroidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().textMatches(\"" + containedText + "\").instance(0))"));     

     form.Argentina.click();

     form.btnLetsShop.click();

     

     item.addtocart.get(0).click();

     item.addtocart.get(0).click();


    item.btnCart.click();

Thread.sleep(4000);

int count=checkout.prodoctList.size();

double sum=0;

for(int i=0;i<count;i++)

{

String amount1= checkout.prodoctList.get(i).getText();

double amount=getAmount(amount1);

sum=sum+amount;//280.97+116.97

}

System.out.println(sum+"sum of products");



String total=checkout.totalAmount.getText();



total= total.substring(1);

double totalValue=Double.parseDouble(total);

System.out.println(totalValue+"Total value of products");

Assert.assertEquals(sum, totalValue); 



stopServer();

}

public static double getAmount(String value)

{

value= value.substring(1);

double amount2value=Double.parseDouble(value);

return amount2value;

}

@BeforeTest
public void killAllnodes() throws IOException, InterruptedException {
	Runtime.getRuntime().exec("taskkill /F /IM node.exe");
	Thread.sleep(5000);
}
}



