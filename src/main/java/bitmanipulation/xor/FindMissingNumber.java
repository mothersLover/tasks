package bitmanipulation.xor;

public class FindMissingNumber {

    public static void main(String... args) {
        int[] arr = new int[] {0,1,2,3,4,6,7,8,9};
        int missingNumber = findMissingNumber(arr, 9);
        System.out.println("missingNumber = " + missingNumber);

        int[] arr1 = new int[] {0,0,1,1,2,2,3,3,4,5,5};
        findExtra(arr1, 5);
    }

    static int findMissingNumber(int[] arr, int range) {
        int xorSum = 0;
        for (int i = 0; i <= range; i++) {
            xorSum ^= i;
        }

        for (int anArr : arr) {
            xorSum ^= anArr;
        }

        return xorSum;
    }

    static int findExtra(int[] arr, int range) {
        int xorSum = 0;

//        for (int i = 0; i <= range; i++) {
//            xorSum ^= i;
//        }
//
//        for (int i = 0; i <= range; i++) {
//            xorSum ^= i;
//        }

        for (int anArr : arr) {
            xorSum ^= anArr;
        }

        return xorSum;
    }
}
