import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class 숫자_카드 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int f_loop = Integer.parseInt(br.readLine());
        int[] f_arr = new int[f_loop];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 0; i<f_loop; i++){
            f_arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(f_arr);

        int s_loop = Integer.parseInt(br.readLine());
        int[] s_arr = new int[s_loop];
        StringTokenizer st_1 = new StringTokenizer(br.readLine());
        for(int i = 0; i<s_loop; i++){
            s_arr[i] = Integer.parseInt(st_1.nextToken());
            System.out.println(binary(f_arr, s_arr[i]));
        }
    }

    public static String binary(int[] arr, int search){
        int left = 0;
        int right = arr.length-1;

        while(left <= right){
            int mid = arr[(left + right) / 2];
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
