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

import uk.wu.kh.graphproject.visualmap.fxobjects.BoundLine;
import java.beans.PropertyChangeSupport;

/**
 *
 * R
 *
 * @author kai.hofbauer
 */
public class Edge {

    private final PropertyChangeSupport supp;

    private final double weight;
    private double pheromone = 0;
    private final Vertex edgeStartVertex;
    private final Vertex edgeEndVertex;
    private BoundLine boundLine;

    public Edge(double weight, Vertex edgeStartVertex, Vertex edgeEndVertex) {
        this.supp = new PropertyChangeSupport(this);
        this.weight = weight;
        this.edgeStartVertex = edgeStartVertex;
        this.edgeEndVertex = edgeEndVertex;
    }

    public double getWeight() {
        return weight;
    }

    public double getPheromone() {
        return pheromone;
    }

    public void setPheromone(double pheromone) {
        supp.firePropertyChange("pheromone", this.pheromone, pheromone);
        this.pheromone = pheromone;
    }

    public Vertex getEdgeEndVertex() {
        return edgeEndVertex;
    }

    public Vertex getEdgeStartVertex() {
        return edgeStartVertex;
    }

    public BoundLine getBoundLine() {
        return boundLine;
    }

    public void setBoundLine(BoundLine boundLine) {
        this.boundLine = boundLine;
    }

    public PropertyChangeSupport getSupp() {
        return supp;
    }

}
