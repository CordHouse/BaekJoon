package dfs_bfs;

/***
 * 23. 06. 26 16시 40분 시작
 * 23. 06. 26 17시 35분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 주어진 조건
 * 1. 가로 N, 세로 2줄로 구성된 표가 주어진다.
 * 2. 첫번째 줄에는 숫자가 1~N까지 차례대로 들어있다.
 * 3. 두번째 줄에는 숫자가 1이상 N 이하인 정수가 들어가 있다.
 * 4. 첫째 줄에서 숫자를 적절히 뽑으면 그 바로 밑에 있는 정수들과 이루는 집합이 일치한다. ex) 1 : 3 -> 3 : 1 이런식으로
 * 5. 해당 조건을 만족하여 최대로 뽑을 수 있는 방법을 찾으시오.
 *
 * 제한사항
 * 1 <= N <= 100
 *
 * 풀이
 * DFS 알고리즘을 이용하여 해당 문제를 접근할 수 있다.
 * 우선 문제를 파악하는 것이 가장 중요한데 처음에 문제를 잘못 접근해서 한참을 헤맸다.
 * 문제에서 제시하는 조건은 1번을 뽑은 경우 3이라는 값이 나오고 3이라는 값을 다시 찾아 들어가면 1이 나온다.
 * 따라서 1 -> 3 -> 1이라는 싸이클이 만들어지고 이럴 경우 우리가 찾고자 하는 값이 되는 것이다.
 * 문제는 DFS에서 어떻게 종료 조건을 만들 것인가 인데, 방문한 곳을 체크하는 visit 배열을 만들어 확인해주면 된다.
 */

public class 숫자고르기_2668 {
    private static int[] arr;
    private static boolean[] visit;
    private static boolean[] check;
    private static int n;
    private static List<Integer> answer = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        arr = new int[n+1];
        visit = new boolean[n+1]; // 방문한 곳 처리
        check = new boolean[n+1]; // 두번째 줄 값만 true -> 그래야 첫번째 줄에서 시간을 줄일 수 있다.

        // Step 1. 두번째 줄 값을 담는다.
        for(int i = 1; i <= n; i++) {
            arr[i] = Integer.parseInt(br.readLine());
            if(!check[arr[i]]) {
                check[arr[i]] = true;
            }
        }

        // Step 2. DFS 시작 -> check 배열을 통해서 갈 수 있는 곳만 간다.
        for(int i = 1; i <= n; i++) {
            if(check[i]) {
                visit[i] = true;
                dfs(i);
                visit[i] = false;
            }
        }

        Collections.sort(answer);
        System.out.println(answer.size());
        for(int v : answer) {
            System.out.println(v);
        }
    }

    public static void dfs(int num) {
        // 종료 기준은 싸이클이 만들어지는 기준이며, 이미 answer 에서 값을 가지고 있다면 포함하지 않는다.
        if(visit[arr[num]]) {
            if(!answer.contains(num)) {
                answer.add(num);
            }
            return;
        }

        // 만약 없다면 다시한번 재귀를 사용해 찾아 들어간다.
        if(!visit[arr[num]] && check[arr[num]]) {
            visit[arr[num]] = true;
            dfs(arr[num]);
            visit[arr[num]] = false;
        }
    }
}
