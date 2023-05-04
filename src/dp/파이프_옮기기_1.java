package dp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;

public class 파이프_옮기기_1 {
    private static boolean[][] check;
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        check = new boolean[N+1][N+1]; // check 2차원 배열을 통해 1이 존재하는 위치를 true로 설정
        // 1의 값을 찾기 위한 반복 시작
        for(int i = 0; i < N; i++) {
            String[] input = br.readLine().split(" ");
            for(int j = 0; j < N; j++) {
                if(input[j].equals("1")) {
                    check[i+1][j+1] = true;
                }
            }
        }
        // 문제에서 16 x 16만큼 진행했을때 마지막 위치 check[N][N] == true 라면 1이 있는 것으로
        // 애초에 갈 수 없는 위치 이기 때문에 미리 예외처리 한다.
        if(check[N][N]) {
            System.out.println(0);
            return ;
        }
        bfs(N);
        System.out.println(count);
    }

    public static void bfs(int N) {
        Queue<Status> queue = new LinkedList<>();
        // 큐안에 담는 값은 마지막 값을 기준으로 시작
        // 항상 상태코드도 함께 담아준다.
        queue.add(new Status(1, 2, "row"));

        while(!queue.isEmpty()) {
            Status s = queue.poll();
            if(s.x == N && s.y == N) {
                count++;
                continue;
            }
            // 파이프가 가로로 연결된 경우 2가지
            if(s.status.equals("row")) {
                if(s.x <= N && s.y+1 <= N && !check[s.x][s.y+1]) {
                    queue.add(new Status(s.x, s.y+1, "row"));
                }
                if(s.x+1 <= N && s.y+1 <= N && crossAble(s.x, s.y)) {
                    queue.add(new Status(s.x+1, s.y+1, "cross"));
                }
            }
            // 파이프가 대각선으로 연결된 경우 3가지
            else if(s.status.equals("cross")) {
                if(s.x <= N && s.y+1 <= N && !check[s.x][s.y+1]) {
                    queue.add(new Status(s.x, s.y+1, "row"));
                }
                if(s.x+1 <= N && s.y+1 <= N && crossAble(s.x, s.y)) {
                    queue.add(new Status(s.x+1, s.y+1, "cross"));
                }
                if(s.x+1 <= N && s.y <= N && !check[s.x+1][s.y]) {
                    queue.add(new Status(s.x+1, s.y, "col"));
                }
            }
            // 파이프가 세로로 연결된 경우 2가지
            else if(s.status.equals("col")) {
                if(s.x+1 <= N && s.y <= N && !check[s.x+1][s.y]) {
                    queue.add(new Status(s.x+1, s.y, "col"));
                }
                if(s.x+1 <= N && s.y+1 <= N && crossAble(s.x, s.y)) {
                    queue.add(new Status(s.x+1, s.y+1, "cross"));
                }
            }
        }
    }

    // 대각 선일때 파이프를 연결 할 수 없는 위치인 경우 3방향 모두 체크해야함
    public static boolean crossAble(int x, int y) {
        return !check[x][y+1] && !check[x+1][y+1] && !check[x+1][y];
    }

    static class Status{
        int x;
        int y;
        String status;
        public Status(int x, int y, String status) {
            this.x = x;
            this.y = y;
            this.status = status;
        }
    }

}