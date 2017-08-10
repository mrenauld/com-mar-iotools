package com.mar.iotools.configuration;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.mar.framework.core.utils.ObjectUtils;
import com.mar.iotools.read.Read;

public class Settings {

    public static final String SEPARATOR = ",";

    private static Settings uniqueInstance = null;

    private static ISettingsDefault settingsDefault = null;

    private HashMap<String, String> settingMap = new HashMap<String, String>();

    private Settings() {

    }

    public static Settings getUniqueInstance() {
        if (uniqueInstance == null) {
            uniqueInstance = new Settings();
        }
        return uniqueInstance;
    }

    public String getSetting(String pName) {
        String value = settingMap.get(pName);
        if (ObjectUtils.isObjectEmpty(value)) {
            value = settingsDefault.getDefaultValue(pName);
        }
        if (!ObjectUtils.isObjectEmpty(value)) {
            value = value.trim();
        }
        return value;
    }

    public boolean getSettingAsBoolean(String pName) {
        String value = getSetting(pName);
        return Boolean.valueOf(value);
    }

    public int getSettingAsInt(String pName) {
        String value = getSetting(pName);
        return Integer.parseInt(value);
    }

    public List<String> getSettingAsList(String pName) {
        String value = getSetting(pName);
        String[] split = value.split(SEPARATOR);
        ArrayList<String> list = new ArrayList<String>(split.length);
        for (int i = 0; i < split.length; ++i) {
            list.add(split[i]);
        }
        return list;
    }

    public void loadSetting(String pPath) {
        String[] text = Read.readText(pPath);
        settingMap = new HashMap<String, String>();
        for (int i = 0; i < text.length; ++i) {
            if (!ObjectUtils.isObjectEmpty(text[i])) {
                String[] split = text[i].split("=");
                if (split.length > 1) {
                    String key = split[0];
                    StringBuilder value = new StringBuilder();
                    for (int j = 1; j < split.length; ++j) {
                        value.append(split[j]);
                        if (j < split.length - 1) {
                            value.append("=");
                        }
                    }
                    settingMap.put(key, value.toString());
                }
            }
        }
    }

}
