import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class site{
    int x, y;
    site(int x, int y){
        this.x = x;
        this.y = y;
    }
}

public class 연구소 {
    static int N, M, count, ans;
    static int[][] map;
    static int[] x = {-1, 1, 0, 0}, y = {0, 0, -1, 1};
    static Queue<site> queue = new LinkedList<site>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for(int i = 0; i<N; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 0; j<M; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 2)
                    queue.add(new site(i, j));
            }
        }
        BFS();
    }

    static void BFS(){
        while(!queue.isEmpty()){
            site site = queue.poll();
            for(int i = 0; i<4; i++) {
                int nx = site.x + x[i];
                int ny = site.y + y[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                    if(map[nx][ny] == 0 && count < 3){
                        map[nx][ny] = 1;
                        count++;
                    }
                    else if(map[nx][ny] == 0){
                        map[nx][ny] = 2;
                        queue.add(new site(nx, ny));
                    }
                }
            }
        }

        for(int i = 0; i< N; i++){
            for(int j = 0; j<M; j++){
                if(map[i][j] == 0)
                    ans++;
            }
        }
        System.out.println(ans);
    }
}
