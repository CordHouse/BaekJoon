import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 주사위_굴리기_14499 {
    private static int[] dice = new int[7];
    private static int[][] map;
    private static int[] dx = {0, 1, -1, 0, 0};
    private static int[] dy = {0, 0, 0, -1, 1};
    private static int m, n, x, y;


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        y = Integer.parseInt(st.nextToken());
        x = Integer.parseInt(st.nextToken());
        int count = Integer.parseInt(st.nextToken());

        map = new int[n][m]; // 지도 초기화

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());
        while(count --> 0) {
            int root = Integer.parseInt(st.nextToken());
            cmd(root);
        }
    }

    // 조건 주의 아무런 동작도 하면 안되기 때문에 미리 x, y 값을 바꾸면 안됌
    public static void cmd(int root) {
        int nx = dx[root] + x;
        int ny = dy[root] + y;
        if(nx >= 0 && ny >= 0 && nx < m && ny < n) {
            x = nx;
            y = ny;
            roll(root);
        }
    }

    public static void roll(int root) {
        int tmp = dice[3];
        switch (root) {
            case 1:
                dice[3] = dice[4];
                dice[4] = dice[6];
                dice[6] = dice[2];
                dice[2] = tmp;
                break;
            case 2:
                dice[3] = dice[2];
                dice[2] = dice[6];
                dice[6] = dice[4];
                dice[4] = tmp;
                break;
            case 3:
                dice[3] = dice[5];
                dice[5] = dice[6];
                dice[6] = dice[1];
                dice[1] = tmp;
                break;
            case 4:
                dice[3] = dice[1];
                dice[1] = dice[6];
                dice[6] = dice[5];
                dice[5] = tmp;
                break;
        }
        // 이동 칸에 있는 수가 0이 아니면 칸 수 -> 주사위 바닥, 칸 수 = 0
        if(map[y][x] != 0) {
            dice[6] = map[y][x];
            map[y][x] = 0;
        }
        // 이동 칸에 있는 수가 0이면 주사위 바닥 -> 칸 수
        else {
            map[y][x] = dice[6];
        }
        System.out.println(dice[3]);
    }
}
