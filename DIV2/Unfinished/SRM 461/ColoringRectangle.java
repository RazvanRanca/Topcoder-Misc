// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class ColoringRectangle {
        ArrayList<Double> redDist = new ArrayList<Double>();
        ArrayList<Double> blueDist = new ArrayList<Double>();

       public boolean validColouring(int x, int dist){
           int indRed = redDist.size()-1;
           int indBlue = blueDist.size()-1;
           double cDist = (double) dist;
           for(int i=0; i<x; i++) {
               if(i%2==0) {
                   if(indRed < 0)
                       break;
                   else{
                       cDist -= redDist.get(indRed);
                       indRed--;
                   }
               }
               if(i%2==1) {
                   if(indBlue < 0)
                       break;
                   else{
                       cDist -= blueDist.get(indBlue);
                       indBlue--;
                   }
               }
               if(cDist <= 0)
                   return true;
           }
           indRed = redDist.size()-1;
           indBlue = blueDist.size()-1;
           cDist = (double) dist;
           for(int i=0; i<x; i++) {
               if(i%2==1) {
                   if(indRed < 0)
                       break;
                   else{
                       cDist -= redDist.get(indRed);
                       indRed--;
                   }
               }
               if(i%2==0) {
                   if(indBlue < 0)
                       break;
                   else{
                       cDist -= blueDist.get(indBlue);
                       indBlue--;
                   }
               }
               if(cDist <= 0)
                   return true;
           }
           return false;
       }
      public int chooseDisks(int width, int height, int[] red, int[] blue) {
          double hh = height/2.0;
          hh = hh*hh;
           for(int i=0; i<red.length; i++) {
               if(red[i] >= height) {
                   double rad = red[i]/2.0;
                   redDist.add(2*Math.sqrt(rad*rad - hh));
               }
           }

          for(int i=0; i<blue.length; i++) {
               if(blue[i] >= height) {
                   double rad = blue[i]/2.0;
                   blueDist.add(2*Math.sqrt(rad*rad - hh));
               }
           }
          Collections.sort(redDist);
          Collections.sort(blueDist);

          int low = 1;
          int high = red.length + blue.length;
          while(low<high) {
              int mid = low + (high-low)/2;
              if(validColouring(mid, width))
                  high = mid;
              else
                  low = mid+1;
          }
          if(validColouring(low,width))
              return low;
          else
              return -1;
      }	 
       
// BEGIN CUT HERE 
     
/** begin cut - don't modify this line*/
	public static void main(String[] a) {
		new ColoringRectangle().runTestCase(0);
		new ColoringRectangle().runTestCase(1);
		new ColoringRectangle().runTestCase(2);
		new ColoringRectangle().runTestCase(3);
		new ColoringRectangle().runTestCase(4);
	}

	public void runTestCase(int nbr) {
		switch(nbr) {
			case 0 : {
				checkOutput(chooseDisks(11, 3, new int[] {5,5}, new int[] {2,5}), 3, 0); break;
			}
			case 1 : {
				checkOutput(chooseDisks(30, 5, new int[] {4,10,7,8,10}, new int[] {5,6,11,7,5}), 4, 1); break;
			}
			case 2 : {
				checkOutput(chooseDisks(16, 4, new int[] {6,5,7}, new int[] {5}), -1, 2); break;
			}
			case 3 : {
				checkOutput(chooseDisks(4, 4, new int[] {5}, new int[] {6}), 1, 3); break;
			}
			case 4 : {
				checkOutput(chooseDisks(6, 2, new int[] {6,6}, new int[] {2}), 3, 4); break;
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
