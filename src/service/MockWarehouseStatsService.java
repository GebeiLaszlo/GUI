package service;

import model.WarehouseStats;

public class MockWarehouseStatsService {

    public WarehouseStats getStats() {
        WarehouseStats stats = new WarehouseStats();

        stats.storeName = "MediaMarkt Budapest Raktár";

        stats.capacity = 50000;
        stats.currentStock = 37842;
        stats.freeSpace = stats.capacity - stats.currentStock;

        stats.budget = 2_000_000;
        stats.revenue = 12_345_678;
        stats.expenses = 7_000_000;
        stats.profit = stats.revenue - stats.expenses;

        return stats;
    }
}
