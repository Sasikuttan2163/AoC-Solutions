package daysix;

import utilities.MyUtilities;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class DaySix {
    static ArrayList<String> data;
    static ArrayList<Integer> timers;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadaysix.txt");
        timers = new ArrayList<>();
        data.forEach(line-> {
            StringTokenizer st = new StringTokenizer(line, ",");
            while(st.hasMoreTokens())
            timers.add(Integer.parseInt(st.nextToken()));
        });
        for(int i=1; i<=80; i++){
            int size = timers.size();
            for(int j=0; j<size; j++){
                int element = timers.get((int)j);
                if(element==0) {
                    timers.add(8);
                    timers.set(j, 6);
                }
                else
                    timers.set(j, timers.get(j)-1);
            }
            int[] numberCount = new int[9];
            for(int k=0; k<9;k++) {
                int finalK = k;
                timers.stream().filter(c->c== finalK).forEach(el->{
                        numberCount[finalK]++;
                });
            }
        }
        System.out.println(timers.size());
    }
}
