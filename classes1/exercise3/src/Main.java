public class Main {

    public static void main(String[] args) {
        int[] arr = {3,2,14,1,2,3,6};
        sequenceAppears(arr);
    }

    static void sequenceAppears (int[] array) {
        for (int i = 0; i < array.length-2; i++) { //array.length-2 => there have to be two more numbers in an array
            if (array[i] == 1) { //check if given number equals 1
                if (array[i+1] == 2 && array[i+2] == 3) //check if two following numbers are 2 and 3
                    System.out.println("true");
            }
        }
    }
}
