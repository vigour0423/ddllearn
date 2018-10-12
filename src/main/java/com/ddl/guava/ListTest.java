package com.ddl.guava;

import com.ddl.guava.entity.Person;
import com.google.common.base.Function;
import com.google.common.base.Supplier;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.BiMap;
import com.google.common.collect.ClassToInstanceMap;
import com.google.common.collect.HashBasedTable;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.HashMultimap;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSortedSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.MapDifference;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.google.common.collect.Multimaps;
import com.google.common.collect.Multiset;
import com.google.common.collect.Multisets;
import com.google.common.collect.MutableClassToInstanceMap;
import com.google.common.collect.Range;
import com.google.common.collect.RangeSet;
import com.google.common.collect.SetMultimap;
import com.google.common.collect.Sets;
import com.google.common.collect.Table;
import com.google.common.collect.Tables;
import com.google.common.collect.TreeMultimap;
import com.google.common.collect.TreeMultiset;
import com.google.common.collect.TreeRangeSet;
import com.google.common.primitives.Ints;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * description: 该文件说明
 * @author dongdongliu
 * @version 1.0
 * @date 2018-08-28 16:03:59
 */
public class ListTest {



    @Test
    public void tableTest() {
        Table<String, String, Integer> tables = HashBasedTable.create();
        tables.put("a", "javase", 80);
        tables.put("b", "javaee", 90);
        tables.put("c", "javame", 100);
        tables.put("d", "guava", 70);

        Map<String, Integer> row = tables.row("a");
        for (Map.Entry<String, Integer> stringIntegerEntry : row.entrySet()) {
            System.out.println(stringIntegerEntry.getKey() + "->" + stringIntegerEntry.getValue());
        }
        Map<String, Integer> javase = tables.column("javase");
        for (String s : javase.keySet()) {
            System.out.println(s + "->" + javase.get(s));
        }

        Map<String, Map<String, Integer>> mapMap = tables.rowMap();

        Set<String> strings = tables.rowKeySet();
        Map<String, Map<String, Integer>> stringMapMap = tables.columnMap();

        Set<Table.Cell<String, String, Integer>> cells = tables.cellSet();
    }

    @Test
    public void classToInstanceMap() {
        ClassToInstanceMap<Person> numberDefaults = MutableClassToInstanceMap.create();

        //numberDefaults.putInstance(Integer.class, Integer.valueOf(0));

        numberDefaults.put(Person.class, new Person("dd", 22));

    }

    @Test
    public void rangeSetTest() {
        RangeSet<Integer> rangeSet = TreeRangeSet.create();
        rangeSet.add(Range.closed(1, 10)); // {[1,10]}
        rangeSet.add(Range.closedOpen(11, 15));//不相连区间:{[1,10], [11,15)}
        rangeSet.add(Range.closedOpen(15, 20)); //相连区间; {[1,10], [11,20)}
        rangeSet.add(Range.openClosed(0, 0)); //空区间; {[1,10], [11,20)}
        rangeSet.remove(Range.open(5, 10)); //分割[1, 10]; {[1,5], [10,10], [11,20)}

        List<Person> objects = Lists.newArrayListWithCapacity(100);
    }

    @Test
    public void iterable() {
        Iterable<Integer> concatenated = Iterables.concat(
            Ints.asList(1, 2, 3, 4),
            Ints.asList(4, 5, 6)); // concatenated包括元素 1, 2, 3, 4, 5, 6

        String lastAdded = Iterables.getLast(Arrays.asList("1", "3", "4"));
        String theElement = Iterables.getOnlyElement(Arrays.asList("4"));
        //如果set不是单元素集，就会出错了！
        List<String> strings = Lists.newArrayList("q", "w");

        List<String> strings2 = Lists.newArrayList("D");


        List<Person> people = Lists.newArrayList(new Person("dd", 1), new Person("ww", 2));

        List<Person> people2 = Lists.newArrayList(new Person("dd", 1), new Person("ww", 3));


        boolean result = Iterables.removeAll(people, people2);

    }

    @Test
    public void listsTest() {
        List<Integer> countUp = Ints.asList(1, 2, 3, 4, 5);

        List countDown = Lists.reverse(countUp); // {5, 4, 3, 2, 1}

        List<List<Integer>> parts = Lists.partition(countUp, 2);//{{1,2}, {3,4}, {5}}


    }

