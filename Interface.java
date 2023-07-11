package project4;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class Interface extends HBox {

	private VBox menuBox;
	private HBox displayBox;
	private Button listButton, findButton, siteButton, positionButton, insertButton, mergeButton, deleteButton,
			exitButton, quitButton, selectFile;

	private TextArea text;
	private File file;
	private FileIO fileObject = new FileIO();
	private EMPHandler handler = new EMPHandler();

	public Interface(Stage prime) {
		
		super();
		
		selectFile = new Button("Select File");
		selectFile.setPrefSize(100,  50);
		
		selectFile.setOnAction(event -> {
			
			getFile(prime);
			fileObject.readFile(file, handler);
			selectFile.setDisable(true);
			
		});
		
		listButton = new Button("List Records");
		listButton.setPrefSize(100,  50);
		
		listButton.setOnAction(event -> {
			
			show(handler.list());
			
		});
		
		findButton = new Button("Find Emp");
		findButton.setPrefSize(100,  50);
		
		findButton.setOnAction(event -> {
			
			String id = JOptionPane.showInputDialog("Input Employment ID").toUpperCase();
			
			if(handler.tree.isElement(id) == true) {
				
				String array[] = new String[1];
				
				array[0] = handler.find(id);
				
				show(array);
				
			}
			
			else {
				
				JOptionPane.showMessageDialog(null, "No Employment ID Found!", "Search Status", JOptionPane.OK_OPTION);
				
			}
		});
		
		siteButton = new Button("Site Search");
		siteButton.setPrefSize(100,  50);
		
		siteButton.setOnAction(event -> {
			
			String site = JOptionPane.showInputDialog("Enter Site").trim();
			
			ArrayList<String> temp;
			
			temp = handler.siteSearch(site);
			
			if(temp.size() == 0) {
				
				JOptionPane.showMessageDialog(null, "No Employees at that site!", "Search Status", JOptionPane.OK_OPTION);
				
			}
			
			showSearched(temp);
			
		});
		
		positionButton = new Button("Position Search");
		positionButton.setPrefSize(100,  50);
		
		positionButton.setOnAction(event -> {
			
			String position = JOptionPane.showInputDialog("Enter Position").trim();
			
			ArrayList <String> temp;
			
			temp = handler.positionSearch(position);
			
			if(temp.size() == 0) {
				
				JOptionPane.showMessageDialog(null, "NO employee with the position!", "Searcg Status", JOptionPane.OK_OPTION);
				
			}
			
			showSearched(temp);
			
		});
		
		insertButton = new Button("Insert Emp");
		insertButton.setPrefSize(100,  50);

		insertButton.setOnAction(event -> {
			
			String array[];
			String temp, last, first, position, site, idGen;
			
			temp = JOptionPane.showInputDialog(null, "LastName, First Name, Position, Site", "Add Employee", JOptionPane.OK_OPTION);
			
			array = temp.split(",");
			
			last = array[0];
			first = array[1];
			position = array[2];
			site = array[3];
			
			idGen = "";
			
			idGen += site.substring(0, 1).toUpperCase();
			idGen += "-";
			idGen += last.substring(0, 3).toUpperCase();
			idGen += first.substring(0, 1).toUpperCase();
			idGen += "-";
			idGen += "01";
			
			EmpRecord empObj = new EmpRecord(idGen, last, first, position, site);
			
			handler.addEmployee(empObj);
			
		});
		
		mergeButton = new Button("Merge Records");
		mergeButton.setPrefSize(100,  50);
		
		mergeButton.setOnAction(event ->{
			
			text.clear();
			getFile(prime);
			fileObject.readFile(file, handler);
			show(handler.list());
			
		});
		
		deleteButton = new Button("Delete Emp");
		deleteButton.setPrefSize(100,  50);
		
		deleteButton.setOnAction(event -> {
			
			String temp = JOptionPane.showInputDialog(null, "Input Employee ID", "Delete Employee", JOptionPane.OK_OPTION).toUpperCase();
			
			if(handler.tree.isElement(temp) == true) {
				
				handler.empArray.remove(handler.tree.findEmp(temp).getKey());
				handler.removeEmp(temp);
				JOptionPane.showMessageDialog(null, "Employee " +temp+ "Deleted!", "Search Status", JOptionPane.OK_OPTION);
				
			}
			
			else {
				
				JOptionPane.showMessageDialog(null, "No Employees with that ID!", "Search Status", JOptionPane.OK_OPTION);
				
			}
			
			show(handler.list());
			
		});
		
		exitButton = new Button("Exit and Save");
		exitButton.setPrefSize(100,  50);
		
		exitButton.setOnAction(event -> {
			
			handler.writeEmp(fileObject);
			
			System.exit(0);
			
		});
		
		quitButton = new Button("Quit");
		quitButton.setPrefSize(100,  50);
		
		quitButton.setOnAction(event -> {
			
			System.exit(0);
			
		});
		
		menuBox = new VBox(10, selectFile, listButton, findButton, siteButton, positionButton, insertButton, mergeButton, 
					deleteButton, exitButton, quitButton);
		
		menuBox.setPadding(new Insets(10, 0, 0, 30));
		menuBox.setFillWidth(true);
		menuBox.setPrefSize(200,  400);
		
		text = new TextArea();
		
		text.setEditable(false);
		text.setPrefSize(600, 400);
		text.autosize();
		text.setStyle("-fx-font-family: 'monospace';");
		
		displayBox = new HBox(5, text, menuBox);
		displayBox.setPadding(new Insets(20));
		displayBox.setPrefSize(800, 600);
		
	}

	public HBox getGUIdisplay() {

		return displayBox;

	}

	public void getFile(Stage p) {

		FileChooser fileSelect = new FileChooser();

		file = fileSelect.showOpenDialog(p);

	}

	public void show(String[] arrayPassed){

		text.clear();

		String array[] = arrayPassed;

		String header;
		String divider;

		divider = "---------------------------------------------------------------------------\n";

		text.appendText(divider);

		header = String.format("%s\t%-15s%-15s%-15s%-15s%-15s\n", "Record#", "EmployeeID", "Last Name", "First Name",
				"Position", "Site");

		text.appendText(header);

		text.appendText(divider);

		for (int i = 0; i < array.length; i++) {

			text.appendText(array[i]);

		}

		text.appendText(divider);

	}

	public void showSearched(ArrayList<String> array) {

		text.clear();

		String header;
		String divider;

		divider = "---------------------------------------------------------------------------\n";

		text.appendText(divider);

		header = String.format("%s\t%-15s%-15s%-15s%-15s%-15s\n", "Record#", "EmployeeID", "Last Name", "First Name",
				"Position", "Site");

		text.appendText(header);

		text.appendText(divider);

		for (int i = 0; i < array.size(); i++) {

			text.appendText(array.get(i));

		}

		text.appendText(divider);

	}
}
