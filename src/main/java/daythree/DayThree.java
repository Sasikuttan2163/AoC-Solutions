package daythree;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class DayThree {
    public static void main(String[] args) {
        ArrayList<String> data = readFile();
        String gamma="", epsilon="";
        for(int i=0; i<12; i++){
            int onecount=0, zerocount=0;
            for (String datum : data) {
                String bit = datum.substring(i, i + 1);
                if (bit.equals("1"))
                    onecount++;
                else
                    zerocount++;
            }
            if(onecount>zerocount) {
                gamma = gamma + "1";
            }
            else {
                gamma = gamma + "0";
            }
        }
        for(int i=0; i<gamma.length(); i++){
            if(gamma.charAt(i)=='1')
                epsilon+="0";
            else
                epsilon+="1";
        }
        System.out.println(gamma+" "+epsilon);
        long power = Integer.parseInt(gamma,2) * Integer.parseInt(epsilon, 2);
        System.out.println("Power "+power);
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

}
