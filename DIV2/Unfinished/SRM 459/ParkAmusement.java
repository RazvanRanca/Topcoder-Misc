// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class ParkAmusement {
      public double getProbability(String[] landings, int startLanding, int K) {
        ArrayList<Integer> from = new ArrayList<Integer>();
        ArrayList<Integer> to = new ArrayList<Integer>();
        ArrayList<Integer> finals = new ArrayList<Integer>();
        ArrayList<Integer> queue = new ArrayList<Integer>();
        ArrayList<Integer> considered = new ArrayList<Integer>();
        HashMap<Integer,Integer> noExits = new HashMap<Integer,Integer>();
        HashMap<Integer,Integer> level = new HashMap<Integer,Integer>();
        HashMap<Integer,Double> prob = new HashMap<Integer,Double>();
        HashMap<Integer,Double> finalProb = new HashMap<Integer,Double>();

        for(int i=0; i<landings.length; i++) {
            int count = 0;
            if(landings[i].charAt(i) == 'E')
                finals.add(i);
            else{
                for(int j=0; j<landings[i].length(); j++)
                    if(landings[i].charAt(j) == '1') {
                        count++;
                        from.add(i);
                        to.add(j);
                    }
           }
           noExits.put(i,count);
        }

        for(int k=0; k<finals.size(); k++) {
            queue.clear();
            level.clear();
            prob.clear();
            int index = 0;
            int start = finals.get(k);
            queue.add(start);
            level.put(start,0);
            prob.put(start, 1.0);
            while(index < queue.size() && level.get(queue.get(index)) < K) {
                int cur = queue.get(index);
                for(int i=0; i<to.size(); i++) {
                    if(to.get(i) == cur) {
                        int nw = from.get(i);
                        queue.add(nw);
                        level.put(nw, level.get(cur) + 1);
                        prob.put(nw, (1.0/noExits.get(nw)) * prob.get(cur));

                        if(level.get(nw) == K) {
                            if(!considered.contains(nw))
                                considered.add(nw);
                            if(finalProb.containsKey(nw))
                                finalProb.put(nw, prob.get(nw) + finalProb.get(nw));
                            else
                                finalProb.put(nw, prob.get(nw));

                        }
                    }
                }

                index++;
            }
            
        }
        double sum = 0;
        for(int i=0; i<considered.size(); i++)
            sum += finalProb.get(considered.get(i));
        if(considered.contains(startLanding))
            return finalProb.get(startLanding)/sum;
        else
            return 0;
      }	 
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
                System.out.println(new ParkAmusement().getProbability(new String[] {"001010001001011", "0P0000000000000", "010110000100011", "000P00000000000", "010100001101011", "111110101101011", "111010001101011", "111111101101101", "00000000E000000", "000000000E00000", "101111111101111", "000100000100011", "101111101101011", "010000001100000", "00000000000000E"}, 12, 3));
		new ParkAmusement().runTestCase(0);
		new ParkAmusement().runTestCase(1);
		new ParkAmusement().runTestCase(2);
		new ParkAmusement().runTestCase(3);
		new ParkAmusement().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(getProbability(new String[] {"E000",  "1000",  "1000",  "1000"}, 1, 1), 0.3333333333333333, 0); break;
			}
			case 1 : {
				checkOutput(getProbability(new String[] {"E000",  "1000",  "1001",  "000P"}, 1, 1), 0.6666666666666666, 1); break;
			}
			case 2 : {
				checkOutput(getProbability(new String[] {"01000100",  "00111000",  "00001010",  "000E0000",  "0000E000",  "00000P00",  "000000P0",  "01000000"}, 1, 2), 0.14285714285714288, 2); break;
			}
			case 3 : {
				checkOutput(getProbability(new String[] {"0100",  "0010",  "0001",  "000E"}, 0, 2), 0.0, 3); break;
			}
			case 4 : {
				checkOutput(getProbability(new String[] {"E00",  "0E0",  "010"}, 0, 1), 0.0, 4); break;
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
