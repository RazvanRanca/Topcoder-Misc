// BEGIN CUT HERE
package topcoder;
// END CUT HERE

import java.util.*;

public class EnemyTowers {

    public int noTurns(int units, int hp, int attack, int towers) {
        int turns = 0;
        int rHp = hp;
        while (units > 0 && towers > 0) {
            int cAt = units;
            if (cAt >= hp) {
                towers -= cAt / hp;
                cAt %= hp;
            }
            if (cAt >= rHp) {
                cAt -= rHp;
                towers--;
                rHp = hp - cAt;
            } else {
                rHp -= cAt;
            }

            if (towers > 0) {
                units -= towers * attack;
            }

            turns++;
        }
        if (units < 1) {
            return Integer.MAX_VALUE;
        } else {
            return turns;
        }
    }




    public int attack(int myUnits, int hpT, int attackT, int numWodT, int numStoT) {
        int lo = 1;
        int hi = myUnits-1;
        while (lo < hi - 1) {
            int mid = lo + (hi - lo) / 2;
            int a = noTurns(mid,hpT,attackT,numWodT);
            int b = noTurns(myUnits - mid, hpT,attackT,numStoT);

            if (a < b) {
                hi = mid;
            } else {
                lo = mid;
            }
        }

        int val1 = Math.max(noTurns(lo,hpT,attackT,numWodT), noTurns(myUnits - lo, hpT,attackT,numStoT));
        int val2 = Math.max(noTurns(hi,hpT,attackT,numWodT), noTurns(myUnits - hi, hpT,attackT,numStoT));
        int val = Math.min(val1,val2);
        if(val == Integer.MAX_VALUE)
            return -1;
        return val;
    }

// BEGIN CUT HERE
    /** begin cut - don't modify this line*/
    public static void main(String[] a) {
        System.out.println(new EnemyTowers().attack(723344865, 31765, 21532, 38298, 2997));
        new EnemyTowers().runTestCase(0);
        new EnemyTowers().runTestCase(1);
        new EnemyTowers().runTestCase(2);
        new EnemyTowers().runTestCase(3);
    }

    public void runTestCase(int nbr) {
        switch (nbr) {
            case 0: {
                checkOutput(attack(7, 2, 1, 2, 3), 2, 0);
                break;
            }
            case 1: {
                checkOutput(attack(120, 10, 40000, 6, 6), 1, 1);
                break;
            }
            case 2: {
                checkOutput(attack(119, 10, 40000, 6, 6), -1, 2);
                break;
            }
            case 3: {
                checkOutput(attack(200, 50, 3, 10, 5), 6, 3);
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
