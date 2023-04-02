import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 토마토7569 {
    // 토마토 상태를 담을 3차원 배열 선언
    private static int[][][] tomato;
    // 상 하 좌 우 위 아래 를 훑어야 하기 때문에 한칸씩 움직이는 값으로 선언
    private static int[] vx = {1,0,-1,0,0,0}, vy = {0,-1,0,1,0,0}, vz = {0,0,0,0,1,-1};
    // bfs를 사용하기 때문에 queue 선언
    private static Queue<Point> queue = new LinkedList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int max = 0; // 토마토 배열에 정해진 숫자중 가장 큰 숫자를 가져온다 => 최종 정답
        String[] len = br.readLine().split(" ");
        int z = Integer.parseInt(len[2]);
        int y = Integer.parseInt(len[1]);
        int x = Integer.parseInt(len[0]);
        tomato = new int[z][y][x];

        // 삼중포문인데, 정상적인 토마토라면 큐에 담는다. => 최조 시작점을 설정하는 계기
        for(int i = 0; i < z; i++) {
            for(int j = 0; j < y; j++) {
                String[] tomatoLayer = br.readLine().split(" ");
                for(int k = 0; k < x; k++) {
                    tomato[i][j][k] = Integer.parseInt(tomatoLayer[k]);
                    if(tomato[i][j][k] == 1) {
                        queue.add(new Point(i, j, k));
                    }
                }
            }
        }

        // bfs로 넓이 탐색을 시작한다. -> 토마토가 처음에 있었던 구간을 지정해서 들어가기 때문에
        // 큐에 해당되는 값만큼 돌기 시작한다.
        bfs(z, y, x);

        // 토마토가 익지 않은 경우도 있을 수 있기 때문에 아닌 경우 -1
        // 그 이외엔 토마토 판을 돌면서 가장 큰 숫자를 찾는다.
        for(int i = 0; i < z; i++) {
            for(int j = 0; j < y; j++) {
                for(int k = 0; k < x; k++) {
                    if(tomato[i][j][k] == 0) {
                        System.out.println(-1);
                        return;
                    }
                    max = Math.max(max, tomato[i][j][k]);
                }
            }
        }

        // 토마토 판을 줬을 때 부터 모두 익은 토마토와 비어있는 상태가 같이 있는 경우 0
        if(max == 1) {
            System.out.println(0);
            return;
        }

        // 마지막으로 최대 값에서 시작부터 1으로 시작했기 때문에 마지막에 -1를 꼭 해줘야한다.
        System.out.println(max-1);
    }

    public static void bfs(int zLen, int yLen, int xLen) {
        while(!queue.isEmpty()) {
            // 큐에서 좌표를 가지고 온다.
            Point point =  queue.poll();

            // 상 하 좌 우 위 아래를 탐색한다.
            for(int i = 0; i < 6; i++) {
                int nz = point.z + vz[i];
                int ny = point.y + vy[i];
                int nx = point.x + vx[i];

                // 토마토 판 안에서만 돌아야 하기 때문에 탐색할 범위를 설정해준다.
                if(nz >= 0 && nz < zLen && ny >= 0 && ny < yLen && nx >= 0 && nx < xLen) {
                    // 토마토가 0인 경우만 큐에 담아주고, 이전 값에서 1만큼을 더해준다.
                    // 시작되는 값은 1임!
                    if (tomato[nz][ny][nx] == 0) {
                        queue.add(new Point(nz, ny, nx));
                        tomato[nz][ny][nx] = tomato[point.z][point.y][point.x] + 1;
                    }
                }
            }
        }
    }

    // 큐에 담을 좌표를 만드는 클래스
    static class Point{
        int z;
        int y;
        int x;

        public Point(int z, int y, int x) {
            this.z = z;
            this.y = y;
            this.x = x;
        }
    }
}
