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

import uk.wu.kh.graphproject.tsp.aco.formicidae.AntHill;
import uk.wu.kh.graphproject.tsp.aco.formicidae.Caste;

/**
 * The Queen gives birth to new ants. She is placed in the AntHill during
 * DigitalAntHill creation.
 *
 * @author kai
 */
public class QueenAnt extends DigitalAnt {

    private DigitalAnt newAnt;

    public QueenAnt(AntHill antHill, ACOThread acoThread) {
        super(true, Caste.QUEEN, antHill, acoThread);
    }

    /**
     * Creates new ant and specifies its anthill, mother and acoThread for
     * thread stopping reasons.
     *
     * @param antHill
     * @param acoThread
     * @return New FemaleWorkerAnt
     */
    public FemaleWorkerAnt giveBirthtoWorker(AntHill antHill, ACOThread acoThread) {
        newAnt = new FemaleWorkerAnt(antHill, acoThread);
        ((FemaleWorkerAnt) newAnt).birth(this);
        return ((FemaleWorkerAnt) newAnt);
    }

    //Not used yet.
    public MaleAnt giveBirthtoMaleAnt(AntHill antHill, ACOThread acoThread) {
        newAnt = new MaleAnt(antHill, acoThread);
        ((MaleAnt) newAnt).birth(this);
        return ((MaleAnt) newAnt);
    }
}
