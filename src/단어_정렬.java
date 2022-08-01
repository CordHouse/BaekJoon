import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class 단어_정렬 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Map<String, Integer> map = new TreeMap<>();

        int word = Integer.parseInt(br.readLine());

        for(int i = 0; i < word; i++){
            String inputWord = br.readLine();
            map.put(inputWord, inputWord.length());
        }

        List<String> keyList = new LinkedList<>(map.keySet());
        Collections.sort(keyList, new Comparator<String>(){
            @Override
            public int compare(String o1, String o2){
                if(map.get(o2) < map.get(o1))
                    return 1;
                else if(map.get(o2) > map.get(o1))
                    return -1;
                return 0;
            }
        });

        for(String key : keyList){
            System.out.println(key);
        }
    }
}
