import com.lowagie.text.DocumentException;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.awt.*;
import java.awt.print.PrinterException;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Initial extends Print_Function implements Help_Buttons_Function, Search_Function, File_Buttons_Function {
    String fileName = "";
    Frame frame;
    //Create text entry
    public static TextArea area = new TextArea();
    public static TextArea getArea() {
        area.setEditable(true);
        area.setWrapText(true);
        area.setPrefColumnCount(50);
        area.setPrefRowCount(100);
        return area;
    }

    //menu buttons
    Menu File = new Menu("File");
    Menu Search = new Menu("Search");
    Menu View = new Menu("View");
    Menu Manage = new Menu("Manage");
    Menu Help = new Menu("Help");
    MenuBar menuBar = new MenuBar();
    //add them to menu
    public MenuBar getMenuBar() {
        menuBar.getMenus().addAll(getFile(), getSearch(), getView(), getManage(), getHelp());
        return menuBar;
    }

    //file buttons
    MenuItem New = new MenuItem("New");
    MenuItem Open = new MenuItem("Open");
    MenuItem Save = new MenuItem("Save");
    MenuItem Exit = new MenuItem("Exit");
    MenuItem Savepdf=new MenuItem("Save as pdf");
    //save as pdf
    public  MenuItem getSavepdf(){
        Savepdf.setOnAction(event -> {
                    File file=new File("src\\main\\resources\\print.txt");
                    try {
                        FileOutputStream out=new FileOutputStream(file);
                        byte text[]=getArea().getText().replaceAll("\n","\r\n").getBytes(StandardCharsets.UTF_8);
                        out.write(text);
                        out.close();
                    } catch (Exception e){e.printStackTrace();}


                    try {
                        text2pdf("src\\main\\resources\\print.txt","src\\main\\resources\\print.pdf");
                    } catch (DocumentException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();}

                    });
        return Savepdf;
    }
    //new
    public MenuItem getNew() {
        New.setOnAction(event -> fileName = File_Buttons_Function.newfile(getArea(), frame));
        return New;
    }
    //open
    public MenuItem getOpen() {
        Open.setOnAction(event -> {
            FileDialog FD = new FileDialog(frame,"open file",FileDialog.LOAD);
            FD.setVisible(true);
            java.io.File file = new File(FD.getDirectory() + FD.getFile());
            fileName = FD.getDirectory() + FD.getFile();
            area.setText(File_Buttons_Function.openfile(file));
        });
        return Open;
    }
    //save
    public MenuItem getSave() {
        Save.setOnAction(event -> {
            String s = area.getText();
            fileName = File_Buttons_Function.savefile(s, frame, fileName);
        });
        return Save;
    }
    //exit
    public MenuItem getExit() {
        Exit.setOnAction(event -> {
            File_Buttons_Function.exit();
        });
        return Exit;
    }
    //add them to file
    public Menu getFile() {
        File.getItems().addAll(getNew(), getOpen(), getSave(), getExit(),getSavepdf());
        return File;
    }

    //search buttons
    MenuItem search = new MenuItem("Search");
    public MenuItem getsearch() {
        search.setOnAction(event -> {
            Stage stage = new Stage();
            stage.initModality(Modality.APPLICATION_MODAL);
            Label label = new Label("Please enter the content you want to search: ");
            TextField text = new TextField();
            Button button = new Button("Search it");
            button.setOnMouseClicked(event1 -> {
                String info = Search_Function.search(getArea().getText(), text.getText());
                if (info.equals("Cannot find it! ")) {
                    Stage stage1 = new Stage();
                    stage1.initModality(Modality.APPLICATION_MODAL);
                    Label label1 = new Label("Cannot find it! ");
                    VBox vBox1 = new VBox();
                    vBox1.setAlignment(Pos.CENTER);
                    vBox1.getChildren().addAll(label1);
                    Scene scene1 = new Scene(vBox1,100,50);
                    stage1.setScene(scene1);
                    stage1.show();
                }
            });
            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(text, button);
            VBox vBox = new VBox();
            vBox.setAlignment(Pos.CENTER);
            vBox.getChildren().addAll(label, hBox);
            Scene scene = new Scene(vBox,500,80);
            stage.setTitle("Search...");
            stage.setScene(scene);
            stage.showAndWait();
        });
        return search;
    }
    public Menu getSearch() {
        Search.getItems().add(getsearch());
        return Search;
    }

    //view buttons
    MenuItem Code = new MenuItem("Code");
    public MenuItem getCode() {
        Code.setOnAction(event -> {
            StringBuilder all = new StringBuilder(getArea().getText());
            new Print_Function().init(all);
        });
        return Code;
    }
    public Menu getView() {
        View.getItems().addAll(getCode());
        return View;
    }
    //manage buttons
    MenuItem Copy = new MenuItem("Copy");
    MenuItem Paste = new MenuItem("Paste");
    MenuItem Cut = new MenuItem("Cut");
    MenuItem Print = new MenuItem("Print");
    StringBuilder temp;
    //cut
    public MenuItem getCut() {
        Cut.setOnAction(event -> {
            temp = new StringBuilder(getArea().getSelectedText());
            StringBuilder all = new StringBuilder(getArea().getText());
            int start = getArea().getSelection().getStart();
            int len = getArea().getSelectedText().length();
            all.delete(start,start+len);
            getArea().setText(all.toString());
        });
        return Cut;
    }
    //copy
    public MenuItem getCopy() {
        Copy.setOnAction(event -> {
            temp = new StringBuilder(getArea().getSelectedText());
        });
        return Copy;
    }
    //paste
    public MenuItem getPaste() {
        Paste.setOnAction(event -> {
            StringBuilder all = new StringBuilder(getArea().getText());
            int start = getArea().getSelection().getStart();
            all.insert(start, temp);
            getArea().setText(all.toString());
        });
        return Paste;
    }
    //print
    public MenuItem getPrint() {
        Print.setOnAction(event -> {
            File file=new File("src\\main\\resources\\print.txt");
            try {
                FileOutputStream out=new FileOutputStream(file);
                byte text[]=getArea().getText().replaceAll("\n","\r\n").getBytes(StandardCharsets.UTF_8);
                out.write(text);
                out.close();
            } catch (Exception e){e.printStackTrace();}


            try {
                text2pdf("src\\main\\resources\\print.txt","src\\main\\resources\\print.pdf");
            } catch (DocumentException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                printPdf("src\\main\\resources\\print.pdf");
            } catch (IOException e) {
                e.printStackTrace();
            } catch (PrinterException e) {
                e.printStackTrace();
            }


        });
        return Print;
    }
    //add them to manage
    public Menu getManage() {
        Manage.getItems().addAll(getCopy(), getPaste(), getCut(),getPrint());
        return Manage;
    }

    //help buttons
    MenuItem About = new MenuItem("About");
    MenuItem Issue = new MenuItem("Issue");
    //about
    public MenuItem getAbout() {
        About.setOnAction(event -> about());
        return About;
    }
    //issue
    public MenuItem getIssue() {
        Issue.setOnAction(event -> issue());
        return Issue;
    }
    //add them to help
    public Menu getHelp() {
        Help.getItems().addAll(getAbout(), getIssue());
        return Help;
    }

    public Scene Initial() {
        VBox vBox = new VBox();
        vBox.getChildren().addAll(getMenuBar(), getArea());
        Scene scene = new Scene(vBox, 1200, 800);
        return scene;
    }
}