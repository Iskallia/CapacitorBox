package net.thedudemc.capacitorbox.init;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBlock;
import net.minecraftforge.registries.IForgeRegistry;

public class InitBlock {

	//public static BlockCapacitorBox CAPACITOR_BOX = new BlockCapacitorBox("capacitor_box");
	//public static ItemBlock ITEM_CAPACITOR_BOX = getItemBlock(CAPACITOR_BOX);

	public static void registerBlocks(IForgeRegistry<Block> registry) {
		//registerBlock(CAPACITOR_BOX, registry);
	}

	public static void registerTileEntities() {
		// GameRegistry.registerTileEntity(TileEntityInfusionCauldron.class,
		// CapacitorBox.getResource("infusion_cauldron"));
	}

	public static void registerItemBlocks(IForgeRegistry<Item> registry) {
		//registry.register(ITEM_CAPACITOR_BOX);
	}

	/* -------------------------- */

	private static void registerBlock(Block block, IForgeRegistry<Block> registry) {
		registry.register(block);
	}

	private static ItemBlock getItemBlock(Block block) {
		ItemBlock itemBlock = new ItemBlock(block);
		String resourceName = block.getRegistryName().getResourcePath();
		itemBlock.setUnlocalizedName(resourceName);
		itemBlock.setRegistryName(resourceName);
		return itemBlock;
	}

}
