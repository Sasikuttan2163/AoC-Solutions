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
            int count = 0;
            for(int i=0; i< elements.size()-3; i++){
                if(elements.get(i)<elements.get(i+3)){
                    count++;
                }

            }
            System.out.println(count);
        }
        catch(Exception e){
            e.printStackTrace();
        }


    }
}
