package project4;

public class EMPset<E> {

	private int manyItems;
	private int initialCapacity = 10;
	private E[] data;
	private boolean[] flag;

	@SuppressWarnings("unchecked")

	public EMPset() {

		if (initialCapacity < 0)

			throw new IllegalArgumentException("Invalid initial capacity");

		data = (E[]) new Object[initialCapacity];

		flag = new boolean[initialCapacity];

		manyItems = 0;
	}

	@SuppressWarnings("unchecked")

	public EMPset(int t) {

		data = (E[]) new Object[t];
		flag = new boolean[t];
		manyItems = 0;

	}

	public void add(E element) {

		if (!isElement(element)) {

			if (manyItems == data.length) {

				ensureCapacity((manyItems + 1) * 2);
				
			}

			data[manyItems] = element;
			flag[manyItems] = false;
			manyItems++;

		}
	}
	
	public E getEmp(int key) {
		
		return data[key];
		
	}
	
	public void upDate(int key, EmpRecord emp) {
		
		EmpRecord temp = (EmpRecord) data[key];
		
		temp.setFirstName(emp.getFirstName());
		temp.setLastName(emp.getLastName());
		temp.setPosition(emp.getPosition());
		temp.setSite(emp.getSite());
		
	}
	
	public boolean isElement(E element) {
		
		for(int i = 0; i < data.length; i++) {
			
			if(data[i] != null) {
				
				if(data[i].equals(element)) {
					
					return true;
					
				}
			}
		}
		
		return false;
		
	}
	
	public void addMany(@SuppressWarnings("unchecked") E... elements) {
		
		if((manyItems + elements.length) > data.length) {
			
			ensureCapacity((manyItems + elements.length) * 2);
			
		}
		
		manyItems += elements.length;
		
	}
	
	@SuppressWarnings("unchecked")
	
	public void ensureCapacity(int minimumCapacity) {
		
		E[] biggerArray;
		
		boolean[] temp = null;
		
		if(data.length < minimumCapacity) {
			
			biggerArray = (E[]) new Object[minimumCapacity];
			
			System.arraycopy(data, 0, biggerArray, 0, manyItems);
			System.arraycopy(flag, 0, temp, 0, manyItems);
			
			flag = temp;
			data = biggerArray;
			
		}
	}
	
	@SuppressWarnings("unchecked")
	public void trimoSize() {
		
		E[] trimmedArray;
		
		if(data.length != manyItems) {
			
			trimmedArray = (E[]) new Object[manyItems];
			
			System.arraycopy(data, 0, trimmedArray, 0, manyItems);
			
			data = trimmedArray;
			
		}
	}
	
	public boolean returnflag(int n) {
		
		return flag[n];
		
	}
	
	public static <E extends Comparable<E> & Cloneable> EMPset<E> union(EMPset<E> s1, EMPset<E> s2){
		
		EMPset<E> newSet = new EMPset<E>(s1.getCapacity() + s2.getCapacity());
		
		newSet.manyItems = s1.manyItems + s2.manyItems;
		
		return newSet;
		
	}
	
	public void remove(int key) {
		
		flag[key] = true;
		
	}
	
	public int getCapacity() {
		
		return data.length;
		
	}
	
	public int size() {
		
		return manyItems;
		
	}
	
	
	
}
