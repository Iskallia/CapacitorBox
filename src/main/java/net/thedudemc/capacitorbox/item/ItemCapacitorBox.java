package net.thedudemc.capacitorbox.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Nonnull;

import crazypants.enderio.base.EnderIO;
import crazypants.enderio.base.Log;
import crazypants.enderio.base.capacitor.CapacitorHelper;
import crazypants.enderio.base.capacitor.CapacitorHelper.SetType;
import crazypants.enderio.base.init.ModObject;
import crazypants.enderio.base.loot.WeightedInteger;
import crazypants.enderio.base.loot.WeightedUpgrade;
import crazypants.enderio.util.NbtValue;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.World;
import net.minecraft.world.storage.loot.RandomValueRange;
import net.minecraft.world.storage.loot.conditions.LootCondition;
import net.minecraft.world.storage.loot.functions.SetMetadata;
import net.thedudemc.capacitorbox.CapacitorBox;

public class ItemCapacitorBox extends Item {

	public ItemCapacitorBox(String name) {
		this.setUnlocalizedName(name);
		this.setRegistryName(CapacitorBox.getResource(name));
	}

	@Override
	public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand) {

		ItemStack heldItem = player.getHeldItem(hand);

		Item item = ModObject.itemBasicCapacitor.getItemNN();
		ItemStack stack = new ItemStack(item);

		ItemStack capacitor = getCapacitor(stack, world.rand);

		RandomValueRange range = new RandomValueRange(3, 4);
		int meta = range.generateInt(world.rand);
		capacitor.setItemDamage(meta);

		player.dropItem(capacitor, false, true);

		if (!player.isCreative())
			heldItem.shrink(1);

		return new ActionResult<ItemStack>(EnumActionResult.PASS, heldItem);
	}

	// all of the following code is credited to EnderIO developers

	public @Nonnull ItemStack getCapacitor(@Nonnull ItemStack stack, @Nonnull Random rand) {
		Map<WeightedUpgrade, Float> keys = new HashMap<WeightedUpgrade, Float>();

		float baselevel = getRandomBaseLevel(rand);

		int no = getRandomCount(rand);

		for (int i = 0; i < no; i++) {
			WeightedUpgrade randomKey = getUpgrade(rand);
			float randomLevel = getRandomLevel(baselevel, rand);
			baselevel = Math.max(baselevel - randomLevel / 10f * rand.nextFloat(), .5f);
			if (keys.containsKey(randomKey)) {
				randomLevel = Math.max(randomLevel, keys.get(randomKey));
			}
			keys.put(randomKey, randomLevel);
		}

		String name = buildBaseName(EnderIO.lang.localize("loot.capacitor.name"), baselevel);
		stack = CapacitorHelper.addCapData(stack, SetType.LEVEL, null, baselevel);

		for (Entry<WeightedUpgrade, Float> entry : keys.entrySet()) {
			stack = CapacitorHelper.addCapData(stack, entry.getKey().setType, entry.getKey().capacitorKey, entry.getValue());
			name = buildName(EnderIO.lang.localize(entry.getKey().langKey, name), entry.getValue());
		}

		NbtValue.CAPNAME.setString(stack, name);

		String count_s = EnderIO.lang.localize("loot.capacitor.entry.count");
		int count = 8;
		try {
			count = Integer.valueOf(count_s);
		} catch (NumberFormatException e) {
			Log.warn("The value of the language key 'enderio.loot.capacitor.entry.count' is not a valid number!");
		}
		NbtValue.CAPNO.setInt(stack, rand.nextInt(count));

		count_s = EnderIO.lang.localize("loot.capacitor.title.count");
		count = 8;
		try {
			count = Integer.valueOf(count_s);
		} catch (NumberFormatException e) {
			Log.warn("The value of the language key 'enderio.loot.capacitor.title.count' is not a valid number!");
		}
		stack.setStackDisplayName(EnderIO.lang.localize("loot.capacitor.title." + rand.nextInt(count)));

		NbtValue.GLINT.setInt(stack, 1);

		return stack;
	}

	private static final @Nonnull List<WeightedInteger> weightedCount = new ArrayList<WeightedInteger>();
	static {
		weightedCount.add(new WeightedInteger(1, 5));
		weightedCount.add(new WeightedInteger(3, 4));
		weightedCount.add(new WeightedInteger(6, 3));
		weightedCount.add(new WeightedInteger(6, 2));
		weightedCount.add(new WeightedInteger(24, 1));
	}

	private int getRandomCount(@Nonnull Random rand) {
		return WeightedRandom.getRandomItem(rand, weightedCount).getInteger();
	}

	public static @Nonnull String buildBaseName(@Nonnull String name, float level) {
		if (level < 1f) {
			name = EnderIO.lang.localize("loot.capacitor.baselevel.10", name);
		} else if (level < 1.5f) {
			name = EnderIO.lang.localize("loot.capacitor.baselevel.15", name);
		} else if (level < 2.5f) {
			name = EnderIO.lang.localize("loot.capacitor.baselevel.25", name);
		} else if (level < 3.5f) {
			name = EnderIO.lang.localize("loot.capacitor.baselevel.35", name);
		} else {
			name = EnderIO.lang.localize("loot.capacitor.baselevel.45", name);
		}
		return name;
	}

	public static @Nonnull String buildName(@Nonnull String name, float level) {
		if (level < 1f) {
			name = EnderIO.lang.localize("loot.capacitor.level.10", name);
		} else if (level < 1.5f) {
			name = EnderIO.lang.localize("loot.capacitor.level.15", name);
		} else if (level < 2.5f) {
			name = EnderIO.lang.localize("loot.capacitor.level.25", name);
		} else if (level < 3f) {
			name = EnderIO.lang.localize("loot.capacitor.level.30", name);
		} else if (level < 3.5f) {
			name = EnderIO.lang.localize("loot.capacitor.level.35", name);
		} else if (level < 4f) {
			name = EnderIO.lang.localize("loot.capacitor.level.40", name);
		} else if (level < 4.25f) {
			name = EnderIO.lang.localize("loot.capacitor.level.42", name);
		} else {
			name = EnderIO.lang.localize("loot.capacitor.level.45", name);
		}
		return name;
	}

	private float getRandomBaseLevel(@Nonnull Random rand) {
		if (rand.nextFloat() < .3f) {
			return 1f + (rand.nextFloat() - rand.nextFloat()) * .5f;
		} else {
			return 1f + rand.nextFloat() + rand.nextFloat() + rand.nextFloat() + rand.nextFloat();
		}
	}

	/**
	 * Gets a random value that is:
	 * 
	 * <p>
	 * For baselevel==1: Centered around 1.8, spread from 0.8 to 2.8
	 * <p>
	 * For baselevel==2: Centered around 2.7, spread from 1.4 to 3.8
	 * <p>
	 * For baselevel==3: Centered around 3.6, spread from 2.5 to 4.2
	 */
	private static float getRandomLevel(float baseLevel, @Nonnull Random rand) {
		return (getRandomLevel2(baseLevel - .6f, rand) + getRandomLevel2(baseLevel + .5f, rand)) / 2 - .5f;
	}

	private static float getRandomLevel2(float baseLevel, @Nonnull Random rand) {
		float result = baseLevel + rand.nextFloat() * (4 - baseLevel) / 3;
		for (int i = 1; i < 2; i++) {
			result += rand.nextFloat() / i * 2;
			if (result >= baseLevel + 1) {
				result -= rand.nextFloat() / (i + 1);
			}
		}
		return Math.min(result, 4.75f);
	}

	private WeightedUpgrade getUpgrade(@Nonnull Random rand) {
		return WeightedRandom.getRandomItem(rand, WeightedUpgrade.getWeightedupgrades()).getUpgrade();
	}

	private @Nonnull static SetMetadata setMetadata(int metaMin, int metaMax) {
		return new SetMetadata(new LootCondition[0], new RandomValueRange(metaMin, metaMax));
	}

}
