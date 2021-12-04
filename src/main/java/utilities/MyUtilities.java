package utilities;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MyUtilities {
    public static ArrayList<String> readFile(String filename){
        ArrayList<String> arr = new ArrayList<String>();
        try{
            File file = new File(MyUtilities.class.getClassLoader().getResource(filename).toURI());
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine()){
                arr.add(sc.nextLine());
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
        return arr;
    }
    public static boolean containsAll(int[] arr, int[] search){
        boolean[] containsEachElement = new boolean[arr.length];
        for(int n:search){
            for(int i=0; i<arr.length; i++) {
                int m=arr[i];
                if (m == n) {
                    containsEachElement[i] = true;
                }
            }
        }
        for(int i=0; i<arr.length; i++){
            if(!containsEachElement[i])
                return false;
        }
        return true;
    }
}
