/*
 * The MIT License
 *
 * Copyright 2023 kai.hofbauer.
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
import uk.wu.kh.graphproject.graph.Vertex;

/**
 * This interface displays the idea of how the Vertexes and Adjancies will be
 * created through reading in from a class implementing this interface. * @see
 * Adjancy
 *
 * @see Vertex
 *
 * @author kai.hofbauer
 */
public interface Reader {

    //TODO: IMPLEMENT
    /**
     * Not needed and implemented yet.
     *
     * @param file
     * @return The complete ArrayList of Vertex objects.
     * @throws java.io.FileNotFoundException
     */
    public abstract ArrayList<Vertex> readVertexFromFile(File file) throws FileNotFoundException;

    /**
     * @param resourceStream
     * @return The complete ArrayList of Vertex objects.
     */
    public abstract ArrayList<Vertex> readVertexFromFile(String resourceStream);

}
