import java.util.ArrayList;
import java.util.Scanner;

public class 최댓값 {
    public static void main(String[] args) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        int tmp;
        int check = 1;
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < 9; i++){
            arrayList.add(Integer.parseInt(sc.next()));
        }
        tmp = arrayList.get(0);
        for(int i = 1; i < 9; i++){
            if(tmp < arrayList.get(i)){
                tmp = arrayList.get(i);
                check = i+1;
            }
        }
        System.out.println(tmp);
        System.out.println(check);

    }
}
