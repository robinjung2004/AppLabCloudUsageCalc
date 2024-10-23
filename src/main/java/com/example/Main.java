package com.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        DataFetcher fetcher = new DataFetcher();
        DataProcessor processor = new DataProcessor();
        ResultSender sender = new ResultSender();

        try {
            Dataset dataset = fetcher.fetchDataset();
            Map<String, Long> totalUsage = processor.calculateTotalUsage(dataset);

            List<ResultItem> resultItems = new ArrayList<>();
            for (Map.Entry<String, Long> entry : totalUsage.entrySet()) {
                resultItems.add(new ResultItem(entry.getKey(), entry.getValue()));
            }

            Result result = new Result(resultItems);
            sender.sendResult(result);

            System.out.println("Ergebnis erfolgreich gesendet.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
