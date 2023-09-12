package com.example.itf_processing.mapper;

import com.example.itf_processing.dao.FileEntity;
import com.example.itf_processing.dao.ItfRecordEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE,
        unmappedTargetPolicy = ReportingPolicy.IGNORE)
public class FileMapper {

    public static final FileMapper INSTANCE = Mappers.getMapper(FileMapper.class);

    public FileEntity toFileEntity(String fileName, String folderName, String filePath) {
        return FileEntity.builder().FILE_NAME(fileName).FOLDER_NAME(folderName).FILE_PATH(filePath).build();
    }
}
