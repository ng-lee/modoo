package com.modoo.global.dto.file;

import com.modoo.global.entity.File;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class FileDto {
    private Long fileCd;
    private String uploadPath;
    private String originalName;
    private String saveName;
    private String extension;
    private Integer fileOrder;

    public static FileDto entityToDto(File file) {
        return FileDto.builder()
                .fileCd(file.getFileCd())
                .uploadPath(file.getUploadPath())
                .originalName(file.getOriginalName())
                .saveName(file.getSaveName())
                .extension(file.getExtension())
                .fileOrder(file.getFileOrder())
                .build();
    }
}
