/*
 * The MIT License
 *
 * Copyright 2024 kai.
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.realRandom.RandomController;
import uk.wu.kh.graphproject.realRandom.RealRandomLoader;
import uk.wu.kh.graphproject.tsp.aco.ACOController;

/**
 *
 * This JFX Controller is connected with the Graph.fxml file and manages the
 * Symmetric TSP settings window.
 * <p>
 * Controller behaviour is described in the PrimaryController class.
 * </p>
 *
 * @see PrimaryController
 * @see FXML
 * @author kai
 */
public class ACOManagerController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="@FXML Code">
    @FXML
    private Label DEFAULT_ANT_START_AGE_LABEL;
    @FXML
    private Label DEFAULT_MAX_ANT_AGE_ITERATIONS_LABEL;
    @FXML
    private Label DEFAULT_ANTHILL_SIZE_LABEL;
    @FXML
    private Label DEFAULT_ANTHILL_OUTPUT_RATE_LABEL;

    @FXML
    private TextField DEFAULT_ANT_START_AGE_TXFLD;
    @FXML
    private TextField DEFAULT_MAX_ANT_AGE_ITERATIONS_TXFLD;
    @FXML
    private TextField DEFAULT_ANTHILL_SIZE_TXFLD;
    @FXML
    private TextField DEFAULT_ANTHILL_OUTPUT_RATE_TXFLD;

    @FXML
    private CheckBox ClearPheromones;
    @FXML
    private CheckBox slowdowncheck;

    @FXML
    private Spinner spinner;
    @FXML
    private Spinner spinnerVertex;

    @FXML
    private Button button_run;
    @FXML
    private Button button_downloadAndUseRealRandomNumbers;
    //</editor-fold>

    /**
     * @see PrimaryController
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Spinner for the slow down in ms functionality.
        spinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(
                Integer.parseInt(ProjectConstantsEnum.DEFAULT_SLOW_DOWN_MIN_MS.label),
                Integer.parseInt(ProjectConstantsEnum.DEFAULT_SLOW_DOWN_MAX_MS.label),
                Integer.parseInt(ProjectConstantsEnum.DEFAULT_SLOW_DOWN_MS.label)
        ));
        spinner.getEditor().setFont(Font.font("System", 18));
        spinner.getEditor().setDisable(true);

        //Spinner for choosing different Vertex to start from. (Set the anthill)
        ObservableList<String> vertexes = FXCollections.observableArrayList();
        for (Vertex vertex : ProjectConstants.vertexList) {
            vertexes.add(Character.toString(vertex.getLetter()));
        }
        spinnerVertex.setValueFactory(
                new SpinnerValueFactory.ListSpinnerValueFactory<>(
                        vertexes
                )
        );
        spinnerVertex.getEditor().setFont(Font.font("System", 18));
        spinnerVertex.getEditor().setDisable(true);

        //Changes the global boolean for slowing down the ants iterations by 
        //delaying each iteration per ant by the selected milliseconds.
        slowdowncheck.setOnAction((t) -> {
            ProjectConstantsEnum.DEFUALT_SLOW_DOWN_BOOLEAN.label = Boolean.toString(slowdowncheck.isSelected());
        });

        //Display constant values to the user
        DEFAULT_ANT_START_AGE_LABEL.setText(ProjectConstantsEnum.DEFAULT_ANT_START_AGE.toString());
        DEFAULT_MAX_ANT_AGE_ITERATIONS_LABEL.setText(ProjectConstantsEnum.DEFAULT_MAX_ANT_AGE_ITERATIONS.toString());
        DEFAULT_ANTHILL_SIZE_LABEL.setText(ProjectConstantsEnum.DEFAULT_ANTHILL_SIZE.toString());
        DEFAULT_ANTHILL_OUTPUT_RATE_LABEL.setText(ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.toString());
        DEFAULT_ANT_START_AGE_TXFLD.setText(ProjectConstantsEnum.DEFAULT_ANT_START_AGE.label);
        DEFAULT_MAX_ANT_AGE_ITERATIONS_TXFLD.setText(ProjectConstantsEnum.DEFAULT_MAX_ANT_AGE_ITERATIONS.label);
        DEFAULT_ANTHILL_SIZE_TXFLD.setText(ProjectConstantsEnum.DEFAULT_ANTHILL_SIZE.label);
        DEFAULT_ANTHILL_OUTPUT_RATE_TXFLD.setText(ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.label);
        slowdowncheck.setSelected(Boolean.parseBoolean(ProjectConstantsEnum.DEFUALT_SLOW_DOWN_BOOLEAN.label));

    }

    /**
     * Runs the ACO once with the selected settings.
     *
     */
    @FXML
    private void run_button_void() {
        //It is important to wait until all processes are finished before 
        //starting new ones at this point.
        button_run.setDisable(true);

        //If the user modified the GUI settings, the new values will be stored for global use.
        ProjectConstantsEnum.DEFAULT_ANT_START_AGE.label = DEFAULT_ANT_START_AGE_TXFLD.getText();
        ProjectConstantsEnum.DEFAULT_MAX_ANT_AGE_ITERATIONS.label = DEFAULT_MAX_ANT_AGE_ITERATIONS_TXFLD.getText();
        ProjectConstantsEnum.DEFAULT_ANTHILL_SIZE.label = DEFAULT_ANTHILL_SIZE_TXFLD.getText();
        ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.label = DEFAULT_ANTHILL_OUTPUT_RATE_TXFLD.getText();

        if (ClearPheromones.isSelected()) {
            //Cleans all pheromones on all edges for a fresh start, 
            //when selected in GUI.
            for (Edge edge : ProjectConstants.edgeList) {
                edge.setPheromone(0);
            }
            ACOController.updateAllEdgeLabels(true);
        }

        ACOController.init(this);
        ACOController.runThreads();
        ACOController.updateFxEdgeLabelsPeriodically();
    }

    /**
     * Fills the integer arrays for real random integers with values from
     * https://random.org please note their rules: https://random.org/clients/
     * To avoid banning from their server there is only one donwload of 15000
     * random integers in total implemented. Therefore the download can be
     * performed only once per program instance.
     *
     * @see RealRandomLoader
     * @see RandomController
     */
    @FXML
    private void downloadAndUseRealRandomNumbers_button_void() {
        if (!RandomController.inited) {
            RandomController.init();
            RandomController.inited = true;
            ProjectConstants.useRealRandomNumbers = true;
            button_downloadAndUseRealRandomNumbers.setDisable(true);
        }
    }

    /**
     * Needed to dynamically change the speed of the ACO to watch the algorithm
     * work.
     *
     * @return Milliseconds from spinner
     */
    public int getSlowDownMS() {
        return (int) spinner.getValue();
    }

    /**
     * Methodcall only when all ACO Threads are finished. Reactivates the run
     * button to run ACO again.
     */
    public void finishedThreads() {
        button_run.setDisable(false);
    }

}
