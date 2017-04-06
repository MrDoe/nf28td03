import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class TD03Application extends Application {
	public TD03Application() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		launch(args);
	}

	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("resources/TD03.fxml"));
//		Controller controller = new Controller(root);

		Scene scene = new Scene(root, 605, 526);

		stage.setTitle("FXML Welcome");
		stage.setScene(scene);
		stage.show();
	}

}
