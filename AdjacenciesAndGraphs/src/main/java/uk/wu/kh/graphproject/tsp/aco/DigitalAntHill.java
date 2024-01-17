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
package uk.wu.kh.graphproject.tsp.aco;

import java.util.ArrayList;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.tsp.aco.formicidae.AntHill;

/**
 * Adds a reference to a Vertex and introduce the antOutputRate wich will let
 * the ants slower out of the anthill. The routeset is a List of lists of
 * antviews. A collection of routes that successfully completed the symmetric
 * TSP.
 *
 * @author kai
 */
public class DigitalAntHill extends AntHill {

    private final Vertex vertex;
    private final int antOutputRate = Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.label);
    
    //TODO: WORK ON ROUTESET
    /**
     * Not used yet.
     */
    private final ArrayList<ArrayList<AntView>> routeSet = new ArrayList<>();

    public DigitalAntHill(Vertex vertex) {
        this.vertex = vertex;
    }

    public Vertex getVertex() {
        return vertex;
    }

    public int getAntOutputRate() {
        return antOutputRate;
    }

    public void addRouteToRouteSet(ArrayList<AntView> set) {
        routeSet.add(set);
    }

    public ArrayList<ArrayList<AntView>> getRouteSet() {
        return routeSet;
    }

}
