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

import java.io.File;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import javafx.application.Platform;
import javafx.stage.WindowEvent;
//import javafx.stage.FileChooser;

//JavaFX App
/**
 * This class with its start() void is the entry point for JavaFX. JavafX is the
 * visualization platform wich is used in this project to display the fast
 * updating pheromone values visual and textual to the user. The use of the
 * JavaFX Application class and compiler settings (pom.xml) resulting in a
 * project without need for the main() void.
 *
 * @see Application
 * @author kai
 */
public class App extends Application {

    private static Scene scene_graph;
    private static Scene scene_ACOManager;
    private static Stage stage_graph;
    private static Stage stage_ACOManager;
//    private static FileChooser fileChooser = new FileChooser();
    private static JavaFXSimpleDialog dialog = new JavaFXSimpleDialog();

    /**
     *
     * Starts the JavaFX application.
     * <p>
     * The fxml files wich are containing the visual layout of the jfx Windows
     * will be loaded. The Stage.show() void finally displays the window/s.
     * </p>
     *
     * @param stage
     * @throws IOException
     */
    @Override
    public void start(Stage stage) throws IOException {
        //Make sure, the user will get informed about occurig exceptions because
        //the exception will be shown in my JavaFX dialog call.
        Thread.setDefaultUncaughtExceptionHandler(new Thread.UncaughtExceptionHandler() {
            @Override
            public void uncaughtException(Thread t, Throwable e) {
                //Displays the uncaught exception to the user.
                JavaFXSimpleDialog.show("Error", e.getMessage());
            }
        });

        scene_graph = new Scene(loadFXML("Graph"));
        stage.setScene(scene_graph);
        stage.setTitle("Graph view");
        stage_graph = stage;
        stage_graph.setOnCloseRequest((WindowEvent t) -> {
            Platform.exit();
        });

        scene_ACOManager = new Scene(loadFXML("ACOManager"));
        stage_ACOManager = new Stage();
        stage_ACOManager.setScene(scene_ACOManager);
        stage_ACOManager.setTitle("Symmetric TSP Round Trip Settings");
        //Calling these window showing voids at the end of all initializaton!
        stage.show();
        stage_ACOManager.show();

    }

    /**
     * Native JavaFX void for loading FXML files out of compiled and uncompiled
     * projects.
     *
     * @param fxml
     * @return
     * @throws IOException
     */
    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

//    public static void main(String[] args) {
//        launch();//JavaFX Application start.
//    }
    /**
     * Saving information about the coordinates of the Vertexes after the user
     * moved it would be nice. But its just too much unnecessary work at this
     * point.
     *
     * @return
     */
    public static File fileSaveDialogFile() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return fileChooser.showSaveDialog(stage_graph);
    }

    /**
     * Saving information about the coordinates of the Vertexes after the user
     * moved it would be nice. But its just too much unnecessary work at this
     * point.
     *
     * @return
     */
    public static File fileOpenDialogFile() {
        throw new UnsupportedOperationException("Not supported yet.");
//        return fileChooser.showOpenDialog(stage_graph);
    }

    /**
     * My JavaFXSimpleDialog class can be utilizied with its show method to show
     * important information to the user. As an example its used to show errors.
     *
     * @return Simple, informative message window.
     */
    public static JavaFXSimpleDialog getDialog() {
        return dialog;
    }
}
