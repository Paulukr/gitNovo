package mp2.util;

import java.util.ArrayList;
import java.util.Arrays;

class BinaryTreeE<T extends Comparable<T>> {
	BinaryTreeNodeE root;
	int height, toTheLeft, toTheRight, width;
	int add(T data) {
		if(root == null) {
			root = new BinaryTreeNodeE(data);
			System.out.println(root.data);
			height = 1;
			width = 1;
			return height;
		}
		int heightTemp = 1;
		int widthTemp = 0;
		for(BinaryTreeNodeE currentNode = root;;) {
			heightTemp ++;
			if(data.compareTo(currentNode.data) == 0) {  currentNode.duplicate++; return heightTemp;}else
			if(data.compareTo(currentNode.data) > 0) {
				widthTemp ++;
				if(currentNode.right == null) {
					currentNode.right = new BinaryTreeNodeE(data);
					//System.out.println(currentNode.right.data);
					if(widthTemp > toTheRight) toTheRight = widthTemp;
					width = toTheLeft + toTheRight + 1;
					if(heightTemp > height) height = heightTemp;
					return heightTemp;
				}else
				currentNode = currentNode.right;
			}else {
				widthTemp --;
				if(currentNode.left == null) {
					currentNode.left = new BinaryTreeNodeE(data);
					//System.out.println(currentNode.left.data);
					if(widthTemp < -toTheLeft) toTheLeft = -widthTemp;
					width = toTheLeft + toTheRight + 1;
					if(heightTemp > height) height = heightTemp;
					return heightTemp;
				}else
				currentNode = currentNode.left;
				//prevDir = true;
			}
			//System.out.println(currentNode.data);
			//prev = currentNode;
		}
		
	}
	int printTree() {
		String map[][] = new String[6][6];
		map[0][2] = "Hello";
		map[0][1] = root.data.toString();
		//System.out.println(Arrays.deepToString(map));
		for (String[] ss : map) {
			if(ss != null) for (String s : ss) if(s != null) System.out.print(s + " ");
			System.out.println();
		}
		int x = 2, y = 0;
//		for(BinaryTreeNodeE currentNode = root;currentNode != null;) {
//			if(currentNode == null)  else
//				System.out.println(currentNode.data);
//		}

			int rightness = 0;
			boolean leftDone = false;
			int trace[] = new int[10];
			int traceHeight = 0;
			Start:
			for(BinaryTreeNodeE currentNode = root;currentNode != null;) {
				//traceHeight ++;
				//trace[traceHeight] = 1;
//				if(currentNode == null) break Start; else {	
				if(leftDone) {
					x++; break;
				}
					map[y][x] = currentNode.data.toString();
					for(;(currentNode.left != null);currentNode = currentNode.left)
					{
						trace[++traceHeight] = 0;
						y++;
						x--;
						map[y+1][x-1] = "/";
						map[y+2][x-2] = (String) currentNode.left.data;
					}
					for (String[] ss : map) {
						for (String s : ss) if(s != null) System.out.print(s + " ");
						System.out.println();
					}
					if((currentNode.right != null)) {
						currentNode = currentNode.right;
						trace[++traceHeight] = 1;
						rightness ++;
						y++;
						x++;
						continue;
					}else {
						if((currentNode.parent != null)) {
							currentNode = currentNode.parent;
							trace[++traceHeight] = 1;
							leftDone = true;
							y--;
						}
					}
//				}
			}
		System.out.println("null");
		return height;
	}

	
	void printTreeR(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		if(node == null) return;
		map[y][x + rootX] = node.data.toString();
		y++;
		if(node.left != null) printTreeR(y, x-2, node.left, rootX, map);
		if(node.right!= null) printTreeR(y, x+2, node.right, rootX, map);
		
	}
	void printTreeRv1(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		if(node == null) return;
		map[y][x + rootX] = node.data.toString();
		y++;
		if(node.left != null) {
			map[y][x - 1 + rootX] = "/";
			printTreeRv1LeftHalf(y + 1, x - 2, node.left, rootX, map);
		}
		if(node.right!= null) {
			map[y][x + 1 + rootX] = "\\";
			printTreeRv1RightHalf(y + 1, x + 2, node.right, rootX, map);
		}
		
	}
	void printTreeRv1LeftHalf(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		if(node == null) return;
		map[y][x + rootX] = node.data.toString();
		y++;
		if(node.left != null) {
			map[y][x + 1 + rootX] = "/";
			printTreeRv1LeftHalf(y + 1, x-(int)Math.ceil(2*Math.pow(2, y/2)), node.left, rootX, map);
		}
		if(node.right!= null) {
			map[y][x + rootX] = "|";
			printTreeRv1LeftHalf(y + 1, x, node.right, rootX, map);
		}
	}
	void printTreeRv1RightHalf(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		if(node == null) return;
		map[y][x + rootX] = node.data.toString();
		y++;
		if(node.left != null) {
			map[y][x + rootX] = "|";
			printTreeRv1RightHalf(y + 1, x, node.left, rootX, map);
		}
		if(node.right!= null) {
			map[y][x - 1 + rootX] = "\\";
			printTreeRv1RightHalf(y + 1, x+(int)Math.ceil(2*Math.pow(2, y/2)), node.right, rootX, map);
		}
		
	}
	void printTreeRv2(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		// go top leftmost
		BinaryTreeNodeE leftmostNode;
		leftmostNode = node;
		int leftmostNodeX = 0;
		int leftmostNodeY = 0;
		while(leftmostNode.left != null) {
			leftmostNode = leftmostNode.left;
			leftmostNodeX++;
			leftmostNodeY++;
		}
		map[leftmostNodeY * 2 + 1][0] = leftmostNode.data.toString();
		map[leftmostNodeY * 2 ][0] = "/";
		
		leftmostNode = leftmostNode.parent;
		map[leftmostNodeY * 2 + 1 - 2][0] = leftmostNode.data.toString();
		
		if(leftmostNode.right != null) {
			leftmostNode = leftmostNode.right;
		}
		map[leftmostNodeY * 2 + 1][0] = leftmostNode.data.toString();
		map[leftmostNodeY * 2 ][0] = "/";
		while(leftmostNode.left != null) {
			leftmostNode = leftmostNode.left;
			leftmostNodeX++;
			leftmostNodeY++;
		}
		
		
		if(node == null) return;
		map[y][x + rootX] = node.data.toString();
		y++;
		if(node.left != null) {
			map[y][x - 1 + rootX] = "/";
			printTreeRv1LeftHalf(y + 1, x - 2, node.left, rootX, map);
		}
		if(node.right!= null) {
			map[y][x + 1 + rootX] = "\\";
			printTreeRv1RightHalf(y + 1, x + 2, node.right, rootX, map);
		}
		
	}
	int printTreeRv3Unix(int y , int x, BinaryTreeNodeE node, int rootX, String map[][]) {
		if(node != null) map[y][x] = node.data.toString(); else return y;
		y++;
		
		int lastY = y ;
		if(node.right != null) {
			map[y][x] = "L";
			map[y][x + 1] = "-";
			y = printTreeRv3Unix(y, x + 2, node.right, 0, map) ;
		}
		if(node.left != null) {
			for(;lastY <= y; lastY ++) map[lastY][x] = "|";
			map[y][x + 1] = "~";
			y = printTreeRv3Unix(y, x + 2, node.left, 0, map);
		}
		return y;
	}
	BinaryTreeNodeE mirrorTree(BinaryTreeNodeE newNode, BinaryTreeNodeE oldNode){
		if(oldNode == null) return null;	
		//newNode.data = oldNode.data;
		if(oldNode.left != null) newNode.right = mirrorTree(new BinaryTreeNodeE(oldNode.left.data), oldNode.left);
		if(oldNode.right != null) newNode.left = mirrorTree(new BinaryTreeNodeE(oldNode.right.data), oldNode.right);
	return newNode;	
	}
	BinaryTreeNodeE doubleTree(BinaryTreeNodeE newNode, BinaryTreeNodeE oldNode) {
		if(oldNode == null) return null;
		newNode.left = new BinaryTreeNodeE(oldNode.data);
		if(oldNode.left != null) {
			newNode.left.left = doubleTree(new BinaryTreeNodeE(oldNode.left.data), oldNode.left);
		}
		if(oldNode.right != null) newNode.right = doubleTree(new BinaryTreeNodeE(oldNode.right.data), oldNode.right);
		return newNode;
	}
	BinaryTreeNodeE sameTree(BinaryTreeNodeE newNode, BinaryTreeNodeE oldNode) {
		if(oldNode == null) return null;
		if(oldNode.left != null)  newNode.left = doubleTree(new BinaryTreeNodeE(oldNode.left.data), oldNode.left);
		if(oldNode.right != null) newNode.right = doubleTree(new BinaryTreeNodeE(oldNode.right.data), oldNode.right);
		return newNode;
	}
	
