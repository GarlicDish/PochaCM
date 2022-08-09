package pochacm.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class CSVReader {
	    public static void main(String[] args) {
	        CSVReader csvReader = new CSVReader();
	        csvReader.readCSV();
	    }

	    public List<List<String>> readCSV() {
	        List<List<String>> csvList = new ArrayList<List<String>>();
	        File csv = new File("C:\\Users\\home\\git\\pochacm\\src\\main\\webapp\\resources\\csv\\au_postcodes.csv");
	        BufferedReader br = null;
	        String line = "";

	        try {
	            br = new BufferedReader(new FileReader(csv));
	            while ((line = br.readLine()) != null) { // readLine()�� �뙆�씪�뿉�꽌 媛쒗뻾�맂 �븳 以꾩쓽 �뜲�씠�꽣瑜� �씫�뼱�삩�떎.
	                List<String> aLine = new ArrayList<String>();
	                String[] lineArr = line.split(","); // �뙆�씪�쓽 �븳 以꾩쓣 ,濡� �굹�늻�뼱 諛곗뿴�뿉 ���옣 �썑 由ъ뒪�듃濡� 蹂��솚�븳�떎.
	                aLine = Arrays.asList(lineArr);
	                csvList.add(aLine);
	            }
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        } finally {
	            try {
	                if (br != null) { 
	                    br.close(); // �궗�슜 �썑 BufferedReader瑜� �떕�븘以��떎.
	                }
	            } catch(IOException e) {
	                e.printStackTrace();
	            }
	        }
	        return csvList;
	    }
	}
