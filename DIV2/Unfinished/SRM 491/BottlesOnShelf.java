// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class BottlesOnShelf {

    public int calc(ArrayList<Integer>){

    }

    public ArrayList<Integer> bar = new ArrayList<Integer>();

      public int getNumBroken(int N, int[] left, int[] right, int[] damage) {



           for(int i=0; i<left.length-1; i++) {
                 if(left[i]<left[i+1]){
                     int aux = left[i];
                     left[i]=left[i+1];
                     left[i+1]=aux;
                     aux=right[i];
                     right[i]=right[i+1];
                     right[i+1]=aux;
                     aux=damage[i];
                     damage[i]=damage[i+1];
                     damage[i+1]=aux;
                     i=0;
               }

           }
           int count = 0;

           for(int i=0; i<left.length; i++) {

           }



// BEGIN CUT HERE 
      public static void main(String[] args) {
        	BottlesOnShelf tp = new BottlesOnShelf();
       	System.out.println(tp.getNumBroken(7,{1},{7},{2}));
        } 
// END CUT HERE
      }

