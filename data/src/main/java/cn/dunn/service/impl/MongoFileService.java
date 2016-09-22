package cn.dunn.service.impl;

import cn.dunn.service.FileService;
import com.mongodb.BasicDBObject;
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
public class MongoFileService implements FileService {
    @Resource
    private GridFsTemplate gridFsTemplate;

    @Override
    public GridFSFile getOneFile(String id) {
        return gridFsTemplate.findOne(Query.query(Criteria.where("_id").is(id)));

    }

    @Override
    public GridFSFile saveFile(InputStream inputStream, String fileName, String type, Map<String, Object> metadata) {
        if (metadata == null)
            metadata = new HashMap<>();
        BasicDBObject dbObject = new BasicDBObject();
        metadata.put("create_time", new Date());
        metadata.put("update_time", new Date());
        metadata.put("type", type);
        metadata.put("postfix", FilenameUtils.getExtension(fileName));
        dbObject.putAll(metadata);
        return gridFsTemplate.store(inputStream, fileName, dbObject);

    }

    @Override
    public GridFSFile saveFile(byte[] bytes, String fileName, String type, Map<String, Object> metadata) {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        return saveFile(inputStream, fileName, type, metadata);
    }

    public void deleteFile(String id) {
        gridFsTemplate.delete(Query.query(Criteria.where("_id").is(id)));
    }


}
