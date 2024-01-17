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

import uk.wu.kh.graphproject.tsp.aco.formicidae.Formicidae;

/**
 * Abstraction of voids of rules for ACO from Wikipedia:
 * 
 * <p>
 * Visualization of the ant colony algorithm applied to the travelling salesman
 * problem. The green lines are the paths chosen by each ant. The blue lines are
 * the paths it may take at each point. When the ant finishes, the pheromone
 * levels are represented in red. It must visit each city exactly once; A
 * distant city has less chance of being chosen (the visibility); The more
 * intense the pheromone trail laid out on an edge between two cities, the
 * greater the probability that that edge will be chosen; Having completed its
 * journey, the ant deposits more pheromones on all edges it traversed, if the
 * journey is short; After each iteration, trails of pheromones evaporate.
 * Source: https://en.wikipedia.org/wiki/Ant_colony_optimization_algorithms
 * </p>
 *
 * @author kai
 */
public interface ACO {

    abstract void visitEveryCityExactlyOnce(Formicidae formicidae);

    abstract void chooseEdgeOnWeight(Formicidae formicidae);

    abstract void chooseEdgeOnPheromones(Formicidae formicidae);

    abstract void depositPheromonesOnShortRoute(Formicidae formicidae);//Not fully implemented yet!

    abstract boolean checkIfRouteIsShort(Formicidae formicidae);//Not implemented yet!

    abstract void pheromoneEvaporation(); //Not implemented yet!

}
