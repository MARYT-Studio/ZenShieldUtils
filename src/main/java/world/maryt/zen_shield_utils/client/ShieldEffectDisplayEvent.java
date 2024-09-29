/*
* Borrowed from:
* https://github.com/defeatedcrow/HeatAndClimateLib/blob/1.12.2_v3/main/java/defeatedcrow/hac/core/client/event/RenderTempHUDEvent.java
* Thanks original mod author @defeatedcrow.
* Code License: https://github.com/defeatedcrow/HeatAndClimateLib?tab=readme-ov-file#%E3%83%A9%E3%82%A4%E3%82%BB%E3%83%B3%E3%82%B9-licenses
* */

package world.maryt.zen_shield_utils.client;


import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.world.World;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent.ElementType;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import world.maryt.zen_shield_utils.ZenShieldUtils;

@SideOnly(Side.CLIENT)
public class ShieldEffectDisplayEvent {

    @SubscribeEvent
    public void doRender(RenderGameOverlayEvent.Post event) {
        if (event.getType() != null && event.getType() == ElementType.ALL) {
            EntityPlayer player = AbsorptionStatusHelper.INSTANCE.getPlayer();
            World world = AbsorptionStatusHelper.INSTANCE.getClientWorld();
            GuiScreen gui = Minecraft.getMinecraft().currentScreen;
            if (player != null && world != null && gui == null && !player.isSpectator()) {
                // Condition of displaying HUD
                if (AbsorptionStatusHelper.INSTANCE.updateAbsorptionStatus()) {

                    int sizeX = event.getResolution().getScaledWidth();
                    int sizeY = event.getResolution().getScaledHeight();


                    GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
                            GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
                            GlStateManager.DestFactor.ZERO);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 0.3F);
                    Minecraft.getMinecraft().getTextureManager().bindTexture(new ResourceLocation(ZenShieldUtils.MOD_ID,"textures/gui/hud.png"));
                    drawDisplayTexture(0, 0, sizeX, sizeY);

                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);

                }
            }
        }
    }

    public void drawDisplayTexture(int x, int y, int width, int height) {
        float f = 0.00390625F * 256;
        Tessellator tessellator = Tessellator.getInstance();
        BufferBuilder vertexBuffer = tessellator.getBuffer();
        vertexBuffer.begin(7, DefaultVertexFormats.POSITION_TEX);
        vertexBuffer.pos(x, y + height, -90.0F).tex(0, f).endVertex();
        vertexBuffer.pos(x + width, y + height, -90.0F).tex(f, f).endVertex();
        vertexBuffer.pos(x + width, y, -90.0F).tex(f, 0).endVertex();
        vertexBuffer.pos(x, y, -90.0F).tex(0, 0).endVertex();
        tessellator.draw();
    }

}