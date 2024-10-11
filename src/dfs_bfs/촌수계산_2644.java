package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class 촌수계산_2644 {

    private static boolean[] visit;
    private static ArrayList<Node>[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(br.readLine());

        arr = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            arr[i] = new ArrayList<>();
        }

        for(int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            arr[p].add(new Node(c, 0));
            arr[c].add(new Node(p, 0));
        }
        visit = new boolean[n+1];
        cal(start, end);
    }
    private static void cal(int start, int end) {
        PriorityQueue<Node> pq = new PriorityQueue<>((o1, o2) -> o2.count - o1.count);
        pq.add(new Node(start, 0));
        visit[start] = true;

        while(!pq.isEmpty()) {
            Node node = pq.poll();

            if(node.next == end) {
                System.out.println(node.count);
                return;
            }

            if(!visit[node.next]) {
                visit[node.next] = true;
            }

            for(Node nextNode : arr[node.next]) {
                if(!visit[nextNode.next]) {
                    pq.add(new Node(nextNode.next, node.count + 1));
                }
            }
        }
        System.out.println("-1");
    }

    static class Node {
        int next;
        int count;

        Node(int next, int count) {
            this.next = next;
            this.count = count;
        }
    }
}
