public static <T> Set<Set<T>> powerSet(Set<T> originalSet) {
    Set<Set<T>> sets = new HashSet<Set<T>>();
    if (originalSet.isEmpty()) {
        sets.add(new HashSet<T>());
        return sets;
    }
    List<T> list = new ArrayList<T>(originalSet);
    T head = list.get(0);
    Set<T> rest = new HashSet<T>(list.subList(1, list.size()));
    for (Set<T> set : powerSet(rest)) {
        Set<T> newSet = new HashSet<T>();
        newSet.add(head);
        newSet.addAll(set);
        sets.add(newSet);
        sets.add(set);
    }
    return sets;
}

public class PowerSet {
        public static void main(String[] args) {
                String st[] = { "x", "y", "z" };
                LinkedHashSet hashSet = new LinkedHashSet();
                int len = st.length;
                int elements = (int) Math.pow(2, len);
                for (int i = 0; i < elements; i++) {
                        String str = Integer.toBinaryString(i);
                        int value = str.length();
                        String pset = str;
                        for (int k = value; k < len; k++) {
                                pset = "0" + pset;
                        }
                        LinkedHashSet set = new LinkedHashSet();
                        for (int j = 0; j < pset.length(); j++) {
                                if (pset.charAt(j) == '1')
                                        set.add(st[j]);
                        }
                        hashSet.add(set);
                }
                System.out.println(hashSet.toString().replace("[", "{").replace("]","}"));
        }
}