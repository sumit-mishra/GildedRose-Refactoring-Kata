package com.gildedrose.inventory;

import com.gildedrose.Item;

public class Sulfuras extends InventoryItem {
    public static final String SULFURAS = "Sulfuras, Hand of Ragnaros";

    public Sulfuras(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
    }

    @Override
    protected void updateExpiry() {
    }

    @Override
    protected void processExpired() {
    }
}
