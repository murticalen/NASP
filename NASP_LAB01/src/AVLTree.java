import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Murta
 *
 */
public class AVLTree<T extends Comparable<T>> {
	
	
	public AVLNode<T> root;
	public Set<T> set = new HashSet<T>();
	
	public static void readFromFileAndCreateTree(
			AVLTree<Integer> tree, String string) throws IOException {
		File file = new File(System.getProperty("user.dir")+"\\"+string+".txt");
		BufferedReader reader = new BufferedReader
				(new InputStreamReader(new FileInputStream(file)));
		String line;
		while((line = reader.readLine())!=null){
			String[] num = line.split(" ");
			for(String n : num){
				try{
					tree.addData(Integer.parseInt(n));
				}
				catch(Exception e){System.err.println(e.getStackTrace());}
			}
		}
		reader.close();
	}
	
	public void addData(T data){
		if(data == null){
			return;
		}
		if(set.contains(data)){
			duplicate(data);
			return;
		}
		set.add(data);
		if(root == null){
			root = new AVLNode<T>(data);
			this.print();
			return;
		}
		AVLNode<T> added;
		if(root.data.compareTo(data) > 0){
			added = addData(data, root, root.leftChild);
		}
		else{
			added = addData(data, root, root.rightChild);
		}
		assignHeight(added);
		AVLDef(added);
		this.print();
	}
	private AVLNode<T> addData(T data, AVLNode<T> parent, AVLNode<T> next){
		if(next == null){
			next = new AVLNode<T>(data, parent);
			if(parent.data.compareTo(data) > 0){
				parent.leftChild = next;
			}
			else{
				parent.rightChild = next;
			}
			return next;
		}
		else{
			if(next.data.compareTo(data) > 0){
				return addData(data, next, next.leftChild);
			}
			else{
				return addData(data, next, next.rightChild);
			}
		}
	}
	public void assignHeight(AVLNode<T> current){
		while(current.parent != null){
			if(current.parent.leftChild == current){
				current.parent.leftHeight = current.h() +1;
				if(AVLDef(current)) break;
				if(current.parent == null) break;
			}
			else if(current.parent.rightChild == current){
				current.parent.rightHeight = current.h() +1;
				if(AVLDef(current)) break;
				if(current.parent == null) break;
			}
			current = current.parent;
		}
	}
	public boolean AVLDef(AVLNode<T> current) {
		if(current.parent != null && current.FR() == 1 && current.parent.FR() == 2){
			AVLNode<T> temp = current.parent;
			current.leftRotation(temp, root);
			temp.resetHeights();
			current.resetHeights();
			return true;
		}
		if(current.parent != null && current.FR() == -1 && current.parent.FR() == -2){
			AVLNode<T> temp = current.parent;
			current.rightRotation(temp, root);
			temp.resetHeights();
			current.resetHeights();
			return true;
		}
		if(current.parent != null && current.FR() == -1 && current.parent.FR() == 2){
			AVLNode<T> parent = current.parent;
			AVLNode<T> leftC = current.leftChild;
			leftC.rightRotation(current, root);
			leftC.leftRotation(parent, root);
            current.resetHeights();
            parent.resetHeights();
            leftC.resetHeights();
            return true;
		}
		if(current.parent != null && current.FR() == 1 && current.parent.FR() == -2){
			AVLNode<T> parent = current.parent;
			AVLNode<T> rightC = current.rightChild;
			rightC.leftRotation(current, root);
			rightC.rightRotation(parent, root);
            current.resetHeights();
            parent.resetHeights();
            rightC.resetHeights();
            return true;
		}
		return false;
	}
	
	public void print(){
		System.out.println("##################################################");
		if(root != null){
			root.recuriveString(root.maxHeight());
		}
	}
	
	public void duplicate(T data){
		System.out.println("##################################################");
		System.out.println("Duplicate value "+data+" was ignored from insertion.");
	}

	public static void main(String[] args) throws Exception{
		AVLTree<Integer> tree = new AVLTree<Integer>();
		if(args.length != 1){
			System.out.println("The number of arguments must be exactly 1,"
					+ " name of the file to read from, without txt extension");
			System.exit(-1);
		}
		readFromFileAndCreateTree(tree, args[0]);
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		tree.root.printBinaryTree(0);
		while(true){
			System.out.println("Unesite jedan broj za unos u stablo");
			String line = reader.readLine();
			try{
				tree.addData(Integer.parseInt(line));
				tree.root.printBinaryTree(0);
			}
			catch(Exception e){
				System.err.println(e.getStackTrace());
			}
		}
	}
}
