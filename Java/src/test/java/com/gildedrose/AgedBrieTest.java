package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AgedBrieTest {
    
    @Test
    void testAgedBrieIncreaseInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    
    @Test
    void testAgedBrieIfExpired() {
        Item[] items = new Item[] { new Item("Aged Brie", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(4, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    void testAgedBrieWhenQualityReachesFifty() {
        Item[] items = new Item[] { new Item("Aged Brie", -2, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(-3, app.items[0].sellIn);
    }
    
    @Test
    void testAgedBrieWhenQualityReachesZero() {
        Item[] items = new Item[] { new Item("Aged Brie", -80, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(-81, app.items[0].sellIn);
    }
}
