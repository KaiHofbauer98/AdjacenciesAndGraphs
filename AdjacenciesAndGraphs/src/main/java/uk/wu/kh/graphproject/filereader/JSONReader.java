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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import uk.wu.kh.graphproject.adjacency.Edge;
import uk.wu.kh.graphproject.adjacency.Vertex;

/**
 * Literature read to accomplish the intake of a JSON file
 *
 * <p>
 * Read JSON as String :
 * <a href="https://devqa.io/java-read-json-file-as-string/">https://devqa.io/java-read-json-file-as-string/</a>
 * </p>
 *
 * <p>
 * Parse JSON in Java :
 * <a href="https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java">https://stackoverflow.com/questions/2591098/how-to-parse-json-in-java</a>
 * </p>
 *
 *
 * <p>
 * Check valid JSON String :
 * <a href="https://javarevisited.blogspot.com/2022/12/how-to-validate-json-in-java-jackson.html#axzz8NP97TUqz">https://javarevisited.blogspot.com/2022/12/how-to-validate-json-in-java-jackson.html#axzz8NP97TUqz</a>
 * </p>
 *
 * <p>
 * Iterator over Jackson jsonNode objects :
 * <a href="https://stackoverflow.com/questions/36109920/how-can-i-navigate-past-an-unknown-key-name-with-a-jackson-jsonnode">https://stackoverflow.com/questions/36109920/how-can-i-navigate-past-an-unknown-key-name-with-a-jackson-jsonnode</a>
 * </p>
 *
 *
 *
 *
 * @author kai
 */
public class JSONReader implements Reader {

    private final ArrayList<Vertex> vertexArrayList = new ArrayList<>();
    private Vertex localVertex;
    private Edge localAdjancy;

    private final ObjectMapper mapper = new ObjectMapper();
    private String jsonString;
    private JsonNode jsonNode = null;
    private JsonNode nodePointer;
    int counter;

    @Override
    public ArrayList<Vertex> readVertexFromFile(String resourceStream) {

//        try {
        //Try-with resources doenst need to close the Input stream in an extra statement.
        //The file content is stored into a String variable after successfull execution.
//        try (InputStream stream = CSVReader.class.getResourceAsStream(resourceStream)) {
//try (InputStream stream = CSVReader.class.getResourceAsStream("Map.JSON")) {   
//            jsonString = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
//        } catch (IOException ioe) {
//            ioe.printStackTrace();
//        }
//        try {
//            jsonString = new String(Files.readAllBytes(Paths.get(App.class.getResource("/uk/wu/kh/graphproject/Map.JSON").getPath())));
//        } catch (IOException ex) {
//            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
//        }
        //jsonString = ProjectConstants.ss;

        //https://stackoverflow.com/questions/54140750/java-nio-cant-read-files-from-jrt-image
        byte[] jlo = null;
        FileSystem fs = FileSystems.getFileSystem(URI.create("jrt:/"));
        try {
            jlo = Files.readAllBytes(fs.getPath("modules", "uk.wu.kh.graphproject", "/uk/wu/kh/graphproject/Map.JSON"));
        } catch (IOException ex) {
            Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (jsonString == null) {
            //Not in JRT!
            try (InputStream stream = CSVReader.class.getResourceAsStream(resourceStream)) {
                jsonString = new String(stream.readAllBytes(), StandardCharsets.UTF_8);
            } catch (IOException ioe) {
                ioe.printStackTrace();
            }
        } else {
            //In JRT Image! (Jlink)
            jsonString = new String(jlo);
        }

        //Without a valid JSON String the following code doesnt make sense.
        if (isValidJSON(jsonString)) {

            //Read the JSON string in Jackson jsonNode tree format.
            try {
                jsonNode = mapper.readTree(jsonString);
            } catch (JsonProcessingException ex) {
                Logger.getLogger(JSONReader.class.getName()).log(Level.SEVERE, null, ex);
//                App.getDialog().show("Exception!", ex.getMessage());
            }

            nodePointer = jsonNode.get("area");

            //Iterates over all information in the json string of this specific
            //json file. a is a area. n is a node. l is a link in the node.
            //it is created to get information out of the o which is one link
            //element in the link list. Because the name of the key is different
            //every time it is needed to break down the json structure to a
            //the keys and values at this point.
            nodePointer.forEach(a -> {
                a.forEach(n -> {
                    localVertex = new Vertex(counter);
                    localVertex.setLetter(n.get("node").asText().charAt(0));
                    localVertex.setX(n.get("x-coord").asInt());
                    localVertex.setY(n.get("y-coord").asInt());
                    vertexArrayList.add(localVertex);
                    counter++;
                });
                //All vertexes has been created. This is important before setting
                //the end vertexes for the adjancies.

                a.forEach(n -> {
                    //The structure needs to be entered again.
                    localVertex = getVertexFromLetter(n.get("node").asText().charAt(0));
                    n.forEach(l -> {
                        l.forEach(o -> {
                            Iterator<Entry<String, JsonNode>> it = o.fields();
                            while (it.hasNext()) {
                                Entry<String, JsonNode> node = it.next();
                                localAdjancy = new Edge(node.getValue().asInt(), localVertex, getVertexFromLetter(node.getKey().charAt(0)));
                                System.out.println("Weight: " + node.getValue().asInt());
                                System.out.println("End Vertex letter : " + getVertexFromLetter(node.getKey().charAt(0)));
                                localVertex.getEdgeList().add(localAdjancy);
                            }
                        });
                    });
                });
            });
        }

        //Sort arrayList
        Comparator lowerIdCharComp = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return Character.compare(((Vertex) o1).getLetter(), ((Vertex) o2).getLetter());
            }
        };

