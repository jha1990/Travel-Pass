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
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserJourneys extends Application{
	
	Map<String, User> userMap = new HashMap<String, User>();
	
	public UserJourneys() {
		// TODO Auto-generated constructor stub
	}
	
	public UserJourneys(Map<String, User> userMap) {
		// TODO Auto-generated constructor stub
		this.userMap = userMap;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		Label label = new Label("User Journeys");
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
		
		List<String> userIds = new ArrayList<String>();
		 userIds.add("--Select--");
		 for(Map.Entry<String, User> entry: userMap.entrySet()) {
			 userIds.add(entry.getKey());
		 }
		 
		 Label userLabel = new Label("Select User:");
		
		ComboBox<String> userCombo = new ComboBox(FXCollections.observableArrayList(userIds));
		userCombo.setMaxWidth(150);
		userCombo.getSelectionModel().selectFirst();


				
		
		TableView tableView = new TableView();
		tableView.setEditable(true);
		tableView.setMaxHeight(200);
		
		TableColumn dayCol = new TableColumn("Day");
		dayCol.setMinWidth(100);
		dayCol.setCellValueFactory(
                new PropertyValueFactory<>("day"));
		
		TableColumn dCol = new TableColumn("Departure");
		dCol.setMinWidth(100);
		dCol.setCellValueFactory(
                new PropertyValueFactory<>("stStationStr"));
		
		TableColumn aCol = new TableColumn("Arrival");
		aCol.setMinWidth(100);
		aCol.setCellValueFactory(
                new PropertyValueFactory<>("edStationStr"));
		
		TableColumn dtCol = new TableColumn("Deaparture Time");
		dtCol.setMinWidth(100);
		dtCol.setCellValueFactory(
                new PropertyValueFactory<>("departureTime"));
		
		TableColumn atCol = new TableColumn("Arrival Time");
		atCol.setMinWidth(100);
		atCol.setCellValueFactory(
                new PropertyValueFactory<>("arrivalTime"));
		
		TableColumn costCol = new TableColumn("Cost");
		costCol.setMinWidth(100);
		costCol.setCellValueFactory(
                new PropertyValueFactory<>("cost"));
		
		tableView.getColumns().addAll(dayCol,dCol,aCol,dtCol,atCol,costCol);

		userCombo.setOnAction(e->{
			String userId = userCombo.getValue();
			User usr = this.userMap.get(userId);
			List<Journey> journeis = usr.getPass().getJourneyList();
			ObservableList<Journey> data = FXCollections.observableArrayList(journeis);
			tableView.setItems(data);
		});

		Button journeyBtn = new Button("By a Journey");
		journeyBtn.setOnAction(e->{
			try {
				new MyTiSystemGUI(this.userMap).start(primaryStage);
			} catch (Exception ex) {
				throw new RuntimeException(ex);
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
		
		pane.add(userLabel, 0, 0);
		pane.add(userCombo, 1, 0);
		
		
		pane.add(tableView, 0, 1);
		pane.setColumnSpan(tableView, 2);

		pane.add(journeyBtn,1,2);
		
	
		
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
	

}
