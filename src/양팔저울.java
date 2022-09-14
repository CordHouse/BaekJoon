import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 양팔저울 {
    static boolean[][] ball;
    static int N;
    static int[] nValue;
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 선언
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        N = Integer.parseInt(br.readLine());
        nValue = new int[N+1];
        ball = new boolean[N+1][15001];

        // Step 2. 추의 갯수와 무게 저장
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < N+1; i++){
            nValue[i] = Integer.parseInt(st.nextToken());
        }

        // Step 3. 배열의 초기값을 재귀함수를 통해 설정
        dfs(0, 0);

        // Step 4. 마지막 배열의 계산하고자 하는 물건의 무게를 찾아 true 인 곳은 Y로 아닌 곳은 N으로 설정한다.
        int M = Integer.parseInt(br.readLine());
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i < M+1; i++){
            int check = Integer.parseInt(st.nextToken());
            if(check > 15000)
                sb.append("N ");
            else{
                if(ball[N][check])
                    sb.append("Y ");
                else
                    sb.append("N ");
            }
        }
        System.out.println(sb);
    }

    // Step 5. 추의 무게는 3가지 방법으로 고려할 수 있다.
    // 1g, 4g => true 가 되는 구간은 1, 3, 4, 5구간 이다.
    // DP를 만들지 않고, 재귀로 사용해서 푸는 이유는 각 추 무게마다 시작하는 지점이 달라지면 값도 달라지기 때문이다.
    public static void dfs(int count, int weight){
        if(ball[count][weight]) // 재귀로 형성 되기 때문에 이미 만들어진 곳이라면 바로 리턴 시킨다.
            return;
        ball[count][weight] = true;
        if(count == N)
            return;

        dfs(count + 1, Math.abs(weight-nValue[count+1]));
        dfs(count + 1, weight);
        dfs(count + 1, weight+nValue[count+1]);
    }
}
