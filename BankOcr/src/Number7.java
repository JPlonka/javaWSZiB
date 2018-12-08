public class Number7 extends Number {
    private char[][] number;
    private int value;

    Number7(){
        number = new char [3][3];
        number[0][0] = ' ';
        number[0][1] = '_';
        number[0][2] = ' ';
        number[1][0] = ' ';
        number[1][1] = ' ';
        number[1][2] = '|';
        number[2][0] = ' ';
        number[2][1] = ' ';
        number[2][2] = '|';

        value = 7;
    }


    @Override
    public char[][] getNumber() {
        return number;
    }

    @Override
    public int getValue() {
        return value;
    }
}
