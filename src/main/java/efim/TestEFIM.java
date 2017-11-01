package efim;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;


/**
 * Example of how to run the EFIM algorithm from the source code, and keep the result in memory
 * @author Philippe Fournier-Viger, 2015
 */
public class TestEFIM {

    public static void main(String [] arg) throws IOException {
        // the input and output file paths
        String input;
        String output = ".//output";
        double tetha = 0.1;
        long minutil = 366632;
        if(arg.length > 0){
            input = arg[0];
            if(arg.length > 1) minutil = Long.parseLong(arg[1]);
        }else {
//            input = fileToPath("500.txt");
//            input = fileToPath("accidents.txt");
            input = fileToPath("chess.txt");

        }

        // the minutil threshold


        // Run the EFIM algorithm
        AlgoEFIM algo = new AlgoEFIM();
        Itemsets itemsets = algo.runAlgorithm(minutil,  input, null, true, Integer.MAX_VALUE, true);
        // Print statistics
        algo.printStats();

        // Print the itemsets
        //itemsets.printItemsets();
    }

    public static String fileToPath(String filename) throws UnsupportedEncodingException {
        URL url = TestEFIM.class.getResource(filename);
        return java.net.URLDecoder.decode(url.getPath(),"UTF-8");
    }
}