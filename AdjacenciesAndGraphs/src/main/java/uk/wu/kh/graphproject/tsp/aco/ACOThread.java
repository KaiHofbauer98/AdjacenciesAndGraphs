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

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.paint.Color;
import uk.wu.kh.graphproject.ACOManagerController;
import uk.wu.kh.graphproject.constants.ProjectConstants;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.visualmap.fxobjects.TrackAnchor;

/**
 *
 * @author kai
 */
public class ACOThread implements Runnable {

    private final DigitalAntHill digitalAntHill;
    private QueenAnt queen;
    private boolean run = true;

    private final ACOManagerController acoMC;

    public ACOThread(DigitalAntHill digitalAntHill, ACOManagerController acoMC) {
        this.digitalAntHill = digitalAntHill;
        this.acoMC = acoMC;
        init();
    }

    private void init() {
        //Create the queen from scratch.
        queen = new QueenAnt(digitalAntHill, this);
        digitalAntHill.getAntList().add(queen);
        digitalAntHill.setQueen(queen);
        //Fill up the anthill with as much ants as specified in project constants.
        for (int i = 1; i <= Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANTHILL_SIZE.label); i++) {
            queen.giveBirthtoWorker(digitalAntHill, this);
//            digitalAntHill.getAntList().getLast().setPosition(digitalAntHill.getVertex());
//            System.out.println(
//                    digitalAntHill.getAntList().getLast().getAge() + " " + digitalAntHill.getAntList().getLast().getPosition().getLetter()
//            );
        }
        System.out.println("Anthill size: " + digitalAntHill.getAntList().size());
    }

    @Override
    public void run() {
        int counter = 0;
        while (run && digitalAntHill.getAntList().size() > 1) {

            //
            for (int i = counter; i < counter + Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.label); i++) {
                if ((digitalAntHill.getAntList().size() - 1) >= i) {
                    digitalAntHill.getAntList().get(i).setOutOfHill(true);
                }

            }
            counter = counter + Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANTHILL_OUTPUT_RATE.label);

            for (DigitalAnt digitalAnt : digitalAntHill.getAntList()) {
                if (run) {

                    if (digitalAnt.isOutOfHill() && !digitalAnt.isDead()) {

                        if (digitalAnt instanceof FemaleWorkerAnt) {

                            if (Boolean.parseBoolean(ProjectConstantsEnum.DEFUALT_SLOW_DOWN_BOOLEAN.label)) {

                                try {
//                            //Female worker ant
//
                                    Thread.sleep(acoMC.getSlowDownMS());
                                } catch (InterruptedException ex) {
                                    Logger.getLogger(ACOThread.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                            ((FemaleWorkerAnt) digitalAnt).iteration();
//                        System.out.println("Iterated");

                            //System.out.println(FemaleWorkerAnt.class.getSimpleName());
                        } else {
                            //System.out.println(digitalAnt.getClass().getSimpleName());
                        }
                    }
                }
            }
        }
        System.out.println("ENDED THREAD!");
    }

    
    @Deprecated
    private void trackAnt(DigitalAnt ant) {
        if (ant.isOutOfHill() && !ant.isDead()) {
            for (TrackAnchor trackAnchor : ProjectConstants.trackList) {
                if (trackAnchor.getVertex() == ant.getPosition()) {

                    new Thread(() -> {
                        trackAnchor.setFill(Color.RED);
                        try {
                            Thread.sleep((((acoMC.getSlowDownMS()) * 1000) / 2));
                        } catch (InterruptedException ex) {
                            Logger.getLogger(ACOThread.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        trackAnchor.setFill(Color.BLUE);
                    }).start();

                } else {
                    trackAnchor.setFill(Color.BLUE);
                }
            }
        }
    }

    public boolean isRun() {
        return run;
    }

    public void setRun(boolean run) {
        this.run = run;
    }
    
}
