/**
 * 23. 09. 05 14시 58분 시작
 * 23. 09. 05 15시 46분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * 주어진 조건
 * 1. 주어진 수식에서 왼쪽부터 순서대로 계산해야한다.
 * 2. 주어진 수식을 계산한 값 중 가장 큰 값이 정답이 되어야 한다.
 * 3. 괄호를 활용하여 수식의 최대값을 활용해 나가면된다.
 *
 * 제한사항
 * 1 <= N <= 19
 * 0 <= 정수 <= 9
 *
 * 풀이
 * 구현 문제로 상단히 난이도가 있게 느껴졌던 문제이다.
 * 특히 주어진 예제 중 "1-9-1-9-1-9-1-9-1-9" ans = 24인 구간이 분배법칙을 사용해야 한다는 점에서 고민을 했던거 같다.
 * 다른 사람의 풀이를 찾아보니, 2가지 선택지를 선택하며 풀 수 있다고 한다.
 * 1. 이전 결과에서 괄호 없이 순서대로 계산하고 ans와 비교하여 값 갱신하기
 * 2. 이전 결과에서 괄호를 활용하여 ans와 비교하고 값 갱신하기
 */
public class 괄호_추가하기_16637 {
    private static List<Character> c = new ArrayList<>();
    private static List<Integer> list = new ArrayList<>();
    private static int len, answer = Integer.MIN_VALUE; // 음수 조건 고려..
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        String input = br.readLine();

        // Step 1. 변수 담기
        // c : 기호
        // list : 숫자
        for(int i = 0; i < n; i++) {
            if(i % 2 == 1) {
                c.add(input.charAt(i));
            }
            else {
                list.add(input.charAt(i) - '0');
            }
        }

        len = n / 2 - 1; // 리스트의 인덱스 번호이기 때문에 -1큼 빼준다.
        dfs(0, list.get(0));
        // 해당 문제는 음수의 조건을 꼭 고려해야한다.
        // 0으로 answer를 초기화 했다가 엄청난 시간낭비를 진행했다.
        // 또한, 0을 곱하는 순간은 ? * 0으로 마무리가 되기 때문에 따로 조건문을 달아줄 필요가 없다.
        System.out.println(answer);
    }

    public static int cal(char c, int left, int right) {
        if(c == '*') {
            return left * right;
        } else if(c == '+') {
            return left + right;
        } else if(c == '-') {
            return left - right;
        }
        return 0;
    }

    public static void dfs(int idx, int result) {
        // idx : 인덱스, len : 리스트의 길이
        // 가장 마지막 인덱스에 도착했을때 answer 의 값이 최종이 되기 때문에 answer를 갱신
        if(idx > len) {
            answer = Math.max(answer, result);
            return ;
        }

        // 괄호 없을 때
        int ans = 0;
        ans += cal(c.get(idx), result, list.get(idx+1));
        dfs(idx+1, ans);

        // 괄호 있을 때
        if(idx < c.size()-1) {
            dfs(idx+2, cal(c.get(idx), result, cal(c.get(idx+1), list.get(idx+1), list.get(idx+2))));
        }
    }
}
