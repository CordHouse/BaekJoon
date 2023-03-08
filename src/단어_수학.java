import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 단어_수학 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(br.readLine());
        int number = 9;
        int sum = 0;
        HashMap<String, Integer> map = new HashMap<>();

        for(int i = 0; i < count; i++) {
            char[] c = br.readLine().toCharArray();
            for(int j = 0; j < c.length; j++) {
                String key = String.valueOf(c[j]);
                int num = (int) Math.pow(10, c.length - j - 1);
                if(!map.containsKey(key)) {
                    map.put(key, num);
                    continue;
                }
                map.put(key, map.get(key) + num);
            }
        }

        List<String> list = new LinkedList<>(map.keySet());
        list.sort(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                if(map.get(o1) > map.get(o2)) {
                    return -1;
                }
                return 1;
            }
        });

        for(String key : list) {
            sum += map.get(key) * number;
            number--;
        }
        System.out.println(sum);
    }
}
