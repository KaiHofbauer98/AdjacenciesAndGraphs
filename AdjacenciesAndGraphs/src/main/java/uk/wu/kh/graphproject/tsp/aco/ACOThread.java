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

import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;

/**
 *
 * @author kai
 */
public class ACOThread implements Runnable {

    private final DigitalAntHill digitalAntHill;
    private QueenAnt queen;

    public ACOThread(DigitalAntHill digitalAntHill) {
        this.digitalAntHill = digitalAntHill;
        init();
    }

    private void init() {
        //Create the queen from scratch.
        queen = new QueenAnt(digitalAntHill);
        digitalAntHill.getAntList().add(queen);
        //Fill up the ant hill.
        for (int i = digitalAntHill.getAntList().size(); i < Integer.parseInt(ProjectConstantsEnum.DEFAULT_ANTHILL_SIZE.label); i++) {
            digitalAntHill.getAntList().add(queen.giveBirthtoWorker(digitalAntHill));
//            digitalAntHill.getAntList().getLast().setPosition(digitalAntHill.getVertex());
            System.out.println(
                    digitalAntHill.getAntList().getLast().getAge() + " " + digitalAntHill.getAntList().getLast().getPosition().getLetter()
            );
        }

    }

    @Override
    public void run() {
        while (digitalAntHill.getAntList().size() > 1) {
            for (DigitalAnt digitalAnt : digitalAntHill.getAntList()) {
                if (digitalAnt instanceof FemaleWorkerAnt) {
                    //Female worker ant
                    
                    ((FemaleWorkerAnt) digitalAnt).iteration();
                    
                    System.out.println(FemaleWorkerAnt.class.getSimpleName());
                }else{
                    System.out.println(digitalAnt.getClass().getSimpleName());
                }
            }
            break;
        }
    }
}
