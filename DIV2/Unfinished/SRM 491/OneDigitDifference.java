// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class OneDigitDifference {
      public int getSmallest(int N) {
          int len = 0;
          int c = N;
          while(c>=10){
              len++;
              c/=10;
          }
          if(N==0)
              return 1;
          
          c*=Math.pow(10, len);
          return (N%c);
            	            
      }	 
       
// BEGIN CUT HERE 
      public static void main(String[] args) {
        	OneDigitDifference tp = new OneDigitDifference();
       	System.out.println(tp.getSmallest(9));
        } 
// END CUT HERE
      }

