import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 하노이_탑_이동_순서 {
    public static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();

        int n = Integer.parseInt(st);

        sb.append((int)Math.pow(2,n)-1).append("\n");
        hanoe(n, 1, 2, 3);
        System.out.println(sb);

    }

    public static void hanoe(int n, int start, int mid, int end){
        if(n == 1){
            sb.append(start + " " + end).append("\n");
        }
        else{
            hanoe(n-1, start, end, mid);
            sb.append(start + " " + end).append("\n");
            hanoe(n-1, mid, start, end);
        }
    }
}
