package daysix;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.StringTokenizer;

public class DaySixPartTwo {
    static ArrayList<Long> timerCounts;
    static ArrayList<String> data;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadaysix.txt");
        timerCounts = new ArrayList<>();
        for(int i=0; i<=8; i++){
            timerCounts.add(0L);
        }
        data.forEach(line-> {
            StringTokenizer st = new StringTokenizer(line, ",");
            while(st.hasMoreTokens()){
                int number = Integer.parseInt(st.nextToken());
                timerCounts.set(number, timerCounts.get(number)+1);
            }
        });
        for(int i=1; i<=256; i++){

            ArrayList<Long> temp = new ArrayList<>();
            for(int j=0; j<9;j++){
                temp.add(0L);
            }
            for(int j=0, k=1; k<=8; j++, k++){
                temp.set(j, timerCounts.get(k));
            }
            temp.set(6, timerCounts.get(0)+timerCounts.get(7));
            temp.set(8, timerCounts.get(0));
            timerCounts = temp;
            System.out.println(timerCounts);
        }
        long count= timerCounts.stream().mapToLong(c -> c).sum();
        System.out.println(count);
    }
}
