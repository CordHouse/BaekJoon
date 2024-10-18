package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class 연산자_끼워넣기_14888 {
    private static int n, min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
    private static int[] num, sign;
    private static char[] cmd;
    private static boolean[] visit;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        num = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        sign = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        cmd = new char[n-1];
        visit = new boolean[n-1];
        int count = 0;
        for(int i = 0; i < sign.length; i++) {
            while(sign[i] --> 0) {
                if(i == 0) {
                    cmd[count++] = '+';
                }
                else if(i == 1) {
                    cmd[count++] = '-';
                }
                else if(i == 2) {
                    cmd[count++] = '*';
                }
                else if(i == 3){
                    cmd[count++] = '/';
                }
            }
        }
        backtracking(0, 0, new char[n-1]);
        System.out.println(max);
        System.out.println(min);
    }

    private static void backtracking(int index, int count, char[] c) {
        if(count == n - 1) {
            cal(c);
            return;
        }

        for(int i = 0; i < cmd.length; i++) {
            if(!visit[i]) {
                visit[i] = true;
                c[index] = cmd[i];
                backtracking(index + 1, count + 1, c);
                visit[i] = false;
            }
        }
    }

    private static void cal(char[] c) {
        int sum = num[0];

        for(int i = 1; i < num.length; i++) {
            if(c[i-1] == '+') {
                sum += num[i];
            }
            else if(c[i-1] == '-') {
                sum -= num[i];
            }
            else if(c[i-1] == '*') {
                sum *= num[i];
            }
            else if(c[i-1] == '/') {
                sum /= num[i];
            }
        }

        max = Math.max(max, sum);
        min = Math.min(min, sum);
    }
}
