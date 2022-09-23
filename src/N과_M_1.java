import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class N과_M_1 {
    // 전역 변수 선언
    static int[] arr;
    static boolean[] check;
    static int N, M;
    static StringBuilder sb = new StringBuilder(); // 이게 있고 없고 차이가 속도차이가 엄청나다
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        arr = new int[M];
        check = new boolean[N+1];

        // Step 2. 백트래킹 진입
        backTracking(0);
        System.out.println(sb);
    }
    // 백트래킹은 방문 여부를 체크 해줘야 한다.
    // 깊이 배열 공간에 값을 집어넣어준다.
    public static void backTracking(int cnt){
        // Step 3. 종료문
        if(cnt == M){
            for(int value : arr) {
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        // Step 4. 동작문
        for(int i = 1; i<=N; i++){
            if(!check[i]) {
                check[i] = true;
                arr[cnt] = i;
                backTracking(cnt+1);
                check[i] = false;
            }
        }
    }
}
