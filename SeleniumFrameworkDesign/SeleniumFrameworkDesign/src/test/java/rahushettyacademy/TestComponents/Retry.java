//179. IRetry Analyzer to rerun the flaky failed Selenium tests in the framework
//Rerun the failed test once again to confirm its not flaky or to confirm its error
//IRetryAnalyzer is the interface to reRun the failed test cases.

package rahushettyacademy.TestComponents;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry implements IRetryAnalyzer{

	int count = 0;
	int maxTry = 1;
	
	@Override
	public boolean retry(ITestResult result) 
	{	
		if(count<maxTry) 
		{ 
			count++;
			return true;
		}
		return false;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
