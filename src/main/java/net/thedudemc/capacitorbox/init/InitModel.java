package net.thedudemc.capacitorbox.init;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.item.Item;
import net.minecraftforge.client.model.ModelLoader;
import net.thedudemc.capacitorbox.CapacitorBox;

public class InitModel {

	public static void registerItemModels() {
		registerSimpleItemModel(InitItem.CAPACITOR_BOX, 0);

	}

	public static void registerTileEntityRenderers() {
		// ClientRegistry.bindTileEntitySpecialRenderer(TileEntityGraveStone.class, new
		// TESRGraveStone());
	}

	private static void registerSimpleItemModel(Item item, int metadata) {
		ModelLoader.setCustomModelResourceLocation(item, metadata, new ModelResourceLocation(item.getRegistryName(), "inventory"));
	}

	private static void registerBlockModel(Block block, int metadata) {
		ModelLoader.setCustomModelResourceLocation(Item.getItemFromBlock(block), metadata, new ModelResourceLocation(CapacitorBox.getResource(block.getUnlocalizedName().substring(5)), "inventory"));
	}

}
