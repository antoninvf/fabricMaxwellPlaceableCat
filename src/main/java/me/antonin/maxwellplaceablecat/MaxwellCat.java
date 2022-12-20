package me.antonin.maxwellplaceablecat;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MaxwellCat implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("MaxwellThePlaceableCat");

	public static final MaxwellCatBlock MAXWELL_CAT = new MaxwellCatBlock(FabricBlockSettings
			.of(Material.WOOL)
			.sounds(BlockSoundGroup.WOOL)
			.hardness(0.8f)
			.resistance(0.8f)
			.nonOpaque()
	);

	@Override
	public void onInitialize() {
		LOGGER.info("Maxwell says meow! 😸");

		BlockRenderLayerMap.INSTANCE.putBlock(MAXWELL_CAT, RenderLayer.getCutout());
		Registry.register(Registry.BLOCK, new Identifier("maxwellplaceablecat", "maxwell"), MAXWELL_CAT);
		Registry.register(Registry.ITEM, new Identifier("maxwellplaceablecat", "maxwell"), new BlockItem(MAXWELL_CAT, new Item.Settings().group(ItemGroup.MISC)));
	}
}
