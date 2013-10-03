import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * 
 */

/**
 * @author Andreas
 *
 */
public class HttpResponse {
	
	private static int statusCode;
	protected HttpResponse(int statusCode) {
		this.statusCode = statusCode;
	}
	
	private String returnStatus() {
		
		if(this.statusCode == 200)
			return "HTTP/1.1 200 OK";
		else if(this.statusCode == 404)
			return "HTTP/1.1 404 Not Found"; 
		else
			return "HTTP/1.1 404 Not Found";
		
		
	}

}
