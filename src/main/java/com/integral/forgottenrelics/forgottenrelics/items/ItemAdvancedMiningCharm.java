package com.integral.forgottenrelics.items;

import java.util.List;

import com.integral.forgottenrelics.Main;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;

import baubles.api.BaubleType;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumRarity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StatCollector;
import vazkii.botania.common.Botania;

public class ItemAdvancedMiningCharm extends ItemBaubleBase {

 public void registerRenderers() {}

 public ItemAdvancedMiningCharm() {
	 super("ItemAdvancedMiningCharm");

	 this.maxStackSize = 1;
	 this.setUnlocalizedName("ItemAdvancedMiningCharm");
	 this.setCreativeTab(Main.tabForgottenRelics);

 }


 @Override
 public void registerIcons(IIconRegister iconRegister)
 {
	 itemIcon = iconRegister.registerIcon("forgottenrelics:Advanced_Mining_Charm");
 }


 @Override
 @SideOnly(Side.CLIENT)
 public void addInformation(ItemStack par1ItemStack, EntityPlayer par2EntityPlayer, List par3List, boolean par4)
 {
	 if(GuiScreen.isShiftKeyDown()){
		 par3List.add(StatCollector.translateToLocal("item.ItemAdvancedMiningCharm1_1.lore") + " " + (int) (RelicsConfigHandler.advancedMiningCharmBoost*100F) + StatCollector.translateToLocal("item.ItemAdvancedMiningCharm1_2.lore")); 
		 par3List.add(StatCollector.translateToLocal("item.ItemAdvancedMiningCharm2_1.lore") + " " + RelicsConfigHandler.advancedMiningCharmReach + StatCollector.translateToLocal("item.ItemAdvancedMiningCharm2_2.lore"));
		 par3List.add(StatCollector.translateToLocal("item.FREmpty.lore"));
		 par3List.add(SuperpositionHandler.getBaubleTooltip(this.getBaubleType(par1ItemStack)));
	 }
	 else {
		 par3List.add(StatCollector.translateToLocal("item.FRShiftTooltip.lore")); 
		
	 }
 }


 @Override
 public EnumRarity getRarity(ItemStack itemStack)
 {
 return EnumRarity.epic;
 }

@Override
public BaubleType getBaubleType(ItemStack arg0) {
	return BaubleType.RING;
}

@Override
public void onEquippedOrLoadedIntoWorld(ItemStack stack, EntityLivingBase player) {
	Botania.proxy.setExtraReach((EntityPlayer)player, RelicsConfigHandler.advancedMiningCharmReach);
}

@Override
public void onUnequipped(ItemStack stack, EntityLivingBase player) {
	Botania.proxy.setExtraReach((EntityPlayer)player, -RelicsConfigHandler.advancedMiningCharmReach);
}
 
 
}
