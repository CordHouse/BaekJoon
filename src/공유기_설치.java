import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 공유기_설치 {
    private static int min = 0; // 최소 값
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String[] input = br.readLine().split(" ");
        int house = Integer.parseInt(input[0]); // 집의 개수
        int share = Integer.parseInt(input[1]); // 공유기 설치 개수
        int[] distance = new int[house]; // 거리를 담는 배열

        for(int i = 0; i < distance.length; i++) {
            distance[i] = Integer.parseInt(br.readLine());
        }

        // 오름차순 정렬
        Arrays.sort(distance);

        binary(1, distance[house-1] - distance[0], distance, share);
        System.out.println(min);
    }
    public static void binary(int left, int right, int[] distance, int share) {
        // 거리의 최소 + 거리의 최대
        while(left <= right) {
            int mid = (left + right) / 2;

            if(wifi(mid, distance) < share) {
                right = mid - 1;
            }
            else {
                // 설치할 수 있는 공유기 갯수보다 크거나 같은 경우
                min = mid;
                left = mid + 1;
            }
        }
    }

    public static int wifi(int mid, int[] distance) {
        int count = 1; // 맨 처음 집에 설치한다는 가정
        int lastLocate = distance[0]; // 설치한 집 중 가장 마지막 집을 선택
        for(int i = 1; i < distance.length; i++) {
            int locate = distance[i];

            // 두 거리 사이차이가 양수이면서 찾고자하는 길이보다 큰 경우만 카운트
            // 카운트 된 거리가 원하는 설치 갯수인 경우 리턴
            if(locate - lastLocate >= mid) {
                count++;
                lastLocate = locate;
            }
        }
        return count;
    }
}
