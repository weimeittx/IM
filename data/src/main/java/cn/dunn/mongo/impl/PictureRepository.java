package cn.dunn.mongo.impl;

import com.mongodb.gridfs.GridFSDBFile;
import org.apache.commons.io.IOUtils;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsTemplate;

import javax.annotation.Resource;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by Administrator on 2016/9/20.
 */
public class PictureRepository {
    @Resource
    private GridFsTemplate gridFsTemplate;

    public byte[] getPicture(String id) throws IOException {
        GridFSDBFile file = gridFsTemplate.findOne(Query.query(Criteria.where("id").is(id)));
        InputStream inputStream = file.getInputStream();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        IOUtils.copy(inputStream,outputStream);
        return outputStream.toByteArray();
    }
}
