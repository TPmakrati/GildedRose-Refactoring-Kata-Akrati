package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ConjuredTest {
    
    @Test
    void testConjuredInDecreaseInQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 12, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(38, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }
    
    @Test
    void testConjuredWhenExpired() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    void testConjuredWhenQualityMoreThanFifty() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", -1, 50) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(46, app.items[0].quality);
        assertEquals(-2, app.items[0].sellIn);
    }
    
    @Test
    void testConjuredWhenQualityReachesZero() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 2, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    
}
