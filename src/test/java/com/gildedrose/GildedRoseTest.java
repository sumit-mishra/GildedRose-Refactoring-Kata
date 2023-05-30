package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQuality_NormalItem_DecreaseSellInValueByOne() {
        int initialSellIn = 10;
        int initialQuality = 10;
        Item normalItem = new Item("+5 Dexterity Vest", initialSellIn, initialQuality);
        GildedRose inventory = new GildedRose(new Item[]{normalItem});

        inventory.updateQuality();

        assertEquals(initialSellIn - 1, normalItem.sellIn);
    }

    @Test
    void updateQuality_NormalItem_DecreaseQualityValueByOne() {
        int initialSellIn = 10;
        int initialQuality = 10;
        Item normalItem = new Item("Elixir of the Mongoose", initialSellIn, initialQuality);
        GildedRose inventory = new GildedRose(new Item[]{normalItem});

        inventory.updateQuality();

        assertEquals(initialQuality - 1, normalItem.quality);
    }

    @Test
    void updateQuality_MultipleNormalItem_DecreaseSellInValueByOne() {
        Item normalItem1 = new Item("+5 Dexterity Vest", 5, 5);
        Item normalItem2 = new Item("Elixir of the Mongoose", 7, 7);

        GildedRose inventory = new GildedRose(new Item[]{normalItem1, normalItem2});

        inventory.updateQuality();

        assertEquals(4, normalItem1.sellIn);
        assertEquals(6, normalItem2.sellIn);
    }

    @Test
    void updateQuality_MultipleNormalItem_DecreaseQualityValueByOne() {
        Item normalItem1 = new Item("+5 Dexterity Vest", 5, 5);
        Item normalItem2 = new Item("Elixir of the Mongoose", 7, 7);

        GildedRose inventory = new GildedRose(new Item[]{normalItem1, normalItem2});

        inventory.updateQuality();

        assertEquals(4, normalItem1.quality);
        assertEquals(6, normalItem2.quality);
    }

    @Test
    void updateQuality_NormalItemDegradesTwiceAsFast_whenSellInValueIsNegative() {
        int sellIn = -1;
        int quality = 10;
        Item item = new Item("Elixir of the Mongoose", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(8, item.quality, "Normal item degrades twice as fast i.e. decrease quality value by 2");
    }

    @Test
    void updateQuality_NormalItemQuality_IsNeverNegative() {
        int sellIn = 5;
        int quality = 0;
        Item item = new Item("Elixir of the Mongoose", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(0, item.quality);
    }

}
