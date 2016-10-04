/*
 * The Greatest Game Ever
 * The Other Guys
 * Version 0.9
 */
package view;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.StackPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * This is a stand-alone application for playing a revolutionary word search 
 * game, the Greatest Game Ever. It plays like the traditional paper and pen
 * game with added pressure of time and only having the ability to find one 
 * target word at a time.
 * @author Mitchell
 */
public class GreatestGameEver extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {

        Parent startLayout = FXMLLoader.load(getClass().getResource("StartScene.fxml"));
        StackPane startScene = new StackPane(startLayout);
        Scene scene = new Scene(startScene);
        
        Rectangle2D visualBounds = Screen.getPrimary().getVisualBounds();
        primaryStage.setScene(scene);
        primaryStage.setFullScreen(true);
        primaryStage.setX(visualBounds.getMinX());
        primaryStage.setY(visualBounds.getMinY());
        primaryStage.setWidth(visualBounds.getWidth());
        primaryStage.setHeight(visualBounds.getHeight());
        primaryStage.setTitle("The Greatest Game Ever");
        primaryStage.getIcons().add(new Image("/files/GreatestGame.jpg"));
        
        // Exit the application and kill all threads if the window is closed.
        primaryStage.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
            System.exit(0);
        });
        
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
