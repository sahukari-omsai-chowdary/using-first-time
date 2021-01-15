package baseclasses;

import org.testng.annotations.BeforeSuite;

public class UnitTestBaseClass {
	@BeforeSuite
	public void globalSetup() {
		System.out.println("global setup");
	}
}
