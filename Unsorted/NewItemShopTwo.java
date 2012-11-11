// BEGIN CUT HERE
package topcoder;
// END CUT HERE

import java.awt.Point;
import java.util.*;

public class NewItemShopTwo {
      public double getMaximum(String[] customers) {
         String[] vals1 = customers[0].split(" ");
         String[] vals2 = customers[1].split(" ");
         TreeMap<Integer,Cust> c1 = new TreeMap<Integer,Cust>();
         TreeMap<Integer,Cust> c2 = new TreeMap<Integer,Cust>();
         double maxVal1 = 0;
         double maxVal2 = 0;
         for(String s : vals1) {
             String[] par = s.split(",");
             int time = Integer.parseInt(par[0]);
             double cost = Double.parseDouble(par[1]);
             double prob = Double.parseDouble(par[2]);
             double value = cost*prob;
             if(value > maxVal1)
                 maxVal1 = value;
             c1.put(time, new Cust(cost, prob));

         }

         for(String s : vals2) {
             String[] par = s.split(",");
             int time = Integer.parseInt(par[0]);
             double cost = Double.parseDouble(par[1]);
             double prob = Double.parseDouble(par[2]);
             double value = cost*prob;
             if(value > maxVal2)
                 maxVal2 = value;
             c2.put(time, new Cust(cost, prob));

         }
         return calcProb (c1, c2, maxVal1, maxVal2);
      }

      public double calcProb(TreeMap<Integer,Cust> c1, TreeMap<Integer,Cust> c2, double maxValue1, double maxValue2) {
          if(c1.size()!= 0 && (c2.size() == 0 || c1.firstKey() < c2.firstKey())) {
              Cust cur = c1.get(c1.firstKey());
              c1.remove(c1.firstKey());
              double pCome = cur.prob/100d;
              double nCome = (1-pCome);
              maxValue1 = 0;
                  for(int k : c1.keySet())
                      if(c1.get(k).value > maxValue1)
                          maxValue1 = c1.get(k).value;
              if(cur.value > maxValue2) {
                  TreeMap<Integer,Cust> c11 = new TreeMap<Integer,Cust>();
                  c11.putAll(c1);
                  TreeMap<Integer,Cust> c22 = new TreeMap<Integer,Cust>();
                  c22.putAll(c2);
                  double one =  cur.cost;
                  double two = calcProb(c11,c22, maxValue1, maxValue2);
                  return pCome * one + nCome*two;
              }
              else {
                  TreeMap<Integer,Cust> p1 = new TreeMap<Integer,Cust>();
                  TreeMap<Integer,Cust> c22 = new TreeMap<Integer,Cust>();
                  c22.putAll(c2);
                  TreeMap<Integer,Cust> c11 = new TreeMap<Integer,Cust>();
                  c11.putAll(c1);
                  TreeMap<Integer,Cust> c21 = new TreeMap<Integer,Cust>();
                  c21.putAll(c2);
                  double one =calcProb(p1,c21,0, maxValue2);
                  double two = calcProb(c11,c22,maxValue1, maxValue2);
                  return pCome * one + nCome*two;
              }
          }
          if(c2.size()!= 0) {
              Cust cur = c2.get(c2.firstKey());
              c2.remove(c2.firstKey());
              double pCome = cur.prob/100d;
              double nCome = (1-pCome);
              maxValue2 = 0;
                  for(int k : c2.keySet())
                      if(c2.get(k).value > maxValue2)
                          maxValue2 = c2.get(k).value;
              if(cur.value > maxValue1) {
                  TreeMap<Integer,Cust> c11 = new TreeMap<Integer,Cust>();
                  c11.putAll(c1);
                  TreeMap<Integer,Cust> c22 = new TreeMap<Integer,Cust>();
                  c22.putAll(c2);
                  double one = cur.cost;
                  double two = calcProb(c11,c22, maxValue1, maxValue2);
                  return pCome * one + nCome*two;
              }
              else {
                  TreeMap<Integer,Cust> p2 = new TreeMap<Integer,Cust>();
                  TreeMap<Integer,Cust> c12 = new TreeMap<Integer,Cust>();
                  c12.putAll(c1);
                  TreeMap<Integer,Cust> c11 = new TreeMap<Integer,Cust>();
                  c11.putAll(c1);
                  TreeMap<Integer,Cust> c21 = new TreeMap<Integer,Cust>();
                  c21.putAll(c2);
                  double one =calcProb(c11,p2,maxValue1, 0);
                  double two =calcProb(c12,c21,maxValue1, maxValue2);
                  return pCome * one + nCome*two;
              }
          }
          return 0d;
      }

      public class Cust {

          public double value;
          public double prob;
          public double cost;

          public Cust(double c, double p){
              cost = c;
              prob =  p;
              value = c*p;
          }

      }

// BEGIN CUT HERE

/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new NewItemShopTwo().runTestCase(0);
		new NewItemShopTwo().runTestCase(1);
		new NewItemShopTwo().runTestCase(2);
		new NewItemShopTwo().runTestCase(3);
		new NewItemShopTwo().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(getMaximum(new String[] { "8,1,80 16,100,11", "12,10,100" }), 19.0, 0); break;
			}
			case 1 : {
				checkOutput(getMaximum(new String[] { "8,1,80 16,100,11", "12,10,90 13,30,5" }), 19.4, 1); break;
			}
			case 2 : {
				checkOutput(getMaximum(new String[] { "0,90,25 2,90,25 4,90,25 6,90,25", "7,100,80" }), 90.0, 2); break;
			}
			case 3 : {
				checkOutput(getMaximum(new String[] { "0,90,25 2,90,25 4,90,25 6,90,25", "7,100,95" }), 95.0, 3); break;
			}
			case 4 : {
				checkOutput(getMaximum(new String[] { "0,3,1 2,4,1 4,5,9 6,2,6 8,5,3 10,5,8 12,9,7 14,9,3",    "1,2,3 3,8,4 5,6,2 7,6,4 9,3,3 11,8,3 13,2,7 15,9,5" } ), 3.0692999999999997, 4); break;
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
