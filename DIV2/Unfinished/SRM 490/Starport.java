// BEGIN CUT HERE
package topcoder;
// END CUT HERE

import java.util.*;

public class Starport {
      public int d(int a, int b){
        if(a<b){
            int aux = b;
            b=a;
            a=aux;
        }
        int r=1;
        int q=b;

        while(b!=0){
            q = (int) a/b;
            r = a%b;
            a=b;
            b=r;

        }
        return a;
    }
      public double getExpectedTime(int N, int M) {
         int d = d(N,M);
         long l = (long) M*N/d;

         long j=0;
         long sum = 0;
         long count = 0;
         for(long i=0; i<l; i+=M){
             while(i>j)
                 j+=N;
             sum+=(j-i);
             count++;
         }

         return ((double) Math.round((double)sum/count * 1000000000))/1000000000;
      }

      

// BEGIN CUT HERE
      public static void main(String[] args) {
        	Starport tp = new Starport();
       	System.out.println(tp.getExpectedTime(1000000000, 999999999));
        }
// END CUT HERE
      }

