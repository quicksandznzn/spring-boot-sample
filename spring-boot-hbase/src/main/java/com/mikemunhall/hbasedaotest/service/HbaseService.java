package com.mikemunhall.hbasedaotest.service;

import java.io.IOException;
import java.util.List;
import org.apache.hadoop.hbase.client.Result;

/**
 * Created by quicksandzn@gmail.com on 2017/9/22.
 */
public interface HbaseService {

    /**
     * 查询全表的数据
     */
    List<Result> scaner(String tablename);

    /**
     * 根据rowKey查询单条记录
     */
    Result getRow(String tableName, String rowKey);

    /**
     * 根据regxKey正则匹配数据
     */
    List<Result> getRegexRow(String tableName, String regxKey);

    /**
     * 根据regxKey正则匹配数据,取出num条
     */
    List<Result> getRegexRow(String tableName, String regxKey, int num);

    /**
     * 根据startKey和endKey的范围匹配数据
     */
    List<Result> getStartRowAndEndRow(String tableName, String startKey, String stopKey);

    /**
     * 确定startKey和endKey的范围，根据regKey匹配数据
     */
    List<Result> getRegexRow(String tableName, String startKey, String stopKey, String regxKey);

    /**
     * 确定startKey和endKey的范围，根据regKey匹配数据,取出num条
     */
    List<Result> getRegexRow(String tableName, String startKey, String stopKey, String regxKey,
        int num);

    /**
     * 添加数据
     */
    void addData(String rowKey, String tableName, String[] column, String[] value);

    /**
     * 删除记录
     */
    void delRecord(String tableName, String... rowKeys);

    /**
     * 修改一条数据
     */
    void updateTable(String tableName, String rowKey, String familyName, String column[],
        String value[]) throws IOException;

    /**
     * 查找最新的一条数据,或者说倒序查询
     */
    Result getNewRow(String tableName);

    /**
     * 正则查出所有匹配的key
     */
    List<String> queryKeys(String tableName, String regxKey);

    /**
     * 增加表中对应字段的值
     */
    long incrQualifier(String tableName, String cf, String rowKey, String column, long num);

    Result getNewRow(String tableName, String regxKey);
}
