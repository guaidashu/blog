package cn.main.config;

import java.util.ArrayList;

@SuppressWarnings("FieldCanBeLocal")
public class Config {
    private String[] menu;
    private int num = 12;
    private static String[] preMenu;
    private static int preNum = 5;

    public String[] getMenu() {
        this.menu = new String[num];
        for (int i = 0; i < num; i++) {
            this.menu[i] = null;
        }
        return this.menu;
    }

    public static String[] getPreMenu() {
        preMenu = new String[preNum];
        for (int i = 0; i < preNum; i++) {
            preMenu[i] = null;
        }
        return preMenu;
    }

    public static ArrayList<String> getAllowImageType() {
        // 设置允许通过的文件类型
        ArrayList<String> fileType = new ArrayList<String>();
        fileType.add("jpg");
        fileType.add("png");
        fileType.add("gif");
        fileType.add("JPEG");
        fileType.add("jpeg");
        return fileType;
    }

}
