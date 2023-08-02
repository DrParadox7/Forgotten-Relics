package com.integral.forgottenrelics;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.integral.forgottenrelics.entities.EntityBabylonWeaponSS;
import com.integral.forgottenrelics.entities.EntityChaoticOrb;
import com.integral.forgottenrelics.entities.EntityCrimsonOrb;
import com.integral.forgottenrelics.entities.EntityDarkMatterOrb;
import com.integral.forgottenrelics.entities.EntityLunarFlare;
import com.integral.forgottenrelics.entities.EntityRageousMissile;
import com.integral.forgottenrelics.entities.EntityShinyEnergy;
import com.integral.forgottenrelics.entities.EntitySoulEnergy;
import com.integral.forgottenrelics.entities.EntityThunderpealOrb;
import com.integral.forgottenrelics.handlers.RecipeOblivionStone;
import com.integral.forgottenrelics.handlers.RelicsChunkLoadCallback;
import com.integral.forgottenrelics.handlers.RelicsConfigHandler;
import com.integral.forgottenrelics.handlers.RelicsEventHandler;
import com.integral.forgottenrelics.handlers.RelicsKeybindHandler;
import com.integral.forgottenrelics.handlers.RelicsMaterialHandler;
import com.integral.forgottenrelics.handlers.RelicsUpdateHandler;
import com.integral.forgottenrelics.handlers.SuperpositionHandler;
import com.integral.forgottenrelics.items.ItemAdvancedMiningCharm;
import com.integral.forgottenrelics.items.ItemAncientAegis;
import com.integral.forgottenrelics.items.ItemApotheosis;
import com.integral.forgottenrelics.items.ItemArcanum;
import com.integral.forgottenrelics.items.ItemChaosCore;
import com.integral.forgottenrelics.items.ItemChaosTome;
import com.integral.forgottenrelics.items.ItemCrimsonSpell;
import com.integral.forgottenrelics.items.ItemDarkSunRing;
import com.integral.forgottenrelics.items.ItemDeificAmulet;
import com.integral.forgottenrelics.items.ItemDimensionalMirror;
import com.integral.forgottenrelics.items.ItemDiscordRing;
import com.integral.forgottenrelics.items.ItemDormantArcanum;
import com.integral.forgottenrelics.items.ItemEldritchSpell;
import com.integral.forgottenrelics.items.ItemFalseJustice;
import com.integral.forgottenrelics.items.ItemFateTome;
import com.integral.forgottenrelics.items.ItemGhastlySkull;
import com.integral.forgottenrelics.items.ItemLunarFlares;
import com.integral.forgottenrelics.items.ItemMiningCharm;
import com.integral.forgottenrelics.items.ItemMissileTome;
import com.integral.forgottenrelics.items.ItemObeliskDrainer;
import com.integral.forgottenrelics.items.ItemOblivionAmulet;
import com.integral.forgottenrelics.items.ItemOblivionStone;
import com.integral.forgottenrelics.items.ItemOmegaCore;
import com.integral.forgottenrelics.items.ItemOverthrower;
import com.integral.forgottenrelics.items.ItemParadox;
import com.integral.forgottenrelics.items.ItemShinyStone;
import com.integral.forgottenrelics.items.ItemSoulTome;
import com.integral.forgottenrelics.items.ItemSuperpositionRing;
import com.integral.forgottenrelics.items.ItemTelekinesisTome;
import com.integral.forgottenrelics.items.ItemTeleportationTome;
import com.integral.forgottenrelics.items.ItemTerrorCrown;
import com.integral.forgottenrelics.items.ItemThunderpeal;
import com.integral.forgottenrelics.items.ItemVoidGrimoire;
import com.integral.forgottenrelics.items.ItemWastelayer;
import com.integral.forgottenrelics.items.ItemWeatherStone;
import com.integral.forgottenrelics.items.ItemXPTome;
import com.integral.forgottenrelics.minetweaker.MineTweakerIntegrator;
import com.integral.forgottenrelics.packets.ApotheosisParticleMessage;
import com.integral.forgottenrelics.packets.ArcLightningMessage;
import com.integral.forgottenrelics.packets.BanishmentCastingMessage;
import com.integral.forgottenrelics.packets.BurstMessage;
import com.integral.forgottenrelics.packets.DiscordKeybindMessage;
import com.integral.forgottenrelics.packets.EntityMotionMessage;
import com.integral.forgottenrelics.packets.ForgottenResearchMessage;
import com.integral.forgottenrelics.packets.GuardianVanishMessage;
import com.integral.forgottenrelics.packets.ICanSwingMySwordMessage;
import com.integral.forgottenrelics.packets.InfernalParticleMessage;
import com.integral.forgottenrelics.packets.ItemUseMessage;
import com.integral.forgottenrelics.packets.LightningBoltMessage;
import com.integral.forgottenrelics.packets.LightningMessage;
import com.integral.forgottenrelics.packets.LunarBurstMessage;
import com.integral.forgottenrelics.packets.LunarFlaresParticleMessage;
import com.integral.forgottenrelics.packets.NotificationMessage;
import com.integral.forgottenrelics.packets.OverthrowChatMessage;
import com.integral.forgottenrelics.packets.PacketVoidMessage;
import com.integral.forgottenrelics.packets.PlayerMotionUpdateMessage;
import com.integral.forgottenrelics.packets.PortalTraceMessage;
import com.integral.forgottenrelics.packets.TelekinesisAttackMessage;
import com.integral.forgottenrelics.packets.TelekinesisParticleMessage;
import com.integral.forgottenrelics.packets.TelekinesisUseMessage;
import com.integral.forgottenrelics.proxy.CommonProxy;
import com.integral.forgottenrelics.research.RelicsAspectRegistry;
import com.integral.forgottenrelics.research.RelicsResearchRegistry;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.network.simpleimpl.SimpleNetworkWrapper;
import cpw.mods.fml.common.registry.EntityRegistry;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.common.ForgeChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.oredict.RecipeSorter;
import net.minecraftforge.oredict.RecipeSorter.Category;
import thaumcraft.common.config.Config;

