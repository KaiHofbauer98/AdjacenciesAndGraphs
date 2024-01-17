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
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.wu.kh.graphproject.ACOManagerController;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.constants.ProjectConstants;

/**
 * The ACOThreads are created, stored in a list and executed.
 *
 * @author kai
 */
public class ACOController {

    private static ArrayList<Thread> ACOThreadList;

    private static Thread watchThread;

    private static ACOThread singleThread;
    private static ACOManagerController acoMC;

    public static void init(ACOManagerController acoMC) {
        ACOController.acoMC = acoMC;
        if (ACOThreadList == null) {
            ACOThreadList = new ArrayList<>();
        }
        if (!ACOThreadList.isEmpty()) {
            ACOThreadList.clear();
        }

        //TODO: WORK ON MULTIPLE THREADS
        //This creates a thread for every Vertex. Not going well yet. No good results.
        //        for (Vertex vertex : ProjectConstants.vertexList) {
        //            ACOThreadList.add(new Thread(new ACOThread(new DigitalAntHill(vertex), acoMC)));
        //        }
        
        //The single thread is working well.
        singleThread = new ACOThread(new DigitalAntHill(ProjectConstants.vertexList.get(0)), acoMC);
        ACOThreadList.add(new Thread(singleThread));
    }

    /**
     * Start all Threads in ACOThreadList for asynchronous, multithreaded work.
     */
    public static void runThreads() {
        for (Thread aCOThread : ACOThreadList) {
            aCOThread.start();
        }
    }

    /**
     * The watchthread keeps the Edge information visually up to date while
     * saving JFX performance while reducing the visual pheromone updates to a
     * minimum a human can notice with his eyes.
     */
    public static void updateFxEdgeLabelsPeriodically() {
        watchThread = new Thread(() -> {
            while (true) {

                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ACOManagerController.class.getName()).log(Level.SEVERE, null, ex);
                }
                updateAllEdgeLabels(false);
                int counter = 0;
                for (Thread thread : ACOThreadList) {
                    if (thread.isAlive()) {
                        counter++;
                    }
                }
                if (counter == 0) {
                    updateAllEdgeLabels(true);
                    acoMC.finishedThreads();
                    break;
                }
            }
        });
        watchThread.start();

    }

    /**
     * Access for all Edges visual information update.
     * @param force 
     */
    public static void updateAllEdgeLabels(boolean force) {

        for (Edge edge : ProjectConstants.edgeList) {
            if (force) {
                edge.getBoundLine().update();
            } else {
                if (edge.getPheromoneVisualUpdate() != edge.getPheromone()) {
                    //updateUILines
                    edge.getBoundLine().update();
                }
            }
        }
    }
}
