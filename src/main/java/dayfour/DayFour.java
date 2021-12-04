package dayfour;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.atomic.AtomicReference;

public class DayFour {
    static ArrayList<String> data = new ArrayList<String>();
    static ArrayList<Integer> bingoList = new ArrayList<>();
    static ArrayList<String> trimmedData;
    static ArrayList<int[][]> matrices;
    public static void main(String[] args) {
        data= MyUtilities.readFile("datadayfour.txt");
        readValues();
        removeEmptyLines();
        removeExcessSpaces();
        process();
        match();
    }

    public static void readValues(){
        StringTokenizer stringTokenizer = new StringTokenizer(data.get(0), ",");
        while(stringTokenizer.hasMoreTokens())
        bingoList.add(Integer.parseInt(stringTokenizer.nextToken()));
        data.remove(0);
    }

    public static void removeEmptyLines(){
        data.removeIf(line -> line.equals(""));
    }

    public static void removeExcessSpaces(){
        StringTokenizer stringTokenizer;
        trimmedData = new ArrayList<String>();
        for(String lines:data){
            stringTokenizer = new StringTokenizer(lines, " ");
            while(stringTokenizer.hasMoreTokens())
            trimmedData.add(stringTokenizer.nextToken());
        }
    }

    public static void process(){
        matrices = new ArrayList<>();
        int matrixNumber=0;
        int[][] matrix = new int[5][5];
        for(;matrixNumber< trimmedData.size()/25; matrixNumber++) {
            for (int i = 0; i < 5; i++) {
                for (int j = 0; j < 5; j++) {
                    matrix[i][j] = Integer.parseInt(trimmedData.get(matrixNumber * 25 + i * 5 + j));
                }
            }
            matrices.add(matrix);
            matrix = new int[5][5];
        }
    }

    public static void match(){
        int matchMatrix = 0;
        int[] matchElements = new int[0];
        label:
        for(int i=5; true; i++){
            int[] searchElements = new int[i];
            for(int j=0; j<i; j++)
                searchElements[j] = bingoList.get(j);
            for(int[][] m:matrices) {
                for (int[] k : m) {
                    if (MyUtilities.containsAll(k, searchElements)) {
                        matchMatrix=matrices.indexOf(m);
                        matchElements = searchElements;
                        break label;
                    }
                }
            }

        }
        int sum=0;
        for(int[] m:matrices.get(matchMatrix)){
            for(int n: m){
                boolean add = true;
                for(int o:matchElements){
                    if(o==n)
                        add=false;
                }
                if(add)
                    sum+=n;
            }
        }
        System.out.println(sum*matchElements[matchElements.length-1]);
    }


}
