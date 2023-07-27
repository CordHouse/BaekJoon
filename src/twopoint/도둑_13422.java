package twopoint;

/***
 * 23. 07. 28 02시 00분 시작
 * 23. 07. 25 03시 23분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * 주어진 조건
 * 1. 집은 연속적으로 이어져 있으며, 집을 지날때마다 집이 가지고 있는 돈을 모두 가져간다.
 * 2. 총 이동해야하는 집의 돈과 내가 총 가져갈 수 있는 돈을 계산하고 적은 경우만 카운트해야한다.
 *
 * 제한사항
 * 1 <= N <= 100_000
 * 1 <= M <= N
 * 1 <= K <= 1_000_000_000
 *
 * 풀이
 * 이번 문제의 조건과 제한사항으로 볼때 모든 경우의 수를 따지게 된다면 시간초과가 발생하도록 주어졌다.
 * 투 포인터를 사용하여 시작과 끝점에 대해 계산을 진행하도록 하면 빠르게 해결할 수 있다.
 * 실제로 풀어보면서 많이 헤매던 부분은 시작지점을 한칸 올리면서 끝 지점을 초기화하고, sum의 값을 초기화 했었는데
 * 그렇게 푼다면 시간초과가 발생한다.
 * 따라서 시작점에 대한 값을 계산해 뺴주고, 새로운 끝점에 대한 값만 확인한다면 문제없이 통과할 수 있을 것이다.
 * 이 부분에서 시간이 가장 오래 걸렸으며, 추가적으로 n과 m이 같은 순간을 꼭 계산해야한다.
 * e가 1부터 시작하기 때문에 문제가 될 우려가 있다.
 * */

public class 도둑_13422 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        // Step 1. 테스트 케이스만큼 반복
        for(int i = 0; i < cycle; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken()); // 집의 개수
            int m = Integer.parseInt(st.nextToken()); // 방문할 수 있는 집의 길이
            long k = Integer.parseInt(st.nextToken()); // 가져갈 수 있는 돈
            int answer = 0;

            // 집안에 돈을 표기 (입력 값)
            int[] house = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

            // Step 2. 투 포인터 시작
            long sum = 0;
            // 집의 개수와 갈 수 있는 집의 길이가 같은 경우
            if(n == m) {
                for(int j = 0; j < m; j++) {
                    sum += house[j];
                }
                if(sum < k) {
                    System.out.println("1");
                }
                else {
                    System.out.println("0");
                }
            }
            else {
                int s = 0; // 시작점
                int e = 1; // 끝점
                int count = 0; // 갯수 카운트 (방문한 집의 수)
                sum = house[0];
                // 시작 점을 기준으로 반복분 종료
                while (s < n) {
                    // 가져간 돈이 제한보다 크거나, 길이 이상으로 넘어간 경우
                    if (sum >= k || count > m - 1) {
                        sum -= house[s++]; // 시작점을 빼주고
                        count--; // 갯수도 하나 빼준다.
                        continue;
                    }
                    sum += house[e++ % n]; // 끝점을 하나 더해주고
                    count++; // 카운트 증가
                    if (count == m) { // 갯수가 같은 경우
                        answer++; // 증가
                    }
                }
                System.out.println(answer);
            }
        }
    }
}
