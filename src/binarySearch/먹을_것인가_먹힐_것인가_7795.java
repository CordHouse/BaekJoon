package binarySearch;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 먹을_것인가_먹힐_것인가_7795 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());

        while (t --> 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int n = Integer.parseInt(st.nextToken());
            int m = Integer.parseInt(st.nextToken());

            int[] a = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();
            int[] b = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).sorted().toArray();

            int answer = 0;
            for(int i = 0; i < b.length; i++) {
                answer += a.length - binarySearch(a, b, i);
            }
            System.out.println(answer);
        }
    }

    private static int binarySearch(int[] a, int[] b, int i) {
        int left = 0;
        int right = a.length-1;
        while(left <= right) {
            int mid = (left + right) / 2;

            if(a[mid] <= b[i]) {
                left = mid + 1;
            }
            else {
                right = mid - 1;
            }
        }
        return left;
    }
}
