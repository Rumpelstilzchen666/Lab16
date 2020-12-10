package MyUtils;

public class QuickSort {
    public static <T> void quickSort(final T[] arr,
            final MyComparator<? super T> comparator) {
        if(arr.length == 0)
            return;
        quickSort(arr, 0, arr.length - 1, comparator);
    }

    private static <T> void quickSort(final T[] arr, final int first,
            final int last, final MyComparator<? super T> comparator) {
        int i = first, j = last;
        T key = arr[(first + last) / 2];

        while(i < j) {
            while(comparator.compare(arr[i], key) < 0) {
                i++;
            }
            while(comparator.compare(arr[j], key) > 0) {
                j--;
            }

            if(i <= j) {
                if(i < j) {
                    T temp = arr[i];
                    arr[i] = arr[j];
                    arr[j] = temp;
                }
                i++;
                j--;
            }
        }
        if(i < last) {
            quickSort(arr, i, last, comparator);
        }
        if(first < j) {
            quickSort(arr, first, j, comparator);
        }
    }
}
