public class Main {

    public static void main(String[] args) {

        sum(1, 13, 12);
    }

    static void sum(int value1, int value2, int value3) {
        int[] values = {value1, value2, value3}; //setting values as array to iterate
        int result = 0;
        int i = 0;
        while(i < 3 && values[i] != 13) { //check if we iterated through every value and check if given value is different from 13
            result += values[i]; //summing values
            i++;
        }
        System.out.println("Result is: " + result);
    }
}
