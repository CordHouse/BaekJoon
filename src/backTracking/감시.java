package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class 감시 {
    private static int min = Integer.MAX_VALUE;
    private static String[][] map;
    private static List<CCTV> cctv = new ArrayList<>(); // 1~5까지의 cctv 가 담길 리스트
    private static int row, col;
    private static int[] dx = {-1,0,1,0}, dy = {0,1,0,-1};
    // type 이 가장 중요하다.
    // type 은 3차원 배열로 각 cctv 마다 90도씩 회전한 경우를 적용하여 가지 수를 입력 한 배열이다.
    private static int[][][] type = {
            {{0}},
            {{0}, {1}, {2}, {3}},
            {{0, 2}, {1, 3}},
            {{0, 1}, {1, 2}, {2, 3}, {3, 0}},
            {{0, 1, 2}, {1, 2, 3}, {2, 3, 0}, {3, 0, 1}},
            {{0, 1, 2, 3}}};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        col = Integer.parseInt(input[0]);
        row = Integer.parseInt(input[1]);
        map = new String[col][row];

        // map 초기화 및 cctv 찾아서 넣기
        for(int i = 0; i < col; i++) {
            String[] line = br.readLine().split(" ");
            for(int j = 0; j < row; j++) {
                if("12345".contains(line[j])) {
                    cctv.add(new CCTV(i, j, line[j]));
                }
                map[i][j] = line[j];
            }
        }
        backTracking(0, map);
        System.out.println(min);
    }

    public static void backTracking(int depth, String[][] map) {
        if(depth == cctv.size()) {
            int count = 0;
            for(int i = 0; i < col; i++) {
                for(int j = 0; j < row; j++) {
                    if(map[i][j].equals("0")) {
                        count++;
                    }
                }
            }
            min = Math.min(min, count);
            return;
        }

        CCTV cctvPoint = cctv.get(depth);
        int level = Integer.parseInt(cctvPoint.level);
        // 여기서 헷갈릴 수 있는데 처음 포문이 가지 수 만큼 반복한다.
        // 예를 들어서 cctv 가 1번이였다면 가지수는 총 {1}, {2}, {3}, {4}로 총 4가지이다.
        for(int i = 0; i < type[level].length; i++) {
            // clone 을 활용하여 배열의 값하나씩 가져와 복사한다.
            // 주의할 점은 cloneMap = map.clone 을 하게되면 같은 해시 값을 가르키기 때문에 값이 map 도 변하게 된다.
            // 2차원 배열에서 얕은 복사를 하게 되면 1차원 배열의 주소 값이 들어가게 된다.
            // 그래서 1차원 배열의 값을 실제 값으로 만들어주기 위해서는 1차원 배열에서 clone 해주어야 한다.
            String[][] cloneMap = new String[col][row];
            for(int k = 0; k < col; k++) {
                cloneMap[k] = map[k].clone();
            }

            // idx 의 범위가 1 ~ 4 사이 값인데 이 범위에 따라 한번에 뻗어나갈 가지수가 달라진다.
            // 예를들어 4면 4방향 모두 # 으로 만든 상태로 반복문이 종료한다.
            for(int idx = 0; idx < type[level][i].length; idx++) {
                // dx, dy 배열을 가르키는 값은 type[level][i][idx]에 들어있으니 주의하자!
                // dx[idx] 이렇게 쓰면 안된다.
                int next = type[level][i][idx];

                int nx = cctvPoint.x + dx[next];
                int ny = cctvPoint.y + dy[next];

                while(true) {
                    // 벽을 만나거나
                    if(nx < 0 || ny < 0 || nx >= col || ny >= row) {
                        break;
                    }
                    // 6을 만나면 정지
                    if(cloneMap[nx][ny].equals("6")) {
                        break;
                    }
                    cloneMap[nx][ny] = "#";
                    nx += dx[next]; // 한쪽 방향으로 이동하면서 값 채워넣기
                    ny += dy[next]; // 위와 동일
                }
            }
            backTracking(depth+1, cloneMap);
        }
    }

    static class CCTV{
        int x;
        int y;
        String level; // cctv 번호

        public CCTV(int x, int y, String level) {
            this.x = x;
            this.y = y;
            this.level = level;
        }
    }
}
