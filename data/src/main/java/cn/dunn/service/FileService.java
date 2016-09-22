package cn.dunn.service;

import com.mongodb.gridfs.GridFSFile;

import java.io.InputStream;
import java.util.Map;

/**
 * 文件服务
 */
public interface FileService {
    GridFSFile getOneFile(String id);

    GridFSFile saveFile(byte[] bytes, String fileName, String type, Map<String, Object> metadata);

    GridFSFile saveFile(InputStream inputStream, String fileName, String type, Map<String, Object> metadata);

    void deleteFile(String id);
}
