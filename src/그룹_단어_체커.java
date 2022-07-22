import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 그룹_단어_체커 {
    static int ans = 0;
    public static void main(String[] args) throws IOException {
        int count = 0;
        String str;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int number = Integer.parseInt(br.readLine());
        String[] arr = new String[number];
        for(int i=0; i<number; i++){
            ArrayList<String> arrayList = new ArrayList<>();
            int fail = 0;
            arr[i] = br.readLine();
            for(int j =0; j<arr[i].length(); j++){
                str = String.valueOf(arr[i].charAt(j));
                if(!arrayList.contains(str)) {
                    arrayList.add(count, str);
                }
                else if(arrayList.contains(str) && !arrayList.get(0).equals(str)) {
                    fail = 1;
                }
            }
            if(fail == 0)
                ans += 1;
        }
        System.out.println(ans);
    }
}
