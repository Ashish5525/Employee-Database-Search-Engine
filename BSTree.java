package project4;

import java.util.ArrayList;

import javafx.scene.Node;

public class BSTree<E> {

	private Node<E> root;

	private int manyItems;

	public BSTree() {

		root = null;
		manyItems = 0;

	}

	public void addEmp(String emp, int index) {

		Node<E> temp = new Node<E>(emp, index, null, null);
		this.root = addEmpNode(this.root, temp);
		manyItems++;

	}

	private Node<E> addEmpNode(Node<E> root, Node<E> emp) {

		if (root == null) {

			root = emp;
		}

		if (root.getId().compareTo(emp.getId()) < 0) {

			root.setRight(addEmpNode(root.getRight(), emp));

		}

		else if (root.getId().compareTo(emp.getId()) > 0) {

			root.setLeft(addEmpNode(root.getLeft(), emp));

		}

		return root;

	}

	public void deleteEmp(Node<E> emp) {

		this.root = deleteEmp(this.root, emp);
		manyItems--;

	}

	private Node<E> deleteEmp(Node<E> root, Node<E> emp) {

		if (root == null) {

			return root;

		}

		if (root.getId().compareTo(emp.getId()) < 0) {

			root.setRight(deleteEmp(root.getRight(), emp));

		}

		else if (root.getId().compareTo(emp.getId()) > 0) {

			root.setLeft(deleteEmp(root.getLeft(), emp));

		}

		else {

			if (root.getLeft() == null) {

				return root.getRight();

			}

			else if (root.getRight() == null) {

				return root.getLeft();

			}

			root = getLeftMost(root.getRight());

		}

		return root;

	}

	public Node<E> getLeftMost(Node<E> root) {

		if (root.getLeft() != null) {

			return getLeftMost(root.getLeft());

		}

		return root;

	}

	public Node<E> findEmp(String id) {

		return findEmpRec(this.root, id);

	}

	public boolean isElement(String id) {

		if (findEmpRec(this.root, id) != null) {

			return true;

		}

		return false;

	}

	private Node<E> findEmpRec(Node<E> root, String emp) {

		if (root == null) {

			return null;

		}

		if (root.getId().compareTo(emp) < 0) {

			return findEmpRec(root.getRight(), emp);

		}

		else if (root.getId().compareTo(emp) > 0) {

			return findEmpRec(root.getLeft(), emp);

		}

		return root;
	}

	public ArrayList<Integer> display() {

		ArrayList<Integer> listAll = new ArrayList<Integer>();

		list(root, listAll);

		return listAll;

	}

	public ArrayList<Integer> list(Node<E> node, ArrayList<Integer> all) {

		if (node == null)

			return all;

		list(node.left, all);
		all.add(node.key);
		list(node.right, all);

		return all;

	}

	public static class Node<E> {

		private String id;
		private int key;
		private Node<E> left;
		private Node<E> right;

		public Node(String emp, int key, Node<E> left, Node<E> right) {

			this.id = emp;
			this.key = key;
			this.left = left;
			this.right = right;
		}

		public int getKey() {

			return key;

		}

		public String getId() {

			return id;

		}
		
		public Node<E> getRight(){
			
			return right;
			
		}
		
		public Node<E> getLeft(){
			
			return left;
			
		}
		
		public void setId(String id) {
			
			this.id = id;
			
		}
		
		public void setKey(int Key) {
			
			this.key = key;
			
		}
		
		public void setRight(Node<E> node) {
			
			right = node;
			 
		}
		
		public void setLeft(Node<E> node) {
			
			left = node;
			
		}
		
		public boolean isLeaf() {
			
			return(left == null) && (right == null);
			
		}
		
		public static <E> int treeSize(Node<E> root) {
			
			if(root == null)
				return 0;
			
			else
				return 1 + treeSize(root.left) + treeSize(root.right);
			
		}
		
		public Node<E> removeRightMost(){
			
			if(right == null)
				return left;
			
			else {
				
				right = right.removeRightMost();
				
				return this;
			}
		}
		
		public Node<E> removeLeftMost(){
			
			if(left == null)
				return right;
			
			else {
				
				left = left.removeLeftMost();
				
				return this;
			}
		}
		
		public static <E> Node <E> treeCopy(Node<E> source){
			
			Node<E> leftCopy, rightCopy;
			
			if(source == null)
				return null;
			
			else {
				
				leftCopy = treeCopy(source.left);
				rightCopy = treeCopy(source.right);
				
				return new Node<E>(source.id, source.key, leftCopy, rightCopy);
			}
		}

	}
}
