package tree;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 뿌리노드는 1부터 시작한다.
 *
 * 제한사항
 * N <= 100_000
 *
 * 풀이
 * 해당 문제는 트리 문제이다.
 * 트리문제는 전위, 중위, 후위의 문제를 많이 다루지만, 이번 문제는 그래프로 접근하여 풀 수 있다.
 * 그래프로 접근했을때, 두 정점에 대해 서로 연결을 하고, 1부터 시작하는 조건에 따라
 * 1부터 자식노드에 대해 부모노드를 설정 후 이후 출력하는 방식으로 진행한다.
 */

public class 트리의_부모_찾기_11725 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        // Step 1. 그래프 초기화
        List<List<Integer>> list = new ArrayList<>();
        for(int i = 0; i <= n; i++) {
            list.add(new ArrayList<>());
        }

        // Step 2. 입력
        StringTokenizer st;
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            list.get(n1).add(n2);
            list.get(n2).add(n1);
        }

        boolean[] visit = new boolean[n+1]; // 방문처리
        int[] parent = new int[n+1]; // 부모 root

        Queue<Integer> queue = new LinkedList<>();
        queue.add(1); // 1번부터 시작
        visit[1] = true;

        while(!queue.isEmpty()) {
            int root = queue.poll();

            for(int left : list.get(root)) { // 1번에 있는 값들이 다 자식노드가 됨
                if(!visit[left]) {
                    visit[left] = true;
                    queue.add(left);
                    parent[left] = root;
                }
            }
        }

        for(int i = 2; i <= n; i++) {
            System.out.println(parent[i]);
        }
    }
}
