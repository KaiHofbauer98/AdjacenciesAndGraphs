/*
 * The MIT License
 *
 * Copyright 2024 kai.
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
package uk.wu.kh.graphproject.realRandom;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.net.ssl.HttpsURLConnection;

/**
 * Opens a https connection to a website wich returns, with the specified
 * values, a set of integers wich will be converted into interger arrays.
 *
 * <p>
 * RANDOM.ORG offers true random numbers to anyone on the Internet. The
 * randomness comes from atmospheric noise, which for many purposes is better
 * than the pseudo-random number algorithms typically used in computer programs.
 * People use RANDOM.ORG for holding drawings, lotteries and sweepstakes, to
 * drive online games, for scientific applications and for art and music. The
 * service has existed since 1998 and was built by Dr Mads Haahr of the School
 * of Computer Science and Statistics at Trinity College, Dublin in Ireland.
 * Today, RANDOM.ORG is operated by Randomness and Integrity Services Ltd.
 * Source: https://random.org
 * </p>
 *
 * @author kai
 */
public class RealRandomLoader {

    private static String httpsURL;
    //The format can be found on https://random.org
    private static final String HTTPSA = "https://www.random.org/integers/?num=";
    private static final String HTTPSB = "&min=";
    private static final String HTTPSC = "&max=";
    private static final String HTTPSD = "&col=1&base=10&format=plain&rnd=new";
    //Sets Firefox as accessing "browser"
    private static final String USR = "User-Agent";
    private static final String AGENT = "Mozilla/5.0 (Windows NT 10.0; Win64; x64; rv:63.0) Gecko/20100101 Firefox/63.0";

    private static URL myurl;
    private static HttpsURLConnection con;
    private static InputStream ins;
    private static InputStreamReader isr;

    /**
     * Easy method call allows saving random number arrays.
     *
     * @param min
     * @param max
     * @param amount
     * @return Integer array with real random numbers from https://random.org
     * @throws IOException
     */
    public static int[] getIntArr(int min, int max, int amount) throws IOException, Exception {

        //I found this limit on the website.
        if (amount > 10000) {
            throw new Exception("Requested amount cant be over 10000!");
        }
        httpsURL = HTTPSA + amount + HTTPSB + min + HTTPSC + max + HTTPSD;

        myurl = URI.create(httpsURL).toURL();
        con = (HttpsURLConnection) myurl.openConnection();
        con.setRequestProperty(USR, AGENT);
        ins = con.getInputStream();
        isr = new InputStreamReader(ins);

        String output = "";
        //Reads the values from https into a String.
        try (BufferedReader in = new BufferedReader(isr)) {
            String inputLine;
            //https://stackoverflow.com/questions/28977308/read-all-lines-with-bufferedreader
            inputLine = in.lines().collect(Collectors.joining());
            output = inputLine;
        }
        //Regex "(?<=\\G.{" + 1 + "})" means splitting every 1 character.
        int[] arr = Arrays.stream(output.substring(1, output.length() - 1).split("(?<=\\G.{" + 1 + "})")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        //Close https connection!
        con.disconnect();
        return arr;
    }
}
