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
package uk.wu.kh.graphproject.visualmap;

import java.util.ArrayList;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextBoundsType;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.visualmap.fxobjects.Anchor;
import uk.wu.kh.graphproject.visualmap.fxobjects.BoundLine;

/**
 *
 * @author kai
 */
public class visualMapBuilder {

    //Needed for CSV impl.
    private static final int startX = 100;
    private static final int startY = 100;
    private static final int distanceX = 100;
    private static final int distanceY = 100;

    private static int anchorX;
    private static int anchorY;
    private static int vertexDisplayableSquareSize;
    private static int rowIndex = 0;
    private static int columnIndex = 0;

    public static void buildMap() {

        cleanList();

        //Save the x by x size for the placement of the vertexes.
        vertexDisplayableSquareSize = getVertexDisplayableSquareSize(ProjectConstants.vertexList.size());

        for (Vertex vertex : ProjectConstants.vertexList) {
            System.out.println("MELDUNG");

            rowIndex = (ProjectConstants.visualMapList.size() / vertexDisplayableSquareSize) + 1;
            anchorY = rowIndex * distanceY;
            System.out.println("VertexID: " + vertex.getUniqueId() + ". Row Index: " + rowIndex);

            //X coordinates
            columnIndex = (ProjectConstants.visualMapList.size() - ((rowIndex - 1) * vertexDisplayableSquareSize)) + 1;
            anchorX = columnIndex * distanceX;

            if (ProjectConstants.isGraphEditable && (vertex.getX() != 0) && (vertex.getY() != 0)) {
                anchorX = vertex.getX();
                anchorY = vertex.getY();
            }
            Anchor anchor = new Anchor(
                    //Color.BLUE,
                    Color.color(Math.random(), Math.random(), Math.random()),
                    new SimpleDoubleProperty(anchorX),
                    new SimpleDoubleProperty(anchorY));

            anchor.setId(String.valueOf(vertex.getUniqueId()));

            vertex.setAnchor(anchor);

            System.out.println(anchorX + ", " + anchorY);

            Text text = new Text(Character.toString(vertex.getLetter()));
            text.setBoundsType(TextBoundsType.VISUAL);

            text.setStyle(
                    "-fx-font-family: \"Times New Roman\";"
                    + "-fx-font-style: italic;"
                    + "-fx-font-size: 16px;"
            );

            text.xProperty().bind(anchor.centerXProperty());
            text.yProperty().bind(anchor.centerYProperty());
            Group fr = new Group(anchor, text);

            ProjectConstants.visualMapList.add(fr);
        }

        removeDoubleEdges();

        for (Vertex vertex : ProjectConstants.vertexList) {

            for (Edge edge : vertex.getEdgeList()) {
                if (ProjectConstants.edgeList.contains(edge)) {
                    BoundLine boundLine = new BoundLine(
                            vertex.getAnchor().centerXProperty(),
                            vertex.getAnchor().centerYProperty(),
                            edge.getEdgeEndVertex().getAnchor().centerXProperty(),
                            edge.getEdgeEndVertex().getAnchor().centerYProperty(),
                            edge
                    );
                    edge.setBoundLine(boundLine);
                    edge.getBoundLine().setText(String.valueOf(edge.getWeight()) + "\n" + edge.getPheromone());

                    Group lineGroup = new Group(boundLine, edge.getBoundLine().getText());

                    ProjectConstants.visualMapList.add(lineGroup);
                }
            }
        }
    }

    /**
     * This function was created and used before the removeDoubleEdges void was
     * created.
     *
     * @deprecated
     */
    @Deprecated
    private static void buildEdges() {
        for (Vertex vertex : ProjectConstants.vertexList) {

            for (Edge edge : vertex.getEdgeList()) {
                BoundLine boundLine = new BoundLine(
                        vertex.getAnchor().centerXProperty(),
                        vertex.getAnchor().centerYProperty(),
                        edge.getEdgeEndVertex().getAnchor().centerXProperty(),
                        edge.getEdgeEndVertex().getAnchor().centerYProperty(),
                        edge
                );
                edge.setBoundLine(boundLine);
                ProjectConstants.visualMapList.add(boundLine);
            }
        }
    }

    /**
     * Important for removing double displayed edges if two vertexes are
     * connected twice. Leaves the list without duplicates.
     */
    private static void removeDoubleEdges() {

        ArrayList<Edge> removeList = new ArrayList<>();

        for (Vertex vertex : ProjectConstants.vertexList) {
            for (Edge edge : vertex.getEdgeList()) {
                ProjectConstants.edgeList.add(edge);

            }
        }

        for (Edge edge : ProjectConstants.edgeList) {
            for (Edge edge1 : ProjectConstants.edgeList) {

                if (edge1 != edge && !removeList.contains(edge)) {
                    if (edge1.getEdgeStartVertex() == edge.getEdgeEndVertex()
                            && edge1.getEdgeEndVertex() == edge.getEdgeStartVertex()) {

                        if (!removeList.contains(edge1)) {
                            removeList.add(edge1);
                        }
                        break;
                    }
                }
            }

        }

        for (Edge edge : removeList) {
            ProjectConstants.edgeList.remove(edge);
        }
        for (Edge edge : ProjectConstants.edgeList) {
            System.out.println(
                    "Start: "
                    + edge.getEdgeStartVertex().getLetter()
                    + " end: "
                    + edge.getEdgeEndVertex().getLetter()
            );
        }
        System.out.println(ProjectConstants.edgeList.size());
    }

    private static void cleanList() {
        //Clear the list if its not empty.
        if (ProjectConstants.visualMapList == null) {
            ProjectConstants.visualMapList = new ArrayList<>();
        } else {
            if (!ProjectConstants.visualMapList.isEmpty()) {
                ProjectConstants.visualMapList.clear();
            }
        }
        if (ProjectConstants.edgeList == null) {
            ProjectConstants.edgeList = new ArrayList<>();
        } else {
            if (!ProjectConstants.edgeList.isEmpty()) {
                ProjectConstants.edgeList.clear();
            }
        }
    }

    /**
     * Displays the Vertexes in a squarelike format if no coordinates existing.
     *
     * @param listLength
     * @return
     */
    private static int getVertexDisplayableSquareSize(int listLength) {
        //The smallest possible square is 2 by 2.
        int x = 2;

        while (true) {
            if (((x * x) - listLength) >= 0) {
                //The listLength fits into this x * x square!
                break;
            }
            x++;
        }
        return x;
    }
}
