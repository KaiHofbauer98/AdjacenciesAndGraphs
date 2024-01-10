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
import uk.wu.kh.graphproject.adjacency.Edge;
import uk.wu.kh.graphproject.adjacency.Vertex;

/**
 * In this class only public static objects should be stored to achieve easy and
 * consistent access to its values. They are globally needed in the project.
 *
 * @author kai
 */
public class ProjectConstants {

    /**
     * The file object storing the coordinates in the visual map of the Vertex
     * and adjancies.
     */
    public static File VISUAL_MAP_SAVE;

    /**
     * The file object which the raw unfiltered inforamtion for the Vertexes and
     * Adjancies come from.
     */
    public static File INFO_READ_IN;

    public static ArrayList<Vertex> vertexList;

    public static ArrayList<Edge> edgeList;

    public static ArrayList<Node> visualMapList;

    public static boolean isGraphEditable = false;

}
