// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class Desertification {
      public int desertArea(String[] island, int T) {
          boolean hasDesert = false;
          int[][] is = new int[island.length][island[0].length()];
          for(int i=0; i<island.length; i++)
              for(int j=0; j<island[i].length(); j++) {
                  is[i][j]=0;
                  if(island[i].charAt(j) == 'D') {
                      hasDesert = true;
                      is[i][j]=1;
                  }

              }

          if(!hasDesert)
              return 0;


              int maxk = Math.min(100, T);
          for(int k=1; k<=maxk; k++) {
              for(int i=0; i<island.length; i++)
                  for(int j=0; j<island[i].length(); j++)
                      if(is[i][j] == 1) {
                          if(i>0 && is[i-1][j] == 0)
                              is[i-1][j]=2;
                          if(i<island.length-1 && is[i+1][j] == 0)
                              is[i+1][j]=2;
                          if(j>0 && is[i][j-1] == 0)
                              is[i][j-1]=2;
                          if(j<island[0].length()-1 && is[i][j+1] == 0)
                              is[i][j+1]=2;

                      }
              for(int i=0; i<island.length; i++)
                  for(int j=0; j<island[i].length(); j++)
                      if(is[i][j] == 2)
                          is[i][j]=1;
          }

          int count = 0;
           for(int i=0; i<island.length; i++)
                  for(int j=0; j<island[i].length(); j++)
                      if(is[i][j]==1)
                          count++;

          return count;
            	            
      }	 
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new Desertification().runTestCase(0);
		new Desertification().runTestCase(1);
		new Desertification().runTestCase(2);
		new Desertification().runTestCase(3);
		new Desertification().runTestCase(4);
		new Desertification().runTestCase(5);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(desertArea(new String[] {"FFF",  "FDF",  "FFF"}, 1), 5, 0); break;
			}
			case 1 : {
				checkOutput(desertArea(new String[] {"FFF",  "FDF",  "FFF"}, 2), 9, 1); break;
			}
			case 2 : {
				checkOutput(desertArea(new String[] {"FFFFF",  "FFDFF",  "FFFFD",  "FFFFF",  "FFFFF"}, 2), 17, 2); break;
			}
			case 3 : {
				checkOutput(desertArea(new String[] {"FFFFFF",  "FFFFFF",  "FFFFFF",  "FFFFFF"}, 1000000000), 0, 3); break;
			}
			case 4 : {
				checkOutput(desertArea(new String[] {"FFFFFDFFFF",  "FDFDFFFFFF",  "FFFFFFFFFD",  "FFFFFFFFFF",  "DDFFFFFFFF",   "FFFFFFFFFD",  "FFFFFFFFFF",  "FFFFFFFDFF",  "FFFFFFFDFF",  "FFFFDDFFFF"}, 3), 90, 4); break;
			}
			case 5 : {
				checkOutput(desertArea(new String[] {"FFFFFDFFFF",  "FDFDFFFFFF",  "FFFFFFFFFD",  "FFFFFFFFFF",  "DDFFFFFFFF",   "FFFFFFFFFD",  "FFFFFFFFFF",  "FFFFFFFDFF",  "FFFFFFFDFF",  "FFFFDDFFFF"}, 98765432), 100, 5); break;
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
