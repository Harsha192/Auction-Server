import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Server2 implements Runnable { 
    public static String bidItem = null; //store the item bid by client
    public static final int BASE_PORT = 2000;   
    public static final String WAIT_AUTH_MSG = "Enter your Name : \n"; 
    public static final String AUTH_DONE_MSG = "You are authorised to bid.\n"; 
    public static final String MSG_POSTED    = "Your bid is Recorded.\n";   
    
    private static ServerSocket serverSocket; 
    private static int socketNumber; 

    private Socket connectionSocket; 
        
    public Server2(int socket) throws IOException { 
		serverSocket = new ServerSocket(socket); 
		socketNumber = socket; 
    }

    public Server2(Socket socket) { 
		this.connectionSocket = socket; 
    }

    public void server_loop() throws IOException { 
		while(true) { 
			Socket socket = serverSocket.accept(); 	    
			Thread worker = new Thread(new Server2(socket)); 
			worker.start(); 	    
		}
    }

    public void run() { 
    	BufferedReader in = null; //read the input from the client
    	PrintWriter out = null; //write the replies to the client's window
		try { 
			in = new 
			BufferedReader(new InputStreamReader(this.connectionSocket.getInputStream()));
			out = new 
			PrintWriter(new OutputStreamWriter(this.connectionSocket.getOutputStream()));	 
	    

			String line, outline; 
			out.println(WAIT_AUTH_MSG);
			int count = 0; //count variable to print the correct message on the client window
			for(line = in.readLine(); 
			line != null && !line.equals("quit"); 
			line = in.readLine()) { 
				if (count==1 || line.equals("bid") ){
					out.println(AUTH_DONE_MSG);//enter the client name
					out.println("Enter the Symbol of the Store you want to Bid : "); //Security symbol of the store that client want to bid
				
				}else if(count==2){
					this.bidItem = line;
					//this will show -1 if the security symbol that client enterd is wrong 
					if(itemExist().equals("-1")){
						out.println("Current cost is : "+itemExist()); 
						out.println("Invalid security name.. Please try again.");
						out.flush();
						break;	
					}
					out.println("Current cost is : "+itemExist()); //show the current cost of the store
					out.println("Enter your Bid : "); //ask to bid
				}else if(count==3){
					//this will check whether the client's bid is less than the previous bid 
					if(!checkBid(line)){
						out.println("Invalid bid value.  press enter to bid again"); 
						out.flush();
						count=1;
						continue;
					}
					out.println("Your bid was successfully recorded.");
					GUI.updateGUI(bidItem,line,Stock.hm); //update the current bid on the gui after check all the requirements
					out.println("Do you want to bid more or exit? (type quit to exit, press enter to bid more)");
					count=0; //set count zero for ready to client suppose to bid again
				}
				count++;
				out.flush();
			}
	    	 
		}catch (IOException e) { 
			System.out.println(e); 
		} 
		try { 	    
			this.connectionSocket.close(); 
		} catch(IOException e) {}
	
	}
	
	
	//this method is check weather the security name that user entered was exist of not in the hashmap (stock)
	public String itemExist(){
		String currentBid = "-1"; //return -1 if not exist 
		if(Stock.hm.get(bidItem) != null){
			String ar[] = Stock.hm.get(bidItem);
			currentBid=ar[2];
		}
		return currentBid;	
	}
	
	//this method check the bid entered by client is greater than the priveous store value
	public boolean checkBid(String current){
		boolean result=false;
		String ar[] = Stock.hm.get(bidItem);
		String prviusBid=ar[2];
		if( Double.parseDouble(current) > Double.parseDouble(prviusBid)){
			result=true;
		}
		return result;
	}
	
}
	    
	