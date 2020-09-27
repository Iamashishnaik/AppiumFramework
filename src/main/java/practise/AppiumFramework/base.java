package practise.AppiumFramework;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.Properties;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.remote.DesiredCapabilities;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;

public class base {
    /*public static AppiumDriverLocalService service;  // this line is mandatory
	public AppiumDriverLocalService startService() ////you need jar file for this slfj4 jar file
	{
		boolean flag= checkIfServerIsRunning(4723);
		if(!flag) {
	AppiumDriverLocalService service = AppiumDriverLocalService.buildDefaultService();
	service.start();
		}
	return service;
	//for this u need 5 jar files
	//1.slf4j-simple 2.slf4j-api, 3.commons-lang3 4.commons-io 5.commons-validator
	}*/
	int port=4723;
	public static AndroidDriver<AndroidElement> driver;
	public AppiumDriverLocalService service;
	public AppiumServiceBuilder builder;
	public DesiredCapabilities cap;
	public AppiumDriverLocalService startServer() {
		//Set Capabilities
		MutableCapabilities cap = new DesiredCapabilities();
		cap.setCapability("noReset", "false");
		
		//Build the Appium service
		builder = new AppiumServiceBuilder();
		builder.usingDriverExecutable(new File("C:\\Program Files\\nodejs\\node.exe"));
		builder.withAppiumJS(new File(System.getProperty("user.home")+"\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"));
		builder.withLogFile(new File (System.getProperty("user.home")+"\\AppiumServerLogs.txt"));
		System.out.println(System.getProperty("user.home"));
		builder.withIPAddress("127.0.0.1");
		builder.usingPort(port);
		
		
		//builder.withCapabilities(cap);
		builder.withArgument(GeneralServerFlag.SESSION_OVERRIDE);
//		builder.withArgument(GeneralServerFlag.LOG_LEVEL,"debug");
		
		if (!base.checkIfServerIsRunning(4723)) {
		//Start the server with the builder
		service = AppiumDriverLocalService.buildService(builder);
		service.start();
		URL appiumServerURL = service.getUrl();
		
	}
		else {
			System.out.println("Appium Server already running on Port - " + port);
		}
		return service;
	}
	
	
	 

	
	public static boolean checkIfServerIsRunning(int port) { //to check if the server has started
		boolean isServerRunning=false;
		ServerSocket serverSocket;
		try {
			serverSocket =new ServerSocket(port);
			serverSocket.close();
		}
		catch(IOException e) {
			isServerRunning =true;
			
		}
		finally {
			serverSocket=null;
		}
		return isServerRunning;
	}
	
	
	public void stopServer() {
		System.out.println("Stopping the service");
		service.stop();
		System.out.println("Stopped the service");
	}
	
	public static void takeScreenshot(String s) throws IOException {
		File srcfile= driver.getScreenshotAs(OutputType.FILE);
		FileUtils.copyFile(srcfile, new File(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\"+s+".png"));
	    
	}
	
	/// we will create a bat file to run the emulator and close the emulator
	public  static void startEmulator() throws IOException, InterruptedException
	{
		
		Runtime.getRuntime().exec(System.getProperty("user.dir")+"\\src\\main\\java\\Resources\\startEmulator.bat");
		
		Thread.sleep(20000);
	}
	public static AndroidDriver<AndroidElement>capabilities(String appName) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
//how not to hardcode the app name		-start
		//declaring the global file here
		
		System.getProperty("user.dir");//this is will give the path if your current project
		FileInputStream fis= new FileInputStream(System.getProperty("user.dir")+"\\src\\main\\java\\practise\\AppiumFramework\\global.properties");// this is how you need to get the path dynamically
	
		Properties prop=new Properties(); //properties does mapping , it goes and create a mapping for fis  
		prop.load(fis);
		prop.get(appName);// it will Retrieve the value /appname 
//	how not to hardcode the app name	-finished
		
		
		
		
		File appDir=new File ("src");
				File app=new File(appDir,(String)prop.getProperty(appName)); //string casting
        DesiredCapabilities capabilities=new DesiredCapabilities();
        
  //how not to hardcode the device name-start using testng and global properties file 
        //method-1
        //String device=(String)prop.getProperty("device");
        //method-2 from maven (highly recommmended)   , we need to call the device from command prom
         //mvn test -DdeviceName=devicename
        
        String device=System.getProperty("deviceName");
        if (device.contains("Emulator"))
        		{
        	startEmulator();
        }
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME,device );
        
  //how not to hardcode the device name-start- finish      
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");//its not hardcoding , its standard
        //the application name should come from your test case
        capabilities.setCapability(MobileCapabilityType.APP,app.getCanonicalPath());
         driver =new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"),capabilities);
        return driver;
	}

}
 