package com.sortingalgorithms;

public class InsertionSort implements Sortable, Countable {

    private long count = 0;

    public int[] sort(int[] A) {
        this.count = 0;
        int n = A.length;
        for (int i = 1; i < n; ++i) {
            int key = A[i];
            int j = i - 1;
            while (j >= 0 && A[j] > key) {
                this.count++;
                A[j + 1] = A[j];
                j = j - 1;
            }
            A[j + 1] = key;
        }
        return A;
    }

    @Override
    public long getCount() {
        return this.count;
    }

}