// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class OneRegister {
        public ArrayList<Integer> sols = new ArrayList<Integer>();
        public ArrayList<Integer> prog = new ArrayList<Integer>();
      public String getProgram(int s, int t) {
          sols.add(t);
          int index = 0;
          if(s == t)
              return "";
          if(t==0)
              return "-";
          if(t==1)
              return "/";
          if(s==0)
              return ":-(";

          while(index < sols.size()) {
              int head = sols.get(index);
              index ++;
              if(Math.sqrt((double) head) % 1 == 0) {
                   int sol = (int) Math.sqrt(head);
                   sols.add(sol);
                   prog.add(0);
                  if(sol == 1 || sol == s)
                    break;
              }

              if(head %2 == 0) {
                   int sol = head/2;
                   sols.add(sol);
                   prog.add(1);
                  if(sol == 1 || sol == s)
                    break;
              }

          }
          if(index >= sols.size())
              return ":-(";

              String res = "";
              index = sols.size() -1;

              if(sols.get(index) == 1)
                  res += "/";
              while(index != 0) {
                  int nex = 0;
                  if(prog.get(index-1) == 0) {
                      nex = sols.get(index) * sols.get(index);
                      res += "*";
                  }
                  else {
                       nex = 2*sols.get(index);
                        res += "+";
                  }
                  index = sols.indexOf(nex);
                  }
            
         return res;

      }	 
       
// BEGIN CUT HERE 
      public static void main(String[] args) {
        	OneRegister tp = new OneRegister();
       	System.out.println(tp.getProgram(7,256));
        } 
// END CUT HERE
      }

