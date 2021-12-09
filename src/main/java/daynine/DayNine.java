package daynine;

import utilities.MyUtilities;

import java.util.ArrayList;

public class DayNine {
    static ArrayList<String> data;
    static ArrayList<Integer> lowPoints;
    public static void main(String[] args) {
        data= MyUtilities.readFile("datadaynine.txt");
        lowPoints=new ArrayList<>();
        for(int i=0; i< data.size(); i++){
            String row = data.get(i);
            for(int j=0; j<data.get(i).length(); j++){
                if(j>0 && j<(row.length()-1)) {
                    if (row.charAt(j) < row.charAt(j - 1) && row.charAt(j) < row.charAt(j+1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                    }
                }
                if(j==0){
                    if (row.charAt(j) < row.charAt(j+1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                    }
                }
                if(j==(row.length()-1)){
                    if (row.charAt(j) < row.charAt(j-1)) {
                        if(i!=(data.size()-1) && i!=0){
                            if(row.charAt(j)<data.get(i-1).charAt(j) && row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==0){
                            if(row.charAt(j)<data.get(i+1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                        if(i==(data.size()-1)){
                            if(row.charAt(j)<data.get(i-1).charAt(j))
                                lowPoints.add(Character.digit(row.charAt(j), 10));
                        }
                    }
                }
            }
        }
        System.out.println(lowPoints.stream().mapToInt(c->c+1).sum());
    }
}
