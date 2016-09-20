package cn.dunn.service;

import java.io.InputStream;
import java.util.Map;
import java.util.Objects;

/**
 * 文件服务
 */
public interface FileService<T> {
    FileNote<T> getOneFile(FileNote<T> id);

    FileNote<T> saveFile(byte[] bytes, String fileName, String type, Map<String, Object> metadata);

    FileNote<T> saveFile(InputStream inputStream, String fileName, String type, Map<String, Object> metadata);

    void deleteFile(FileNote<String> fileNote);
}
