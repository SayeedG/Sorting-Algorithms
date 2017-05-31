package com.sortingalgorithms;

public class QuickSort implements Sortable, Countable {

    private long count = 0;

    @Override
    public int[] sort(int[] A) {
        this.count = 0;
        sort(A, 0, A.length - 1);
        return A;
    }

    int partition(int arr[], int low, int high) {
        int pivot = arr[high];
        int i = (low - 1);
        for (int j = low; j <= high - 1; j++) {
            this.count++;
            if (arr[j] <= pivot) {
                i++;
                int temp = arr[i];
                arr[i] = arr[j];
                arr[j] = temp;
            }
        }
        int temp = arr[i + 1];
        arr[i + 1] = arr[high];
        arr[high] = temp;
        return i + 1;
    }

    void sort(int arr[], int low, int high) {
        if (low < high) {
            int pi = partition(arr, low, high);
            sort(arr, low, pi - 1);
            sort(arr, pi + 1, high);
        }
    }

    @Override
    public long getCount() {
        return this.count;
    }
}
