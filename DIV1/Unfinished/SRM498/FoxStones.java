// BEGIN CUT HERE
package topcoder;
// END CUT HERE

import java.awt.Point;
import java.util.*;

public class FoxStones {

      public HashSet<Point> getOneMarked(int mx, int my, int px, int py, int sizeX, int sizeY) {
          int dist = Math.max(Math.abs(px-mx), Math.abs(py-my));
          HashSet<Point> res = new HashSet<Point>();
        int cx = mx-dist;
        int cy1=my-dist;
        int cy2=my+dist;
        while(cx<=sizeX && cx <= mx+dist) {
            if(cx >= 1) {
                if(cy1 >= 1)
                    res.add(new Point(cx,cy1));
                if(cy2 <= sizeY)
                    res.add(new Point(cx,cy2));
            }
            cx++;
        }
        int cy =my-dist;
        int cx1=mx-dist;
        int cx2=mx+dist;
        while(cy<=sizeY && cy <= my+dist) {
            if(cy >= 1) {
                if(cx1 >= 1)
                    res.add(new Point(cx1,cy));
                if(cx2 <= sizeX)
                    res.add(new Point(cx2,cy));
            }
            cy++;
        }

        return res;
    }
      public int getCount(int N, int M, int[] sx, int[] sy) {
          HashSet<Point> visited = new HashSet<Point>();
          long res = 1;
          for(int i=1; i<=N; i++)
              for(int j=1; j<=M; j++) {
                  Point t = new Point(i,j);
                  if(!visited.contains(t)) {
                      HashSet<Point> cur = getOneMarked(sx[0],sy[0],i,j,N,M);

                      HashSet<Point> removed = new HashSet<Point>();

                      for(int k=1; k<sx.length; k++) {
                          int dist = Math.max(Math.abs(i-sx[k]), Math.abs(j-sy[k]));
                          for(Point p:cur) {

                              if(dist != Math.max(Math.abs(p.x-sx[k]), Math.abs(p.y-sy[k]))) {
                                  removed.add(p);

                              }

                          }
                          cur.removeAll(removed);
                          removed.clear();
                          if(cur.size() == 1)
                              break;
                      }
                      visited.addAll(cur);
                      res *=fac(cur.size());
                      res%=1000000009;
                  }
              }
          return (int) res;
      }

      public long fac(long x) {
          long res = 1;
          while(x>1) {
              res*=x;
              x--;
          }
          return res%1000000009;
      }

// BEGIN CUT HERE

/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new FoxStones().runTestCase(0);
		new FoxStones().runTestCase(1);
		new FoxStones().runTestCase(2);
		new FoxStones().runTestCase(3);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(getCount(6, 1, new int[] {3}, new int[] {1}), 4, 0); break;
			}
			case 1 : {
				checkOutput(getCount(2, 2, new int[] {2}, new int[] {1}), 6, 1); break;
			}
			case 2 : {
				checkOutput(getCount(3, 3, new int[] {1,2,3}, new int[] {1,2,3}), 8, 2); break;
			}
			case 3 : {
				checkOutput(getCount(12, 34, new int[] {5,6,7,8,9,10}, new int[] {11,12,13,14,15,16}), 410850247, 3); break;
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
