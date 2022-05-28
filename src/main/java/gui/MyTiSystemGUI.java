package gui;

import fileio.FileOperations;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MyTiSystemGUI extends Application {

	static Map<String, User> userMap = new HashMap<String, User>();


	static {
		Map<String, User> mapObj = FileOperations.readOperation();
		if (mapObj == null) {
			System.out.println("Hi");
			User user1 = new User("lc", "Senior", "Lawrence Cavedon", "lawrence.cavedon@rmit.edu.au");
			User user2 = new User("vm", "Adult", "Xiang Li", "vuhuy.mai@rmit.edu.au");
			userMap.put("lc", user1);
			userMap.put("vm", user2);
		} else {
			userMap = FileOperations.readOperation();
		}
	}

	public MyTiSystemGUI() {
		// TODO Auto-generated constructor stub


	}

	public MyTiSystemGUI(Map<String, User> userMap) {
		// TODO Auto-generated constructor stub
		this.userMap = userMap;
	}

	public static void main(String[] args) {
		//System.out.println("Hi");
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {

		Label header = new Label("Welcome to My Ticket System");
		header.setFont(Font.font(16));

		Label label = new Label("Buy a journey");
		label.setFont(Font.font(16));

		Label labelEMpty = new Label();
		Label labelEMpty1 = new Label();

		Dialog<String> dialog = new Dialog<>();
		dialog.setContentText("Hi");
		dialog.setTitle("Test");
		ButtonType okBtn = new ButtonType("OK");
		dialog.getDialogPane().getButtonTypes().add(okBtn);

		ImageView imageView = new ImageView();
		imageView.setImage(new Image(new FileInputStream("myticketlogo.png")));
		imageView.setFitWidth(50);
		imageView.setFitHeight(50);

		Button mngUserBtn = new Button("Manage Users");
		//mngUserBtn.setFont(Font.font(10));
		mngUserBtn.setOnAction(e -> {
			try {
				new ManageUsersGUI(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});


		GridPane gridPane = new GridPane();
		gridPane.add(imageView, 0, 0);
		gridPane.add(header,1,0);
		gridPane.add(labelEMpty1, 0, 1);
		gridPane.add(label, 0, 2);
		gridPane.setVgap(10);
		gridPane.setHgap(10);
		//gridPane.setColumnSpan(label, 2);


		gridPane.add(labelEMpty, 0, 3);
		gridPane.setAlignment(Pos.CENTER);

		BorderPane borderPane = new BorderPane();
		borderPane.setTop(gridPane);
		borderPane.setAlignment(gridPane, Pos.CENTER);
		borderPane.setPadding(new Insets(10));

		Label userIdLabel = new Label("Select user: ");

		Label departureLabel = new Label("Select Departure: ");
		Label arrivalLabel = new Label("Select Arrival: ");

		Label dateTimeLabel = new Label("Select Day and Time: ");
		dateTimeLabel.setWrapText(true);

		List<String> userIds = new ArrayList<String>();
		userIds.add("--Select--");
		for (Map.Entry<String, User> entry : userMap.entrySet()) {
			userIds.add(entry.getKey());
		}
		String station[] = {"--Select--", "Central", "Richmond", "LilyDale", "Epping", "Flagstaff"};
		String days[] = {"--Select--", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};

		ComboBox<String> userCombo = new ComboBox<String>(FXCollections.observableArrayList(userIds));
		userCombo.getSelectionModel().selectFirst();
		userCombo.setMaxWidth(150);

		ComboBox<String> departureCombo = new ComboBox<String>(FXCollections.observableArrayList(station));
		departureCombo.getSelectionModel().selectFirst();
		departureCombo.setMaxWidth(150);

		ComboBox<String> arrivalCombo = new ComboBox<String>(FXCollections.observableArrayList(station));
		arrivalCombo.getSelectionModel().selectFirst();
		arrivalCombo.setMaxWidth(150);

		ComboBox<String> dayCombo = new ComboBox<String>(FXCollections.observableArrayList(days));
		dayCombo.getSelectionModel().selectFirst();
		dayCombo.setMaxWidth(150);

		HBox hBox = new HBox(10);
		HBox hBoxButtons = new HBox(10);

		TextField timeField = new TextField();
		timeField.setPromptText("Start Time");
		timeField.setMaxWidth(100);

		TextField timeEndField = new TextField();
		timeEndField.setPromptText("End Time");
		timeEndField.setMaxWidth(100);

		hBox.getChildren().addAll(timeField, timeEndField);

		TextArea txtArea = new TextArea();
		txtArea.setMaxHeight(100);
		txtArea.setPromptText("Comments");

		Label respLabel = new Label();

		Button purchase = new Button("Purchase");
		purchase.setOnAction(e -> {
			String userId = userCombo.getValue();
			String departure = departureCombo.getValue();
			String arrivalVal = arrivalCombo.getValue();
			String day = dayCombo.getValue();
			String sTime = timeField.getText();
			String eTime = timeEndField.getText();
			String comment = txtArea.getText();

			if (userId == null || userId.length() == 0 || "--Select--".equalsIgnoreCase(userId)) {
				respLabel.setText("Select User id ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Select User id");
				dialog.show();
				return;
			}
			if (departure == null || departure.length() == 0 || "--Select--".equalsIgnoreCase(departure)) {
				respLabel.setText("Select Departure ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Select Departure ");
				dialog.show();
				return;
			}
			if (arrivalVal == null || arrivalVal.length() == 0 || "--Select--".equalsIgnoreCase(arrivalVal)) {
				respLabel.setText("Select Arrival ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Select Arrival ");
				dialog.show();
				return;
			}

			if (day == null || day.length() == 0 || "--Select--".equalsIgnoreCase(day)) {
				respLabel.setText("Select Day ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Select Day ");
				dialog.show();
				return;
			}
			if (sTime == null || sTime.length() == 0) {
				respLabel.setText("Enter start time ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Enter start time");
				dialog.show();
				return;
			}
			if (eTime == null || eTime.length() == 0) {
				respLabel.setText("Enter end time ");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Enter end time");
				dialog.show();
				return;
			}

			int stimeInt = Integer.parseInt(sTime);
			int etimeInt = Integer.parseInt(eTime);
			Station station1 = getSelectedStation(departure);
			Station station2 = getSelectedStation(arrivalVal);
			/*Journey journey = new Journey();
			journey.setDay(day);
			journey.setDepartureTime(stimeInt);
			journey.setArrivalTime(etimeInt);
			journey.setStartStation(station1);
			journey.setEndStation(station2);*/

			User usr = this.userMap.get(userId);
			if (usr.getCredit() == 0) {
				respLabel.setText("Please add credit first. Thanks !");
				respLabel.setTextFill(Color.RED);
				dialog.setContentText("Please add credit first. Thanks !");
				dialog.show();
				return;
			}

			//Here is the save part.
			if (usr.getPassType() == null) {
				if (Math.abs(stimeInt - etimeInt) <= 200) {

					if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("sunday")) {

						System.out.println("Free Pass");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0);
						if (journey.getCost() <= usr.getCredit()) {
							System.out.println("Valid Till: " + (stimeInt + 200));
							usr.addJourneyToUser(journey, "2 Hour");
							System.out.println(userId + " remaining balance: $" + usr.getCredit());
							txtArea.setText("Valid Till: " + (stimeInt + 200) + "\n" + userId + " remaining balance: $" + usr.getCredit());

						} else {
							System.out.println("Insufficient balance");
							dialog.setContentText("Insufficient balance");
							dialog.show();
						}

					} else if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("Friday")) {
						System.out.println("Concession Pass");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0.5 * getPassPrice("2 hour", getPrices()));
						if (journey.getCost() <= usr.getCredit()) {
							usr.addJourneyToUser(journey, "2 Hour");
							System.out.println(userId + " remaining balance: $" + usr.getCredit());
							txtArea.setText(userId + " remaining balance: $" + usr.getCredit());
						} else {
							System.out.println("Insufficient balance");
							dialog.setContentText("Insufficient balance");
							dialog.show();
						}

					} else {
						System.out.println("2 hour pass purchased");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, getPassPrice("2 hour", getPrices()));
						if (journey.getCost() <= usr.getCredit()) {
							System.out.println("Valid Till: " + (stimeInt + 200));
							usr.addJourneyToUser(journey, "2 Hour");
							System.out.println(userId + " remaining balance: $" + usr.getCredit());
							txtArea.setText("Valid Till: " + (stimeInt + 200) + " \n " + userId + " remaining balance: $" + usr.getCredit());
						} else {
							System.out.println("Insufficient balance");
							dialog.setContentText("Insufficient balance");
							dialog.show();
						}


					}

				} else {

					if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("sunday")) {
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0);
						System.out.println("Free Pass");
						if (journey.getCost() <= usr.getCredit()) {
							usr.addJourneyToUser(journey, "All Day");
							System.out.println(userId + " remaining balance: $" + usr.getCredit());
							txtArea.setText("Free Pass " + " \n " + userId + " remaining balance: $" + usr.getCredit());
						} else {
							System.out.println("Insufficient balance");
							dialog.setContentText("Insufficient balance");
							dialog.show();
						}

					} else if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("Friday")) {
						System.out.println("Concession Pass");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0.5 * getPassPrice("All day", getPrices()));
						if (journey.getCost() <= usr.getCredit()) {
							usr.addJourneyToUser(journey, "All Day");
							System.out.println(userId + " remaining balance: $" + usr.getCredit());
							txtArea.setText("Concession Pass" + " \n " + userId + " remaining balance: $" + usr.getCredit());
						} else {
							System.out.println("Insufficient balance");
							dialog.setContentText("Insufficient balance");
							dialog.show();
						}

					} else {
						System.out.println("All day pass purchased");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, getPassPrice("All day", getPrices()));
						usr.addJourneyToUser(journey, "All Day");
						System.out.println(userId + " remaining balance: $" + usr.getCredit());
						txtArea.setText("All day pass purchased" + " \n " + userId + " remaining balance: $" + usr.getCredit());
					}

				}
			} else {
				if (usr.getPassType().equalsIgnoreCase("All Day")) {
					Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0);
					System.out.print("All Day pass  is valid ");
					usr.addJourneyToUser(journey, "All Day");
					System.out.println(userId + " remaining balance: $" + usr.getCredit());
					txtArea.setText("All Day pass  is valid " + " \n " + userId + " remaining balance: $" + usr.getCredit());
				} else if (usr.getPassType().equalsIgnoreCase("2 Hour")) {
					if (usr.checkPassifAvaiable(stimeInt)) {
						System.out.println("Current 2 hour pass is still valid");
						Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0);
						usr.addJourneyToUser(journey, "2 hour");
						System.out.println(userId + " remaining balance: $" + usr.getCredit());
						txtArea.setText("Current 2 hour pass is still valid" + " \n " + userId + " remaining balance: $" + usr.getCredit());
					} else {

						if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("sunday")) {
							System.out.println("Free Pass");
							Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0);
							if (journey.getCost() <= usr.getCredit()) {
								usr.addJourneyToUser(journey, "All Day");
								System.out.println(userId + " remaining balance: $" + usr.getCredit());
								txtArea.setText("Free Pass" + " \n " + userId + " remaining balance: $" + usr.getCredit());
							} else {
								System.out.println("Insufficient balance");
							}

						} else if ((usr.getType().equalsIgnoreCase("Senior") || usr.getType().equalsIgnoreCase("Adult")) && day.equalsIgnoreCase("Friday")) {
							System.out.println("Concession Pass");
							Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, 0.5 * getPassPrice("All day", getPrices()));
							if (journey.getCost() <= usr.getCredit()) {
								usr.addJourneyToUser(journey, "All Day");
								System.out.println(userId + " remaining balance: $" + usr.getCredit());
								txtArea.setText("Concession Pass" + " \n " + userId + " remaining balance: $" + usr.getCredit());
							} else {
								System.out.println("Insufficient balance");
							}

						} else {
							System.out.println("2 Hour Pass Upgraded To All Day Pass for " + userId + " for $" + getPassPrice("All day", getPrices()) + " Credit");
							Journey journey = new Journey(station1, station2, day, stimeInt, etimeInt, getPassPrice("All day", getPrices()));
							if (journey.getCost() <= usr.getCredit()) {
								usr.addJourneyToUser(journey, "All Day");
								System.out.println(userId + " remaining balance: $" + usr.getCredit());
								txtArea.setText("2 Hour Pass Upgraded To All Day Pass for " + userId + " for $" + getPassPrice("All day", getPrices()) + " Credit" + " \n " + userId + " remaining balance: $" + usr.getCredit());
							} else {
								System.out.println("Insufficient balance");
							}

						}


					}
				}
			}


			respLabel.setText("Successfully purchased");
			respLabel.setTextFill(Color.GREEN);

			userCombo.setValue("--Select--");
			departureCombo.setValue("--Select--");
			arrivalCombo.setValue("--Select--");
			dayCombo.setValue("--Select--");
			timeField.setText("");
			timeEndField.setText("");
			//txtArea.setText("");
		});
		Button cancel = new Button("Cancel");
		cancel.setOnAction(e -> {
			System.exit(0);
		});
		Button journeys = new Button("User Journeys");
		journeys.setOnAction(e -> {
			try {
				new UserJourneys(this.userMap).start(primaryStage);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		});

		hBoxButtons.getChildren().addAll(purchase, cancel, mngUserBtn, journeys);


		HBox footer = new HBox(10);

		Button saveBtn = new Button("SAVE");
		saveBtn.setOnAction(e -> {
			FileOperations.writeOperation(userMap);
			dialog.setContentText("Saved successfully");
			dialog.show();
		});
		Button quitBtn = new Button("QUIT");
		quitBtn.setOnAction(e -> {
			System.exit(0);
		});

		footer.getChildren().addAll(saveBtn, quitBtn);

		GridPane pane = new GridPane();

		pane.add(userIdLabel, 0, 0);
		pane.add(userCombo, 1, 0);
		pane.setColumnSpan(userCombo, 2);
		pane.setHgrow(userCombo, Priority.ALWAYS);

		pane.add(departureLabel, 0, 1);
		pane.add(departureCombo, 1, 1);
		pane.setColumnSpan(departureCombo, 2);
		pane.setHgrow(departureCombo, Priority.ALWAYS);

		pane.add(arrivalLabel, 0, 2);
		pane.add(arrivalCombo, 1, 2);
		pane.setColumnSpan(arrivalCombo, 2);
		pane.setHgrow(arrivalCombo, Priority.ALWAYS);


		pane.add(dateTimeLabel, 0, 3);
		pane.add(dayCombo, 1, 3);
		pane.add(hBox, 2, 3);
		//pane.setColumnSpan(hBox, 2);

		pane.add(txtArea, 0, 4);
		pane.setColumnSpan(txtArea, 3);

		pane.add(hBoxButtons, 1, 5);
		/*
		 * pane.add(cancel, 2, 4); pane.add(mngUserBtn, 3, 4);
		 */
		pane.setHgrow(hBoxButtons, Priority.ALWAYS);
		pane.setColumnSpan(hBoxButtons, 2);

		pane.add(respLabel, 1, 6);
		pane.setColumnSpan(respLabel, 2);

		pane.setAlignment(Pos.BASELINE_CENTER);
		pane.setHgap(10);
		pane.setVgap(10);


		borderPane.setCenter(pane);
		borderPane.setAlignment(pane, Pos.CENTER);

		borderPane.setBottom(footer);

		borderPane.setAlignment(footer, Pos.CENTER);

		Scene scene = new Scene(borderPane, 600, 500);
		//scene.setFill(Color.web("#81c483"));
		primaryStage.setScene(scene);
		primaryStage.setTitle("MyTiSystem");
		primaryStage.show();

	}

	/**
	 * @return the array list of station
	 */
	private static ArrayList<Station> getStation() {
		ArrayList<Station> stationList = new ArrayList<>();
		// creating station
		Station firstStation = new Station("Central", 1);
		Station secondStation = new Station("Richmond", 1);
		Station thirdStation = new Station("LilyDale", 2);
		Station fourthStation = new Station("Epping", 2);
		Station fifthStation = new Station("Flagstaff", 1);
		stationList.add(firstStation);
		stationList.add(secondStation);
		stationList.add(thirdStation);
		stationList.add(fourthStation);
		stationList.add(fifthStation);
		return stationList;
	}

	private static Station getSelectedStation(String selectedStation) {

		List<Station> li = getStation();
		for (Station st : li) {
			if (selectedStation.equalsIgnoreCase(st.getName())) {
				return st;
			}
		}

		return null;
	}

	/**
	 * @return array list of pass type and price
	 */
	private static ArrayList<Price> getPrices() {
		ArrayList<Price> priceList = new ArrayList<>();
		Price hourPrice = new Price("2 hsour", 1.25);
		Price dayPrice = new Price("All Day", 1.20);
		priceList.add(hourPrice);
		priceList.add(dayPrice);
		return priceList;
	}

	/**
	 * @param passType the type of pass
	 * @return the price of pass
	 */
	private static double getPassPrice(String passType, ArrayList<Price> priceList) {
		for (int i = 0; i < priceList.size(); i++) {
			if (priceList.get(i).getType().equalsIgnoreCase(passType)) {
				return priceList.get(i).getCost();
			}
		}
		return 0;
	}
}
