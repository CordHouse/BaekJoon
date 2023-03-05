import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 유기농_배추 {
    private static int[] vx = {-1, 1, 0, 0};
    private static int[] vy = {0, 0, -1, 1};
    private static BufferedReader br;
    private static int number;
    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        int caseCycle = Integer.parseInt(br.readLine());
        for(int index = 0; index < caseCycle; index++) {
            String[] list = br.readLine().split(" ");
            int r = Integer.parseInt(list[1]);
            int c = Integer.parseInt(list[0]);
            int cycle = Integer.parseInt(list[2]);
            int[][] table = new int[r][c];
            boolean[][] check = new boolean[r][c];
            number = 0;
            init(cycle, table);
            numberCheck(r, c, table, check);
            System.out.println(number);
        }
    }

    public static void init(int cycle, int[][] table) throws IOException {
        for (int i = 0; i < cycle; i++) {
            String[] baeChu = br.readLine().split(" ");
            int r = Integer.parseInt(baeChu[1]);
            int c = Integer.parseInt(baeChu[0]);
            table[r][c] = 1;
        }
    }

    public static void numberCheck(int xSize, int ySize, int[][] table, boolean[][] check) {
        for(int i = 0; i < xSize; i++) {
            for(int j = 0; j < ySize; j++) {
                if(table[i][j] == 1 && !check[i][j]) {
                    dfs(i, j, xSize, ySize, table, check);
                    number++;
                }
            }
        }
    }

    public static void dfs(int x, int y, int xSize, int ySize, int[][] table, boolean[][] check) {
        if(check[x][y]) {
            return ;
        }
        check[x][y] = true;

        for(int i = 0; i < 4; i++) {
            int nx = x + vx[i];
            int ny = y + vy[i];

            if(nx >= 0 && ny >= 0 && nx < xSize && ny < ySize) {
                if(!check[nx][ny] && table[nx][ny] == 1) {
                    dfs(nx, ny, xSize, ySize, table, check);
                }
            }
        }
    }
}
