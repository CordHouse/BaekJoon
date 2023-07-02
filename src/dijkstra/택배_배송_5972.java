package dijkstra;

/***
 * 23. 07. 02 13시 24분 시작
 * 23. 07. 02 13시 40분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

/**
 * 주어진 조건
 * 1. 현서가 찬홍이에게 배달하러 가는 길에 최대한의 적은 소를 만나서 여물을 줘야한다.
 * 2. 양방향 연결이 되어 있는 길이다.
 * 3. 현서는 1에 있고, 찬홍이는 N에 있다.
 *
 * 제한사항
 * 1 <= N <= 50_000
 * 1 <= M <= 50_000
 * 1 <= A_i, B_i <= N
 * 0 <= C_i <= 1_000
 *
 * 풀이
 * 다익스트라 알고리즘으로 풀어야하는 문제이다.
 * 해당 문제에서 제시한 방향은 이동하는 길마다 소를 만나게 되고, 그 소를 최소한으로 만나는 루트를 선정해
 * 통로의 가중치를 최소화 하라는 의미이다.
 * 따라서 길은 양방향으로 연결되도록 설계하고, 1 -> N까지 이동하면서 각각의 노드에서의 최소 값을 가지도록
 * 해주면된다. 길의 최소 값은 하나의 배열에 저장하고, 방문처리를 해주면서 중복 처리되는 일이 없도록 해야한다.
 */

public class 택배_배송_5972 {
    private static int n, m;
    private static List<Node>[] root;
    private static int[] load;
    private static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        // Step 1. 변수 선언 및 초기화
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);
        load = new int[n+1]; // 각 길 마다의 가중치를 갱신할 변수
        Arrays.fill(load, Integer.MAX_VALUE); // 최소 값을 구해야하기 때문에 처음엔 맥스로 채워준다.

        // Step 2. 노드 정보를 담을 변수 초기화
        root = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            root[i] = new ArrayList<>();
        }

        // Step 3. 입력 값 대입
        // 양방향으로 이어져있이 때문에 cur, next 두 개다 값을 넣어줘야함
        // 단방향이면 하나만 해줘야한다.
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int cur = Integer.parseInt(line[0]);
            int next = Integer.parseInt(line[1]);
            int value = Integer.parseInt(line[2]);

            root[cur].add(new Node(next, value));
            root[next].add(new Node(cur, value));
        }

        // Step 4. 다익스트라 시작, 방문처리 배열도 함께 초기화
        check = new boolean[n+1];
        dijkstra(1);
        System.out.println(load[n]);
    }

    public static void dijkstra(int start) {
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.value - o2.value);
        queue.add(new Node(start, 0)); // 시작 되는 값은 1
        load[start] = 0; // 처음 위치는 거리가 0

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            if(!check[node.next]) {
                check[node.next] = true;
            }

            for(Node nextNode : root[node.next]) {
                // 처음과 다음 가중치를 합한게 기존에 담아놓은 가중치보다 작아야지 갱신 가능능
               if(!check[nextNode.next] && load[nextNode.next] >= node.value + nextNode.value) {
                    load[nextNode.next] = node.value + nextNode.value;
                    queue.add(new Node(nextNode.next, load[nextNode.next]));
                }
            }
        }
    }

    static class Node{
        int next;
        int value;
        public Node(int next, int value) {
            this.next = next;
            this.value = value;
        }
    }
}
