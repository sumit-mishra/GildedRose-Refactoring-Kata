package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GildedRoseTest {

    @Test
    void updateQuality_NormalItem_DecreaseSellInValueByOne() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item normalItem = new Item("+5 Dexterity Vest", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{normalItem});

        inventory.updateQuality();

        assertEquals(sellInDays - 1, normalItem.sellIn);
    }

    @Test
    void updateQuality_NormalItem_DecreaseQualityValueByOne() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item normalItem = new Item("Elixir of the Mongoose", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{normalItem});

        inventory.updateQuality();

        assertEquals(qualityValue - 1, normalItem.quality);
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
    void updateQuality_NormalItem_DecreasesQualityTwiceAsFast_WhenSellInValueIsNegative() {
        int sellInDays = -1;
        int qualityValue = 10;
        Item item = new Item("Elixir of the Mongoose", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(8, item.quality, "Normal item degrades twice as fast i.e. decrease quality value by 2");
    }

    @Test
    void updateQuality_NormalItem_QualityIsNeverNegative() {
        int sellInDays = 5;
        int qualityValue = 0;
        Item item = new Item("Elixir of the Mongoose", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityValue() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item item = new Item("Aged Brie", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(qualityValue + 1, item.quality, "Aged items increases quality value by 1");
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityValueLimitIsFifty() {
        int sellInDays = 10;
        int qualityValue = 50;
        Item item = new Item("Aged Brie", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(qualityValue, item.quality, "Aged items quality value increase stops at 50.");
    }

    @Test
    void updateQuality_AgedItem_IncreaseQualityTwiceAsFast_WhenSellInValueIsZeroOrLess() {
        int sellInDays = -1;
        int qualityValue = 10;
        Item item = new Item("Aged Brie", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(12, item.quality);
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseSellInValue() {
        int sellInDays = -1;
        int qualityValue = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(-1, item.sellIn, "A legendary item never has to be sold, hence no decrease in sellIn value.");
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseQualityValue() {
        int sellInDays = -1;
        int qualityValue = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(10, item.quality, "A legendary item never has to be sold, hence no decrease in quality value.");
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValue() {
        int sellInDays = 15;
        int qualityValue = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(21, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueByTwo_WhenSellInValueIsTenOrLess() {
        int sellInDays = 10;
        int qualityValue = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(22, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueLimitIsFifty() {
        int sellInDays = 10;
        int qualityValue = 50;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueByThree_WhenSellInValueIsFiveOrLess() {
        int sellInDays = 5;
        int qualityValue = 20;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(23, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueUpToFifty_WhenSellInValueIsFiveOrLess() {
        int sellInDays = 5;
        int qualityValue = 49;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(50, item.quality);
    }

    @Test
    void updateQuality_BackstagePasses_UpdateQualityToZero_WhenSellInValueIsZeroOrLess() {
        int sellInDays = 0;
        int qualityValue = 10;
        Item item = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(0, item.quality);
    }

    @Test
    void updateQuality_ConjuredItem_DecreasesQualityTwiceAsFastAsNormalItem() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item item = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(8, item.quality);
    }

    @Test
    void updateQuality_ConjuredItem_DecreasesSellInValue() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item item = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(9, item.sellIn);
    }

    @Test
    void updateQuality_ConjuredItem_DecreasesQualityValueByFour_WhenSellInValueIsZeroOrLess() {
        int sellInDays = 0;
        int qualityValue = 10;
        Item item = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(6, item.quality);
    }

    @Test
    void updateQuality_ConjuredItem_QualityIsNeverNegative() {
        int sellInDays = 5;
        int qualityValue = 0;
        Item item = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(0, item.quality);
    }

}
