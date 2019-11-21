package net.thedudemc.capacitorbox;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = CapacitorBox.MODID, name = CapacitorBox.NAME, version = CapacitorBox.VERSION, dependencies = "required-after:enderio;required-after:endercore")
public class CapacitorBox {

	@Mod.Instance
	private static CapacitorBox INSTANCE;

	public static final String MODID = "capacitorbox";
	public static final String NAME = "Capacitor Box";
	public static final String VERSION = "1.12.2-1.0.2";

	private static Logger logger = LogManager.getLogger(CapacitorBox.NAME);

	public static CapacitorBox getInstance() {
		return INSTANCE;
	}

	public static ResourceLocation getResource(String name) {
		return new ResourceLocation(CapacitorBox.MODID, name);
	}

	@Mod.EventHandler
	public void onPreInitialization(FMLPreInitializationEvent event) {
	}

	@Mod.EventHandler
	public void onInitialization(FMLInitializationEvent event) {
	}

	@Mod.EventHandler
	public void onPostInitialization(FMLPostInitializationEvent event) {
	}
}
