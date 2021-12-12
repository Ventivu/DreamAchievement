package com.ventivu.DreamAchievement;


import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

@Mod(modid = DreamAchievement.MODID,name =DreamAchievement.MODNAME,version = DreamAchievement.VERSION,dependencies ="required-before:magcore" )
public class DreamAchievement {
    public static final String MODID = "DreamAchievement";
    public static final String MODNAME = MODID;
    public static final String VERSION = "0.0.1";
    
    @Mod.Instance(DreamAchievement.MODID)
    public static DreamAchievement instance = new DreamAchievement();

    @SidedProxy(serverSide = "com.ventivu.DreamAchievement.CommonProxy",clientSide = "com.ventivu.DreamAchievement.CommonProxy")
    public static CommonProxy proxy;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        proxy.init(event);
    }

    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        proxy.serverStarting(event);
    }

}
