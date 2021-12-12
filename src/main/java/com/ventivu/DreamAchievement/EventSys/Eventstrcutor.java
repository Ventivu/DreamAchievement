package com.ventivu.DreamAchievement.EventSys;

import com.ventivu.DreamAchievement.FileProcessor.Storage;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;

enum EventList {WakeUp}

public class Eventstrcutor {
    private static EventList choosed = EventList.WakeUp;

    public static void newEvent(String EventName, String ConditionItem, String display, String innerkey) {
        try {
            choosed = EventList.valueOf(EventName);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        switch (choosed) {
            case WakeUp:
                EventActuator.wakeup.put((Item) Item.itemRegistry.getObject(ConditionItem), new Event(innerkey, display));
                break;
            default:
        }
    }


}

class Event {
    String key;
    String display;

    public Event(String key, String display) {
        this.key = key;
        this.display = StatCollector.translateToLocal(display);
    }

    public boolean ActiveThis(EntityPlayer player) {
        player.addChatComponentMessage(new ChatComponentText(display));
        Achievement ac = Storage.getAchievement(key);
        if (ac == null) return false;
        player.triggerAchievement(ac);
        return true;
    }
}