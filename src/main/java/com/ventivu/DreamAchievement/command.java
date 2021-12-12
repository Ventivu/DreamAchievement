package com.ventivu.DreamAchievement;

import com.ventivu.DreamAchievement.FileProcessor.JSONReader;
import com.ventivu.core.Core.Commands;

public class command extends Commands {
    public static void sendCommand(){
        Commands.addVersionMessage(DreamAchievement.MODNAME,DreamAchievement.VERSION);
        Commands.addReloadControl(DreamAchievement.MODNAME, JSONReader.class);
    }
}
