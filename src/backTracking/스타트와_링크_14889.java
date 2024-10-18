package backTracking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class 스타트와_링크_14889 {
    private static int[][] table;
    private static boolean[] player;
    private static int n;
    private static int min = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        table = new int[n][n];
        player = new boolean[n];
        for(int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j = 0; j < n; j++) {
                table[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        backtracking(0, 0);
        System.out.println(min);
    }

    private static void backtracking(int index, int count) {
        if(count == n / 2) {
            cal();
        }

        for(int i = index; i < n; i++) {
            if(!player[i]) {
                player[i] = true;
                backtracking(i + 1, count + 1);
                player[i] = false;
            }
        }
    }

    private static void cal() {
        int startTeam = 0, linkTeam = 0;

        for(int i = 0; i < n - 1; i++) {
            for(int j = i + 1; j < n; j++) {
                if(player[i] && player[j]) {
                    startTeam += table[i][j] + table[j][i];
                }
                else if(!player[i] && !player[j]){
                    linkTeam += table[i][j] + table[j][i];
                }
            }
        }

        min = Math.min(min, Math.abs(startTeam - linkTeam));
    }
}
