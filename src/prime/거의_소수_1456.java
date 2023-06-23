package prime;

/***
 * 23. 06. 23 14시 30분 시작
 * 23. 06. 23 15시 15분 종료
 * 성공 유무 -> 성공
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 어떤 수가 소수의 N제곱 꼴일때 거의 소수라고 한다.
 * 2. 거의 소수가 두 정수 A와 B 사이의 값으로 몇 개인지 구하여라.
 *
 * 제한사항
 * 1 <= a, b <= 10^14
 *
 * 풀이
 * 소수 문제는 에라토스테네스를 가장 많이 활용하여 문제를 풀 수 있다.
 * 따라서 소수를 만드는 부분의 형태는 비슷하지만, 해당 문제에서 주어진 제한 범위 중 맥스 값이 10^14이기 때문에
 * long 타입으로 선언해서 계산해주어야한다.
 * 또한, 거의 소수를 구할 때 이전엔 루트 max만큼 계산하려 하였으나, i가 루트 max를 넘는 값도 사잇 값이 될 수 있기 때문에
 * 주의해야 한다.
 * ex ) 1 100000000000000
 * max = 100_000_000_000_000
 * max = 10_000_000 -> 루트 맥스
 * 두 max 값을 기준으로 값은 아예 다르게 나오기 때문에 첫번째 맥스 값을 사용해야한다.
 */
public class 거의_소수_1456 {
    private static int answer = 0;
    private static final int SIZE = 10_000_001;
    private static boolean[] prime = new boolean[SIZE];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        // Step 1. 제한 사항 기준으로 타입에 따라 타입 선언하기
        long a = Long.parseLong(input[0]);
        long b = Long.parseLong(input[1]);

        // Step 2. 소수 만들기
        makePrime();

        // Step 3. 소수 중 거의 소수 찾기
        for(int i = 2; i < SIZE; i++) {
            if(!prime[i]) {
                searchPrime(i, a, b);
            }
        }
        System.out.println(answer);
    }

    public static void searchPrime(int prime, long min, long max) {
        long num = 0;
        int n = 2;
        while(true) {
            if(num > max) {
                break;
            }
            num = (long) Math.pow(prime, n++);
            if(num >= min && num <= max) {
                answer++;
            }
        }
    }

    public static void makePrime() {
        prime[0] = true;
        prime[1] = true;

        for(int i = 2; i < Math.sqrt(SIZE); i++) {
            if(prime[i]) {
                continue;
            }
            for(int j = i*i; j < SIZE; j+=i) {
                prime[j] = true;
            }
        }
    }
}
