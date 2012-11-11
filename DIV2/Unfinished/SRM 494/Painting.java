// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class Painting {

       public boolean brushWorksHere(String[] picture, int size, int x, int y) {
           for(int i=x; i<x+size; i++)
               for(int j=y; j<y+size; j++)
                   if(picture[i].charAt(j) != 'B')
                       return false;

           return true;
       }

       public boolean brushWorks(String[] picture, int size, int blacks) {
           HashSet<Integer> ps = new HashSet<Integer>();
           int ss = picture[0].length();
           for(int i=0; i<=picture.length-size; i++)
               for(int j=0; j<=picture[0].length()-size; j++)
                   if(brushWorksHere(picture,size,i,j)) {
                       for(int i1=i; i1<i+size; i1++)
                            for(int j1=j; j1<j+size; j1++)
                                ps.add(i1*ss + j1);
                   }
           if(ps.size() == blacks)
                       return true;
           return false;
       }
       
      public int largestBrush(String[] picture) {
          int blacks = 0;
          for(int i=0; i<picture.length; i++)
               for(int j=0; j<picture[0].length(); j++)
                   if(picture[i].charAt(j) == 'B')
                       blacks++;
         int hi = Math.min(picture.length,picture[0].length());
         int lo = 1;
         while(lo<hi) {
             int cur = lo + (hi-lo+1)/2;
             if(brushWorks(picture,cur,blacks))
                 lo = cur;
             else
                 hi = cur-1;
         }
         return lo;
      }	 
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
                System.out.println(new Painting().largestBrush(new String[] {"WBB", "BBW", "BBB"}));
		new Painting().runTestCase(0);
		new Painting().runTestCase(1);
		new Painting().runTestCase(2);
		new Painting().runTestCase(3);
		new Painting().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(largestBrush(new String[] {"BBBB",  "BBBB",  "BBBB",  "BBBB"}), 4, 0); break;
			}
			case 1 : {
				checkOutput(largestBrush(new String[] {"BBBB",  "BWWB",  "BWWB",  "BBBB"}), 1, 1); break;
			}
			case 2 : {
				checkOutput(largestBrush(new String[] {"WBBBBB",  "BBBBBB",  "BBBBBB",  "BBBBBB"} ), 3, 2); break;
			}
			case 3 : {
				checkOutput(largestBrush(new String[] {"BBBB",  "BBBB",  "WBBB",  "BBBB",  "BBBB",  "BBBB"} ), 2, 3); break;
			}
			case 4 : {
				checkOutput(largestBrush(new String[] {"WBBBBBWWWWWWWWW",  "WBBBBBBWWWWWWWW",  "WBBBBBBBBBBBWWW",  "WBBBBBBBBBBBWWW",  "BBBBBBBBBBBBBBB",  "BBBBBBBBBBBBBBB",  "BBBBBBBBBBBBBBB",  "BBBBBBBBWWBBBBB",  "BBBBBBBBWBBBBBB",  "WBBBBBBBWBBBBBW",  "BBBBBBBWWBBBBBW",  "BBBBBBBWWBBBBBW",  "BBBBBBWWWBBBBBW",  "BBBBBWWWWWWWWWW",  "BBBBBWWWWWWWWWW"} ), 5, 4); break;
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
