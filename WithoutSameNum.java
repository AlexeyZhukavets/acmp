package acmp;

import java.io.*;

/**
 * Created by alexe_000 on 24.01.2018.
 */
public class WithoutSameNum {

    //Path to the default input-output files
    private static String inputfileName = "d://input.txt";
    private static String outputfilename = "d://output.txt";
    //Rezult String
    private static String rezult;

    public static void main(String[] args) throws FileNotFoundException {
        String stringInputValue = read();
        int inputValue = Integer.parseInt(stringInputValue);
        if(inputValue < 1 || inputValue > 10000){
            System.out.println("Some error with input value");
            System.exit(1);
        }
        write(withSameVal(inputValue));
    }

    static String withSameVal(int valInArow){
        int inArow = 0;
        int value = 0;
        while (inArow < valInArow){
            value++;
            if(haveSameNumber(value)){
                continue;
            }
            inArow++;
        }
        return value + "";
    }

    static boolean haveSameNumber(int value){
        String strVal = value + "";
        char[] val = strVal.toCharArray();
        if(val.length == 1){
            return false;
        }
        if(val.length == 2 && val[0] == val[1]){
            return true;
        }
        if(val.length > 2){
            for (int i = 0; i < val.length-1; i++){
                for (int j = i + 1; j < val.length; j++){
                    if(val[i] == val[j]){
                        return true;
                    }
                }
            }
        }
        return false;
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
