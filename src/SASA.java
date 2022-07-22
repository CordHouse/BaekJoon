import java.util.Scanner;

public class SASA {
    public static void main(String[] args){
        Scanner N = new Scanner(System.in);

        int nIN = N.nextInt();
        int mIN = N.nextInt();

        if(1<=nIN && Math.pow(10,9)>=mIN){
            System.out.println(Math.min(nIN/2, mIN/2));
        }
    }
}
