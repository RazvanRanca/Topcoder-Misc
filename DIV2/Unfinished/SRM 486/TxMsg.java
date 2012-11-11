import java.util.*;

public class TxMsg {
      public String getMessage(String original) {
            String fin = "";
            String word = "";
            char c;
            int i=0;
           while(i < original.length()) {
                boolean write = true;
                c = original.charAt(i);
               
            if(containsC(original, i))
                while(c != ' '){
                    write = true;
                    if(isV(c) || (i>0 && !isV(original.charAt(i-1)) && original.charAt(i-1) != ' '))
                        write = false;
                    if(write)
                        word += c;

                    i++;
                     if(i < original.length())
                         c = original.charAt(i);
                    else
                        break;
                    
                }
             
            else {
                while(c != ' '){
                    word += c;
                    i++;
                    if(i < original.length())
                         c = original.charAt(i);
                    else
                        break;
                }
                
            
            }
            
                fin +=word;
                word = "";
                
                if(i < original.length())
                     fin += ' ';
                i++;
                
            }
            

            return fin;
      }	 

     public boolean isV(char c) {
         if(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u')
                    return true;
         return false;
     }

     public boolean containsC(String s, int pos) {

          for(int i=pos; i<s.length(); i++) {

                char c = s.charAt(i);
                if(c == ' ')
                    return false;
         if(!(c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u'))
                    return true;
          }
         return false;
     }
      }



// Powered by FileEdit
