package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 문자열_폭발 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // 일반적인 String 연산보다 훨씬 빠르다.
        // 자세한 내용은 cs 공부 -> 자료구조에서 확인해보면 좋다.
        StringBuilder sb = new StringBuilder();
        String inputString = br.readLine(); // 파싱할 문자열
        String boomString = br.readLine(); // 폭파할 문자열

        int inputLen = inputString.length(); // 주어진 문자열의 길이
        int boomLen = boomString.length(); // 폭파할 문자열의 길이

        for(int i = 0; i < inputLen; i++) {
            sb.append(inputString.charAt(i)); // 주어진 문자열을 캐릭터형으로 변환하여 담아준다.
            // 해당 조건이 들어가는 이유는 제거할 폭탄보단 커야 아래 반복문이 돌수 있기 때문이다.
            // 주어진 문자열의 길이 2, 폭파할 문자열의 길이 2 가능
            // 주어진 문자열의 길이 1, 폭파할 문자열의 길이 2 불가능
            if(sb.length() >= boomLen) {
                boolean check = true; // 문자열을 비교하면서 비교 문자열이 서로 다르다면 false
                // 현재까지 입력된 문자열 뒤에서부터 해당 폭파할 문자열까지 길이만큼 반복
                for(int j = 0; j < boomLen; j++) {
                    char inputC = sb.charAt(sb.length() - boomLen + j);
                    char boomC = boomString.charAt(j);

                    // 두 문자가 다르면 종료
                    if(inputC != boomC) {
                        check = false;
                        break;
                    }
                }

                // 그게 아니라면 폭파시킨다. -> 빼준다
                if(check) {
                    sb.delete(sb.length()-boomLen, sb.length());
                }
            }
        }
        // 문자 값이 비었다면 FRULA를 출력
        if(sb.toString().isEmpty()) {
            sb.append("FRULA");
        }
        // 그게아니라면 있는 값 출력
        System.out.println(sb);
    }

}
