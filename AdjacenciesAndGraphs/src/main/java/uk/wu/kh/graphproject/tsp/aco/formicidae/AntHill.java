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

import uk.wu.kh.graphproject.tsp.aco.DigitalAnt;
import java.util.ArrayList;
import uk.wu.kh.graphproject.tsp.aco.QueenAnt;

/**
 * An anthill holds a reference to every ant living in it.
 *
 * @author kai
 */
public class AntHill {

    private final ArrayList<DigitalAnt> antList = new ArrayList<>();

    private QueenAnt queen;

    public ArrayList<DigitalAnt> getAntList() {
        return antList;
    }

    public QueenAnt getQueen() {
        return queen;
    }

    public void setQueen(QueenAnt queen) {
        this.queen = queen;
    }
}
