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
    void updateQuality_NormalItem_DecreasesQualityValueByTwo_WhenSellInValueIsZeroOrNegative() {
        int sellInDays1 = 0;
        int qualityValue1 = 10;
        Item itemWithZeroSellInDays = new Item("Elixir of the Mongoose", sellInDays1, qualityValue1);

        int sellInDays2 = -1;
        int qualityValue2 = 20;
        Item itemWithNegativeSellInDays = new Item("Elixir of the Mongoose", sellInDays2, qualityValue2);

        GildedRose inventory = new GildedRose(new Item[]{itemWithZeroSellInDays, itemWithNegativeSellInDays});

        inventory.updateQuality();

        assertEquals(8, itemWithZeroSellInDays.quality, "Normal item degrades twice as fast i.e. decrease quality value by 2");
        assertEquals(18, itemWithNegativeSellInDays.quality, "Normal item degrades twice as fast i.e. decrease quality value by 2");
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
        int sellInDays1 = 0;
        int qualityValue1 = 10;
        Item itemWithZeroSellInDays = new Item("Aged Brie", sellInDays1, qualityValue1);

        int sellInDays2 = -1;
        int qualityValue2 = 20;
        Item itemWithNegativeSellInDays = new Item("Aged Brie", sellInDays2, qualityValue2);

        GildedRose inventory = new GildedRose(new Item[]{itemWithZeroSellInDays, itemWithNegativeSellInDays});

        inventory.updateQuality();

        assertEquals(12, itemWithZeroSellInDays.quality);
        assertEquals(22, itemWithNegativeSellInDays.quality);
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseSellInValue() {
        int sellInDays = 0;
        int qualityValue = 10;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(sellInDays, item.sellIn, "A legendary item never has to be sold, hence no decrease in sellIn value.");
    }

    @Test
    void updateQuality_LegendaryItem_NeverDecreaseQualityValue() {
        int sellInDays = -1;
        int qualityValue = 80;
        Item item = new Item("Sulfuras, Hand of Ragnaros", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(qualityValue, item.quality, "A legendary item never has to be sold, hence no decrease in quality value.");
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
        int sellInDays1 = 9;
        int qualityValue1 = 20;
        Item itemWithSellInDaysNine = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays1, qualityValue1);

        int sellInDays2 = 10;
        int qualityValue2 = 30;
        Item itemWithSellInDaysTen = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays2, qualityValue2);
        GildedRose inventory = new GildedRose(new Item[]{itemWithSellInDaysNine, itemWithSellInDaysTen});

        inventory.updateQuality();

        assertEquals(22, itemWithSellInDaysNine.quality);
        assertEquals(32, itemWithSellInDaysTen.quality);
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
        int sellInDays1 = 4;
        int qualityValue1 = 20;
        Item itemWithSellInDaysFour = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays1, qualityValue1);

        int sellInDays2 = 5;
        int qualityValue2 = 30;
        Item itemWithSellInDaysFive = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays2, qualityValue2);
        GildedRose inventory = new GildedRose(new Item[]{itemWithSellInDaysFour, itemWithSellInDaysFive});

        inventory.updateQuality();

        assertEquals(qualityValue1 + 3, itemWithSellInDaysFour.quality);
        assertEquals(qualityValue2 + 3, itemWithSellInDaysFive.quality);
    }

    @Test
    void updateQuality_BackstagePasses_IncreaseQualityValueUpToFifty_WhenSellInValueIsFiveOrLess() {
        int sellInDays1 = 4;
        int qualityValue1 = 48;
        Item itemWithSellInDaysFour = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays1, qualityValue1);

        int sellInDays2 = 5;
        int qualityValue2 = 49;
        Item itemWithSellInDaysFive = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays2, qualityValue2);
        GildedRose inventory = new GildedRose(new Item[]{itemWithSellInDaysFour, itemWithSellInDaysFive});

        inventory.updateQuality();

        assertEquals(qualityValue1 + 2, itemWithSellInDaysFour.quality);
        assertEquals(qualityValue2 + 1, itemWithSellInDaysFive.quality);
    }

    @Test
    void updateQuality_BackstagePasses_UpdateQualityToZero_WhenSellInValueIsZeroOrLess() {
        int sellInDays1 = -1;
        int qualityValue1 = 50;
        Item itemWithSellInDaysNegative = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays1, qualityValue1);

        int sellInDays2 = 0;
        int qualityValue2 = 10;
        Item itemWithSellInDaysZero = new Item("Backstage passes to a TAFKAL80ETC concert", sellInDays2, qualityValue2);
        GildedRose inventory = new GildedRose(new Item[]{itemWithSellInDaysNegative, itemWithSellInDaysZero});

        inventory.updateQuality();

        assertEquals(0, itemWithSellInDaysNegative.quality);
        assertEquals(0, itemWithSellInDaysZero.quality);
    }

    @Test
    void updateQuality_ConjuredItem_DecreasesQualityTwiceAsFastAsNormalItem() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item conjuredItem = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{conjuredItem});

        inventory.updateQuality();

        assertEquals(qualityValue - 2, conjuredItem.quality);
    }

    @Test
    void updateQuality_ConjuredItem_DecreasesSellInValue() {
        int sellInDays = 10;
        int qualityValue = 10;
        Item item = new Item("Conjured", sellInDays, qualityValue);
        GildedRose inventory = new GildedRose(new Item[]{item});

        inventory.updateQuality();

        assertEquals(qualityValue - 1, item.sellIn);
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
