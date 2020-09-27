package Resources;

import org.testng.annotations.DataProvider;

public class TestData {
	@DataProvider(name="inputData")
	public Object getDatafield()
	{
		Object[] obj=new Object[2];
		obj[0]="hello";
		obj[1]="ashish";
		return obj;
	}
}
