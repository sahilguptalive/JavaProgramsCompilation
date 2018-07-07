import java.util.*;
import java.io.*;
import java.lang.*;

public class Test{

    public static void main(String[] args) {
        sop("\n\nsol: "+solution(-20200000,20020299));
        sop("\n\nsol: "+solution(20,30));
        sop("\n\nsol: "+solution(10,10));
        sop("\n\nsol: "+solution(0,0));
        sop("\n\nsol: "+solution(-1,0));
        sop("\n\nsol: "+solution(-20,20));
    }

    private static int solution(int A,int B){
        if(A>B){
            return 0;
        }
        if(B<0){
            return 0;
        }

        int a = A<0?0:A;
        int b = B<0?0:B;
        sop("\nA:"+A+"a:"+a);
        sop("B: "+B+"b:"+b);
        int sol=0;
        for(int i=a;i<=b;i++){
            sol+=isWholeSquare(i)?1:0;
        }
        return sol;
    }
    
    private static boolean isWholeSquare(int num){
        int sqrt=(int)Math.sqrt(num);
        return sqrt*sqrt==num;
    }
    private static void sop(String str){
        System.out.print(str);
    }

}
