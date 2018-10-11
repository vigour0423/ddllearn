package com.ddl.guava.collection.immutable;

import com.ddl.guava.entity.Person;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Lists;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class ImmutableTest {
    private static final Logger logger = LoggerFactory.getLogger(ImmutableTest.class);

    private Person person1 = new Person("aa", 1);

    private Person person2 = new Person("bb", 2);

    private Person person3 = new Person("aa", 1);

    @Test
    public void testJDKImmutable() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");

        System.out.println(list);

        List<String> unmodifiableList = Collections.unmodifiableList(list);

        System.out.println("不可变集合1" + unmodifiableList);

        List<String> unmodifiableList1 = Collections.unmodifiableList(Arrays.asList("a", "b", "c"));
        System.out.println("不可变集合2" + unmodifiableList1);

        String temp = unmodifiableList.get(1);
        System.out.println("unmodifiableList [0]：" + temp);

       /*向集合里添加东西后，不可变集合的成员也主动添加了
        说明：Collections.unmodifiableList实现的不是真正的不可变集合，当原始集合修改后，
        不可变集合也发生变化。不可变集合不可以修改集合数据，当强制修改时会报错，实例中的最后两个add会直接抛出不可修改的错误。*/
        list.add("baby");
        System.out.println("list add a item after list:" + list);
        System.out.println("list add a item after unmodifiableList:" + unmodifiableList);

        unmodifiableList1.add("bb");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList1);

        unmodifiableList.add("cc");
        System.out.println("unmodifiableList add a item after list:" + unmodifiableList);
    }

    @Test
    public void immutableSetTest() {

        List<Person> arrayList = Lists.newArrayList(
                new Person("wangwu", 12),
                new Person("lisi", 15)

        );
        //不可变的集合,不重复,不可使用null值
        ImmutableSet<Person> persons = ImmutableSet.of(person1, person1, person2);

        try {
            System.out.println("取出集合的第一个元素" + persons.asList().get(0) + " 集合的大小为" + persons.size());
            persons.add(person3);
        } catch (UnsupportedOperationException e) {
            logger.error("不可集合不能做修改操作");
        }
        //通过builder构建不可变集合
        ImmutableSet<Person> personBuilds = ImmutableSet.<Person>builder()
                .add(person1)
                .addAll(arrayList)
                .build();

        //并不会影响上面的集合成员
        arrayList.add(new Person("xixi", 18));

        //结合lambda提取想要的信息
        List<String> strings = personBuilds.stream()
                .filter((p) -> Objects.equal(p.getName(), "aa"))
                .map(Person::getName)
                .collect(Collectors.toList());

        System.out.println(strings.get(0).equals("aa"));


    }

    @Test
    public void immutableSortedTest() {

        //对有序不可变集合来说，排序是在构造集合的时候完成的
        ImmutableSortedSet<String> sortedSet = ImmutableSortedSet.of("a", "c", "b", "a", "d", "b");
        for (String s : sortedSet) {
            System.out.println(s);
        }

        /*在这段代码中，ImmutableList.copyOf(imSet)会智能地返回时间复杂度为常数的ImmutableSet的imSet.asList()。
　　       一般来说，ImmutableXXX.copyOf(ImmutableCollection)会避免线性复杂度的拷贝操作。如在以下情况：
　　      这个操作有可能就利用了被封装数据结构的常数复杂度的操作。但例如ImmutableSet.copyOf(list)不能在常数复杂度下实现。
　　      这样不会导致内存泄漏－例如，你有个ImmutableList<String> imInfolist，然后你显式操作ImmutableList.copyOf(imInfolist.subList(0, 10))。这样的操作可以避免意外持有不再需要的在hugeList里元素的reference。
　　      它不会改变集合的语意－像ImmutableSet.copyOf(myImmutableSortedSet)这样的显式拷贝操作，因为在ImmutableSet里的hashCode()和equals()的含义和基于comparator的ImmutableSortedSet是不同的。
　　      这些特性有助于最优化防御性编程的性能开销
         */
        ImmutableList<String> strings = ImmutableList.copyOf(sortedSet);

    }
}
