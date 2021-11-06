package edu.sdnu.homework8;


public class Levenshtein {
    public static void main(String[] args) {
        method();
    }
    public static void method(){
        String s1 = createString1();
        String s2 = createString2();
        lSEditDistCaller(s1, s2);
    }
    public static String createString1(){
        System.out.println("This s1 is :" + "SNOWY");
        return "SNOWY";
    }
    public static String createString2(){
        System.out.println("The s2 is:" + "SUNNY");
        return "SUNNY";
    }
    public static void lSEditDistCaller(String s1, String s2){
        int[][] initialMatrixE = initializationE(s1, s2);
        char[][] initialMatrixP = initializationP(s1, s2);
        char[] tempS1 = s1.toCharArray();
        char[] tempS2 = s2.toCharArray();
        for(int i = 1; i < initialMatrixE.length; i++){
            for(int j = 1; j < initialMatrixE[0].length; j++){
                int u = initialMatrixE[i -1][j] + 1;
                int l = initialMatrixE[i][j - 1] + 1;
                int topLeftCorner;
                char flag;
                if(tempS2[i - 1] == tempS1[j - 1]){
                    topLeftCorner = initialMatrixE[i -1][j - 1];
                    flag = '0';
                } else {
                    topLeftCorner = initialMatrixE[i - 1][j - 1] + 1;
                    flag = '1';
                }
                if(l <= u && l <= topLeftCorner){
                     initialMatrixE[i][j] = l;
                     initialMatrixP[i][j] = 'L';
                } else if(u <= topLeftCorner){
                    initialMatrixE[i][j] = u;
                    initialMatrixP[i][j] = 'U';
                } else {
                    initialMatrixE[i][j] = topLeftCorner;
                    initialMatrixP[i][j] = flag;
                }
            }
        }
        for(int i = 0; i < initialMatrixE.length; i++){
            for(int j = 0; j < initialMatrixE[0].length; j++){
                System.out.print(initialMatrixE[i][j]);
            }
            System.out.println();
        }
        for(int i = 0; i < initialMatrixP.length; i++){
            for(int j = 0; j < initialMatrixP.length; j++){
                System.out.print(initialMatrixP[i][j]);
            }
            System.out.println();
        }
        getLsEdit(initialMatrixE, initialMatrixP);
    }
    public static int[][] initializationE(String s1, String s2){
        int[][] initialMatrixE = new int[s1.length() + 1][s2.length() + 1];
        for(int j = 1; j < initialMatrixE.length; j++){
            initialMatrixE[0][j] = j;
        }
        for(int j = 1; j < initialMatrixE.length; j++){
            initialMatrixE[j][0] = j;
        }
        return initialMatrixE;
    }
    public static char[][] initializationP(String s1, String s2){
        char[][] initialMatrixP = new char[s2.length() + 1][s2.length() + 1];
        char[] s1TempChar = s1.toCharArray();
        char[] s2TempChar = s2.toCharArray();
        for(int i = 1; i < initialMatrixP[0].length; i++){
            initialMatrixP[0][i] = s1TempChar[i - 1];
        }
        for(int j = 1; j < initialMatrixP.length; j++){
            initialMatrixP[j][0] = s2TempChar[j - 1];
        }
        initialMatrixP[0][0] = ' ';
        return initialMatrixP;
    }
    public static void getLsEdit(int[][] MatrixE, char[][] MatrixP){
        int i = MatrixE.length - 1;
        int j = MatrixE[0].length - 1;
        while(i > 0 || j > 0){
            if(MatrixP[i][j] == '0' || MatrixP[i][j] == '1'){
                System.out.println(MatrixE[i][j] + " " + MatrixP[i][j]);
                i--;
                j--;
            } else if(MatrixP[i][j] == 'L'){
                System.out.println(MatrixE[i][j] + " " + MatrixP[i][j]);
                j--;
            } else {
                System.out.println(MatrixE[i][j] + " " + MatrixP[i][j]);
                i--;
            }
        }
    }
}
