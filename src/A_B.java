import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_B {
    private static int answer = -1;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());

        System.out.println(dfs(a, b, 1));
    }

    // dfs를 사용하게 된 이유
    // -> 그래프적인 접근을 하여 answer의 정답을 찾는다.
    // 왼쪽은 *2 / 오른쪽은 뒷 숫자에 1을 붙인다.
    // 주의해야 할 사항으론 dfs 변수로 들어가는 a는 꼭 long 타입이여야 한다.
    // * 2를 한 경우 인트 범위를 넘을 수 있기 때문!! 주어진 a, b 범위는 10^9이기 때문이다.
    public static long dfs(long a, long b, int count) {
        // dfs의 종료 조건 a가 b보다 큰 경우
        if(a >= b) {
            // answer의 정답은 a == b 가 될 경우가 정답이다.
            if(a == b) {
                answer = count;
                return answer;
            }
            return answer;
        }
        // dfs 를 두 경우로 나눠 그래프 모양으로 접근
        // 왼쪽라인은 a * 2만큼 한 경우
        // 오른쪽 라인은 일의 자리에 1을 더한 경우
        dfs(a * 2, b, count+1);
        dfs(Long.parseLong(a + "1"), b, count+1);
        return answer;
    }
}
