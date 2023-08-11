package backTracking;

/**
 * 23. 08. 11 13시 10분 시작
 * 23. 08. 11 14시 20분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

/**
 * 주어진 조건
 * 1. 7명의 여학생들로 구성되어야 한다.
 * 2. 7명의 자리는 서로 가로나 세로로 반드시 인접해 있어야 한다.
 * 3. 반드시 "이다솜파"의 학생들로만 구성될 필요는 없다.
 * 4. 항상 "이다솜파"가 우위를 점유해야한다. 7명의 학생 중 적어도 4명 이상은 반드시 포함되어야 한다.
 *
 * 제한사항
 * S 이다솜파
 * Y 임도연파
 *
 * 풀이
 * BFS로 접근하여 풀어야하는 문제라고 생각했다. 하지만 문제를 접근하고 풀다보니 주어진 테스트케이스를 제외한 나머지
 * 부분에서 문제가 발생했고 그로인해 정확한 풀이가 아니라는 것을 알게 되었다.
 * 다른 분의 풀이를 보고 작성해보았다. 이 코드를 작성하신 분은 재귀를 사용하여 조합을 만들고 그 조합에 따라
 * BFS를 수행하는 방식으로 작성하신 것 같다.
 */

public class 소문난_칠공주_1941 {
    private static final int SIZE = 5;
    private static String[][] map = new String[SIZE][SIZE];
    private static int[] cx = new int[25], cy = new int[25];
    private static int[] dx = {1, 0, -1, 0}, dy = {0, -1, 0, 1};
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // Step 1. 맵 테이블 생성
        for(int i = 0; i < SIZE; i++) {
            String[] line = br.readLine().split("");
            for(int j = 0; j < SIZE; j++) {
                map[i][j] = line[j];
            }
        }

        // x와 y 좌표에 대한 입력을 미리 받아놓는다.
        for(int i = 0; i < 25; i++) {
            cx[i] = i % 5;
            cy[i] = i / 5;
        }

        // Step 2. 조합 만들기
        combination(new int[7], 0, 0, 7);
        System.out.println(answer);
    }

    public static void combination(int[] comb, int index, int depth, int left) {
        if(left == 0) {
            bfs(comb);
            return;
        }

        if(depth == 25) {
            return;
        }

        comb[index] = depth;
        combination(comb, index+1, depth+1, left-1); // 선택한 경우
        combination(comb, index, depth+1, left); // 선택하지 않은 경우
    }

    public static void bfs(int[] comb) {
        Queue<Integer> queue = new LinkedList<>();
        boolean[] visit = new boolean[7];

        visit[0] = true;
        queue.add(comb[0]);
        int cnt = 1, sCount = 0;

        while(!queue.isEmpty()) {
            int cur = queue.poll();

            if(map[cy[cur]][cx[cur]].equals("S")) {
                sCount++;
            }

            for(int i = 0; i < 4; i++) {
                for(int next = 1; next < 7; next++) {
                    if(!visit[next] && cx[cur]+dx[i] == cx[comb[next]] && cy[cur]+dy[i] == cy[comb[next]]) {
                        visit[next] = true;
                        queue.add(comb[next]);
                        cnt++;
                    }
                }
            }
        }

        // 최종적으로 7만큼 카운트 된 상태에서 S가 4이상인 경우만 증가
        if(cnt == 7) {
            if(sCount >= 4) {
                answer++;
            }
        }
    }
}
