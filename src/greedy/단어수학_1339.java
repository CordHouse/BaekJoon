package greedy;

/***
 * 23. 06. 19 17시 16분 시작
 * 23. 06. 19 18시 35분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 대문자로 이루어진 알파벳이 주어진다.
 * 2. 알파벳은 0 ~ 9까지의 숫자 가중치로 이루어진다.
 * 3. 가중치가 높은 순서대로 값을 구해 해당 문자열을 계산한다.
 *
 * 제한사항
 * 1 <= N <= 10
 * 알파벳은 대문자로 구성된다.
 * 모든 단어에 포함된 알파벳은 최대 10개
 * 수의 최대 길이는 8
 *
 * 풀이
 * 구할 수 있는 수의 최대 값을 구하는 문제로 그리디 알고리즘을 적용한다.
 * 문제를 읽어보면 자바의 경우 Map 을 이용하여 풀이하면 좋을거 같다는 생각이 든다.
 * 여기서 중요한 점은 value 에 어떤 값을 넣어줘야하는 것인가 인데, 첫 시도에서는 가장 높은 알파벳에서 부터 9 -> 0으로
 * 숫자를 대입했었다.
 * 하지만 이는 올바른 방법이 아니다. 예를 들어 10개의 입력이 들어올때, ABB 1개, BB가 9개 들어온다면 어떻게 될까?
 * -> A를 9로 지정했다면 B는 8이 되고, 1780이라는 값이 도출된다.
 * -> B를 9로 지정하고 A를 8로 지정한다면 1790이라는 값이 도출된다.
 * 해당 문제의 핵심은 단위가 더 큰 값이라고 절대 큰 값을 가질 수 없다는 것을 인지해야한다.
 * 각 자릿수 별로 가중치를 누적시켜 최종적으로 큰 값을 9로 선정해야 한다.
 * ex)
 * 2
 * GCB
 * ABDCF
 * {A=10000, B=1001, C=20, D=100, F=1, G=100}
 */
public class 단어수학_1339 {
    private static int setNumber = 9;
    private static int answer = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        Map<Character, Integer> map = new HashMap<>();

        // Step 1. 입력에 대해 각 자릿수 별 누적 값을 구한다.
        for(int i = 0; i < n; i++) {
            String line = br.readLine();
            for(int j = 0; j < line.length(); j++) {
                int num = (int) Math.pow(10, line.length() - j - 1); // 각 자릿수 별 값을 계산
                if(!map.containsKey(line.charAt(j))) { // 해당 key가 없는 경우 num값으로 초기화
                    map.put(line.charAt(j), num);
                    continue;
                }
                map.put(line.charAt(j), map.get(line.charAt(j)) + num); // key가 있는 경우 누적
            }
        }

        // Step 2. 누적된 최종 값 오름차순 정렬
        List<Character> list = new LinkedList<>(map.keySet());
        list.sort((o1, o2) -> map.get(o2) - map.get(o1));

        // Step 3. 각 자리별로 9 -> 0 순으로 곱해서 숫자를 더한다.
        // 1010 * 9 => 9090
        // 10 * 8 => 80
        // answer = 9090 + 80 = 9170
        for(char key : list) {
            answer += map.get(key) * setNumber;
            setNumber--;
        }
        System.out.println(answer);
    }
}