import java.io.*;
import java.util.*;

public class Main {
    static class Node {
        int num;
        Node left;
        Node right;
        public Node(int num) {
            this.num = num;
        }
    }
    static Node root;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));

        int V = Integer.parseInt(br.readLine());

        for (int i = 0; i < V; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int num = st.nextToken().charAt(0);
            int left = st.nextToken().charAt(0);
            int right = st.nextToken().charAt(0);

            addNode(num, left == '.' ? -1 : left, right == '.' ? -1 : right);
        }
        
        preOrder(root);
        sb.append('\n');
        inOrder(root);
        sb.append('\n');
        postOrder(root);
        System.out.println(sb.toString());
    }

    static void addNode(int num, int leftNum, int rightNum) {
        if (root == null) {
            root = new Node(num);
            if (leftNum != -1) {
                root.left = new Node(leftNum);
            }
            if (rightNum != -1) {
                root.right = new Node(rightNum);
            }
        } else {
            searchNode(root, num, leftNum, rightNum);
        }
    }

    static void searchNode(Node node, int num, int leftNum, int rightNum) {
        if (node == null) {
            return;
        } else if (node.num == num){
            if (leftNum != -1) {
                node.left = new Node(leftNum);
            }
            if (rightNum != -1) {
                node.right = new Node(rightNum);
            }
        } else {
            searchNode(node.left, num, leftNum, rightNum);
            searchNode(node.right, num, leftNum, rightNum);
        }
    }

    // 전위 순회 Root -> left -> right
    static void preOrder(Node node) {
        if (node != null) {
            sb.append((char)node.num);
            if (node.left != null) preOrder(node.left);
            if (node.right != null) preOrder(node.right);
        }
    }

    // 중위 순회 left -> Root -> right
    static void inOrder(Node node) {
        if (node != null) {
            if (node.left != null) inOrder(node.left);
            sb.append((char)node.num);
            if (node.right != null) inOrder(node.right);
        }
    }

    // 후위 순회 left -> right -> root
    static void postOrder(Node node) {
        if (node != null) {
            if (node.left != null) postOrder(node.left);
            if (node.right != null) postOrder(node.right);
            sb.append((char)node.num);
        }
    }

}