import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.*;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public interface File_Buttons_Function {

    //new
    static String newfile(TextArea area, Frame frame){
        String fileName = "";
        String s = "";
        String newFileName = savefile(s, frame, fileName);
        area.setText(null);
        return newFileName;
    }
    //open
    static String openfile(File file){
        char[] chars = new char [(int)file.length()];
        try {
            BufferedReader BR = new BufferedReader(new FileReader(file));
            BR.read(chars);
            BR.close();
        }
        catch(FileNotFoundException FE){
            System.out.println("file not found");
            System.exit(0);
        }
        catch(IOException IE){
            System.out.println("IO error");
            System.exit(0);
        }
        String s =new String (chars);
        return s;
    }
    //save
    static String savefile(String s,Frame frame,String fileName){
        if(fileName.equals("")){
            FileDialog FD = new FileDialog(frame,"save file",FileDialog.SAVE);
            FD.setVisible(true);
            try {
                File file = new File(FD.getDirectory() + FD.getFile());
                fileName = FD.getDirectory() + FD.getFile();
                BufferedWriter BW = new BufferedWriter(new FileWriter(file));
                BW.write(s, 0, s.length());
                BW.close();
            }
            catch(FileNotFoundException FE){
                System.out.println("file not found");
                System.exit(0);
            }
            catch( IOException IE) {
                System.out.println("IO error");
                System.exit(0);
            }
        }
        else {
            try {
                File file = new File(fileName);
                BufferedWriter BW = new BufferedWriter(new FileWriter (file));
                BW.write(s, 0, s.length());
                BW.close();

            }
            catch(FileNotFoundException FE){
                System.out.println("file not found");
                System.exit(0);
            }
            catch(IOException IE)
            {
                System.out.println("IO error");
                System.exit(0);
            }
        }
        return fileName;
    }
    //exit
    static void exit() {
        Stage stage = new Stage();
        stage.initModality(Modality.APPLICATION_MODAL);
        Label label = new Label("Do you want to exit?");
        Button yes = new Button("Yes");
        yes.setOnMouseClicked(event -> System.exit(0));
        Button no = new Button("No");
        no.setOnMouseClicked(event -> stage.close());
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(yes,no);
        VBox vBox = new VBox();
        vBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(label, hBox);
        Scene scene = new Scene(vBox,250,80);
        stage.setTitle("Exit...");
        stage.setScene(scene);
        stage.showAndWait();
    }
}