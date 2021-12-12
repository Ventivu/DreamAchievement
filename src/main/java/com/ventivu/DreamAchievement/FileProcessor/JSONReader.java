package com.ventivu.DreamAchievement.FileProcessor;

import com.google.gson.Gson;
import com.ventivu.DreamAchievement.DreamAchievement;
import com.ventivu.core.MagCore;
import com.ventivu.core.Utils.jsonUtil;
import net.minecraftforge.common.AchievementPage;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

import static com.ventivu.DreamAchievement.EventSys.EventActuator.wakeup;

public class JSONReader {
    private static final String Location_Folder = MagCore.PATH + DreamAchievement.MODNAME;
    private static File folder = new File(Location_Folder);
    static Gson gson = new Gson();

    public JSONReader() {
        if (creatFloder()) {
            makeByFiles(true);
        }
    }

    public static void reload() {
        Storage.rollBack();
        wakeup.clear();
        if (creatFloder()) {
            makeByFiles(false);
        }
    }

    private static boolean creatFloder() {
        return folder.isDirectory() || folder.mkdirs();
    }

    private static void makeByFiles(Boolean firstMake) {
        int count;
        String[] list = folder.list();
        if (list==null||list.length == 0) {
            buildExample();
            makeByFiles(firstMake);
            return;
        }
        if (firstMake) {
            for (String i : list) {
                i = i.split(".json")[0];
                count = creatPages(i);
            }
        }
        Storage.first=firstMake;
        for (File file : folder.listFiles()) {
            String inf = jsonUtil.toString(file);
            JsonMoudel model = gson.fromJson(inf, JsonMoudel.class);
            model.active(file.getName().split(".json")[0]);

        }
    }

    public static int creatPages(String Name) {
        AchievementPage Page = new AchievementPage(Name);
        AchievementPage.registerAchievementPage(Page);
        if (Storage.Page.containsKey(Name)) return Storage.Page.keySet().size();
        Storage.Page.put(Name, Page);
        return Storage.Page.keySet().size();
    }

    public static void buildExample() {
        File example = new File(Location_Folder + "\\example.json");
        JsonMoudel model = new JsonMoudel();
        Achieve a = new Achieve();
        model.Set.add(a);
        try {
            example.createNewFile();
            FileOutputStream s = new FileOutputStream(example);
            OutputStreamWriter w = new OutputStreamWriter(s, StandardCharsets.UTF_8);
            w.append(gson.toJson(model));
            w.close();
            s.close();
            System.out.println("没有检测到资源,已创建示例文件");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
