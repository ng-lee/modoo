package com.modoo.global.service;

import com.modoo.global.constant.FileType;
import com.modoo.global.dto.file.ImageFileDto;
import com.modoo.global.entity.ImageFile;
import com.modoo.global.repository.ImageFileRepository;
import com.modoo.global.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileUtil fileUtil;
    private final ImageFileRepository imageFileRepository;

    @Transactional
    public ImageFileDto singleFileUpload(MultipartFile file, FileType fileType) {
        ImageFile imageFile = fileUtil.uploadFile(file, fileType.getType(), 1);
        ImageFile savedImageFile = imageFileRepository.save(imageFile);
        return ImageFileDto.entityToDto(savedImageFile);
    }

    @Transactional
    public List<ImageFileDto> multiFileUpload(List<MultipartFile> multipartFileList, FileType fileType) {
        List<ImageFile> imageFileDtoList = fileUtil.uploadFiles(multipartFileList, fileType.getType(), 1);
        List<ImageFile> savedImageFileList = imageFileRepository.saveAll(imageFileDtoList);
        return savedImageFileList.stream()
                .map(ImageFileDto::entityToDto)
                .collect(Collectors.toList());
    }
}
