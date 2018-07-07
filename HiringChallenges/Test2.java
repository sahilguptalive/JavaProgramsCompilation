import java.util.*;
import java.io.*;
import java.lang.*;

public class Test2{

    public static void main(String[] args) {
        sop("\n\nsol: "+solution(7,42, new int[]{6,1}));
  //      sop("\n\nsol: "+solution(1,""));
    //    sop("\n\nsol: "+solution(2,"1A"));
    }

    private static void sop(String str){
        System.out.print(str);
    }
    private static void sop(int[][] matrix,int row){
       for(int i=0;i<row;i++){
            sop("\n"+Arrays.toString(matrix[i]));
       }
    }
  /*static int solution(int X, int Y, int[] A) {
        int N = A.length;
        int result = -1;
        int nX = 0;
        int nY = 0;
        for (int i = 0; i < N; i++) {
            if (A[i] == X)
                nX += 1;
            else if (A[i] == Y)
                nY += 1;
            if (nX == nY)
                result = i;
        }
        return result;
    }*/
    public static int solution(int N,String S){
        int[][] matrix=new int[N][10];
        setAllTo1(matrix,N,10);
        sop(matrix,N);
        markOccupied(matrix,N,10,S);
        sop(matrix,N);
        int sol=0;
        for(int i=0;i<N;i++){
            sop("\n getting sol for 0 to 2");
            sol+=getContSeq(matrix,i,0,3);
            sop("\n getting sol for 3 to 6");
            sol+=getContSeq(matrix,i,3,7);
            sop("\n getting sol for 7 to 9");
            sol+=getContSeq(matrix,i,7,10);
        }
        return sol;
    }

    private static int getContSeq(int[][] matrix,int row,int start,int end){
        int sol=0;
        for(int i=start;i+2<end;){
            //sop("\n i is: "+i);
            if(matrix[row][start]+
                    matrix[row][start+1]+
                    matrix[row][start+2]==3){
                       //found one continuous sequence of 3
                        sol++;
                        i+=3;
                    }else{
                        //find last index of 0 in first 3
                        if(matrix[row][start+2]==0){
                            i=start+3;
                        }
                        else if(matrix[row][start+1]==0){
                            i=start+2;
                        }
                        else if(matrix[row][start]==0){
                            i=start+1;
                        }
                    }
        }else{
            i++;
        }
        sop("\nreturning sol in continous seq sol: "+sol+"start: "+start+"end: "+end+"row: "+row);

        return sol;

    }

    private static void setAllTo1(int[][]matrix,int row,int col){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                matrix[i][j]=1;
            }
        }
    }

    private static void markOccupied(int [][] matrix,int row,int col,String occ){
        if(occ.trim().isEmpty()){
            return;
        }
        String[] spltArray=occ.split(" ");
        sop("\nsplitted string: "+Arrays.toString(spltArray));
        for(String splt:spltArray){
            matrix[getRow(splt.charAt(0))][getColumn(splt.charAt(1))]=0;
        }
    }
    
    private static int getRow(char ch){
        return (ch-'0')-1;
    }
    private static int getColumn(char ch){
        switch(ch){
            case 'A':
            case 'B':
            case 'C':
            case 'D':
            case 'E':
            case 'F':
            case 'G':
            case 'H':
                return ch-'A';
            case 'J': 
            case 'K':
                return ch-'A'-1; 
            default:
                throw new IllegalArgumentException("illegal column ref");
        }
    }
}
