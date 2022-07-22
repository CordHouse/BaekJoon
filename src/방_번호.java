import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;

public class 방_번호 {
    public static void main(String[] args){
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        int count = 0;

        for(int i = 0; i<9; i++){
            hashMap.put(i,0);
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String line = br.readLine();
            for(int i = 0; i < line.length(); i++) {
                Character st = line.charAt(i);
                if(st == '9')
                    st = '6';
                hashMap.replace(Integer.parseInt(String.valueOf(st)), hashMap.get(Integer.parseInt(String.valueOf(st))) + 1);
            }
            for(int j = 0; j<hashMap.values().size(); j++) {
                if(j == 6 && hashMap.get(j) > 1){
                    if(count < (int) Math.round(hashMap.get(j)/2.0))
                        count = (int) Math.round(hashMap.get(j)/2.0);
                }
                else if(hashMap.get(j)>0){
                    if(count < Math.round(hashMap.get(j)))
                        count = Math.round(hashMap.get(j));
                }
            }
            System.out.println(count);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
