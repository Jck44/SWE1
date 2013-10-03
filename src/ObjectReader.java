/**
 * 
 */
import java.io.*;
import java.nio.file.*;

/**
 * @author Andreas
 *
 */
public class ObjectReader  {
	
	private Path path = null;
	private String MIMEtype = null;
	
	protected ObjectReader(String path){
		try {
			this.setPath(path.toLowerCase());
			this.setMIMEType(Files.probeContentType(this.getPath()));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	private void setPath(String path){
		//if(
			try{	
				this.path = FileSystems.getDefault().getPath("html", path);
			}
			catch (InvalidPathException e){
				//HTTP RESPONE 404
				System.out.println("Error: " + e);
			}
		
	}
	
	private void setMIMEType(String MIMEType){
		this.MIMEtype = MIMEType;
	}
	
	private Path getPath(){
		return this.path;
	}
	
	private String pathToString(){
		return this.path.toString();
	}
	
	protected String getMIMEType(){
		return this.MIMEtype;
	}
	
	public String toString(){
		
		StringBuilder contentBuilder = new StringBuilder();
		try {
			BufferedReader input = new BufferedReader(new FileReader(this.pathToString()));
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
