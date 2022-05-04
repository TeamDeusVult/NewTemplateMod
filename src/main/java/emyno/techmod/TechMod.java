package emyno.techmod;

import com.mojang.logging.LogUtils;
import com.tterrag.registrate.Registrate;
import com.tterrag.registrate.util.nullness.NonNullSupplier;
import emyno.techmod.foundation.AllBlocks;
import emyno.techmod.foundation.data.TechModItemGroup;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import net.minecraftforge.fml.event.lifecycle.InterModProcessEvent;
import net.minecraftforge.event.server.ServerStartingEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

import java.util.stream.Collectors;


// The value here should match an entry in the META-INF/mods.toml file
@Mod("techmod")
public class TechMod
{
    public static final String ID = "techmod";
    public static final String NAME = "Tech Mod";
    public static final String VERSION = "1.0.0";

    // Directly reference a slf4j logger
    private static final Logger LOGGER = LogUtils.getLogger();
    public static final CreativeModeTab BASE_CREATIVE_TAB = new TechModItemGroup();
    private static final NonNullSupplier<Registrate> REGISTRATE = NonNullSupplier.lazy(() -> Registrate.create(ID));

    public static Registrate registrate() { return REGISTRATE.get(); }
    public static ResourceLocation asResource(String path) { return new ResourceLocation(ID, path); }

    // run static initialization
    public TechMod() { onCtor(); }

    public static void onCtor() {
        ModLoadingContext modLoadingContext = ModLoadingContext.get();

        AllBlocks.register();

        //AllHexConfigs.register(modLoadingContext);

        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        IEventBus forgeEventBus = MinecraftForge.EVENT_BUS;

        modEventBus.addListener(TechMod::init);

        //modEventBus.addGenericListener(Feature.class, AllHexWorldFeatures::registerOreFeatures);
    }

    public static void init(final FMLCommonSetupEvent event) {
//        event.enqueueWork(() -> {
//            AllHexWorldFeatures.registerFeatures();
//            AllHexWorldFeatures.registerPlacementTypes();
//        });
    }
}
