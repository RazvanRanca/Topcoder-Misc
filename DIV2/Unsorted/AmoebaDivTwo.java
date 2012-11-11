import java.util.*;

public class AmoebaDivTwo {
      public int count(String[] table, int K) {
          int count = 0;
          int res = 0;

          for(int i=0; i<table.length; i++) {
              for(int j=0; j<table[0].length(); j++) {
                  char c = table[i].charAt(j);
                  if(c == 'A')
                      count ++;
                  else {
                      if(count>=K)
                        res += count-K+1;
                      count = 0;
                  }

              }
               if(count>=K)
                   res += count-K+1;
                count = 0;
          }
          if(K>1){
          count  = 0;
          for(int i=0; i<table[0].length(); i++) {
              for(int j=0; j<table.length; j++) {
                  char c = table[j].charAt(i);
                  if(c == 'A')
                      count ++;
                  else {
                      if(count>=K)
                        res += count-K+1;
                      count = 0;
                  }

              }
               if(count>=K)
                   res += count-K+1;
                count = 0;
          }
          }
          return res;
      }	 
       

}


// Powered by FileEdit
// Powered by CodeProcessor
