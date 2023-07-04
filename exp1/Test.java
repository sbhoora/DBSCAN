//SHUBHI BHOORA 300228522 CSI2110
import java.util.*;
import java.io.*;  

public class Test {
	public static ArrayList<ArrayList<Double>> read(String filename) throws Exception {
	
	ArrayList<ArrayList<Double>> result = new ArrayList<ArrayList<Double>>();

    ArrayList<Double> xAll = new ArrayList<Double>();
    ArrayList<Double> yAll = new ArrayList<Double>();
    ArrayList<Double> zAll = new ArrayList<Double>();
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); 
	
	// read points
	while (sc.hasNext())  
	{  
		xAll.add(Double.parseDouble(sc.next()));
		yAll.add(Double.parseDouble(sc.next()));
		zAll.add(Double.parseDouble(sc.next()));
	}

	result.add(xAll);
	result.add(yAll);
	result.add(zAll);

	sc.close();  //closes the scanner  
	
	return result;
  }
 
	public static boolean isEqual(String fileKD, String fileLin) throws Exception { 
		ArrayList<ArrayList<Double>> lin = Test.read(fileLin);
	 	ArrayList<ArrayList<Double>> kd = Test.read(fileKD);

	 	Collections.sort(lin.get(0));
        Collections.sort(kd.get(0));

        Collections.sort(lin.get(1));
        Collections.sort(kd.get(1));

        Collections.sort(lin.get(2));
        Collections.sort(kd.get(2));

        if(lin.get(0).equals(kd.get(0)) == true && lin.get(1).equals(kd.get(1)) == true &&
    			lin.get(2).equals(kd.get(2)) == true){
        	return true;
        }
        return false;
	} 

	public static void main(String[] args) throws Exception { 
		boolean p1 = Test.isEqual("pt1_kd.txt","pt1_lin.txt");
		boolean p2 = Test.isEqual("pt2_kd.txt","pt2_lin.txt");
		boolean p3 = Test.isEqual("pt3_kd.txt","pt3_lin.txt");
		boolean p4= Test.isEqual("pt4_kd.txt","pt4_lin.txt");
		boolean p5 = Test.isEqual("pt5_kd.txt","pt5_lin.txt");
		boolean p6 = Test.isEqual("pt6_kd.txt","pt6_lin.txt");

		System.out.println("P1: " + p1);
		System.out.println("P2: " + p2);
		System.out.println("P3: " + p3);
		System.out.println("P4: " + p4);
		System.out.println("P5: " + p5);
		System.out.println("P6: " + p6);

	}
}