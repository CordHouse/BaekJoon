package twopoint;

/**
 * 23. 08. 20 04시 30분 시작
 * 23. 08. 20 05시 07분 종료
 * 성공 유무 -> 실패
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * 주어진 조건
 * 1. 문자열 S의 가장 앞의 문자를 T의 마지막에 추가한다.
 * 2. 문자열 S의 가장 뒤의 문자를 T의 마지막에 추가한다.
 * 3. 해당 규칙을 토대로 사전순으로 가장 빠른 문자열을 출력한다
 *
 * 제한사항
 * N <= 2_000
 *
 * 풀이
 * 투포인터를 활용할 수 있는 문제로 투포인터의 두 가지 풀이 중 맨 앞과 맨 뒤로 포인터를 잡고 찾는 문제이다.
 * 우선 가장 처음 부분과 끝 부분을 시작 지점으로 잡고 반복하면서 줄여준다. -> 문제의 조건
 * 여기서 주의해야하는 점이 있다. 같은 문자열이 발생한 경우 왼쪽과 오른쪽 중 어느 곳을 먼저 잡아야할까?
 * 다음 문자열을 참고해야한다는 점을 고려해서 문제를 풀어야 한다.
 * 그렇지 않으면 주어진 테케는 통과하지만 히든 테케에서 걸리게 된다.
 */

public class 문자열_생성_6137 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        char[] arr = new char[n];
        for(int i = 0; i < n; i++) {
            arr[i] = br.readLine().charAt(0);
        }

        int s = 0;
        int e = n-1;
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while(s <= e) {
            if(count == 80) {
                sb.append("\n");
                count = 0;
            }
            if(arr[s] - '0' < arr[e] - '0') {
                sb.append(arr[s++]);
            }
            else if(arr[s] - '0' > arr[e] - '0') {
                sb.append(arr[e--]);
            }
            else {
                int left = s;
                int right = e;
                while(arr[left] == arr[right]) {
                    if(left < n-1) {
                        left++;
                    }
                    if(right > 0) {
                        right--;
                    }
                    if(arr[left] < arr[right]) {
                        sb.append(arr[s++]);
                    }
                    else if(arr[left] > arr[right]){
                        sb.append(arr[e--]);
                    }
                }
            }
            count++;
        }

        System.out.println(sb);
    }
}
