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
		List<Item> itemsWithoutSulfuras = Arrays.asList(items).parallelStream()
				.filter(item -> (!item.name.equals("Sulfuras, Hand of Ragnaros"))).collect(Collectors.toList());
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
		int value;
		switch (item.name) {
		case "Backstage passes to a TAFKAL80ETC concert":
			value = item.sellIn < 6 ? 3 : item.sellIn < 11 ? 2 : 1;
			break;
		case "Aged Brie":
			value = 1;
			break;
		default:
			value = item.name.equals("Conjured Mana Cake") ? -2 : -1;
			break;
		}
		alterBy = updateAlterByValue(alterBy, value);
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