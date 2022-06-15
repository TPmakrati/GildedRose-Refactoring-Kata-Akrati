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
		List<Item> itemsWithoutSulfuras = Arrays.asList(items).parallelStream().filter(
				item -> (!item.name.equals("Sulfuras, Hand of Ragnaros")) && !(item.quality < 0 || item.quality > 50))
				.collect(Collectors.toList());
		itemsWithoutSulfuras.parallelStream().forEach(item -> updateItemQuality(item));
		adjustQuality(itemsWithoutSulfuras);
	}

	private void updateItemQuality(Item item) {
		int alterBy = 0;
		alterBy = updateAlterBy(item, alterBy);
		item.sellIn -= 1;
		if (item.sellIn < 0) {
			alterBy = updateAlterByIfExpired(item, alterBy);
		}
		alterByValue(item, alterBy);
	}

	private int updateAlterBy(Item item, int alterBy) {
		switch(item.name){
			case "Conjured Mana Cake" :
				alterBy = updateAlterByValue(alterBy, -2);
				break;
			case "Backstage passes to a TAFKAL80ETC concert" :
				if (item.sellIn < 6)
					alterBy = updateAlterByValue(alterBy, +3);
				else if (item.sellIn < 11)
					alterBy = updateAlterByValue(alterBy, +2);
				else
					alterBy = updateAlterByValue(alterBy, +1);
				break;
			case "Aged Brie" :
				alterBy = updateAlterByValue(alterBy, +1);
				break;
			default :
				alterBy = updateAlterByValue(alterBy, -1);
				break;
		}		
		return alterBy;
	}

	private int updateAlterByValue(int alterBy, int value) {
		alterBy += value;
		return alterBy;		
	}
	
	private int updateAlterByIfExpired(Item item, int alterBy) {
		alterBy = !item.name.equals("Backstage passes to a TAFKAL80ETC concert") ? alterBy *= 2 : -item.quality;
		return alterBy;
	}

	private void alterByValue(Item item, int alterBy) {
		item.quality += alterBy;
	}

	private void adjustQuality(List<Item> itemsWithoutSulfuras) {
		itemsWithoutSulfuras.parallelStream().filter(item -> item.quality < 0).forEach(item -> item.quality = 0);
		itemsWithoutSulfuras.parallelStream().filter(item -> item.quality > 50).forEach(item -> item.quality = 50);
	}
}