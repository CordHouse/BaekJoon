import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class A_B {
//    private static int answer = -1;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
        int a = Integer.parseInt(st.nextToken());
        int b = Integer.parseInt(st.nextToken());
        int answer = -1;
        // count 가 처음에 1인 이유는 b -> a로 찾아가는 것이기 때문에 최초 b를 카운트 해야하기 때문이다.
        // b == a 인 경우는 안세나요? -> b > a 조건이기 때문에 같은 경우는 이전에 count 가 된 후 반복문을 종료하기 때문이다.
        int count = 1;
        while(b > a) {
            // 뒷 자리수가 1인 경우
            if(b % 10 == 1) {
                b = b/10;
            }
            // 2로 나눠 떨어질 수 있는 수인 경우
            else if(b % 2 == 0){
                b /= 2;
            }
            // 중간에 나온 홀수 중 나눠지지 않는 경우 바로 -1 출력
            // 반례 1 15 -> -1이 정상 이 조건을 넣지 않은 경우 b /= 2에서 소수점 없애고 정수로 받아와 나눠버려 문제 발생
            else {
                break;
            }
            count++;
        }
        // 반복문 종료 이후 a와 b가 같은 경우 answer 에 count 된 횟수 담기 그 외 answer = -1
        if(a == b) {
            answer = count;
        }
        System.out.println(answer);
    }
//    public static void main(String[] args) throws IOException {
//        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        StringTokenizer st = new StringTokenizer(br.readLine(), " ");
//        int a = Integer.parseInt(st.nextToken());
//        int b = Integer.parseInt(st.nextToken());
//
//        dfs(a, b, 1);
//        System.out.println(answer);
//    }
//
//    // dfs를 사용하게 된 이유
//    // -> 그래프적인 접근을 하여 answer의 정답을 찾는다.
//    // 왼쪽은 *2 / 오른쪽은 뒷 숫자에 1을 붙인다.
//    // 주의해야 할 사항으론 dfs 변수로 들어가는 a는 꼭 long 타입이여야 한다.
//    // * 2를 한 경우 인트 범위를 넘을 수 있기 때문!! 주어진 a, b 범위는 10^9이기 때문이다.
//    public static void dfs(long a, long b, int count) {
//        // dfs의 종료 조건 a가 b보다 큰 경우
//        if(a >= b) {
//            // answer의 정답은 a == b 가 될 경우가 정답이다.
//            if(a == b) {
//                answer = count;
//                return ;
//            }
//            return ;
//        }
//        // dfs 를 두 경우로 나눠 그래프 모양으로 접근
//        // 왼쪽라인은 a * 2만큼 한 경우
//        // 오른쪽 라인은 일의 자리에 1을 더한 경우
//        dfs(a * 2, b, count+1);
//        dfs(Long.parseLong(a + "1"), b, count+1);
//        return ;
//    }
}
