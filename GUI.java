import javax.swing.*;
import java.awt.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.*;

public class GUI extends JFrame{
	private static int lblNumber = 2;
	private static int isExist1 = 0;
	private static int isExist2 = 0;

	//array with the labels
	private static JLabel lblArray[] = new JLabel[9];
	private static JLabel lblArray2[] = new JLabel[9];
    String[] dislpyKeys = new String[8]; //to set the initial store values in the gui
	
	//constructor
    public GUI(String[] dislpyKeys){
        this.dislpyKeys = dislpyKeys;
    }
	
	//this method is to update the gui 
	public static void updateGUI(String bidItem,String bid,HashMap<String,String[]> hm){
		String ar[] = hm.get(bidItem);
		ar[2] = bid;
		hm.put(bidItem,ar);
		for(int i=1;i<9;i++){
			if(lblArray[i].getText().equals(ar[1])){
				lblArray2[i].setText(ar[2]);
				isExist1=1;
				break;
			}
		}
		
		if(isExist1==0){
			isExist2=1;
			isExist1=1;
		}
		
		if(isExist1==1 && isExist2==1){
			lblArray[lblNumber].setText(ar[1]);
			lblArray2[lblNumber].setText(bid);
			lblNumber++;
			isExist2=0;
		}

		isExist1=0;
		if(lblNumber==7){
			lblNumber=2;
		}
	}

	//this method is to set the initial gui
	public void setGUI(HashMap<String,String[]> hm){
	        setLayout(null);
                this.setSize(500,400);
                int xloc = 60;
                int yloc = 50;
                this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                setTitle("Auction Server");
                
                String displyVal[] = new String[2];

                lblArray[0] = new JLabel("Security Name");
                lblArray[0].setBounds(60,10,300,30);
                add(lblArray[0]);

                lblArray2[0] = new JLabel("Price");
                lblArray2[0].setBounds(380,10,300,30);
                add(lblArray2[0]);
                
                for (int i=0;i<8;i++){
                        lblArray[i+1] = new JLabel();
                        displyVal = hm.get(dislpyKeys[i]);
                        lblArray[i+1].setText(displyVal[1]);
                        lblArray[i+1].setBounds(xloc,yloc,300,30);
                        add(lblArray[i+1]);
                      
                        lblArray2[i+1] = new JLabel();
                        lblArray2[i+1].setText(displyVal[2]);
                        lblArray2[i+1].setBounds(380,yloc,300,30);
                        add(lblArray2[i+1]);
                        yloc = yloc + 10+30;

                }
                this.setVisible(true);
                this.setResizable(false);
	}
}