import static com.integral.forgottenrelics.Tags.MODID;


@Mod(modid = MODID, version = Tags.VERSION, name = Tags.MODNAME, acceptedMinecraftVersions = "[1.7.10]", dependencies = "required-after:Thaumcraft@[4.2.3.5,);required-after:Botania@[r1.8-237,)")
public class Main {
	
	public static SimpleNetworkWrapper packetInstance;
	
	@SidedProxy(clientSide= Tags.GROUPNAME + ".proxy.ClientProxy", serverSide=Tags.GROUPNAME + ".proxy.CommonProxy")
	public static CommonProxy proxy;

	@Instance(MODID)
	public static Main instance;
	public static List<String> darkRingDamageNegations = new ArrayList();
	
	/**
	 * Hash Map, containing players' casting cooldowns (used for some spellbooks).
	 * If any value above 0 is put there, it will be decremented by 1 each respective
	 * player's tick, until eventually reaches zero.
	 * Only effective on the server side.
	 */
	public static HashMap<EntityPlayer, Integer> castingCooldowns = new HashMap<EntityPlayer, Integer>();
	
	/**
	 * Hash Map used for containing hidden researches and their respective item triggers.
	 * Behold Superposition Handler to see methods that are used to interact with it.
	 */
	public static HashMap<String, List<ItemStack>> forgottenKnowledge = new HashMap<String, List<ItemStack>>();
	
	public static Item itemFalseJustice;
	public static Item itemDeificAmulet;
	public static Item itemParadox;
	public static Item itemOblivionAmulet;
	public static Item itemArcanum;
	public static Item itemDormantArcanum;
	public static Item itemFateTome;
	public static Item itemDarkSunRing;
	public static Item itemChaosCore;
	public static Item itemMiningCharm;
	public static Item itemAdvancedMiningCharm;
	public static ItemTelekinesisTome itemTelekinesisTome;
	public static Item itemAncientAegis;
	public static Item itemMissileTome;
	public static Item itemCrimsonSpell;
	public static Item itemGhastlySkull;
	public static Item itemObeliskDrainer;
	
	public static Item itemEldritchSpell;
	public static Item itemLunarFlares;
	public static Item itemTeleportationTome;
	public static Item itemShinyStone;
	public static Item itemSuperpositionRing;
	public static Item itemSoulTome;
	
