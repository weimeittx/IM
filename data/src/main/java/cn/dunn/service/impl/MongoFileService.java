package cn.dunn.service.impl;

import cn.dunn.service.FileNote;
import cn.dunn.service.FileService;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSFile;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class MongoFileService implements FileService<String> {
    @Resource
    private GridFsTemplate gridFsTemplate;

    @Override
    public FileNote<String> getOneFile(FileNote<String> id) {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id.getId())));
        InputStream inputStream = file.getInputStream();
        DBObject metaData = file.getMetaData();
        return new FileNote<String>() {
            @Override
            public String getId() {
                return file.getId().toString();
            }

            @Override
            public String MD5() {
                return file.getMD5();
            }

            @Override
            public long length() {
                return file.getLength();
            }

            @Override
            public String postfix() {
                return FilenameUtils.getExtension(file.getFilename());
            }

            @Override
            public String type() {
                return (String) metaData.get(TYPE);
            }

            @Override
            public Date createTime() {
                return (Date) metaData.get(CREATE_TIME);
            }

            @Override
            public Date updateTime() {
                return (Date) metaData.get(UPDATE_TIME);
            }

            @Override
            public InputStream inputStream() {
                return inputStream;
            }

            @Override
            public Map<String, String> metaData() {
                return metaData.toMap();
            }

            @Override
            public String filename() {
                return file.getFilename();
            }
        };
    }

    @Override
    public FileNote<String> saveFile(InputStream inputStream, String fileName, String type, Map<String, Object> metadata) {
        if (metadata == null)
            metadata = new HashMap<>();
        BasicDBObject dbObject = new BasicDBObject();
        metadata.put(FileNote.CREATE_TIME, new Date());
        metadata.put(FileNote.UPDATE_TIME, new Date());
        metadata.put(FileNote.TYPE, type);
        metadata.put(FileNote.POSTFIX, FilenameUtils.getExtension(fileName));
        dbObject.putAll(metadata);
        GridFSFile file = gridFsTemplate.store(inputStream, fileName, dbObject);
        return () -> file.getId().toString();
    }

    @Override
    public FileNote<String> saveFile(byte[] bytes, String fileName, String type, Map<String, Object> metadata) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return saveFile(inputStream, fileName, type, metadata);
    }

    public void deleteFile(FileNote<String> fileNote) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(fileNote.getId())));
    }


}
