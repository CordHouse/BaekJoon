import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 설탕_배달 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        int num = Integer.parseInt(br.readLine());
//        int save = num, count = 0;
//
//        while(save - 3 >= 0){
//            if(save % 5 == 0){
//                save -= 5;
//                count++;
//            }
//            else if(save % 5 <= 3 && save != 5) {
//                save -= 3;
//                count++;
//            }
//            else if(save % 3 == 0){
//                save -= 3;
//                count++;
//            }
//            else {
//                save -= 5;
//                count++;
//            }
//        }
//
//        if(save != 0)
//            System.out.println("-1");
//        else System.out.println(count);

        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        arr[0] = 0;
        arr[1] = -1;
        arr[2] = -1;
        arr[3] = 1;
        arr[4] = -1;
        for(int i =5; i<=n; i++){
            if(arr[i-3] == -1 && arr[i-5] == -1)
                arr[i] = -1;
            else{
                if(Math.min(arr[i-3], arr[i-5]) == -1)
                    arr[i] = Math.max(arr[i-3], arr[i-5]) + 1;
                else{
                    arr[i] = Math.min(arr[i-3], arr[i-5]) + 1;
                }
            }
        }
        System.out.println(arr[n]);
    }
}
