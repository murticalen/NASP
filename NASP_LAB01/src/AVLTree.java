import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

/**
 * @author Murta
 *
 */
public class AVLTree<T extends Comparable<T>> {
	
	
	public AVLNode<T> root;
	
	public static void readFromFileAndCreateTree(
			AVLTree<Integer> tree, String string) throws IOException {
		File file = new File(System.getProperty("user.dir")+"\\"+string+".txt");
		System.out.println(file);
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
		if(root == null){
			root = new AVLNode<T>(data, 1);
			root.h = 1;
			return;
		}
		AVLNode<T> added;
		if(root.data.compareTo(data) > 0){
			added = addData(data, root, root.leftChild, 1);
		}
		else{
			added = addData(data, root, root.rightChild, 1);
		}
		AVLDef(added);
	}
	private AVLNode<T> addData(T data, AVLNode<T> parent, AVLNode<T> next, int height){
		if(next == null){
			next = new AVLNode<T>(data, height);
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
				return addData(data, next, next.leftChild, height+1);
			}
			else{
				return addData(data, next, next.rightChild, height+1);
			}
		}
/*		int lh = 0, rh = 0;
		if(next.leftChild != null){
			lh = next.leftChild.h;
			next.leftHeight = lh;
		}
		if(next.rightChild != null){
			rh = next.rightChild.h;
			next.rightHeight = rh;
		}
		next.h = Math.max(next.leftHeight, next.rightHeight);*/
	}	
	public void AVLDef(AVLNode<T> current) {
		while(current.parent != null){
			if(current.parent != null && current.FR == 1 && current.parent.FR == 2){
				current.rightRotation(current.parent, root);
			}
			if(current.parent != null && current.FR == -1 && current.parent.FR == -2){
				current.leftRotation(current.parent, root);
			}
			if(current.parent != null && current.FR == -1 && current.parent.FR == 2){
				
			}
			if(current.parent != null && current.FR == 1 && current.parent.FR == -2){
				return;
			}
			current = current.parent;
		}
	}
	
	public void print(){
		if(root != null){
			root.countHeightForAll(1);
			root.recuriveString(root.maxHeight());
		}
	}

	public static void main(String[] args) throws Exception{
		AVLTree<Integer> tree = new AVLTree<Integer>();
		if(args.length != 1){
			System.out.println("The number of arguments must be exactly 1,"
					+ " name of the file to read from, without txt extension");
			System.exit(-1);
		}
		readFromFileAndCreateTree(tree, args[0]);
		tree.print();
		//tree.addData("Error");
		//tree.addData("ERROR");
		//tree.addData("Not good");
		//tree.addData("Fail");
		
		/*tree.addData("A");
		tree.addData("QQ");
		tree.addData("PP");
		tree.addData("P");
		tree.addData("Q");
		tree.addData("R");
		tree.print();
		tree.root.rightChild.leftChild.rightRotation(tree.root.rightChild);*/
		System.out.println("####################################"
				+ "Novi ispis stabla, nakon dodavanja"
				+ "####################################");
		tree.print();
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		while(true){
			System.out.println("Unesite jedan broj za unos u stablo");
			String line = reader.readLine();
			try{
				tree.addData(Integer.parseInt(line));
				System.out.println("####################################"
						+ "Novi ispis stabla, nakon dodavanja"
						+ "####################################");
				tree.print();
			}
			catch(Exception e){
				System.err.println(e.getStackTrace());
			}
		}
	}

}
