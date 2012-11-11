// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class FriendTour {

      public double tourProbability(String[] friends, int K) {
         int visited = (int) Math.pow(2, friends.length) -1;
         int goal = (int) Math.pow(2, friends.length-1);

         double[][] A = new double[visited+1][friends.length];
         ArrayList<ArrayList<Integer>> frnd = new ArrayList<ArrayList<Integer>>();

         for(int i=0; i<friends.length; i++) {
             A[visited][i] = 1;
             frnd.add(new ArrayList<Integer>());
             for(String s: friends[i].split(" "))
                 frnd.get(i).add(Integer.parseInt(s)-1);
         }

         visited--;
         while(visited >= goal) {
             String bits = Integer.toBinaryString(visited);
             bits = (bits.substring(bits.length()-friends.length));
             for(int i=0; i<friends.length;i++) {
                 if(bits.charAt(i) == '0')
                     A[visited][i] = 0;
                 else {
                     double factor = 1;
                     ArrayList<Integer> cur = frnd.get(i);
                     

                     ArrayList<Double> values = new ArrayList<Double>();
                     for(int neighbour:cur) {
                         if(bits.charAt(neighbour) == '0')
                             values.add(A[visited^(int)Math.pow(2, friends.length - neighbour-1)][neighbour]);
                     }
                     Collections.sort(values);
                     int rank = 1;
                     double sum = 0;
                     if(values.size() > 0) {
                         if(K>cur.size())
                             sum += values.get(values.size()-1);
                         else {
                             for(int q=values.size()-1; q>=0; q--) {
                                 factor = choose(cur.size()-rank,K-1)/choose(cur.size(),K);

                                 sum += values.get(q)*factor;
                                 rank++;
                             }
                             
                         }
                     }
                     A[visited][i] = sum;
                 }
             }
             visited--;
         }

         return A[goal][0];
      }

      public static double choose(int n, int k) {
          if(n==k || k==0)
              return 1.0;
          if(n<k || k<0)
              return 0.0;
          double a = n;
          int c = 1;
          while(c < k) {
              a*=(n-c);
              c++;
          }
          double b = k;
          
          c = k-1;

          while(c>1) {
              b*=c;
              c--;
          }
          return a/b;
      }
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
            //System.out.println(new FriendTour().tourProbability(new String[] {"2 3", "1 4 3", "2 1 4", "2 3"}, 1));
		//new FriendTour().runTestCase(0);
		//new FriendTour().runTestCase(1);
		new FriendTour().runTestCase(2);
		new FriendTour().runTestCase(3);
		new FriendTour().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(tourProbability(new String[] {"2 3 4",  "1 3 4",  "1 2 4",  "1 2 3"}, 1), 0.2222222222222222, 0); break;
			}
			case 1 : {
				checkOutput(tourProbability(new String[] {"2 3 4",  "1 3 4",  "1 2 4",  "1 2 3"}, 2), 0.6666666666666666, 1); break;
			}
			case 2 : {
				checkOutput(tourProbability(new String[] {"3 2 4",  "3 5 1",  "5 2 1 4",  "3 1 5",  "3 2 4"}, 2), 0.3333333333333333, 2); break;
			}
			case 3 : {
				checkOutput(tourProbability(new String[] {"3 2 4",  "3 5 1",  "5 2 1 4",  "3 1 5 6",  "3 2 4",  "4"} , 2), 0.3055555555555556, 3); break;
			}
			case 4 : {
				checkOutput(tourProbability(new String[] {"6 5 4 2",  "1 6 3 5",  "5 4 2",  "3 1 5",  "2 4 3 1 6",  "1 2 5"}, 3), 0.73125, 4); break;
			}
		}
	}
	final void checkOutput(int mine, int them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(long mine, long them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	final void checkOutput(double mine, double them, int nbr) {
		boolean success = doubleCompare(mine, them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append(mine);
			out.append(", Expected: ");
			out.append(them);
		}
		System.out.println(out);
	}
	private static boolean doubleCompare(double expected, double result){
		double MAX_DOUBLE_ERROR = 1E-9;
		if(Double.isNaN(expected)){
			return Double.isNaN(result);
		}else if(Double.isInfinite(expected)){
			if(expected > 0){
				return result > 0 && Double.isInfinite(result);
			}else{
				return result < 0 && Double.isInfinite(result);
			}
		}else if(Double.isNaN(result) || Double.isInfinite(result)){
			return false;
		}else if(Math.abs(result - expected) < MAX_DOUBLE_ERROR){
			return true;
		}else{
			double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
				expected * (1.0 + MAX_DOUBLE_ERROR));
			double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
					expected * (1.0 + MAX_DOUBLE_ERROR));
			return result > min && result < max;
		}
	}
	final void checkOutput(char mine, char them, int nbr) {
		boolean success = (mine==them);
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("'");
			out.append(mine);
			out.append("'");
			out.append(", Expected: ");
			out.append("'");
			out.append(them);
			out.append("'");
		}
		System.out.println(out);
	}
	final void checkOutput(String mine, String them, int nbr) {
		boolean success = (mine.equals(them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("\"");
			out.append(mine);
			out.append("\"");
			out.append(", Expected: ");
			out.append("\"");
			out.append(them);
			out.append("\"");
		}
		System.out.println(out);
	}
	final void checkOutput(long[] mine, long[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(char[] mine, char[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(double[] mine, double[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(int[] mine, int[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}
	final void checkOutput(String[] mine, String[] them, int nbr) {
		boolean success = (Arrays.equals(mine, them));
		StringBuffer out = new StringBuffer();
		out.append("Example ");
		out.append((nbr+1));
		out.append(" - ");
		out.append(success ? "success" : "failure   ");
		if(!success) {
			out.append("Got: ");
			out.append("{");
			for(int x=0;x<mine.length;x++) {
				out.append(mine[x]);
				if(x<mine.length-1) out.append(", ");
			}
			out.append("}");
			out.append(", Expected: ");
			out.append("{");
			for(int x=0;x<them.length;x++) {
				out.append(them[x]);
				if(x<them.length-1) out.append(", ");
			}
			out.append("}");
		}
		System.out.println(out);
	}

/** end cut - don't modify this line*/



// END CUT HERE

}
