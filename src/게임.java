import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 게임 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] input = br.readLine().split(" ");
        long x = Integer.parseInt(input[0]);
        long y = Integer.parseInt(input[1]);
        // 100을 먼저 곱해주는 이유는 소수점 자리를 없애기 위해서 항상 y > x 성립
        long search = y * 100 / x;
        // 확률이 99프로는 100프로가 절대로 될 수가 없다. 따라서 예외처리 해줘야함
        if(x == y || search == 99) {
            System.out.println(-1);
            return ;
        }

        System.out.println(binarySearch(x, y, search));
    }

    public static long binarySearch(long left, long right, long search) {
        // 1 ~ x(left)까지 범위를 잡으면 그 안에 확률은 변동하게 되어있다.
        long start = 1L;
        long end = left;
        while(start < end) {
            long mid = (start + end) / 2;
            // 증가된 값 찾아서 확률 비교
            long find = (right + mid) * 100 / (left + mid);
            if(find > search) {
                end = mid;
            }
            else {
                start = mid+1;
            }
        }
        // 최종 가장 작은 값을 리턴
        return end;
    }
}
