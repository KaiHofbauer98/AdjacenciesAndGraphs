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
package uk.wu.kh.graphproject.filereader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;
import uk.wu.kh.graphproject.graph.Edge;
import uk.wu.kh.graphproject.graph.Vertex;
import uk.wu.kh.graphproject.constants.ProjectConstantsEnum;

/**
 *
 * First implementation of a filereader. (Not used in Assignment so comments are
 * reduced!)
 *
 * @author kai
 */
public class CSVReader implements Reader {

    private final ArrayList<Vertex> vertexArrayList = new ArrayList<>();

    /**
     *
     * @param file
     * @return
     */
    @Override
    public ArrayList<Vertex> readVertexFromFile(File file) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    /**
     *
     * @param resourceStream
     * @return
     */
    @Override
    public ArrayList<Vertex> readVertexFromFile(String resourceStream) {

        String line;
        int counter = 0;
        String[] columnNames;
        String[] values;
        Scanner scanner = new Scanner(CSVReader.class.getResourceAsStream(resourceStream));
        while (scanner.hasNextLine()) {
            line = scanner.nextLine();
            values = line.split(ProjectConstantsEnum.DEFAULT_CSV_DELIMITER.label);
            if (counter == 0) {
                //The first line of the CSV Contains the column names!
                columnNames = values.clone();
            } else {
                vertexArrayList.add(new Vertex(Integer.parseInt(values[0])));
            }

            counter++;
        }
        scanner.close();

        scanner = new Scanner(CSVReader.class.getResourceAsStream("/uk/wu/kh/graphproject/CSV.csv"));
        int contained_adjances = 0;
        int counter2 = 0;
        //Skip the first column which is the uniqueid
        int pointer;

        while (scanner.hasNextLine()) {
            System.out.println("RR");
            line = scanner.nextLine();
            values = line.split(ProjectConstantsEnum.DEFAULT_CSV_DELIMITER.label);

            if (counter2 != 0) {

                for (Vertex vertex : vertexArrayList) {

                    if (vertex.getUniqueId() == Integer.parseInt(values[0])) {

                        //Amount of values in this row minus the id column. This divided by two makes the number of 
                        //Adjances in this row.
                        counter = 0;
                        pointer = 1;
                        contained_adjances = (values.length - 1) / 2;
                        System.out.println(contained_adjances);

                        while (counter < contained_adjances) {
                            vertex.getEdgeList().add(new Edge(
                                    Integer.parseInt(values[pointer]),
                                    vertex,
                                    searchVertexWithUniqueId(Integer.parseInt(values[pointer + 1]))
                            ));
                            //skip the last two values in the next step.
                            pointer = pointer + 2;
                            counter++;

                        }
                    }
                }
            }
            counter2++;
        }
        scanner.close();

        //Sort arrayList
        Comparator lowerIdComp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Integer.compare(((Vertex) o1).getUniqueId(), ((Vertex) o2).getUniqueId());
            }
        };

        vertexArrayList.sort(lowerIdComp);

        return vertexArrayList;

    }

    private Vertex searchVertexWithUniqueId(int uniqueId) {
        Vertex vertexOut = null;
        for (Vertex vertex : vertexArrayList) {
            if (vertex.getUniqueId() == uniqueId) {
                vertexOut = vertex;
            }
        }
        return vertexOut;
    }

}
