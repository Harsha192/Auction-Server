import java.io.BufferedReader;
import java.io.FileReader;
import java.util.*;

public class Stock {

	public static HashMap<String, String[]> hm = new HashMap<String, String[] >();

    public HashMap store(String csvFile) {
        
        try{
            BufferedReader br = new BufferedReader(new FileReader(csvFile));
            String line = br.readLine(); //read csv file line by line

            while ((line=br.readLine()) != null) {
				//split the line by ","
                String[] item = line.split(",");
                
				//String array to keep the details of person
				String[] arry = new String[3];
                arry[0] = item[0];
                arry[1] = item[1];
                arry[2] = item[2];
                
				String key = item[0];
                hm.put(key, arry);

            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return hm;
    }
}
