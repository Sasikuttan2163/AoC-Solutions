package dayten;

import utilities.MyUtilities;

import java.util.ArrayList;

public class DayTenPartTwo {
    static ArrayList<String> data;
    static ArrayList<String> corruptedLineIndices;
    static ArrayList<Long> sumOfPoints;
    static String[] chunkInitiators = {"(", "{", "<", "["};
    static String[] chunkTerminators = {")", "}", ">", "]"};
    public static void main(String[] args) {
        data= MyUtilities.readFile("datadayten.txt");
        sumOfPoints=new ArrayList<>();
        corruptedLineIndices = new ArrayList<>();
        removeCorruptedLines();
        for(int i=0; i<data.size(); i++){
            String line = data.get(i);
            while(hasNestedBrackets(line))
                line=clearBrackets(line);
            data.set(i, line);
        }
        for (String line : data) {
            long points = 0;
            for (int j = line.length() - 1; j >= 0; j--) {
                switch (line.charAt(j)) {
                    case '(' -> points = points * 5 + 1;
                    case '[' -> points = points * 5 + 2;
                    case '{' -> points = points * 5 + 3;
                    case '<' -> points = points * 5 + 4;
                }
            }
            sumOfPoints.add(points);
        }
        sumOfPoints.sort(Long::compare);
        System.out.println(sumOfPoints.get(sumOfPoints.size()/2));
    }
    public static String clearBrackets(String l){
        return l.replace("()", "").replace("{}", "").replace("<>", "").replace("[]","");
    }
    public static boolean hasNestedBrackets(String l){
        return l.contains("()")||l.contains("[]")||l.contains("{}")||l.contains("<>");
    }
    public static void removeCorruptedLines(){
        for(int k=0; k<data.size(); k++){
            String a=data.get(k);
            while(hasNestedBrackets(a)){
                a=clearBrackets(a);
            }
            label:
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    if(hasNestedBrackets(a.replace(chunkTerminators[i], chunkTerminators[j]))) {
                        corruptedLineIndices.add(data.get(k));
                        break label;
                    }
                }
            }
        }
        corruptedLineIndices.forEach(line->{
            data.remove(line);
        });
    }
}
