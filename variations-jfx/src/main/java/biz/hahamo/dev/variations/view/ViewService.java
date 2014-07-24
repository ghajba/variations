package biz.hahamo.dev.variations.view;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import biz.hahamo.dev.variations.controller.LoginService;

/**
 * Service to load / assemble views for the application
 * 
 * @author GHajba
 * 
 */
public class ViewService {

    private final LoginService loginService;

    public ViewService(LoginService loginService) {
        this.loginService = loginService;
    }

    public Stage getLoginStage(Window owner) {
        GridPane grid = createDefaultGrid();

        Text scenetitle = new Text("Please log in to access the database!");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        Label userName = new Label("Login:");
        grid.add(userName, 0, 1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label pw = new Label("Password:");
        grid.add(pw, 0, 2);

        final PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        final Button btn = new Button("Sign in");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(btn);
        grid.add(hbBtn, 1, 4);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, 6);

        btn.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.setFill(Color.FIREBRICK);
                String loginText = "Username or password is wrong!";
                if (loginService.authenticateCredentials(userTextField.getText(), DigestUtils.sha512Hex(pwBox.getText()))) {
                    loginText = "Success";
                    LoginService.logInUser();
                    Stage stage = (Stage) btn.getScene().getWindow();
                    stage.close();
                }
                actiontarget.setText(loginText);
            }
        });

        Scene scene = new Scene(grid, 500, 275);
        return createStage(owner, scene, "Login");
    }

    public Stage getUserCreation(Window owner, String optionalMessage) {
        GridPane grid = createDefaultGrid();
        
        Text optionalMessageToDisplay = new Text(optionalMessage);
        int index = 0;
        if(!StringUtils.isBlank(optionalMessage)) {
            grid.add(optionalMessageToDisplay, 0, 1);
            index = 1;
        }

        Text scenetitle = new Text("Create a user (all fields are required)");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        final Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, index+1);

        final TextField userTextField = new TextField();
        grid.add(userTextField, 1, index+1);

        final Label emailLabel = new Label("Email address:");
        grid.add(emailLabel, 0, index+2);

        final TextField emailTextField = new TextField();
        grid.add(emailTextField, 1, index+2);

        final Label loginLabel = new Label("Login:");
        grid.add(loginLabel, 0, index+3);

        final TextField loginTextField = new TextField();
        grid.add(loginTextField, 1, index+3);

        final Label passwordLabel = new Label("Password:");
        grid.add(passwordLabel, 0, index+4);

        final PasswordField passwordField = new PasswordField();
        grid.add(passwordField, 1, index+4);

        final Label passwordLabel2 = new Label("Repeat password:");
        grid.add(passwordLabel2, 0, index+5);

        final PasswordField passwordField2 = new PasswordField();
        grid.add(passwordField2, 1, index+5);

        final Button button = new Button("Create user");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(button);
        grid.add(hbBtn, 1, index+6);

        final Text actiontarget = new Text();
        grid.add(actiontarget, 1, index+7);

        button.setOnAction(new EventHandler<ActionEvent>() {

            public void handle(ActionEvent e) {
                actiontarget.getStyleClass().add("errorField");
                if (!validate()) {
                    actiontarget.setText("There are validation failures.");
                    return;
                }
                if (userExists()) {
                    actiontarget.setText("Login already exists!");
                    return;
                }
                Long userId = loginService.createUser(userTextField.getText(), loginTextField.getText(),
                        DigestUtils.sha512Hex(passwordField.getText()), emailTextField.getText());

                if (userId == null) {
                    actiontarget.setText("Could not create user. Try again later.");
                    return;
                }
                actiontarget.setText("Success!");
                Stage stage = (Stage) button.getScene().getWindow();
                stage.close();

            }

            private boolean userExists() {
                return loginService.findUserByLogin(loginTextField.getText()) != null;
            }

            private boolean validate() {
                boolean valid = true;
                if (StringUtils.isBlank(userTextField.getText())) {
                    nameLabel.setTooltip(new Tooltip("Name must not be empty!"));
                    nameLabel.getStyleClass().add("errorField");
                    valid = false;
                }
                if (StringUtils.isBlank(emailTextField.getText())) {
                    emailLabel.setTooltip(new Tooltip("Email address must not be empty!"));
                    emailLabel.getStyleClass().add("errorField");
                    valid = false;
                }
                if (StringUtils.isBlank(loginTextField.getText())) {
                    loginLabel.setTooltip(new Tooltip("Login must not be empty!"));
                    loginLabel.getStyleClass().add("errorField");
                    valid = false;
                }
                if (StringUtils.isBlank(passwordField.getText())) {
                    passwordLabel.setTooltip(new Tooltip("Password must not be empty!"));
                    passwordLabel.getStyleClass().add("errorField");
                    valid = false;
                }
                if (StringUtils.isBlank(passwordField2.getText())) {
                    passwordLabel2.setTooltip(new Tooltip("Password must not be empty!"));
                    passwordLabel2.getStyleClass().add("errorField");
                    valid = false;
                }
                if (!StringUtils.isBlank(passwordField.getText()) && !StringUtils.isBlank(passwordField2.getText())) {
                    if (!passwordField.getText().equals(passwordField2.getText())) {
                        passwordLabel2.setTooltip(new Tooltip("The two passwords are not the same!"));
                        passwordLabel2.getStyleClass().add("errorField");
                        valid = false;
                    }
                }
                return valid;
            }
        });
        Scene scene = new Scene(grid, 600, 400);
        scene.getStylesheets().add(ViewService.class.getResource("Common.css").toExternalForm());
        
        return createStage(owner, scene, "Create user");
    }

    private GridPane createDefaultGrid() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));
        return grid;
    }

    public Scene getMainScene() {

        final VBox vBox = new VBox();
        Rectangle2D bounds = Screen.getPrimary().getVisualBounds();
        Scene scene = new Scene(vBox, bounds.getWidth(), bounds.getHeight());
        scene.getStylesheets().add(ViewService.class.getResource("Common.css").toExternalForm());
        final Window owner = vBox.getScene().getWindow();
        MenuBar menuBar = new MenuBar();
        menuBar.setUseSystemMenuBar(true);

        final Menu userManagementMenu = new Menu("User management");
        final Menu vehicleMenu = new Menu("Vehicle management");
        final Menu routeMenu = new Menu("Route management");
        final Menu driverMenu = new Menu("Driver management");
        final Menu shippingNoteMenu = new Menu("Shipping note management");
        
        final MenuItem loginMenuItem = new MenuItem("Login");

        loginMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                Stage loginStage = getLoginStage(owner);

                if (loginService.getAllUsers().isEmpty()) {
                    getUserCreation(loginStage, "There are no users in the database.");
                }
            }
        });
        loginMenuItem.setVisible(!LoginService.isUserLoggedIn());

        final MenuItem logoutMenuItem = new MenuItem("Logout");
        logoutMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                LoginService.logOutUser();
            }
        });
        
        final MenuItem createUserMenuItem = new MenuItem("Create a user");
        createUserMenuItem.setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent t) {
                getUserCreation(owner, null);
            }
        });
        final MenuItem findUserMenuItem = new MenuItem("Find user");
        
        userManagementMenu.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                loginMenuItem.setVisible(!LoginService.isUserLoggedIn());
                logoutMenuItem.setVisible(LoginService.isUserLoggedIn());
                createUserMenuItem.setDisable(!LoginService.isUserLoggedIn());
                findUserMenuItem.setDisable(!LoginService.isUserLoggedIn());
            }
        });
        logoutMenuItem.setVisible(LoginService.isUserLoggedIn());

        userManagementMenu.getItems().addAll(loginMenuItem, createUserMenuItem, findUserMenuItem, logoutMenuItem);
        
        final MenuItem createDriver = new MenuItem("Create driver");
        createDriver.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
                createDriver(owner);
            }
        });
        final MenuItem findDriver = new MenuItem("Find driver");
        driverMenu.getItems().addAll(createDriver, findDriver);
        driverMenu.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                createDriver.setDisable(!LoginService.isUserLoggedIn());
                findDriver.setDisable(!LoginService.isUserLoggedIn());
            }
        });
        
        final MenuItem createVehicle = new MenuItem("Create vehicle");
        final MenuItem findVehicle = new MenuItem("Find vehicle");
        vehicleMenu.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                createVehicle.setDisable(!LoginService.isUserLoggedIn());
                findVehicle.setDisable(!LoginService.isUserLoggedIn());
            }
        });
        vehicleMenu.getItems().addAll(createVehicle, findVehicle);
        
        final MenuItem createRoute = new MenuItem("Create route");
        final MenuItem findRoute = new MenuItem("Find route");
        routeMenu.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                createRoute.setDisable(!LoginService.isUserLoggedIn());
                findRoute.setDisable(!LoginService.isUserLoggedIn());
            }
        });
        routeMenu.getItems().addAll(createRoute, findRoute);
        
        final MenuItem createShippingNote = new MenuItem("Create shipping note");
        final MenuItem findShippingNote = new MenuItem("Find shipping note");
        shippingNoteMenu.setOnShowing(new EventHandler<Event>() {
            @Override
            public void handle(Event arg0) {
                createShippingNote.setDisable(!LoginService.isUserLoggedIn());
                findShippingNote.setDisable(!LoginService.isUserLoggedIn());
            }
        });
        shippingNoteMenu.getItems().addAll(createShippingNote, findShippingNote);

        menuBar.getMenus().addAll(userManagementMenu, vehicleMenu, routeMenu, driverMenu, shippingNoteMenu);
        vBox.getChildren().addAll(menuBar);

        return scene;
    }
    
    public Stage createDriver(Window owner) {
        final GridPane grid = createDefaultGrid();
        Scene scene = new Scene(grid, 400, 300);
        scene.getStylesheets().add(ViewService.class.getResource("Common.css").toExternalForm());
        
        Text scenetitle = new Text("Create a new driver˝");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);

        final Label nameLabel = new Label("Name:");
        grid.add(nameLabel, 0, 1);

        final TextField nameTextField = new TextField();
        grid.add(nameTextField, 1, 1);
        
        final Label drivingLicenceLabel = new Label("Driving licence number:");
        grid.add(drivingLicenceLabel, 0, 2);

        final TextField drivingLicenceTextField = new TextField();
        grid.add(drivingLicenceTextField, 1, 2);
        
        final Label licenceCategoryLabel = new Label("Driving licence category:");
        grid.add(licenceCategoryLabel, 0, 3);

        final TextField licenceCategoryField = new TextField();
        grid.add(licenceCategoryField, 1, 3);
        
        final Label licenceExpiresLabel = new Label("Driving licence expires:");
        grid.add(licenceExpiresLabel, 0, 4);
        
        final DatePicker licenceExpiresPicker = new DatePicker();
        grid.add(licenceExpiresPicker, 1, 4);

        final Button saveButton = new Button("Save");
        HBox hbBtn = new HBox(10);
        hbBtn.setAlignment(Pos.BOTTOM_RIGHT);
        hbBtn.getChildren().add(saveButton);
        grid.add(hbBtn, 1, 5);
        
        saveButton.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent arg0) {
                ((Stage)grid.getScene().getWindow()).close();
                
            }
        });
        
        return createStage(owner, scene, "Create driver");
    }
    
    private Stage createStage(Window owner, Scene scene, String title) {
        Stage stage = new Stage(StageStyle.DECORATED);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.setTitle(title);
        stage.initOwner(owner);
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        return stage;
    }
}
