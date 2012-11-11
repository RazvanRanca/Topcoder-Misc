// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class PalindromeGame {

    public int getMaximum(String[] front, int[] back) {

        int max = 0;
        HashSet<Integer> considered = new HashSet<Integer>();
        int maxMiddle = 0;
        int len1 = front[0].length() / 2;
        int len2 = front[0].length() - len1;
        for (int i = 0; i < front.length; i++) {
            if (!considered.contains(i)) {
                ArrayList<Integer> eq = new ArrayList<Integer>();

                String halfRev = new StringBuilder(front[i].substring(len2)).reverse().toString();
                if (front[i].substring(0, len1).equals(halfRev)) {
                    considered.add(i);
                    for (int j = 0; j < front.length; j++)
                        if (front[i].equals(front[j])) {
                            considered.add(j);
                            eq.add(back[j]);
                        }

                    Collections.sort(eq);
                    int start = eq.size()-1;
                    if(eq.size()%2==1 && eq.get(eq.size()-1)>maxMiddle) {
                        maxMiddle = eq.get(eq.size()-1);
                        start--;
                    }
                    for(;start>=0;start--)
                        max+=eq.get(start);


                } else {
                    String curRev = new StringBuilder(front[i]).reverse().toString();
                    ArrayList<Integer> norm = new ArrayList<Integer>();
                    ArrayList<Integer> rev = new ArrayList<Integer>();
                    norm.add(back[i]);
                    considered.add(i);
                    for (int j = 0; j < front.length; j++) {
                        if (curRev.equals(front[j]) && j != i) {
                            considered.add(j);
                            rev.add(back[j]);
                        } else if (front[i].equals(front[j]) && j != i) {
                            considered.add(j);
                            norm.add(back[j]);
                        }
                    }
                    Collections.sort(rev);
                    Collections.sort(norm);
                    int rc = rev.size() - 1;
                    int nc = norm.size() - 1;
                    while (rc >= 0 && nc >= 0) {
                        max += norm.get(nc) + rev.get(rc);
                        rc--;
                        nc--;
                    }
                }


            }
        }



        max += maxMiddle;
        return max;
    }

// BEGIN CUT HERE 
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        new PalindromeGame().runTestCase(0);
        new PalindromeGame().runTestCase(1);
        new PalindromeGame().runTestCase(2);
    }

    public void runTestCase(int nbr) {
        switch (nbr) {
            case 0: {
                checkOutput(getMaximum(new String[]{"topcoder", "redcoder", "redocpot"}, new int[]{7, 5, 3}), 10, 0);
                break;
            }
            case 1: {
                checkOutput(getMaximum(new String[]{"rabbit"}, new int[]{1000000}), 0, 1);
                break;
            }
            case 2: {
                checkOutput(getMaximum(new String[]{"abc", "abc", "def", "cba", "fed"}, new int[]{24, 7, 63, 222, 190}), 499, 2);
                break;
            }
        }
    }

    final void checkOutput(int mine, int them, int nbr) {
        boolean success = (mine == them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }

    final void checkOutput(long mine, long them, int nbr) {
        boolean success = (mine == them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
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
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append(mine);
            out.append(", Expected: ");
            out.append(them);
        }
        System.out.println(out);
    }

    private static boolean doubleCompare(double expected, double result) {
        double MAX_DOUBLE_ERROR = 1E-9;
        if (Double.isNaN(expected)) {
            return Double.isNaN(result);
        } else if (Double.isInfinite(expected)) {
            if (expected > 0) {
                return result > 0 && Double.isInfinite(result);
            } else {
                return result < 0 && Double.isInfinite(result);
            }
        } else if (Double.isNaN(result) || Double.isInfinite(result)) {
            return false;
        } else if (Math.abs(result - expected) < MAX_DOUBLE_ERROR) {
            return true;
        } else {
            double min = Math.min(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            double max = Math.max(expected * (1.0 - MAX_DOUBLE_ERROR),
                    expected * (1.0 + MAX_DOUBLE_ERROR));
            return result > min && result < max;
        }
    }

    final void checkOutput(char mine, char them, int nbr) {
        boolean success = (mine == them);
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
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
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
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
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append("{");
            for (int x = 0; x < mine.length; x++) {
                out.append(mine[x]);
                if (x < mine.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for (int x = 0; x < them.length; x++) {
                out.append(them[x]);
                if (x < them.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
        }
        System.out.println(out);
    }

    final void checkOutput(char[] mine, char[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append("{");
            for (int x = 0; x < mine.length; x++) {
                out.append(mine[x]);
                if (x < mine.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for (int x = 0; x < them.length; x++) {
                out.append(them[x]);
                if (x < them.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
        }
        System.out.println(out);
    }

    final void checkOutput(double[] mine, double[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append("{");
            for (int x = 0; x < mine.length; x++) {
                out.append(mine[x]);
                if (x < mine.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for (int x = 0; x < them.length; x++) {
                out.append(them[x]);
                if (x < them.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
        }
        System.out.println(out);
    }

    final void checkOutput(int[] mine, int[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append("{");
            for (int x = 0; x < mine.length; x++) {
                out.append(mine[x]);
                if (x < mine.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for (int x = 0; x < them.length; x++) {
                out.append(them[x]);
                if (x < them.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
        }
        System.out.println(out);
    }

    final void checkOutput(String[] mine, String[] them, int nbr) {
        boolean success = (Arrays.equals(mine, them));
        StringBuffer out = new StringBuffer();
        out.append("Example ");
        out.append((nbr + 1));
        out.append(" - ");
        out.append(success ? "success" : "failure   ");
        if (!success) {
            out.append("Got: ");
            out.append("{");
            for (int x = 0; x < mine.length; x++) {
                out.append(mine[x]);
                if (x < mine.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
            out.append(", Expected: ");
            out.append("{");
            for (int x = 0; x < them.length; x++) {
                out.append(them[x]);
                if (x < them.length - 1) {
                    out.append(", ");
                }
            }
            out.append("}");
        }
        System.out.println(out);
    }
    /** end cut - don't modify this line*/
// END CUT HERE
}
