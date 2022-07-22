import java.util.Scanner;

public class OX퀴즈 {
    public static void main(String[] args){
        Scanner in = new Scanner(System.in);

        int count = in.nextInt();
        int[] sum = new int[count];
        String[] ans = new String[count];
        for(int i = 0; i<count; i++) {
            ans[i] = in.next();
            int ansCount = 0;
            for(int j = 0; j<ans[i].length(); j++){
                if(ans[i].charAt(j) == 'O'){
                    ansCount++;
                    sum[i] += ansCount;
                }
                else{
                    ansCount = 0;
                }
            }
        }

        for(int i = 0; i<count; i++){
            System.out.println(sum[i]);
        }
    }
}
