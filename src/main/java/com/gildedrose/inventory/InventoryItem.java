package com.gildedrose.inventory;

import com.gildedrose.Item;

import static com.gildedrose.inventory.AgedBrie.AGED_BRIE;
import static com.gildedrose.inventory.BackstagePasses.BACKSTAGE_PASSES;
import static com.gildedrose.inventory.Conjured.CONJURED;
import static com.gildedrose.inventory.Sulfuras.SULFURAS;

public class InventoryItem {

    protected Item item;

    public InventoryItem(Item item) {
        this.item = item;
    }

    public static InventoryItem create(Item item) {
        if (item.name.equals(AGED_BRIE)) {
            return new AgedBrie(item);
        }
        if (item.name.equals(BACKSTAGE_PASSES)) {
            return new BackstagePasses(item);
        }
        if (item.name.equals(SULFURAS)) {
            return new Sulfuras(item);
        }
        if (item.name.equals(CONJURED)) {
            return new Conjured(item);
        }

        return new InventoryItem(item);
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
        if (item.quality > 0) {
            item.quality -= 1;
        }
    }

    protected void increaseQuality() {
        if (item.quality < 50) {
            item.quality += 1;
        }
    }

    protected boolean isExpired() {
        return item.sellIn < 0;
    }

}
