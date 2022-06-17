package com.gildedrose;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class GildedRoseTest {

    @Test
    public void foo() {
        Item[] items = new Item[] { new Item("foo", 0, 0) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals("foo", app.items[0].name);
    }
    
    @Test
    public void testAgedBrieIncreaseInQuality() {
        Item[] items = new Item[] { new Item("Aged Brie", 2, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(3, app.items[0].quality);
        assertEquals(1, app.items[0].sellIn);
    }
    
    @Test
    public void testBackstageIncreaseInQuality() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 12, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(41, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }
    
    @Test
    public void testBackstageWhenDaysRemainTen() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 10, 49) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(50, app.items[0].quality);
        assertEquals(9, app.items[0].sellIn);
    }
    
    @Test
    public void testBackstageWhenExpired() {
        Item[] items = new Item[] { new Item("Backstage passes to a TAFKAL80ETC concert", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    public void testConjuredInDecreaseInQuality() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 12, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(38, app.items[0].quality);
        assertEquals(11, app.items[0].sellIn);
    }
    
    @Test
    public void testConjuredWhenExpired() {
        Item[] items = new Item[] { new Item("Conjured Mana Cake", 0, 2) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(0, app.items[0].quality);
        assertEquals(-1, app.items[0].sellIn);
    }
    
    @Test
    public void testGenericDecreaseInQuality() {
        Item[] items = new Item[] { new Item("+5 Dexterity Vest", 1, 40) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(39, app.items[0].quality);
        assertEquals(0, app.items[0].sellIn);
    }
    
    @Test
    public void testSulfuras() {
        Item[] items = new Item[] { new Item("Sulfuras, Hand of Ragnaros", 12, 80) };
        GildedRose app = new GildedRose(items);
        app.updateQuality();
        assertEquals(80, app.items[0].quality);
        assertEquals(12, app.items[0].sellIn);
    }
}
