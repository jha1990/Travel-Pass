package gui;

import fileio.FileOperations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.HashMap;
import java.util.Map;

public class AddNewUser extends Application{
	
	Map<String, User> userMap = new HashMap<String, User>();
	
	public AddNewUser() {
		// TODO Auto-generated constructor stub
	}
	
	public AddNewUser(Map<String, User> userMap) {
		// TODO Auto-generated constructor stub
		this.userMap = userMap;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label label = new Label("Add new user");
		label.setFont(Font.font(16));
		Dialog<String> dialog = new Dialog<>();
		dialog.setContentText("Hi");
		dialog.setTitle("Test");
		ButtonType okBtn = new ButtonType("OK");
		dialog.getDialogPane().getButtonTypes().add(okBtn);
		BorderPane borderPane = new BorderPane();   
		borderPane.setTop(label);
		borderPane.setAlignment(label, Pos.CENTER);
		borderPane.setPadding(new Insets(10));
		
		Label userIdLabel = new Label("User Id: ");
		Label nameLabel = new Label("Name: ");
		Label emailLabel = new Label("EmailId: ");
		Label type = new Label("Type: ");
		
		Label respLabel = new Label();
		
		TextField idField = new TextField();
		TextField nameField = new TextField();
		TextField emailField = new TextField();
		TextField typeField = new TextField();
		
		String userTypes[] = {"--Select--","Adult", "Junior", "Senior"};
		ComboBox<String> userTypeCombo = new ComboBox(FXCollections.observableArrayList(userTypes));
		userTypeCombo.setMaxWidth(150);
		userTypeCombo.getSelectionModel().selectFirst();
		
		Button save = new Button("Save");
		save.setOnAction(e->{
			String id = idField.getText();
			String name = nameField.getText();
			String email = emailField.getText();
			String typeV = userTypeCombo.getValue();

			if(typeV.equalsIgnoreCase("--Select--")){
				dialog.setContentText("Please select type");
				dialog.show();
				return;
			}
			
			User user = new User(id, typeV, name, email);
			
			this.userMap.put(id, user);
			
			respLabel.setText(id + " is successfully added");
			 
			idField.setText("");
			nameField.setText("");
			emailField.setText("");
			typeField.setText("");
			try {
				new ManageUsersGUI(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e->{
			try {
				new ManageUsersGUI(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
				
		
		
		HBox footer = new HBox(10);
		
		Button saveBtn = new Button("SAVE");
		saveBtn.setOnAction(e->{
			FileOperations.writeOperation(userMap);
			dialog.setContentText("Saved Successfully");
			dialog.show();
		});
		Button quitBtn = new Button("QUIT");
		quitBtn.setOnAction(e->{
			System.exit(0);
		});
		
		footer.getChildren().addAll(saveBtn,quitBtn);
		
		GridPane pane = new GridPane();
		
		pane.add(userIdLabel, 0, 0);
		pane.add(idField, 1, 0);
		pane.setColumnSpan(idField, 2);
		
		pane.add(nameLabel, 0, 1);
		pane.add(nameField, 1, 1);
		pane.setColumnSpan(nameField, 2);
		
		pane.add(emailLabel,0, 2);
		pane.add(emailField,1, 2);
		pane.setColumnSpan(emailField, 2);
		
		pane.add(type,0, 3);
		pane.add(userTypeCombo,1, 3);
		pane.setColumnSpan(userTypeCombo, 2);
		
		pane.add(save, 1, 4);
		pane.add(cancel, 2, 4);
		
		pane.add(respLabel, 0, 5);
		pane.setColumnSpan(respLabel, 3);
		
	
		
		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		
		
		
		borderPane.setCenter(pane);
		//borderPane.setAlignment(pane,Pos.CENTER);
		
		borderPane.setBottom(footer);
		
		
		
		Scene scene = new Scene(borderPane,600,400); 
		//scene.setFill(Color.web("#81c483"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("MyTiSystem");
		primaryStage.show();
	}
	
	/*public static void main(String[] args) {
		launch(args);
	}*/

}