	public String toString() {
		String returnString = "";
		String map[][] = new String[100][100];
		map[printTreeRv3Unix(0, 0, root, 0, map)] = null;//(tree.toTheLeft -1 + 1)
		for (String[] ss : map) {
			if(ss != null) for (String s : ss) if(s != null) returnString += s + " "; else returnString += "  ";
			else break;
			returnString += "\n";
		}
		return returnString;
	}
	
public static void main(String[] args) {
		// TODO Auto-generated method stub
		BinaryTreeE<Integer> tree = new BinaryTreeE<Integer>();
		//for(int i =0 ; i < 20; i ++) tree.add(new Integer((int)(10 * Math.random())));
		tree.add(4);
		tree.add(5);
		tree.add(2);
		tree.add(1);
		tree.add(3);
		//tree.printTree();
//		tree.add(2);
//		tree.add(1);
//		tree.add(3);
//		
		System.out.println("tree size " + tree.root.size());
		System.out.println("tree depth " + tree.root.depthOOP());
		System.out.println("tree min " + tree.root.minValueOOP());
		System.out.println("tree max " + tree.root.maxValueOOP());
		System.out.println("tree to line " + tree.root.printToLine1());
//		System.out.println("tree has path sum " + tree.root.hasPathSum(20, 0));
		System.out.println("tree has paths :");
		tree.root.printPaths(new byte[0], 0);
		String map[][] = new String[tree.height][2*(int)Math.pow(2, tree.height)];
		tree.printTreeR(0, 0, tree.root, (tree.toTheLeft -1 + 1)*2, map);
		System.out.println("tree h:" + tree.height + " w:" + tree.width);
		for (String[] ss : map) {
			if(ss != null) for (String s : ss) if(s != null) System.out.print(s + " "); else System.out.print("  ");
			System.out.println();
		}
		System.out.println("tree h:" + tree.height + " w:" + tree.width);
		String map2[][] = new String[tree.height * 2][4*(int)Math.pow(2, tree.height)];
		tree.printTreeRv1(0, 0, tree.root, (int)Math.pow(2, tree.height)*2, map2);//(tree.toTheLeft -1 + 1)
		for (String[] ss : map2) {
			if(ss != null) for (String s : ss) if(s != null) System.out.print(s + " "); else System.out.print("  ");
			System.out.println();
		}		
		String map3[][] = new String[tree.height * 2][4*(int)Math.pow(2, tree.height)];
		tree.printTreeRv1(0, 0, tree.root, (int)Math.pow(2, tree.height)*2, map3);//(tree.toTheLeft -1 + 1)
		for (String[] ss : map3) {
			if(ss != null) for (String s : ss) if(s != null) System.out.print(s + " "); else System.out.print("  ");
			System.out.println();
		}
		System.out.println("PrintPostoder:\n" + tree.root.printPostorder());
		BinaryTreeE<Integer> mirrorTree = new BinaryTreeE<Integer>();
		mirrorTree.root = tree.mirrorTree(tree.new BinaryTreeNodeE(tree.root.data), tree.root);
		System.out.println("mirror tree\n" + mirrorTree);
		System.out.println("to String\n" + tree);
		System.out.println("to ArrayList\n" + tree.root.toArrayList());
		ArrayList<Integer> list =  tree.root.toArrayList();
		System.out.println("to ArrayList\n" + list.size());
		System.out.println("to Array\n" + Arrays.toString(tree.root.toArray()));
		BinaryTreeE<Integer> doubleTree = new BinaryTreeE<Integer>();
		doubleTree.root = tree.doubleTree(tree.new BinaryTreeNodeE(tree.root.data), tree.root);
		System.out.println("double tree\n" + doubleTree);
		BinaryTreeE<Integer> sameTree = new BinaryTreeE<Integer>();
		sameTree.root = tree.sameTree(tree.new BinaryTreeNodeE(tree.root.data), tree.root);
		System.out.println("same tree\n" + sameTree);
		System.out.println(" tree\n" + tree);
	}
	