        vertexArrayList.sort(lowerIdCharComp);
        return vertexArrayList;

    }

    private Vertex getVertexFromLetter(char c) {
        Vertex vertexOut = null;
        for (Vertex vertex : vertexArrayList) {
            if (vertex.getLetter() == c) {
                vertexOut = vertex;
            }
        }
        return vertexOut;
    }

    /**
     * I played with the code below to get a full understanding of the Jackson
     * json jsonNode. After running this code I got excatly what I wanted. In
     * readVertexFromFile the logic is used to build the internal structures
     * needed for working on the TSP.
     */
    @Deprecated
    private void displayTreeInfo() {

        if (nodePointer != null) {

            //Get the area name
            System.out.println("Area: " + nodePointer.get("name"));

            //Displays the JSON Tree.
            nodePointer.forEach(a -> {
                a.forEach(n -> {
                    System.out.println(n.get("node"));
                    System.out.println(n.get("x-coord"));
                    System.out.println(n.get("y-coord"));
                    n.forEach(l -> {
                        l.forEach(o -> {
                            Iterator<Entry<String, JsonNode>> it = o.fields();
                            while (it.hasNext()) {
                                Entry<String, JsonNode> node = it.next();
                                System.out.println(node.getKey());
                                System.out.println(node.getValue().asInt());
                            }
                        });
                    });
                });

            });
        }

    }

    /**
     * Checks if the given json String can be read properly.
     *
     * @param json
     * @return
     */
    public boolean isValidJSON(final String json) {
        boolean valid = false;
        try {
            jsonNode = mapper.readTree(json);
            //After command execution the JSON string is confirmed readable.
            valid = true;
        } catch (JsonProcessingException e) {
            //The .readTree(json) statement was not successfully.
            //Shows information to the user.
//            App.getDialog().show("Exception!", "The JSON file could not be verified by Jackson: ObjectMapper.readTree()");
        }
        return valid;
    }

    /**
     * Not needed jet, the main JSON file is included in the projects resources
     * and can be read as resourceStream.
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     */
    @Override
    public ArrayList<Vertex> readVertexFromFile(File file) throws FileNotFoundException {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody

    }

}
