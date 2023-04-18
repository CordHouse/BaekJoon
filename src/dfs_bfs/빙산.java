package dfs_bfs;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 빙산 {
    private static int[][] map;
    private static boolean[][] check;
    private static Queue<Point> queue = new LinkedList<>();
    private static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
    private static int min = 0, count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");

        int y = Integer.parseInt(input[0]); // y의 길이
        int x = Integer.parseInt(input[1]); // x의 길이
        map = new int[y][x]; // 상태를 담을 맵 초기화
        for(int i = 0; i < y; i++) {
            String[] status = br.readLine().split(" "); // 담을 변수를 하나씩 나눈다.
            for(int j = 0; j < x; j++) {
                map[i][j] = Integer.parseInt(status[j]); // 남은 변수를 하나씩 담아준다.
            }
        }
        queueInit(x, y); // 큐안에 담을 값을 초기화 시작
        System.out.println(min);
    }
    public static void queueInit(int x, int y) {
        check = new boolean[y][x]; // 체크변수는 dfs에서 사용될 예정이다.
        for(int i = 0; i < y; i++) {
            for(int j = 0; j < x; j++) {
                if(map[i][j] != 0) {
                    if(!check[i][j]) { // 한번도 체크되지 않은 지역이라면 카운트를 증가 시킨다.
                        count++;
                    }
                    dfs(j, i, x, y); // dfs로 한 값에 대해 연결된 빙하를 체크한다.
                    queue.add(new Point(j, i)); // 하면서 bfs에 담아줄 값을 큐에 담는다.
                }
            }
        }
        if(queue.isEmpty()) { // 큐가 비었다는건 더이상 섬이 두개로 나눠지지 않고 있다는 의미로 0을 리턴
            min = 0;
            return;
        }
        if(count > 1) { // 카운트가 2이상인 경우에는 min값이 출력 -> 빙하가 두개로 나뉜 경우임
            return;
        }
        count = 0; // 매번 카운트는 0으로 초기화 -> 전역변수로 설정되어 있음
        min++; // min 값이 빙하로 나뉘면서 지나온 년도 변수
        bfs(x, y); // 모든 조건이 해당되지 않으면 bfs 시작
    }
    public static void bfs(int x, int y) {
        boolean[][] bfsCheck = new boolean[y][x]; // 녹는 빙하를 계산하면서 0이 된 값을 중복적용하면 안되기 때문에 선언
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            bfsCheck[point.y][point.x] = true; // bfs로 시작 값을 체크해준다. 중복 계산 방지

            for(int i = 0; i < 4; i++) {
                int nx = dx[i] + point.x;
                int ny = dy[i] + point.y;

                if(nx >= 0 && ny >= 0 && nx < x && ny < y) {
                    if(map[ny][nx] == 0 && !bfsCheck[ny][nx]) {
                        map[point.y][point.x] -= 1; // 녹게되면 0 하나 당 1씩 빼준다.
                    }
                }
            }
            if(map[point.y][point.x] < 0) { // 음수인 경우 0으로 초기화
                map[point.y][point.x] = 0;
            }
        }
        queueInit(x, y); // 다시 큐에 값을 넣기 위해 큐초기화 함수로 접근
    }
    // dfs는 연결된 빙하를 찾는 함수이다.
    public static void dfs(int x, int y, int xLen, int yLen) {
        check[y][x] = true;
        for(int i = 0; i < 4; i++) {
            int nx = dx[i] + x;
            int ny = dy[i] + y;

            if(nx >= 0 && ny >= 0 && nx < xLen && ny < yLen) {
                if(map[ny][nx] != 0 && !check[ny][nx]) {
                    dfs(nx, ny, xLen, yLen);
                }
            }
        }
    }

}
class Point{
    int x;
    int y;
    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
