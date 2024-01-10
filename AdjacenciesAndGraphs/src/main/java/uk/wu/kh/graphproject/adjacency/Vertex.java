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
package uk.wu.kh.graphproject.adjacency;

import java.io.Serializable;
import java.util.ArrayList;
import uk.wu.kh.graphproject.visualmap.fxobjects.Anchor;

/**
 * asd
 *
 * @author kai.hofbauer
 */
public class Vertex implements Serializable {

    private final int uniqueId;
    private Anchor anchor;
    private String displayableInformation;
    private ArrayList<Edge> edgeList = new ArrayList<>();
    private int X;
    private int Y;
    private char letter;

    public Vertex(int uniqueId) {
        this.uniqueId = uniqueId;
    }

    public Vertex(int uniqueId, String displayableInformation) {
        this.uniqueId = uniqueId;
        this.displayableInformation = displayableInformation;
    }

    public int getUniqueId() {
        return uniqueId;
    }

    public String getDisplayableInformation() {
        return displayableInformation;
    }

    public void setDisplayableInformation(String displayableInformation) {
        this.displayableInformation = displayableInformation;
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