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
package uk.wu.kh.graphproject.constants;

import java.io.File;
import java.util.ArrayList;
import javafx.scene.Node;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.realRandom.RandomController;
import uk.wu.kh.graphproject.realRandom.RealRandomLoader;

/**
 * In this class only public static objects should be stored to achieve easy and
 * consistent access to its values.
 *
 * @author kai
 */
public class ProjectConstants {

    /**
     * The file object storing the coordinates in the visual map of the Vertex
     * and adjancies. (When needed not implemented jet!)
     */
    public static File VISUAL_MAP_SAVE;

    /**
     * The file object which the raw unfiltered information for the Vertexes and
     * Adjancies come from.
     */
    public static File INFO_READ_IN;

    /**
     * Holds references to all Vertexes, read in from CSV or JSON File.
     */
    public static ArrayList<Vertex> vertexList;

    /**
     * Holds references to all Edges, read in from CSV or JSON File.
     */
    public static ArrayList<Edge> edgeList;

    /**
     * Holds references to all JavaFX onjects needed to display the Vertexes and
     * Edges to the user. The Node class allows adding the whole ArrayList of
     * Node objecs to a JavaFX scene.
     *
     * @see Node
     */
    public static ArrayList<Node> visualMapList;

    /**
     * Switches movability of Vertexes on screen. (+ dynamic scaling of Edges
     * and labels)
     */
    public static boolean isGraphEditable = true;

    /**
     * Switches if ants should use random values to naivgate through the graph.
     */
    public static boolean randomAntBehaviour = true;

    /**
     * Switches the use of real random numbers from https://random.org
     *
     * @see RandomController
     * @see RealRandomLoader
     */
    public static boolean useRealRandomNumbers = false;

}
