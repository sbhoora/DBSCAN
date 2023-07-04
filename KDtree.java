//SHUBHI BHOORA 300228522 CSI2110
public class KDtree {

	public class KDnode {
		public Point3D point;
		public int axis;
		public double value;
		public KDnode left;
		public KDnode right;

		public KDnode(Point3D pt, int axis) {
			this.point = pt;
			this.axis = axis;
			this.value = pt.get(axis);
			left = null;
			right = null;
		}
		
	}

	private KDnode root;

	public KDnode getRoot(){
		return root;
	}

	// construct empty tree
	public KDtree() {
		root = null;
	}

	public KDnode insert(Point3D point, KDnode node, int axis) {
		if(root==null){
			this.root = new KDnode(point, axis);
		}
		if(node == null){
			node = new KDnode(point, axis);
		} else if (point.get(axis) <= node.value) {
			node.left = insert(point, node.left, (axis+1) % 3);
		}else{
			node.right = insert(point, node.right, (axis+1) % 3);
		}
		return node;
	}
}