	public class BinaryTreeNodeE   {//<T extends Comparable<T>>
		public void m1(){System.out.println("m1");};
		BinaryTreeNodeE(T elem) {
			data = elem;left=null;right=null;parent=null;
		}
		public  T data; //node data
		public BinaryTreeNodeE left;//left child link
		public BinaryTreeNodeE right;//right child link
		public BinaryTreeNodeE parent;//parent link
		
		int duplicate;
		int height;
		
		int size(BinaryTreeNodeE node) {
			if(node != null) return 1 + size(node.left) +size(node.right);
			return 0;
			}
		int size() {
			return size(this);
		}
		int depth(BinaryTreeNodeE node) {
			if(node != null) return Math.max(depth(this.left), depth(this.right)) + 1;
			return 0;
		}
		int depth() {
			return depth(this);
		}
		int depthOOP() {
			return Math.max((this.left == null)?0:this.left.depthOOP(), (this.right == null)?0:this.right.depthOOP()) + 1;
		}
		T minValueOOP() {
			if(this.left != null) return  this.left.minValueOOP();
			return data;
		}
		T maxValueOOP() {
			if(this.right != null) return  this.right.maxValueOOP();
			return data;
		}
		String printToLine1() {
			return ((this.left == null)?"":this.left.printToLine1()) + this.data + ((this.right == null)?"":this.right.printToLine1());
		}
		ArrayList<T> toArrayList(){
			ArrayList<T> list = new ArrayList<T>();
			toArrayListVoid(list);
			return list;
		}
		private void toArrayListVoid(ArrayList<T> list){

			if(left != null) left.toArrayListVoid(list);
			list.add(data);  
 			if(right != null) right.toArrayListVoid(list);
		}
		Object[] toArray() {
			Object[] o = toArrayList().toArray();
			return o;
//			return (T[]) toArrayList().toArray();
		}
//		T[] toArray2() {
//			ArrayList<T> list = toArrayList();
//			T[] array = new T[list.size()];
//			return o;
////			return (T[]) toArrayList().toArray();
//		}
//		boolean hasPathSum(int sum, int tsum) {
//			if(left != null) if(left.hasPathSum(sum, tsum + (int)data)) return true; else
//				if(right != null) return right.hasPathSum(sum, tsum + (int)data); 
//			if(sum == tsum + (int)data) return true; else return false;		
//		}
		void printPaths(byte[] path, int pathLength) {
			if((left == null)&&(right == null)) { System.out.println(Arrays.toString(path)); return;}
			if(left != null) {
				byte[] tpath = Arrays.copyOf(path, pathLength + 1);
				tpath[pathLength] = -1;
				left.printPaths(tpath, pathLength + 1);
			}
			if(right != null) {
				byte[] tpath = Arrays.copyOf(path, pathLength + 1);
				tpath[pathLength] = 1;
				right.printPaths(tpath, pathLength + 1);
			}
		}
		String printPostorder() {
			return (left == null?"":left.printPostorder()) + (right == null?"":right.printPostorder()) + data.toString(); 
		}
		
//	1	0void printPaths(int[][] paths, int pathsLen, int[] path, int pathLength) {
//			if((left == null)&&(right == null)) paths[pathsLen] = new int[pathLength];
//			
//		}
	}
//	class BinaryTreeEInt  extends BinaryTreeE<Integer>  {
//		public class BinaryTreeNodeEInt   extends BinaryTreeE<Integer>.BinaryTreeNodeE{
//			BinaryTreeNodeEInt(Integer elem) {
//				super(elem);
//				// TODO Auto-generated constructor stub
//			}
//			boolean hasPathSum(int sum, int tsum) {
//				if(left != null) if(left.hasPathSum(sum, tsum + (int)data)) return true; else
//					if(right != null) return right.hasPathSum(sum, tsum + (int)data); 
//				if(sum == tsum + (int)data) return true; else return false;		
//			}
//		}
//	}
}
