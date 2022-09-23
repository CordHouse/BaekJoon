import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

// bfs 접근 방법
// Step 1. 전역 변수로 board or map 선언
// Step 2. 전역 변수로 check -> 방문 여부 체크
// Step 3. 상, 하, 좌, 우 방문해야 하기 때문에 x, y 선언
// Step 4. queue 에 변수를 집어 넣야 하는데 그 값이 x, y이기 때문에 클래스를 하나 만들어 생성자로 삽입
// Step 5. bfs 시작하면 queue 에 Add 시키고, while 에서 poll 시킨다.

public class 미로_탐색 {
    static int[][] board;
    static boolean[][] check;
    static int[] dx = {-1, 1, 0, 0};
    static int[] dy = {0, 0, -1, 1};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int row = Integer.parseInt(st.nextToken());
        int col = Integer.parseInt(st.nextToken());

        board = new int[row+1][col+1];
        check = new boolean[row+1][col+1];

        for(int i = 1; i<=row; i++){
            String line = br.readLine();
            for(int j = 1; j<=col; j++){
                board[i][j] = Integer.parseInt(String.valueOf(line.charAt(j-1)));
            }
        }

        System.out.println(bfs(1,1, row, col));
    }

    public static int bfs(int startRow, int startCol, int endRow, int endCol){
        int distance = 1;
        Queue<position> queue = new LinkedList<>();
        check[startRow][startCol] = true;
        queue.add(new position(startRow, startCol, distance));

        if(startRow == endRow && startCol == endCol){
            return queue.poll().distance;
        }

        while (!queue.isEmpty()){
            position tmp = queue.poll();
            int x = tmp.x;
            int y = tmp.y;
            distance = tmp.distance;
            for(int i =0; i<4; i++){
                int nx = x + dx[i];
                int ny = y + dy[i];
                if(nx >= 0 && ny >= 0 && nx <= endRow && ny <= endCol){
                    if(!check[nx][ny] && board[nx][ny] == 1){
                        check[nx][ny] = true;
                        if(nx == endRow && ny == endCol)
                            return distance+1;
                        queue.add(new position(nx, ny, distance+1));
                    }
                }
            }
        }
        return distance;
    }

    static class position{
        int x;
        int y;
        int distance;
        position(int x, int y, int distance){
            this.x = x;
            this.y = y;
            this.distance = distance;
        }
    }
}
