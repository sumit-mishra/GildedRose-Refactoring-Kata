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
    void updateQuality_NormalItem_DegradesTwiceAsFast_WhenSellInValueIsNegative() {
        int sellIn = -1;
        int quality = 10;
        Item item = new Item("Elixir of the Mongoose", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(8, item.quality, "Normal item degrades twice as fast i.e. decrease quality value by 2");
    }

    @Test
    void updateQuality_NormalItem_QualityIsNeverNegative() {
        int sellIn = 5;
        int quality = 0;
        Item item = new Item("Elixir of the Mongoose", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityValue() {
        int sellIn = 10;
        int quality = 10;
        Item item = new Item("Aged Brie", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(quality + 1, item.quality, "Aged items increases quality value by 1");
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityValueLimitIsFifty() {
        int sellIn = 10;
        int quality = 50;
        Item item = new Item("Aged Brie", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(quality, item.quality, "Aged items quality value increase stops at 50.");
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityTwiceAsFast_WhenSellInValueIsZeroOrLess() {
        int sellIn = -1;
        int quality = 10;
        Item item = new Item("Aged Brie", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(12, item.quality);
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseSellInValue() {
        int sellIn = -1;
        int quality = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(-1, item.sellIn, "A legendary item never has to be sold, hence no decrease in sellIn value.");
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseQualityValue() {
        int sellIn = -1;
        int quality = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellIn, quality);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(10, item.quality, "A legendary item never has to be sold, hence no decrease in quality value.");
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValue() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 15, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(21, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueByTwo_WhenSellInValueIsTenOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(22, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueLimitIsFifty() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 10, 50);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueByTwo_WhenSellInValueIsFiveOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(23, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueUpToFifty_WhenSellInValueIsFiveOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 5, 49);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_UpdateQualityToZero_WhenSellInValueIsZeroOrLess() {
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", 0, 20);
        GildedRose subject = new GildedRose(new Item[]{item});

        subject.updateQuality();

        assertEquals(0, item.quality);
    }

}