    @Test
    public void setsTest() {
        Set<String> wordsWithPrimeLength = ImmutableSet.of("one", "two", "three", "six", "seven", "eight");
        Set<String> primes = ImmutableSet.of("two", "three", "five", "seven");

        Sets.SetView<String> intersection = Sets.intersection(primes, wordsWithPrimeLength);
        Sets.SetView<String> union = Sets.union(primes, wordsWithPrimeLength);
        Sets.SetView<String> difference = Sets.difference(wordsWithPrimeLength, primes);

        // intersection包含"two", "three", "seven"
        ImmutableSet<String> strings = intersection.immutableCopy();//可以使用交集，但不可变拷贝的读取效率更高


        HashSet<String> strings1 = Sets.newHashSet("w", "q", "e");
        HashSet<String> strings2 = Sets.newHashSet("dd", "q", "e");

        Sets.SetView<String> intersection2 = Sets.difference(strings2, strings1);
        intersection2.copyInto(strings1);
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

    @Test
    public void multiMapsTest() {
        List<Person> persons = Arrays.asList(
            new Person("zhang", 15),
            new Person("zhang", 16),
            new Person("lee", 18)
        );
        Multimap<String, Person> multimap = Multimaps.index(persons, new Function<Person, String>() {
            @Override
            public String apply(Person input) {
                return input.getName();
            }
        });
        System.out.println(multimap);


        Map<String, Integer> left = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        Map<String, Integer> right = ImmutableMap.of("a", 1, "b", 2, "c", 3);
        MapDifference<String, Integer> diff = Maps.difference(left, right);
        Map<String, Integer> entriesInCommon = diff.entriesInCommon();// {"b" => 2}
        Map<String, MapDifference.ValueDifference<Integer>> differenceMap = diff.entriesDiffering();// {"b" => 2}
        Map<String, Integer> entriesOnlyOnLeft = diff.entriesOnlyOnLeft();// {"a" => 1}
        Map<String, Integer> entriesOnlyOnRight = diff.entriesOnlyOnRight();// {"d" => 5}

        Map<String, Collection<Person>> map = multimap.asMap();

        //inverFrom

        ArrayListMultimap<String, Integer> arrayListMultimap = ArrayListMultimap.create();
        arrayListMultimap.putAll("b", Ints.asList(2, 4, 6));
        arrayListMultimap.putAll("a", Ints.asList(4, 2, 1));
        arrayListMultimap.putAll("c", Ints.asList(2, 5, 3));

        TreeMultimap<Integer, String> invertFrom = Multimaps.invertFrom(arrayListMultimap, TreeMultimap.<Integer, String>create
            ());

        //forMap
        Map<String, Integer> immutableMap = ImmutableMap.of("a", 1, "b", 1, "c", 2);
        SetMultimap<String, Integer> forMap = Multimaps.forMap(immutableMap);
        // multimap：["a" => {1}, "b" => {1}, "c" => {2}]
        Multimap<Integer, String> inverse = Multimaps.invertFrom(forMap, HashMultimap.<Integer, String>create());
        // inverse：[1 => {"a","b"}, 2 => {"c"}]

    }

    @Test
    public void multiSetTest() {
        Multiset<String> multiset1 = HashMultiset.create();
        multiset1.add("a", 6);
        multiset1.add("b", 5);
        Multiset<String> multiset2 = HashMultiset.create();
        multiset2.add("a", 5);

        boolean result = multiset1.containsAll(multiset2);//返回true；因为包含了所有不重复元素，
        //虽然multiset1实际上包含2个"a"，而multiset2包含5个"a"
        boolean result1 = Multisets.containsOccurrences(multiset1, multiset2);// returns false


        //boolean result3 = Multisets.retainOccurrences(multiset1, multiset2);

        boolean result4 = Multisets.removeOccurrences(multiset1, multiset2);

        multiset2.remove("a"); // multiset2 现在包含3个"a"
        multiset2.removeAll(multiset1);//multiset2移除所有"a"，虽然multiset1只有2个"a"
        boolean empty = multiset2.isEmpty();// returns true
    }

    @Test
    public void tablesTest() {
        // 使用LinkedHashMaps替代HashMaps
        Table<String, Character, Integer> table = Tables.newCustomTable(
            Maps.<String, Map<Character, Integer>>newLinkedHashMap(),
            new Supplier<Map<Character, Integer>>() {
                public Map<Character, Integer> get() {
                    return Maps.newLinkedHashMap();
                }
            });
    }

}
