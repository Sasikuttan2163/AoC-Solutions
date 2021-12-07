package dayseven;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DaySeven {
    static ArrayList<String> data;
    static ArrayList<Integer> position;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayseven.txt");
        position = new ArrayList<>();
        data.forEach(line->{
            StringTokenizer st = new StringTokenizer(line,",");
            while(st.hasMoreTokens())
                position.add(Integer.parseInt(st.nextToken()));
        });
        System.out.println(position+"\n"+position.size());
        ArrayList<Integer> temp = new ArrayList<>(position);
        final int[] leastFuel = {10000000};
        position.forEach(n->{
            int fuel=0;
            for (int i=0; i<position.stream().mapToInt(a->a).max().getAsInt(); i++) {
                fuel += Math.abs(n - i);
            }
            if(fuel< leastFuel[0]){
                leastFuel[0] =fuel;
            }
        });
        System.out.println(leastFuel[0]);
    }

}
