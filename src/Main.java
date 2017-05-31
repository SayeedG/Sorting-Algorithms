package com.sortingalgorithms;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private List<Sortable> sortingAlgorithms;

    public static void main(String[] args) throws IOException {
        String path = System.getProperty("path");
        new Main().start(path);
    }

    private void start(String path) throws IOException {
        sortingAlgorithms = new ArrayList<>();
        sortingAlgorithms.add(new InsertionSort());
        sortingAlgorithms.add(new HeapSort());
        sortingAlgorithms.add(new MergeSort());
        sortingAlgorithms.add(new QuickSort());

        Files.list(Paths.get(path))
                .filter(Files::isRegularFile)
                .forEach(filePath -> {
                            for (Sortable algorithm : sortingAlgorithms) {
                                try {
                                    int[] nums = getNums(filePath);
                                    String resultName = Paths.get(".", algorithm.getClass().getSimpleName()
                                            + "-" + filePath.getFileName().toString()).toString();
                                    Path resultPath = Paths.get(resultName);
                                    try (BufferedWriter writer = Files.newBufferedWriter(resultPath, StandardCharsets.UTF_8)) {
                                        Arrays.stream(algorithm.sort(nums)).forEach(num -> {
                                            try {
                                                writer.write(String.valueOf(num) + "\n");
                                            } catch (IOException e) {
                                                e.printStackTrace();
                                            }
                                        });
                                        writer.write(String.valueOf("COUNT: " + ((Countable) algorithm).getCount() + "; N: " + nums.length + ";"));
                                        writer.flush();
                                        System.out.println("COUNT: " + ((Countable) algorithm).getCount() + "; N: " + nums.length + "; " + resultName);
                                    } catch (IOException e) {
                                        e.printStackTrace();
                                    }
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                );
    }

    private int[] getNums(Path filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath.toFile()));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = br.readLine()) != null) {
            if (line.length() > 0) {
                lines.add(line);
            }
        }
        int[] nums = new int[lines.size()];
        int i = 0;

        for (String l : lines) {
            try {
                nums[i++] = Integer.parseInt(l);
            } catch (NumberFormatException e) {
                // skip non numbers
            }
        }
        return nums;

    }

}
