package com.ventivu.DreamAchievement.FileProcessor;

import net.minecraft.stats.Achievement;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatList;
import net.minecraftforge.common.AchievementPage;

import java.util.*;

public class Storage {
    static List<Achievement> saves = new ArrayList<>();
    static Map<String, AchievementPage> Page = new HashMap<>();
    static boolean first = true;

    public static AchievementPage addAchieveToPage(Achievement achieve, String key, boolean isSpecial) {
        if (first) {
            if (key == null || Page.containsKey(key)) {
                AchievementPage page = Page.get(key);
                saves.add(achieve);
                page.getAchievements().add(achieve);
                if (isSpecial) achieve.setSpecial();
                achieve.registerStat();
                return page;
            }
            return null;
        }
        return ReAddToPage(achieve, key, isSpecial);
    }

    public static AchievementPage ReAddToPage(Achievement achieve, String key, boolean isSpecial) {
        if (key == null || Page.containsKey(key)) {
            AchievementPage page = Page.get(key);
            saves.add(achieve);
            page.getAchievements().add(achieve);
            if (isSpecial) achieve.setSpecial();
            AchievementList.achievementList.add(achieve);
            StatList.allStats.add(achieve);
            return page;
        }
        return null;
    }

    public static void rollBack() {
        for (Achievement temp : saves) {
            StatList.allStats.remove(temp);
        }
        saves.removeIf(temp -> AchievementList.achievementList.remove(temp));
        for (String a : Page.keySet()) {
            Page.get(a).getAchievements().clear();
        }
    }

    public static Achievement getAchievement(String key){
        for(Achievement ac:saves){
            if(Objects.equals(ac.statId, key))return ac;
        }
        return null;
    }
}
