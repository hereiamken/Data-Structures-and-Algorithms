public class MergeBUSort {
    public static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high) {
        for (int k = low; k <= high; k++) {
            aux[k] = a[k];
        }

        int i = low, j = mid + 1;
        for (int k = low; k <= high; k++) {
            if (i > mid) {
                a[k] = aux[j++];
            } else if (j > high) {
                a[k] = aux[i++];
            } else if (less(aux[j], aux[i])) {
                a[k] = aux[j++];
            } else {
                a[k] = aux[i++];
            }
        }
    }

//    public static void sort(Comparable[] a, Comparable[] aux, int low, int high) {
//        if (high <= low) return;
//        int mid = low + (high - low) / 2;
//        sort(a, aux, low, mid);
//        sort(a, aux, mid + 1, high);
//        merge(a, aux, low, mid, high);
//    }

    private static boolean less(Comparable v, Comparable w) {
        // check less
        return v.compareTo(w) < 0;
    }

    private static void sort(Comparable[] a) {
        int N = a.length;
        Comparable[] aux = new Comparable[N];
        for (int sz = 1; sz < N; sz = sz + sz) {
            for (int low = 0; low < N - sz; low += sz + sz) {
                merge(a, aux, low, low + sz - 1, Math.min(low + sz + sz - 1, N - 1));
            }
        }
    }

    public static void main(String[] args) {
        Integer[] arr = {10, 23, 3, 14, 46, 5, 0, 13, 67, 96};
        sort(arr);

        for (Integer a : arr
        ) {
            System.out.println(a);
        }
    }
}
