package backTracking;

/***
 * 23. 07. 14 16시 25분 시작
 * 23. 07. 14 17시 12분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. [1, 2, 3] 으로 N의 길이를 갖는 수열이 나열되어있다.
 * 2. 해당 수열 중 연속된 숫자가 나올 경우 나쁜 수열이 된다.
 * ex ) 32121 -> 21이 중복된다.
 *
 * 제한사항
 * 1 <= N <= 80
 *
 * 풀이
 * 이번 문제는 백트래킹으로 접근하여 풀 수 있는 문제이다.
 * 처음에 시도했던 방법은 N의 길이를 갖는 수열을 만든다음에 거기서 0부터 길이까지 비교하면서 나쁜수열을 찾았는데
 * 이럴 경우 메모리초과 뿐만아니라 시간초과가 발생한다.
 * 따라서 다른 방법을 선택해야하는데, dfs를 진행하면서 하나씩 숫자를 붙일때 마다 중복된 것이 있는지 확인해야한다.
 * 예를들어서 32121323의 경우 32121에서 중복이 일어난다. 하지만 하나씩 추가하면서 중복을 체크할 경우
 * 32121에서 중복이 일어나기 때문에 맨마지막 1을 제외하게 된다.
 * 그래서 전체 수열을 구한다음 다시 비교할 필요가 없어지게 되는 것이다.
 */

public class 좋은수열_2661 {
    private static int n;
    private static int[] number = {1, 2, 3};
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0, "");
    }

    public static void dfs(int depth, String str) {
        if(depth == n) {
            System.out.println(str);
            System.exit(0);
            return;
        }

        for(int i = 0; i < number.length; i++) {
            // [1, 2, 3] 숫자를 하나씩 넣어보면서 중복여부를 체크한다.
            if(makeString(str + number[i])) {
                dfs(depth+1, str + number[i]);
            }
        }
    }

    // 중복을 확인하는 함수
    public static boolean makeString(String str) {
        // i는 1부터 시작해야한다. i 만큼 곱해야하기 때문
        for(int i = 1; i <= str.length() / 2; i++) {
            // 현재 값
            String cur = str.substring(str.length() - i * 2, str.length() - i);
            // 비교할 값값
           String next = str.substring(str.length() - i);
            if(cur.equals(next)) {
                return false;
            }
        }
        return true;
    }
}
