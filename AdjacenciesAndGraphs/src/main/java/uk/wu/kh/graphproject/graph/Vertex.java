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
package uk.wu.kh.graphproject.graph;

import java.util.ArrayList;
import uk.wu.kh.graphproject.visualmap.fxobjects.Anchor;

/**
 * A Vertex or Node is a point in a graph where a unknown amount of edges
 * connect.
 *
 * https://en.wikipedia.org/wiki/Vertex_(graph_theory)
 *
 * @see Edge
 * @author kai.hofbauer
 */
public class Vertex {

    /**
     * For unique identification every Vertex has his own uniqueId which is an
     * integer in this case.
     */
    private final int uniqueId;

    /**
     * The JFX related Anchor class is used for visualization. Every Vertex
     * object holds a reference to his own Anchor. The Anchor can be accessed
     * over its Vertex.
     *
     * @see Anchor
     */
    private Anchor anchor;

    /**
     * Every Vertex can have a unknown amount of connected edges. Therefore
     * every Vertex has its own ArrayList of edges.
     *
     * @see Edge
     */
    private ArrayList<Edge> edgeList = new ArrayList<>();

    /**
     * Variable in wich the coordinates are saved into during readig from graph
     * file. The value is used later when creating the Anchors.
     *
     * @see Anchor
     */
    private int X;

    /**
     * Variable in wich the coordinates are saved into during readig from graph
     * file. The value is used later when creating the Anchors.
     *
     * @see Anchor
     */
    private int Y;

    /**
     * The assignment includes a graph example with characters. This attribute
     * is implemented here so every Vertex has its own character.
     */
    private char letter;

    public Vertex(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public ArrayList<Edge> getEdgeList() {
        return edgeList;
    }

    public void setEdgeList(ArrayList<Edge> adjancyList) {
        this.edgeList = adjancyList;
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public int getX() {
        return X;
    }

    public void setX(int X) {
        this.X = X;
    }

    public int getY() {
        return Y;
    }

    public void setY(int Y) {
        this.Y = Y;
    }

    public char getLetter() {
        return letter;
    }

    public void setLetter(char letter) {
        this.letter = letter;
    }
}
