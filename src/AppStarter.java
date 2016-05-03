import ch.isitar.oop2.projectOscar.view.MovieUIFX;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * the application starter
 */
public class AppStarter extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent rootPanel = new MovieUIFX();
        Scene scene = new Scene(rootPanel, 400, 250);
        primaryStage.setTitle("Movies");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * starts the program
     *
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        System.setProperty("prism.lcdtext", "false");
        launch(args);
    }
}
