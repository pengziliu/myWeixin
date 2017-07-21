package com.lzp.service;

import com.lzp.bean.Page;
import com.lzp.util.ReflectionUtils;
import com.mongodb.WriteResult;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liuzp on 2017/5/24.
 */
public class BaseMongoService<T>{

    @Autowired
    private MongoTemplate mongoTemplate;

    /**
     * 通过id获取对象
     * @param id
     * @return
     */
    public T getById(String id){
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        return (T) mongoTemplate.findOne(query,getEntityClass());
    }

    /**
     *
     * @param query
     * @return
     */
    public  T findOne(Query query){
        return (T) mongoTemplate.findOne(query,getEntityClass());
    }


    public void save(T clazz){
          mongoTemplate.save(clazz);
    }

    /**
     * 根据主键更新记录
     * @param id
     * @param update
     * @return
     */
    public boolean update(String id,Update update){
        boolean result = false;
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        WriteResult writeResult =  mongoTemplate.updateFirst(query,update,getEntityClass());
        if (null != writeResult) {
            if (writeResult.getN() > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 通过id移除对象
     * @param id
     * @return
     */
    public boolean delById (String id){
        boolean result = false;
        Query query = new Query(Criteria.where("_id").is(new ObjectId(id)));
        WriteResult writeResult =  mongoTemplate.remove(query,getEntityClass());
        if (null != writeResult) {
            if (writeResult.getN() > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 根据条件删除记录
     * @param query
     * @return
     */
    public boolean remove(Query query){
        boolean result = false;
        WriteResult writeResult =  mongoTemplate.remove(query,getEntityClass());
        if (null != writeResult) {
            if (writeResult.getN() > 0) {
                result = true;
            }
        }
        return result;
    }


    /**
     * 删除多条记录
     * @param ids
     * @return
     */
    public boolean del (String ids[]){

        boolean result = false;
        List<ObjectId> list =new ArrayList<>();
        for(String id:ids){
            list.add(new ObjectId(id));
        }
        Query query = new Query(Criteria.where("_id").in(list));
        WriteResult writeResult =  mongoTemplate.remove(query,getEntityClass());
        if (null != writeResult) {
            if (writeResult.getN() > 0) {
                result = true;
            }
        }
        return result;
    }

    /**
     * 通用分页
     * @param query
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     * @throws Exception
     */
    public Page lists(Query query, Integer pageNum, Integer pageSize, Sort sort) throws Exception {
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        List<T> list = list(query,pageNum,pageSize,sort);
        pageSize = pageSize > 20 ? 20 :pageSize;
        Page page = new Page(pageNum, pageSize);
        page.setTotalCount(getCount(query));
        page.setListData(list);
        page.setPageCount(page.getTotalCount() % page.getPageSize() == 0 ? page.getTotalCount() / page.getPageSize() : page.getTotalCount() / page.getPageSize() + 1);
        return page;
    }

    public long  getCount(Query query){
        return mongoTemplate.count(query,getEntityClass());
    }

    /**
     * 分页获取List
     * @param query
     * @param pageNum
     * @param pageSize
     * @param sort
     * @return
     */
    public List<T>  list(Query query, Integer pageNum, Integer pageSize,Sort sort){
        pageNum = pageNum == null ? 1 : pageNum;
        pageSize = pageSize == null ? 20 : pageSize;
        query.skip((pageNum - 1) * pageSize).limit(pageSize);
        if(sort!=null){
            query.with(sort);
        }
        return (List<T>) this.mongoTemplate.find(query,getEntityClass());
    }

    public  List<T> list(Query query){
        return (List<T>) this.mongoTemplate.find(query,getEntityClass());
    }


    /**
     * 获取需要操作的实体类class
     *
     * @return
     */
    private Class<T> getEntityClass(){
        return ReflectionUtils.getSuperClassGenricType(getClass());
    }


}
