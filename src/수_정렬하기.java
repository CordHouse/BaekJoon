import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 수_정렬하기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int tmp = 0;
        int[] buf = new int[count];
        for(int i = 0; i < count; i++){
            buf[i] = Integer.parseInt(br.readLine());
        }
        for(int i = 0; i < count-1; i++){
            for(int j = i; j<count-1; j++) {
                if (buf[i] > buf[j + 1]) {
                    tmp = buf[i];
                    buf[i] = buf[j + 1];
                    buf[j + 1] = tmp;
                }
            }
        }
        for(int i = 0; i<count;i++){
            System.out.println(buf[i]);
        }
    }
}
