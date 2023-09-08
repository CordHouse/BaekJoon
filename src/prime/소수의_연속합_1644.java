package prime;

/**
 * 23. 09. 08 23시 20분 시작
 * 23. 09. 09 00시 36분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 연속된 소수들의 합이 N과 같은 경우의 개수를 구해야한다.
 * 2. 같은 소수는 한 번 밖에 쓸 수 없다.
 * 3. 꼭 소수는 연속적이어야 한다.
 *
 * 제한사항
 * 1 <= N <= 4_000_000
 *
 * 풀이
 * 이번 문제는 소수와 투포인터의 응용으로 까다로운 문제에 속한다.
 * 우선 주어진 입력이 4_000_000까지의 범위를 가지고 있기 때문에, 반복을 잘못했다간 시간초과가 발생할 수 밖에 없다.
 * 그렇다는 것은 누적되는 합에 따라 투포인터를 활용해 더하거나 빼서 시간복잡도를 줄여야만 했다.
 * 시작 포인터인 2와 3, 그리고 둘의 합인 5는 미리 제외하여 경우의 수를 출력해줘야 오류가 발생하지 않는다.
 * 시작점을 기준으로 goal 과 같아지는 지점에서 카운팅을 해주어야 한다.
 * 종료는 s가 4_000_000에 도달하는 시점과, s가 e와 같아지는 시점에서만 종료시켜주면 된다.
 * 그래야 쓸대없는 시간낭비를 줄여줄 수 있다.
 */

public class 소수의_연속합_1644 {
    private static final int MAX = 4_000_001;
    private static int goal, sum;
    private static int answer = 0;
    private static boolean[] prime = new boolean[MAX];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        goal = Integer.parseInt(br.readLine());

        // Step 1. 2, 3, 5에 대한 예외처리
        if(goal == 2 || goal == 3) {
            System.out.println(1);
            return;
        }
        if(goal == 5) {
            System.out.println(2);
            return;
        }

        // Step 2. 소수 만들기
        makePrime();

        // Step 3. 투포인터 시작
        int s = 2;
        int e = 3;
        int tmp = 0;
        sum = s + e;
        // e가 MAX - 1 이 되는 시점을 꼭 추가해주어야 한다. 그렇지 않으면 반복문이 끝나지 않아 인덱싱 에러가 발생한다.
        while(s < e && e < MAX-1) {
            if(s == goal) {
                break;
            }
            // 정답에 대한 카운팅
            if(sum == goal && tmp != 0) {
                answer++;
            }
            // 작은 경우
            if(sum < goal) {
                tmp = searchPrime(++e);
                sum += tmp;
            }
            // 큰 경우
            else {
                tmp = searchPrime(s++);
                sum -= tmp;
            }
        }
        System.out.println(answer);
    }

    // 소수 판별
    public static int searchPrime(int num) {
        if(!prime[num]) {
            return num;
        }
        return 0;
    }
    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(MAX); i++) {
            for(int j = i*i; j < MAX; j+=i) {
                if(!prime[j]) {
                    prime[j] = true;
                }
            }
        }
    }
}
