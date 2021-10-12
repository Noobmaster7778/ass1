import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;


public interface Search_Function {
    static String search(String all, String s) {
        if (all.contains(s)) {
            Print_Function.search(all,s);
            return "got it! ";
        }
        else {
            return "Cannot find it! ";
        }
    }
}
