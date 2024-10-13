import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 사탕_게임_3085 {
    private static String[][] map;
    private static int n, max;
    private static String tmp;
    private static int[] dx = {-1, 0, 1, 0}, dy = {0, 1, 0, -1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        map = new String[n][n];
        for(int i = 0; i < n; i++) {
            String[] st = br.readLine().split("");
            for(int j = 0; j < n; j++) {
                map[i][j] = st[j];
            }
        }

        switchCell();
    }

    private static void switchCell() {
        for(int i = 0; i < n; i++) {
            for(int j = 0; j < n; j++) {
                for(int k = 0; k < 4; k++) {
                    int nx = dx[k] + i;
                    int ny = dy[k] + j;

                    if(nx >= 0 && ny >= 0 && nx < n && ny < n) {
                        if(!map[i][j].equals(map[nx][ny])) {
                            tmp = map[i][j];
                            map[i][j] = map[nx][ny];
                            map[nx][ny] = tmp;

                            moveCount(i, j);

                            tmp = map[i][j];
                            map[i][j] = map[nx][ny];
                            map[nx][ny] = tmp;
                        }
                    }
                }
            }
        }
        System.out.println(max);
    }

    private static void moveCount(int x, int y) {
        for(int i = 0; i < n; i++) {
            int count = 1;
            for(int j = 0; j < n-1; j++) {
                if(map[i][j].equals(map[i][j+1])) {
                    count++;
                    max = Math.max(max, count);
                }
                else {
                    count = 1;
                }
            }
        }

        for(int i = 0; i < n; i++) {
            int count = 1;
            for(int j = 0; j < n-1; j++) {
                if(map[j][i].equals(map[j+1][i])) {
                    count++;
                    max = Math.max(max, count);
                }
                else {
                    count = 1;
                }
            }
        }
    }
}
