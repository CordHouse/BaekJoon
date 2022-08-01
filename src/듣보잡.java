import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 듣보잡 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int loopCount = Integer.parseInt(st.nextToken()) + Integer.parseInt(st.nextToken());
        Map<String, Integer> map = new HashMap<>();
        int listSize = 0;

        for(int i = 0; i < loopCount; i++){
            String key = br.readLine();
            if(map.containsKey(key)){
                map.put(key, map.get(key)+1);
                listSize++;
            }
            else{
                map.put(key, 1);
            }
        }

        System.out.println(listSize);
        List<String> keyList = new LinkedList<>(map.keySet());
        Collections.sort(keyList);
        for(String key : keyList){
            if(map.get(key) > 1){
                System.out.println(key);
            }
        }

    }
}
