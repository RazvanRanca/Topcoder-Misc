// BEGIN CUT HERE
package topcoder;
// END CUT HERE

import java.util.*;

public class TheTriangleBothDivs {
      public String fix(String time) {
        ArrayList<Integer> fHour = new ArrayList<Integer>();
        ArrayList<Integer> sHour = new ArrayList<Integer>();
        ArrayList<Integer> fMin = new ArrayList<Integer>();
        ArrayList<Integer> sMin = new ArrayList<Integer>();
        ArrayList<Integer> diff = new ArrayList<Integer>();
        if(time.charAt(0) == '?')
            for(int i=0; i<3; i++)
                 fHour.add(i);
        else
            fHour.add(Integer.parseInt(time.charAt(0) + ""));

        if(time.charAt(1) == '?')
            for(int i=0; i<10; i++)
                 sHour.add(i);
        else
            sHour.add(Integer.parseInt(time.charAt(1) + ""));

        if(time.charAt(3) == '?')
            for(int i=0; i<6; i++)
                 fMin.add(i);
        else
            fMin.add(Integer.parseInt(time.charAt(3) + ""));

        if(time.charAt(4) == '?')
            for(int i=0; i<10; i++)
                 sMin.add(i);
        else
            sMin.add(Integer.parseInt(time.charAt(4) + ""));

        if(time.charAt(10) == '?') {
            if(time.charAt(9) == '+')
                for(int i=0; i<10; i++)
                     diff.add(i);
            else {
                if(time.charAt(9) == '-')
                     for(int i=-9; i<0; i++)
                          diff.add(i);
                else
                    for(int i=-9; i<10; i++)
                          diff.add(i);
            }
        }
        else {
            if(time.charAt(9) == '+')
                diff.add(Integer.parseInt(time.charAt(10) + ""));
            else {
                if(time.charAt(9) == '-')
                        diff.add(-1*Integer.parseInt(time.charAt(10) + ""));
                else {
                     diff.add(-1*Integer.parseInt(time.charAt(10) + ""));
                     diff.add(Integer.parseInt(time.charAt(10) + ""));
                }
            }
        }

        String mn = "99999";
        for(int h1:fHour)
            for(int h2:sHour){
                int hour = h1*10 + h2;
                if(hour<24)
                    for(int m1:fMin)
                        for(int m2:sMin) {
                            int min = m1*10+m2;
                            String cur = "";
                            for(int d:diff) {
                                int inithour = hour;
                                hour -= d;
                                if(hour<0)
                                    hour+=24;
                                if(hour == 24)
                                    hour = 0;
                                hour %=24;


                                String chour = Integer.toString(hour);
                                if(chour.length() == 1)
                                    chour = "0" + chour;

                                String cmin = Integer.toString(min);
                                if(cmin.length() == 1)
                                    cmin = "0" + cmin;

                                cur = chour + ":" + cmin;
                                if(mn.compareTo(cur) > 0)
                                    mn = cur;

                                hour = inithour;

                            }
                        }
            }

          return mn;
      }

// BEGIN CUT HERE

/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new TheTriangleBothDivs().runTestCase(0);
		new TheTriangleBothDivs().runTestCase(1);
		new TheTriangleBothDivs().runTestCase(2);
		new TheTriangleBothDivs().runTestCase(3);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(fix("17:45 GMT-4"), "21:45", 0); break;
			}
			case 1 : {
				checkOutput(fix("16:?? GMT??"), "00:00", 1); break;
			}
			case 2 : {
				checkOutput(fix("?1:34 GMT-9"), "06:34", 2); break;
			}
			case 3 : {
				checkOutput(fix("??:?? GMT??"), "00:00", 3); break;
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
