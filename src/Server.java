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
        int sNr = 8080;//We need a try-catch because lots of errors can be thrown
        try {
        	
            ServerSocket sSocket = new ServerSocket(sNr);
            System.out.println("Server started at: " + new Date() + " on Port: " + sNr);
             
             
            //Loop that runs server functions
            while(true) {
                //Wait for a client to connect
                Socket socket = sSocket.accept();
                System.out.println("New connection on Port: "  + socket.getPort());
             
                 
                //Create a new custom thread to handle the connection
                ClientThread cT = new ClientThread(socket);
                 
                //Start the thread!
                new Thread(cT).start();
                 
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
	            String inputLine = null;
	            String getString = null;
	            while (!(inputLine = in.readLine()).isEmpty()) {
	                System.out.println(inputLine);
	                if(inputLine.startsWith("GET"))
	                	getString = inputLine;
	                
	            }
	            System.out.println(getString);
	            getString = getString.substring(getString.indexOf('/'),getString.lastIndexOf(' '));
	            System.out.println(getString);
	           
	            out.println("HTTP/1.1 200 OK");
	            
	            
	            out.println("Server: Experimental 0.1");
	            out.println("Date: " + new Date() + "");
	            out.println("Content-type: text/html\r\n");
	            out.println("<html>");
	            out.println("<body>");
	            out.println("<h1>Server running!!</h1>\r\n<p>Test Text</p>");
	            out.println("</body>");
	            out.println("</html>");
	            System.out.println("###Response sent###");
	            in.close();
	            out.close();
	        	threadSocket.close();
        	}
        	 catch (IOException e){
             	System.out.println("Error: " + e);
             }
        }
       
    }
}
