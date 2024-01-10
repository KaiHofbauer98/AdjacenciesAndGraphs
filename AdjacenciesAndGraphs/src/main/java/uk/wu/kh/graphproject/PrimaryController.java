//<editor-fold defaultstate="collapsed" desc="This File has been generated through the creation of the Project in NetBeans IDE 19 09.11.2023">

/*
Original File Content:
package uk.wu.kh.graphproject;

import java.io.IOException;
import javafx.fxml.FXML;

public class PrimaryController {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
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

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Group;
import javafx.scene.layout.AnchorPane;
import uk.wu.kh.graphproject.adjacency.Edge;
import uk.wu.kh.graphproject.adjacency.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.filereader.JSONReader;
import uk.wu.kh.graphproject.tsp.aco.ACOController;
import uk.wu.kh.graphproject.visualmap.visualMapBuilder;

public class PrimaryController implements Initializable {

    @FXML
    private AnchorPane ancPane;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

//        DoubleProperty startX = new SimpleDoubleProperty(40);
//        DoubleProperty startY = new SimpleDoubleProperty(100);
//        DoubleProperty endX = new SimpleDoubleProperty(300);
//        DoubleProperty endY = new SimpleDoubleProperty(200);
//        DoubleProperty endXX = new SimpleDoubleProperty(400);
//        DoubleProperty endYY = new SimpleDoubleProperty(300);
//
//        start = new Anchor(Color.PALEGREEN, startX, startY);
//
//        Anchor end = new Anchor(Color.TOMATO, endX, endY);
//        Anchor tester = new Anchor(Color.CORAL, endXX, endYY);
//
//        Line line = new BoundLine(startX, startY, endX, endY);
//        Line linee = new BoundLine(endX, endY, endXX, endYY);
//        
//        ArrayList<Node> er = new ArrayList<>();
//        
//        er.add(start);
//        er.add(end);
//        er.add(tester);
//        er.add(line);
//        er.add(linee);
        //stage.setScene(new Scene(new Group(linee, line, start, end, tester), 400, 400, Color.ALICEBLUE));
//        ancPane.getChildren().add(new Group(er));
//        CSVReader reader = new CSVReader();
//
//        ProjectConstants.vertexList = reader.readVertexFromFile(ProjectConstantsEnum.DEFAULT_CSV_FILE_LOCATION.label);
//
//        for (Vertex vertex : ProjectConstants.vertexList) {
//            System.out.println(vertex.getUniqueId() + " . " + vertex.getAdjancyList().size());
//
//        }
////        visualMapBuilder.buildMap();
//        visualMapBuilder.build_test_map();
//        ancPane.getChildren().add(new Group(ProjectConstants.visualMapList));
//
//        AntColonyOptimization aco = new AntColonyOptimization();
//        aco.init();
        JSONReader reader = new JSONReader();
        ProjectConstants.vertexList = reader.readVertexFromFile(ProjectConstantsEnum.DEFAULT_JSON_FILE_LOCATION.label);

        for (Vertex vertex : ProjectConstants.vertexList) {
            System.out.println(vertex.getUniqueId() + " " + vertex.getLetter() + " . " + vertex.getEdgeList().size());

            for (Edge ad : vertex.getEdgeList()) {
                System.out.println("END LETTER: " + ad.getEdgeEndVertex().getLetter());
            }

        }

        visualMapBuilder.buildMap();
        ancPane.getChildren().add(new Group(ProjectConstants.visualMapList));

        System.out.println("RUNNED");
        
        ACOController aco = new ACOController();
        aco.init();
        aco.runThreads();

    }

    @FXML
    private void change_color() {
////        start.setFill(Color.BLACK);
//        File savefile;
//
//        //Adding action on the menu item
//        savefile = App.fileSaveDialogFile();
//
//        try {
//            ObjectReaderAndSaver.writeObject(savefile, ProjectConstants.vertexList);
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        }

        for (Vertex vrt : ProjectConstants.vertexList) {
            System.out.println(
                    "Id: " + vrt.getUniqueId()
                    + " lttr: " + vrt.getLetter() +
                            " x: " + vrt.getAnchor().centerXProperty().doubleValue() + 
                            " y: " + vrt.getAnchor().centerYProperty().doubleValue()
            );
        }

    }

    @FXML
    private void test() {
//        try {
//            //        System.exit(0);
//
//            ObjectReaderAndSaver.readObject(App.fileOpenDialogFile());
//        } catch (IOException ex) {
//            ex.printStackTrace();
//        } catch (ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }

        for (Edge edge : ProjectConstants.edgeList) {
            edge.setPheromone(2);
        }

    }

}
