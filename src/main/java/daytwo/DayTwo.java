package daytwo;

import java.io.File;
import java.util.Scanner;

public class DayTwo {
    public static void main(String[] args) {
        try {
            File file = new File(DayTwo.class.getClassLoader().getResource("datadaytwo.txt").toURI());
            Scanner sc = new Scanner(file);
            int forward = 0;
            int depth = 0;
            while(sc.hasNextLine()){
                String command = sc.nextLine();
                String direction = command.substring(0, command.indexOf(' '));
                int magnitude = Integer.parseInt(command.substring(command.indexOf(' ')+1));
                System.out.println(direction+" + "+magnitude);

                switch(direction){
                    case "up"->{
                        depth = depth-magnitude;
                    }
                    case "down"->{
                        depth = depth+magnitude;
                    }
                    case "forward"->{
                        forward+=magnitude;

                    }
                }

            }
            System.out.println(forward*depth);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
