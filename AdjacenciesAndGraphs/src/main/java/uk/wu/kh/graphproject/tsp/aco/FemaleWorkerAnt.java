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
import java.util.Comparator;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.realRandom.RandomController;
import uk.wu.kh.graphproject.tsp.aco.formicidae.AntHill;
import uk.wu.kh.graphproject.tsp.aco.formicidae.Caste;
import uk.wu.kh.graphproject.tsp.aco.formicidae.Formicidae;
import uk.wu.kh.graphproject.visualmap.fxobjects.TrackAnchor;

/**
 *
 * @author kai
 */
public class FemaleWorkerAnt extends DigitalAnt implements ACO {

    //<editor-fold defaultstate="collapsed" desc="Comparators">
    private static final Comparator edgeWeightComp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return Double.compare(((AntView) o1).getGoalEdge().getWeight(), ((AntView) o2).getGoalEdge().getWeight());
        }
    };

    private static final Comparator pheromoneWeightComp = new Comparator() {
        @Override
        public int compare(Object o1, Object o2) {
            return Double.compare(((AntView) o1).getGoalEdge().getPheromone(), ((AntView) o2).getGoalEdge().getPheromone());
        }
    };
    //</editor-fold>

    private final ArrayList<Vertex> visited = new ArrayList<>();

    private final ArrayList<AntView> antViews = new ArrayList<>();

    private final ArrayList<AntView> antViewList = new ArrayList<>();

    private AntView choosedOnWeight;
    private AntView choosedOnPheromones;

    private int twoIntArrEntryPoint = 0;
    private int threeIntArrEntryPoint = 0;
    private int realRandomArrCounterThree = 0;
    private int realRandomArrCounterTwo = 0;

    public FemaleWorkerAnt(AntHill antHill, ACOThread acoThread) {
        super(false, Caste.FEMALE, antHill, acoThread);
    }

    public void iteration() {
//        currentVertexes.clear();
//        currentEdges.clear();

//        int g = (int) (ProjectConstants.vertexList.size() - (ProjectConstants.vertexList.size() * 0.25));
//        System.out.println("Size " + g);
        //Remove the startpoint to make sure its free!
        if (antViewList.size() > (int) (ProjectConstants.vertexList.size() - 3)) {
            if (visited.contains(((DigitalAntHill) this.getAntHill()).getVertex())) {
                visited.remove(((DigitalAntHill) this.getAntHill()).getVertex());
            }
        }

        visitEveryCityExactlyOnce(this);
        chooseEdgeOnWeight(this);
        chooseEdgeOnPheromones(this);

        for (AntView antView : antViews) {
//            System.out.println("Vertex: " + antView.getVertexGoal().getLetter() + " Weight: " + antView.getGoalEdge().getWeight() + " Pheromones: " + antView.getGoalEdge().getPheromone()
//                    + " choosed on weight?: " + (antView == choosedOnWeight)
//                    + " choosed on pheromones?: " + (antView == choosedOnPheromones)
//            );
        }

        moveAnt();
        if (antViewList.size() == (ProjectConstants.vertexList.size() + 1) && this.getPosition() == ((DigitalAntHill) this.getAntHill()).getVertex() && antViewList.size() > (int) (ProjectConstants.vertexList.size() - 3)) {
            //Way found!
            System.out.println("GOAL visited:" + antViewList.size());

            depositPheromonesOnShortRoute(this);
//            App.getDialog().show("", "");
            ((DigitalAntHill) super.getAntHill()).addRouteToRouteSet(antViewList);
            ((DigitalAnt) this).foundRoute();
        }
    }

    @Override
    public void visitEveryCityExactlyOnce(Formicidae formicidae) {

        antViews.clear();

        visited.add(this.getPosition());

        //Iterate over all existing edges.
        for (Edge edge : ProjectConstants.edgeList) {

            if (edge.getEdgeStartVertex() == this.getPosition() || edge.getEdgeEndVertex() == this.getPosition()) {
                //Edges are connected to the current vertex.

                //are the end or start vertexes already visited?
                if ((visited.contains(edge.getEdgeStartVertex()) && !visited.contains(edge.getEdgeEndVertex()))
                        || (!visited.contains(edge.getEdgeStartVertex()) && visited.contains(edge.getEdgeEndVertex()))) {

//                    currentEdges.add(edge);
                    if (edge.getEdgeStartVertex() != this.getPosition()) {
                        antViews.add(new AntView(edge.getEdgeStartVertex(), edge));
//                        currentVertexes.add(edge.getEdgeStartVertex());
                    }
                    if (edge.getEdgeEndVertex() != this.getPosition()) {
                        antViews.add(new AntView(edge.getEdgeEndVertex(), edge));
//                        currentVertexes.add(edge.getEdgeEndVertex());
                    }
                }
            }
        }

//        for (Vertex currentVertex : currentVertexes) {
//            System.out.println("Nearby Vertexes: " + currentVertex.getLetter());
//        }
//                for (Edge currentEdge : currentEdges) {
//            System.out.println("Nearby edge weight: " + currentEdge.getWeight());
//        }
        //Saves the current vertex into the visited vertexes list.
    }

    private int getRandomArrEntryPoint(int arrSize) {
        int max;
        int min = 0;
        int range = 0;
        if (arrSize == 3) {
            max = RandomController.getThree().length;
            range = max - min + 1;
        }
        if (arrSize == 2) {
            max = RandomController.getTwo().length;
            range = max - min + 1;
        }
        int entryPoint = (int) (Math.random() * range) + min;
        return entryPoint;
    }

    @Override
    public void chooseEdgeOnWeight(Formicidae formicidae) {
        if (ProjectConstants.randomAntBehaviour) {
            if (antViews.isEmpty()) {
                //Nothing can be done.
            } else {
                int max = antViews.size() - 1;
                int min = 0;
                int range = max - min + 1;

                if (ProjectConstants.useRealRandomNumbers) {
                    if (RandomController.inited) {
                        if (antViews.size() == 3) {
                            if (threeIntArrEntryPoint == 0) {
                                threeIntArrEntryPoint = getRandomArrEntryPoint(3);
                            }

                            while (((threeIntArrEntryPoint + realRandomArrCounterThree) > RandomController.getThree().length - 1)) {
                                threeIntArrEntryPoint = getRandomArrEntryPoint(3);
                                realRandomArrCounterThree = 0;
                            }

                            //Using random numbers out of array of 10000 ints
                            //from 0 to 2
                            choosedOnWeight = antViews.get(RandomController.getThree()[(threeIntArrEntryPoint + realRandomArrCounterThree)]);
                            realRandomArrCounterThree++;
                        } else {
                            if (antViews.size() == 2) {
                                if (twoIntArrEntryPoint == 0) {
                                    twoIntArrEntryPoint = getRandomArrEntryPoint(2);
                                }
                                while (((twoIntArrEntryPoint + realRandomArrCounterTwo) > RandomController.getTwo().length - 1)) {
                                    twoIntArrEntryPoint = getRandomArrEntryPoint(2);
                                    realRandomArrCounterTwo = 0;
                                }
                                //Using random numbers out of array of 5000 ints
                                //from 0 to 1
                                choosedOnWeight = antViews.get(RandomController.getTwo()[(twoIntArrEntryPoint + realRandomArrCounterTwo)]);
                                realRandomArrCounterTwo++;
                            } else {
                                //No changes.
                                choosedOnWeight = antViews.get((int) (Math.random() * range) + min);
                            }
                        }
                    }
                } else {
                    //(int)(Math.random() * (antViews.size() - 1 + 1)) + 1;

                    choosedOnWeight = antViews.get((int) (Math.random() * range) + min);
//                    choosedOnWeight = antViews.get((int) (Math.random() * (antViews.size())));
                    //(int)(Math.random() * (max - min) + min)
                }
            }
        } else {
            if (antViews.isEmpty()) {
                //Nothing happens here, in the next method call chooseEdgeOnPheromones
                //The ant will get older. (This means the ant runned into a loop.)
            } else {
                antViews.sort(edgeWeightComp);
                System.out.println(antViews.size());
                choosedOnWeight = antViews.getFirst();
            }
        }
    }

    @Override
    public void chooseEdgeOnPheromones(Formicidae formicidae) {
        if (ProjectConstants.randomAntBehaviour) {
            if (antViews.isEmpty()) {

            } else {
//                //(int)(Math.random() * (antViews.size() - 1 + 1)) + 1;
//                choosedOnPheromones = antViews.get((int) (Math.random() * (antViews.size())));
                antViews.sort(pheromoneWeightComp);
                choosedOnPheromones = antViews.getFirst();
            }
        } else {

            if (antViews.isEmpty()) {
                aging(1);
            } else {
                antViews.sort(pheromoneWeightComp);
                choosedOnPheromones = antViews.getFirst();
            }
        }
    }

    private void moveAnt() {
        Vertex pos = this.getPosition();
        if (choosedOnPheromones == choosedOnWeight) {
            //The selection for both weights are the same!
            this.setPosition(choosedOnPheromones.getVertexGoal());
            antViewList.add(choosedOnPheromones);

        } else {
            if (choosedOnPheromones.getGoalEdge().getPheromone() == 0) {
                //The pheromone and weight selection is different. Follow the weight
                this.setPosition(choosedOnWeight.getVertexGoal());
                antViewList.add(choosedOnWeight);
            } else {
                this.setPosition(choosedOnPheromones.getVertexGoal());
                antViewList.add(choosedOnPheromones);

//                throw new UnsupportedOperationException("ANT LOGIC NOT SUPPORTED");
            }
        }
        if (pos == this.getPosition()) {
            if (ProjectConstants.trackAnt) {

                if (getAntHill().getAntList().get(ProjectConstants.dblPrpty.intValue()) == this) {

                    for (TrackAnchor trackAnch : ProjectConstants.trackList) {
                        trackAnch.setFill(Color.WHITE);
                    }

                    ArrayList<TrackAnchor> e = new ArrayList<>();
                    for (AntView antView : antViewList) {

                        for (TrackAnchor trackAnchor : ProjectConstants.trackList) {
                            if (antView.getVertexGoal() == trackAnchor.getVertex()) {
                                e.add(trackAnchor);
                            }
                        }

//                        antView.getGoalEdge().setPheromone(antView.getGoalEdge().getPheromone() - 0.001);
                    }
                    int i = 0;
                    while (i <=3) {                        
                        for (TrackAnchor trackAnchor : e) {
                            trackAnchor.setFill(Color.WHITE);
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FemaleWorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        for (TrackAnchor trackAnchor : e) {
                            trackAnchor.setFill(Color.YELLOW);
                        }
                        try {
                            Thread.sleep(250);
                        } catch (InterruptedException ex) {
                            Logger.getLogger(FemaleWorkerAnt.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        i++;
                        
                    }
                }

            }
            for (AntView antView : antViewList) {
                antView.getGoalEdge().setPheromone(antView.getGoalEdge().getPheromone() - 0.001);
            }

            this.death();
        } else {
            antViewList.getLast().getGoalEdge().setPheromone(antViewList.getLast().getGoalEdge().getPheromone() + 0.001);

            if (ProjectConstants.trackAnt) {
                if (getAntHill().getAntList().get(ProjectConstants.dblPrpty.intValue()) == this) {
                    for (TrackAnchor trackAnch : ProjectConstants.trackList) {
                        if (trackAnch.getVertex() == getPosition()) {
                            trackAnch.setFill(Color.RED);
                        } else {
                            trackAnch.setFill(Color.WHITE);
                        }
                    }
                }
            }

            aging(1);
//            System.out.println("Moves to: " + this.getPosition().getLetter());
        }
        //            aging((int) choosedOnPheromones.getGoalEdge().getWeight());

    }

    @Override
    public boolean checkIfRouteIsShort(Formicidae formicidae) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void depositPheromonesOnShortRoute(Formicidae formicidae) {
//        for (Edge edge : ProjectConstants.edgeList) {
//            edge.setPheromone(0);
//        }
        for (AntView antView : antViewList) {
            antView.getGoalEdge().setPheromone(2);
        }

    }

    @Override
    public void pheromoneEvaporation() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
