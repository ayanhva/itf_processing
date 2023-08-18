package com.example.itf_processing.helper;

import com.example.itf_processing.logger.DPLogger;
import jcifs.CIFSContext;
import jcifs.context.SingletonContext;
import jcifs.smb.NtlmPasswordAuthenticator;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class SmbHelper {
    private static final DPLogger log = DPLogger.getLogger(SmbHelper.class);
    private final String username;
    private final String password;

    public SmbHelper(@Value("${smb.credentials.username}") String username,
                     @Value("${smb.credentials.password}") String password) {
        this.username = username;
        this.password = password;
    }

    public CIFSContext getContextForConnection() {
        NtlmPasswordAuthenticator authenticator = new NtlmPasswordAuthenticator("", username, password);
        SingletonContext context = SingletonContext.getInstance();
        return context.withCredentials(authenticator);
    }

    public void saveToOutputFile(String outputFileName, List<String> outputFileData) throws Exception {

        SmbFile smbFile = new SmbFile(outputFileName, getContextForConnection());
        log.info("ActionLog.saveToOutputFile file data before saving: #name: {}, #size: {}",
                outputFileName, outputFileData.size());

        try (SmbFileOutputStream outStream = new SmbFileOutputStream(smbFile)) {
            for (String line : outputFileData)
                outStream.write(line.getBytes());

            outStream.flush();
        }

        log.info("ActionLog.saveToOutputFile file data after saving: #name: {}, #size: {}",
                smbFile.getCanonicalPath(), smbFile.length());
    }

    public void moveFile(SmbFile originalFile, String moveTo) throws Exception {
        SmbFile smbToFile = new SmbFile(moveTo + originalFile.getName(), getContextForConnection());
        originalFile.renameTo(smbToFile);
    }

    public void copyFile(SmbFile originalFile, String copyTo) throws Exception {
        SmbFile smbToFile = new SmbFile(copyTo + originalFile.getName(), getContextForConnection());
        originalFile.copyTo(smbToFile);
    }

    public void deleteFile(SmbFile file) throws Exception {
        file.delete();
    }
}