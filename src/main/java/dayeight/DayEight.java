package dayeight;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DayEight {
    static ArrayList<String> data, processedData;
    final static int ONE_LENGTH=2, SEVEN_LENGTH=3, FOUR_LENGTH=4, EIGHT_LENGTH=7;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayeight.txt");
        processedData = new ArrayList<>();
        data.forEach(line->{
            processedData.addAll(List.of(line.substring(line.indexOf("|") + 1).trim().split(" ")));
        });
        int count=0;
        for(int i=0; i<processedData.size();i++){
            String code = processedData.get(i);

            switch (code.length()) {
                case ONE_LENGTH, FOUR_LENGTH, SEVEN_LENGTH, EIGHT_LENGTH -> count++;
            }
        }
        System.out.println(count);
    }
}
