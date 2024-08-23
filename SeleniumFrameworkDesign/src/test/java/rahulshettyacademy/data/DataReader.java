// FileUtils is used which will read file JSon file and convert that into one variable

package rahulshettyacademy.data;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import org.apache.commons.io.FileUtils;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class DataReader {
	
	
	public List<HashMap<String, String>> getJsonDataToMap() throws IOException 
	{	
		//Read JSon to string
		String jsonContent = FileUtils.readFileToString(new File("C:\\Users\\madhu\\eclipse\\java-2023-09\\eclipse\\SeleniumFrameworkDesign\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrdeFile.json"));
		
		//String to hasMap JackSon DataBind 
		
		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent, new TypeReference<List<HashMap<String,String>>>(){
		});
		return data;
		
	}
	
	
	
	
	

}
