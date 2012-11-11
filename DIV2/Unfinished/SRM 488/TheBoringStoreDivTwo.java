// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class TheBoringStoreDivTwo {

      public ArrayList<String> fst = new ArrayList<String>();
      public ArrayList<String> snd = new ArrayList<String>();

      public String find(String J, String B) {
             String fin = "";
            int size = 0;
            for(int i=0; i<J.length(); i++) {
                for(int j=i+1; j<J.length(); j++) {
                    String cur1 = J.substring(i, j);
                    for(int k=j+1; k<J.length(); k++) {
                        for(int k1=k+1; k1<=J.length(); k1++){
                            String cur2 = J.substring(k,k1);
                            if(cur1.charAt(0) == cur2.charAt(0)){
                                String diff="";
                                if(cur1.length()>cur2.length())
                                    diff=cur1.substring(cur2.length());
                                else if(cur1.length()<cur2.length())
                                        diff=cur2.substring(cur1.length());
                                if(diff.length()==0 || B.contains(diff)){
                                    fst.add(cur1);
                                    snd.add(cur2);
                                }
                            }
                        }
                    }

                }
            }

             for(int i=0; i<B.length(); i++) {
                for(int j=i+1; j<B.length(); j++) {
                    String cur1 = B.substring(i, j);
                    for(int k=j+1; k<B.length(); k++) {
                        for(int k1=k+1; k1<=B.length(); k1++){
                            String cur2 = B.substring(k,k1);
                            if(cur1.endsWith(cur2) || cur2.endsWith(cur1)) {
                                for(int c=0; c<fst.size();c++){

                                    String f1=fst.get(c).concat(cur1);
                                    String s1=snd.get(c).concat(cur2);
                                    if(f1.equals(s1) && (f1.length() > size ||(f1.length() == size && fin.compareTo(f1)>0))){
                                        size = f1.length();
                                        fin = f1;
                                    }

                                    f1=fst.get(c).concat(cur2);
                                    s1=snd.get(c).concat(cur1);
                                    if(f1.equals(s1) && (f1.length() > size ||(f1.length() == size && fin.compareTo(f1)>0))){
                                        size = f1.length();
                                        fin = f1;
                                    }
                                }
                            }
                        }
                    }
                }
             }
            boolean[] one = new boolean[10];
            
            return fin;


      }
       
// BEGIN CUT HERE 
      public static void main(String[] args) {
        	TheBoringStoreDivTwo tp = new TheBoringStoreDivTwo();
       	System.out.println(tp.find("gg","hh"));
        } 
// END CUT HERE
      }

