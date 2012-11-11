// BEGIN CUT HERE 
package topcoder;
// END CUT HERE

import java.util.*;

public class TheProduct {

    public long maxProduct(int[] numbers, int k, int maxDist) {
        int N = numbers.length;

        String s = Long.toBinaryString((1 << k) - 1;
        long maxVal = Long.MIN_VALUE;
        while ((s & 1 << N)==0) {
            String bits = Long.toBinaryString(s);
            for(int i=bits.length(); i<N; i++)
                bits = "0" + bits;
            
            boolean valid = true;
            long value = Long.MIN_VALUE;
            int lastBit = -1;
            for(int i=0; i<bits.length(); i++){
                if(bits.charAt(i) == '1') {
                    if(lastBit == -1)
                        lastBit = i;
                    else
                        if(i-lastBit > maxDist) {
                            valid = false;
                            break;
                        }
                    lastBit = i;
                    if(valid && value == Long.MIN_VALUE)
                        value = numbers[i];
                    else
                        if(valid)
                            value *=numbers[i];
                }
            }
            if(valid && value>maxVal)
                maxVal = value;

            long lo = s & ~(s - 1);       // lowest one bit
            long lz = (s + lo) & ~s;      // lowest zero bit above lo
            s |= lz;                     // add lz to the set
            s &= ~(lz - 1);              // reset bits below lz
            s |= (lz / lo / 2) - 1;      // put back right number of bits at end
        }
        return maxVal;
    }

// BEGIN CUT HERE 
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        System.out.println(new TheProduct().maxProduct(new int[] {-14, -37, 26, -11, 4, 50, 9, -16, -50, -25, 15, 13, 19, 45, 5, -35, 30, 38, -15, 29, 34, 17, -20, -36, 7, -32, 26, 8, -22, 29, 18, -42, -29, 24, 35}, 1, 11));
        new TheProduct().runTestCase(0);
        new TheProduct().runTestCase(1);
        new TheProduct().runTestCase(2);
        new TheProduct().runTestCase(3);
    }

    public void runTestCase(int nbr) {
        switch (nbr) {
            case 0: {
                checkOutput(maxProduct(new int[]{7, 4, 7}, 2, 1), 28, 0);
                break;
            }
            case 1: {
                checkOutput(maxProduct(new int[]{7, 4, 7}, 2, 50), 49, 1);
                break;
            }
            case 2: {
                checkOutput(maxProduct(new int[]{-3, -5, -8, -9, -1, -2}, 3, 3), -10, 2);
                break;
            }
            case 3: {
                checkOutput(maxProduct(new int[]{3, 0, -2, 10, 0, 0, 3, -8, 0, 2}, 2, 2), 0, 3);
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
