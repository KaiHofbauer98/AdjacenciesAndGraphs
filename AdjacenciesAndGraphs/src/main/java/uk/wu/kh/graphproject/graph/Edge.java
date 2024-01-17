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

import uk.wu.kh.graphproject.tsp.aco.FemaleWorkerAnt;
import uk.wu.kh.graphproject.visualmap.fxobjects.BoundLine;

/**
 * A Edge is a connection between two Vertexes or Nodes.
 *
 * @see Vertex
 * @author kai.hofbauer
 */
public class Edge {

    /**
     * The weight is specified in the read in graph file. It symbolizes the grit
     * usage for a winter road. The more grit usage is needed the more likely
     * the gasoline usage and/or time needed is increased too. For this reason,
     * when edges needs to be driven multiple times on, to complete the graph
     * tour with all edges visited, always the shortest should be used multiple
     * times to reduce costs.
     */
    private final double weight;

    /**
     * The pheromones are part of the ACO algorithm implemented. The ants will
     * follow the edges with more pheromones more likeley.
     *
     * @see FemaleWorkerAnt
     * @see uk.wu.kh.graphproject.tsp.aco
     */
    private double pheromone = 0;

    /**
     * This value is needed to check if a visual update for a label is required
     * due to change between checks. This is implemented for JFX performance
     * reasons. (When updating the BoundLines labels and line designs.)
     *
     * @see BoundLine
     */
    private double pheromoneVisualUpdate = 0;

    /**
     * Each edge in the graph example has two connected Vertexes. (The value of
     * one of the Vertexes could also be null to symbolize an Edge with just one
     * Vertex on one end.)
     */
    private final Vertex edgeStartVertex;
    private final Vertex edgeEndVertex;

    /**
     * Every Edge object holds its own refernce to its own BoundLine object
     * which is the JFX implementation to make a Edge displayable, scalable and
     * movable on screen.
     * 
     * @see BoundLine
     */
    private BoundLine boundLine;

    /**
     * Some vlaues would not change, for this reason they will be stored during
     * Object construction.
     *
     * @param weight
     * @param edgeStartVertex
     * @param edgeEndVertex
     */
    public Edge(double weight, Vertex edgeStartVertex, Vertex edgeEndVertex) {
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

    public double getPheromoneVisualUpdate() {
        return pheromoneVisualUpdate;
    }

    public void setPheromoneVisualUpdate(double pheromoneVisualUpdate) {
        this.pheromoneVisualUpdate = pheromoneVisualUpdate;
    }
}
