package dayone;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.util.Scanner;

public class SonarSweep {
    public static void main(String[] args) throws FileNotFoundException, URISyntaxException {
        int curr, prev, count=0;
        ClassLoader classLoader = SonarSweep.class.getClassLoader();
        File file = new File(classLoader.getResource("depths.txt").toURI());
        Scanner sc = new Scanner(file);
        prev = sc.nextInt();
        while(sc.hasNextInt()){
            curr=sc.nextInt();
            if(curr>prev)
                count++;
            prev = curr;
        }
        System.out.println(count);
    }
}
