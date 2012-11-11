import java.util.*;

public class BuyingFlowers {
    public int bestDiff(int s) {
        if(s==0)
            return -1;

        int k = (int) Math.sqrt((double) s);
        while(s%k != 0)
            k++;
        int j = (int) s/k;

        return Math.abs(k - j);
    }
      public int buy(int[] roses, int[] lilies) {
         int len = roses.length;

         int best = -1;
                int elements = (int) Math.pow(2, len);
                for (int i = 0; i < elements; i++) {
                        String str = Integer.toBinaryString(i);
                        int value = str.length();
                        String pset = str;
                        for (int k = value; k < len; k++) {
                                pset = "0" + pset;
                        }
                        int rose = 0;
                        int lili = 0;
                        for (int j = 0; j < pset.length(); j++) {
                                if (pset.charAt(j) == '1') {
                                        rose +=roses[j];
                                        lili +=  lilies[j];
                                        if(Math.abs(rose-lili) <= 1 && (rose+lili > 0)) {
                                            int diff = bestDiff(rose+lili);
                                            if((diff < best && diff >=0) || best == -1) {
                                                best = diff;
                                                if(best == 0)
                                                    return 0;
                                            }
                                        }
                                }
                        }

                }

                return best;

      }
       
      }



// Powered by FileEdit


// Powered by FileEdit
