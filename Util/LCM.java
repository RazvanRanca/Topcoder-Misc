   public  int lcm(int a, int b) {
       int p = Math.abs(a*b);

        while(b!=0) {
            int c = a;
            a = b;
            b = c%b;
        }
        return (int) p/a;
    }

