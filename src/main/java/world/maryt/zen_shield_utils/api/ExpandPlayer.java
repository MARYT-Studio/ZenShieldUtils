package world.maryt.zen_shield_utils.api;

import crafttweaker.CraftTweakerAPI;
import crafttweaker.annotations.ZenRegister;
import crafttweaker.api.player.IPlayer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.PotionEffect;
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
        // It cannot be removed easily, so let user know by printing a log.
        for (PotionEffect effect : playerEntity.getActivePotionEffects()) {
            if (effect.getEffectName().equals("effect.absorption")) {
                LOGGER.info("Cleared Absorption amount, but useless Absorption potion effect still remains.");
            }
        }
    }
}
