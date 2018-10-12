package com.ddl.guava.collection.util;

import com.ddl.guava.entity.Person;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class GuavaUtilTest {

    @Test
    public void iterable() {
        //串联多个 iterables
        Iterable<Integer> concatenated = Iterables.concat(
                Ints.asList(1, 2, 3, 4),
                Ints.asList(4, 5, 6));

        //获取集合中的,首个、最后元素
        List<String> strings = Arrays.asList("1", "3", "4");
        String lastAdded = Iterables.getLast(strings);
        String first = Iterables.getFirst(strings, null);
        System.out.println("集合中的最后的元素" + lastAdded);
        System.out.println("集合中的第一个元素" + first);

        //获取 iterable 中唯一的元素，如果 iterable 为空或有多个元素，则快速失败
        try {
            String theElement = Iterables.getOnlyElement(Collections.singletonList("4"));
        } catch (Exception e) {
        }


        List<Person> people = Lists.newArrayList(
                new Person("zhangsang", 1),
                new Person("lisi", 2));

        List<Person> people2 = Lists.newArrayList(
                new Person("zhangsang", 1),
                new Person("xiaoming", 3));

        List<Person> people3 = Lists.newArrayList(
                new Person("zhangsang", 1),
                new Person("lisi", 2));

        //返回对象在 iterable 中出现的次数
        int frequency = Iterables.frequency(concatenated, 4);

        //把 iterable 按指定大小分割，得到的子集都不能进行修改操作(UnsupportedOperationException)
        Iterable<List<Person>> partition = Iterables.partition(people, 2);

        //如果两个 iterable 中的所有元素相等且顺序一致，返回 true
        boolean result = Iterables.elementsEqual(people2, people3);
        boolean result2 = Iterables.elementsEqual(people, people3);

        //限制 iterable 的元素个数限制给定值
        Iterable<Person> limit = Iterables.limit(people, 1);
        for (Person person : limit) {
            System.out.println(person);
        }

        System.out.println(people2);
        //移除元素
        Iterables.removeIf(people2, p -> Objects.equal(p.getName(), "zhangsang"));
        System.out.println(people2);
        Iterables.removeAll(people, people2);
        //保留元素（保留people在people2中相同的元素）
        Iterables.retainAll(people, people2);


    }

    @Test
    public void listsTest() {
        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);

        // {5, 4, 3, 2, 1}反转
        List countDown = Lists.reverse(countUp);

        //{{1,2}, {3,4}, {5}}分割
        List<List<Integer>> parts = Lists.partition(countUp, 2);


    }

    //普通的分割方法
    private void partition() {
        List<Person> peoples = Lists.newArrayList(
                new Person("zhangsang", 1),
                new Person("lisi", 2));
        int size = peoples.size();
        int toIndex = 1;
        if (size > 1) {
            for (int i = 0; i < size; i += 1) {
                if (i + 1 > size) {
                    //作用为toIndex最后没有n条数据则剩余几条newPerson中就装几条
                    toIndex = size - i;
                }
                List<Person> newPerson = peoples.subList(i, i + toIndex);

            }
        }
    }

    @Test
    public void setsTest() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        //求交集
        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        System.out.println("intersection" + intersection);

        //求并集
        Sets.SetView<String> union = Sets.union(primes, wordsWithPrimeLength);
        System.out.println("union" + union);


        //求差集（保留前者不同于后者的数据）
        Sets.SetView<String> difference = Sets.difference(wordsWithPrimeLength, primes);
        System.out.println("difference" + difference);

        //对自己做不可变拷贝。
        ImmutableSet<String> strings = intersection.immutableCopy();


        Set<String> strings1 = Sets.newHashSet("w", "q", "e");
        Set<String> strings2 = Sets.newHashSet("dd", "q", "e");

        Sets.SetView<String> intersection2 = Sets.difference(strings2, strings1);

        //拷贝进另一个可变集合
        intersection2.copyInto(strings1);

        System.out.println(strings1);


        Set<String> animals = ImmutableSet.of("gerbil", "hamster");
        Set<String> fruits = ImmutableSet.of("apple", "orange", "banana");

        //返回所有集合的笛卡儿积
        // {{"gerbil", "apple"}, {"gerbil", "orange"}, {"gerbil", "banana"},
        // {"hamster", "apple"}, {"hamster", "orange"}, {"hamster", "banana"}}
        Set<List<String>> product = Sets.cartesianProduct(animals, fruits);

        //返回给定集合的所有子集
        // {{}, {"gerbil"}, {"hamster"}, {"gerbil", "hamster"}}
        Set<Set<String>> animalSets = Sets.powerSet(animals);

    }

    @Test
    public void mapsTest() {
        List<Person> persons = Arrays.asList(
                new Person("zhang", 15),
                new Person("wang", 16),
                new Person("lee", 18)
        );
        /**
         * 转换后的Map具有唯一键
         */
        Map<String, Person> map = Maps.uniqueIndex(persons, new Function<Person, String>() {
            @Override
            public String apply(Person person) {
                return person.getName();
            }
        });
        System.out.println(map);
    }
}
