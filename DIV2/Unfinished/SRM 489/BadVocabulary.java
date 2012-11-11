import java.util.*;

public class BadVocabulary {
      public int count(String badPrefix, String badSuffix, String badSubstring, String[] vocabulary) {
          int c = 0;
           for(String word : vocabulary) {
               boolean bad = false;
               if(word.startsWith(badPrefix))
                   bad = true;

               if(!bad && word.endsWith(badSuffix))
                   bad = true;

               if(!bad && word.length() > 2 && word.substring(1, word.length()-1).contains(badSubstring)) {

                   bad = true;
               }

               if(bad)
                   c++;
           }

           return c;
      }

      }



// Powered by FileEdit


// Powered by FileEdit
