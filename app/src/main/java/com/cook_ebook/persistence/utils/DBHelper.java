package com.cook_ebook.persistence.utils;

import android.content.Context;
import android.content.res.AssetManager;

import com.cook_ebook.application.Main;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;


public class DBHelper {

    private static final String DB_PATH = "db";

    public static void copyDatabaseToDevice(Context context) {

        String[] assetNames;
        File dataDirectory = context.getDir(DB_PATH, context.MODE_PRIVATE);
        AssetManager assetManager = context.getAssets();

        try {

            assetNames = assetManager.list(DB_PATH);
            for (int i = 0; i < assetNames.length; i++) {
                assetNames[i] = DB_PATH + "/" + assetNames[i];
            }

            copyAssetsToDirectory(context, assetNames, dataDirectory);

            Main.setDBPathName(dataDirectory.toString() + "/" + Main.getDBPathName());

        } catch (IOException e) {
            System.out.println("[ERROR]: could not access application data");
            e.printStackTrace();
        }
    }

    private static void copyAssetsToDirectory(Context context, String[] assets, File directory) throws  IOException {
        AssetManager assetManager = context.getAssets();

        for (String asset : assets) {
            String[] components = asset.split("/");
            String copyPath = directory.toString() + "/" + components[components.length - 1];

            char[] buffer = new char[1024];

            int count;

            File outFile = new File(copyPath);

            if (!outFile.exists()) {

                InputStreamReader inputStreamReader = new InputStreamReader(assetManager.open(asset));
                FileWriter fileWriter = new FileWriter(outFile);

                count = inputStreamReader.read();
                while (count != -1) {
                    fileWriter.write(buffer, 0, count);
                    count = inputStreamReader.read();
                }

                fileWriter.close();
                inputStreamReader.close();

            }

        }
    }

}
