import javafx.application.Application;
import javafx.stage.Stage;
import java.text.SimpleDateFormat;
import java.util.Date;

public class App extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        Initial initial = new Initial();
        primaryStage.setScene(initial.Initial());
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            File_Buttons_Function.exit();
        });
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        StringBuilder info = new StringBuilder();
        info.append("From Chang Yin & Yansheng Ma   ");
        info.append(formatter.format(date));
        primaryStage.setTitle(info.toString());
        primaryStage.show();
    }
}
