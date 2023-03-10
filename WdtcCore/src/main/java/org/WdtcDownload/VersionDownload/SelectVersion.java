package org.WdtcDownload.VersionDownload;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.github.axet.wget.WGet;
import javafx.scene.control.TextField;
import org.WdtcDownload.DownloadLib.GetLibUrl;
import org.WdtcDownload.FileUrl;
import org.WdtcDownload.DownloadResourceFile.DownloadResourceFile;
import org.WdtcLauncher.Version;
import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class SelectVersion {
    private static final Logger LOGGER = Logger.getLogger(SelectVersion.class);
    private static String version_number;
    private static TextField label = new TextField();
    private static boolean BMCLAPI;
    private static Version version;

    public SelectVersion(String version_number, TextField label, boolean BMCLAPI) {
        SelectVersion.version_number = version_number;
        SelectVersion.label = label;
        SelectVersion.BMCLAPI = BMCLAPI;
        SelectVersion.version = new Version(version_number);
    }


    public void selectversion() throws Exception {
        String vm_e = "";
        try {
            if (BMCLAPI) {
                URL version_manifest_url = new URL(FileUrl.getBmclapiVersionManifest());
                URLConnection uc = version_manifest_url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
                vm_e = in.readLine();
            } else {
                URL version_manifest_url = new URL(FileUrl.getMojangVersionManifest());
                URLConnection uc = version_manifest_url.openConnection();
                BufferedReader in = new BufferedReader(new InputStreamReader(uc.getInputStream(), StandardCharsets.UTF_8));
                vm_e = in.readLine();
            }
        } catch (IOException e) {
            LOGGER.error("* ???????????????");
        }
        JSONObject vm_e_j = JSONObject.parseObject(vm_e);
        JSONArray versions_j = vm_e_j.getJSONArray("versions");
        for (int i = 0; i < versions_j.size(); i++) {
            String version_name = versions_j.getJSONObject(i).getString("id");
            if (Objects.equals(version_number, version_name)) {
                URL v_url = new URL(versions_j.getJSONObject(i).getString("url"));
                File v_j = new File(version.getVersionJson());
                new WGet(v_url, v_j).download();
                new GetLibUrl(version_number, BMCLAPI, label).readdown();
                label.setText("???????????????");
                LOGGER.debug("???????????????");
                new DownloadResourceFile(v_j, label, BMCLAPI).getresource_file();
                LOGGER.debug("????????????");
            }
        }


    }

}
