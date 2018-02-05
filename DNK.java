package acmp;

import java.io.*;

/**
 * Created by alexe_000 on 25.01.2018.
 */

/*
*_____________________
*  Progress:
* _{1}_. Read the file
* _{2}_. Split the result into two lines, and transform each lines into a char[]
* _{3}_. Checking for correctness of input data
* _{4}_. Test the occurrence of a subSequence in a sequence, and recording the result of the test
*_____________________
* */

public class DNK {

    //Path to the default input-output files
    private static String inputfileName = "d://input.txt";
    private static String outputfilename = "d://output.txt";
    //Rezult String
    private static String rezult;

    public static void main(String[] args) throws FileNotFoundException {
        //Subtract an array of strings from a file                     _{1}_
        String[] commonStrings = read().split(" ");

        //Convert sequences to char array                              _{2}_
        char[] subSequence = commonStrings[0].toCharArray();
        char[] sequence = commonStrings[1].toCharArray();

        //Check sequences                                              _{3}_
        validation(subSequence, sequence);

        //Check whether the subSequence can be evalted into a sequence _{4}_
        consistencyCheck(subSequence, sequence);
    }

    //Check the input sequences
    static void validation(char[] subSecuence, char[] secuence){
        if(!validAGCT(subSecuence) || !validAGCT(secuence)){
            System.out.println("Wrong parameters");
            System.exit(0);
        }
    }

    //looking for a discrepancy AGCT in the sequence
    static boolean validAGCT(char[] secuence){
        //result of checking
        boolean validSec = false;
        for (int i = 0; i < secuence.length; i++){
            char value = secuence[i];
            if(value == 'A' || value == 'G' || value == 'C' || value == 'T'){
                validSec = true;
            }else {
                return false;
            }
        }
        return validSec;
    }

    //Check whether the subSequence can be evalted into a sequence
    static void consistencyCheck(char[] subSecuence, char[] secuence){
        int boundCondSub = subSecuence.length;
        int bound = secuence.length;
        int indexSec = 0;
        int indexSubSec = 0;
        while (indexSec < bound){
            if(subSecuence[indexSubSec] == secuence[indexSec]){
                indexSubSec++;
            }
            if(indexSubSec == boundCondSub){
                write("YES");
                return;
            }
            indexSec++;
        }
        write("NO");
    }

    //Read the file if it is less than 256 kb
    public static String read() throws FileNotFoundException {
        //The result of reading
        StringBuilder sb = new StringBuilder();
        //Create file
        File file = new File(inputfileName);

        //Checking the file size
        if(file.length() > 262144){            //262144 B - 256 KB
            System.out.println("The file size exceeds 256 KB");
            System.exit(0);
        }

        //Read file
        try {
            BufferedReader in = new BufferedReader(new FileReader( file.getAbsoluteFile()));
            try {
                String s;
                while ((s = in.readLine()) != null) {
                    s = s + " ";
                    sb.append(s);
                }
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
        return sb.toString().trim();
    }

    //Write rezult
    public static void write(String result) {
        File file = new File(outputfilename);
        try {
            //Create the file if it does not exist
            if(!file.exists()){
                file.createNewFile();
            }
            PrintWriter out = new PrintWriter(file.getAbsoluteFile());
            try {
                //Writes the results to a file
                out.print(result);
            } finally {
                //Close PrintWriter
                out.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }
}
