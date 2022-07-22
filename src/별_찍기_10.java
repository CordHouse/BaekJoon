import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class 별_찍기_10 {
    static String[][] sta;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String st = br.readLine();
        int N = Integer.parseInt(st);

        sta = new String[N][N];

        star(0,0, N,false);

        StringBuilder sb = new StringBuilder();
        for(int i = 0; i<N; i++){
            for(int j = 0; j<N; j++){
                sb.append(sta[i][j]);
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

    public static void star(int x, int y, int num, boolean blank){
        if(blank){
            for(int i = x; i<x+num; i++){
                for(int j = y; j<y+num; j++){
                    sta[i][j] = " ";
                }
            }
            return;
        }

        if(num==1){
            sta[x][y] = "*";
            return;
        }

        int size = num/3;
        int count =0;
        for(int i = x; i<x+num; i+= size){
            for(int j = y; j<y+num; j+= size){
                count++;
                if(count == 5) {
                    star(i,j,size,true);
                }
                else{
                    star(i,j,size,false);
                }
            }
        }
    }
}
