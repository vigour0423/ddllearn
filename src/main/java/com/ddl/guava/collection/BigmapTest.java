package com.ddl.guava.collection;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.Maps;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class BigmapTest {
    private static final Logger logger = LoggerFactory.getLogger(BigmapTest.class);

    /**
     * 逆转Map的key和value
     * @param <S>
     * @param <T>
     * @param map
     * @return
     */
    private static <S, T> Map<T, S> getInverseMap(Map<S, T> map) {
        Map<T, S> inverseMap = new HashMap<T, S>(16);
        for (Map.Entry<S, T> entry : map.entrySet()) {
            inverseMap.put(entry.getValue(), entry.getKey());
        }
        return inverseMap;
    }

    /**
     * 需求:当我们需要通过序号查找文件名，很简单。但是如果我们需要通过文件名查找其序号时，我们就不得不遍历map了。
     * 当然我们还可以编写一段Map倒转的方法来帮助实现倒置的映射关系。
     */
    @Test
    public void logMapTest() {
        Map<Integer, String> logfileMap = Maps.newHashMap();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        logfileMap.put(3, "c.log");

        System.out.println("logfileMap:" + logfileMap);

        /**
         * 1. 如何处理重复的value的情况。不考虑的话，反转的时候就会出现覆盖的情况.
         * 2. 如果在反转的map中增加一个新的key，倒转前的map是否需要更新一个值呢?
         */
        Map<String, Integer> logfileInverseMap = getInverseMap(logfileMap);

        System.out.println("logfileInverseMap:" + logfileInverseMap);
    }


    @Test
    public void biMapTest() {
        //实现数据双向映射绑定
        BiMap<Integer, String> logfileMap = HashBiMap.create();
        logfileMap.put(1, "a.log");
        logfileMap.put(2, "b.log");
        try {
            //在 BiMap 中，如果你想把键映射到已经存在的值，会抛出 IllegalArgumentException 异常。
            logfileMap.put(3, "a.log");
        } catch (IllegalArgumentException e) {
            logger.error(e.getMessage(), e);
        }

        //保证值是唯一的,因此 values()返回 Set 而不是普通的 Collection
        logfileMap.put(3, "cs.log");

        //反转键值映射，它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象
        BiMap<String, Integer> filelogMap = logfileMap.inverse();

        //强制替换它的键，forcePut(key, value),不存在则创建
        logfileMap.forcePut(4, "c.log");
        filelogMap.forcePut("b.log", 5);

        System.out.println("logfileMap:" + logfileMap);
        System.out.println("filelogMap:" + filelogMap);
    }
}
