package dayone;

import dayone.SonarSweep;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class SonarSweepPartTwo {
    public static void main(String[] args) {
        ClassLoader classLoader = SonarSweep.class.getClassLoader();
        File file;
        Scanner sc;
        try {
            file = new File(classLoader.getResource("depths.txt").toURI());
            sc = new Scanner(file);
            ArrayList<Integer> elements = new ArrayList<Integer>();
            while(sc.hasNextInt())
                elements.add(sc.nextInt());
            int sumprev = 0, sumcurr = 0, count = 0;
            for(int i=0; i<3; i++){
                sumprev = sumprev + elements.get(i);
            }
            for(int i=1; i< elements.size()-2; i++){
                for(int j=0; j<3; j++){
                    sumcurr = sumcurr + elements.get(i+j);
                }
                if(sumcurr>sumprev){
                    count++;
                }
                sumprev = sumcurr;
                sumcurr=0;
            }
            System.out.println(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}
