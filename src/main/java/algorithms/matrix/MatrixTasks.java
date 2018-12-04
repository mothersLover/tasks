package algorithms.matrix;

import javafx.beans.property.SimpleListProperty;

import java.text.DecimalFormat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class MatrixTasks {

    public static void main(String... args) {
        List<String> strings = new ArrayList<>();
        int matrix[][] = {{1, 2}, {12, 3}, {5, 1}, {4, 2}, {1, 0}};
//        plusMinus(arr);
//        staircase(6);
//        miniMaxSum(arr);
//        birthdayCakeCandles(arr);
//        String s = timeConversion("07:05:45AM");
//        robot1(matrix);
//        gradingStudents(new int[]{73, 67, 38, 33});
//        countApplesAndOranges(7, 11, 5, 15, new int[]{-2, 2, 1}, new int[]{5, -6});
//        kangaroo(0, 3, 4, 2);
//        int i = saveThePrisoner2(999999999  , 999999998  , 99998394);
//        int i = saveThePrisoner2(12    , 430895283         , 10);
//        System.out.println("i = " + i);
//        long[] array = {6L, 8L, 11L, 15L, 1L, 2L, 4L, 5L};
//        int start = 0;
//        int end = array.length;
//        int middle = (start + end) >>> 1;
//        merge(array, start, middle, end);
        int[] arr1 = {1,4,4,4,5,3};
//        int[] arr2 = {16,32,96};
//        merge(arr1, arr2);
//        int totalX = getTotalX(arr1, arr2);
//        int solve = solve(arr1, 3, 2);
//        int i = divisibleSumPairs(6, 3, arr1);
//        migratoryBirds(arr1);
//        absolutePermutation(3, 0);
//        String[] strings = new String[]{".......", "...O...", "....O..", ".......", "OO.....", "OO....."};
//        normBomberMan(3, strings);
        reverse(1534236469);
    }

    private static int getFraction(int[] a) {
        int fraction = 1;
        for (int i = 0; i < a.length - 1; i++) {
            int mod = a[i + 1] % a[i];
            if (mod == 0) {
                int del = a[i + 1] / a[i];
                if (del % fraction == 0 && fraction == 1 )
                {
                    fraction = del;
                }
            } else {
                int res = a[i + 1] * a[i];
                if (res % fraction == 0) {
                    fraction = res;
                } else {
                    fraction *= res;
                }
            }
        }
        return fraction;
    }

    /*
     * Complete the getTotalX function below.
     */
    static int getTotalX(int[] a, int[] b) {
        /*
         * Write your code here.
         */
        int smallest = 0;
        for (int i : b) {
            smallest = getSmallestFraction(smallest, i);
        }

        int biggest = 1;
        for (int i: a) {
            biggest = getGreatestFraction(biggest, i);
            if (smallest < biggest) {
                return 0;
            }
        }

        if (smallest % biggest != 0) {
            return 0;
        }

//        return smallest / biggest - 1;
        int totalX = 1;

//        for (int i = biggest; i < smallest; i++) {
//            if (smallest % i == 0 && i % biggest == 0) {
//                totalX++;
//            }
//        }

        while (biggest < smallest) {
            biggest += biggest;
            if (smallest % biggest == 0) {
                totalX++;
            }
        }

        return totalX;

    }

    static int getSmallestFraction(int multiplier, int number) {
        while (multiplier > 0 && number > 0) {
            if (multiplier >= number) {
                multiplier %= number;
            } else {
                number %= multiplier;
            }
        }
        return multiplier + number;
    }

    static int getGreatestFraction(int multiplier, int number) {
        return number / getSmallestFraction(multiplier, number) * multiplier;
    }

    /*
    * Complete the getTotalX function below.
    */
    static int getTotalX2(int[] a, int[] b) {
        int fractionA = getFraction(a);
        int fractionB = getFraction(b);
        int fraction = 1;
        if (fractionB >= fractionA && (fractionB % fractionA == 0)) {
            fraction = fractionA;
        } else {
            fraction = fractionA * fractionB;
        }
        int sum = 1;
        int n = a[a.length - 1];
        int m = b[0];
        if (fraction > m) {
            return 0;
        }
        while(n < m) {
            n *= fraction;
            sum++;
        }
        return sum;
    }

    static int saveThePrisoner2(int n, int m, int s) {
        int i = n - s + 1;
        int dif = i - m;
        if (dif >= 0) {
            return n - dif;
        } else {
            int absDiff = Math.abs(dif);

            while (absDiff > n) {
                absDiff -= n;
            }
            return absDiff;
        }
    }

    static int saveThePrisoner(int n, int m, int s) {
        int pos = s;
        for (int i = 1; i < m ; i++) {
            if (pos > n) {
                pos = 0;
            } else {
                pos++;
            }
        }
        return pos;
    }

    static int[] merge(int[] arr1, int[] arr2) {
        int[] ints = new int[arr1.length + arr2.length];
        int threshold = arr1.length > arr2.length ? arr1.length : arr2.length;
        for (int i = 0, j = 0, k = 0; i < threshold; j++) {
            if (k == arr2.length || arr1[i] < arr2[k]) {
                ints[j] = arr1[i++];
            } else {
                ints[j] = arr2[k++];
            }
        }
        return ints;
    }


    private static void merge(long[] array, int start, int middle, int end) {
        long[] buffer = Arrays.copyOfRange(array, start, middle);
        for (int i = 0, j = start, k = middle; i < buffer.length; j++) {
            long a = 0L;
            if (k == end || buffer[i] < array[k]) {
                a = buffer[i++];
            } else {
                a = array[k++];
            }
            array[j] = a;
//            array[j] = (k == end || buffer[i] < array[k]) ? buffer[i++] : array[k++];
        }
    }


    /*
     * Complete the gradingStudents function below.
     */
    static int[] gradingStudents(int[] grades) {
        /*
         * Write your code here.
         */
        for (int i = 0; i < grades.length; i++) {
            int value = grades[i];
            if (value < 38) {
                continue;
            }
            int multiplier = value / 5;
            int nextMultiplier = ++multiplier;
            int nextValue = nextMultiplier * 5;
            int diff = nextValue - value;
            if (diff < 3) {
                grades[i] = nextValue;
            }
        }
        return grades;
    }

    static String kangaroo(int x1, int v1, int x2, int v2) {
        String yes = "YES";
        String no = "NO";

        if (x1 == x2 && v1 == v2) {
            return yes;
        }

        if ((x2 > x1 && v2 > v1) || (x1 > x2 && v1 > v2)) {
            return no;
        }

        int diff = Math.abs(x2 - x1);
        for (int i = 1; ; i++) {
            x1 += v1;
            x2 += v2;
            int newDiff = Math.abs(x2 - x1);
            if (newDiff == 0) {
                return yes;
            }
            if (newDiff > diff || (newDiff == diff)) {
                return no;
            }
            diff = newDiff;
        }
    }

    static void countApplesAndOranges(int s, int t, int a, int b, int[] apples, int[] oranges) {
        collectFruits(s, t, a, apples);
        collectFruits(s, t, b, oranges);
    }

    private static void collectFruits(int s, int t, int a, int[] apples) {
        int appleSum = 0;
        for (int i=0; i < apples.length; i++) {
            int apple = apples[i];
            int appleCoordinate = a + apple;
            if (appleCoordinate >= s && appleCoordinate <= t) {
                appleSum++;
            }
        }
        System.out.println(appleSum);
    }

    static int robot1(int[][] vp) {
        /*
         * Write your code here.
         */
        int n = vp.length;
        double numbers = Math.pow(2, n);
        long approximatelyNumbers = (long) (numbers * 2);
        int finalScore = 0;
        int branchTotalScore = 0;
        for (long i = 0L; i < approximatelyNumbers; i++) {
            if (branchTotalScore > finalScore) {
                finalScore = branchTotalScore;
            }
            branchTotalScore = 0;
            int branchScore = 0;
            int branchEnergy = 0;
            for (int j = 1; j <= n; j++) {
                if (j == n) {
                    branchScore += vp[j - 1][0];
                    branchTotalScore = branchScore;
                    break;
                } else {
                    //int randomNum = rand.nextInt((max - min) + 1) + min;
                    int way = new Random().nextInt(2) + 1;
                    if (way == 1) {
                        branchScore += vp[j - 1][0];
                    } else {
                        branchEnergy = vp[j - 1][1];
                    }
                    if (branchEnergy > 0) {
                        branchEnergy--;
                    }
                    else {
                        break;
                    }
                }
            }
        }
        return finalScore;
    }

    // Complete the staircase function below.
    static void staircase(int n) {
        for (int i = n - 1; i >= 0; i--) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < n; j++) {
                if (j >= i) {
                    s.append("#");
                } else {
                    s.append(" ");
                }
            }
            System.out.println(s.toString());
        }
    }

    static String timeConversion(String s) {
        /*
         * Write your code here.
         */
        if (!s.endsWith("PM") && !s.endsWith("AM")) {
            return s;
        }
        String[] strings = s.split(":");
        int length = strings.length;
        String last = strings[length - 1];
        String pmOrAm = last.substring(2);
        String seconds = last.substring(0, 2);
        strings[length - 1] = seconds;
        boolean isPM = false;
        if (pmOrAm.equals("PM")) {
            isPM = true;
        }
        Integer integer = Integer.valueOf(strings[0]);
        String hour = strings[0];
        if (integer == 12 && isPM) {
            hour = String.valueOf(12);
        } else if (integer == 12 && !isPM) {
            hour = "00";
        } else if (isPM) {
            hour = String.valueOf(integer + 12);
        }
        strings[0] = hour;
        return strings[0] + ":" + strings[1] + ":" + strings[2];
    }

    // Complete the birthdayCakeCandles function below.
    static int birthdayCakeCandles(int[] ar) {
        Arrays.sort(ar);
        int length = ar.length;
        int biggestElement = ar[length - 1];
        int sum = 0;
        for (int anAr : ar) {
            if (anAr == biggestElement) {
                sum++;
            }
        }
        return sum;
    }

    static void miniMaxSum(int[] arr) {
        Arrays.sort(arr);
        long maxSum = 0;
        long minSum = 0;
        for (int i = 0; i < 4; i++) {
            minSum += arr[i];
        }
        for (int i = arr.length - 1; i > arr.length - 5; i--) {
            maxSum += arr[i];
        }
        System.out.println(maxSum);
        System.out.println(minSum);
    }

    static void plusMinus(int[] arr) {
        double pos = 0D;
        double neg = 0D;
        double zer = 0D;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == 0) {
                zer++;
            } else if (arr[i] < 0) {
                neg++;
            } else if (arr[i] > 0) {
                pos++;
            }
        }
        double p = pos / arr.length;
        double n = neg / arr.length;
        double z = zer / arr.length;
        DecimalFormat format = new DecimalFormat("#.######");
        System.out.println(format.format(p));
        System.out.println(format.format(n));
        System.out.println(format.format(z));
    }

    static int[] solve(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new RuntimeException();
        }
        int billScore = 0;
        int aliceScore = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] > b[i]) {
                aliceScore++;
            } else if (a[i] < b[i]) {
                billScore++;
            }
        }
        return new int[]{aliceScore, billScore};
    }

    static int diagonalDifference(int[][] matrix) {
        int primaryDiagonalSum = 0;
        int secondaryDiagonalSum = 0;
        for (int i = 0; i < matrix.length; i++) {
            primaryDiagonalSum += matrix[i][i];
            secondaryDiagonalSum += matrix[i][matrix.length - i];
        }
        return Math.abs(primaryDiagonalSum - secondaryDiagonalSum);
    }

    // Complete the breakingRecords function below.
    static int[] breakingRecords(int[] scores) {
        int max = scores[0];
        int min = scores[0];
        int maxCount = 0;
        int minCount = 0;
        for (int i : scores) {
            if (max < i) {
                max = i;
                maxCount++;
            }
            if (min > i) {
                min = i;
                minCount++;
            }
        }
        return new int[]{maxCount, minCount};
    }

    // Complete the solve function below.
    static int solve(int[] s, int d, int m) {
        int length = s.length;
        if (length < m) {
            return 0;
        }
        if (length == 1) {
            return (m == 1 && s[0] == d) ? 1 : 0;
        }
        int count = 0;
        for (int i = 0; i <= length - m; i++) {
            int sum = 0;
            for (int j = 0; j < m; j++) {
                sum += s[i + j];
            }
            if (sum == d) {
                count++;
            }
        }
        return count;
    }

    // Complete the divisibleSumPairs function below.
    static int divisibleSumPairs(int n, int k, int[] ar) {
        int sum = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((ar[i] + ar[j]) % k == 0) {
                   sum++;
                }
            }
        }
        return sum;
    }

    static int migratoryBirds(int[] ar) {
        Map<Integer, Integer> map = new HashMap<>(ar.length);
        for (int i : ar) {
            if (map.containsKey(i)) {
                Integer v = map.get(i);
                map.put(i, ++v);
            } else {
                map.put(i, 1);
            }
        }

        int key = 0;
        int value = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer entryValue = entry.getValue();
            Integer entryKey = entry.getKey();
            if (entryValue > value) {
                key = entryKey;
                value = entryValue;
            } else if (entryValue == value) {
                key = Math.min(key, entryKey);
            }
        }
        return key;
    }

    // Complete the absolutePermutation function below.
    static int[] absolutePermutation(int n, int k) {
        int[] ints = new int[n];
        if (k > n) {
            return new int[]{-1};
        }
        for (int i = 1; i <= n; i++) {
            int position;
            if (i > k) {
                position = i - k;
            } else {
                position = i + k;
            }
            if (position <= n && ints[position - 1] == 0) {
                ints[position - 1] = i;
                continue;
            }
            position = i + k;
            if (position <= n && ints[position - 1] == 0) {
                ints[position - 1] = i;
                continue;
            }
            return new int[]{-1};
        }
        return ints;
    }

    static int[][] increaseTime(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int n = arr[i][j];
                if (n != -1) {
                    arr[i][j] = ++n;
                }
            }
        }
        return arr;
    }

    static int[][] checkIfTimeToDetonate(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int n = arr[i][j];
                if (n != -1) {
                    if (n == 3) {
                        arr[i][j] = -1;
                        if (i - 1 >= 0) {
                            explodeNeighbor(arr, i - 1, j);
                        }
                        if (i + 1 < arr.length) {
                            explodeNeighbor(arr, i + 1, j);
                        }
                        if (j - 1 >= 0) {
                            explodeNeighbor(arr, i , j - 1);
                        }
                        if (j + 1 < arr.length) {
                            explodeNeighbor(arr, i , j + 1);
                        }
                    }
                }
            }
        }
        return arr;
    }

    private static void explodeNeighbor(int[][] arr, int i, int j) {
        int neighbor = arr[i][j];
        if (neighbor != 3) {
            arr[i][j] = -1;
        }
    }

    static int[][] plantBombs(int[][] arr) {
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                int n = arr[i][j];
                if (n == -1) {
                    arr[i][j] = 0;
                }
            }
        }
        return arr;
    }

    // Complete the bomberMan function below.
    static String[] bomberMan(int n, String[] grid) {
        char bomb = (char)79;
        char empty = '.';
        int rows = grid.length;
        int columns = grid[0].length();
        int[][] arr = new int[rows][columns];
        for (int i =0; i < grid.length; i++) {
            char[] chars = grid[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                char aChar = chars[j];
                if (aChar == bomb) {
                    arr[i][j] = 0;
                } else if (aChar == empty) {
                    arr[i][j] = -1;
                }
            }
        }

        if (n == 1) {
            return grid;
        }
        if (n % 2 == 0) {
            arr = plantBombs(arr);
        } else {
            if (n % 4 == 1) {
                n = 5;
            }
            if (n % 4 == 3) {
                n = 3;
            }
            for (int i = 1; i <= n; i++) {
                arr = increaseTime(arr);
                arr = checkIfTimeToDetonate(arr);
                if (i % 2 == 0) {
                    arr = plantBombs(arr);
                }
            }
        }


        for (int i = 0; i < arr.length; i++) {
            StringBuilder s = new StringBuilder();
            for (int j = 0; j < arr[i].length; j++) {
                if (arr[i][j] == -1) {
                    s.append(empty);
                } else {
                    s.append(bomb);
                }
            }
            grid[i] = s.toString();
        }
        return grid;
    }

    static String[] normBomberMan(int n, String[] grid) {
        char bomb = (char)79;
        char empty = '.';
        int rows = grid.length;
        int columns = grid[0].length();
        char[][] chars = new char[rows][columns];
        int[][] timer = new int[rows][columns];
        int sec = 1;
        for (int i = 0; i < rows; i++) {
            String s1 = grid[i];
            chars[i] = s1.toCharArray();;
        }

        sec++;

        int[] dx = {0, 0, +1, -1};
        int[] dy = {+1, -1, 0, 0};

        if (n == 1) {
            return grid;
        } else if (n % 2 == 0) {
            n = 2;
        } else if (n % 4 == 3) {
            n = 3;
        } else if (n % 4 == 1) {
            n = 5;
        }

        for (int s = sec; s <= n; s++) {

            if (s % 2 == 0) {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (chars[i][j] != bomb) {
                            chars[i][j] = bomb;
                            timer[i][j] = s;
                        }
                    }
                }
            } else {
                for (int i = 0; i < rows; i++) {
                    for (int j = 0; j < columns; j++) {
                        if (chars[i][j] == bomb && timer[i][j] == s - 3) {
                            chars[i][j] = empty;
                            for (int k =0; k < 4; k++) {
                                int di = i + dx[k];
                                int dj = j + dy[k];
                                if (di >= 0 && di < rows && dj >= 0 && dj < columns && (timer[di][dj] != s -3)) {
                                    chars[di][dj] = empty;
                                }
                            }
                        }
                    }
                }
            }
        }

        for (int i = 0; i < rows; i++) {
            grid[i] = String.valueOf(chars[i]);
        }
        return grid;
    }

    public static int reverse(int x) {
        List<Integer> a = new ArrayList();
        while(Math.abs(x) >= 10) {
            a.add(x % 10);
            x /= 10;
        }
        a.add(x);
        double result = 0;
        for(int i=0; i<a.size(); i++) {
            int digit = a.size() - i - 1;
            double v = a.get(i) * Math.pow(10, digit);
            result += v;
            if (result >= Integer.MAX_VALUE || result <= Integer.MIN_VALUE) {
                return 0;
            }
        }
        return (int) result;
    }

}
