package emyno.techmod.foundation;

import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.builders.BlockBuilder;
import com.tterrag.registrate.builders.ItemBuilder;
import com.tterrag.registrate.providers.loot.RegistrateBlockLootTables;
import com.tterrag.registrate.util.entry.BlockEntry;
import com.tterrag.registrate.util.nullness.NonNullFunction;
import emyno.techmod.TechMod;
import emyno.techmod.content.blocks.CoolStone;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

import static emyno.techmod.foundation.data.RegistrateHelpers.*;

public class AllBlocks
{
    // Initialize this class
    public static void register() {}

    private static final Registrate REGISTRATE = TechMod.registrate().creativeModeTab(() -> TechMod.BASE_CREATIVE_TAB);


    public static final BlockEntry<CoolStone> COOL_STONE = REGISTRATE.block("cool_stone", CoolStone::new)
            .initialProperties(() -> Blocks.STONE)
            .properties(p -> p.requiresCorrectToolForDrops().sound(SoundType.STONE))
            .transform(pickaxeOnly())
            .loot((lt, b) -> lt.add(b,
                    RegistrateBlockLootTables.createSilkTouchDispatchTable(b,
                            RegistrateBlockLootTables.applyExplosionDecay(b, LootItem.lootTableItem(Items.DIAMOND)
                                    .apply(ApplyBonusCount.addOreBonusCount(Enchantments.BLOCK_FORTUNE))))))
            .tag(BlockTags.NEEDS_IRON_TOOL)
            .tag(Tags.Blocks.STONE)
            // register item for this block
            .transform(tagBlockAndItem("ores/cool_stone", "ores_in_ground/cool_stone"))
            .tag(Tags.Items.ORES)
            .build()
            .register();
}