	public static Item itemDimensionalMirror;
	public static Item itemWeatherStone;
	public static Item itemApotheosis;
	public static Item itemChaosTome;
	public static Item itemOmegaCore;
	public static Item itemXPTome;
	
	public static Item itemThunderpeal;
	public static Item itemTerrorCrown;
	public static Item itemWastelayer;
	public static Item itemOverthrower;
	public static Item itemDiscordRing;
	
	public static Item itemVoidGrimoire;
	public static Item itemOblivionStone;
	
	public RelicsConfigHandler configHandler = new RelicsConfigHandler();
	public static final Logger log = LogManager.getLogger("ForgottenRelics");
	
	public static final int howCoolAmI = Integer.MAX_VALUE;

	@EventHandler
	public void load(FMLInitializationEvent event) {
		Main.proxy.registerDisplayInformation();
		
		RecipeSorter.register("forge:oblivionstone", RecipeOblivionStone.class, Category.SHAPELESS,
				"after:forge:shapelessorenbt");
		
		darkRingDamageNegations.add(DamageSource.lava.damageType);
		darkRingDamageNegations.add(DamageSource.inFire.damageType);
		darkRingDamageNegations.add(DamageSource.onFire.damageType);
		
	}

	@EventHandler
	public void serverLoad(FMLServerStartingEvent event) {
		if (Loader.isModLoaded("MineTweaker3")) {
			MineTweakerIntegrator.registerCommands();
		}
	}

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		configHandler.configDisposition(event);
		
		packetInstance = NetworkRegistry.INSTANCE.newSimpleChannel("RelicsChannel");
		packetInstance.registerMessage(PortalTraceMessage.Handler.class, PortalTraceMessage.class, 1, Side.CLIENT);
		packetInstance.registerMessage(BurstMessage.Handler.class, BurstMessage.class, 2, Side.CLIENT);
		packetInstance.registerMessage(ApotheosisParticleMessage.Handler.class, ApotheosisParticleMessage.class, 3, Side.CLIENT);
		packetInstance.registerMessage(LunarBurstMessage.Handler.class, LunarBurstMessage.class, 4, Side.CLIENT);
		packetInstance.registerMessage(LunarFlaresParticleMessage.Handler.class, LunarFlaresParticleMessage.class, 5, Side.CLIENT);
		packetInstance.registerMessage(LightningMessage.Handler.class, LightningMessage.class, 6, Side.CLIENT);
		packetInstance.registerMessage(ArcLightningMessage.Handler.class, ArcLightningMessage.class, 7, Side.CLIENT);
		packetInstance.registerMessage(ICanSwingMySwordMessage.Handler.class, ICanSwingMySwordMessage.class, 8, Side.CLIENT);
		packetInstance.registerMessage(EntityMotionMessage.Handler.class, EntityMotionMessage.class, 9, Side.CLIENT);
		packetInstance.registerMessage(LightningBoltMessage.Handler.class, LightningBoltMessage.class, 10, Side.CLIENT);
		packetInstance.registerMessage(InfernalParticleMessage.Handler.class, InfernalParticleMessage.class, 11, Side.CLIENT);
		packetInstance.registerMessage(ItemUseMessage.Handler.class, ItemUseMessage.class, 12, Side.CLIENT);
		packetInstance.registerMessage(BanishmentCastingMessage.Handler.class, BanishmentCastingMessage.class, 13, Side.CLIENT);
		packetInstance.registerMessage(PlayerMotionUpdateMessage.Handler.class, PlayerMotionUpdateMessage.class, 14, Side.CLIENT);
		packetInstance.registerMessage(NotificationMessage.Handler.class, NotificationMessage.class, 15, Side.CLIENT);
		packetInstance.registerMessage(OverthrowChatMessage.Handler.class, OverthrowChatMessage.class, 16, Side.CLIENT);
		packetInstance.registerMessage(DiscordKeybindMessage.Handler.class, DiscordKeybindMessage.class, 17, Side.SERVER);
		packetInstance.registerMessage(ForgottenResearchMessage.Handler.class, ForgottenResearchMessage.class, 18, Side.CLIENT);
		packetInstance.registerMessage(TelekinesisAttackMessage.Handler.class, TelekinesisAttackMessage.class, 19, Side.SERVER);
		packetInstance.registerMessage(TelekinesisUseMessage.Handler.class, TelekinesisUseMessage.class, 20, Side.SERVER);
		packetInstance.registerMessage(TelekinesisParticleMessage.Handler.class, TelekinesisParticleMessage.class, 21, Side.CLIENT);
		packetInstance.registerMessage(PacketVoidMessage.Handler.class, PacketVoidMessage.class, 22, Side.CLIENT);
		packetInstance.registerMessage(GuardianVanishMessage.Handler.class, GuardianVanishMessage.class, 23, Side.CLIENT);
		
