package daythree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

class DayThreePartTwo{
    public static void main(String[] args) {
        ArrayList<String> data = readFile();
        ArrayList<String> databackup = new ArrayList<>(data);
        String oxygen ="";
        String carbon ="";
        oxygen = oxygenGeneratorRating(databackup);
        databackup=new ArrayList<>(data);
        carbon = co2ScrubberRating(databackup);
        long rating = Integer.parseInt(oxygen, 2)*Integer.parseInt(carbon, 2);
        System.out.println(rating);
    }


    public static ArrayList<String> readFile(){
        ArrayList<String> filedata = new ArrayList<String>();
        try {
            File file = new File(DayThree.class.getClassLoader().getResource("datadaythree.txt").toURI());
            Scanner sc = new Scanner(file);
            while(sc.hasNextLine())
                filedata.add(sc.nextLine());
        }catch(Exception e){
            e.printStackTrace();
        }
        return filedata;
    }


    public static char mostCommonCharacterAtPosition(ArrayList<String> arr, int pos){
        int one=0, zero=0;
        for(String line:arr){
            if(line.charAt(pos)=='1')one++;
            else zero++;
        }
        if(one>zero)
            return '1';
        else if(one==zero)
            return 't';
        else
            return '0';
    }


    public static String oxygenGeneratorRating(ArrayList<String> arr){
        for(int i=0; i<12; i++){
            int finalI = i;
            char ch = mostCommonCharacterAtPosition(arr, i);
            if(ch=='1')
                arr.removeIf(line -> line.charAt(finalI)!='1');
            else if(ch=='0')
                arr.removeIf(line -> line.charAt(finalI)!='0');
            else
                arr.removeIf(line -> line.charAt(finalI)!='1');
            if(arr.size()==1)
                break;
        }
        return arr.get(0);
    }

    public static String co2ScrubberRating(ArrayList<String> arr){
        for(int i=0; i<12; i++){
            int finalI = i;
            char ch = mostCommonCharacterAtPosition(arr, i);
            if(ch=='1')
                arr.removeIf(line -> line.charAt(finalI)!='0');
            else if(ch=='0')
                arr.removeIf(line -> line.charAt(finalI)!='1');
            else
                arr.removeIf(line -> line.charAt(finalI)!='0');
            if(arr.size()==1)
                break;
        }
        return arr.get(0);
    }
}