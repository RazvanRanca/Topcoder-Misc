// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class Badgers {


    public int makeProgram(String[] output) {
        int modul = 1000000007;
        int res = 1;
        ArrayList<Character> row = new ArrayList<Character>();
        for(int i=0; i<output.length-1; i++) {
            row.clear();
            for(int j=0; j<output[0].length(); j++)
                row.add('n');
            for(int j=0; j<output[0].length()-1; j++) {
                char f = output[i].charAt(j);
                char s = output[i].charAt(j+1);
                if(f == 'B' && s == 'B') {
                    char aux = row.get(j);
                    row.set(j, row.get(j+1));
                    row.set(j+1, aux);
                }
                if(f == 'B' && s == 'W') {
                    row.set(j, 'B');
                    row.set(j+1, 'B');
                }

                if(f=='W' && s == 'B') {
                    row.set(j, 'W');
                    row.set(j+1, 'W');
                }
            }
            int c = 0;
            for(int j=0; j<output[0].length(); j++) {
                if(row.get(j) != 'n') {
                    if(row.get(j) == output[i+1].charAt(j))
                        c++;
                    else {
                        return 0;
                    }
                }
            }

            for(int j=0; j<c; j++) {
                res *=2;
                res %= modul;
            }
        }
        return res;
    }

    public ArrayList<Integer> fballs = new ArrayList<Integer>();
    public ArrayList<Integer> balls = new ArrayList<Integer>();
    public int start = 0;
    public int end = 0;
    public int reversed = -1;
    public int inverted = -1;

    public int countBlack(String ballSequence, int repetitions) {
        for(int i=0; i<ballSequence.length(); i++) {
            if(ballSequence.charAt(i) == 'B')
                fballs.add(1);
            else
               fballs.add(0);

        }

        for (int i=0; i<repetitions; i++) {
            balls.addAll(fballs);
        }
        end= balls.size()-1;
        int c = 0;
        while (start <= end) {
            if(reversed == -1) {
                if((inverted == -1 && balls.get(start)==1) || (inverted == 1 && balls.get(start) == 0)) {
                    start++;
                    c++;
                    inverted *=-1;
                }
                else {
                    start++;
                    reversed *=-1;
                }
            }
            else {
                if((inverted == -1 && balls.get(end)==1) || (inverted == 1 && balls.get(end) == 0)) {                    start++;
                    end--;
                    c++;
                    inverted *=-1;
                }
                else {
                    end--;
                    reversed *=-1;
                }
            }
        }
        return c;
    }

    public boolean canFeed(int no, int[] hunger, int[] greed, int totalFood) {
        ArrayList<Integer> values = new ArrayList<Integer>();
        for(int i=0; i<hunger.length; i++) {
            values.add(hunger[i] + greed[i]*(no-1));
        }
        Collections.sort(values);
        int total = 0;
        for(int i=0; i<no; i++)
            total+=values.get(i);
        return (total <= totalFood);
    }
      public int feedMost(int[] hunger, int[] greed, int totalFood) {
        int start = 0;
        int finish = hunger.length;
        while(start < finish) {
            int cur = start + (finish-start+1)/2;
            if(canFeed(cur, hunger, greed, totalFood))
                start = cur;
            else
                finish = cur-1;
        }

        return start;
      }	 
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		//System.out.println(new Badgers().countBlack("BWWBBWBBWWWBBWWBBBWW", 5000));
            System.out.println(new Badgers().makeProgram(new String[] {"WB","BB" }));
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(feedMost(new int[] {1,2,3}, new int[] {2,2,1}, 7), 2, 0); break;
			}
			case 1 : {
				checkOutput(feedMost(new int[] {5,2,1,5}, new int[] {0,2,4,1}, 19), 3, 1); break;
			}
			case 2 : {
				checkOutput(feedMost(new int[] {1,1,1,1,1}, new int[] {1000,1000,1000,1000,1000}, 10), 1, 2); break;
			}
			case 3 : {
				checkOutput(feedMost(new int[] {1,2,3,4,5,6,7,8,9,10}, new int[] {10,9,8,7,6,5,4,3,2,1}, 100), 5, 3); break;
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
