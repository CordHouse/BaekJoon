package tree;

/***
 * 23. 06. 20 15시 20분 시작
 * 23. 06. 20 16시 15분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 전위, 중위, 후위로 출력할 것
 *
 * 제한사항
 * 1 <= N <= 26
 *
 * 풀이
 * 전위, 후위, 중위와 같은 탐색 문제가 주어진다면 트리 문제로 봐야한다.
 * 트리는 Node 클래스를 만들어 root, left, right 로 구분하고 접근하는게 가장 좋다.
 * 출력의 경우 어디서 출력문을 만들어 줄지에 따라 전위를 기준으로 가장 앞, 중간, 후 로 나눌 수 있다.
 */
public class 트리_순회_1991 {
    private static Node node = new Node('A', null, null);
    private static int n;
    private static StringBuilder sb = new StringBuilder();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n; i++) {
            char[] line = br.readLine().toCharArray();
            addNode(node, line[0], line[2], line[4]);
        }
        // 전위
        preorder(node);
        sb.append("\n");
        // 중위
        inorder(node);
        sb.append("\n");
        // 후위
        postorder(node);
        System.out.println(sb.toString());
    }

    // 전위
    public static void preorder(Node node) {
        if(node == null) return;
        sb.append(node.root);
        preorder(node.left);
        preorder(node.right);
    }

    // 중위
    public static void inorder(Node node) {
        if(node == null) return;
        inorder(node.left);
        sb.append(node.root);
        inorder(node.right);
    }

    // 후위
    public static void postorder(Node node) {
        if(node == null) return;
        postorder(node.left);
        postorder(node.right);
        sb.append(node.root);
    }

    // 트리 만들기
    public static void addNode(Node node, char root, char left, char right) {
        if(node.root == root) {
            node.left = left != '.' ? new Node(left, null, null) : null;
            node.right = right != '.' ? new Node(right, null, null) : null;
        } else {
            if(node.left != null) {
                addNode(node.left, root, left, right);
            }
            if(node.right != null) {
                addNode(node.right, root, left, right);
            }
        }
    }

    // 노드가 노트타입을 가지고 있기 때문에 재귀와 같은 효과가 발생한다.
    static class Node{
        char root;
        Node left;
        Node right;
        public Node(char root, Node left, Node right) {
            this.root = root;
            this.left = left;
            this.right = right;
        }
    }
}
