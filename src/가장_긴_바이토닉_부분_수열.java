import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 가장_긴_바이토닉_부분_수열 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int[] number = new int[count+1];
        String[] input = br.readLine().split(" ");
        int[] upDp = new int[count+1];
        int[] downDp = new int[count+1];
        int answer = 0;

        for(int i = 1; i <= count; i++) {
            number[i] = Integer.parseInt(input[i-1]);
        }

        for(int i = 1; i < number.length; i++) {
            up(upDp, i, number);
            down(downDp, i, number);
            answer = Math.max(answer, upDp[i]+downDp[i]);
        }
        System.out.println(answer+1);
    }

    public static int up(int[] upDp, int n, int[] number) {
        if(upDp[n] == 0) {
            for (int j = n - 1; j > 0; j--) {
                if (number[n] > number[j]) {
                    upDp[n] = Math.max(upDp[n], up(upDp, j, number) + 1);
                }
            }
        }
        return upDp[n];
    }

    public static int down(int[] downDp, int n, int[] number) {
        if(downDp[n] == 0) {
            for (int j = n + 1; j < number.length; j++) {
                if (number[n] > number[j]) {
                    downDp[n] = Math.max(downDp[n], down(downDp, j, number) + 1);
                }
            }
        }
        return downDp[n];
    }
}
