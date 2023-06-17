package backTracking;

/***
 * 23. 06. 17 15시 41분 시작
 * 23. 06. 17 17시 20분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 주어진 조건
 * 1. 0 ~ N-1의 번호가 있고, 일부 사람들은 친구이다.
 * 2. 총 5명의 친구관계가 형성되는지 판단하여 출력하자
 * 3. 연속된 5명의 친구를 선택해야한다.
 *
 * 제한사항
 * 5 <= N <= 2_000
 * 1 <= M <= 2_000
 * (0 <= a,b <= N-1, a != b)
 *
 * 풀이
 * 해당 문제는 백트래킹으로 접근하여 푸는 문제이다.
 * 처음에 문제에 대한 이해가 되지 않아, 친구의 친구가 있다면 형성되는 조건으로 잘못이해해 풀이했었다.
 * 그게 아니라, ABCDE는 5명의 사람을 지칭하며, 총 5단계의 거리를 가지고 있어야 한다는 뜻이다.
 * ex) A -> B -> C -> D -> E ( 이 부분을 이해하기까지 오랜 시간이 걸렸다. )
 *
 * 주의사항으로는 해당 문제는 5개의 제약이 없다면 엄청난 시간이 걸리기 때문에 주의해야한다.
 * 특히 answer == 1인 구간을 꼭 작성해주자. 안그러면 시간초과 발생 (5인 경우가 엄청 많아질 수도 있기 때문)
 * */

public class ABCDE_13023 {
    private static int n, m;
    private static int answer = 0;
    private static List<Integer>[] node;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        n = Integer.parseInt(input[0]);
        m = Integer.parseInt(input[1]);

        // Step 1. 양방향으로 담을 변수 선언
        node = new ArrayList[n];
        for(int i = 0; i < n; i++) {
            node[i] = new ArrayList<>();
        }

        // Step 2. 주어진 친구의 숫자를 양방향으로 추가
        for(int i = 0; i < m; i++) {
            String[] line = br.readLine().split(" ");
            int left = Integer.parseInt(line[0]);
            int right = Integer.parseInt(line[1]);
            node[left].add(right);
            node[right].add(left);
        }

        // Step 3. DFS 시작
        // answer가 1일때 종료해주는 것이 핵심 -> 안그러면 시간초과 납니다.
        visit = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(answer == 1) {
                break;
            }
            dfs(1, i);
        }
        System.out.println(answer);
    }

    public static void dfs(int depth, int start) {
        if(depth == 5) {
            answer = 1;
            return;
        }

        visit[start] = true; // 반복문 이전에 방문처리하는 이유는 start 지점의 방문처리를 위해서
        for(int v : node[start]) {
            if(!visit[v]) {
                visit[v] = true; // 안에서 처리를 한번더 해주면 start를 제외하고 다음 값에 대해 방문처리
                dfs(depth+1, v);
                visit[v] = false;
            }
        }
        visit[start] = false;
    }
}