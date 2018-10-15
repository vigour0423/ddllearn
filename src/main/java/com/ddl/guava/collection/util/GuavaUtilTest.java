package com.ddl.guava.collection.util;

import com.ddl.guava.entity.Book;
import com.ddl.guava.entity.Person;
import com.google.common.base.Objects;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableListMultimap;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMultiset;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.collect.TreeMultimap;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

        List<Book> books = Lists.newArrayList(
                new Book("java", "ISBN123"),
                new Book("zk", "ISBN789"),
                new Book("netty", "ISBN456")
        );

        /**
         * 转换后的Map具有唯一键，否则报错IllegalArgumentException
         */
        Map<String, Person> map = Maps.uniqueIndex(persons, Person::getName);

        System.out.println(map);

        //对Map的Value进行转换
        Map<String, Integer> mapValue = Maps.transformValues(map, Person::getAge);
        System.out.println("对Map的Value进行转换" + mapValue);

        //把Set中对象作为key,把field作为value
        Set<Book> bookSet = Sets.newHashSet(books);
        Map<Book, String> bookToIsbn = Maps.asMap(bookSet, Book::getIsbn);
        System.out.println("Set中对象作为key,把field作为value" + bookToIsbn);


        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3, "f", 4);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "d", 2, "e", 3, "f", 5);

        // 用来比较两个 Map 以获取所有不同点
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        //键相同但是值不同值映射项。
        Map<String, MapDifference.ValueDifference<Integer>> differenceMap = diff.entriesDiffering();
        //两个 Map 中都有的映射项，包括匹配的键与值
        Map<String, Integer> entriesInCommon = diff.entriesInCommon();
        // 键只存在于左边 Map 的映射项(key不同)
        Map<String, Integer> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();
        //键只存在于右边 Map 的映射项(key不同)
        Map<String, Integer> entriesOnlyOnRight = diff.entriesOnlyOnRight();


        System.out.println("differenceMap:" + differenceMap);
        System.out.println("common:" + entriesInCommon);
        System.out.println("OnlyOnLeft:" + entriesOnlyOnLeft);
        System.out.println("OnlyOnRight:" + entriesOnlyOnRight);
    }

    @Test
    public void multisetsTest() {
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("b", 2);
        multiset1.add("a", 6);

        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        //返回 Multiset 的不可变拷贝，并将元素按重复出现的次数做降序排列
        ImmutableMultiset<String> strings = Multisets.copyHighestCountFirst(multiset1);

        //虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"
        //对任意 o，如果 sub.count(o)<=super.count(o)，返回true
        boolean result = Multisets.containsOccurrences(multiset1, multiset2);
        System.out.println(result);

        //返回两个 multiset 的交集;
        Multiset<String> intersection = Multisets.intersection(multiset1, multiset2);
        System.out.println(intersection);

        //修改 removeFrom，以保证任意o都符合removeFrom.count(o)<=toRetain.count(o)
        Multisets.retainOccurrences(multiset1, multiset2);
        System.out.println(multiset1);

        //对 toRemove 中的重复元素，仅在 removeFrom 中删除相同个数。
        Multisets.removeOccurrences(multiset1, multiset2);
        System.out.println(multiset1);

        //multiset2移除所有"a"，虽然multiset1只有2个"a"
        multiset2.removeAll(multiset1);

    }

    @Test
    public void multimapsTest() {
        ImmutableSet digits = ImmutableSet.of(
                "zero", "one", "two", "three",
                "four", "five", "six", "seven", "eight", "nine"
        );

        /*
         *通常针对的场景是：有一组对象，它们有共同的特定
         * 属性，我们希望按照这个属性的值查询对象，但属性值不一定是独一无二的。
         */
        ImmutableListMultimap<Integer, String> digitsByLength = Multimaps.index(digits, String::length);
        System.out.println(digitsByLength);

        ImmutableSet<Person> persons = ImmutableSet.of(
                new Person("zhang", 15),
                new Person("zhang", 14),
                new Person("wang", 16),
                new Person("lee", 18)
        );

        //按照name分组
        ImmutableListMultimap<String, Person> immutableListMultimap = Multimaps.index(persons, Person::getName);
        System.out.println("分组" + immutableListMultimap);

        System.out.println("==========================================");
        //按照name分组(使用java8以上进行分组)
        Map<String, List<Person>> collect = persons.stream()
                .collect(Collectors.groupingBy(Person::getName));
        System.out.println("分组" + collect);

        ArrayListMultimap<String, Integer> multimap = ArrayListMultimap.create();
        multimap.putAll("b", Ints.asList(2, 4, 6));
        multimap.putAll("a", Ints.asList(4, 2, 1));
        multimap.putAll("c", Ints.asList(2, 5, 3));


        //反转
        //注意我们选择的实现，因为选了TreeMultimap，得到的反转结果是有序的
        /*
         * inverse maps:
         * 1 => {"a"}
         * 2 => {"a", "b", "c"}
         * 3 => {"c"}
         * 4 => {"a", "b"}
         * 5 => {"c"}
         * 6 => {"b"}
         */
        TreeMultimap<Integer, String> inverse = Multimaps.invertFrom(multimap, TreeMultimap.create());
        System.out.println(inverse);

        Map<String, Integer> map = ImmutableMap.of("a", 1, "b", 1, "c", 2);

        //把 Map 包装成 SetMultimap。
        SetMultimap<String, Integer> forMap = Multimaps.forMap(map);

        //可以把多对一的 Map 反转为一对多的 Multimap。
        Multimap<Integer, String> invertFrom = Multimaps.invertFrom(forMap, HashMultimap.create());
        System.out.println(invertFrom);

    }

    @Test
    public void tablesTest() {
        // 使用LinkedHashMaps替代HashMaps
        Table<String, Character, Integer> table = Tables.newCustomTable(Maps.newLinkedHashMap(), Maps::newLinkedHashMap);
        //方法允许你把 Table<C, R, V>转置成 Table<R, C, V>。
        Table<Character, String, Integer> transpose = Tables.transpose(table);

    }

}
