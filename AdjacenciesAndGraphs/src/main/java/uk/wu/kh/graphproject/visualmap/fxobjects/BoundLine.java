/*
 * The MIT License
 *
 * Copyright 2023 kai.
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
package uk.wu.kh.graphproject.visualmap.fxobjects;

import java.text.DecimalFormat;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.constants.ProjectConstants;

/**
 * This class is needed to implement all behaviour needed for displaying action
 * while ACO is running.
 *
 * @author kai
 */
public class BoundLine extends Line {//implements PropertyChangeListener {

    /**
     * Holds reference to its own Edge for accessibility reasons.
     *
     * @see Edge
     */
    private final Edge edge;

    /**
     * JFX Text wich will be used to display information for each BoundLine.
     */
    private final Text text = new Text();

    /**
     * This listeners listen to changes in the BoundLines DoubleProperties.
     */
    private static ChangeListener listnerx;
    private static ChangeListener listnery;

    /**
     * Needed to shorten the pheromone values shown on the BoundLine
     *
     * <p>
     * https://stackoverflow.com/questions/8137218/trim-double-to-2-decimal-places
     * </p>
     */
    DecimalFormat df = new DecimalFormat("#.##");

    /**
     * Each BoundLine has four DoubleProperty values which are dynamically
     * updating when moved. They are connected to the belonging Vertex X and Y
     * DoubleProperties.
     */
    private DoubleProperty startX;
    private DoubleProperty startY;
    private DoubleProperty endX;
    private DoubleProperty endY;

    //This is a codeblock which will be executed once during object construction.
    {
        if (ProjectConstants.isGraphEditable) {

            //This listeners listen to changes in the BoundLines DoubleProperties.
            //They adjust the Text position to the BoundLine dynamically.
            listnerx = new ChangeListener() {
                @Override
                public void changed(ObservableValue ov, Object t, Object t1) {
                    setTextX();
                }
            };//Reminder: (startX.doubleValue() + endX.doubleValue()) / 2

            listnery = new ChangeListener() {
                @Override
                public void changed(ObservableValue ov, Object t, Object t1) {
                    setTextY();
                }
            };//Reminder: (startX.doubleValue() + endX.doubleValue()) / 2

        }
        this.text.setBoundsType(TextBoundsType.VISUAL);
        this.text.setStyle(
                "-fx-font-family: \"Times New Roman\";"
                + "-fx-font-style: italic;"
                + "-fx-font-size: 16px;"
        );

    }

    public BoundLine(DoubleProperty startX, DoubleProperty startY, DoubleProperty endX, DoubleProperty endY, Edge edge) {
        this.edge = edge;
        //Bind own line start and end to the position of the vertexes Double 
        //properties.
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        //Setting initial style
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.3));
        setStrokeLineCap(StrokeLineCap.ROUND);
        getStrokeDashArray().setAll(10.0, 5.0);
        setMouseTransparent(true);
        //Stores Doubleproperties for observation through listnerx and listnery 
        //listeners.
        this.startX = startX;
        this.endX = endX;
        this.startY = startY;
        this.endY = endY;

        if (ProjectConstants.isGraphEditable) {
            startXProperty().addListener(listnerx);
            endXProperty().addListener(listnerx);
            startYProperty().addListener(listnery);
            endYProperty().addListener(listnery);
        }
        setTextX();
        setTextY();
    }

    /**
     * JavaFX UI performance settings Because the methodcall is so extrordinary
     * high, the updating of the UI needs to be done slower to avoid crashes
     * with JavaFX.
     */
    public void update() {
        //Update the edges pheromone visually in text and line appereance form
        double pheromone = edge.getPheromone();
        this.text.setText(
                String.valueOf(edge.getWeight())
                + "\n"
                + String.valueOf(df.format(pheromone))
        );

        if (pheromone > 1) {
            // black and thick

            setStrokeWidth(3);
            setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.6));
            getStrokeDashArray().setAll();

        } else {
            if (pheromone < 1 || pheromone > 0.5) {
                //grey and through

                //The pheromones are way too thin in pixels so they get 
                //multiplied by 100
                setStrokeWidth(pheromone * 100);
                setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
                getStrokeDashArray().setAll();
            } else {
                //grey and dashed

                setStrokeWidth(2);
                setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.3));
                getStrokeDashArray().setAll(10.0, 5.0);
            }
        }
    }

    /**
     * Moves the text to the X middle of the BoundLine (/2) The + 1 moves the
     * Textfield one pixel more away from the line due to better visibility.
     */
    private void setTextX() {
        text.setX(((startX.doubleValue() + endX.doubleValue()) / 2) + 1);
    }

    /**
     * Moves the text to the Y middle of the BoundLine (/2).
     */
    private void setTextY() {
        text.setY((startY.doubleValue() + endY.doubleValue()) / 2);
    }

    public Text getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }
}
