package com.modoo.global.util;

import com.modoo.global.entity.ImageFile;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Component
public class FileUtil {

    private final String uploadPath;
    private static final int MAX_UPLOAD_COUNT = 5;
    private static final List<String> ALLOW_FILE_EXTENSION = List.of("jpg", "jpeg", "png", "gif");

    public FileUtil(@Value("${file.upload-path}") String uploadPath) {
        this.uploadPath = uploadPath;
    }

    /**
     * 단일 파일 업로드
     *
     * @param multipartFile - 파일 객체
     * @return DB에 저장할 파일 정보
     */
    public ImageFile uploadFile(final MultipartFile multipartFile, final String fileType, Integer fileOrder) {

        if (multipartFile == null || multipartFile.isEmpty()) {
            throw new RuntimeException("파일을 선택해주세요.");
        }

        String extension = StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());

        if (!isAllowedExtension(extension)) {
            throw new RuntimeException("허용되지 않는 파일 확장자입니다.");
        }

        String saveName = generateSaveFilename(multipartFile.getOriginalFilename());
        String uploadPath = getUploadPath(fileType) + File.separator + saveName;
        File uploadFile = new File(uploadPath);

        try {
            multipartFile.transferTo(uploadFile);
        } catch (IOException e) {
            throw new RuntimeException("파일 업로드 중 오류가 발생했습니다.", e);
        }

        return ImageFile.builder()
                .uploadPath(uploadPath)
                .originalName(multipartFile.getOriginalFilename())
                .saveName(saveName)
                .extension(extension)
                .fileOrder(fileOrder)
                .build();
    }

    /**
     * 다중 파일 업로드
     *
     * @param multipartFiles - 파일 객체 List
     * @return DB에 저장할 파일 정보 List
     */
    public List<ImageFile> uploadFiles(final List<MultipartFile> multipartFiles, final String fileType, Integer fileOrder) {
        if (multipartFiles == null || multipartFiles.isEmpty()) {
            throw new RuntimeException("파일을 선택해주세요.");
        }

        if (multipartFiles.size() > MAX_UPLOAD_COUNT) {
            throw new RuntimeException("최대 " + MAX_UPLOAD_COUNT + "개 까지 업로드 가능합니다.");
        }

        List<ImageFile> files = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            if (multipartFile.isEmpty()) {
                continue;
            }
            files.add(uploadFile(multipartFile, fileType, fileOrder++));
        }
        return files;
    }

    /**
     * 저장 파일명 생성
     *
     * @param filename 원본 파일명
     * @return 디스크에 저장할 파일명
     */
    private String generateSaveFilename(final String filename) {
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        String extension = StringUtils.getFilenameExtension(filename);
        return uuid + "." + extension;
    }

    /**
     * 파일 확장자 체크
     * @param extension
     * @return
     */
    private boolean isAllowedExtension(String extension) {
        return extension != null && ALLOW_FILE_EXTENSION.contains(extension);
    }

    /**
     * 업로드 경로 반환
     *
     * @return 업로드 경로
     */
    private String getUploadPath() {
        return makeDirectories(uploadPath);
    }

    /**
     * 업로드 경로 반환
     *
     * @param addPath - 추가 경로
     * @return 업로드 경로
     */
    private String getUploadPath(final String addPath) {
        return makeDirectories(uploadPath + File.separator + addPath);
    }

    /**
     * 업로드 폴더(디렉터리) 생성
     *
     * @param path - 업로드 경로
     * @return 업로드 경로
     */
    private String makeDirectories(final String path) {
        File dir = new File(path);
        if (dir.exists() == false) {
            dir.mkdirs();
        }
        return dir.getPath();
    }
}
