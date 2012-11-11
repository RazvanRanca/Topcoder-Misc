//binary search can be used if and only if for all x in S, p(x) implies p(y) for all y > x
//finds first x for which p(x) is true
public int binary_search(int lo, int hi) {
   while (lo < hi) {
      int mid = lo + (hi-lo)/2;
      if (p(mid) == true)
         hi = mid;
      else
         lo = mid+1;
   }

   if (p(lo) == false)
      complain                // p(x) is false for all x in S!

   return lo;         // lo is the least x for which p(x) is true
}

// finds last x for which p(x) is false
public int binary_search(int lo, int hi) {
   while (lo < hi) {
      int mid = lo + (hi-lo+1)/2;
      if (p(mid) == true)
         hi = mid-1;
      else
         lo = mid;
   }

   if (p(lo) == false)
      complain                // p(x) is false for all x in S!

   return lo;         // lo is the least x for which p(x) is true
}

