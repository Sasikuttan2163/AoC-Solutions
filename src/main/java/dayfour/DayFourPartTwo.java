package dayfour;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DayFourPartTwo {
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
        trimmedData = new ArrayList<String>();
        data.forEach(line->{
            trimmedData.addAll(Arrays.asList(line.split("[ \n]")));
            trimmedData.removeIf(el->{
                return el.equals("");
            });
        });
        System.out.println(trimmedData);
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
        boolean[] wonBoard = new boolean[matrices.size()];
        label:
        for(int i=5; true; i++){
            int[] searchElements = new int[i];
            for(int j=0; j<i; j++)
                searchElements[j] = bingoList.get(j);
            for(int[][] m:matrices) {
                if(numberOfBoardLeft(wonBoard)==0) {
                    break label;
                }
                for (int[] k : m) {
                    if (MyUtilities.containsAll(k, searchElements)) {
                        matchMatrix=matrices.indexOf(m);
                        matchElements = searchElements;
                        wonBoard[matrices.indexOf(m)] = true;
                    }
                }
                int[] col = new int[5];
                for(int j=0, counter=0; j<5; j++){
                    for(int k=0; k<5; k++)
                        col[k] = m[k][j];
                    if(MyUtilities.containsAll(col, searchElements)){
                        matchMatrix=matrices.indexOf(m);
                        matchElements = searchElements;
                        wonBoard[matrices.indexOf(m)] = true;
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

    public static int numberOfBoardLeft(boolean[] values){
        int count=0;
        for(boolean b: values){
            if(!b)
                count++;
        }
        return count;
    }
}
