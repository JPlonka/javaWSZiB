public class Main {

    public static void main(String[] args) {
        isTeen(20, 19);
    }

    static void isTeen(int value1, int value2) {
        boolean teenValue1 = false;
        boolean teenValue2 = false;
        if (value1 < 20 && value1 > 12) teenValue1 = true; //check if value1 is teen
        if (value2 < 20 && value2 > 12) teenValue2 = true; //check if value2 is teen

        if ((teenValue1 || teenValue2) && teenValue1 != teenValue2) { //check if one of two values is teen and if they are not both teen
            System.out.println("true");
        }
        else {
            System.out.println("false");
        }

    }
}
