package acmp;

import java.io.*;

/**
 * Created by alexe_000 on 25.01.2018.
 */
public class DNK {


    //Path to the default input-output files
    private static String inputfileName = "d://input.txt";
    private static String outputfilename = "d://output.txt";
    //Rezult String
    private static String rezult;

    public static void main(String[] args) throws FileNotFoundException {
        String[] commonStrings = read().split(" ");
        commonStrings[0] = commonStrings[0].trim();
        commonStrings[1] = commonStrings[1].trim();
        System.out.println(commonStrings[0] + " " + commonStrings[1]);
        char[] subSequence = commonStrings[0].toCharArray();
        char[] sequence = commonStrings[1].toCharArray();
        validation(subSequence, sequence);
        consistencyCheck(subSequence, sequence);
    }

    static void validation(char[] subSecuence, char[] secuence){

        boolean validSec = false;
        boolean validSubSec = false;
        int maxLength = secuence.length + subSecuence.length;

        if(maxLength > 131072){
            System.out.println("Sorry length is very big");
            System.exit(1);
        }

        for (int i = 0; i < subSecuence.length; i++){
            char value = subSecuence[i];
            if(value == 'A' || value == 'G' || value == 'C' || value == 'T'){
                validSubSec = true;
            }
            else {
                validSubSec = false;
                break;
            }
        }

        for (int i = 0; i < secuence.length; i++){
            char value = secuence[i];
            if(value == 'A' || value == 'G' || value == 'C' || value == 'T'){
                validSec = true;
            }else {
                validSec = false;
                break;
            }
        }

        if(!validSec || !validSubSec){
            System.out.println("Wrong parameters");
            System.exit(1);
        }
    }

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



    public static String read() throws FileNotFoundException {
        StringBuilder sb = new StringBuilder();
        File file = new File(inputfileName);
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
