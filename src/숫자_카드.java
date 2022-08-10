import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자_카드 {
    public static void main(String[] args) throws IOException {
        // Step 1. 변수 입력
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int f_loop = Integer.parseInt(br.readLine());
        int[] f_arr = new int[f_loop];
        StringTokenizer st = new StringTokenizer(br.readLine());
        // 입력받은 문자를 Token기준으로 배열에 입력
        for(int i = 0; i<f_loop; i++){
            f_arr[i] = Integer.parseInt(st.nextToken());
        }
        // 입력받은 문자 정렬
        Arrays.sort(f_arr);

        int s_loop = Integer.parseInt(br.readLine());
        int[] s_arr = new int[s_loop];
        StringTokenizer st_1 = new StringTokenizer(br.readLine());
        // 다시한번 입력받은 문자를 입력받고 이분 탐색 함수 시작
        for(int i = 0; i<s_loop; i++){
            s_arr[i] = Integer.parseInt(st_1.nextToken());
            System.out.println(binary(f_arr, s_arr[i]));
        }
    }

    // Step 3. 이분 탐색
    public static String binary(int[] arr, int search){
        int left = 0;
        int right = arr.length-1;

        // 반복문 왼쪽 시점과 오른쪽 시점이 교차 되는 시점에 반복문 종료
        while(left <= right){
            int mid = arr[(left + right) / 2]; // mid값을 통해 비교하기 위해 선언
            if(search < mid){
                right = ((left + right) / 2) - 1;
            }
            else if(search > mid){
                left = ((left + right) / 2) + 1;
            }
            else
                return "1";
        }
        return "0";
    }
}
