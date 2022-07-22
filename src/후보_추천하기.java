import java.util.ArrayList;
import java.util.Scanner;

public class 후보_추천하기 {
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int Person = sc.nextInt();
        int vote = sc.nextInt();
        ArrayList<Integer> al = new ArrayList<>();
        ArrayList<Integer> al_point = new ArrayList<>();
        int[] in = new int[vote];
        for(int i = 0; i<vote; i++) {
            in[i] = sc.nextInt();
        }
        for(int i = 0; i<vote; i++){
            if(al.contains(in[i])){
                al_point.set(al.indexOf(in[i]), al_point.get(al.indexOf(in[i]))+1);
            }
            else {
                if(al.size()<Person) {
                    al.add(in[i]);
                    al_point.add(0);
                }
                else{
                    if(al_point.get((0)) == 0){
                        al.remove(0);
                        al_point.remove(0);
                    }
                    else if(al_point.get(1) == 0){
                        al.remove(1);
                        al_point.remove(1);
                    }
                    else if(al_point.get(2) == 0){
                        al.remove(2);
                        al_point.remove(2);
                    }
                    al.add(in[i]);
                    al_point.add(0);
                }
            }
        }
        System.out.println(al);
        int tmp = 0;
        for(int i = 0; i<al.size(); i++){
            for(int j = i; j<al.size()-1; j++){
                if(al.get(i) > al.get(j+1)){
                    tmp = al.get(i);
                    al.set(i,al.get(j+1));
                    al.set(j+1,tmp);
                }
            }
            if(i == Person-1)
                System.out.print(al.get(i));
            else
                System.out.print(al.get(i)+" ");
        }
    }
}
