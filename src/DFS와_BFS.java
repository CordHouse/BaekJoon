import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class DFS와_BFS {
    static boolean[] DFS_check, BFS_check; // 방문 체크
    static LinkedList<Integer>[] tree; // 그래프를 표현
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // 정점의 개수
        int M = Integer.parseInt(st.nextToken()); // 간선의 개수
        int V = Integer.parseInt(st.nextToken()); // 시작 지점
        tree = new LinkedList[N+1];
        DFS_check = new boolean[N+1]; // DFS 방문 체크
        BFS_check = new boolean[N+1]; // BFS 방문 체크
        for(int i = 1; i < N+1; i++){
            tree[i] = new LinkedList<>(); // 각 정점마다 LinkedList로 담을 공간 만들어주기
        }
        // LinkedList에 중복되지 않게 변수 담기
        for(int i = 0; i< M; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            addEdge(n1, n2);
        }
        // 오름차순으로 정리하여 낮은 순서대로 방문하도록 설정
        for(int i = 1; i<N+1; i++){
            Collections.sort(tree[i]);
        }

        DFS(V);
        System.out.println();
        BFS(V);
    }

    public static void addEdge(int n1, int n2){
        tree[n1].add(n2);
        tree[n2].add(n1);
    }

    public static void DFS(int n){
        if(DFS_check[n])
            return;
        DFS_check[n] = true;
        System.out.print(n + " ");
        for(int i : tree[n]){
            if(!DFS_check[i])
                DFS(i);
        }
    }

    public static void BFS(int n){
        Queue<Integer> queue = new LinkedList<>();
        BFS_check[n] = true;
        queue.add(n);

        while(queue.size() != 0){
            n = queue.poll();
            System.out.print(n + " ");
            for(int i : tree[n]){
                if(!BFS_check[i]) {
                    BFS_check[i] = true;
                    queue.add(i);
                }
            }
        }

    }

}
