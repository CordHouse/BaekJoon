package dijkstra;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

// 1. 배열 초기화
// 2. 시작할 위치 입력
// 3. 이동하면서 방문여부를 체크하고, 해당 위치의 값과 이동 후 최소 값을 비교해 작은 값으로 갱신
// 4. 반복
public class 최단경로 {
    private static ArrayList<Node>[] graph;
    private static int[] distance;
    private static boolean[] check;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);
        int start = Integer.parseInt(br.readLine());
        check = new boolean[v+1];

        distance = new int[v+1];
        graph = new ArrayList[v+1];
        Arrays.fill(distance, Integer.MAX_VALUE); // 거리 초기화

        // 그래프에 대한 값을 하나하나 List로 만들어준다.
        // List<Node>[] -> Node 로 된 배열이 List 로 구성됨
        for(int i = 1; i <= v; i++) {
            graph[i] = new ArrayList<>();
        }

        // 연관관계를 맺어준다.
        // 단방향임을 조심하자.
        for(int i = 0; i < e; i++) {
            String[] line = br.readLine().split(" ");
            int lineU = Integer.parseInt(line[0]);
            int lineV = Integer.parseInt(line[1]);
            int lineW = Integer.parseInt(line[2]);

            graph[lineU].add(new Node(lineV, lineW));
        }

        // 다익스트라 시작
        dijkstra(start);

        // 출력
        for(int i = 1; i < distance.length; i++) {
            if(distance[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
                continue;
            }
            System.out.println(distance[i]);
        }
    }

    public static void dijkstra(int start) {
        // 우선순위 큐를 쓰는데 커스텀 클래스를 썻기 때문에 기준을 적어줘야한다.
        PriorityQueue<Node> queue = new PriorityQueue<>((o1, o2) -> o1.w - o2.w);
        queue.add(new Node(start, 0));
        distance[start] = 0;

        while(!queue.isEmpty()) {
            Node node = queue.poll();

            // 방문 시 체크
            if(!check[node.v]) {
                check[node.v] = true;
            }

            for(Node n : graph[node.v]) {
                // 이동할 수 있고, 거리가 최소가 되도록 갱신할 수 있는 구간이라면 담아준다.
                if(!check[n.v] && distance[n.v] > n.w + node.w) {
                    distance[n.v] = n.w + node.w;
                    // 거리가 갱신된 값을 담아준다.
                    queue.add(new Node(n.v, distance[n.v]));
                }
            }
        }
    }

    static class Node {
        int v;
        int w;
        public Node(int v, int w) {
            this.v = v;
            this.w = w;
        }
    }
}
