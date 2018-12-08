public class Number3 extends Number {
    private char[][] number;
    private int value;

    Number3(){
        number = new char [3][3];
        number[0][0] = ' ';
        number[0][1] = '_';
        number[0][2] = ' ';
        number[1][0] = ' ';
        number[1][1] = '_';
        number[1][2] = '|';
        number[2][0] = ' ';
        number[2][1] = '_';
        number[2][2] = '|';

        value = 3;
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
