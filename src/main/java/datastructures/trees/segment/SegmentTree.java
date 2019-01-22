package test.datastructures.trees.segment;

public class SegmentTree {
    public int getElementCount() {
        return elementCount;
    }

    private int elementCount;

    public int getTreeSize() {
        return treeSize;
    }

    private int treeSize;

    public int[] getTree() {
        return tree;
    }

    private int[] tree;
    private int[] lazy;

    public SegmentTree(int[] array) {
        elementCount = array.length;
        treeSize = elementCount * 2 + 2; // additional element just in case
        tree = new int[treeSize];
        lazy = new int[treeSize];
        build(1, array, 0, elementCount - 1);
    }

    private void build(int node, int[] array, int start, int end) {
        if (start == end) {
            tree[node] = array[start];
        } else {
            int middle = (start + end) / 2;
            build(node * 2, array, start, middle);
            build(node * 2 + 1, array, middle + 1, end);
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    public void update(int index, int value) {
        update(1, 0, elementCount - 1, index, value);
    }

    public void updateLazy(int index, int value) {
        updateLazy(1, 0, elementCount - 1, index, value);
    }

    private void update(int node, int start, int end, int index, int value) {
        if (start == end) {
            tree[node] = value;
        } else {
            int middle = (start + end) / 2;
            if (index >= start && index <= middle) {
                update(node * 2, start, middle, index, value);
            } else {
                update(node * 2 + 1, middle + 1, end, index, value);
            }
            tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
        }
    }

    private void updateLazy(int node, int start, int end, int index, int value) {
        if (start == end) {
            lazy[node] = value;
        } else {
            int middle = (start + end) / 2;
            if (index >= start && index <= middle) {
                updateLazy(node * 2, start, middle, index, value);
            } else {
                updateLazy(node * 2 + 1, middle + 1, end, index, value);
            }
        }
    }

    public void incrementRange(int left, int right, int value) {
        incrementRange(1, 0, elementCount - 1, left, right, value);
    }

    public void incrementRangeLazy(int left, int right, int value) {
        incrementRangeLazy(1, 0, elementCount - 1, left, right, value);
    }

    private void incrementRange(int node, int start, int end, int left, int right, int value) {
        if (start > end || start > right || end < left) {
            return;
        } else {
            if (start == end) {
                tree[node] += value;
            } else {
                int middle = (start + end) / 2;
                incrementRange(node * 2, start, middle, left, right, value);
                incrementRange(node * 2 + 1, middle + 1, end, left, right, value);
                tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
            }
        }
    }

    private void incrementRangeLazy(int node, int start, int end, int left, int right, int value) {
        if (start > end || start > right || end < left) {
            return;
        }
        if (start >= left && end <= right) {
            lazy[node] += value;
        } else {
            int middle = (start + end) / 2;
            incrementRangeLazy(node * 2, start, middle, left, right, value);
            incrementRangeLazy(node * 2 + 1, middle + 1, end, left, right, value);
        }
    }

    public void updateRange(int left, int right, int value) {
        updateRange(1, 0, elementCount - 1, left, right, value);
    }

    private void updateRange(int node, int start, int end, int left, int right, int value) {
        if (start > end || start > right || end < left) {
            return;
        } else {
            if (start == end) {
                tree[node] = value;
            } else {
                int middle = (start + end) / 2;
                updateRange(node * 2, start, middle, left, right, value);
                updateRange(node * 2 + 1, middle + 1, end, left, right, value);
                tree[node] = Math.max(tree[node * 2], tree[node * 2 + 1]);
            }
        }
    }

    public int query(int left, int right) {
        return query(1, 0, elementCount - 1, left, right);
    }

    public int queryLazy(int left, int right) {
        return queryLazy(1, 0, elementCount - 1, left, right);
    }

    private int query(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        } else {
            int middle = (start + end) / 2;
            int leftValue = query(node * 2, start, middle, left, right);
            int rightValue = query(node * 2 + 1, middle + 1, end, left, right);
            return Math.max(leftValue, rightValue);
        }
    }

    private int queryLazy(int node, int start, int end, int left, int right) {
        if (start > right || end < left) {
            return 0;
        }
        if (lazy[node] != 0) {
            tree[node] += lazy[node];
            if (start != end) {
                lazy[node * 2] += lazy[node];
                lazy[node * 2 + 1] += lazy[node];
            }
            lazy[node] = 0;
        }
        if (left <= start && end <= right) {
            return tree[node];
        } else {
            int middle = (start + end) / 2;
            int leftValue = queryLazy(node * 2, start, middle, left, right);
            int rightValue = queryLazy(node * 2 + 1, middle + 1, end, left, right);
            return Math.max(leftValue, rightValue);
        }
    }
}
