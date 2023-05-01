package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class N_Queen {
    private static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = new int[N];
        // 배열 위치 : 행
        // 배열 값 : 열

        backTracking(0, N, arr);
        System.out.println(count);
    }
    public static void backTracking(int depth, int N, int[] arr) {
        if(depth == N) {
            count++;
            return;
        }
        for(int i = 0; i < N; i++) {
            arr[depth] = i;

            // passCheck 를 통해 퀸의 이동방향과 겹치지 않으며 퀸을 놓을 수 있는 위치를 찾는다.
            // 만약 조건문에 해당하지 않는다면 i값이 증가하고 depth 값은 유지되어 arr[depth] 가 변화하게 된다.
            if(passCheck(depth, arr)) {
                backTracking(depth+1, N, arr);
            }
        }
    }

    public static boolean passCheck(int depth, int[] arr) {
        for(int j = 0; j < depth; j++) {
            // 행은 열보다 무조건 작기 때문에 행과 열이 둘다 같은지 판단할 필요는 없다.
            // 때문에 열을 비교해줘야 하는데, 열은 depth 보다 작은 만큼 반복하기 때문에 같아지는 경우 만 같은 열에 위치한 경우이다.
            // 참고로 퀸은 이미 정해진 위치의 행과 열, 대각선 그 어느 곳에도 속해선 안된다.
            if(arr[depth] == arr[j]) {
                return false;
            }
            // 대각선에 위치한지 판단하기 위한 조건문이다.
            // depth 를 기준으로 이동하면서 대각선에 위치한 경우는 행과 열의 차가 같을 때 이다.
            // (1, 1) 와 (2, 2) = 차이 1로 동일, (1, 1) 와 (2, 1) = 차이가 동일하지 않음
            else if(Math.abs(depth - j) == Math.abs(arr[depth] - arr[j])) {
                return false;
            }
        }
        // 위 두 조건문이 해당되지 않으면 퀸이 공격할 수 없는 위치에 이동하는 것이다.
        return true;
    }
}
