package net.thedudemc.capacitorbox.init;

import net.minecraft.item.Item;
import net.minecraftforge.registries.IForgeRegistry;
import net.thedudemc.capacitorbox.item.ItemCapacitorBox;

public class InitItem {

	public static ItemCapacitorBox CAPACITOR_BOX = new ItemCapacitorBox("capacitor_box");

	public static void registerItems(IForgeRegistry<Item> registry) {

		registerItem(CAPACITOR_BOX, registry);
	}

	private static void registerItem(Item item, IForgeRegistry<Item> registry) {
		registry.register(item);
	}

}
