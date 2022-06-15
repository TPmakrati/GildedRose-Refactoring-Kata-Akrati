package com.gildedrose;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class GildedRose {
	Item[] items;

	public GildedRose(Item[] items) {
		this.items = items;
	}

	public void updateQuality() {
		List<Item> itemsWithoutSulfuras = Arrays.asList(items).stream().filter(item -> !item.name.equals("Sulfuras, Hand of Ragnaros")).collect(Collectors.toList());
		for (Item item : itemsWithoutSulfuras) {			
			int alterBy = 0;
			if (item.quality >= 0 && item.quality <= 50) {
				if (!item.name.equals("Aged Brie")
						&& !item.name.equals("Backstage passes to a TAFKAL80ETC concert")) {
					alterBy--;
					if (item.name.equals("Conjured Mana Cake"))
						alterBy--;
				} else {
					alterBy++;
					if (item.name.equals("Backstage passes to a TAFKAL80ETC concert")
							&& item.quality + alterBy < 50) {
						if (item.sellIn < 6)
							alterBy += 2;
						else if (item.sellIn < 11)
							alterBy += 1;
					}
				}
			}
			item.sellIn -= 1;
			if (item.sellIn < 0) {
				if (!item.name.equals("Backstage passes to a TAFKAL80ETC concert"))
					alterBy *= 2;
				else
					alterBy = -item.quality;
			}
			if (item.quality + alterBy > -1 && item.quality + alterBy < 51)
				item.quality += alterBy;
		}
	}
}