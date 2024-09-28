package world.maryt.zen_shield_utils;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import world.maryt.zen_shield_utils.client.ShieldEffectDisplayEvent;

@Mod(modid = ZenShieldUtils.MOD_ID, name = ZenShieldUtils.MOD_NAME, version = ZenShieldUtils.VERSION, dependencies = ZenShieldUtils.DEPENDENCIES)
public class ZenShieldUtils {
    public static final String MOD_ID = Tags.MOD_ID;
    public static final String MOD_NAME = Tags.MOD_NAME;
    public static final String VERSION = Tags.VERSION;
    public static final String DEPENDENCIES = "required-after:crafttweaker;required-after:mixinbooter";

    public static final Logger LOGGER = LogManager.getLogger(MOD_NAME);

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(new ShieldEffectDisplayEvent());
    }
}
