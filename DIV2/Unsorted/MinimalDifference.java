import java.util.*;
public class MinimalDifference {

        public int digitSum(int x){
            int sum  = 0;
            while(x != 0) {
                sum += x%10;
                x/=10;
            }
            return sum;
        }
	public int findNumber(int A, int B, int C) {
            int res = 0;
            int diff = Integer.MAX_VALUE;
            int digitC = digitSum(C);
		for(int candidate = A; candidate <= B; candidate++) {
                    int cdiff = Math.abs(digitSum(candidate) - digitC);
                    if(cdiff <diff || diff == Integer.MAX_VALUE) {
                        diff = cdiff;
                        res = candidate;
                    }
                }
            return res;

	}
}


// Powered by FileEdit
// Powered by CodeProcessor
