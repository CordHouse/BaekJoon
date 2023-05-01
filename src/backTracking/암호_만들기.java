package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 암호_만들기 {
    private static String[] password;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        String[] arr = br.readLine().split(" ");

        int L = Integer.parseInt(input[0]);
        int C = Integer.parseInt(input[1]);
        password = new String[L];

        Arrays.sort(arr); // 백트래킹시 문자열 형태가 알파벳 순서대로 나와야하기 때문에 미리 정렬한다.
        backTracking(0, 0, L, C, arr);
    }

    public static void backTracking(int depth, int idx, int L, int C, String[] arr) {
        if(depth == L) {
            int vowel = 0;
            int notVowel = 0;
            StringBuilder valueBuilder = new StringBuilder();

            for (String s : password) {
                if ("aeiou".contains(s)) {
                    vowel++;
                } else {
                    notVowel++;
                }
                valueBuilder.append(s);
            }
            if (vowel > 0 && notVowel > 1) {
                System.out.println(valueBuilder);
            }

            return;
        }

        for(int i = idx; i < C; i++) {
            // 처음에 중복에 대해 고려하다 set 을 통한 중복제거를 이용했는데 그럴 필요가 없었다.
            // depth 와 idx 에 대해 각각 증가를 시켜준다는 것은 4와 6이 서로 다른 숫자로 주어졌기 때문이다.
            // password 안에 값이 4개가 있더라도, depth 와 L 의 크기를 비교하여 조건문이 발동하기 때문에 그 이전까진
            // depth 위치에 따라 값이 담기게 된다.
            // 결국 모든 경우에 수를 따져보기 때문에 중복이 되지 않는다. ( 미리 정렬했기 때문 )
            password[depth] = arr[i];
            backTracking(depth+1, i+1, L, C, arr);
        }
    }
}