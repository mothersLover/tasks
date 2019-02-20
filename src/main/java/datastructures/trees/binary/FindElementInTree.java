package test.datastructures.trees.binary;

import org.junit.Assert;
import org.junit.Test;

import static test.datastructures.trees.binary.DeleteNodeFromBinarySearchTree.fillTree;

public class FindElementInTree {

    @Test
    public void test() {
        int[] ints = new int[] {6,4,2,5,1,3,8,7,9,10};
        DeleteNodeFromBinarySearchTree.TreeNode root = fillTree(ints);
        boolean elementPresent = isElementPresent(root, 8);
        Assert.assertTrue(elementPresent);
        boolean elementPresent1 = isElementPresent(root, 12);
        Assert.assertFalse(elementPresent1);
    }

    public static boolean isElementPresent(DeleteNodeFromBinarySearchTree.TreeNode root, int value) {
        if (root == null) {
            return false;
        }
        if (root.value == value) {
            return true;
        }
        if (value < root.value) {
            return isElementPresent(root.left, value);
        } else {
            return isElementPresent(root.right, value);
        }
    }
}
