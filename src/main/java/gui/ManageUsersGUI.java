package gui;

import fileio.FileOperations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManageUsersGUI extends Application{
	
	Map<String, User> userMap = new HashMap<String, User>();
	User selectedUser;
	
	public ManageUsersGUI() {
		User user1 = new User("lc", "Senior", "Lawrence Cavedon", "lawrence.cavedon@rmit.edu.au");
		 User user2 = new User("vm", "Adult", "Xiang Li", "vuhuy.mai@rmit.edu.au");
		 userMap.put("lc", user1);
		 userMap.put("vm", user2);
	}
	
	public ManageUsersGUI(Map<String, User> userMap) {
		this.userMap = userMap;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Label label = new Label("Manage Users");
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
		
		Label userIdLabel = new Label("Select user: ");
		
		 
		 
		 List<String> userIds = new ArrayList<String>();
		 userIds.add("--Select--");
		 for(Map.Entry<String, User> entry: userMap.entrySet()) {
			 userIds.add(entry.getKey());
		 }
		
		
		
		
		TableView tableView = new TableView();
		tableView.setEditable(true);
		tableView.setMaxHeight(100);
		
		TableColumn idCol = new TableColumn("User Id");
		idCol.setMinWidth(100);
		idCol.setCellValueFactory(
                new PropertyValueFactory<>("id"));
		
		TableColumn nameCol = new TableColumn("Name");
		nameCol.setMinWidth(100);
		nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name"));
		
		TableColumn emailCol = new TableColumn("Email");
		emailCol.setMinWidth(100);
		emailCol.setCellValueFactory(
                new PropertyValueFactory<>("email"));
		
		TableColumn typeCol = new TableColumn("Type");
		typeCol.setMinWidth(100);
		typeCol.setCellValueFactory(
                new PropertyValueFactory<>("type"));
		
		tableView.getColumns().addAll(idCol,nameCol,emailCol,typeCol);
		
		TextField timeField = new TextField();
		
		TextField creditField = new TextField();
		
		ComboBox<String> userCombo = new ComboBox(FXCollections.observableArrayList(userIds));
		userCombo.setMaxWidth(150);
		userCombo.getSelectionModel().selectFirst();
		
		userCombo.setOnAction(e->{
			String userId = userCombo.getValue();
			selectedUser = userMap.get(userId);
			System.out.println(selectedUser.toString());
			ObservableList<User> data = FXCollections.observableArrayList(selectedUser);
			tableView.setItems(data);
			
			
			creditField.setText(selectedUser.getCredit()+"");

		});
		
		Button btn = new Button("Add New User");
		Button btnJourney = new Button("By a journey");
		btnJourney.setOnAction(e -> {
			//popup.show(primaryStage);
			try {
				new MyTiSystemGUI(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		btn.setOnAction(e -> {
			//popup.show(primaryStage);
			try {
				new AddNewUser(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});
		
		Label creditLabel = new Label("Your Credits");
		
		Label respLabel = new Label();
		
		Button addBtn = new Button("Save Credits");
		addBtn.setOnAction(e->{
			String userid = userCombo.getValue();
			String credit = creditField.getText();
			if(userid==null || userid.length()==0 || userid.equalsIgnoreCase("--select--")) {
				respLabel.setText("Please select user id first");
				respLabel.setTextFill(Color.RED);
				return;
			}
			try {
				double value = Double.parseDouble(credit);
				if(value==0.0) {
					respLabel.setText("Please Enter your credit");
					respLabel.setTextFill(Color.RED);
					return;
				}
				selectedUser.rechargeTravelPass(value);
				this.userMap.put(userid, selectedUser);
				respLabel.setText("Credit value updated");
				respLabel.setTextFill(Color.GREEN);
			}catch(Exception ex) {
				respLabel.setText("Please enter valid credits(in numbers)");
				respLabel.setTextFill(Color.RED);
				return;
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
		pane.add(userCombo, 1, 0);
		pane.add(btn, 2, 0);
		pane.add(btnJourney, 3, 0);
		//pane.setColumnSpan(userCombo, 2);
		
		pane.add(tableView, 0, 1);
		pane.setColumnSpan(tableView, 4);
		
		pane.add(creditLabel, 0, 2);
		pane.add(creditField, 1, 2);
		pane.add(addBtn, 2, 2);

		pane.add(respLabel, 1, 3);
		pane.setColumnSpan(respLabel, 3);
	
		
		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.setHgap(10);
		pane.setVgap(10);
		
		borderPane.setCenter(pane);
		//borderPane.setAlignment(pane,Pos.CENTER);
		
		borderPane.setBottom(footer);
		
		borderPane.setAlignment(footer, Pos.CENTER);
		
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
