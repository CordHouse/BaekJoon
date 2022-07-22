import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 적록색약 {
    static String[][] RGB, RB;
    static boolean[][] RGB_check, RB_check;
    static int R = 0, G = 0, B = 0, RR=0,BB=0,N;
    static int[] vx = {-1, 1, 0, 0};
    static int[] vy = {0, 0, -1, 1};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        RGB = new String[N][N];
        RB = new String[N][N];
        RGB_check = new boolean[N][N];
        RB_check = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            String st = br.readLine();
            for (int j = 0; j < N; j++) {
                RGB[i][j] = String.valueOf(st.charAt(j));
                RB[i][j] = RGB[i][j].replace("G","R");
                RGB_check[i][j] = Boolean.FALSE;
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (RGB[i][j].equals("R") && !RGB_check[i][j]) {
                    R++;
                    DFS(i, j, "R",0);
                }
                else if(RGB[i][j].equals("G") && !RGB_check[i][j]) {
                    G++;
                    DFS(i, j, "G",0);
                }
                else if(RGB[i][j].equals("B") && !RGB_check[i][j]) {
                    B++;
                    DFS(i, j, "B",0);
                }
                if (RB[i][j].equals("R") && !RB_check[i][j]) {
                    RR++;
                    DFS(i, j, "R",1);
                }
                else if(RB[i][j].equals("B") && !RB_check[i][j]) {
                    BB++;
                    DFS(i, j, "B",1);
                }
            }
        }

        System.out.println((R+G+B) +" "+ (RR+BB));

    }

    static void DFS(int x, int y, String color, int n) {
        if(n == 0)
            RGB_check[x][y] = Boolean.TRUE;
        else if(n==1)
            RB_check[x][y] = Boolean.TRUE;

        for (int i = 0; i < 4; i++) {
            int nx = x + vx[i];
            int ny = y + vy[i];
            if (nx >= 0 && ny >= 0 && nx < N && ny < N) {
                if (RGB[nx][ny].equals(color) && !RGB_check[nx][ny] && n==0)
                    DFS(nx, ny, color,0);
                else if(RB[nx][ny].equals(color) && !RB_check[nx][ny] && n==1)
                    DFS(nx,ny,color,1);
            }
        }
    }
}
