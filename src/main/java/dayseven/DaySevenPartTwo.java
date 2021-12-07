package dayseven;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DaySevenPartTwo {
    static ArrayList<String> data;
    static ArrayList<Integer> position;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayseven.txt");
        position = new ArrayList<>();
        data.forEach(line->{
            StringTokenizer stringTokenizer = new StringTokenizer(line, ",");
            while(stringTokenizer.hasMoreTokens())
                position.add(Integer.parseInt(stringTokenizer.nextToken()));
        });
        long leastFuel = Long.MAX_VALUE;
        long maxPosition=position.stream().max(Integer::compare).get();
        long minPosition=position.stream().min(Integer::compare).get();
        for(long i=minPosition;i<maxPosition;i++){
            long fuel=0;
            for(long p:position){
                long numberOfMoves = Math.abs(i-p);
                fuel+=numberOfMoves*(numberOfMoves+1)/2;
            }
            if(fuel<leastFuel)
                leastFuel=fuel;
        }
        System.out.println(leastFuel);
    }
}
