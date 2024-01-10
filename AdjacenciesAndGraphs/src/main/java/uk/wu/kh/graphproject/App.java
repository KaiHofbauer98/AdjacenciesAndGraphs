//<editor-fold defaultstate="collapsed" desc="This File has been generated through the creation of the Project in NetBeans IDE 19 09.11.2023">

/*
Original File Content:
package uk.wu.kh.graphproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


//JavaFX App

public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.show();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        launch();
    }

}
 */
//</editor-fold>
/*
 * The MIT License
 *
 * Copyright 2023 kai.hofbauer.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package uk.wu.kh.graphproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
//import javafx.stage.FileChooser;

//JavaFX App
/**
 * This class is the entry point for javafx.
 *
 * @author kai
 */
public class App extends Application {

    private static Scene scene;
//    private static FileChooser fileChooser = new FileChooser();
    private static Stage stage_one;
//    private static JavaFXSimpleDialog dialog = new JavaFXSimpleDialog();

    /**
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {

//        stage.setTitle("Hello World!");
//        Button btn = new Button();
//        btn.setText("Say 'Hello World'");
//        btn.setOnAction(new EventHandler<ActionEvent>() {
// 
//            @Override
//            public void handle(ActionEvent event) {
//                System.out.println("Hello World!");
//            }
//        });
//        
//        StackPane root = new StackPane();
//        root.getChildren().add(btn);
//        stage.setScene(new Scene(root, 300, 250));
//        stage.show();

        var modRef = App.class.getModule()
                .getLayer()
                .configuration()
                .findModule(App.class.getModule().getName())
                .orElseThrow()
                .reference();
        System.out.printf("Module Location = %s%n%n", modRef.location().orElseThrow());

        var dataUrl = App.class.getResource("/uk/wu/kh/graphproject");
        var fileUrl = App.class.getResource("/uk/wu/kh/graphproject/Map.JSON");
        System.out.printf("Data URL = %s%nFile URL = %s%n%n", dataUrl, fileUrl);

        scene = new Scene(loadFXML("primary"), 640, 480);
        stage.setScene(scene);
        stage.setTitle("Line Manipulation Sample");
        stage_one = stage;
        stage.show();

    }

    private static Parent loadFXML(String fxml) throws IOException {

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
//        fileChooser.setTitle("Save");
//        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("All Files", "*.*"));

        var modRef = App.class.getModule()
                .getLayer()
                .configuration()
                .findModule(App.class.getModule().getName())
                .orElseThrow()
                .reference();
        System.out.printf("Module Location = %s%n%n", modRef.location().orElseThrow());

        var fileUrl = App.class.getResource("/uk/wu/kh/graphproject");
        var dataUrl = App.class.getResource("/uk/wu/kh/graphproject/CSV.csv");
        System.out.printf("Data URL = %s%nFile URL = %s%n%n", dataUrl, fileUrl);

        launch();

    }

//    public static File fileSaveDialogFile() {
//        return fileChooser.showSaveDialog(stage_one);
//    }
//
//    public static File fileOpenDialogFile() {
//        return fileChooser.showOpenDialog(stage_one);
//    }
//
//    public static JavaFXSimpleDialog getDialog() {
//        return dialog;
//    }
}
