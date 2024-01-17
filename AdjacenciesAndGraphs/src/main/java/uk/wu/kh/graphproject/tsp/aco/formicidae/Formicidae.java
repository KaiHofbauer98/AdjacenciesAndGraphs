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
package uk.wu.kh.graphproject.tsp.aco.formicidae;

/**
 * The scientific latin name for ant is Formicidae it has much more attributes
 * but in this Formicidae implementation there are just a few realted to moving
 * bahavoiur.
 *
 * @author kai
 */
public class Formicidae {

    //TODO: IMPLEMENT FLY VOIDS
    /**
     * Possible fly voids in the future?
     */
    private boolean pairOfWings;

    /**
     * Ants gender
     */
    private final Caste caste;

    /**
     * Ants home
     */
    private final AntHill antHill;

    public Formicidae(boolean pairOfWings, Caste caste, AntHill antHill) {
        this.pairOfWings = pairOfWings;
        this.caste = caste;
        this.antHill = antHill;
    }

    public boolean hasPairOfWings() {
        return pairOfWings;
    }

    public void setPairOfWings(boolean pairOfWings) {
        this.pairOfWings = pairOfWings;
    }

    public Caste getCaste() {
        return caste;
    }

    public AntHill getAntHill() {
        return antHill;
    }

}
