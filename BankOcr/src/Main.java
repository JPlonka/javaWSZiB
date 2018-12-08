import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Main {



    public static void main(String[] args) {
        File file = new File("bank_ocr_dojo_us3");


        ArrayList<Number> numbers = new ArrayList<>();

        numbers.add(new Number0());
        numbers.add(new Number1());
        numbers.add(new Number2());
        numbers.add(new Number3());
        numbers.add(new Number4());
        numbers.add(new Number5());
        numbers.add(new Number6());
        numbers.add(new Number7());
        numbers.add(new Number8());
        numbers.add(new Number9());

        String line1;
        String line2;
        String line3;
        String emptyLine;
        String outputNumber;
        int tempOutputNumber;
        int foundNumber;
        char[][] checking = new char[3][3];
        int checksum;
        boolean illegibleNumber;

        try {
            PrintWriter out = new PrintWriter("output.txt");
            Scanner sc = new Scanner(file);
            while (sc.hasNextLine()) {
                //SETTING UP FOR NEXT NUMBER
                outputNumber = "";
                foundNumber = 0;
                tempOutputNumber = 0;
                checksum = 0;
                illegibleNumber = false;

                //READING LINES FROM A FILE
                line1 = sc.nextLine();
                line2 = sc.nextLine();
                line3 = sc.nextLine();
                emptyLine = sc.nextLine();

                int row = 0;
                for(int i = 0; i < 9; i++) {
                    for( int j = 0; j < 3 && row < 27; j++) {
                        if(line1.length() > 0) {
                            checking[0][j] = line1.charAt(row);
                        } else {
                            checking[0][j] = ' ';
                        }
                        checking[1][j] = line2.charAt(row);
                        checking[2][j] = line3.charAt(row);
                        row++;
                    }

                    //CHECK WHICH NUMBER IS THIS

                    for(int q = 0; q < 10; q++) {
                        if (Arrays.deepEquals(checking, numbers.get(q).getNumber())) {
                            outputNumber += Integer.toString(numbers.get(q).getValue());
                            tempOutputNumber = numbers.get(q).getValue();
                            foundNumber++;
                        }
                    }

                    if (foundNumber == i) {
                        outputNumber += "?";
                        illegibleNumber = true;
                        foundNumber++;
                    }

                    //CHECKSUM

                    if(!illegibleNumber) {
                        checksum += (9 - i) * tempOutputNumber;
                    }

                }

                if(!illegibleNumber) {
                    if(checksum % 11 != 0) {
                        outputNumber += " ERR";
                    }
                } else {
                    outputNumber += " ILL";
                }

                System.out.println(outputNumber);
                out.println(outputNumber);
            }

            out.close();


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }
}
