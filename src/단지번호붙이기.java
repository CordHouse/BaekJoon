import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


// DFS => 깊이 우선 탐색 ( 자식노드 먼저 ), Stack 사용
// BFS => 넓이 우선 탐색 ( 형제노드 먼저 ), Queue 사용

public class 단지번호붙이기 { // DFS, BFS 둘다 풀수 있는 방식
    static int[][] map; // 2차원 배열 값을 담을 변수 선언
    static Boolean[][] check; // 해당 하는 자리는 방문했던 곳인지 확인하는 변수 선언
    static int[] build_Count; // 각 단지별 갯수를 판단하기 위해 선언
    static int build_Num = 0, loop; // build_Num = 단지, loop = 2차원 배열의 끝자리를 판단하기 위함
    static int[] dx = {-1, 1, 0, 0}, dy = {0, 0, -1, 1}; // 상하좌우를 검색하기 위한 방법

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        loop = Integer.parseInt(br.readLine());

        map = new int[loop][loop];
        check = new Boolean[loop][loop];
        build_Count = new int[loop * loop]; // 얘는 왜 이렇게 잡지?

        // Step1. 첫번째 for 문에서는 표를 만들어 값을 넣어주고 초기화를 해준다.
        for (int i = 0; i < loop; i++) {
            String st = br.readLine();
            for (int j = 0; j < loop; j++) {
                map[i][j] = st.charAt(j) - '0';
                check[i][j] = Boolean.FALSE;
            }
        }

        // Step2. 두번째 for 문에서는 1이 처음 나타났을때 1번째 단지를 시작해주며 DFS 함수 호출
        for (int i = 0; i < loop; i++) {
            for (int j = 0; j < loop; j++) {
                if (map[i][j] == 1 && !check[i][j]) {
                    build_Num++;
                    DFS(i, j);
                }
            }
        }

        Arrays.sort(build_Count); // 오름차순 정리
        System.out.println(build_Num); // 단지 수 출력

        // Step4. 오름차순 정리로 인해 필요없는 값 0은 패스하기 위해 해당 문장처리
        for(int i = 0; i<build_Count.length; i++){
            if(build_Count[i] == 0)
                continue;
            System.out.println(build_Count[i]);
        }
    }

    static void DFS(int x, int y) {
        // 한번 탐색한 곳은 False -> True로 바꿔준다.
        check[x][y] = Boolean.TRUE;
        build_Count[build_Num]++;

        // Step3. 해당하는 위치에 상하좌우 값을 확인
        for (int i = 0; i < 4; i++) {
            int nx = x + dx[i];
            int ny = y + dy[i];

            // 해당 범위는 2차원 배열의 시작점과 끝점을 의미한다.
            if (nx >= 0 && ny >= 0 && nx < loop && ny < loop) {
                if (map[nx][ny] == 1 && !check[nx][ny]) {
                    DFS(nx, ny); // 단지별 묶음이기 때문에 다시 재귀호출
                }
            }
        }
    }
}
