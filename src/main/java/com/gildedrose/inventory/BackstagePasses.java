package com.gildedrose.inventory;

import com.gildedrose.Item;

public class BackstagePasses extends InventoryItem {
    public static final String NAME = "Backstage passes to a TAFKAL80ETC concert";
    public static final int SIX_DAYS = 6;
    public static final int ELEVEN_DAYS = 11;

    public BackstagePasses(Item item) {
        super(item);
    }

    @Override
    protected void updateQuality() {
        increaseQuality();
        if (item.sellIn < ELEVEN_DAYS) {
            increaseQuality();
        }
        if (item.sellIn < SIX_DAYS) {
            increaseQuality();
        }
    }

    @Override
    protected void processExpired() {
        item.quality = 0;
    }
}
