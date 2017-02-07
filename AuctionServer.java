/*
* Author        : H.R.H.G. Kosala (E/13/192)
* Last Modified : 2016.10.16
* Problem       : Auction Server
* */
/* This programme is to implement a online auction server*/

import java.util.HashMap;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class AuctionServer{

	public static void main(String[] args)  throws IOException{
		Stock s = new Stock();
		HashMap<String,String[]> hm = new HashMap<String,String[]>();
		hm = s.store("stocks.csv");
		String[] dislpyKeys = new String[8]; //set the initial cost of some stores to display on the gui
				dislpyKeys[0] = "FB";
                dislpyKeys[1] = "VRTU";
                dislpyKeys[2] = "MSFT";
                dislpyKeys[3] = "GOOGL";
                dislpyKeys[4] = "YHOO";
                dislpyKeys[5] = "XLNX";
                dislpyKeys[6] = "TSLA";
                dislpyKeys[7] = "TXN";
		GUI g = new GUI(dislpyKeys);
		g.setGUI(hm); //set the initial gui
		
		Server2 server = new Server2(Server2.BASE_PORT);
		server.server_loop(); 
		
		
	}
}