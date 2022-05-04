package emyno.techmod.foundation.data;

import emyno.techmod.foundation.AllBlocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

public class TechModItemGroup extends TechModItemGroupBase
{
    public TechModItemGroup() {
        super("base");
    }

    @Override
    public ItemStack makeIcon() {
        return AllBlocks.COOL_STONE.get().asItem().getDefaultInstance();
    }
}