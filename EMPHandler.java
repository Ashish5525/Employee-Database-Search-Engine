package project4;

import java.util.ArrayList;

public class EMPHandler {

	EMPset<EmpRecord> empArray;

	BSTree<String> tree;

	public EMPHandler() {

		empArray = new EMPset<>();
		tree = new BSTree<>();

	}

	public int addEmployee(EmpRecord emp) {

		if (tree.isElement(emp.emplID) == true) {

			int key = tree.findEmp(emp.emplID).getKey();

			if (empArray.getEmp(key).lastName.compareTo(emp.lastName) != 0) {

				String temp = emp.emplID;
				temp = temp.substring(0, 7);
				temp += "02";
				emp.setEmplID(temp);
				empArray.add(emp);
				tree.addEmp(emp.emplID, empArray.size() - 1);
				return 0;
			}

			else {

				empArray.upDate(key, emp);

				return 1;
			}
		}

		else {

			empArray.add(emp);
			tree.addEmp(emp.emplID, empArray.size() - 1);
			return 0;

		}
	}

	public void removeEmp(String empId) {

		empArray.remove(tree.findEmp(empId).getKey());
		tree.deleteEmp(tree.findEmp(empId));

	}

	public String[] list() {
		
		ArrayList<Integer> temp;
		
		temp = tree.display();
		
		String array[] = new String[temp.size()];
		
		for(int i = 0; i < temp.size(); i++) {
			
			array[i] = String.format("%s\t", temp.get(i)) + empArray.getEmp(temp.get(i)).toString();
			
		}
		
		return array;
		
	}

	public String find(String n) {

		tree.findEmp(n);

		String emp = String.format("%s\t", tree.findEmp(n).getKey())
				+ empArray.getEmp(tree.findEmp(n).getKey()).toString();

		return emp;

	}

	public ArrayList<String> siteSearch(String siteCompare) {

		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < empArray.size(); i++) {

			if (empArray.getEmp(i).site.compareToIgnoreCase(siteCompare) == 0) {

				if (empArray.returnflag(i) == false) {

					String number = Integer.toString(i);
					number += "\t";
					String tempS = String.format("%S", number + empArray.getEmp(i).toString());
					temp.add(tempS);

				}
			}
		}

		return temp;
	}

	public ArrayList<String> positionSearch(String positionCompare) {

		ArrayList<String> temp = new ArrayList<String>();

		for (int i = 0; i < empArray.size(); i++) {

			if (empArray.getEmp(i).position.compareToIgnoreCase(positionCompare) == 0) {

				if (empArray.returnflag(i) == false) {

					String number = Integer.toString(i);
					number += "\t";
					String tempS = String.format("%s", number + empArray.getEmp(i).toString());
					temp.add(tempS);

				}
			}
		}

		return temp;
	}

	public boolean writeEmp(FileIO fileObject) {

		FileIO tempFile = fileObject;

		String listArray[];

		listArray = list();

		String tempArray[] = new String[listArray.length + 4];

		String divider, header;

		divider = "---------------------------------------------------------------------------\n";

		header = String.format("%s\t%-15s%-15s%-15s%-15s%-15s\n", "Record#", "EmployeeID", "Last Name", "First Name",
				"Position", "Site");

		tempArray[0] = divider;
		tempArray[1] = header;
		tempArray[2] = divider;

		for (int i = 0; i < listArray.length; i++) {

			tempArray[i + 3] = listArray[i];

		}

		tempArray[tempArray.length - 1] = divider;

		return tempFile.write(tempArray);

	}
}
