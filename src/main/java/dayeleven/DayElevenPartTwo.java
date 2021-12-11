package dayeleven;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;

public class DayElevenPartTwo {
    static ArrayList<String> data;
    static int[][] octopi;
    static ArrayList<int[]> capturedCoordinates;
    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayeleven.txt");
        capturedCoordinates=new ArrayList<>();
        octopi=new int[10][10];
        for(int i=0; i<10; i++){
            for(int j=0; j<10; j++){
                octopi[i][j]=Character.digit(data.get(i).charAt(j), 10);
            }
        }
        for(int i=0; true; i++){
            capturedCoordinates = new ArrayList<>();
            for(int j=0; j<10; j++){
                for(int k=0; k<10; k++){
                    octopi[j][k]++;
                    int finalJ = j;
                    int finalK = k;
                    if(octopi[j][k]>9 && capturedCoordinates.stream().map(r->r[0]== finalJ &&r[1]== finalK).filter(a->a).count()==0)
                        flash(j, k);
                }
            }
            capturedCoordinates.forEach(point->{
                octopi[point[0]][point[1]]=0;
            });
            int sum=0;
            for(int j=0; j<10; j++){
                for(int k=0; k<10; k++){
                    sum+=octopi[j][k];
                }
            }
            if(sum==0){
                System.out.println(i+1);
                break;
            }
        }
    }

    /**
     * <p>This method does all the operations to be done when an octopus flashes:</p>
     * <p>• Adds one to the energy of surrounding octopuses.</p>
     * <p>• If point which is not captured already attains energy more than 9, recursively calls itself.</p>
     * @param row The row index of the octopus about to flash.
     * @param col The column index of the octopus about to flash.
     */
    public static void flash(int row, int col){
        capturedCoordinates.add(new int[]{row, col});
        for(int i=row-1, c=0; c<3; i++, c++){
            if(i<0 || i>9) continue;
            for(int j=col-1, c2=0; c2<3; c2++, j++){
                if(j<0 || j>9) continue;
                octopi[i][j]++;
                int finalI = i;
                int finalJ = j;
                if(capturedCoordinates.stream().map(r->r[0]==finalI &&r[1]== finalJ).filter(a->a).count()==0 && octopi[i][j]>9)
                    flash(i,j);
            }
        }
    }
}
