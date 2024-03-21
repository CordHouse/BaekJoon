package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;
/***
 * 24. 03. 22 15시 50분 시작
 * 24. 03. 22 16시 07분 종료
 * 성공 유무 -> 성공
 */

/**
 * 주어진 조건
 * 1. 방향 없는 그래프가 주어졌을때 연결 요소의 갯수 찾기
 * 2. 정점 갯수 N, 간선의 갯수 M
 * 3. 간선의 양 끝점 u, v (Node)
 *
 * 제한사항
 * 1 <= N <= 1_000, 0 <= M <= N * (N-1) / 2
 *
 * 풀이
 * 연결요소와 그룹으로 문제를 해석할 수 있어 유니온 파인드로 접근했다.
 * 물론 DFS, BFS 로도 접근해 풀 수 있다.
 * 다른 그래프 탐색의 경우 처음 방문하는 노드를 찾아 카운트 해주면 되는 것이다.
 */

public class 연결_요소의_개수_11724 {
    static int[] parent;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        parent = new int[n+1];

        for(int i = 1; i <= n; i++) {
            parent[i] = i;
        }

        while(m --> 0) {
            st = new StringTokenizer(br.readLine());
            int o1 = Integer.parseInt(st.nextToken());
            int o2 = Integer.parseInt(st.nextToken());

            union(find(o1), find(o2));
        }

        HashSet<Integer> set = new HashSet<>();
        for(int v : parent) {
            if(v != 0) {
                set.add(find(v));
            }
        }
        System.out.println(set.size());
    }

    static void union(int o1, int o2) {
        if(o1 < o2) {
            parent[o2] = o1;
        }
        else {
            parent[o1] = o2;
        }
    }

    static int find(int num) {
        if(parent[num] == num) {
            return num;
        }
        return parent[num] = find(parent[num]);
    }
}
