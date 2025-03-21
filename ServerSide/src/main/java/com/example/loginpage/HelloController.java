package com.example.loginpage;
//import com.example.game_basic.HelloApplication;

import javafx.scene.control.ScrollBar;
import javafx.animation.RotateTransition;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;

import javafx.beans.property.SimpleStringProperty;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import javafx.scene.canvas.Canvas;
import javafx.scene.shape.Polygon;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

import java.awt.*;
import java.sql.Date;
import java.time.LocalDate;
import java.util.*;


import java.io.IOException;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class HelloController {
    public String USERNAME;
    @FXML
    public TextField username1 = new TextField();
    @FXML
    public PasswordField password1 = new PasswordField();
    @FXML
    public TextField username2 = new TextField();
    @FXML
    public PasswordField password2 = new PasswordField();
    @FXML
    public Text invalidUserText = new Text();
    @FXML
    public Text accLevel = new Text();
    @FXML
    public Text accCoin = new Text();
    @FXML
    public Text teamOvr = new Text();
    @FXML
    public Text teamValue = new Text();
    @FXML
    public Text accName = new Text();
    @FXML
    public Text accXP = new Text();
    @FXML
    public Button marketPlacebtn = new Button();
    @FXML
    private TableView table;
    @FXML
    private TableView table1;
    @FXML
    private TableView table2;
    @FXML
    public TextField playerName = new TextField();
    @FXML
    public TextField minPrice = new TextField();
    @FXML
    public TextField maxPrice = new TextField();
    @FXML
    public TextField minOvr = new TextField();
    @FXML
    public TextField maxOvr = new TextField();
    @FXML
    private RadioButton radioST;
    @FXML
    private RadioButton radioCM;
    @FXML
    private RadioButton radioCB;
    @FXML
    private RadioButton radioGK;
    @FXML
    private ComboBox st1;
    @FXML
    private ComboBox st2;
    @FXML
    private ComboBox st3;
    @FXML
    private ComboBox cm1;
    @FXML
    private ComboBox cm2;
    @FXML
    private ComboBox cm3;
    @FXML
    private ComboBox cb1;
    @FXML
    private ComboBox cb2;
    @FXML
    private ComboBox cb3;
    @FXML
    private ComboBox cb4;
    @FXML
    private ComboBox gk;
    @FXML
    private ImageView teamovr;
    @FXML
    private ImageView rankBadge;
    @FXML
    private LineChart<String, Number> divisionRivalChart;

    @FXML
    private ObservableList<Person> personObservableListTable1 = FXCollections.observableArrayList();
    private ObservableList<Person> personObservableListTable2 = FXCollections.observableArrayList();
    private ObservableList<Person> personObservableListTable3 = FXCollections.observableArrayList();
    private ObservableList<Person> personObservableListTable4 = FXCollections.observableArrayList();
    private ObservableList<Person> personObservableListTablebidding = FXCollections.observableArrayList();
    private ObservableList<Person> personObservableListTableHistory = FXCollections.observableArrayList();

    private ToggleGroup positionGroup;

    private Stage stage;
    private Scene playScene;
    private Scene loadingScene;

    public HelloController() {
        // Initialize the stage here or pass it in constructor.
        stage = new Stage();
    }
    public void initialize() {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) screenSize.getWidth();
        int height = (int) screenSize.getHeight();
        playScene = new Scene(new StackPane(), width, height);

        // Create the Loading Pane
        VBox loadingPane = new VBox(10); // Add spacing between components
        loadingPane.setStyle("-fx-alignment: center;"); // Center the VBox

        // Set Background Image
        BackgroundImage backgroundImage = new BackgroundImage(
                new Image(getClass().getResource("/mainmenu.jpg").toExternalForm()),
                BackgroundRepeat.NO_REPEAT,  // Image repetition settings
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,  // Center the image
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, true) // Auto size
        );
        loadingPane.setBackground(new Background(backgroundImage));

        // Add Loading Text
        Text loadingText = new Text("Searching for a match!!");
        loadingText.setFill(Color.WHITE); // White text for better visibility
        loadingText.setFont(new Font(30));  // Set larger font size (increased from default)

        // Add Progress Indicator
        ProgressIndicator progressIndicator = new ProgressIndicator();
        progressIndicator.getStyleClass().add("progress-indicator");
        progressIndicator.setStyle("-fx-padding: 0 0 10 0;"); // Add bottom padding
        progressIndicator.setMinSize(100, 100);  // Minimum size
        progressIndicator.setMaxSize(100, 100);  // Maximum size
        progressIndicator.setPrefSize(100, 100); // Preferred size



        // Add text and progress indicator in VBox (ensure proper alignment)
        loadingPane.getChildren().addAll(progressIndicator, loadingText);

        // Setup the Loading Scene
        loadingScene = new Scene(loadingPane, width, height);
