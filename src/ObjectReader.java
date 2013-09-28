/**
 * 
 */
import java.io.*;

/**
 * @author Andreas
 *
 */
public class ObjectReader {
	
	String path = null;
	String objectType = null;
	
	public ObjectReader(String path, String objectType){
		this.setPath(path.toLowerCase());
		this.setObjectType(objectType);
	}
	
	private void setPath(String path){
		this.path = path;
	}
	
	private void setObjectType(String objectType){
		this.objectType = objectType;
	}
	
	private String getPath(){
		return this.path;
	}
	
	private String getObjectType(){
		return this.objectType;
	}
	
	public String toString(){
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(this.getPath()));
			String temp = null;
			while ((temp = input.readLine()) != null){
				contentBuilder.append(temp);
			}
			input.close();
			
			
		}
		catch (IOException e){
			System.out.println("Error: " + e);
		}
		
		return contentBuilder.toString();
	}

}
