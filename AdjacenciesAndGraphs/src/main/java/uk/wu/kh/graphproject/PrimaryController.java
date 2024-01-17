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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.filereader.JSONReader;
import uk.wu.kh.graphproject.visualmap.visualMapBuilder;

/**
 * The controller classes work together with the FXML files in the resource
 * folder to create, display and manage the JavaFX windows. GUI elements like
 * buttons can be connected with a void with an @FXML tag. The GUI elements need
 * to have the same fxmlid string like the the variables declared in the
 * controller class to provide access.
 * <p>
 * The primary controller is connected to the graph view window. During its
 * initialisation the Vertexes and Edge lists are read in from a resource file.
 * The visualmapbuilder creates JavaFX displayable objects wich are designed to
 * completely visualize the graph.
 * </p>
 *
 * @see visualMapBuilder
 * @see Edge
 * @see Vertex
 * @see FXML
 * @author kai
 */
public class PrimaryController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    /**
     * JavaFX native void in wich necessary statements take place before
     * displaying the window to the user.
     *
     * @see Initializable
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //TODO: REMOVE
        //Old CSV testing import code. Marked for removal.
//        CSVReader reader = new CSVReader();
//
//        ProjectConstants.vertexList = reader.readVertexFromFile(ProjectConstantsEnum.DEFAULT_CSV_FILE_LOCATION.label);
//
//        visualMapBuilder.buildMap();
//        ancPane.getChildren().add(new Group(ProjectConstants.visualMapList));
//
//
        JSONReader reader = new JSONReader();
        ProjectConstants.vertexList = reader.readVertexFromFile(ProjectConstantsEnum.DEFAULT_JSON_FILE_LOCATION.label);

        //TODO: REMOVE
        //Old method to verify consistency of read in vertexes and edges through
        //console output.
//        for (Vertex vertex : ProjectConstants.vertexList) {
//            System.out.println(vertex.getUniqueId() + " " + vertex.getLetter() + " . " + vertex.getEdgeList().size());
//
//            for (Edge ad : vertex.getEdgeList()) {
//                System.out.println("END LETTER: " + ad.getEdgeEndVertex().getLetter());
//            }
//
//        }
        visualMapBuilder.buildMap();
        //Makes all JavaFX objects in ProjectConstants.visualMapList visible to
        //the user.
        ancPane.getChildren().add(new Group(ProjectConstants.visualMapList));
    }

    //TODO: Implement position-textfile save void.
    /**
     * Simple output of all vertex positions for storing the coordinates. Should
     * be replaced with a position-textfile save void.
     */
    @FXML
    private void printPos() {
        for (Vertex vrt : ProjectConstants.vertexList) {
            System.out.println(
                    "Id: " + vrt.getUniqueId()
                    + " lttr: " + vrt.getLetter()
                    + " x: " + vrt.getAnchor().centerXProperty().doubleValue()
                    + " y: " + vrt.getAnchor().centerYProperty().doubleValue()
            );
        }
    }

    @FXML
    private void close() {
        Platform.exit();
    }
}
