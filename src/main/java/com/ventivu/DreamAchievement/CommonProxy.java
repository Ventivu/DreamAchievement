package com.ventivu.DreamAchievement;

import com.ventivu.DreamAchievement.EventSys.EventActuator;
import com.ventivu.DreamAchievement.FileProcessor.JSONReader;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class CommonProxy {
    @Mod.EventHandler
    public void serverStarting(FMLServerStartingEvent event) {
        command.sendCommand();
    }

    public void init(FMLInitializationEvent event) {
        new EventActuator();
        new JSONReader();
    }
}
