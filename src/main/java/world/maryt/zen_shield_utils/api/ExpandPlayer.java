package world.maryt.zen_shield_utils.api;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.player.IPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import stanhebben.zenscript.annotations.ZenExpansion;
import stanhebben.zenscript.annotations.ZenGetter;
import stanhebben.zenscript.annotations.ZenMethod;
import stanhebben.zenscript.annotations.ZenSetter;
import static world.maryt.zen_shield_utils.ZenShieldUtils.LOGGER;

@ZenExpansion("crafttweaker.player.IPlayer")
@ZenRegister
public class ExpandPlayer {

    @ZenMethod
    @ZenGetter("absorptionAmount")
    public static float getAbsorptionAmount(IPlayer player) {
        return ((EntityPlayer) player.getInternal()).getAbsorptionAmount();
    }

    @ZenMethod
    @ZenSetter("absorptionAmount")
    public static void setAbsorptionAmount(IPlayer player, float amount) {
        if (amount < 0.0f) {
            CraftTweakerAPI.logError("Absorption amount cannot be less than zero!");
            return;
        }
        ((EntityPlayer) player.getInternal()).setAbsorptionAmount(amount);
    }

    @ZenMethod
    public static void clearAbsorption(IPlayer player) {
        EntityPlayer playerEntity = (EntityPlayer) player.getInternal();
        playerEntity.setAbsorptionAmount(0.0f);
        // After that, vanilla Absorption buff has no use.
        if (playerEntity.isPotionActive(MobEffects.ABSORPTION)) {
            playerEntity.removePotionEffect(MobEffects.ABSORPTION);
            LOGGER.info("Cleared Absorption amount and Absorption effect.");
        }
    }
}
