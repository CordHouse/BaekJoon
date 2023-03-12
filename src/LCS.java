import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class LCS {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        char[] first = br.readLine().toCharArray();
        char[] second = br.readLine().toCharArray();
        int[][] lcsTable = new int[first.length+1][second.length+1];

        for(int i = 1; i <= first.length; i++) {
            for(int j = 1; j <= second.length; j++) {
                if(first[i-1] == second[j-1]) {
                    lcsTable[i][j] = lcsTable[i-1][j-1] + 1;
                    continue;
                }
                lcsTable[i][j] = Math.max(lcsTable[i-1][j], lcsTable[i][j-1]);
            }
        }
        System.out.println(lcsTable[first.length][second.length]);
    }
}
