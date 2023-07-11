package project4;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Project4 extends Application{
	
	private Interface empRecords;
	private Scene scene;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		launch(args);
		
	}
	
	@Override
	
	public void start(Stage primaryStage)throws Exception{
		
		empRecords = new Interface(primaryStage);
		
		scene = new Scene(empRecords.getGUIdisplay());
		
		primaryStage.setScene(scene);
		primaryStage.sizeToScene();
		primaryStage.setTitle("Emp Records");
		primaryStage.setResizable(true);
		primaryStage.show();
		
	}
}
