import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

public class 숫자의_개수 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int A = Integer.parseInt(br.readLine());
        int B = Integer.parseInt(br.readLine());
        int C = Integer.parseInt(br.readLine());
        String sum = String.valueOf(A*B*C);
        Map<String, Integer> map = new HashMap<>();

        for(int i = 0; i<10; i++){
            map.put(String.valueOf(i), 0);
        }

        for(int i = 0; i< sum.length(); i++){
            if(map.containsKey(String.valueOf(sum.charAt(i))))
                map.put(String.valueOf(sum.charAt(i)), map.get(String.valueOf(sum.charAt(i))) + 1);
        }

        for(String key : map.keySet()){
            System.out.println(map.get(key));
        }
    }
}
