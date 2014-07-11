package com.mcbeyondreality.beyondrealitycore;

import com.mcbeyondreality.beyondrealitycore.handlers.BeyondRealityCoreEvent;
import com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;


@Mod(name = "Beyond Reality Core", modid = "beyondrealitycore", version = "1.3")


public class BeyondRealityCore {
	
	@Instance("beyondrealitycore")
	public static BeyondRealityCore instance;
	
	@SidedProxy( clientSide="com.mcbeyondreality.beyondrealitycore.proxy.ClientProxy", serverSide="com.mcbeyondreality.beyondrealitycore.proxy.CommonProxy")
	public static CommonProxy proxy;
	
	public static String[] bannedEnderBlocks;
	public static int aggroRange, numSilverfish, perSilverfish;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event){	
		Configuration config = new Configuration(event.getSuggestedConfigurationFile());
		config.load();
		
		bannedEnderBlocks = config.get("End Settings", "Blocks the Endermen Don't want you to take", new String[] {"tile.whiteStone"}).getStringList();
		aggroRange = config.get("End Settings", "Enderman Range for block breaks", 16).getInt();
		
		perSilverfish = config.get("Stone Breaking", "Chance of silverfish spawning when breaking stone (0-100)", 5).getInt();
		numSilverfish = config.get("Stone Breaking", "Max # of silverfish to spawn", 2).getInt();
		
		config.save();

		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreEvent());

//		if (event.getSide() == Side.CLIENT)
//        {
//		MinecraftForge.EVENT_BUS.register(new BeyondRealityCoreCapeEvent());
//        }
	}
	
	@EventHandler
	public void init(FMLInitializationEvent event) {
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {}

}