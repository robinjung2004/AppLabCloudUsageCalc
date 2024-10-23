package com.example;

import java.util.HashMap;
import java.util.Map;

public class DataProcessor {
    public Map<String, Long> calculateTotalUsage(Dataset dataset) {
        Map<String, Map<String, Long>> customerWorkloadMap = new HashMap<>();

        for (Event event : dataset.getEvents()) {
            customerWorkloadMap.putIfAbsent(event.getCustomerId(), new HashMap<>());
            Map<String, Long> workloadMap = customerWorkloadMap.get(event.getCustomerId());

            String workloadId = event.getWorkloadId();
            workloadMap.putIfAbsent(workloadId, 0L);

            if ("start".equals(event.getEventType())) {
                workloadMap.put(workloadId, -event.getTimestamp());
            } else if ("stop".equals(event.getEventType())) {
                workloadMap.put(workloadId, workloadMap.get(workloadId) + event.getTimestamp());
            }
        }

        // Gesamtnutzungszeit pro Kunde berechnen
        Map<String, Long> totalUsagePerCustomer = new HashMap<>();
        for (Map.Entry<String, Map<String, Long>> customerEntry : customerWorkloadMap.entrySet()) {
            long totalUsage = 0;
            for (long usage : customerEntry.getValue().values()) {
                totalUsage += usage;
            }
            totalUsagePerCustomer.put(customerEntry.getKey(), totalUsage);
        }

        return totalUsagePerCustomer;
    }
}
