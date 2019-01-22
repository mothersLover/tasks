package test.datastructures.trees.segment;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class SegmentTreeTest {
    private SegmentTree segmentTree;

    @Before
    public void prepareTree() {
        int[] arr = new int[] {1,8,2,7,3,6,4,5};
        segmentTree = new SegmentTree(arr);
    }

    @Test
    public void segmentTreeBuildTest() {
        int[] tree = segmentTree.getTree();
        int[] supposedResult = new int[] {0,8,8,6,8,7,6,5,1,8,2,7,3,6,4,5,0,0};
        boolean equals = Arrays.equals(supposedResult, tree);
        Assert.assertTrue(equals);
    }

    @Test
    public void segmentTreeUpdateTest() {
        segmentTree.update(0, 9);
        segmentTree.update(6, 5);
        segmentTree.update(4, 8);
        int[] tree = segmentTree.getTree();
        int[] supposedResult = new int[] {0,9,9,8,9,7,8,5,9,8,2,7,8,6,5,5,0,0};
        boolean equals = Arrays.equals(supposedResult, tree);
        Assert.assertTrue(equals);
    }

    @Test
    public void segmentTreeIncrementRangeTest() {
        segmentTree.incrementRange(6, 7, 1);
        int[] supposedResult = new int[] {0,8,8,6,8,7,6,6,1,8,2,7,3,6,5,6,0,0};
        int[] tree = segmentTree.getTree();
        boolean equals = Arrays.equals(supposedResult, tree);
        Assert.assertTrue(equals);
    }

    @Test
    public void segmentTreeUpdateRangeTest() {
        segmentTree.updateRange(6, 7, 1);
        int[] supposedResult = new int[] {0,8,8,6,8,7,6,1,1,8,2,7,3,6,1,1,0,0};
        int[] tree = segmentTree.getTree();
        boolean equals = Arrays.equals(supposedResult, tree);
        Assert.assertTrue(equals);
    }

    @Test
    public void segmentTreeQueryTest() {
        int query = segmentTree.query(3, 7);
        Assert.assertEquals(7 , query);
    }
}
