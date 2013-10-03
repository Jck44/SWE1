import java.net.*;
import java.util.*;
import java.io.*;

 
public class Server
{
    public static void main(String[] args)
    {
        new Server();
    }
     
    public Server()
    {
        int sNr = 8080; 
        try {
        	
            ServerSocket sSocket = new ServerSocket(sNr);
            System.out.println("Server started at: " + new Date() + " on Port: " + sNr);
                          
            //Loop that runs server functions
            while(true) {
                //Wait for a client to connect
                Socket socket = sSocket.accept();
                System.out.println("New connection on Port: "  + socket.getPort());
                
                 
                //Create a new custom thread to handle the connection
                ClientThread cThread = new ClientThread(socket);
                //socket.close();
                 
                //Start the thread!
                new Thread(cThread).start();
               
                 
            }
            
        } catch(IOException exception) {
            System.out.println("Error: " + exception);
        }
    }
     
    //Here we create the ClientThread inner class and have it implement Runnable
    //This means that it can be used as a thread
    class ClientThread implements Runnable
    {
        Socket threadSocket;
         
        //This constructor will be passed the socket
        public ClientThread(Socket socket)
        {
            //Here we set the socket to a local variable so we can use it later
            threadSocket = socket;
                       
        }
         
        public void run(){
        	PrintWriter out = null;
        	BufferedReader in = null;
        	try{
        		
	        	out = new PrintWriter(threadSocket.getOutputStream(), true);
	        	in = new BufferedReader(new InputStreamReader(threadSocket.getInputStream()));
	        	//HttpResponse newResponse = new HttpResponse
	            String inputLine = null;
	            String requestString = null;
	            while (!(inputLine = in.readLine()).isEmpty()) {
	                System.out.println(inputLine);
	                if(inputLine.startsWith("GET") || inputLine.startsWith("POST"))
	                	requestString = inputLine;
	            }
	            
	            String requestPage = requestString.substring(requestString.indexOf('/')+1,requestString.lastIndexOf(' '));
	            if(requestPage.isEmpty())
	            	requestPage = "index.html";
	            System.out.println(requestPage);
	            
	            	
	            
	            out.println("HTTP/1.1 200 OK");
	            out.println("Server: Experimental 0.1");
	            out.println("Date: " + new Date() + "");
	            out.println("Content-type: text/html\r\n");
	            out.println("<html>");
	            out.println("<body>");
	            out.println(new ObjectReader(requestPage).toString());	      
	            out.println("</body>");
	            out.println("</html>");
	            System.out.println("###Response sent###");
	            in.close();
	            out.flush();
	            out.close();
	        	threadSocket.close();
	        	
        	}
        	 catch (IOException e){
             	System.out.println("Error: " + e);
             }
        }
       
    }
}
