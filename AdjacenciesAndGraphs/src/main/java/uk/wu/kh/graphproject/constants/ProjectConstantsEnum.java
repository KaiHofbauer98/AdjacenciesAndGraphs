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
package uk.wu.kh.graphproject.constants;

/**
 *
 * @author kai.hofbauer
 */
public enum ProjectConstantsEnum {

    PROJECT_CREATOR("Kai Hofbauer"),
    DEFAULT_JSON_FILE_LOCATION("/uk/wu/kh/graphproject/Map.JSON"),
    DEFAULT_CSV_FILE_LOCATION("/uk/wu/kh/graphproject/CSV.csv"),
    DEFAULT_PROJECT_WORKING_DIR_WIN(""),
    DEFAULT_PROJECT_WORKING_DIR_MACOS(""),
    DEFAULT_PROJECT_WORKING_DIR_LINUX(""),
    DEFAULT_CSV_DELIMITER(";"),
    DEFAULT_ANT_START_AGE("0"),
    DEFAULT_MAX_ANT_AGE_ITERATIONS("30"),
    DEFAULT_ANTHILL_SIZE("200"),
    DEFAULT_ANTHILL_OUTPUT_RATE("3");

    public final String label;

    private ProjectConstantsEnum(String label) {
        this.label = label;
    }

}
