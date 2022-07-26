import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 그림 {
    static int[][] map;
    static boolean[][] check;

    static int picture = 0, N = 0, M = 0;
    static int[] vx = {-1,1,0,0}, vy = {0,0,-1,1}, count;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[M][N];
        check = new boolean[M][N];
        count = new int[N*M];

        for(int i = 0; i < N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j < M; j++){
                map[j][i] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i < N; i++){
            for(int j = 0; j < M; j++){
                if(map[j][i]==1 && !check[j][i]){
                    picture++;
                    DFS(j, i);
                }
            }
        }
        Arrays.sort(count);
        System.out.println(picture+"\n"+count[count.length-1]);

    }

    static void DFS(int x, int y){
        check[x][y] = true;
        count[picture-1]++;

        for(int i = 0; i<4; i++){
            int nx = x + vx[i];
            int ny = y + vy[i];
            if(nx >= 0 && ny >= 0 && nx < M && ny < N){
                if(map[nx][ny] == 1 && !check[nx][ny])
                    DFS(nx, ny);
            }
        }
    }
}
