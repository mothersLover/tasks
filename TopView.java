package test.datastructures.trees.binary;

import java.util.*;

public class TopView {

    static class Node {
        Node left;
        Node right;
        int data;

        Node(int data) {
            this.data = data;
            left = null;
            right = null;
        }
    }

    static class NodeWrapper implements Comparable<NodeWrapper> {
        Node node;
        int dist;

        NodeWrapper(Node node, int dist) {
            this.node = node;
            this.dist = dist;
        }

        @Override
        public int compareTo(NodeWrapper o) {
            return Integer.compare(o.dist, this.dist);
        }
    }

    public static void topView2(Node root) {
        recursionLeft(root.left);
        System.out.print(root.data + " ");
        recursionRight(root.right);
    }

    public static List<NodeWrapper> topViewCorrect(Node root) {
        ArrayDeque<NodeWrapper> nodes = new ArrayDeque<>();
        List<NodeWrapper> nodeWrapperList = new ArrayList<>();
        nodes.addFirst(new NodeWrapper(root, 0));
        Set<Integer> distances = new HashSet<>();
        while (!nodes.isEmpty()) {
            NodeWrapper nodeWrapper = nodes.pollFirst();
            Node node = nodeWrapper.node;
            int dist = nodeWrapper.dist;
            if (!distances.contains(dist)) {
                distances.add(dist);
                nodeWrapperList.add(nodeWrapper);
            }
            Node left = node.left;
            Node right = node.right;
            if (left != null) {
                nodes.addLast(new NodeWrapper(left, dist - 1));
            }
            if (right != null) {
                nodes.addLast(new NodeWrapper(right, dist + 1));
            }
        }
        nodeWrapperList.sort(Collections.reverseOrder());
        return nodeWrapperList;
    }

    private static void recursionLeft(Node root) {
        if (root != null) {
            recursionLeft(root.left);
            System.out.print(root.data + " ");
        }
    }

    private static void recursionRight(Node root) {
        if (root != null) {
            System.out.print(root.data + " ");
            recursionRight(root.right);
        }
    }

    public static void topView(Node root) {
        if(root != null) {
            ArrayDeque<Node> a = new ArrayDeque();
            Map<Node, Boolean> b = new HashMap();
            System.out.print(root.data + " ");
            Node left = root.left;
            Node right = root.right;
            if (left != null) {
                a.addLast(root.left);
                b.put(root.left, false);
            }
            if (right != null) {
                a.addLast(root.right);
                b.put(root.right, true);
            }

            while(!a.isEmpty()) {
                root = a.pollFirst();
                System.out.print(root.data + " ");
                Boolean node = b.get(root);
                if (node != null && node == false) {
                    left = root.left;
                    if (left != null) {
                        a.addLast(root.left);
                        b.put(root.left, false);
                    }
                }
                if (node != null && node == true) {
                    right = root.right;
                    if (right != null) {
                        a.addLast(root.right);
                        b.put(root.right, true);
                    }
                }
            }


        }
    }

    public static Node insert(Node root, int data) {
        if(root == null) {
            return new Node(data);
        } else {
            Node cur;
            if(data <= root.data) {
                cur = insert(root.left, data);
                root.left = cur;
            } else {
                cur = insert(root.right, data);
                root.right = cur;
            }
            return root;
        }
    }

    public static void main(String[] args) {
        String nodes = "37 23 108 59 86 64 94 14 105 17 111 65 55 31 79 97 78 25 50 22 66 46 104 98 81 90 68 40 103 77 74 18 69 82 41 4 48 83 67 6 2 95 54 100 99 84 34 88 27 72 32 62 9 56 109 115 33 15 91 29 85 114 112 20 26 30 93 96 87 42 38 60 7 73 35 12 10 57 80 13 52 44 16 70 8 39 107 106 63 24 92 45 75 116 5 61 49 101 71 11 53 43 102 110 1 58 36 28 76 47 113 21 89 51 19 3";
        String nodes2 = "1 2 5 3 6 4";
        Node root = getNode(nodes);

        printTree(root);
        System.out.println(" ================================================");
        List<NodeWrapper> wrappers = topViewCorrect(root);
        System.out.println(" ================================================");
        for (NodeWrapper nodeWrapper : wrappers) {
            System.out.print(nodeWrapper.node.data + " ");
        }
    }

    private static Node getNode(String nodes) {
        String[] split = nodes.split(" ");
        Node root = null;
        for (String s : split) {
            int n = Integer.valueOf(s);
            root = insert(root, n);
        }
        return root;
    }

    public static void printTree(Node root) {
        Map<Integer, List<NodeWrapper>> map = new HashMap<>();
        levelTraversal(root, map);

        for (Map.Entry<Integer, List<NodeWrapper>> entry : map.entrySet()) {
            List<NodeWrapper> value = entry.getValue();
            System.out.println();
            for (NodeWrapper node : value) {
                System.out.print(node.node.data + "  ");
            }
            System.out.println();
        }
    }

    private static void levelTraversal(Node root, Map<Integer, List<NodeWrapper>> map) {
        if (root != null) {
            ArrayDeque<NodeWrapper> nodes = new ArrayDeque<>();
            nodes.addFirst(new NodeWrapper(root, 0));
            while (!nodes.isEmpty()) {
                NodeWrapper nodeWrapper = nodes.pollFirst();
                int level = nodeWrapper.dist;
                Node node = nodeWrapper.node;
                if (map.containsKey(level)) {
                    List<NodeWrapper> nodeWrappers = map.get(level);
                    nodeWrappers.add(nodeWrapper);
                    map.put(level, nodeWrappers);
                } else {
                    ArrayList<NodeWrapper> nodeWrappers = new ArrayList<>();
                    nodeWrappers.add(nodeWrapper);
                    map.put(level, nodeWrappers);
                }
                Node left = node.left;
                Node right = node.right;
                level++;
                if (left != null) {
                    nodes.addLast(new NodeWrapper(left, level));
                }
                if (right != null) {
                    nodes.addLast(new NodeWrapper(right, level));
                }
            }
        }
    }
}
