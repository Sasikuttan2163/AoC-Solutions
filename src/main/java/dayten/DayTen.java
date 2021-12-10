package dayten;

import utilities.MyUtilities;

import java.util.ArrayList;

public class DayTen {
    static ArrayList<String> data;
    static ArrayList<String> corruptionCharacters;
    static String[] chunkInitiators = {"(", "{", "<", "["};
    static String[] chunkTerminators = {")", "}", ">", "]"};
    public static void main(String[] args) {
        data= MyUtilities.readFile("datadayten.txt");
        corruptionCharacters = new ArrayList<>();
        data.forEach(line->{
            String a=line;
            while(hasNestedBrackets(a)){
                a=clearBrackets(a);
            }
            label:
            for(int i=0; i<4; i++){
                for(int j=0; j<4; j++){
                    if(hasNestedBrackets(a.replace(chunkTerminators[i], chunkTerminators[j]))) {
                        corruptionCharacters.add(chunkTerminators[i]);
                        break label;
                    }
                }
            }
        });
        System.out.println(corruptionCharacters.stream().mapToInt(a->switch(a){
            case ")": yield 3;
            case "]": yield 57;
            case "}": yield 1197;
            case ">": yield 25137;
            default: yield 0;
        }).sum());
    }
    public static String clearBrackets(String l){
        return l.replace("()", "").replace("{}", "").replace("<>", "").replace("[]","");
    }
    public static boolean hasNestedBrackets(String l){
        return l.contains("()")||l.contains("[]")||l.contains("{}")||l.contains("<>");
    }
}
