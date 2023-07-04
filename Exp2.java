//SHUBHI BHOORA 300228522 CSI2110
import java.util.*;
import java.io.*;  

public class Exp2 {
	// reads a csv file of 3D points (rethrow exceptions!)
	public static List<Point3D> read(String filename, int n) throws Exception {
	    List<Point3D> points= new ArrayList<Point3D>(); 
		double x,y,z;
		
		Scanner sc = new Scanner(new File(filename));  
		// sets the delimiter pattern to be , or \n \r
		sc.useDelimiter(",|\n|\r");  

		// skipping the first line x y z
		sc.next(); sc.next(); sc.next();
		
		int i = 1; //line counter

		// read points
		while (sc.hasNext())  
		{	
			x= Double.parseDouble(sc.next());
			y= Double.parseDouble(sc.next());
			z= Double.parseDouble(sc.next());
			//
			if((i%n)==0){ //only adds every nth point 
				points.add(new Point3D(x,y,z));
			} 
			i++;
		}   
		sc.close();  //closes the scanner  
		return points;
  }
  public static void main(String[] args) throws Exception { 
  	//predefined arguments, if none are provided
  	String[] params = new String[] {"kd", "0.5", "Point_Cloud_3.csv", "10"};
  	//if arguments are provided, overide the predefined arguments with the provided one
  	for(int i=0;i<args.length;i++){params[i]=args[i];}
  	double eps = Double.parseDouble(params[1]);
  	List<Point3D> points= Exp2.read(params[2],Integer.parseInt(params[3]));

  	double time = 0;
  	double avgTime;

  	if(params[0].equals("lin")){
  		NearestNeighbors nn= new NearestNeighbors(points);
  		for(Point3D p: points){ 
  			double startTime = System.nanoTime(); //starts timer to measure runtime for NN rangeQuery()
			nn.rangeQuery(p, eps);
			double endTime = System.nanoTime(); 
			double duration = (endTime - startTime)/1000000; // in milliseconds.
			time+=duration; 
  		}
  		avgTime = time/points.size(); //finds average runtime for NN rangeQuery()
		System.out.println("Average Compute Time for Linear: "+avgTime+"ms");
  	}else if(params[0].equals("kd")){
  		NearestNeighborsKD nnkd= new NearestNeighborsKD(points);
  		for(Point3D p: points){
  			double startTime = System.nanoTime(); //starts timer to measure runtime for NNKD rangeQuery()
			nnkd.rangeQuery(p, eps);
			double endTime = System.nanoTime();
			double duration = (endTime - startTime)/1000000; // in milliseconds.
			time+=duration;
  		}
  		avgTime = time/points.size(); //finds average runtime for NNKD rangeQuery()
		System.out.println("Average Compute Time for KD Tree: "+avgTime+"ms");
  	}
  }
}