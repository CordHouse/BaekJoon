import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class 알파벳_찾기 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String text = br.readLine();
        ArrayList<Character> save = new ArrayList<Character>();
        ArrayList<Integer> ans = new ArrayList<Integer>();
        for(int i = 0; i<26; i++){
            save.add((char)('a'+i));
            ans.add(-1);
        }

        for(int j = 0; j<text.length(); j++) {
            for(int k = 0; k<26; k++) {
                if (save.get(k).equals(text.charAt(j)) && ans.get(k) == -1) {
                    ans.set(k,j);
                    break;
                }
            }
        }
        for(int i : ans)
            System.out.print(i+" ");
    }
}
