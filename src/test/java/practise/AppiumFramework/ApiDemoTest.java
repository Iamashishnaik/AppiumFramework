package practise.AppiumFramework;

import java.io.IOException;
import java.net.MalformedURLException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.TestData;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import pageObjects.HomePage;
import pageObjects.Preferences;

public class ApiDemoTest extends base{
     
	

	@Test
	public void apiDemo() throws IOException, InterruptedException {
		
		service=startServer() ;
		
		
		// TODO Auto-generated method stub
		AndroidDriver<AndroidElement> driver= capabilities("apiDemo");
		HomePage h= new HomePage(driver); 
		Preferences p= new Preferences(driver);
		h.Preferences.click();
		p.Preferences.click();
		//driver.findElementByXPath("//android.widget.TextView[@text='Preference']").click();// belongs to homepage
	//	driver.findElementByXPath("//android.widget.TextView[@text='3. Preference dependencies']").click();
		//driver.findElementById("android:id/checkbox").click();
		//driver.findElementByXPath("(//android.widget.RelativeLayout)[2]").click();
		//driver.findElementByXPath("//android.widget.TextView[@class='android.widget.RelativeLayout']").clear();
		//driver.findElementByClassName("android.widget.EditText").click();
		//driver.findElementByClassName("android.widget.EditText").sendKeys("shilpy");
		//driver.findElementByXPath("//android.widget.Button[@text='OK']").click();
		//driver.findElementByClassName("android.widget.Button").get(1).click();
		
		//p.dependencies.get(1).click();
	
		
		stopServer(); 	
	
	} 
	

}
