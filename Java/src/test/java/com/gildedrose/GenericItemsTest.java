package com.gildedrose;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GenericItemsTest {
    
    @Test
    void testGenericDecreaseInQuality() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 1, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(39, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }
    
    @Test
    void testGenericWhenExpired() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", 0, 4) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(2, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    void testGenericWhenQualityReachesZero() {
        Item[] items = new Item[] { new Item("Elixir of the Mongoose", -2, 1) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-3, app.items[0].sellIn);
    }
    
}
