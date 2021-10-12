import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public interface Help_Buttons_Function {
    //about
    default void about() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("This text editor was created by Chang Yin and Yansheng Ma.");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().add(label);
        Scene scene = new Scene(vBox,500,80);
        stage.setTitle("About...");
        stage.setScene(scene);
        stage.showAndWait();
    }
    //issue
    default void issue() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);stage.initModality(Modality.APPLICATION_MODAL);
        Label label1 = new Label("If you have some problems with the text editor, ");
        Label label2= new Label("please send an email to yinchang0303@outlook.com");
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label1, label2);
        Scene scene = new Scene(vBox,500,80);
        stage.setTitle("Issue...");
        stage.setScene(scene);
        stage.showAndWait();
    }
}
