package com.gildedrose.inventory;

import com.gildedrose.Item;

public class InventoryItem {

    public static final int FIFTY = 50;
    public static final int ZERO_DAYS = 0;
    protected Item item;

    public InventoryItem(Item item) {
        this.item = item;
    }

    public static InventoryItem create(Item item) {
        switch (item.name) {
            case AgedBrie.NAME:
                return new AgedBrie(item);
            case BackstagePasses.NAME:
                return new BackstagePasses(item);
            case Sulfuras.NAME:
                return new Sulfuras(item);
            case Conjured.NAME:
                return new Conjured(item);
            default:
                return new InventoryItem(item);
        }
    }

    public void dailyUpdate() {
        updateQuality();
        updateExpiry();
        if (isExpired()) {
            processExpired();
        }
    }

    protected void updateQuality() {
        decreaseQuality();
    }

    protected void updateExpiry() {
        item.sellIn -= 1;
    }

    protected void processExpired() {
        decreaseQuality();
    }

    protected void decreaseQuality() {
        item.quality = Math.max(item.quality - 1, ZERO_DAYS);
    }

    protected void increaseQuality() {
        if (item.quality < FIFTY) {
            item.quality += 1;
        }
    }

    protected boolean isExpired() {
        return item.sellIn < ZERO_DAYS;
    }

}
