package com.modoo.global.service;

import com.modoo.global.constant.FileType;
import com.modoo.global.entity.ImageFile;
import com.modoo.global.repository.ImageFileRepository;
import com.modoo.global.util.FileUtil;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
public class FileService {

    private final FileUtil fileUtil;
    private final ImageFileRepository imageFileRepository;

    @Transactional
    public ImageFile singleFileUpload(MultipartFile file, FileType fileType) {
        ImageFile imageFile = fileUtil.uploadFile(file, fileType.getType(), 1);
        return imageFileRepository.save(imageFile);
    }

    @Transactional
    public List<ImageFile> multiFileUpload(List<MultipartFile> multipartFileList, FileType fileType) {
        List<ImageFile> imageFileDtoList = fileUtil.uploadFiles(multipartFileList, fileType.getType(), 1);
        return imageFileRepository.saveAll(imageFileDtoList);
    }
}
