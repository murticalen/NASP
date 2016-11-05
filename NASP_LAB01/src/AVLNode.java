public class AVLNode<T extends Comparable<T>>{
	public T data;
	public int FR;
	public int height;
	public int leftHeight;
	public int rightHeight;
	public AVLNode<T> leftChild;
	public AVLNode<T> rightChild;
	public AVLNode<T> parent;
	
	public AVLNode(T data, int height) {
		this.data = data;
		this.leftHeight = 0;
		this.rightHeight = 0;
		this.countFR();
	}
	public AVLNode(T data, int height, AVLNode<T> parent){
		this.data = data;
		this.parent = parent;
		this.height = height;
		this.countFR();
	}
	public AVLNode(T data, int height, AVLNode<T> leftChild, AVLNode<T> rightChild,
			AVLNode<T> parent) {
		this.data = data;
		this.leftChild = leftChild;
		this.rightChild = rightChild;
		this.parent = parent;
		this.countFR();
	}	
	public void recuriveString(int maxHeight){
		if(leftChild != null){
			leftChild.recuriveString(maxHeight);
		}
		int level = height;
		if(level == 1){
			System.out.print("R#");
		}
		else{
			System.out.print(level+"#");
		}
		while(level < maxHeight - 1){
			level++;
			System.out.print("======");
		}
		System.out.println(" "+data);
		if(rightChild != null){
			rightChild.recuriveString(maxHeight);
		}
	}
	public int maxHeight(){
		int max = 1;
		if(leftChild != null){
			max = leftChild.maxHeight();
		}
		if(rightChild != null){
			int heightRight = rightChild.maxHeight();
			max = max > heightRight ? max : heightRight;
		}
		return max + 1;
	}
	public void countHeightForAll(int height){
		this.height = height;
		if(this.leftChild != null){
			this.leftChild.countHeightForAll(height+1);
		}
		if(this.rightChild != null){
			this.rightChild.countHeightForAll(height+1);
		}
	}
	public void rightRotation(AVLNode<T> parent, AVLNode<T> root){
		if(parent.parent != null){
			if(parent.parent.rightChild == parent){
				parent.parent.rightChild = this;
			}
			else{
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
	}
	public void leftRotation(AVLNode<T> parent, AVLNode<T> root){
		if(parent.parent != null){
			if(parent.parent.rightChild == parent){
				parent.parent.rightChild = this;
			}
			else{
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
	}
	public void countFR(){
		int leftHeight = 0, rightHeight = 0;
		if(leftChild != null){
			leftChild.countFR();
			leftHeight = leftChild.height;
		}
		else{
			leftHeight = height;
		}
		if(rightChild != null){
			rightChild.countFR();
			rightHeight = rightChild.height;
		}
		else{
			rightHeight = height;
		}
		this.FR = leftHeight - rightHeight;
	}
}