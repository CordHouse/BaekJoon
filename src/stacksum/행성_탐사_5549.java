package stacksum;

/**
 * 23. 09. 06 15시 00분 시작
 * 23. 09. 06 15시 29분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 가로 n, 세로 m이라는 직사각형 맵이 주어진다.
 * 2. J, O, I에 대해 각각 누적합을 구하고 출력해야한다.
 *
 * 제한사항
 * 1 <= N, M <= 1_000
 * 1 <= K <= 100_000
 * (a, b), (c, d)
 *
 * 풀이
 * 이번 문제는 누적합을 응용한 문제이다.
 * 처음에 풀었던 방법은 시간초과가 발생하였는데, 브루트포스로 풀었기 때문이다.
 * 따라서 그 방법이 아닌, 2차 배열의 누적합으로 풀어야하는데, 표 내에서 중복되어 계산되는 값을 제외하고,
 * 영역을 설정한 다음 해당 영역만큼을 제외시켜주는 방법을 택해야한다.
 * 그럼 쓸대 없이 반복하여 계산되는 값이 줄어 시간복잡도가 감소하게 된다.
 * 아래 풀이를 참고하자!
 */

public class 행성_탐사_5549 {
    private static int n, m, k;
    private static int[][][] map;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        k = Integer.parseInt(br.readLine());
        int layer = 0;
        map = new int[3][n+1][m+1];
        for (int i = 1; i <= n; i++) {
            String line = br.readLine();
            for (int j = 1; j <= m; j++) {
                char c = line.charAt(j - 1);
                if(c == 'J') layer = 0;
                else if(c == 'O') layer = 1;
                else layer = 2;
                map[layer][i][j] = 1;
            }
        }

        // h : 0 -> 2 까지 J O I 순으로 담아준다.
        // 누적해서 내려가는 값은 왼쪽 + 상단 - 왼쪽 대각선으로 계산한다. (왼쪽 대각선이 두번 더해지기 때문이다.)
        for(int h = 0; h < 3; h++) {
            for (int i = 1; i <= n; i++) {
                for (int j = 1; j <= m; j++) {
                    map[h][i][j] += map[h][i-1][j] + map[h][i][j-1] - map[h][i-1][j-1];
                }
            }
        }

        for(int i = 0; i < k; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            search(a, b, c, d);
        }
    }

    public static void search(int a, int b, int c, int d) {
        // 정해진 구간에서 가로 영역, 세로 영역만큼 빼주고
        // 가로와 세로가 겹쳐지는 구간이 2번 빠지기 때문에 1만큼 더해주면 최종적인 답안을 구할 수 있다.
        // 즉 (영역의 끝 - 가로 영역의 최대 값 - 세로 영역의 최대 값 + 중복된 값)
        int J = map[0][c][d] - map[0][a-1][d] - map[0][c][b-1] + map[0][a-1][b-1];
        int O = map[1][c][d] - map[1][a-1][d] - map[1][c][b-1] + map[1][a-1][b-1];
        int I = map[2][c][d] - map[2][a-1][d] - map[2][c][b-1] + map[2][a-1][b-1];

        System.out.println(J+" "+O+" "+I);
    }
}
