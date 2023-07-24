package prime;

/***
 * 23. 07. 25 00시 10분 시작
 * 23. 07. 25 01시 10분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 조건
 * 1. 게임 시작 전 플레이어는 1부터 1_000_000 사이의 수가 적힌 서로 다른 카드를 잘 섞은 뒤 한장씩 나눠 갖는다.
 * 2. 매 턴마다 플레이어는 다른 플레이어와 한 번씩 결투를 합니다.
 * 3. 결투는 서로의 카드를 보여주는 방식으로 진행되며, 플레이어의 카드에 적힌 수로 다른 플레이어의 카드에 적힌 수를
 * 나눴을 때, 나머지가 0이면 승리합니다. 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나눠 떨어지면
 * 패배합니다. -> 둘다 아닌 경우 무승부
 * 4. 승리한 쪽은 1점을 획득, 패배한 쪽은 1점을 잃습니다. -> 무승부는 0점
 * 5. 본인을 제외한 다른 모든 플레이어와 정확히 한 번씩 결투를 하고 나면 게임이 종료됩니다.
 *
 * 제한사항
 * 2 <= N <= 100_000
 * 1 <= i <= N에 대해 1 <= xi <= 1_000_000
 * 모든 i <= i < j <= N 에 대해 xi != xj
 * 즉 어떤 수도 두 번 이상 등장하지 않습니다.
 *
 * 풀이
 * 이번 문제를 보고 에라토스테네스를 떠올리긴 쉽지 않는 것 같다.
 * 사실 문제에서 힌트를 얻는다면 제한사항에서 두 번이상 같은 숫자가 반복되지 않으며, 변수 값이 100만으로 주어진 다는 것이다.
 * 빠르게 에라토스테네스로 접근해서 문제를 푼다면 다음과 같이 풀 수 있다.
 * */
public class 수_나누기_게임_27172 {
    private static int n;
    private static int[] point, card;
    private static final int MAX = 1_000_001;
    private static boolean[] prime;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        // Step 1. 변수 크기 설정
        point = new int[MAX]; // 점수를 담을 변수
        prime = new boolean[MAX]; // 소수 = 입력 받는 값을 미리 방문처리 하기 위한 변수

        card = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        // Step 2. 방문한 곳 방문처리
        for(int c : card) {
            prime[c] = true;
        }

        // Step 3. 소수 찾기 시작
        makePrime();

        StringBuilder sb = new StringBuilder();
        for(int c : card) {
            sb.append(point[c]).append(" ");
        }
        System.out.println(sb);
    }
    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int c : card) {
            for (int j = c+c; j < MAX; j += c) {
                // 이미 방문했던 곳과 겹친다면 해당 구간은 나눠지는 값이다.
                // j가 c+c 부터 시작해야하는 이유는 나 자신은 제자리에서 더하고 빼기 때문이다.
                if(prime[j]) {
                    point[c]++;
                    point[j]--;
                }
            }
        }
    }
}
