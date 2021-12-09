package dayeight;

import utilities.MyUtilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class DayEightPartTwo {
    static ArrayList<String> data, signals, outputCodes;
    static int sum=0;
    final static int ONE_LENGTH=2, SEVEN_LENGTH=3, FOUR_LENGTH=4, EIGHT_LENGTH=7;
    final static String[] displayPatterns={"012456", "25", "02346", "02356", "1235", "01356", "013456", "025", "0123456", "012356"};
    /* Format:
            0                _
        1       2           | |
            3                -
        4       5           | |
            6                -

         Each segment in the seven segment display is assigned a number and the segments required for forming each number is noted.
     */


    public static void main(String[] args) {
        data = MyUtilities.readFile("datadayeight.txt");
        signals = new ArrayList<>();
        outputCodes = new ArrayList<>();
        data.forEach(line->{
            signals.addAll(List.of(line.substring(0, line.indexOf("|")).trim().split(" ")));
            outputCodes.addAll(List.of(line.substring(line.indexOf("|")+1).trim().split(" ")));
        });
        findSignalNumbers();
    }

    public static void findSignalNumbers(){
        String[] numberSignals = new String[10];
        String[] charactersInSignal = new String[7]; //Stores the meaningful orientation of letters in the seven segment display in the format shown above.

        int[] alphabetCount = new int[7]; //Counts the number of alphabets. Index 0 for a, 1 for b, 2 for c and so on.
        for(int i=0; i<signals.size();i++){
            String signal = signals.get(i);
            for(int j=0; j<7;j++) {
                for(int k=0; k<signal.length(); k++){
                    if(signal.charAt(k)==(char)(j+97))
                        alphabetCount[j]++;
                }
            }

            switch(signal.length()){
                case FOUR_LENGTH -> numberSignals[4]=signal;
                case EIGHT_LENGTH -> numberSignals[8]=signal;
                case ONE_LENGTH -> numberSignals[1]=signal;
                case SEVEN_LENGTH -> numberSignals[7]=signal;
            }

            if((i+1)%10==0){   //Code to be executed after all 10 signals in a row have been scanned.
                int[] finalAlphabetCount = alphabetCount; //Variables used in lambda expressions should be final or effectively final so at many places non-final variables have been copied to effectively final ones..
                int[] assignedDuplicateIndices=new int[2]; //Index 0 for storing repetition of position 2, 1 for 3.
                String[] finalCharactersInSignal = charactersInSignal;
                numberSignals[1].chars().forEach(ch->{ //To understand how these work, refer to the comments at the bottom.
                    ch=ch-97;
                    if(finalAlphabetCount[ch]==8){
                        finalCharactersInSignal[2]=Character.toString((char)(ch+97));
                        assignedDuplicateIndices[0]=ch;
                    }
                });
                numberSignals[4].chars().forEach(ch->{
                    ch=ch-97;
                    if(finalAlphabetCount[ch]==7) {
                        finalCharactersInSignal[3] = Character.toString((char) (ch + 97));
                        assignedDuplicateIndices[1]=ch;
                    }
                });
                for(int j=0; j<alphabetCount.length; j++){
                    if(alphabetCount[j]==6)
                        charactersInSignal[1]=Character.toString((char)(j+97));
                    else if(alphabetCount[j]==4)
                        charactersInSignal[4]=Character.toString((char)(j+97));
                    else if(alphabetCount[j]==9)
                        charactersInSignal[5]=Character.toString((char)(j+97));
                    if(alphabetCount[j]==8 && assignedDuplicateIndices[0]!=j)  //If character at that index has not already been assigned, add to the signal.
                        charactersInSignal[0]=Character.toString((char)(j+97));
                    if(alphabetCount[j]==7 && assignedDuplicateIndices[1]!=j)
                        charactersInSignal[6]=Character.toString((char)(j+97));
                }

                int extractOutputValue = getOutputValue(i/10, charactersInSignal);
                sum+=extractOutputValue;

                alphabetCount = new int[7];
                numberSignals = new String[10];
                charactersInSignal = new String[7];
            }

        }
        System.out.println(sum);
    }

    public static int getOutputValue(int index, String[] charactersInSignals){
        String output="";
        String[] sortedOutputCode = new String[4];   //Stores the outputCodes after sorting them in alphabetical order
        String[] parsedPatterns = new String[10];    //Finds the String representation of each number based on the seven-segment patterns
        Arrays.fill(sortedOutputCode, "");
        Arrays.fill(parsedPatterns, "");
        for(int i=4*index; i<4*index+4; i++){       //Each row has 4 output values
            AtomicReference<String> sorted = new AtomicReference<>("");
            String outputCode= outputCodes.get(i);
            outputCode.chars().sorted().forEach(ch->{
                sorted.updateAndGet(v -> v + (char) ch);
            });
            sortedOutputCode[i-4*index]=sorted.get();
        }
        for(int i=0; i<10; i++){
            String numCode = "";
            for(int k=0; k<displayPatterns[i].length();k++){
                numCode = numCode + charactersInSignals[Integer.parseInt(String.valueOf(displayPatterns[i].charAt(k)))];
                // Gets each number represented in the segments specific to that row of signals.

            }
            int finalI = i;
            numCode.chars().sorted().forEach(ch->{
                parsedPatterns[finalI]+=(char)ch;  //Adds the characters of each String representation of number based on the charactersInSignal of each row.
            });
        }
        for(int i=0; i<sortedOutputCode.length; i++){
            for(int j=0; j<10; j++){
                if(sortedOutputCode[i].equals(parsedPatterns[j]))
                    output+=j; //Appends the number to the output if the representation is detected.
            }
        }
        return Integer.parseInt(output);
    }
}


/*
LOGIC BEHIND LINES 62-87

First, all the numbers 0-9 are represented in seven-segment display form.
Then the number of occurrences of each segment are counted. This gives the
following result:

    8(0)
6(1)      8(2)
    7(3)
4(4)      9(5)
    7(6)

From this, it can be deduced that the character which appears 6 times in the
signals is at position 1, 4 times is at position 4 and 9 times is at position
5. But there is a conflict for 4 positions - a character appearing 8 times can
go to either position 0 or 2 and the character appearing 7 times can be at 3
or 6. To deduce which character appearing 8 times goes to segment 2, the characters
in the seven segment representation of 1 are taken. The character which is present
in the representation of 1 will go to position 2 and the character which is not
goes to position 0. assignedDuplicateIndices array stores the indices which were
added to the charactersInSignal. After deducing the position of various characters
in the seven segment display, the output values can be analysed and deduced
before adding all of them to get the sum.
 */