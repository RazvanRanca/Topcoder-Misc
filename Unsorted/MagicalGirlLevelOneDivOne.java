import java.util.*;

public class MagicalGirlLevelOneDivOne {
      public String isReachable(int[] jumpTypes, int x, int y) {
         if(x==0 && y==0)
             return "YES";
         double max = Math.sqrt(Math.abs(x-y) + 0.0);
         int[] pos = new int[2*((int) max + 3) + 2];
         int start = (int) max + 3;
         pos[start] = 1;
         Arrays.fill(pos, 0);
         for(int i=0; i < jumpTypes.length; i++)
             if(jumpTypes[i] <= max + 2) {
                pos[start + jumpTypes[i]] = 1;
                pos[start - jumpTypes[i]] = 1;
             }
         for(int j=1; j<(int)max + 3; j++)
             if(pos[j] == 0)
                for(int i=0; i < jumpTypes.length; i++)
                    if(Math.abs(start + j - jumpTypes[i]) <(int) max + 3) {

                        if(pos[start + j - jumpTypes[i]] == 1)
                            pos[start + j]=1;
                        if(pos[start - j + jumpTypes[i]] == 1)
                            pos[start - j]=1;
                    }
         int rez = x-y;
         for(int i=1; i<=max; i++)
             if(rez % i == 0) {
                  int n = i + 1;
                  if(n*n-1 == 0 || (n*y - x)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
                  n = rez/i + 1;
                  if(n*n-1 == 0 || (n*y - x)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
             }
         rez = y-x;
         for(int i=1; i<=max; i++)
             if(rez % i == 0) {
                  int n = i + 1;
                  if(n*n-1== 0 || (x - n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
                  n = rez/i + 1;
                  if(n*n-1== 0 || (x - n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
             }
         rez = x-y;
         for(int i=1; i<=max; i++)
             if(rez % i == 0) {
                  int n = i - 1;
                  if(n*n-1== 0 || (x + n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
                  n = rez/i - 1;
                  if(n*n-1== 0 || (x + n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
             }

         rez = y-x;
         for(int i=1; i<=max; i++)
             if(rez % i == 0) {
                  int n = i - 1;
                  if(n*n-1== 0 || (x + n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
                  n = rez/i - 1;
                  if(n*n-1== 0 || (x + n*y)%(n*n-1) == 0)
                        if (pos[start + n] == 1)
                            return "YES";
             }
         return "NO";
         }
      	 
       



// BEGIN CUT HERE

/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new MagicalGirlLevelOneDivOne().runTestCase(0);
		new MagicalGirlLevelOneDivOne().runTestCase(1);
		new MagicalGirlLevelOneDivOne().runTestCase(2);
		new MagicalGirlLevelOneDivOne().runTestCase(3);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(isReachable(new int[] {2}, 1, 1), "YES", 0); break;
			}
			case 1 : {
				checkOutput(isReachable(new int[] {3}, 5, 4), "NO", 1); break;
			}
			case 2 : {
				checkOutput(isReachable(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10}, 1000000000, -999999999), "YES", 2); break;
			}
			case 3 : {
				checkOutput(isReachable(new int[] {999999999}, 999999999, 0), "NO", 3); break;
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
