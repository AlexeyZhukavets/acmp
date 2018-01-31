package acmp;

import java.io.*;
import java.util.Arrays;

/**
 * Created by alexe_000 on 31.01.2018.
 */
public class MatrixMultiplication {

    //Path to the default input-output files
    private static String inputfileName = "d://input.txt";
    private static String outputfilename = "d://output.txt";
    private static int[] rezult;
    private static int condition;       //int condition - "p"
    private static int n;               //int n - dimension of one matrix n*n
    private static int lineNumber;      //int lineNumber - the number of the search string
    private static int colmn;           //int colmn - the number of the search colmn
    private static int m;               //int m - number of matrices

    public static void main(String[] args){

        // Create file
        File file = new File(inputfileName);

        try {
            BufferedReader in = new BufferedReader(new FileReader(file.getAbsoluteFile()));
            try {

                //Read the first 3 lines
                String string1 = in.readLine();
                String string2 = in.readLine();
                String string3 = in.readLine();
                //Ignore an empty string
                in.readLine();

                String[] mn = string1.split(" ");
                String[] sc = string2.split(" ");

                //Initialize m, n, lineNumber, colmn, condition
                m = Integer.parseInt(mn[0]);
                n = Integer.parseInt(mn[1]);
                lineNumber = Integer.parseInt(sc[0]);
                colmn = Integer.parseInt(sc[1]);
                condition = Integer.parseInt(string3);

                //Validation of basic parameters
                validation(m, n, condition, lineNumber, colmn);

                //----------------------We alternately read all the matrices------------------------
                for (int i = 0; i < m; i++){
                    String matrixStr = "";
                    for (int j = 0; j < n; j++){
                        matrixStr += in.readLine() + " ";
                    }
                    in.readLine();

                    int[] tmpIntArray = strToIntArr(matrixStr.trim().split(" "));

                    if(i == 0){
                        rezult  = getFirstResultString(tmpIntArray);
                        continue;
                    }else {
                        multiplication(matrixStr);
                    }
                }

                //The desired number
                int rezultValue = rezult[colmn - 1];

                //Check the result of the last multiplication
                if(rezultValue >= condition){
                    write((rezultValue % condition) + "");
                }else {
                    write(rezultValue + "");
                }
                //---------------------------------------------------------------------------------
            } finally {
                in.close();
            }
        } catch(IOException e) {
            throw new RuntimeException(e);
        }
    }

    //We get the string of the first matrix that is written to the resulting array rezult[]
    static int[] getFirstResultString(int[] array){
        int from = (n * (lineNumber-1));
        int[] rezultColumn = Arrays.copyOfRange(array, from, from + n);
        return rezultColumn;
    }

    //Validation of basic parameters
    static void validation(int m, int n, int p, int a, int b){

        //A and B
        if(a > n || b > n || a < 1 || b < 1){
            System.out.println("A or B > N OR A or B < 1");
            System.exit(0);
        }
        //m
        if(m > 130 || m < 1){
            System.out.println("We have a problem :(");
            System.out.println("Wrong number of matrices");
            System.exit(0);
        }
        //n
        if(n > 130 || n < 1){
            System.out.println("We have a problem :(");
            System.out.println("Invalid number of matrices");
            System.exit(0);
        }
        //p
        if(!isPrime(p)){
            System.out.println("P is not prime");
            System.exit(0);
        }
        if(p > 1000){
            System.out.println("We have a problem :(");
            System.out.println("Invalid number of 'p'");
            System.exit(0);
        }

    }

    //Condition is prime?
    public static boolean isPrime(int number) {
        if (number < 2) return false;
        for (int i = 2; i*i <= number; i++){
            if (number % i == 0) return false;
        }
        return true;
    }

    //Get all columns of the matrix
    static int[][] getColumns(int[] array){
        int[][] rezultArray = new int[n][n];
        int index = 0;
        for (int i = 0; i < n; i++){
            for (int j = i; j < n * n; j += n){
                rezultArray[i][index++] = array[j];
            }
            index = 0;
        }
        return rezultArray;
    }

    //Multiplication
    static void multiplication(String matrix){
        String[] matrixStringArray = matrix.trim().split(" ");
        int[] matrixIntArray = strToIntArr(matrixStringArray);
        int[][] columnMatrix = getColumns(matrixIntArray);
        int[] resultArray = new int[n];
        for (int i = 0; i < n; i++){
            for (int j = 0; j < n; j++){
                resultArray[i] += rezult[j] * columnMatrix[i][j];
                if(resultArray[i] >= condition){
                    resultArray[i] = resultArray[i] % condition;
                }
            }
        }
        rezult = resultArray;
    }

    //Preform an array of strings into an array of integers
    static int[] strToIntArr(String[] strArray){
        int[] intArray = new int[strArray.length];
        for(int i = 0; i < strArray.length; i++){
            intArray[i] = Integer.parseInt(strArray[i].trim());
        }
        return intArray;
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
