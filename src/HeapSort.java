package com.sortingalgorithms;

public class HeapSort implements Sortable, Countable {

    private long count = 0;

    @Override
    public int[] sort(int A[]) {
        this.count = 0;
        int n = A.length;
        for (int i = n / 2 - 1; i >= 0; i--) {
            heapify(A, n, i);
        }
        for (int i = n - 1; i >= 0; i--) {
            int temp = A[0];
            A[0] = A[i];
            A[i] = temp;
            heapify(A, i, 0);
        }
        return A;
    }

    private void heapify(int arr[], int n, int i) {
        int largest = i;
        int l = 2 * i + 1;
        int r = 2 * i + 2;
        if (l < n && arr[l] > arr[largest]) {
            largest = l;
        }
        if (r < n && arr[r] > arr[largest]) {
            largest = r;
        }
        if (largest != i) {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;
            this.count++;
            heapify(arr, n, largest);
        }
    }

    @Override
    public long getCount() {
        return this.count;
    }
}
