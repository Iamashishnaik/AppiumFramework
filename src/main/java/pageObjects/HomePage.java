package pageObjects;
// All the objects belonging to a page will be defined in one java class

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.PageFactory;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;

public class HomePage {

	
	 public HomePage(AndroidDriver<AndroidElement> driver) {
		// TODO Auto-generated constructor stub
		 PageFactory.initElements(new AppiumFieldDecorator(driver), this);
			
			
	}


	@AndroidFindBy(xpath="//android.widget.TextView[@text='Preference']")
	public WebElement Preferences;
	
	//it means FindElementByXpath("//android.widget.TextView[@text='Preference']");
	
}
