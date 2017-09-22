package com.mikemunhall.hbasedaotest.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.hadoop.hbase.client.Delete;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.HTableInterface;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.ResultScanner;
import org.apache.hadoop.hbase.client.Scan;
import org.apache.hadoop.hbase.filter.CompareFilter.CompareOp;
import org.apache.hadoop.hbase.filter.Filter;
import org.apache.hadoop.hbase.filter.FilterList;
import org.apache.hadoop.hbase.filter.PageFilter;
import org.apache.hadoop.hbase.filter.PrefixFilter;
import org.apache.hadoop.hbase.filter.RegexStringComparator;
import org.apache.hadoop.hbase.filter.RowFilter;
import org.apache.hadoop.hbase.util.Bytes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.hadoop.hbase.HbaseTemplate;
import org.springframework.data.hadoop.hbase.TableCallback;
import org.springframework.stereotype.Service;

/**
 * Created by quicksandzn@gmail.com on 2017/9/22.
 */
@Service
public class HbaseServiceImpl implements HbaseService {

    @Autowired
    private HbaseTemplate hbaseTemplate;
    private final String encoding = "utf-8";

    @Override
    public List<Result> scaner(final String tableName) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                Scan scan = new Scan();
                ResultScanner rs = table.getScanner(scan);
                for (Result result : rs) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public Result getRow(final String tableName, final String rowKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<Result>() {
            @Override
            public Result doInTable(HTableInterface table) throws Throwable {
                Get get = new Get(rowKey.getBytes(encoding));
                return table.get(get);
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rowFilter = new RowFilter(CompareOp.EQUAL, rc);
                Scan scan = new Scan();
                scan.setFilter(rowFilter);
                ResultScanner rs = table.getScanner(scan);
                for (Result result : rs) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String regxKey, final int num) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareOp.EQUAL, rc);
                if (num > 0) {// 过滤获取的条数
                    Filter filterNum = new PageFilter(num);// 每页展示条数
                    fl.addFilter(filterNum);
                }
                // 过滤器的添加
                fl.addFilter(rf);
                Scan scan = new Scan();
                scan.setFilter(fl);// 为查询设置过滤器的list
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getStartRowAndEndRow(final String tableName, final String startKey,
        final String stopKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(startKey.getBytes(encoding));// 开始的key
                scan.setStopRow(stopKey.getBytes(encoding));// 结束的key
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String startKey,
        final String stopKey, final String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                // 设置正则过滤器
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareOp.EQUAL, rc);
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(startKey.getBytes(encoding));// 开始的key
                scan.setStopRow(stopKey.getBytes(encoding));// 结束的key
                scan.setFilter(rf);// 为查询设置过滤器的list
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }
        });
    }

    @Override
    public List<Result> getRegexRow(final String tableName, final String startKey,
        final String stopKey, final String regxKey, final int num) {
        return hbaseTemplate.execute(tableName, new TableCallback<List<Result>>() {
            List<Result> list = new ArrayList<>();

            @Override
            public List<Result> doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                // 设置正则过滤器
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareOp.EQUAL, rc);
                if (num > 0) {// 过滤获取的条数
                    Filter filterNum = new PageFilter(num);// 每页展示条数
                    fl.addFilter(filterNum);
                }
                // 过滤器的添加
                fl.addFilter(rf);
                // 过滤器的添加
                Scan scan = new Scan();
                scan.setStartRow(startKey.getBytes(encoding));// 开始的key
                scan.setStopRow(stopKey.getBytes(encoding));// 结束的key
                scan.setFilter(fl);// 为查询设置过滤器的list
                ResultScanner rscanner = table.getScanner(scan);
                for (Result result : rscanner) {
                    list.add(result);
                }
                return list;
            }

        });
    }

    @Override
    public void addData(final String rowKey, final String tableName, final String[] column,
        final String[] value) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {

            @Override
            public String doInTable(HTableInterface table) throws Throwable {

                Put put = new Put(Bytes.toBytes(rowKey));// 设置rowkey
                for (int j = 0; j < column.length; j++) {
                    put.add(Bytes.toBytes("cf1"), Bytes.toBytes(column[j]),
                        Bytes.toBytes(value[j]));
                }
                table.put(put);
                return "ok";
            }

        });
    }

    @Override
    public void delRecord(final String tableName, final String... rowKeys) {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {

            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                List<Delete> list = new ArrayList<>();
                for (String rowKey : rowKeys) {
                    Delete del = new Delete(Bytes.toBytes(rowKey));
                    list.add(del);
                }
                table.delete(list);
                return "ok";
            }

        });
    }

    @Override
    public void updateTable(final String tableName, final String rowKey, final String familyName,
        final String[] column, final String[] value)
        throws IOException {
        hbaseTemplate.execute(tableName, new TableCallback<String>() {

            @Override
            public String doInTable(HTableInterface table) throws Throwable {
                Put put = new Put(Bytes.toBytes(rowKey));
                for (int j = 0; j < column.length; j++) {
                    put.add(Bytes.toBytes(familyName), Bytes.toBytes(column[j]),
                        Bytes.toBytes(value[j]));
                }
                table.put(put);
                return "ok";
            }

        });
    }

    @Override
    public Result getNewRow(final String tableName) {
        return hbaseTemplate.execute(tableName, new TableCallback<Result>() {

            @Override
            public Result doInTable(HTableInterface table) throws Throwable {
                Filter filterNum = new PageFilter(1);// 每页展示条数
                Scan scan = new Scan();
                scan.setFilter(filterNum);
                scan.setReversed(true);
                ResultScanner scanner = table.getScanner(scan);
                return scanner.next();
            }

        });
    }

    @Override
    public Result getNewRow(final String tableName, final String regxKey) {
        return hbaseTemplate.execute(tableName, new TableCallback<Result>() {

            @Override
            public Result doInTable(HTableInterface table) throws Throwable {
                FilterList fl = new FilterList(FilterList.Operator.MUST_PASS_ALL);
                RegexStringComparator rc = new RegexStringComparator(regxKey);
                RowFilter rf = new RowFilter(CompareOp.EQUAL, rc);
                Filter filterNum = new PageFilter(1);// 每页展示条数
                fl.addFilter(rf);
                fl.addFilter(filterNum);
                Scan scan = new Scan();
                scan.setFilter(fl);
                scan.setReversed(true);
                ResultScanner scanner = table.getScanner(scan);
                return scanner.next();
            }

        });
    }

    @Override
    public List<String> queryKeys(final String tableName, final String regxKey) {
        // TODO Auto-generated method stub
        return hbaseTemplate.execute(tableName, new TableCallback<List<String>>() {
            List<String> list = new ArrayList<>();

            @Override
            public List<String> doInTable(HTableInterface table) throws Throwable {
                PrefixFilter filter = new PrefixFilter(regxKey.getBytes(encoding));
                Scan scan = new Scan();
                scan.setFilter(filter);
                ResultScanner scanner = table.getScanner(scan);
                for (Result rs : scanner) {
                    list.add(new String(rs.getRow()));
                }
                return list;
            }

        });
    }

    @Override
    public long incrQualifier(final String tableName, final String cf, final String rowKey,
        final String column, final long num) {
        // TODO Auto-generated method stub
        return hbaseTemplate.execute(tableName, new TableCallback<Long>() {
            @Override
            public Long doInTable(HTableInterface table) throws Throwable {
                long qualifie = table
                    .incrementColumnValue(rowKey.getBytes(encoding), cf.getBytes(encoding),
                        column.getBytes(encoding), num);
                return qualifie;
            }

        });
    }

}
