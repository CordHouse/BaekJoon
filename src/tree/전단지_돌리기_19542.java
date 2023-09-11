package tree;

/**
 * 23. 09. 10 11시 10분 시작
 * 23. 09. 10 11시 52분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 각 노드간의 거리는 1로 고정되어 있다.
 * 2. 현재 노드에서 힘의 거리만큼 던져서 전단지를 돌릴 수 있다.
 * 3. 이동거리는 왕복으로 계산하여 시작지점으로 돌아오는 것을 고려해야한다.
 *
 * 제한사항
 * 1 <= N <= 100_000
 * 1 <= S <= N
 * 0 <= D <= N
 * 1 <= x, y <= N, x != y
 *
 * 풀이
 * 풀이자체는 간단하게 생각해 볼 수 있는 문제이지만, 그것을 구현하는 것에 많이 고민해야 했던 문제이다.
 * 이동하는 거리마다 왕복된 거리값을 계산해야하기 때문에 최종 값에 * 2가 들어가고
 * 배열안에 원점에 대해서 이동한 거리를 담아주게 된다면 쉽게 구할 수 있는 문제이다.
 */

public class 전단지_돌리기_19542 {
    private static ArrayList<Integer>[] node;
    private static boolean[] visit;
    private static int[] arr;
    private static int n, s, d, tmp = 0, answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken()); // 노드의 개수
        s = Integer.parseInt(st.nextToken()); // 케니소프트 위치
        d = Integer.parseInt(st.nextToken()); // 힘

        // Step 1. 노드 초기화
        node = new ArrayList[n+1];
        for(int i = 1; i <= n; i++) {
            node[i] = new ArrayList<>();
        }

        // Step 2. 노드에 대한 값을 양방향으로 설정 -> 방향이 정해지지 않았기 때문
        for(int i = 1; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int start = Integer.parseInt(st.nextToken());
            int end = Integer.parseInt(st.nextToken());

            node[start].add(end);
            node[end].add(start);
        }

        // Step 3. 리프노드를 따라가며 거리 계산
        arr = new int[n+1];
        visit = new boolean[n+1];
        tree(s);
        System.out.println(answer * 2);
    }

    public static int tree(int start) {
        visit[start] = true;
        for(int next : node[start]) {
            if(!visit[next]) {
                // 이전 노드에 더해진 값과 현재 노드에 대한 계산이 이루어져야한다.
                arr[start] = Math.max(arr[start], tree(next) + 1);
            }
        }

        if(start != s && arr[start] >= d) {
            answer++;
        }
        return arr[start];
    }
}
