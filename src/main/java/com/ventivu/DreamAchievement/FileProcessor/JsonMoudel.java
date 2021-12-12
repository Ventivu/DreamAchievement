package com.ventivu.DreamAchievement.FileProcessor;

import net.minecraft.item.Item;
import net.minecraft.stats.Achievement;

import java.util.ArrayList;
import java.util.List;

import static com.ventivu.DreamAchievement.EventSys.Eventstrcutor.newEvent;

public class JsonMoudel {
    public List<Achieve>Set=new ArrayList<>();

    public boolean active(String key){
        for(Achieve s:Set){
            Achievement ach=new Achievement(s.innerKey,s.DisplayKey,s.Posx,s.PosY, (Item)Item.itemRegistry.getObject(s.DisplayItem), null);
            if(Storage.addAchieveToPage(ach,key,s.isSpecial)==null){
                System.out.println("key:"+key+"不存在,请重启刷新");
                return false;
            }
            newEvent(s.eventTrace,s.EventCondition,s.DisplayMessage,s.innerKey);
        }
        return true;
    }
}
class Achieve{
    String innerKey="example";
    String DisplayKey="example";
    boolean isSpecial=false;
    int Posx=0,PosY=0;
    String DisplayItem="minecraft:apple";
    String eventTrace="WakeUp";
    String EventCondition="minecraft:apple";
    String DisplayMessage="苹果触发事件";
}