package com.harmonywisdom.dshbcbp.attachment.service.impl;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * 附件配置管理
 *
 * Created by hotleave on 15-3-10.
 */
public class AttachmentConfigManager {
    private static AttachmentConfigManager ourInstance = new AttachmentConfigManager();
    private static Logger logger = LoggerFactory.getLogger(AttachmentConfigManager.class);
    public static final String SAVE_METHOD = "attachment.saveMethod";
    public static final String SAVE_PATH = "attachment.savePath";
    public static final String TEMP_DIR = "attachment.upload.tmpdir";
    public static final String MAX_SIZE = "attachment.upload.maxSize";
    public static final String SIZE_THRESHOLD = "attachment.upload.sizeThreshold";

    public static final String OLD_SYSTEM_VERIFY_URL = "oldSystem.verifyUrl";
    public static final String OLD_SYSTEM_DOWN_DOC_URL = "oldSystem.downDocUrl";
    public static final String IMG_PATH = "attachment.imgPath";
    public static final String IMG_URL = "attachment.imgURL";


    private String savePath;
    private String tempDir;
    private long maxSize = 2097152L; // 2M
    private int threshold = 1048576; // 1M
    private SaveMethod saveMethod;

    private String oldSystemVerifyUrl;
    private String oldSystemDownDocUrl;
    private String imgPath;
    private String imgUrl;

    public static AttachmentConfigManager getInstance() {
        return ourInstance;
    }

    private AttachmentConfigManager() {
        InputStream inputStream = AttachmentConfigManager.class.getClassLoader().getResourceAsStream("/systemConfig.properties");
        try {
            if (inputStream != null) {
                Properties properties = new Properties();
                properties.load(inputStream);
                loadConfig(properties);
            }
        } catch (IOException e) {
            logger.error("未找到systemConfig.properties文件", e);
        }
    }

    void loadConfig(Properties properties) {
        savePath = properties.getProperty(SAVE_PATH);
        oldSystemVerifyUrl = properties.getProperty(OLD_SYSTEM_VERIFY_URL);
        oldSystemDownDocUrl = properties.getProperty(OLD_SYSTEM_DOWN_DOC_URL);
        imgPath = properties.getProperty(IMG_PATH);
        imgUrl = properties.getProperty(IMG_URL);
        tempDir = properties.getProperty(TEMP_DIR);
        tempDir = StringUtils.isBlank(tempDir) ? null : tempDir;

        String method = properties.getProperty(SAVE_METHOD);
        saveMethod = SaveMethod.valueOf(method.toUpperCase());

        String max = properties.getProperty(MAX_SIZE);
        if (StringUtils.isNotBlank(max)) {
            maxSize = Long.valueOf(max) * 1024;
        }

        String threshold = properties.getProperty(SIZE_THRESHOLD);
        if (StringUtils.isNotBlank(threshold)) {
            this.threshold = Integer.valueOf(threshold) * 1024;
        }
    }

    public boolean isNeedChangeTempDir() {
        return tempDir != null;
    }

    public boolean isSaveToDisk() {
        return saveMethod == SaveMethod.BOTH || saveMethod == SaveMethod.DISK;
    }

    public boolean isSaveToDB() {
        return saveMethod == SaveMethod.BOTH || saveMethod == SaveMethod.DB;
    }

    public String getSavePath() {
        return savePath;
    }

    public String getTempDir() {
        return tempDir;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public int getThreshold() {
        return threshold;
    }

    private enum SaveMethod {
        DB, DISK, BOTH
    }

    public String getOldSystemVerifyUrl() {
        return oldSystemVerifyUrl;
    }

    public String getOldSystemDownDocUrl() {
        return oldSystemDownDocUrl;
    }

    public String getImgPath() {
        return imgPath;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
