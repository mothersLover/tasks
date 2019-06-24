package algorithms.sort;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

public class FraudulentActivityNotifications {

    @Test
    public void test() {
        int[] ints1 = new int[] {7,8,9,2,3,4,10,5,6};
        int result = activityNotifications(ints1, 4);
        Assert.assertEquals(1, result);
        int[] ints2 = new int[] {2, 3, 4, 2, 3, 6, 8, 4, 5};
        result = activityNotifications(ints2, 5);
        Assert.assertEquals(2, result);
        int[] ints3 = new int[] {1, 2, 3, 4, 4};
        result = activityNotifications(ints3, 4);
        Assert.assertEquals(0, result);
    }

    static int activityNotifications(int[] expenditure, int d) {
        int result = 0;
        if (d >= expenditure.length) {
            return result;
        }
        int[] sortArray = new int[d];
        System.arraycopy(expenditure, 0, sortArray, 0, d);
        Arrays.sort(sortArray);

        for (int i = d; i < expenditure.length; i++) {
            int daySpent = expenditure[i];
            float middleValue = getMiddleValue(sortArray);
            float daySpentFloat = daySpent + 0.0F;
            if (middleValue * 2 <= daySpentFloat) {
                result++;
            }
            int valueToKick = expenditure[i - d];
            shiftArray(sortArray, valueToKick);
            addNewDay(sortArray, expenditure[i]);
        }
        return result;
    }

    private static void addNewDay(int[] arr, int valueToAdd) {
        int left = 0;
        int right = arr.length - 1;
        int index = -1;
        while (left <= right) {
            int middlePos = (left + right) >>> 1;
            int middleValue = arr[middlePos];
            if (middleValue < valueToAdd) {
                left = middlePos + 1;
            } else if (middlePos > valueToAdd) {
                right = middlePos - 1;
            } else {
                index = middlePos;
                break;
            }
            index = left;
        }
        if (index >= arr.length - 1) {
            arr[arr.length - 1] = valueToAdd;
        } else {
            System.arraycopy(arr, index, arr, index + 1, arr.length - 1 - index);
            arr[index] = valueToAdd;
        }
    }

    private static void shiftArray(int[] arr, int valueToKick) {
        int positionToKick = Arrays.binarySearch(arr, valueToKick);
        System.arraycopy(arr, positionToKick + 1, arr, positionToKick, arr.length - 1 - positionToKick);
    }

    private static float getMiddleValue(int[] arr) {
        if (arr.length % 2 == 0) {
            int leftMiddle = (arr.length - 1) >>> 1;
            int rightMiddle = leftMiddle + 1;
            int intermediateResult = arr[leftMiddle] + arr[rightMiddle];
            if (intermediateResult % 2 == 0) {
                return intermediateResult >>> 1;
            }
            return (intermediateResult >>> 1) + 0.5f;
        }
        return arr[(arr.length - 1) >>> 1];
    }
}
