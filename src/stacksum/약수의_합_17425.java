package stacksum;

/***
 * 23. 07. 01 12시 39분 시작
 * 23. 07. 01 13시 46분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

/**
 * 주어진 조건
 * 1. 자연수 A의 약수의 합은 A의 모든 약수를 더한 값이고 그 값은 f(a)로 표현한다.
 * 2. a 보다 작거나 같은 자연수 y의 f(y) 값을 더한 값은 g(x)라고 한다.
 *
 * 제한사항
 * 1 <= T <= 100_000
 * 1 <= N <= 1_000_000
 *
 * 풀이
 * 이번 문제는 누적합을 구하는 문제로 이전에 값들을 활용해 누적해야하는 부분이 존재한다.
 * 약수를 해결하면서 제한사항을 만족시키려면 에라토스테네스의 체의 원리를 활용하여 풀어야한다.
 * 과정은 이러하다. 각 숫자의 배율이 해당 위치를 지나가며 더해진다면 자연스럽게 약수의 합이 완성된 배열이 나오게 된다.
 * 해당 배열을 토대로 다 더해주는 과정이 필요한데, 그 과정은 새로운 배열을 선언하여 이전의 누적에 현재의 누적 값을 더하는
 * 방식으로 진행하면 된다.
 * 이번 문제의 주의사항으로는 10만 * 100만이기 때문에 long 타입의 변수 선언과 배열 또한 누적된 값들이 담기기에
 * long 타입으로 선언해주어야 제한사항에 걸리지 않는다. 이 부분을 주의하며 문제를 풀어보자
 */

public class 약수의_합_17425 {
    private static long[] fx, gx;
    private static final int SIZE = 1_000_001;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t = Integer.parseInt(br.readLine());

        fx = new long[SIZE];
        gx = new long[SIZE];

        Arrays.fill(fx, 1);
        make();

        for(int i = 1; i < SIZE; i++) {
            gx[i] += gx[i-1] + fx[i];
        }

        for(int i = 0; i < t; i++) {
            int num = Integer.parseInt(br.readLine());
            sb.append(gx[num]).append("\n");
        }
        System.out.println(sb);
    }
    public static void make() {
        for(int i = 2; i < SIZE; i++) {
            for(int j = 1; i*j < SIZE; j++) {
                fx[i*j] += i;
            }
        }
    }
}