		itemFalseJustice = new ItemFalseJustice();
		itemDeificAmulet = new ItemDeificAmulet();
		itemParadox = new ItemParadox(RelicsMaterialHandler.materialParadoxicalStuff);
		itemOblivionAmulet = new ItemOblivionAmulet();
		itemArcanum = new ItemArcanum();
		itemDormantArcanum = new ItemDormantArcanum();
		itemFateTome = new ItemFateTome();
		itemDarkSunRing = new ItemDarkSunRing();
		itemChaosCore = new ItemChaosCore();
		itemMiningCharm = new ItemMiningCharm();
		itemAdvancedMiningCharm = new ItemAdvancedMiningCharm();
		itemTelekinesisTome = new ItemTelekinesisTome();
		itemAncientAegis = new ItemAncientAegis();
		itemMissileTome = new ItemMissileTome();
		itemCrimsonSpell = new ItemCrimsonSpell();
		itemGhastlySkull = new ItemGhastlySkull();
		itemObeliskDrainer = new ItemObeliskDrainer();
		itemEldritchSpell = new ItemEldritchSpell();
		itemLunarFlares = new ItemLunarFlares();
		itemTeleportationTome = new ItemTeleportationTome();
		itemShinyStone = new ItemShinyStone();
		itemSuperpositionRing = new ItemSuperpositionRing();
		itemSoulTome = new ItemSoulTome();
		itemDimensionalMirror = new ItemDimensionalMirror();
		itemWeatherStone = new ItemWeatherStone();
		itemApotheosis = new ItemApotheosis();
		itemChaosTome = new ItemChaosTome();
		itemOmegaCore = new ItemOmegaCore();
		itemXPTome = new ItemXPTome();
		itemThunderpeal = new ItemThunderpeal();
		itemTerrorCrown = new ItemTerrorCrown(0, RelicsMaterialHandler.materialNobleGold);
		itemWastelayer = new ItemWastelayer(RelicsMaterialHandler.materialParadoxicalStuff);
		itemOverthrower = new ItemOverthrower();
		itemDiscordRing = new ItemDiscordRing();
		itemVoidGrimoire = new ItemVoidGrimoire();
		itemOblivionStone = new ItemOblivionStone();
		
		GameRegistry.registerItem(itemFalseJustice, "ItemFalseJustice");
		GameRegistry.registerItem(itemDeificAmulet, "ItemDeificAmulet");
		GameRegistry.registerItem(itemParadox, "ItemParadox");
		GameRegistry.registerItem(itemOblivionAmulet, "ItemOblivionAmulet");
		GameRegistry.registerItem(itemArcanum, "ItemArcanum");
		GameRegistry.registerItem(itemDormantArcanum, "ItemDormantArcanum");
		GameRegistry.registerItem(itemFateTome, "ItemFateTome");
		GameRegistry.registerItem(itemDarkSunRing, "ItemDarkSunRing");
		GameRegistry.registerItem(itemChaosCore, "ItemChaosCore");
		GameRegistry.registerItem(itemMiningCharm, "ItemMiningCharm");
		GameRegistry.registerItem(itemAdvancedMiningCharm, "ItemAdvancedMiningCharm");
		GameRegistry.registerItem(itemTelekinesisTome, "ItemTelekinesisTome");
		GameRegistry.registerItem(itemAncientAegis, "ItemAncientAegis");
		GameRegistry.registerItem(itemMissileTome, "ItemMissileTome");
		GameRegistry.registerItem(itemCrimsonSpell, "ItemCrimsonSpell");
		GameRegistry.registerItem(itemGhastlySkull, "itemGhastlySkull");
		GameRegistry.registerItem(itemObeliskDrainer, "ItemObeliskDrainer");
		GameRegistry.registerItem(itemEldritchSpell, "ItemEldritchSpell");
		GameRegistry.registerItem(itemLunarFlares, "ItemLunarFlares");
		GameRegistry.registerItem(itemTeleportationTome, "ItemTeleportationTome");
		GameRegistry.registerItem(itemShinyStone, "ItemShinyStone");
		GameRegistry.registerItem(itemSuperpositionRing, "ItemSuperpositionRing");
		GameRegistry.registerItem(itemSoulTome, "ItemSoulTome");
		GameRegistry.registerItem(itemDimensionalMirror, "ItemDimensionalMirror");
		GameRegistry.registerItem(itemWeatherStone, "ItemWeatherStone");
		GameRegistry.registerItem(itemApotheosis, "ItemApotheosis");
		GameRegistry.registerItem(itemChaosTome, "ItemChaosTome");
		GameRegistry.registerItem(itemOmegaCore, "ItemOmegaCore");
		GameRegistry.registerItem(itemXPTome, "ItemXPTome");
		GameRegistry.registerItem(itemThunderpeal, "ItemThunderpeal");
		GameRegistry.registerItem(itemTerrorCrown, "ItemTerrorCrown");
		GameRegistry.registerItem(itemOverthrower, "ItemOverthrower");
		GameRegistry.registerItem(itemDiscordRing, "ItemDiscordRing");
		GameRegistry.registerItem(itemVoidGrimoire, "ItemVoidGrimoire");
		GameRegistry.registerItem(itemOblivionStone, "ItemOblivionStone");
		
