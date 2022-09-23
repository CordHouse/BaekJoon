import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과_M_4 {
    static int N, M;
    static int[] ans;
    static boolean[] check;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];
        check = new boolean[N+1];

        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth){
        if(depth == M){
            for(int value : ans){
                sb.append(value).append(" ");
            }
            sb.append("\n");
            check[ans[0]] = true;
            return;
        }

        for(int i = 1; i<=N; i++){
            if(!check[i]) {
                ans[depth] = i;
                if(depth >= 1 && ans[depth-1] > ans[depth]){
                    continue;
                }
                dfs(depth + 1);
            }
        }
    }
}
