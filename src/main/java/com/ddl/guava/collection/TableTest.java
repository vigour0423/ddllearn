package com.ddl.guava.collection;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;
import org.junit.Test;

import java.util.Map;
import java.util.Set;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class TableTest {

    @Test
    public void tableTest() {
        //通常来说，当你想使用多个键做索引的时候，你可能会用类似 Map<key, Map<key, value>>的实现
        Table<String, String, Integer> tables = HashBasedTable.create();
        tables.put("a", "javase", 80);
        //多键索引一样，覆盖
        tables.put("a", "javase", 90);
        tables.put("c", "javaee", 90);
        tables.put("b", "javame", 100);
        tables.put("d", "javame", 70);

        //通过行索引获取数据
        Map<String, Integer> row = tables.row("a");
        for (Map.Entry<String, Integer> stringIntegerEntry : row.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + "->" + stringIntegerEntry.getValue());
        }

        //通过列索引获取数据
        Map<String, Integer> javase = tables.column("javase");
        for (String s : javase.keySet()) {
            System.out.println(s + "->" + javase.get(s));
        }

        //通过行索引转换数据
        Map<String, Map<String, Integer>> mapMap = tables.rowMap();
        System.out.println(mapMap);

        //通过列索引转换数据
        Map<String, Map<String, Integer>> stringMapMap = tables.columnMap();
        System.out.println(stringMapMap);

        //获取不重复的key
        Set<String> rowKey = tables.rowKeySet();
        Set<String> columnKey = tables.columnKeySet();

        Set<Table.Cell<String, String, Integer>> cells = tables.cellSet();

    }
}
