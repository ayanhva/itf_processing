package com.example.itf_processing;

import com.example.itf_processing.dao.FileEntity;
import com.example.itf_processing.dao.FileRepository;
import com.example.itf_processing.dao.ItfRecordEntity;
import com.example.itf_processing.dao.ItfRecordRepository;
import com.example.itf_processing.logger.DPLogger;
import com.example.itf_processing.mapper.FileMapper;
import com.example.itf_processing.mapper.ItfRecordMapper;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileInputStream;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class ItfProcessingService {

    private static final DPLogger log = DPLogger.getLogger(ItfProcessingService.class);
    private final FileProcessor fileProcessor;
    private @Value("${smb.credentials.input-dir}")
    String exdDir;
    private final ItfRecordRepository recordRepo;
    private final FileRepository fileRepo;

    @PostConstruct
    public void processItfFiles() {
        try {
            System.out.println(exdDir);
            var folders = exdDir.split("/");
            var currentFolder = folders[folders.length-1];

            SmbFile[] smbFiles = fileProcessor.getDigitalCardFile(exdDir);

            System.out.println(smbFiles.length);

            Arrays.stream(smbFiles).forEach(file -> {
                try {
                    checkFileSize(file);
                    if (file.isFile()) {
                        String fileName = file.getName();
                        System.out.println(fileName);
                        processFile(file, fileName, currentFolder);
                        fileRepo.save(FileMapper.INSTANCE.toFileEntity(fileName, currentFolder, file.getCanonicalPath()));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                    log.error("ActionLog.processClearingFiles.error while processing, ex: ", ex);
                }
            });
        } catch (Exception ex) {
            log.error("ActionLog.processClearingFiles.error ", ex);
        }
    }

    private void processFile(SmbFile file, String fileName, String folderName) {
        log.info("ActionLog.processFile start.");
        List<String> rows = readItfFile(file);

        List<ItfRecordEntity> records = new ArrayList<>();
        rows.forEach(row -> {
            try {
                var row_0_part_1 = get_part_1_text(row);
                var row_0_part_1_1 = get_part_1_1_text(row);
                var row_0_part_2 = get_part_2_text(row);
                var row_0_part_2_1 = get_part_2_1_text(row);
                ItfRecordEntity record = ItfRecordMapper.INSTANCE.toEntity(row_0_part_1, row_0_part_1_1, row_0_part_2,
                        row_0_part_2_1, fileName, folderName, file.getCanonicalPath());
                records.add(record);
            } catch (Exception e) {
                System.out.println(row);
                throw e;
            }
        });

        System.out.println(records.size());

        recordRepo.saveAll(records);

        log.info("ActionLog.processFile end.");
    }

    public String get_part_1_text(String row) {
        return row.substring(26, 126);
    }

    public String get_part_1_1_text(String row) {
        int startingPosition = row.indexOf('\n') + 26;
        int length = 69;

        return row.substring(startingPosition, startingPosition + length);
    }

    public String get_part_2_text(String row) {
        int startingPosition = row.indexOf('\n', row.indexOf('\n') + 1) + 26;
        int length = 100;

        return row.substring(startingPosition, startingPosition + length);
    }

    public String get_part_2_1_text(String row) {
        int firstCarriageReturnPosition = row.indexOf('\n');
        int secondCarriageReturnPosition = row.indexOf('\n', firstCarriageReturnPosition + 1);
        int thirdCarriageReturnPosition = row.indexOf('\n', secondCarriageReturnPosition + 1);

        int startingPosition = thirdCarriageReturnPosition + 26;
        int length = 69;

        return row.substring(startingPosition, startingPosition + length);
    }

    private List<String> readItfFile(SmbFile file) {
        log.info("ActionLog.readItfFile start.");
        List<String> rows = new ArrayList<>();
        StringBuilder row = new StringBuilder();

        int part = 0;
        boolean flag = false;

        try (BufferedReader br = new BufferedReader(new InputStreamReader(new SmbFileInputStream(file)))) {
            String line;
            System.out.println("entered");
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                if (flag) {
                    row.append(line).append('\n');
                    flag = false;
                    part++;
                } else if (line.trim().startsWith("33") && (line.contains("PART1") || line.contains("PART2"))) {
                    row.append(line).append('\n');
                    flag = true;
                }

                if (part == 2) {
                    rows.add(row.toString());
                    row.setLength(0);
                    part = 0;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        log.info("ActionLog.readItfFile end.");
        return rows;
    }

    private void checkFileSize(SmbFile file) throws Exception {
        long actualSize = 0;
        long previousSize = -1;

        while (actualSize != previousSize) {
            previousSize = file.length();
            TimeUnit.SECONDS.sleep(3);
            actualSize = file.length();
        }
    }
}