		EntityRegistry.registerModEntity(EntityRageousMissile.class, "SomeVeryRageousStuff", 237, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityCrimsonOrb.class, "EntityCrimsonOrb", 238, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityDarkMatterOrb.class, "EntityDarkMatterOrb", 239, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntitySoulEnergy.class, "EntitySoulEnergy", 240, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityLunarFlare.class, "EntityLunarFlare", 241, Main.instance, 196, 20, true);
		EntityRegistry.registerModEntity(EntityShinyEnergy.class, "EntityShinyEnergy", 242, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityBabylonWeaponSS.class, "EntityBabylonWeaponSS", 243, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityChaoticOrb.class, "EntityChaoticOrb", 245, Main.instance, 64, 20, true);
		EntityRegistry.registerModEntity(EntityThunderpealOrb.class, "EntityThunderpealOrb", 246, Main.instance, 64, 20, true);
		
		RelicsAspectRegistry.registerItemAspectsFirst();
		
		proxy.registerKeybinds();
		
		MinecraftForge.EVENT_BUS.register(new RelicsUpdateHandler());
		
		FMLCommonHandler.instance().bus().register(new RelicsKeybindHandler());
		MinecraftForge.EVENT_BUS.register(new RelicsEventHandler());
		
		proxy.registerRenderers(this);
		
	}
	
	@EventHandler
	public static void postLoad(FMLPostInitializationEvent event) {
		GameRegistry.addRecipe(new RecipeOblivionStone());
		
		RelicsResearchRegistry.integrateInfusion();
		RelicsAspectRegistry.registerItemAspectsLast();
		RelicsResearchRegistry.integrateResearch();
		
		log.info("Forgotten Knowledge list:");
		for (String key : Main.forgottenKnowledge.keySet())
			log.info(" - " + key);
		
		if (RelicsConfigHandler.forgottenKnowledgeOverridingEnabled)
		SuperpositionHandler.setupOverrides();
		
		Config.shieldRecharge = RelicsConfigHandler.runicRechargeSpeed;
		Config.shieldWait = RelicsConfigHandler.runicRechargeDelay;
		Config.shieldCost = RelicsConfigHandler.runicCost;
		Config.notificationDelay = RelicsConfigHandler.notificationDelay;
		
		if (Loader.isModLoaded("MineTweaker3")) {
			MineTweakerIntegrator.init();
		}
		
		ForgeChunkManager.setForcedChunkLoadingCallback(instance, new RelicsChunkLoadCallback());
		
		RelicsUpdateHandler.init();
	}
	
	
	public static CreativeTabs tabForgottenRelics = new CreativeTabs("tabForgottenRelics") {
		@SideOnly(Side.CLIENT)
		public Item getTabIconItem() {
			return itemApotheosis;
		}
	};
	

}
