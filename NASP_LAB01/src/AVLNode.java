public class AVLNode<T extends Comparable<T>>{
	public T data;
	public int leftHeight;
	public int rightHeight;
	public AVLNode<T> leftChild;
	public AVLNode<T> rightChild;
	public AVLNode<T> parent;
	
	public AVLNode(T data) {
		this.data = data;
		this.leftHeight = 0;
		this.rightHeight = 0;
	}
	public AVLNode(T data, AVLNode<T> parent){
		this.data = data;
		this.parent = parent;
		this.leftHeight = 0;
		this.rightHeight = 0;
	}
/*	public AVLNode(T data, int height, AVLNode<T> leftChild, AVLNode<T> rightChild,
			AVLNode<T> parent) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
		this.countFR();
	}*/	
	public void recuriveString(int maxHeight){
		if(leftChild != null){
			leftChild.recuriveString(maxHeight);
		}
		int level = h();
		if(level == maxHeight -1){
			System.out.print("R#");
		}
		else{
			System.out.print(level+"#");
		}
		while(level > 0){
			level--;
			System.out.print("======");
		}
		System.out.println(" "+data+" L:"+leftHeight+" R:"+rightHeight+" H:"+h());
		if(rightChild != null){
			rightChild.recuriveString(maxHeight);
		}
	}
	public int h(){
		return leftHeight > rightHeight ? leftHeight : rightHeight;
	}
	public int maxHeight(){
		int max = 0;
		if(leftChild != null){
			max = leftChild.maxHeight();
		}
		if(rightChild != null){
			int heightRight = rightChild.maxHeight();
			max = max > heightRight ? max : heightRight;
		}
		return max + 1;
	}
	public void printBinaryTree(int level){
		if(this.rightChild != null)
			this.rightChild.printBinaryTree(level+1);
	    if(level!=0){
	        for(int i=0;i<level-1;i++)
	            System.out.print("|\t");
	            System.out.println("|-------"+this.data);
	    }
	    else
	        System.out.println(this.data);
		if(this.leftChild != null)
			this.leftChild.printBinaryTree(level+1);
	}
	public void rightRotation(AVLNode<T> parent, AVLNode<T> root){
		if(parent.parent != null){
			if(parent.parent.rightChild == parent){
				parent.parent.rightChild = this;
			}
			else if(parent.parent.leftChild == parent){
				parent.parent.leftChild = this;
			}
		}
		else{
			root = this;
		}
		parent.leftChild = this.rightChild;
		this.rightChild = parent;
		this.parent = parent.parent;
		parent.parent = this;
        //try{parent.leftChild.parent = parent;}catch (Exception e){}
	}
	public void leftRotation(AVLNode<T> parent, AVLNode<T> root){
		if(parent.parent != null){
			if(parent.parent.rightChild == parent){
				parent.parent.rightChild = this;
			}
			else if(parent.parent.leftChild == parent){
				parent.parent.leftChild = this;
			}
		}
		else{
			root = this;
		}
		parent.rightChild = this.leftChild;
		this.leftChild = parent;
		this.parent = parent.parent;
		parent.parent = this;
		//try{parent.rightChild.parent = parent;}catch (Exception e){}
	}
	public void resetHeights(){
		if(this.leftChild != null){
			this.leftHeight = this.leftChild.h() +1;
		}
		else{
			this.leftHeight = 0;
		}
		if(this.rightChild != null){
			this.rightHeight = this.rightChild.h() +1;
		}
		else{
			this.rightHeight = 0;
		}
	}
	public int FR(){
		return rightHeight-leftHeight;
	}
}