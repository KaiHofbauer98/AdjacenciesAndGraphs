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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javafx.beans.property.DoubleProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.StrokeLineCap;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import uk.wu.kh.graphproject.adjacency.Edge;
import uk.wu.kh.graphproject.constants.ProjectConstants;

/**
 *
 * @author kai
 */
public class BoundLine extends Line implements PropertyChangeListener {

    private final Edge edge;

    private Text text = new Text();

    private static ChangeListener listnerx;

    private static ChangeListener listnery;

    private DoubleProperty startX;
    private DoubleProperty startY;
    private DoubleProperty endX;
    private DoubleProperty endY;

    {
        if (ProjectConstants.isGraphEditable) {

            listnerx = new ChangeListener() {
                @Override
                public void changed(ObservableValue ov, Object t, Object t1) {
                    setTextX();
                }
            };//(startX.doubleValue() + endX.doubleValue()) / 2

            listnery = new ChangeListener() {
                @Override
                public void changed(ObservableValue ov, Object t, Object t1) {
                    setTextY();
                }
            };//(startX.doubleValue() + endX.doubleValue()) / 2

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
        startXProperty().bind(startX);
        startYProperty().bind(startY);
        endXProperty().bind(endX);
        endYProperty().bind(endY);
        setStrokeWidth(2);
        setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.3));
        setStrokeLineCap(StrokeLineCap.ROUND);
        getStrokeDashArray().setAll(10.0, 5.0);
        setMouseTransparent(true);
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
        init();
    }

    private void init() {
        edge.getSupp().addPropertyChangeListener(this);
    }

    private void setTextX() {
        text.setX(((startX.doubleValue() + endX.doubleValue()) / 2) + 1);
    }

    private void setTextY() {
        text.setY((startY.doubleValue() + endY.doubleValue()) / 2);
    }

    public Text getText() {
        return text;
    }

    public void setText(String text) {
        this.text.setText(text);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        //Update the edges pheromone visually in text and line appereance form
        this.text.setText(
                String.valueOf(edge.getWeight())
                + "\n"
                + String.valueOf(evt.getNewValue())
        );

        if (((double) evt.getNewValue()) > 1) {
            // black and thickkk
            setStrokeWidth(3);
            setStroke(Color.BLACK.deriveColor(0, 1, 1, 0.6));
            getStrokeDashArray().setAll();

        } else {
            if (((double) evt.getNewValue()) < 1 || ((double) evt.getNewValue()) > 0.5) {
                //through

                setStrokeWidth(2);
                setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.5));
                getStrokeDashArray().setAll();
            } else {
                //grey and gestrichelt

                setStrokeWidth(2);
                setStroke(Color.GRAY.deriveColor(0, 1, 1, 0.3));
                getStrokeDashArray().setAll(10.0, 5.0);
            }
        }
    }

}
