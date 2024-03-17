import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/***
 * 24. 03. 17 21시 40분 시작
 * 24. 03. 17 22시 10분 종료
 * 성공 유무 -> 실패
 */

/**
 * 주어진 조건
 * 1. 무한히 큰 배열안에 다음과 같은 분수가 지그재그로 나타난다.
 * 2. 1/1 -> 1/2 -> 2/1 -> 3/1 -> 2/2 -> 1/3 ...
 *
 * 제한사항
 * 1 <= X <= 10_000_000
 *
 * 풀이
 * 주어진 제한사항과 시간으로 보았을때 일반적인 알고리즘을 사용하면 시간초과가 발생한다.
 * 따라서 규칙을 찾아야 문제를 해결할 수 있다.
 * 1. 내가 찾고자 하는 x라는 숫자가 어디에 해당하는지 찾아야한다.
 * 2. 대각선을 기준으로 이전 대각선에 포함된 모든 칸들을 구해야한다.
 * 3. 대각선은 분자 + 분모의 값으로 이루어져 있으며 실제 갯수는 분자 + 분모 - 1의 값으로 되어 있다.
 * 일단 3번 내용으로 현재 대각선에 위치한 칸의 갯수를 파악할 수 있다.
 * 파악 후 2번과 더해주면 내가 원하는 x값 보다 크거나 같은지 판단할 수 있다.
 * 지그재그라는 규칙성 덕분에 대각선의 위치가 홀수인지 짝수인지에 따라 분자 분모 값만 서로 변경해주면 된다.
 */
public class 분수찾기_1193 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int x = Integer.parseInt(br.readLine());

        int line = 1; // 대각선의 갯수
        int prevSellCount = 0; // 이전 대각선까지의 칸의 총 합
        String answer;

        while(true) {
            // x <= 대각선 + 이전까지 칸의 갯수를 판단한다.
            if(x <= line + prevSellCount) {
                // 대각선이 짝수인 경우
                if(line % 2 == 0) {
                    // 위 -> 아래로 이동하는 방향
                    // 따라서 위치는 분자가 결정
                    // 중요한건 분자 + 분모 = 대각선의 원래 크기
                    // 이번 문제에서 대각선은 칸의 개수와 맞추기 위해 -1을 해주었고
                    // 따라서 분모는 해당 대각선 칸의 갯수 - 분자 + 1이 될 수 있다.
                    answer = (x - prevSellCount) + "/" + (line - (x - prevSellCount - 1));
                }
                // 대각선이 홀수 인경우
                else {
                    answer = (line - (x - prevSellCount - 1)) + "/" + (x - prevSellCount);
                }
                break;
            }
            // x에 포함되지 않는다면 다음 대각선을 확인하고, 칸의 갯수를 누적해준다.
            else {
                prevSellCount += line;
                line++;
            }
        }
        System.out.println(answer);
    }
}
