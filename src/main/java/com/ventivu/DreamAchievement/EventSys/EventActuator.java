package com.ventivu.DreamAchievement.EventSys;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerWakeUpEvent;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class EventActuator {
    public static Map<Item, Event> wakeup = new HashMap<>();

    public EventActuator() {
        MinecraftForge.EVENT_BUS.register(this);
    }

    @SubscribeEvent
    public void PlayerWakeUpEvent(PlayerWakeUpEvent event) {
        EntityPlayer player = event.entityPlayer;
        ItemStack[] Inv = player.inventory.mainInventory;
        player.addChatComponentMessage(new ChatComponentText(Arrays.toString(Inv)));
        for (ItemStack stack : Inv) {
            Event target=stack==null?null:wakeup.get(stack.getItem());
            if(target==null)continue;
            target.ActiveThis(player);
        }
    }
}
