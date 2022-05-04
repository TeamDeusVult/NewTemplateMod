package emyno.techmod.foundation.data;

import com.tterrag.registrate.util.entry.RegistryEntry;
import emyno.techmod.TechMod;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.entity.ItemRenderer;
import net.minecraft.client.resources.model.BakedModel;
import net.minecraft.core.NonNullList;
import net.minecraft.world.item.*;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Collection;
import java.util.EnumSet;
import java.util.stream.Collectors;

public abstract class TechModItemGroupBase extends CreativeModeTab
{
    public TechModItemGroupBase(String id) {
        super(getGroupCountSafe(), TechMod.ID + "." + id);

        TechMod.registrate().addLang("itemGroup." + TechMod.ID + ".base", TechMod.NAME);
    }

    @Override
    @OnlyIn(Dist.CLIENT)
    public void fillItemList(NonNullList<ItemStack> items) {
        addItems(items, true);
        addBlocks(items);
        addItems(items, false);
    }

    @OnlyIn(Dist.CLIENT)
    public void addBlocks(NonNullList<ItemStack> items) {
        for (RegistryEntry<? extends Block> entry : getBlocks()) {
            Block def = entry.get();
            Item item = def.asItem();
            if (item != Items.AIR)
                def.fillItemCategory(this, items);
        }
    }

    @OnlyIn(Dist.CLIENT)
    public void addItems(NonNullList<ItemStack> items, boolean specialItems) {
        Minecraft mc = Minecraft.getInstance();
        ItemRenderer itemRenderer = mc.getItemRenderer();
        ClientLevel world = mc.level;

        for (RegistryEntry<? extends Item> entry : getItems()) {
            Item item = entry.get();
            if (item instanceof BlockItem)
                continue;
            ItemStack stack = new ItemStack(item);
            BakedModel model = itemRenderer.getModel(stack, world, null, 0);
            if (model.isGui3d() != specialItems)
                continue;
            item.fillItemCategory(this, items);
        }
    }

    protected Collection<RegistryEntry<Block>> getBlocks() {
        return TechMod.registrate().getAll(Block.class);
    }

    protected Collection<RegistryEntry<Item>> getItems() {
        return TechMod.registrate().getAll(Item.class);
    }
}