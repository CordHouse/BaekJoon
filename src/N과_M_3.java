import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class N과_M_3 {
    static int N, M;
    static int[] ans;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        ans = new int[M];

        dfs(0);
        System.out.println(sb);
    }

    public static void dfs(int depth){
        if(depth == M){
            for(int value : ans){
                sb.append(value).append(" ");
            }
            sb.append("\n");
            return;
        }

        for(int i = 1; i<= N; i++){
            ans[depth] = i;
            dfs(depth+1);
        }
    }
}
