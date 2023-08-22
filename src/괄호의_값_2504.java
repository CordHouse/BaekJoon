/**
 * 23. 08. 22 04시 00분 시작
 * 23. 08. 22 05시 24분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * 주어진 조건
 * 1. 4개의 기호 '(', ')', '[', ']' 를 이용해 올바른 괄호열을 찾는다.
 * 2. 한 쌍의 괄호는 (), [] 이 존재한다.
 * 3. () -> 값 2
 * 4. [] -> 값 3
 * 5. (X) -> 값 2 * X
 * 6. [X] -> 값 3 * X
 *
 * 제한사항
 * 1 <= String.length <= 30
 *
 * 풀이
 * 이번 문제의 핵심은 어떻게 괄호의 쌍을 찾아 주어진 조건 3~6번 처럼 계산하는가 이다.
 * 자바의 경우 Stack이라는 자료구조를 사용해 접근해 푸는 것이 가장 좋다.
 * 하지만 이번 문제의 경우 괄호가 올바르게 적용되었는가를 고려하는게 아니라 계산을 해야하기 때문에 조금더 심화적인 고민을
 * 해야한다.
 * 이 해결방법을 찾지 못해 블로그를 찾아보았는데 분배법칙을 이용하여 풀었다고 적혀있다.
 * 예를들어 (()[[]])([]) 처럼 문제가 제시되어 있다면, (()[[]])의 경우 22라는 값이 나와야한다.
 * 분배법칙으로 생각해보면 (()) + ([[]])처럼 적용이 가능하다. 즉 4 + 18 = 22라는 값이 도출된다.
 * 이 방법을 찾았다면 괄호가 열릴때마다 곱해주고, 닫힐때마다 곱한만큼 값을 정리해주면 원하는 값이 도출되는 것을 알 수 있다.
 * 자세한 풀이는 아래를 참고하자!
 */

public class 괄호의_값_2504 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        // Step 1. Stack 자료구조 사용
        Stack<Character> input = new Stack<>();

        // Step 2. 변수 지정 및 반복
        int answer = 0;
        int tmp = 1; // 중간에 계산되는 X의 값
        for(int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);

            if(c == '(') { // 괄호가 열림
                input.push(c);
                tmp *= 2;
            } else if(c == '[') { // 괄호가 열림
                tmp *= 3;
                input.push(c);
            } else { // 괄호가 잠김
                if(c == ')') {
                    if(input.isEmpty() || input.peek() != '(') {
                        answer = 0;
                        break;
                    }
                    else if(line.charAt(i-1) == '(') {
                        answer += tmp;
                    }
                    input.pop();
                    tmp /= 2; // 값 정리 (분배 법칙으로 인해 빼줘야 계산이 맞음)
                } else if(c == ']') {
                    if(input.isEmpty() || input.peek() != '[') {
                        answer = 0;
                        break;
                    }
                    else if(line.charAt(i-1) == '[') {
                        answer += tmp;
                    }
                    input.pop();
                    tmp /= 3; // 값 정리 (분배 법칙으로 인해 빼줘야 계산이 맞음)
                }
            }
        }

        // Step 3. 스택이 비어있지 않다면 괄호의 갯수가 맞지 않는 것이기 때문에 0 처리
        // 요기 주의해야함!
        if(!input.isEmpty()) {
            answer = 0;
        }

        System.out.println(answer);
    }
}
