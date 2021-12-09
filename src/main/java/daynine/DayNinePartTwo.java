package daynine;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;

public class DayNinePartTwo {
    static ArrayList<String> data;
    static ArrayList<Integer> lowPoints;
    static ArrayList<int[]> lowPointCoordinates;
    static int[] basinSizes;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadaynine.txt");
        lowPoints = new ArrayList<>();
        lowPointCoordinates = new ArrayList<>();
        findLowPoints();
        basinSizes = new int[lowPoints.size()];
        ArrayList<int[]> capturedPoints=new ArrayList<>();
        for(int i=0; i<lowPointCoordinates.size(); i++) {
            int line = lowPointCoordinates.get(i)[0];
            int x = lowPointCoordinates.get(i)[1];
            searchRight(x, line, i, capturedPoints);
            searchLeft(x, line, i,  capturedPoints);
            searchUp(x, line, i, capturedPoints);
            searchDown(x, line, i, capturedPoints);
        }
        long product = 1;
        basinSizes = Arrays.stream(basinSizes).sorted().toArray();
        int count=0;
        for(int j= basinSizes.length-1; count<3; count++){
            product*=basinSizes[j-count];
        }
        System.out.println(product);
    }


    public static void searchRight(int x, int line, int i, ArrayList<int[]> capturedCoordinates){
        for(int j=x; j<data.get(line).length(); j++){
            String d=data.get(line);
            if(d.charAt(j)!='9'){
                int finalJ = j;
                if(capturedCoordinates.stream().map(a->a[0]==line&&a[1]== finalJ).filter(a->a).count()!=0)
                    continue;
                capturedCoordinates.add(new int[]{line, j});
                basinSizes[i]++;
                searchUp(j, line, i, capturedCoordinates);
                searchDown(j, line, i, capturedCoordinates);
            }
            else return;
        }
    }

    public static void searchDown(int x, int line, int i, ArrayList<int[]> capturedCoordinates){
        for(int j=line+1; j<data.size(); j++){
            String d=data.get(j);
            if(d.charAt(x)!='9'){
                int finalJ = j;
                if(capturedCoordinates.stream().map(a-> a[0]== finalJ && a[1]==x).filter(a->a).count()!=0)
                    continue;
                capturedCoordinates.add(new int[]{j, x});
                basinSizes[i]++;
                searchRight(x, j, i, capturedCoordinates);
                searchLeft(x, j, i, capturedCoordinates);
            }
            else return;
        }
    }

    public static void searchUp(int x, int line, int i, ArrayList<int[]> capturedCoordinates){
        for(int j=line-1; j>=0; j--){
            String d = data.get(j);
            if(d.charAt(x)!='9') {
                int finalJ = j;
                if(capturedCoordinates.stream().map(a-> a[0]== finalJ && a[1]==x).filter(a->a).count()!=0)
                    continue;
                capturedCoordinates.add(new int[]{j, x});
                basinSizes[i]++;
                searchRight(x, j, i, capturedCoordinates);
                searchLeft(x, j, i, capturedCoordinates);
            }
            else return;
        }
    }

    public static void searchLeft(int x, int line, int i, ArrayList<int[]> capturedCoordinates){
        for(int j=x; j>=0; j--){
            String d = data.get(line);
            if(d.charAt(j)!='9'){
                int finalJ = j;
                if(capturedCoordinates.stream().map(a->a[0]==line&&a[1]== finalJ).filter(a->a).count()!=0)
                    continue;
                capturedCoordinates.add(new int[]{line, j});
                basinSizes[i]++;
                searchUp(j, line, i, capturedCoordinates);
                searchDown(j, line, i,capturedCoordinates);
            }
            else return;
        }
    }




    public static void findLowPoints(){
        for(int i=0; i< data.size(); i++){
            String row = data.get(i);
            for(int j=0; j<data.get(i).length(); j++){
                if(j>0 && j<(row.length()-1)) {
                    if (row.charAt(j) < row.charAt(j - 1) && row.charAt(j) < row.charAt(j+1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                    }
                }
                if(j==0){
                    if (row.charAt(j) < row.charAt(j+1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                    }
                }
                if(j==(row.length()-1)){
                    if (row.charAt(j) < row.charAt(j-1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j)) {
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                                lowPointCoordinates.add(new int[]{i,j});
                            }
                        }
                    }
                }
            }
        }
    }
}
