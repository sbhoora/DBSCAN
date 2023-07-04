//SHUBHI BHOORA 300228522 CSI2110
/*
 * Incomplete Experiment 1 
 *
 * CSI2510 Algorithmes et Structures de Donnees
 * www.uottawa.ca
 *
 * Robert Laganiere, 2022
 *
*/ 
import java.util.List;
import java.util.ArrayList;

import java.io.*;  
import java.util.Scanner;  

public class Exp1 {
  
  // reads a csv file of 3D points (rethrow exceptions!)
  public static List<Point3D> read(String filename) throws Exception {
	  
    List<Point3D> points= new ArrayList<Point3D>(); 
	double x,y,z;
	
	Scanner sc = new Scanner(new File(filename));  
	// sets the delimiter pattern to be , or \n \r
	sc.useDelimiter(",|\n|\r");  

	// skipping the first line x y z
	sc.next(); sc.next(); sc.next();
	
	// read points
	while (sc.hasNext())  
	{  
		x= Double.parseDouble(sc.next());
		y= Double.parseDouble(sc.next());
		z= Double.parseDouble(sc.next());
		points.add(new Point3D(x,y,z));  
	}   
	
	sc.close();  //closes the scanner  
	
	return points;
  }

  public static void save(int pt, String type, List<Point3D> result) throws FileNotFoundException, UnsupportedEncodingException{
			PrintWriter writer = new PrintWriter("pt"+pt+"_"+type+".txt", "UTF-8");
	    writer.println("Experiment 1:");
	    for(Point3D p: result){
	      String xyz = p.getX()+","+p.getY()+","+p.getZ();
	      writer.println(xyz);
	      System.out.println(xyz);
	    }
	    System.out.println();
	    writer.close();
	}
  
  public static void main(String[] args) throws Exception {  
  //predefined arguments, if none are provided
  String[] params = new String[] {"kd", "0.05", "Point_Cloud_1.csv", "14.15982089", "4.680702457", "-0.133791584"};
  //if arguments are provided, overide the predefined arguments with the provided one
  for(int i=0;i<args.length;i++){params[i]=args[i];}
   
  // not reading args[0]
	double eps= Double.parseDouble(params[1]);
  
  // reads the csv file
  List<Point3D> points= Exp1.read(params[2]);
	
	//initializes Point3D based on arguments provided
	Point3D query= new Point3D(Double.parseDouble(params[3]),
								Double.parseDouble(params[4]),
								Double.parseDouble(params[5]));
	
	//point number from pdf
	int pt = 6;

	if(params[0].equals("lin")){
		//initialize NearestNeighbors
		NearestNeighbors nn= new NearestNeighbors(points);
		List<Point3D> neighbors= nn.rangeQuery(query,eps);
		System.out.println("------------LINEAR------------");
		System.out.println("number of neighbors = "+neighbors.size());
		save(pt,"lin",neighbors);
	}else if(params[0].equals("kd")){
		//initialize NearestNeighborsKD
		NearestNeighborsKD nnkd= new NearestNeighborsKD(points);
		List<Point3D> neighbors= nnkd.rangeQuery(query,eps);
		System.out.println("------------KD TREE------------");
		System.out.println("number of neighbors = "+neighbors.size());
		save(pt,"kd",neighbors);
	}
	
  }   
}
