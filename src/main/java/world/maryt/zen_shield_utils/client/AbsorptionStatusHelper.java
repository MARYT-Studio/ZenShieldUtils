package world.maryt.zen_shield_utils.client;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;


@SideOnly(Side.CLIENT)
public class AbsorptionStatusHelper {

    private AbsorptionStatusHelper() {}

    public static final AbsorptionStatusHelper INSTANCE = new AbsorptionStatusHelper();

    private static boolean absorptionStatus = false;

    public void init() {
        MinecraftForge.EVENT_BUS.register(new ShieldEffectDisplayEvent());
    }

    public EntityPlayer getPlayer() {
        return Minecraft.getMinecraft().player;
    }

    public World getClientWorld() {
        return Minecraft.getMinecraft().world;
    }

    public boolean updateAbsorptionStatus() {
        return getAbsorptionStatus(getPlayer());
    }

    public boolean getAbsorptionStatus(EntityPlayer player) {
        if (player.getAbsorptionAmount() > 0.0f) {
            if (!absorptionStatus) {
                absorptionStatus = true;
            }
        } else {
            if (absorptionStatus) {
                absorptionStatus = false;
            }
        }
        return absorptionStatus;
    }
}