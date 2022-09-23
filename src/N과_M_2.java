import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
public class N과_M_2 {
    // 전역 변수 선언
    static int N, M;
    static boolean[] check;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        check = new boolean[N+1];
        ans = new int[M];

        // dfs 시작, cnt = depth, start = 반복 시작 위치
        // ex) 앞보다 뒤에 숫자가 하나더 많이 출발하기 때문에 이렇게 설정
        dfs(0, 1);
        System.out.println(sb);
    }
    public static void dfs(int cnt, int start){
        if(cnt == M){
            for(int value : ans){
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = start; i<=N; i++){
            if(!check[i]){
                check[i] = true;
                ans[cnt] = i;
                dfs(cnt+1, i+1);
                check[i] = false;
            }
        }
    }
}
