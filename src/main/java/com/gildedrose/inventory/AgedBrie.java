package com.gildedrose.inventory;

import com.gildedrose.Item;

public class AgedBrie extends InventoryItem {
    public static final String AGED_BRIE = "Aged Brie";

    public AgedBrie(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
    }

    @Override
    protected void processExpired() {
        increaseQuality();
    }
}
