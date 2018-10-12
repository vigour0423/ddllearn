package com.ddl.guava.collection.multi;

import com.ddl.guava.entity.Employee;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class MultimapTest {

    private Map<String, List<Employee>> map = new HashMap<>();

    @Test
    public void multimapTest() {
        Employee xiaoming = new Employee("xiaoming", 21);
        Employee wangwu = new Employee("wangwu", 21);

        List<Employee> employees = Lists.newArrayList(
                new Employee("xiaoming", 21),
                new Employee("xiaoming", 22)
        );

        Multimap<String, Employee> multimap = ArrayListMultimap.create();
        multimap.put("kaifabu", xiaoming);
        multimap.put("kaifabu", wangwu);

        //等价于multimap.putAll("xingzhenbu", employees);
        multimap.get("xingzhenbu").addAll(employees);

        System.out.println("multimap:" + multimap.size());
        System.out.println("multimap:" + multimap.containsKey("kaifabu"));
        System.out.println("multimap:" + multimap.keys());
        System.out.println("multimap:" + multimap.containsKey("renshibu"));
        System.out.println("multimap:" + multimap.size());
        System.out.println("multimap:" + multimap.get("kaifabu").size());

        //根据key移除指定的元素
        multimap.remove("xingzhenbu", new Employee("xiaoming", 21));

        //根据key移除所有元素
        multimap.removeAll("xingzhenbu");

        System.out.println("xingzhenbu:" + multimap.get("xingzhenbu"));

        //清除
        //multimap.clear();

        //返回 Multimap 中所有”键-单个值映射”
        Collection<Map.Entry<String, Employee>> entries = multimap.entries();
        for (Map.Entry<String, Employee> entry : entries) {
            System.out.println(entry.getKey() + "->" + entry.getValue());
        }

        //返回元素组成的集合
        Collection<Employee> values = multimap.values();

        multimap.putAll("xingzhenbu", employees);

        //通过key进行对集合分组
        Collection<Collection<Employee>> values1 = multimap.asMap().values();

        System.out.println("集合里面装集合" + values1);


        //用 Set 表示 Multimap 中所有不同的键。
        Set<String> strings = multimap.keySet();
        System.out.println(strings.size());

        //移除key,同时会把对应的元素移除
        strings.remove("kaifabu");


         /*  用 Multiset 表示 Multimap 中的所有键，每个键重复出现的次数等于它映射的值的个数。可以从这个
            Multiset 中移除元素，但不能做添加操作；移除操作会反映到底层的 Multimap。
        */
        Multiset<String> keys = multimap.keys();
        try {
            //移除key,同时会把对应的元素移除
            keys.remove("kaifabu", 1);
            keys.add("44");
        } catch (UnsupportedOperationException e) {
            System.out.println(e.getMessage());
        }

    }

    private void jdkMap(String key, Employee employee) {
        List<Employee> employees = map.get(key);
        if (employees == null) {
            employees = new ArrayList<>();
            map.put(key, employees);
        }
        employees.add(employee);

        //jdk1.8后的使用
        List<Employee> employees1 = map.computeIfAbsent(key, k -> new ArrayList<>());
        employees1.add(employee);
    }
}
