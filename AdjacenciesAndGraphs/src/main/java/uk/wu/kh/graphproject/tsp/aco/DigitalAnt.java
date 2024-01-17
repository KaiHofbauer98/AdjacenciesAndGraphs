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

import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;
import uk.wu.kh.graphproject.tsp.aco.formicidae.AntHill;
import uk.wu.kh.graphproject.tsp.aco.formicidae.Caste;
import uk.wu.kh.graphproject.tsp.aco.formicidae.Formicidae;

/**
 *
 * @author kai
 */
public class DigitalAnt extends Formicidae implements AbstractDigitalAnt {

    private int age = Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANT_START_AGE.label);
    private QueenAnt mother = null;
    private Vertex position;
    private boolean outOfHill = false;
    private boolean dead = false;

    /**
     * Needed for stopping the thread.
     */
    private final ACOThread acoThread;

    public DigitalAnt(boolean pairOfWings, Caste caste, AntHill antHill, ACOThread acoThread) {
        super(pairOfWings, caste, antHill);
        this.position = ((DigitalAntHill) antHill).getVertex();
        this.acoThread = acoThread;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Vertex getPosition() {
        return position;
    }

    public void setPosition(Vertex position) {
        this.position = position;
    }

    @Override
    public void birth(QueenAnt queenAnt) {
        this.mother = queenAnt;
        super.getAntHill().getAntList().add(this);
    }

    @Override
    public void death() {
        //This methodcall caused the ConcurrentMofificationException!
        //While accessing the object in a thread.
        //super.getAntHill().getAntList().remove(this);
        
        this.dead = true;
        //Checks if it is the last ant in thread.
        lastAntInHill();
    }

    /**
     * Stops the running ACOThread as soon as possible. Route found.
     *
     * @see ACOThread
     */
    private void lastAntInHill() {
        if (this == super.getAntHill().getAntList().getLast()) {
            //Last ant in anthill died!
            acoThread.setRun(false);
        }
    }

    /**
     * Stops the running ACOThread as soon as possible. Route found.
     *
     * @see ACOThread
     */
    public void foundRoute() {
        acoThread.setRun(false);
    }

    /**
     * If the ant is older than her life should be long, specified in constants,
     * the ant dies.
     *
     * @param time_passed
     */
    @Override
    public void aging(int time_passed) {
        age = age + time_passed;
        if (age > Integer.parseInt(ProjectConstantsEnum.DEFAULT_MAX_ANT_AGE_ITERATIONS.label)) {
            death();
        }
    }

    public boolean isOutOfHill() {
        return outOfHill;
    }

    public void setOutOfHill(boolean outOfHill) {
        this.outOfHill = outOfHill;
    }

    public boolean isDead() {
        return dead;
    }

}
