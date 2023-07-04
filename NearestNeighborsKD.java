//SHUBHI BHOORA 300228522 CSI2110
import java.util.*;
public class NearestNeighborsKD {
	KDtree kdTree;

    public NearestNeighborsKD(List<Point3D> points){
    	kdTree = new KDtree();
		List<Point3D> neighbors;
		
		for (Point3D p : points) {
			kdTree.insert(p,kdTree.getRoot(),0); 
		}
    }

    public List<Point3D> rangeQuery(Point3D p, double eps) {
		List<Point3D> neighbors = new ArrayList<Point3D>();
		rangeQuery(p, eps, neighbors, kdTree.getRoot());
		return neighbors;
	}

	public void rangeQuery(Point3D p, double eps, List<Point3D> n, KDtree.KDnode node) {
		if (node == null){
			return;
		}
		if (p.distance(node.point) < eps){
			n.add(node.point);
		}
		if ((p.get(node.axis) - eps) <= node.value){
			rangeQuery(p, eps, n, node.left);
		}
		if ((p.get(node.axis) + eps) > node.value){
			rangeQuery(p, eps, n, node.right);
		}
		return;
	}

}