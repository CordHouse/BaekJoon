package string;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class AC_test {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int cycle = Integer.parseInt(br.readLine());

        for(int i = 0; i < cycle; i++) {
            String[] cmd = br.readLine().split("");
            int leftPointer = 0;
            int rightPointer = Integer.parseInt(br.readLine())-1;
            String input = br.readLine();
            boolean right = false, check = false; // default 앞
            String[] value = input.substring(1, input.length()-1).split(",");

            for(int index = 0; index < cmd.length; index++) {
                // R 이 제시된 경우 반전을 줘야하는데, 전반적으로 뒤집으면 시간초과나기 때문에
                // 뒤집은 것처럼 보이는 효과가 필요함
                if(cmd[index].equals("R")) {
                    right = !right;
                }
                // 포인터의 위치가 교차하는 순간 error 발생
                else if(leftPointer > rightPointer) {
                    System.out.println("error");
                    check = true;
                    break;
                }
                else if(!right) { // 앞이 기준인 경우
                    leftPointer++;
                }
                else { // 뒤가 기준인 경우
                    rightPointer--;
                }
            }
            if(check) { // 포인터의 교차 순간은 패스
                continue;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("[");
            // 해당 조건을 넣지 않으면, rightPointer 가 음수인 경우 에러 발생
            // [1] <- 배열 값이 하나인 경우 에러 발생
            if(leftPointer >= 0 && rightPointer >= 0 && leftPointer <= rightPointer) {
                // 앞이 기준인 경우 앞부터 양 포인터 사이 값을 출력
                if (!right) {
                    sb.append(value[leftPointer]);
                    for (int index = leftPointer + 1; index <= rightPointer; index++) {
                        sb.append(",").append(value[index]);
                    }
                } else { // 뒤가 기준인 경우 뒤부터 양 포인터 사이 값을 출력
                    sb.append(value[rightPointer]);
                    for (int index = rightPointer - 1; index >= leftPointer; index--) {
                        sb.append(",").append(value[index]);
                    }
                }
            }
            sb.append("]");
            System.out.println(sb);
        }
    }
}
