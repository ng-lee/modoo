package com.modoo.global.dto.file;

import com.modoo.global.entity.ImageFile;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@Builder
@ToString
public class ImageFileDto {
    private Long fileCd;
    private String uploadPath;
    private String originalName;
    private String saveName;
    private String extension;
    private Integer fileOrder;

    public static ImageFileDto entityToDto(ImageFile imageFile) {
        return ImageFileDto.builder()
                .fileCd(imageFile.getFileCd())
                .uploadPath(imageFile.getUploadPath())
                .originalName(imageFile.getOriginalName())
                .saveName(imageFile.getSaveName())
                .extension(imageFile.getExtension())
                .fileOrder(imageFile.getFileOrder())
                .build();
    }
}