//        loadingScene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Link to the CSS file

        stage.setResizable(false);
        stage.setMaximized(true);
        stage.initStyle(StageStyle.UTILITY);
    }





    public final DatabaseHandler dbHandler = new DatabaseHandler();

    @FXML
    protected void onRegScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("new-window.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
    }

    @FXML
    protected void onLoginScreen(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("hello-view.fxml"));
        Parent newRoot = fxmlLoader.load();
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
    }

    @FXML
    protected void mainMenuPage(String username, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("mainMenu.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController mainMenuController = fxmlLoader.getController();

        String accLevel = dbHandler.getAccountLevel(username);
        String accCoin = dbHandler.getAccountCoin(username);

        mainMenuController.setUserInfo(username, accLevel, accCoin);

        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
    }

    @FXML
    protected void divisionRivals(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("division-rivals.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController divisionsController = fxmlLoader.getController();
        String username = accName.getText();

        divisionsController.setUserInfo(username, dbHandler.getAccountLevel(username), dbHandler.getAccountCoin(username));
        divisionsController.setDivisionRivalInfo(username);
        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
    }

    @FXML
    protected void rankings(ActionEvent event) throws IOException {
        MusicManager.clickSound();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ranking.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController divisionsController = fxmlLoader.getController();
        String username = accName.getText();

        divisionsController.setUserInfo(username, dbHandler.getAccountLevel(username), dbHandler.getAccountCoin(username));
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
    }
    @FXML
    protected void marketPlace(ActionEvent event) throws IOException {
        MusicManager.clickSound();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController marketPlaceController = fxmlLoader.getController();
        String username = accName.getText();

        marketPlaceController.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);

        // Move table setup inside the controller to ensure table is not null
        marketPlaceController.setupTable(username, 1, null, null, null, null, null, null);
        marketPlaceController.setupListingTable(username);
        marketPlaceController.setupBiddingTable(username);
    }
    @FXML
    protected void marketPlaceNameSearch(String player_name, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController marketPlaceController = fxmlLoader.getController();
        String username = accName.getText();

        marketPlaceController.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);

        // Move table setup inside the controller to ensure table is not null
        marketPlaceController.setupTable(username, 2, player_name, null, null, null, null, null);
    }
    @FXML
    protected void marketPlacePriceSearch(String min, String max, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController marketPlaceController = fxmlLoader.getController();
        String username = accName.getText();

        marketPlaceController.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);

        // Move table setup inside the controller to ensure table is not null
        marketPlaceController.setupTable(username, 3, null, min, max, null, null, null);
    }
    @FXML
    protected void marketPlaceOvrSearch(String min, String max, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController marketPlaceController = fxmlLoader.getController();
        String username = accName.getText();

        marketPlaceController.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);

        // Move table setup inside the controller to ensure table is not null
        marketPlaceController.setupTable(username, 4, null, null, null, min, max, null);
    }
    @FXML
    protected void marketPlacePositionSearch(String position, ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController marketPlaceController = fxmlLoader.getController();
        String username = accName.getText();

        marketPlaceController.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);

        // Move table setup inside the controller to ensure table is not null
        marketPlaceController.setupTable(username, 5, null, null, null, null, null, position);
    }
    @FXML
    protected void teamProfile(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("team-profile.fxml"));
        Parent newRoot = fxmlLoader.load();
        HelloController teamProfileController = fxmlLoader.getController();
        String username = accName.getText();

        teamProfileController.setUserInfo(username, dbHandler.getAccountLevel(username), dbHandler.getAccountCoin(username));

        // Set up the stage
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        currentStage.getScene().setRoot(newRoot);
        teamProfileController.setupTeamTable(username);
        teamProfileController.setupLineUp(username);
    }

    public void setupTable(String username, int x, String player_name, String min, String max, String minOvr, String maxOvr, String pos) {

        TableColumn<Person, String> img = new TableColumn<>("Image");
        TableColumn<Person, String> name = new TableColumn<>("NAME");
        TableColumn<Person, String> position = new TableColumn<>("POSITION");
        TableColumn<Person, String> ovr = new TableColumn<>("OVR");
        TableColumn<Person, String> country = new TableColumn<>("Country");
        TableColumn<Person, String> price = new TableColumn<>("Price");
        TableColumn<Person, String> base = new TableColumn<>("Base");

        // Button column
        TableColumn<Person, Void> actionCol = new TableColumn<>("Action");

        table.setSelectionModel(null);

        // Add columns to the TableView
        table.getColumns().addAll(img, name, position, ovr, country, price, base, actionCol);

        // Map TableColumns to Person properties using PropertyValueFactory

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        ovr.setCellValueFactory(new PropertyValueFactory<>("ovr"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));
        base.setCellValueFactory(new PropertyValueFactory<>("base"));


        img.setPrefWidth(80);
        img.setCellValueFactory(new PropertyValueFactory<>("img"));

        // Populate the TableView
        ArrayList<String> players = null;
        ArrayList<String> teamPlayer = null;
        teamPlayer = (ArrayList<String>) dbHandler.getTeamPlayers(username);

        if (x == 1) {
            players = (ArrayList<String>) dbHandler.getAllPlayers(username);
        }
        else if (x == 2) {
            players = (ArrayList<String>) dbHandler.searchPlayersByName(username, player_name);
        }
        else if (x == 3) {
            players = (ArrayList<String>) dbHandler.searchPlayersByPrice(username, min, max);
        }
        else if (x == 4) {
            players = (ArrayList<String>) dbHandler.searchPlayersByOvr(username, minOvr, maxOvr);
        }
        else if (x == 5) {
            players = (ArrayList<String>) dbHandler.searchPlayersByPosition(username, pos);
        }
        for (String player : players) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(50); // Adjust this value for the desired image width
            player_img.setFitHeight(50); // Adjust this value for the desired image height
            player_img.setPreserveRatio(true); // Maintain the aspect ratio
            player_img.setSmooth(true); // Enable smooth resizing

            personObservableListTable1.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], null, null, parts[8], parts[9], parts[10],parts[11] ));
        }
        table.setItems(personObservableListTable1);

        ArrayList<String> finalTeamPlayer = new ArrayList<>();
        if (teamPlayer != null) {
            for (String player : teamPlayer) {
                // Extract player_base_id from player string
                String playerBaseId = player.split("-")[0]; // Assuming player_base_id is the first element
                finalTeamPlayer.add(playerBaseId);
            }
        }


        actionCol.setCellFactory(col -> new TableCell<Person, Void>() {
            private final Button button = new Button();

            {
                button.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    if (button.getText().equalsIgnoreCase("Buy")) {
                        player_buy(username, person, button);
                    }
                    else if (button.getText().equalsIgnoreCase("Bid")) {
                        player_bid(username, person, button);
                    }
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Person person = getTableView().getItems().get(getIndex());
                    String personId = person.getPlayer_base_id(); // Player's ID

                    // Check if the player's ID is in the team list
                    boolean isPlayerInTeam = finalTeamPlayer.contains(personId);

                    // Set the button's text and disable state based on conditions
                    button.setText(person.getBase().equalsIgnoreCase("YES") ? "Buy" : "Bid");
                    button.setDisable(isPlayerInTeam); // Disable if player is in the team

                    button.setStyle(
                            "-fx-background-color: #002431;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-border-color: #309dd6;" +
                                    "-fx-border-radius: 4px;" +
                                    "-fx-background-radius: 5px;" +
                                    "-fx-border-width: 2px;" +
                                    "-fx-cursor: hand;" +
                                    "-fx-pref-width: 70px;"
                    );
                    if(isPlayerInTeam){
                        button.setText("Bought");
                        button.setStyle(
                                "-fx-background-color: #002431;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-border-color: #309dd6;" +
                                        "-fx-border-radius: 4px;" +
                                        "-fx-background-radius: 5px;" +
                                        "-fx-border-width: 2px;" +
                                        "-fx-cursor: hand;" +
                                        "-fx-pref-width: 70px;"+
                                        "-fx-pref-height: 38px;"+
                                        "-fx-font-size: 13px;"
                        );
                    }
                    setGraphic(button);
                }
            }

        });
    }

    public void setupListingTable(String username) {
        TableColumn<Person, String> img = new TableColumn<>("IMG");
        TableColumn<Person, String> name = new TableColumn<>("Name");
        TableColumn<Person, String> position = new TableColumn<>("Position");
        TableColumn<Person, String> ovr = new TableColumn<>("OVR");
        TableColumn<Person, String> price = new TableColumn<>("Price");

        TableColumn<Person, Void> actionCol = new TableColumn<>("Action");

        table1.setSelectionModel(null);

        table1.getColumns().clear();
        table1.getColumns().addAll(img, name, position, ovr, price, actionCol);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ovr.setCellValueFactory(new PropertyValueFactory<>("ovr"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        price.setCellValueFactory(new PropertyValueFactory<>("price"));

        img.setPrefWidth(80);
        img.setCellValueFactory(new PropertyValueFactory<>("img"));

        img.setPrefWidth(60);
        name.setPrefWidth(85); // Adjusted width for the NAME column
        position.setPrefWidth(50); // Adjusted width for the POSITION column
        ovr.setPrefWidth(40); // Adjusted width for the OVR column
        actionCol.setPrefWidth(80); // Adjusted width for the Action column
        price.setPrefWidth(75); // Adjusted width for the Action column

        ArrayList<String> players = (ArrayList<String>) dbHandler.getListingPlayers(username);

        personObservableListTable2.clear(); // Clear the list to prevent duplicates
        for (String player : players) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView(new Image(parts[1], true));
            player_img.setFitWidth(50);
            player_img.setFitHeight(50);
            player_img.setPreserveRatio(true);
            player_img.setSmooth(true);

            personObservableListTable2.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], null, null, parts[8], parts[9], parts[10], parts[11]));
        }

        table1.setItems(personObservableListTable2);

        // Setting smaller font size for the second table
        actionCol.setCellFactory(col -> new TableCell<Person, Void>() {
            private final Button button = new Button();

            {
                button.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());

                    showBiddingDetails(username, person);
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Person person = getTableView().getItems().get(getIndex());
                    String personId = person.getPlayer_base_id(); // Player's ID

                    button.setText("See Details");

                    button.setStyle(
                            "-fx-background-color: #002431;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-border-color: #309dd6;" +
                                    "-fx-border-radius: 4px;" +
                                    "-fx-background-radius: 5px;" +
                                    "-fx-border-width: 2px;" +
                                    "-fx-cursor: hand;" +
                                    "-fx-pref-width: 80px;" +
                                    "-fx-font-size: 12px;"
                    );
                    setGraphic(button);

                    // Change the font size of the name, ovr, and price columns
                    name.setStyle("-fx-font-size: 13px;"); // For 'name' column
                    position.setStyle("-fx-font-size: 13px;"); // For 'name' column
                    ovr.setStyle("-fx-font-size: 13px;"); // For 'ovr' column
                    price.setStyle("-fx-font-size: 13px;"); // For 'price' column
                }
            }
        });
    }

    public void setupBiddingTable(String username){
        TableColumn<Person, String> img = new TableColumn<>("Image");
        TableColumn<Person, String> name = new TableColumn<>("NAME");
        TableColumn<Person, String> position = new TableColumn<>("POSITION");
        TableColumn<Person, String> ovr = new TableColumn<>("OVR");
        TableColumn<Person, String> price = new TableColumn<>("Bidding Price");

        TableColumn<Person, Void> actionCol = new TableColumn<>("Action");

        table2.setSelectionModel(null);

        table2.getColumns().clear();
        table2.getColumns().addAll(img, name, position, ovr, price, actionCol);

        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        ovr.setCellValueFactory(new PropertyValueFactory<>("ovr"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        price.setCellValueFactory(new PropertyValueFactory<>("biddingPrice"));

        img.setPrefWidth(80);
        img.setCellValueFactory(new PropertyValueFactory<>("img"));

        img.setPrefWidth(60);
        name.setPrefWidth(85); // Adjusted width for the NAME column
        position.setPrefWidth(50); // Adjusted width for the POSITION column
        ovr.setPrefWidth(40); // Adjusted width for the OVR column
        actionCol.setPrefWidth(80); // Adjusted width for the Action column
        price.setPrefWidth(75); // Adjusted width for the Action column

        ArrayList<String> players = (ArrayList<String>) dbHandler.getBidPlayers(username);

        personObservableListTable4.clear(); // Clear the list to prevent duplicates
        for (String player : players) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView(new Image(parts[1], true));
            player_img.setFitWidth(50);
            player_img.setFitHeight(50);
            player_img.setPreserveRatio(true);
            player_img.setSmooth(true);

            personObservableListTable4.add(new Person(player_img, parts[2], parts[3], parts[4], parts[0], parts[5]));
        }

        table2.setItems(personObservableListTable4);

        // Setting smaller font size for the second table
        actionCol.setCellFactory(col -> new TableCell<Person, Void>() {
            private final Button button = new Button();

            {
                button.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    dbHandler.cancelBidPlayer(username, person.getPlayerID());
                    reloadMarketplaceProfile(username);
                });

            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);

                if (empty) {
                    setGraphic(null);
                } else {
                    Person person = getTableView().getItems().get(getIndex());
                    String personId = person.getPlayer_base_id(); // Player's ID

                    button.setText("✖ Bidding");

                    button.setStyle(
                            "-fx-background-color: #002431;" +
                                    "-fx-text-fill: white;" +
                                    "-fx-border-color: #309dd6;" +
                                    "-fx-border-radius: 4px;" +
                                    "-fx-background-radius: 5px;" +
                                    "-fx-border-width: 2px;" +
                                    "-fx-cursor: hand;" +
                                    "-fx-pref-width: 80px;" +
                                    "-fx-font-size: 12px;"
                    );
                    setGraphic(button);

                    // Change the font size of the name, ovr, and price columns
                    name.setStyle("-fx-font-size: 13px;"); // For 'name' column
                    position.setStyle("-fx-font-size: 13px;"); // For 'name' column
                    ovr.setStyle("-fx-font-size: 13px;"); // For 'ovr' column
                    price.setStyle("-fx-font-size: 13px;"); // For 'price' column
                }
            }
        });
    }


    public void setupTeamTable(String username){
        USERNAME = username;
        TableColumn<Person, String> img = new TableColumn<>("Image");
        TableColumn<Person, String> name = new TableColumn<>("NAME");
        TableColumn<Person, String> position = new TableColumn<>("POSITION");
        TableColumn<Person, String> ovr = new TableColumn<>("OVR");
        TableColumn<Person, String> country = new TableColumn<>("Country");

        // Button column
        TableColumn<Person, Void> actionCol = new TableColumn<>("Action");
        table1.getStyleClass().add("custom-table");

        table1.setSelectionModel(null);

        // Add columns to the TableView
        table1.getColumns().addAll(img, name, position, ovr, country, actionCol);

        // Map TableColumns to Person properties using PropertyValueFactory
        name.setCellValueFactory(new PropertyValueFactory<>("name"));
        position.setCellValueFactory(new PropertyValueFactory<>("position"));
        ovr.setCellValueFactory(new PropertyValueFactory<>("ovr"));
        country.setCellValueFactory(new PropertyValueFactory<>("country"));

        img.setPrefWidth(60);
        name.setPrefWidth(78); // Adjusted width for the NAME column
        position.setPrefWidth(50); // Adjusted width for the POSITION column
        ovr.setPrefWidth(50); // Adjusted width for the OVR column
        country.setPrefWidth(60); // Adjusted width for the Country column
        actionCol.setPrefWidth(50); // Adjusted width for the Action column

        img.setCellValueFactory(new PropertyValueFactory<>("img"));

        ArrayList<String> teamPlayer = null;
        teamPlayer = (ArrayList<String>) dbHandler.getTeamPlayers(username);

        for (String player : teamPlayer) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(40); // Adjust this value for the desired image width
            player_img.setFitHeight(40); // Adjust this value for the desired image height
            player_img.setPreserveRatio(true); // Maintain the aspect ratio
            player_img.setSmooth(true); // Enable smooth resizing

            personObservableListTable1.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12],parts[13] ));
        }
        table1.setItems(personObservableListTable1);

        // Sort the OVR column in descending order
        ovr.setSortType(TableColumn.SortType.DESCENDING);
        table1.getSortOrder().add(ovr); // Add the OVR column to the sort order
        table1.sort(); // Apply the sorting

        actionCol.setCellFactory(column -> {
            return new TableCell<Person, Void>() {
                private final Button actionButton = new Button("Options");
                {
                    ContextMenu contextMenu = new ContextMenu();

                    // Create menu items
                    MenuItem sellItem = new MenuItem("Sell");
                    MenuItem upgradeItem = new MenuItem("Upgrade");

                    // Add menu items to the context menu
                    contextMenu.getItems().addAll(sellItem, upgradeItem);

                    // Apply styling to the ContextMenu
                    contextMenu.setStyle("-fx-background-color: #002431; -fx-border-color: #309dd6; -fx-border-width: 1px; -fx-text-fill: white;");
                    sellItem.setStyle("-fx-text-fill: white;");
                    upgradeItem.setStyle("-fx-text-fill: white;");

                    // Set button action
                    actionButton.setOnMouseClicked(e -> {
                        Person currentPerson = getTableView().getItems().get(getIndex());

                        if (currentPerson.getSellable().equals("NO")) {
                            sellItem.setDisable(true);    // Disable "Sell" menu item
                            upgradeItem.setDisable(true); // Disable "Upgrade" menu item
                        } else {
                            sellItem.setDisable(false);   // Enable "Sell" menu item
                            upgradeItem.setDisable(false); // Enable "Upgrade" menu item
                        }

                        if (!contextMenu.isShowing()) {
                            contextMenu.show(actionButton, e.getScreenX(), e.getScreenY());
                        } else {
                            contextMenu.hide();
                        }
                    });

                    // Set actions for menu items
                    sellItem.setOnAction(e -> sellAction(getTableView().getItems().get(getIndex())));
                    upgradeItem.setOnAction(e -> upgradeAction(getTableView().getItems().get(getIndex())));
                }

                @Override
                protected void updateItem(Void item, boolean empty) {
                    super.updateItem(item, empty);
                    if (empty) {
                        setGraphic(null);
                    } else {
                        actionButton.setStyle(
                                "-fx-background-color: #002431;" +
                                        "-fx-text-fill: white;" +
                                        "-fx-border-color: #309dd6;" +
                                        "-fx-border-radius: 4px;" +
                                        "-fx-background-radius: 5px;" +
                                        "-fx-border-width: 2px;" +
                                        "-fx-cursor: hand;" +
                                        "-fx-pref-width: 70px;"
                        );
                        setGraphic(actionButton);
                    }
                }
            };
        });

        table1.setPlaceholder(new Label("No players in the team."));
        actionCol.setSortable(false); // Disable sorting for the action column
    }


    public void setupLineUp(String username) {
        Map<String, List<String>> playersByPosition = dbHandler.getTeamPlayersByPosition(username);

        // Creating and populating the combo box items for strikers
        ArrayList<Person> strikers = new ArrayList<>();
        for (String player : playersByPosition.get("ST")) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(50); // Set image width
            player_img.setFitHeight(50); // Set image height
            player_img.setPreserveRatio(true); // Keep aspect ratio

            // Add the player to the list
            strikers.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13] ));
        }

        ArrayList<Person> midfielders = new ArrayList<>();
        for (String player : playersByPosition.get("CM")) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(50); // Set image width
            player_img.setFitHeight(50); // Set image height
            player_img.setPreserveRatio(true); // Keep aspect ratio

            // Add the player to the list
            midfielders.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]));
        }

        ArrayList<Person> defenders = new ArrayList<>();
        for (String player : playersByPosition.get("CB")) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(50); // Set image width
            player_img.setFitHeight(50); // Set image height
            player_img.setPreserveRatio(true); // Keep aspect ratio

            // Add the player to the list
            defenders.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]));
        }

        ArrayList<Person> goalkeepers = new ArrayList<>();
        for (String player : playersByPosition.get("GK")) {
            String[] parts = player.split("-");

            ImageView player_img = new ImageView();
            player_img.setImage(new Image(parts[1], true)); // true for background loading
            player_img.setFitWidth(50); // Set image width
            player_img.setFitHeight(50); // Set image height
            player_img.setPreserveRatio(true); // Keep aspect ratio

            // Add the player to the list
            goalkeepers.add(new Person(parts[0], player_img, parts[2], parts[3], parts[4], parts[5], parts[6], parts[7], parts[8], parts[9], parts[10], parts[11], parts[12], parts[13]));
        }

        // Initialize active player placeholders
        Person st1Active = null;
        Person st2Active = null;
        Person st3Active = null;

        Person cm1Active = null;
        Person cm2Active = null;
        Person cm3Active = null;

        Person cb1Active = null;
        Person cb2Active = null;
        Person cb3Active = null;
        Person cb4Active = null;
        Person gkActive = null;

        // Filter active players
        for (Person player : strikers) {
            if ("yes".equalsIgnoreCase(player.getActive())) { // Check active status (base == "yes")
                int position = Integer.parseInt(player.getPos());
                if (position == 1) st1Active = player;
                if (position == 2) st2Active = player;
                if (position == 3) st3Active = player;
            }
        }

        for (Person player : midfielders) {
            if ("yes".equalsIgnoreCase(player.getActive())) { // Check active status (base == "yes")
                int position = Integer.parseInt(player.getPos());
                if (position == 1) cm1Active = player;
                if (position == 2) cm2Active = player;
                if (position == 3) cm3Active = player;
            }
        }

        for (Person player : defenders) {
            if ("yes".equalsIgnoreCase(player.getActive())) { // Check active status (base == "yes")
                int position = Integer.parseInt(player.getPos());
                if (position == 1) cb1Active = player;
                if (position == 2) cb2Active = player;
                if (position == 3) cb3Active = player;
                if (position == 4) cb4Active = player;
            }
        }

        for (Person player : goalkeepers) {
            if ("yes".equalsIgnoreCase(player.getActive())) { // Check active status (base == "yes")
                int position = Integer.parseInt(player.getPos());
                if (position == 0) gkActive = player;
            }
        }

        // Populate ComboBoxes
        st1.setItems(FXCollections.observableArrayList(strikers));
        st2.setItems(FXCollections.observableArrayList(strikers));
        st3.setItems(FXCollections.observableArrayList(strikers));
        cm1.setItems(FXCollections.observableArrayList(midfielders));
        cm2.setItems(FXCollections.observableArrayList(midfielders));
        cm3.setItems(FXCollections.observableArrayList(midfielders));
        cb1.setItems(FXCollections.observableArrayList(defenders));
        cb2.setItems(FXCollections.observableArrayList(defenders));
        cb3.setItems(FXCollections.observableArrayList(defenders));
        cb4.setItems(FXCollections.observableArrayList(defenders));
        gk.setItems(FXCollections.observableArrayList(goalkeepers));

        // Set default selection for active players
        if (st1Active != null) st1.getSelectionModel().select(st1Active);
        if (st2Active != null) st2.getSelectionModel().select(st2Active);
        if (st3Active != null) st3.getSelectionModel().select(st3Active);
        if (cm1Active != null) cm1.getSelectionModel().select(cm1Active);
        if (cm2Active != null) cm2.getSelectionModel().select(cm2Active);
        if (cm3Active != null) cm3.getSelectionModel().select(cm3Active);
        if (cb1Active != null) cb1.getSelectionModel().select(cb1Active);
        if (cb2Active != null) cb2.getSelectionModel().select(cb2Active);
        if (cb3Active != null) cb3.getSelectionModel().select(cb3Active);
        if (cb4Active != null) cb4.getSelectionModel().select(cb4Active);
        if (gkActive != null) gk.getSelectionModel().select(gkActive);

        // Repeat this process for midfielders, defenders, and goalkeepers

        setupComboBoxWithImages(st1);
        setupComboBoxWithImages(st2);
        setupComboBoxWithImages(st3);
        setupComboBoxWithImages(cm1);
        setupComboBoxWithImages(cm2);
        setupComboBoxWithImages(cm3);
        setupComboBoxWithImages(cb1);
        setupComboBoxWithImages(cb2);
        setupComboBoxWithImages(cb3);
        setupComboBoxWithImages(cb4);
        setupComboBoxWithImages(gk);

    }


    private void setupComboBoxWithImages(ComboBox<Person> comboBox) {
        // Set the ButtonCell (selected item display)
        comboBox.setButtonCell(new ListCell<>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null); // Clear the content
                } else {
                    setText(item.getName()); // Set the name
                    ImageView imageView = new ImageView(item.getImg().getImage()); // Ensure a fresh ImageView
                    imageView.setFitWidth(50); // Set image size
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView); // Display the image
                }
            }
        });

        // Set the ListCell (dropdown display)
        comboBox.setCellFactory(listView -> new ListCell<>() {
            @Override
            protected void updateItem(Person item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setGraphic(null); // Clear the content
                } else {
                    setText(item.getName()); // Set the name
                    ImageView imageView = new ImageView(item.getImg().getImage()); // Ensure a fresh ImageView
                    imageView.setFitWidth(50); // Set image size
                    imageView.setFitHeight(50);
                    imageView.setPreserveRatio(true);
                    setGraphic(imageView); // Display the image
                }
            }
        });
    }

    public void setActivePlayerBtn(){
        MusicManager.clickSound();

        Set<Person> set = new LinkedHashSet<>();
        set.add((Person) st1.getSelectionModel().getSelectedItem());
        set.add((Person) st2.getSelectionModel().getSelectedItem());
        set.add((Person) st3.getSelectionModel().getSelectedItem());
        set.add((Person) cm1.getSelectionModel().getSelectedItem());
        set.add((Person) cm2.getSelectionModel().getSelectedItem());
        set.add((Person) cm3.getSelectionModel().getSelectedItem());
        set.add((Person) cb1.getSelectionModel().getSelectedItem());
        set.add((Person) cb2.getSelectionModel().getSelectedItem());
        set.add((Person) cb3.getSelectionModel().getSelectedItem());
        set.add((Person) cb4.getSelectionModel().getSelectedItem());
        set.add((Person) gk.getSelectionModel().getSelectedItem());

        if(set.size() != 11){
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Error");
            errorAlert.setHeaderText("Cannot Save");
            errorAlert.setContentText("Same player has been selected for multiple position");
            errorAlert.showAndWait();
            return;
        }

        String st1_id = ((Person) st1.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '1';
        String st2_id = ((Person) st2.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '2';
        String st3_id = ((Person) st3.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '3';
        String cm1_id = ((Person) cm1.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '1';
        String cm2_id = ((Person) cm2.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '2';
        String cm3_id = ((Person) cm3.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '3';
        String cb1_id = ((Person) cb1.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '1';
        String cb2_id = ((Person) cb2.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '2';
        String cb3_id = ((Person) cb3.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '3';
        String cb4_id = ((Person) cb4.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '4';
        String gk_id = ((Person) gk.getSelectionModel().getSelectedItem()).getPlayer_base_id() +'-'+ '0';

        ArrayList<String> player_ids = new ArrayList<>();
        player_ids.add(st1_id);
        player_ids.add(st2_id);
        player_ids.add(st3_id);
        player_ids.add(cm1_id);
        player_ids.add(cm2_id);
        player_ids.add(cm3_id);
        player_ids.add(cb1_id);
        player_ids.add(cb2_id);
        player_ids.add(cb3_id);
        player_ids.add(cb4_id);
        player_ids.add(gk_id);

        dbHandler.updateActivePlayers(player_ids, USERNAME);
    }


    public void setMarketPlaceInfo(String username, String accountCoin) {
        accCoin.setText(accountCoin);
        accName.setText(username);
    }

    public void loginButton(ActionEvent event){
//        String username = username1.getText();
//        String password = password1.getText();
        MusicManager.clickSound();
        String username = "abc";
        String password = "123";
        username1.setText("");
        password1.setText("");

        boolean success = dbHandler.login(username, password);
//        boolean success = true;
        if (success) {
            USERNAME = username;
            try {
                mainMenuPage(username, event);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            invalidUserText.setVisible(true);
        }
    }
    public void RegButton(){
        MusicManager.clickSound();
        String username = username2.getText();
        String password = password2.getText();
        if(username2.getText().isEmpty() || password2.getText().isEmpty()) {
            return;
        }
        username2.setText("");
        password2.setText("");

        boolean success = dbHandler.registration(username, password);
        Stage stage = new Stage();

        Pane root = new Pane();
        root.setStyle("-fx-background-color: #002431;");
        Scene scene = new Scene(root, 500, 150);

        Text text = new Text();
        Button button = new Button();
        if(success){
            text.setText("Registration Successful. Please Login to play ;)");
            button.setText("Login");
        }
        else {
            text.setText("Registration Failed, Username is taken. Try Again :(");
            button.setText("Registration");
        }

        text.setX(50);
        text.setY(50);
        text.setFont(Font.font("Impact", FontWeight.BOLD, 20));
        text.setFill(Color.valueOf("#309dd6"));

        button.setLayoutX(210);
        button.setLayoutY(90);
        button.setStyle("-fx-background-color: #309dd6;  -fx-cursor: hand;");
        button.setFont(Font.font("Impact", 20));

        button.setOnAction( e ->{
            Stage currentStage = (Stage) button.getScene().getWindow(); // Get current stage
            currentStage.close();
        });

        root.getChildren().add(text);
        root.getChildren().add(button);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public void setUserInfo(String username, String accountLevel, String accountCoin) {
        MusicManager.clickSound();
        try {
            accName.setText(username);
            accLevel.setText(accountLevel);
            accCoin.setText(accountCoin);
            accXP.setText(String.valueOf(dbHandler.getAccountXP(username)));

            int team_Value = dbHandler.getTeamValue(username);
            teamValue.setText(String.valueOf(team_Value));

            int team_ovr = dbHandler.getTeamOvr(username);
            int avg = team_ovr / 11;
            teamOvr.setText(String.valueOf(avg));
            if(avg >= 80 && avg <90){
                String ovr = "/ove2.png";
                teamovr.setImage(new Image(ovr, true)); // true for background loading
                teamovr.setFitWidth(185); // Adjust this value for the desired image width
                teamovr.setFitHeight(195); // Adjust this value for the desired image height
                teamovr.setPreserveRatio(true); // Maintain the aspect ratio
                teamovr.setSmooth(true); // Enable smooth resizing
            } else if (avg >= 90) {
                String ovr = "/ovr1.png";
                teamovr.setImage(new Image(ovr, true)); // true for background loading
                teamovr.setFitWidth(225); // Adjust this value for the desired image width
                teamovr.setFitHeight(250); // Adjust this value for the desired image height
                teamovr.setPreserveRatio(true); // Maintain the aspect ratio
                teamovr.setSmooth(true); // Enable smooth resizing
            }
        } catch (Exception e) {

        }
    }
    public void setDivisionRivalInfo(String username){

        String playerInfo = null;
        playerInfo = dbHandler.getDivisionRivalInfo(username);

        String[] parts = playerInfo.split(":");
        String rank = parts[0];
        String badge = "";
        if(rank.equals("1")){
            badge = "/amateur.png";
        }
        else if(rank.equals("2")){
            badge = "/pro.png";
        }
        else if(rank.equals("3")){
            badge = "/worldclass.png";
        }
        else if(rank.equals("4")){
            badge = "/legendary.png";
        }
        rankBadge.setImage(new Image(badge, true)); // true for background loading
        rankBadge.setFitWidth(288); // Adjust this value for the desired image width
        rankBadge.setFitHeight(331); // Adjust this value for the desired image height
        rankBadge.setPreserveRatio(true); // Maintain the aspect ratio
        rankBadge.setSmooth(true); // Enable smooth resizing

        String[] score_parts = parts[1].split("-");
        XYChart.Series<String, Number> series = new XYChart.Series<>();

        for (int i = 0; i < score_parts.length; i++) {
            try {
                int score = Integer.parseInt(score_parts[i]); // Parse the score
                series.getData().add(new XYChart.Data<>("Match " + (i + 1), score)); // Add to chart as "Match X"
            } catch (NumberFormatException e) {
                System.err.println("Invalid number: " + score_parts[i]);
            }
        }

        // Update the chart (assuming you have a LineChart object called divisionRivalChart)
        divisionRivalChart.getData().clear(); // Clear any existing data
        divisionRivalChart.getData().add(series); // Add the new series
    }

    @FXML
    public void mainMenuButton(ActionEvent event) {
        try {
            String username = accName.getText();
            mainMenuPage(username, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void searchByNameButton(ActionEvent event) {
        try {
            String player_name = playerName.getText();
            marketPlaceNameSearch(player_name, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void searchByPriceButton(ActionEvent event) {
        try {
            String min = minPrice.getText();
            String max = maxPrice.getText();
            marketPlacePriceSearch(min, max, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void searchByOvrButton(ActionEvent event) {
        try {
            String min = minOvr.getText();
            String max = maxOvr.getText();
            System.out.println(min);
            System.out.println(max);
            marketPlaceOvrSearch(min, max, event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @FXML
    public void searchByPositionButton(ActionEvent event) throws IOException {
        positionGroup = new ToggleGroup();
        radioST.setToggleGroup(positionGroup);
        radioCM.setToggleGroup(positionGroup);
        radioCB.setToggleGroup(positionGroup);
        radioGK.setToggleGroup(positionGroup);

        RadioButton selectedRadioButton = (RadioButton) positionGroup.getSelectedToggle();
        String x = selectedRadioButton != null ? selectedRadioButton.getText() : "None";

        marketPlacePositionSearch(x, event);
    }

    public void player_buy(String username, Person person, Button button) {
        String player_base_id = person.getPlayer_base_id();
        String playerID = person.getPlayerID();
        int accCoin = Integer.parseInt(dbHandler.getAccountCoin(username));
        int playerPrice = Integer.parseInt(person.getPrice());

        if (accCoin >= playerPrice) {
            // Create a confirmation alert
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Purchase Confirmation");
            alert.setHeaderText("Confirm Purchase");
            alert.setContentText("Do you want to buy " + person.getName() + " for " + playerPrice + " coins?");

            // Display the alert and wait for user response
            Optional<ButtonType> result = alert.showAndWait();

            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Proceed with the purchase
                System.out.println("Buy confirmed for: " + person.getName());
                // Deduct coins from the account
                int remainingCoins = accCoin - playerPrice;
                dbHandler.updateAccountCoin(username, String.valueOf(remainingCoins));

                setMarketPlaceInfo(username, String.valueOf(remainingCoins));

                // Perform other actions like marking the player as purchased in the database
                dbHandler.insertIntoTeamPlayer(username, playerID, player_base_id);

                button.setDisable(true);
                button.setText("Bought");
                button.setStyle(
                        "-fx-background-color: #002431;" +
                                "-fx-text-fill: white;" +
                                "-fx-border-color: #309dd6;" +
                                "-fx-border-radius: 4px;" +
                                "-fx-background-radius: 5px;" +
                                "-fx-border-width: 2px;" +
                                "-fx-cursor: hand;" +
                                "-fx-pref-width: 70px;"+
                                "-fx-pref-height: 38px;"+
                                "-fx-font-size: 13px;"
                );
            }
        } else {
            // Show an error alert if there are not enough coins
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Insufficient Coins");
            errorAlert.setHeaderText("Purchase Failed");
            errorAlert.setContentText("You do not have enough coins to buy " + person.getName() + ".");
            errorAlert.showAndWait();
        }
    }

    public void player_bid(String username, Person person, Button button) {
        personObservableListTablebidding.clear();

        // Create a new stage
        Stage biddingStage = new Stage();
        biddingStage.setTitle("Bidding Details - " + person.getName());

        // Create a BorderPane layout
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #002431;");

        // Create the TableView
        TableView<Person> biddingTable = new TableView<>();

        // Define columns
        TableColumn<Person, String> bidderCol = new TableColumn<>("Bidder");
        TableColumn<Person, Integer> biddingPriceCol = new TableColumn<>("Bidding Price");

        bidderCol.setCellValueFactory(new PropertyValueFactory<>("bidder"));
        biddingPriceCol.setCellValueFactory(new PropertyValueFactory<>("biddingPrice"));
        bidderCol.setPrefWidth(150);
        biddingPriceCol.setPrefWidth(150);

        // Add columns to the TableView
        biddingTable.getColumns().addAll(bidderCol, biddingPriceCol);

        ArrayList<String> players = null;
        players = (ArrayList<String>) dbHandler.getPlayersWithBiddingDetails(Integer.parseInt(person.getPlayerID()));
        for (String player : players) {
            String[] parts = player.split("-");
            personObservableListTablebidding.add(new Person(parts[2], parts[1], parts[3], parts[0]));
        }

        biddingTable.setItems(personObservableListTablebidding);

        // Sort the Bidding Price column in descending order
        biddingPriceCol.setSortType(TableColumn.SortType.DESCENDING);
        biddingTable.getSortOrder().add(biddingPriceCol); // Add the column to the sort order
        biddingTable.sort(); // Apply the sorting

        // Layout components
        VBox centerLayout = new VBox(10, biddingTable);
        centerLayout.setAlignment(Pos.CENTER);

        // Create the input box and "Bid" button
        TextField bidInput = new TextField();
        bidInput.setPromptText("Enter your bid");
        bidInput.setStyle("-fx-pref-width: 200px; -fx-font-size: 14px; -fx-background-color: #002431; -fx-border-color: #309dd6; -fx-border-width: 3; -fx-border-radius: 4; -fx-text-fill: #ffffff; -fx-prompt-text-fill: #888888;");

        Button bidButton = new Button("Bid");
        bidButton.setStyle("-fx-background-color: #309dd6; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand;");

        // Add functionality to the "Bid" button
        bidButton.setOnAction(event -> {
            String bidValue = bidInput.getText();
            if (bidValue.isEmpty() || !bidValue.matches("\\d+")) {
                System.out.println("Invalid bid. Please enter a valid number.");
                return;
            }

            int bidAmount = Integer.parseInt(bidValue);
            placeBid(username, person, bidAmount);
            reloadMarketplaceProfile(username);
            biddingStage.close(); // Close the window after submitting the bid
        });

        // Add the input box and button to an HBox
        HBox bottomLayout = new HBox(10, bidInput, bidButton);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(10));
        bottomLayout.setStyle("-fx-background-color: #002431;"); // Match stage's background

        // Add components to the BorderPane
        layout.setCenter(centerLayout);
        layout.setBottom(bottomLayout);

        // Setup the scene and stage
        Scene scene = new Scene(layout, 360, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        biddingStage.setScene(scene);
        biddingStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        biddingStage.showAndWait();
    }

    public void matchHistory(ActionEvent actionEvent) {
        MusicManager.clickSound();
        String username = accName.getText();

        // ObservableList to hold String arrays representing matches
        ObservableList<String[]> matchesList = FXCollections.observableArrayList();

        Stage matchHistoryStage = new Stage();
        matchHistoryStage.setTitle("Match History");

        // Create a BorderPane layout
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #002431;");

        // Create the TableView
        TableView<String[]> mhistory = new TableView<>();

        // Define columns
        TableColumn<String[], String> meCol = new TableColumn<>("Me");
        TableColumn<String[], String> scoreCol = new TableColumn<>("Score");
        TableColumn<String[], String> opponentCol = new TableColumn<>("Opponent");

        // Bind columns to the appropriate index of the String[] (0-based index)
        meCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[0]));
        scoreCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[1]));
        opponentCol.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue()[2]));
        mhistory.setSelectionModel(null);

        // Set column widths
        meCol.setPrefWidth(150);
        scoreCol.setPrefWidth(100);
        opponentCol.setPrefWidth(150);

        // Add columns to the TableView
        mhistory.getColumns().addAll(meCol, scoreCol, opponentCol);

        // Add cell factory to "Score" column for per-cell styling
        scoreCol.setCellFactory(column -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);

                    try {
                        // Extract numeric scores from the "Score" column text (e.g., "2 - 1")
                        String[] scoreParts = item.split(" - ");
                        int meScore = Integer.parseInt(scoreParts[0].trim());
                        int opponentScore = Integer.parseInt(scoreParts[1].trim());

                        // Apply styles based on score comparison
                        if (meScore > opponentScore) {
                            setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                        } else if (meScore < opponentScore) {
                            setStyle("-fx-text-fill: red; -fx-font-weight: bold;");
                        } else {
                            setStyle("-fx-text-fill: white; -fx-font-weight: bold;");
                        }
                    } catch (NumberFormatException e) {
                        setStyle(""); // Reset style if parsing fails
                    }
                }
            }
        });

        // Populate the ObservableList with match data from the database
        ArrayList<String> matches = (ArrayList<String>) dbHandler.getMatchHistory(username);
        for (String match : matches) {
            String[] parts = match.split("-"); // Example: ["me", "score", "opponent", "opponentScore"]
            String[] row = {parts[0], parts[1] + " - " + parts[3], parts[2]};
            matchesList.add(row); // Add the array directly to the ObservableList
        }

        mhistory.setItems(matchesList); // Set the TableView's items

        // Add the TableView to the layout
        VBox centerLayout = new VBox(mhistory);
        centerLayout.setAlignment(Pos.CENTER);
        layout.setCenter(centerLayout);

        // Set up and display the stage
        Scene scene = new Scene(layout, 500, 400);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        matchHistoryStage.setScene(scene);
        matchHistoryStage.initModality(Modality.APPLICATION_MODAL);
        matchHistoryStage.showAndWait();
    }

    public void spinTheWheel(ActionEvent actionEvent) {
        MusicManager.clickSound();
        Stage spinWheelStage = new Stage();
        spinWheelStage.setTitle("Spin The Wheel");

        String username = accName.getText(); // Example of getting the username
        final int WHEEL_RADIUS = 200;
        final String[] SECTIONS = {"card", "1000", "10000", "5000", "50", "card", "100", "100000", "50000", "1000"};
        final int POINTER_ANGLE = 90;

        // Create a canvas for the wheel
        Canvas canvas = new Canvas(WHEEL_RADIUS * 2, WHEEL_RADIUS * 2);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        // Define an array of colors for each section
        final Color[] SECTION_COLORS = {
                Color.rgb(120,209,235), Color.rgb(226,205,32), Color.rgb(41,226,32), Color.rgb(226,123,32), Color.rgb(226,32,73), Color.rgb(120,209,235), Color.rgb(226,205,32), Color.rgb(41,226,32), Color.rgb(226,123,32), Color.rgb(226,32,73)
        };

        // Define images for specific sections (null if no image)
        final Image[] SECTION_IMAGES = {
                new Image("/customCard.jpg"), // Replace with valid image paths
                null, // No image for this section
                null,
                null,
                null, new Image("/customCard.jpg"),
                null, null, null, null
        };

        // Draw the wheel
        double anglePerSection = 360.0 / SECTIONS.length;
        double startAngle = 0;

        for (int i = 0; i < SECTIONS.length; i++) {
            // Draw the background color arc
            gc.setFill(SECTION_COLORS[i % SECTION_COLORS.length]);
            gc.fillArc(0, 0, WHEEL_RADIUS * 2, WHEEL_RADIUS * 2, startAngle, anglePerSection, ArcType.ROUND);

            if (SECTION_IMAGES[i] != null) {
                // Scale the image to fit within a section's bounding area
                double scaleFactor = 0.04; // Adjust the scale factor as needed (smaller values shrink the image)
                double imageWidth = SECTION_IMAGES[i].getWidth() * scaleFactor;
                double imageHeight = SECTION_IMAGES[i].getHeight() * scaleFactor;

                // Calculate the position to center the image in the wheel
                double centerX = WHEEL_RADIUS + Math.cos(Math.toRadians(startAngle + anglePerSection / 2)) * WHEEL_RADIUS * 0.5;
                double centerY = WHEEL_RADIUS - Math.sin(Math.toRadians(startAngle + anglePerSection / 2)) * WHEEL_RADIUS * 0.5;

                // Draw the scaled image centered within its section
                gc.drawImage(
                        SECTION_IMAGES[i],
                        centerX - imageWidth / 2, // Adjust position to center horizontally
                        centerY - imageHeight / 2, // Adjust position to center vertically
                        imageWidth,
                        imageHeight
                );
            }

            // Add text for each section
            gc.setFill(Color.BLACK);
            gc.setFont(new Font("Arial", 16)); // Set the font style and size
            double angle = Math.toRadians(startAngle + anglePerSection / 2);
            double textX = WHEEL_RADIUS + Math.cos(angle) * WHEEL_RADIUS * 0.6;
            double textY = WHEEL_RADIUS - Math.sin(angle) * WHEEL_RADIUS * 0.6;
            startAngle += anglePerSection;
            if(i == 0 || i == 5) continue;
            gc.fillText(SECTIONS[i], textX - 20, textY); // Adjust text offset

        }



        // Add a pointer at the top
        Polygon pointer = new Polygon();
        pointer.getPoints().addAll(
                (double) WHEEL_RADIUS, 0.0,  // Top center of the wheel
                (double) WHEEL_RADIUS - 20, 20.0,
                (double) WHEEL_RADIUS + 20, 20.0
        );
        pointer.setFill(Color.BLACK);

        // Button to spin the wheel
        Button spinButton = new Button("Spin");
        spinButton.setStyle(
                "-fx-background-color: #002431;" +
                        "-fx-text-fill: white;" +
                        "-fx-border-color: #309dd6;" +
                        "-fx-border-radius: 4px;" +
                        "-fx-background-radius: 5px;" +
                        "-fx-border-width: 2px;" +
                        "-fx-cursor: hand;" +
                        "-fx-pref-width: 70px;"
        );

        Date date = dbHandler.getUserDateAndSpin(username);
        LocalDate currentDate = LocalDate.now();

        // Compare dates
        if (currentDate.isEqual(date.toLocalDate())) {
            spinButton.setDisable(true);
        }

        spinButton.setOnAction(e -> {
            // Play spinwheel sound
            MusicManager.spinwheel();

            RotateTransition rotateTransition = new RotateTransition(Duration.seconds(3), canvas);
            Random random = new Random();
            double randomEndAngle = 1440 + random.nextInt(360); // At least 4 full rotations
            rotateTransition.setByAngle(randomEndAngle);
            rotateTransition.setOnFinished(event -> {
                // Stop spinwheel sound after the spin ends
                MusicManager.stopSpinwheelSound();

                double finalAngle = randomEndAngle % 360; // Normalize to 0–359°
                double adjustedAngle = (finalAngle + POINTER_ANGLE) % 360;

                // Calculate the winning section
                int winningIndex = (int) (adjustedAngle / anglePerSection);
                String winningPrize = SECTIONS[winningIndex];
                System.out.println("Winner: " + winningPrize);

                if (winningPrize.equals("card")) {
                    String currentCard = String.valueOf(Integer.parseInt(dbHandler.getAccountCardCount(username)) + 1);
                    dbHandler.updateAccountCard(username, currentCard);
                } else {
                    String currentCoin = String.valueOf(Integer.parseInt(dbHandler.getAccountCoin(username)) + Integer.parseInt(winningPrize));
                    dbHandler.updateAccountCoin(username, currentCoin);
                }

                showSuccessMessage1(winningPrize, spinWheelStage);
                dbHandler.updateDate(username, currentDate);
            });
            rotateTransition.play();
        });



        // Layout
        StackPane root = new StackPane(canvas, pointer, spinButton);
        StackPane.setAlignment(spinButton, Pos.BOTTOM_CENTER);
        root.setStyle("-fx-padding: 20; -fx-background-color: #002431;");

        // Set up and display the stage
        Scene scene = new Scene(root, 500, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        spinWheelStage.setScene(scene);
        spinWheelStage.initModality(Modality.APPLICATION_MODAL);
        spinWheelStage.showAndWait();
        accCoin.setText(dbHandler.getAccountCoin(username));
    }

    private void sellAction(Person person) {
        if (person != null) {
            if (person.getBase().equals("YES")) {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Sell Confirmation");
                alert.setHeaderText("Confirm Sell");
                alert.setContentText("Do you want to sell " + person.getName() + " for " + person.getPrice() + " coins?");

                // Display the alert and wait for user response
                Optional<ButtonType> result = alert.showAndWait();

                if (result.isPresent() && result.get() == ButtonType.OK) {
                    int remainingCoins = Integer.parseInt(dbHandler.getAccountCoin(USERNAME)) + Integer.parseInt(person.getPrice());
                    dbHandler.updateAccountCoin(USERNAME, String.valueOf(remainingCoins));

                    // Perform other actions like marking the player as purchased in the database
                    dbHandler.deletePlayerFromTeam(USERNAME, person.getPlayerID());
                    reloadTeamProfile();
                }
            } else if (person.getBase().equals("NO")) {
                TextInputDialog dialog = new TextInputDialog();
                dialog.setTitle("Set Price");
                dialog.setHeaderText("Set Sell Price for " + person.getName());

                // Set styles for the dialog
                DialogPane dialogPane = dialog.getDialogPane();
                dialogPane.setStyle("-fx-background-color: #002431; -fx-border-color: #309dd6; -fx-border-width: 2px; -fx-text-fill: white;");
                dialogPane.lookup(".label").setStyle("-fx-text-fill: white; -fx-font-size: 14px;");
                dialogPane.lookup(".text-field").setStyle("-fx-background-color: #FFFFFF; -fx-text-fill: #000000;");

                // Display the dialog and wait for user input
                Optional<String> result = dialog.showAndWait();

                if (result.isPresent()) {
                    try {
                        int price = Integer.parseInt(result.get());
                        if (price > 0) {
                            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                            confirmationAlert.setTitle("Confirm Sell");
                            confirmationAlert.setHeaderText("Do you want to sell " + person.getName() + " for " + price + " coins?");
                            confirmationAlert.getDialogPane().setStyle("-fx-background-color: #002431; -fx-border-color: #309dd6; -fx-border-width: 2px; -fx-text-fill: white;");
                            confirmationAlert.getDialogPane().lookup(".label").setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

                            Optional<ButtonType> confirmationResult = confirmationAlert.showAndWait();

                            if (confirmationResult.isPresent() && confirmationResult.get() == ButtonType.OK) {
                                dbHandler.insertIntoMarketPlaceAndUpdatePlayerData(USERNAME, person.getPlayerID(), price);
                            }
                        } else {
                            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                            errorAlert.setTitle("Invalid Price");
                            errorAlert.setHeaderText(null);
                            errorAlert.setContentText("The price must be a positive number.");
                            errorAlert.showAndWait();
                        }
                    } catch (NumberFormatException e) {
                        Alert errorAlert = new Alert(Alert.AlertType.ERROR);
                        errorAlert.setTitle("Invalid Input");
                        errorAlert.setHeaderText(null);
                        errorAlert.setContentText("Please enter a valid numeric value for the price.");
                        errorAlert.showAndWait();
                    }
                }
            }
        }
    }
    private void upgradeAction(Person person) {
        if (person == null) {
            System.out.println("No player selected for upgrading.");
            return;
        }

        // Create a new stage
        Stage upgradeStage = new Stage();
        upgradeStage.setTitle("Upgrade Player - " + person.getName());

        // Create a BorderPane layout for flexible positioning
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #002431;");

        // Player image
        ImageView playerImage = new ImageView(person.getImg().getImage());
        playerImage.setFitWidth(100);
        playerImage.setFitHeight(100);
        playerImage.setPreserveRatio(true);
        playerImage.setSmooth(true);
        playerImage.setStyle("-fx-effect: dropshadow(gaussian, rgba(0, 0, 0, 0.75), 10, 0, 0, 0);");

        // Top-right corner: Image and card count
        ImageView cardImage = new ImageView(new Image("/customCard1.jpg")); // Replace with the actual card image path
        cardImage.setFitWidth(30);
        cardImage.setFitHeight(50);

        Label cardCountLabel = new Label(dbHandler.getAccountCardCount(USERNAME));
        cardCountLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");

        // Combine the card image and card count into an HBox
        HBox cardInfoBox = new HBox(10, cardImage, cardCountLabel); // Add spacing of 10
        cardInfoBox.setAlignment(Pos.TOP_RIGHT);

        // Place the card info box in the top-right corner
        BorderPane.setAlignment(cardInfoBox, Pos.TOP_RIGHT);
        layout.setTop(cardInfoBox);

        // Player details
        Label playerNameLabel = new Label("Name: " + person.getName());
        Label playerPositionLabel = new Label("Position: " + person.getPosition());
        Label playerOvrLabel = new Label("Current OVR: " + person.getOvr());
        Label levelLabel = new Label("Current Level: " + person.getLevel());

        int cost = (Integer.parseInt(person.getLevel()) + 1) * 50000;
        Label upgradeCostLabel = new Label("Upgrade Cost: " + cost + " Coins");

        for (Label label : new Label[]{playerNameLabel, playerPositionLabel, playerOvrLabel, levelLabel, upgradeCostLabel}) {
            label.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        }

        int accountCoin = Integer.parseInt(dbHandler.getAccountCoin(USERNAME));

        // Buttons layout
        Button upgradeButton = new Button("Upgrade with Coins");
        Button upgradeWithCoinButton = new Button("Upgrade with Power Pass");

        // Common button style
        String buttonStyle = "-fx-background-color: #309dd6; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand;";
        upgradeButton.setStyle(buttonStyle);
        upgradeWithCoinButton.setStyle(buttonStyle);

        // Upgrade button action
        upgradeButton.setOnAction(e -> handleUpgradeAction(person, upgradeStage, cost, accountCoin, false));

        // Upgrade with coins button action
        upgradeWithCoinButton.setOnAction(e -> handleUpgradeAction(person, upgradeStage, cost, accountCoin, true));

        // Add buttons to HBox
        HBox buttonBox = new HBox(10, upgradeButton, upgradeWithCoinButton);
        buttonBox.setAlignment(Pos.CENTER);

        // Combine components
        VBox centerLayout = new VBox(10, playerImage, playerNameLabel, playerPositionLabel, playerOvrLabel, levelLabel, upgradeCostLabel, buttonBox);
        centerLayout.setAlignment(Pos.CENTER);

        layout.setCenter(centerLayout);

        // Setup the scene and stage
        Scene scene = new Scene(layout, 450, 450); // Adjusted width for better alignment
        upgradeStage.setScene(scene);

        upgradeStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        upgradeStage.showAndWait();
    }
    private void handleUpgradeAction(Person person, Stage upgradeStage, int cost, int accountCoin, boolean isCoinUpgrade) {
        if (isCoinUpgrade) {
            int cardCount = Integer.parseInt(dbHandler.getAccountCardCount(USERNAME));
            if (cardCount >= 1) {
                String playerID = person.getPlayerID();

                if (person.getBase().equals("YES")) {
                    dbHandler.upgradeFromBasePlayer(playerID, USERNAME);
                } else {
                    dbHandler.upgradeFromPlayer(playerID, person.getOvr(), person.getLevel(), USERNAME);
                }
                dbHandler.updateAccountCard(USERNAME, String.valueOf(cardCount - 1));
                upgradeStage.close();
                showSuccessMessage(person.getName());

                // Reload team profile
                reloadTeamProfile();
            } else {
                upgradeStage.close();
                showFailedMessage(person.getName());
            }
        }
        else {
            if (accountCoin >= cost) {
                accCoin.setText(String.valueOf(accountCoin - cost));
                String playerID = person.getPlayerID();

                if (person.getBase().equals("YES")) {
                    dbHandler.upgradeFromBasePlayer(playerID, USERNAME);
                } else {
                    dbHandler.upgradeFromPlayer(playerID, person.getOvr(), person.getLevel(), USERNAME);
                }
                dbHandler.updateAccountCoin(USERNAME, String.valueOf(accountCoin - cost));
                upgradeStage.close();
                showSuccessMessage(person.getName());

                // Reload team profile
                reloadTeamProfile();
            } else {
                upgradeStage.close();
                showFailedMessage(person.getName());
            }
        }
    }

    public void placeBid(String username, Person person, int bidAmount){
        dbHandler.placeBidonMarketplace(person.getPlayerID(), username, bidAmount);
    }

    public void showBiddingDetails(String username, Person person) {
        if (person == null) {
            System.out.println("No player selected for bidding details.");
            return;
        }

        personObservableListTable3.clear();

        // Create a new stage
        Stage biddingStage = new Stage();
        biddingStage.setTitle("Bidding Details - " + person.getName());

        // Create a BorderPane layout
        BorderPane layout = new BorderPane();
        layout.setPadding(new Insets(20));
        layout.setStyle("-fx-background-color: #002431;");

        // Create the TableView
        TableView<Person> biddingTable = new TableView<>();

        // Define columns
        TableColumn<Person, String> bidderCol = new TableColumn<>("Bidder");
        TableColumn<Person, Integer> biddingPriceCol = new TableColumn<>("Bidding Price");

        bidderCol.setCellValueFactory(new PropertyValueFactory<>("bidder"));
        biddingPriceCol.setCellValueFactory(new PropertyValueFactory<>("biddingPrice"));
        bidderCol.setPrefWidth(150);
        biddingPriceCol.setPrefWidth(150);

        TableColumn<Person, Void> actionCol = new TableColumn<>("Actions");
        actionCol.setCellFactory(col -> new TableCell<>() {
            private final Button acceptButton = new Button("Accept");

            {
                acceptButton.setOnAction(event -> {
                    Person person = getTableView().getItems().get(getIndex());
                    acceptBid(person); // Implement logic for accepting a bid
                    biddingStage.close();
                });
            }

            @Override
            protected void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(acceptButton);
                }
            }
        });

        // Add columns to the TableView
        biddingTable.getColumns().addAll(bidderCol, biddingPriceCol, actionCol);

        ArrayList<String> players = null;

        players = (ArrayList<String>) dbHandler.getPlayersWithBiddingDetails(username, Integer.parseInt(person.getPlayerID()));
        for (String player : players) {
            String[] parts = player.split("-");
            personObservableListTable3.add(new Person(parts[2], parts[1], parts[3], parts[0]));
        }

        biddingTable.setItems(personObservableListTable3);

        // Sort the Bidding Price column in descending order
        biddingPriceCol.setSortType(TableColumn.SortType.DESCENDING);
        biddingTable.getSortOrder().add(biddingPriceCol); // Add the column to the sort order
        biddingTable.sort(); // Apply the sorting

        // Layout components
        VBox centerLayout = new VBox(10, biddingTable);
        centerLayout.setAlignment(Pos.CENTER);

        // Create the "Cancel" button
        Button cancelButton = new Button("Cancel Listing");
        cancelButton.setStyle("-fx-background-color: #ff4d4d; -fx-text-fill: white; -fx-font-size: 14px; -fx-cursor: hand;");
        cancelButton.setPrefWidth(150);
        cancelButton.setOnAction(event -> {
            dbHandler.cancelListingPlayer(username, person.getPlayerID());
            biddingStage.close();
            reloadMarketplaceProfile(username);
        });

        // Add the Cancel button to an HBox
        HBox bottomLayout = new HBox(cancelButton);
        bottomLayout.setAlignment(Pos.CENTER);
        bottomLayout.setPadding(new Insets(10));
        bottomLayout.setStyle("-fx-background-color: #002431;"); // Match stage's background

        // Add components to the BorderPane
        layout.setCenter(centerLayout);
        layout.setBottom(bottomLayout);

        // Setup the scene and stage
        Scene scene = new Scene(layout, 500, 600);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm());
        biddingStage.setScene(scene);
        biddingStage.initModality(Modality.APPLICATION_MODAL); // Block interaction with other windows
        biddingStage.showAndWait();
    }

    // Accept bid logic
    private void acceptBid(Person person) {
        String seller = person.getSeller();
        String bidder = person.getBidder();
        int biddingPrice = Integer.parseInt(person.getBiddingPrice());
        String playerID = person.getPlayerID();

        String sellerCoin = dbHandler.getAccountCoin(seller);
        String bidderCoin = dbHandler.getAccountCoin(bidder);

        dbHandler.deleteMarketplaceEntryAndUpdate(seller, bidder, playerID);
        int newSellerCoin = Integer.parseInt(sellerCoin) + biddingPrice;
        int newBidderCoin = Integer.parseInt(bidderCoin) - biddingPrice;

        dbHandler.updateAccountCoin(seller, String.valueOf(newSellerCoin));
        dbHandler.updateAccountCoin(bidder, String.valueOf(newBidderCoin));
        reloadMarketplaceProfile(seller);
    }


    private void reloadTeamProfile() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("team-profile.fxml"));
            Parent newRoot = fxmlLoader.load();

            HelloController controller = fxmlLoader.getController();
            controller.setUserInfo(USERNAME, dbHandler.getAccountLevel(USERNAME), dbHandler.getAccountCoin(USERNAME));

            Stage currentStage = (Stage) accCoin.getScene().getWindow();
            currentStage.getScene().setRoot(newRoot);

            controller.setupTeamTable(USERNAME);
            controller.setupLineUp(USERNAME);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    private void reloadMarketplaceProfile(String username) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("marketPlace.fxml"));
            Parent newRoot = fxmlLoader.load();

            HelloController controller = fxmlLoader.getController();
            controller.setUserInfo(username, dbHandler.getAccountLevel(username), dbHandler.getAccountCoin(username));

            controller.setMarketPlaceInfo(username, dbHandler.getAccountCoin(username));

            Stage currentStage = (Stage) accCoin.getScene().getWindow();
            currentStage.getScene().setRoot(newRoot);

            controller.setupTable(username, 1, null, null, null, null, null, null);
            controller.setupListingTable(username);
            controller.setupBiddingTable(username);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
//        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();
    }


    private void showSuccessMessage(String playerName) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Upgrade Successful");
            alert.setHeaderText(null);
            alert.setContentText(playerName + " has been successfully upgraded!");
            alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-text-fill: black;");
            alert.showAndWait();
        });
    }
    private void showFailedMessage(String playerName) {
        Platform.runLater(() -> {
            Alert errorAlert = new Alert(Alert.AlertType.ERROR);
            errorAlert.setTitle("Insufficient Coins");
            errorAlert.setHeaderText("Purchase Failed");
            errorAlert.setContentText("You do not have enough coins to buy " + playerName + ".");
            errorAlert.showAndWait();
        });
    }
    private void showSuccessMessage1(String x, Stage spinWheelStage) {
        Platform.runLater(() -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Spin the Wheel");
            alert.setHeaderText(null);
            if (x.equals("card")) {
                alert.setContentText("Congratulations! 🎉🎉🎉🎉 You have received an upgrade card");
            } else {
                alert.setContentText("Congratulations! 🎉🎉🎉🎉 You have received " + x + " coins");
            }
            alert.getDialogPane().setStyle("-fx-font-size: 14px; -fx-text-fill: black;");

            // Close the spin wheel stage when the alert is dismissed
            alert.setOnHidden(event -> spinWheelStage.close());
            alert.showAndWait();
        });

    }

//    public void messageButton(ActionEvent event) {
//        Stage messageStage = new Stage();
//        messageStage.setTitle("Messages");
//
//        // SplitPane to separate users list and message interface
//        SplitPane splitPane = new SplitPane();
//
//        // Left Pane: Usernames ListView
//        ListView<String> userListView = new ListView<>();
//        List<String> usernames = dbHandler.getAllUsernames();
//        userListView.getItems().addAll(usernames);
//
//        userListView.setPrefWidth(150);
//        userListView.setStyle("-fx-border-color: #cccccc; -fx-padding: 10;");
//
//        // Right Pane: Messaging Interface
//        VBox messagePane = new VBox(10);
//        messagePane.setPadding(new Insets(10));
//        messagePane.setPrefWidth(400);
//        messagePane.setStyle("-fx-border-color: #cccccc;");
//
//        Label titleLabel = new Label("Select a user to start messaging");
//        titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");
//
//        VBox messageContainer = new VBox(5);
//        messageContainer.setPrefHeight(400);
//        messageContainer.setStyle("-fx-padding: 10; -fx-border-radius: 5;");
//
//        ScrollPane scrollPane = new ScrollPane(messageContainer);
//        scrollPane.setFitToWidth(true);
//        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
//
//        TextField messageInput = new TextField();
//        messageInput.setPromptText("Type a message...");
//        messageInput.setPrefWidth(300);
//        Button sendButton = new Button("Send");
//        sendButton.setDisable(true);
//        sendButton.setPrefWidth(70);
//
//        HBox inputPane = new HBox(5, messageInput, sendButton);
//        inputPane.setAlignment(Pos.CENTER_RIGHT);
//
//        messagePane.getChildren().addAll(titleLabel, scrollPane, inputPane);
//
//        // Handle user selection from ListView
//        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedUser) -> {
//            if (selectedUser != null) {
//                titleLabel.setText("Chat with " + selectedUser);
//                messageContainer.getChildren().clear();
//                sendButton.setDisable(false);
//
//                // Fetch messages between the current user and the selected user
//                List<String[]> messages = dbHandler.getMessagesBetweenUsers(accName.getText(), selectedUser);
//
//                for (String[] messageData : messages) {
//                    String sender = messageData[0];
//                    String message = messageData[1];
//
//                    if (sender.equals(accName.getText())) { // Sent messages
//                        Label messageLabel = new Label(message);
//                        messageLabel.setStyle("-fx-background-color: #309dd6; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: black;");
//                        HBox messageBox = new HBox(messageLabel);
//                        messageBox.setAlignment(Pos.CENTER_RIGHT);
//                        messageContainer.getChildren().add(messageBox);
//
//                    } else { // Received messages
//                        Label messageLabel = new Label(message);
//                        messageLabel.setStyle("-fx-background-color: #213840; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: white;");
//                        HBox messageBox = new HBox(messageLabel);
//                        messageBox.setAlignment(Pos.CENTER_LEFT);
//                        messageContainer.getChildren().add(messageBox);
//
//                    }
//                }
//
//                // Scroll to the bottom of the chat
//                scrollPane.setVvalue(1.0);
//            }
//        });
//
//        // Handle sending a message
//        sendButton.setOnAction(e -> {
//            String selectedUser = userListView.getSelectionModel().getSelectedItem();
//            if (selectedUser != null && !messageInput.getText().trim().isEmpty()) {
//                String message = messageInput.getText().trim();
//
//                // Save the message to the database
//                dbHandler.saveMessage(accName.getText(), selectedUser, message);
//
//                // Update the message list (add new message to view)
//                Label messageLabel = new Label(message);
//                messageLabel.setStyle("-fx-background-color: #309dd6; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: black;");
//                HBox messageBox = new HBox(messageLabel);
//                messageBox.setAlignment(Pos.CENTER_RIGHT);
//                messageContainer.getChildren().add(messageBox);
//
//                messageInput.clear();
//
//                // Scroll to the bottom of the chat
//                scrollPane.setVvalue(1.0);
//            }
//        });
//
//        // Add ListView and Message Interface to SplitPane
//        splitPane.getItems().addAll(userListView, messagePane);
//        splitPane.setDividerPositions(0.3); // Adjust the ratio of ListView and messagePane
//
//        // Scene and Stage Setup
//        Scene scene = new Scene(splitPane, 700, 600);
//        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Optional CSS for styling
//        scene.getRoot().setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
//        messageStage.setScene(scene);
//        messageStage.initModality(Modality.APPLICATION_MODAL);
//        messageStage.showAndWait();
//    }


    public void messageButton(ActionEvent event) {
        Stage messageStage = new Stage();
        messageStage.setTitle("Messages");

        // SplitPane to separate users list and message interface
        SplitPane splitPane = new SplitPane();

        // Left Pane: Usernames ListView
        ListView<String> userListView = new ListView<>();
        List<String> usernames = dbHandler.getAllUsernames();
        userListView.getItems().addAll(usernames);

        userListView.setPrefWidth(150);
        userListView.setStyle("-fx-border-color: #cccccc; -fx-padding: 10;");

        // Right Pane: Messaging Interface
        VBox messagePane = new VBox(10);
        messagePane.setPadding(new Insets(10));
        messagePane.setPrefWidth(350);
        messagePane.setStyle("-fx-border-color: #cccccc;");

        Label titleLabel = new Label("Select a user to start messaging");
        titleLabel.setStyle("-fx-font-size: 16px; -fx-text-fill: gray;");

        ListView<HBox> messageListView = new ListView<>();
        messageListView.setPrefHeight(400);

        TextField messageInput = new TextField();
        messageInput.setPromptText("Type a message...");
        messageInput.setPrefWidth(300);
        Button sendButton = new Button("Send");
        sendButton.setDisable(true);
        sendButton.setPrefWidth(70);
        Button playButton = new Button("Play");
        playButton.setDisable(true);
        playButton.setPrefWidth(70);

        HBox inputPane = new HBox(5, messageInput, sendButton, playButton);
        inputPane.setAlignment(Pos.CENTER_RIGHT);

        messagePane.getChildren().addAll(titleLabel, messageListView, inputPane);

        // Track selected user
        AtomicReference<String> currentChatUser = new AtomicReference<>();

        // Handle user selection from ListView
        userListView.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, selectedUser) -> {
            if (selectedUser != null) {
                currentChatUser.set(selectedUser);
                titleLabel.setText("Chat with " + selectedUser);
                sendButton.setDisable(false);
                playButton.setDisable(false);
                updateMessages(messageListView, accName.getText(), selectedUser);
            }
            if(selectedUser.equals(accName.getText())){
                playButton.setDisable(true);
            }
        });

        // Poll the database every 2 seconds for new messages
        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(2), e -> {
            String selectedUser = currentChatUser.get();
            if (selectedUser != null) {
                updateMessages(messageListView, accName.getText(), selectedUser);
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        // Handle sending a message
        sendButton.setOnAction(e -> {
            String selectedUser = userListView.getSelectionModel().getSelectedItem();
            String message = messageInput.getText().trim();

            // Ensure that message is not empty
            if (selectedUser != null && !message.isEmpty()) {
                // Display the message immediately to the right (for the current user)
                HBox sentMessageHBox = new HBox(10);
                Label sentMessageLabel = new Label(message);
                sentMessageLabel.setStyle("-fx-background-color: #aad4f5; -fx-padding: 10px; -fx-background-radius: 15px;");
                sentMessageHBox.setAlignment(Pos.CENTER_RIGHT);
                sentMessageHBox.getChildren().add(sentMessageLabel);

                // Add to the message list immediately
                messageListView.getItems().add(sentMessageHBox);

                // Scroll the view to show the latest sent message
                messageListView.scrollTo(messageListView.getItems().size() - 1);

                // Save the sent message to the database
                dbHandler.saveMessage(accName.getText(), selectedUser, message);

                // Clear the input field after sending the message
                messageInput.clear();
            }
        });

        playButton.setOnAction(e->{
            String selectedUser = userListView.getSelectionModel().getSelectedItem();
            playMatchWFriend(selectedUser);
        });



        // Add ListView and Message Interface to SplitPane
        splitPane.getItems().addAll(userListView, messagePane);
        splitPane.setDividerPositions(0.3); // Adjust the ratio of ListView and messagePane

        // Scene and Stage Setup
        Scene scene = new Scene(splitPane, 700, 500);
        scene.getStylesheets().add(getClass().getResource("/style.css").toExternalForm()); // Optional CSS for styling
        scene.getRoot().setStyle("-fx-background-radius: 0; -fx-border-radius: 0;");
        messageStage.setScene(scene);
        messageStage.initModality(Modality.APPLICATION_MODAL);
        messageStage.setOnCloseRequest(e -> timeline.stop()); // Stop polling on window close
        messageStage.showAndWait();
    }



//    private void updateMessages(ListView<HBox> messageListView, String currentUser, String chatUser) {
//        List<Message> messages = dbHandler.getMessagesBetweenUsers(currentUser, chatUser);
//        messageListView.getItems().clear();  // Clear old messages
//
//        for (Message message : messages) {
//            HBox messageHBox = new HBox(10);
//
//            // Determine where to align the message (left or right)
//            if (message.getSender().equals(currentUser)) {
//                // Sent message (right side)
//                Label sentMessageLabel = new Label(message.getContent());
//                sentMessageLabel.setStyle("-fx-background-color: #309dd6; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: black;");
//                messageHBox.setAlignment(Pos.CENTER_RIGHT);
//                messageHBox.getChildren().add(sentMessageLabel);
//            } else {
//                // Received message (left side)
//                Label receivedMessageLabel = new Label(message.getContent());
//                receivedMessageLabel.setStyle("-fx-background-color: #213840; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: white;");
//                messageHBox.setAlignment(Pos.CENTER_LEFT);
//                messageHBox.getChildren().add(receivedMessageLabel);
//            }
//
//            // Add the message to the list
//            messageListView.getItems().add(messageHBox);
//        }
//
//        // Scroll to the bottom of the chat
//        messageListView.scrollTo(messageListView.getItems().size() - 1);
//    }
    private void updateMessages(ListView<HBox> messageListView, String currentUser, String chatUser) {
        // Store the current scroll position (vvalue) of the ListView
        ScrollBar scrollBar = (ScrollBar) messageListView.lookup(".scroll-bar:vertical");
        double scrollPosition = (scrollBar != null) ? scrollBar.getValue() : 0;

        // Retrieve the new messages from the database
        List<Message> messages = dbHandler.getMessagesBetweenUsers(currentUser, chatUser);
        messageListView.getItems().clear(); // Clear the previous messages

        // Add the new messages to the ListView
        for (Message message : messages) {
            HBox messageHBox = new HBox(10);

            if (message.getSender().equals(currentUser)) {
                // Sent message (align to the right)
                Label sentMessageLabel = new Label(message.getContent());
                sentMessageLabel.setStyle("-fx-background-color: #309dd6; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: black;");
                messageHBox.setAlignment(Pos.CENTER_RIGHT);
                messageHBox.getChildren().add(sentMessageLabel);
            } else {
                // Received message (align to the left)
                Label receivedMessageLabel = new Label(message.getContent());
                receivedMessageLabel.setStyle("-fx-background-color: #213840; -fx-background-radius: 15; -fx-padding: 10; -fx-text-fill: white;");
                messageHBox.setAlignment(Pos.CENTER_LEFT);
                messageHBox.getChildren().add(receivedMessageLabel);
            }

            messageListView.getItems().add(messageHBox); // Add message to ListView
        }

        // If user was near the bottom (or at the bottom), ensure ListView scrolls to the bottom
        if (scrollPosition >= 0.95) {
            // Scroll to the bottom of the chat automatically
            messageListView.scrollTo(messageListView.getItems().size() - 1);
        } else {
            // Restore the previous scroll position (if the user was not at the bottom)
            if (scrollBar != null) {
                scrollBar.setValue(scrollPosition); // Restore scroll position
            }
        }
    }

    public void playMatch(ActionEvent event) {

//        stage.setScene(loadingScene);
//        stage.setOnCloseRequest(closeEvent  -> {
//            System.out.println("Window closed!");
//        });
//        stage.show();

        Platform.runLater(() -> {
            try {
                com.example.game_basic.HelloApplication.setUsername(accName.getText()); // Set username before starting
                Stage gameStage = new Stage();
                new com.example.game_basic.HelloApplication().start(gameStage);

                com.example.game_basic.HelloApplication.MainCall();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }



    public void playMatchWFriend(String friend_user){
        stage.setScene(loadingScene);
        stage.setOnCloseRequest(closeEvent  -> {
            System.out.println("Window closed!");
        });
        stage.show();
    }

}
