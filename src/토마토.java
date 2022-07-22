import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// 토마토 클래스 생성하여 객체로 받아 저장하기
class tomato{
    int x, y;

    tomato(int x, int y) {
        this.x = x;
        this.y = y;
    }
}

public class 토마토 {
    static int[][] map; // 토마토를 담을 테이블
    static int N, M; // 가로와 세로 길이

    static int[] vx = {-1, 1, 0, 0}, vy = {0, 0, -1, 1}; // 상하좌우를 살피기 위해 선언

    static Queue<tomato> queue = new LinkedList<tomato>(); // BFS를 사용하기 위해 queue선언
    static tomato tomato; // tomato클래스에 대한 tomato 변수 선언

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());
        map = new int[N][M];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
                if(map[i][j] == 1){
                    queue.add(new tomato(i, j)); // queue에 i, j값 집어넣기
                }
            }
        }
        BFS(); // BFS 호출
    }

    static void BFS() {
        while (!queue.isEmpty()){ // queue가 비어있을 때까지 반복하기 때문에 재귀호출할 필요가 없음
            tomato = queue.poll();
            for(int i = 0; i<4; i++){
                int nx = tomato.x + vx[i];
                int ny = tomato.y + vy[i];

                if(nx >= 0 && ny >= 0 && nx < N && ny < M){
                    if(map[nx][ny] == 0) {
                        queue.add(new tomato(nx, ny));
                        map[nx][ny] = map[tomato.x][tomato.y] + 1;
                    }
                }
            }
        }
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
                if(map[i][j] == 0){
                    System.out.println("-1");
                    return;
                }
            }
        }
        System.out.println(map[tomato.x][tomato.y]-1); // 1을 빼주는 이유는 첫 시작이 1이여서 그럼
    }
}
