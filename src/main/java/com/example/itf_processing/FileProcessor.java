package com.example.itf_processing;

import com.example.itf_processing.exception.FileException;
import com.example.itf_processing.helper.SmbHelper;
import com.example.itf_processing.logger.DPLogger;
import jcifs.smb.SmbException;
import jcifs.smb.SmbFile;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.concurrent.TimeUnit;

@Component
@RequiredArgsConstructor
public class FileProcessor {

    private static final DPLogger log = DPLogger.getLogger(FileProcessor.class);
    private static final String INPUT_STREAM_EXCEPTION = "CAN_NOT_GET_INPUT_STREAM";
    private final SmbHelper smbHelper;

    private String getFileName(String path) {
        String[] fullPath = path.split("/");
        return fullPath[fullPath.length - 1];
    }

    public SmbFile[] getDigitalCardFile(String filePath) {
        log.info("ActionLog.getFile start.");

        try {
            SmbFile smb = new SmbFile(filePath, smbHelper.getContextForConnection());
            SmbFile[] files = smb.listFiles();

            Arrays.stream(files).forEach(file -> {
                        log.debug("ActionLog.getDigitalCardFile fileName: {} start.", file.getName());
                        checkFileSize(file);
                        log.debug("ActionLog.getDigitalCardFile fileName: {} end.", file.getName());
                    }
            );

            log.info("ActionLog.getFile end.");
            return files;
        } catch (IOException e) {
            log.error("ActionLog.getFile error. {}", e);
            throw new FileException(e.getMessage());
        }
    }

    public InputStream getInputStream(SmbFile file) {
        log.debug("ActionLog.getInputStream file: {}.", file.getName());
        try {
            return file.getInputStream();
        } catch (IOException e) {
            log.error("ActionLog.getInputStream.error. ", e);
            throw new FileException(INPUT_STREAM_EXCEPTION);
        }
    }

    private void checkFileSize(SmbFile file) {
        long actualSize = 0;
        long previousSize = -1;

        while (actualSize != previousSize) {

            try {
                previousSize = file.length();
                TimeUnit.SECONDS.sleep(3);
                actualSize = file.length();
            } catch (SmbException | InterruptedException e) {
                log.error("ActionLog.checkFileSize.error while checking file size {} ", e);
            }
        }
    }
}
