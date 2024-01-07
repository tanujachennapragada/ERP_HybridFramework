package driverFactory;

import org.testng.annotations.Test;

public class AppTest
{

@Test
public void Kickstart() throws Throwable 
{
	DriverScript ds=new DriverScript();
	ds.startTest();
}
	
}
