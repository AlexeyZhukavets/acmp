package acmp;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by alexe_000 on 23.01.2018.
 */
public class SumModule {

    //Path to the default input-output files
    private static String inputfileName = "d://input.txt";
    private static String outputfilename = "d://output.txt";
    static int[] intArray;
    static int lengthArray;

    public static void main(String[] args) throws FileNotFoundException {
        String str = read();
        str = str.trim();
        String[] rezult = str.split(" ");
        lengthArray = Integer.parseInt(rezult[0]);
        intArray = new int[lengthArray];
        initializeArray(rezult);
        writeIndexesElem(getMaxModSum());
    }

    static List getMaxModSum(){
        List n = new ArrayList<Integer>();
        List p = new ArrayList<Integer>();
        int negative = 0;
        int positive = 0;
        for(int i = 0; i < intArray.length; i++){
            if(intArray[i] < 0){
                negative = negative - intArray[i];
                n.add(i + 1);
            }else {
                positive = positive + intArray[i];
                p.add(i + 1);
            }
        }
        if(Math.abs(negative) > positive){
            return n;
        }
        return p;
    }

    static void initializeArray(String[] rezult){
        for (int i = 0; i < lengthArray; i++){
            intArray[i] = Integer.parseInt(rezult[i + 1]);
        }
    }

    static void writeIndexesElem(List list){
        String rezult = "";
        rezult = rezult + list.size() + "\n";
        for (int i = 0; i < list.size(); i++){
            rezult = rezult + list.get(i) + " ";
        }
        rezult = rezult.trim();
        write(rezult);
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
