package org.WdtcLauncher.GameSet;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import org.WdtcDownload.SetFilePath.SetPath;
import org.WdtcLauncher.FilePath;
import org.WdtcLauncher.Version;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

public class GetGame {
    private static final File m_t = new File(FilePath.getStarterBat());
    private static final File l_j = new File(FilePath.getLauncherJson());
    private static final File u_j = new File(FilePath.getUsersJson());
    private static final String users_uuid = String.valueOf(UUID.randomUUID()).replaceAll("-", "");

    public static void Getgame(String v) throws IOException {
        StringBuilder game_set = new StringBuilder();
        Version version = new Version(v);
        JSONObject l_e_j = JSON.parseObject(FileUtils.readFileToString(l_j, "UTF-8"));
        JSONArray game_j = l_e_j.getJSONArray("game");
        JSONObject v_e_j = JSONObject.parseObject(FileUtils.readFileToString(new File(version.getVersionJson()), "UTF-8"));
        JSONObject assetIndex_j = v_e_j.getJSONObject("assetIndex");
        JSONObject u_e_j = JSONObject.parseObject(FileUtils.readFileToString(u_j, "UTF-8"));
        String user_name = u_e_j.getString("user_name");

        String usersname = " " + game_j.getString(0) + " " + user_name;
        game_set.append(usersname);

        String version_set = " " + game_j.getString(1) + " " + v;
        game_set.append(version_set);

        String gamedir = " " + game_j.getString(2) + " " + SetPath.getGame_path();
        game_set.append(gamedir);

        String assersdir = " " + game_j.getString(3) + " " + SetPath.getGame_assetsdir();
        game_set.append(assersdir);

        String assetIndex = " " + game_j.getString(4) + " " + assetIndex_j.getString("id");
        game_set.append(assetIndex);

        String Uuid = " " + game_j.getString(5) + " " + users_uuid;
        game_set.append(Uuid);

        String accesstoken = " " + game_j.getString(6) + " " + "${auth_access_token}";
        game_set.append(accesstoken);

        String clientid = " " + game_j.getString(7) + " " + "${clientid}";
        game_set.append(clientid);

        String xuid = " " + game_j.getString(8) + " " + "${auth_xuid}";
        game_set.append(xuid);

        String type = " " + game_j.getString(9) + " " + "legacy";
        game_set.append(type);

        String v_type = " " + game_j.getString(10) + " " + "Wdtc-dome";
        game_set.append(v_type);
        FileUtils.writeStringToFile(m_t, game_set.toString(), "UTF-8", true);

    }
}
