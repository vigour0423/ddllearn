package com.ddl.guava.ordering;

import com.ddl.guava.entity.Person;
import com.ddl.guava.entity.Student;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

import java.util.List;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class OrderingTest {
    public static void main(String[] args) {

        Person ddl = new Person("b", 14);
        Person ldd = new Person("a", 14);

        Student s1 = new Student("Mahesh", "Parashar", 1, "VI");


        //使用Comparable类型的自然顺序， 例如：整数从小到大，字符串是按字典顺序;
        Ordering<Comparable> natural = Ordering.natural();

        //使用toString()返回的字符串按字典顺序进行排序;
        Ordering<Object> toString = Ordering.usingToString();

        //返回一个所有对象的任意顺序.
        Ordering<Object> arbitrary = Ordering.arbitrary();

        //通过自然排序比较，返回值（0=相等，1大于，-1小于),
        System.out.println(ComparisonChain.start()
                .compare(ddl.getName(), ldd.getName(), natural.nullsLast())
                .result());


        System.out.println(ComparisonChain.start()
                //返回由它们的字符串表示的自然顺序，toString()比较对象进行排序,使用当前排序器，但额外把 null 值排到最后面。
                .compare(ddl, s1, toString.nullsLast())
                .result());

        List<Person> persons = Lists.newArrayList(
                new Person("chang", 15),
                new Person("ahang", 16),
                new Person("bee", 18),
                new Person("bee", 13)
        );
        Ordering<Person> ordering = natural
                .nullsFirst()
                //首先应用功能给它们，然后比较使用此这些结果的顺序元素。
                .onResultOf(Person::getName);

        //返回包含的元素排序此排序可变列表
        List<Person> people = ordering.sortedCopy(persons);

        //返回根据这个顺序给出迭代，为了从最大到最小的k个最大的元素。
        List<Person> people1 = ordering.greatestOf(persons, 2);

        List<Person> people2 = ordering.leastOf(persons, 2);

        //返回最少指定的值，根据这个顺序。
        Person people3 = ordering.min(persons);
        Person people4 = ordering.max(persons);

        System.out.println(people);
        System.out.println(people1);
        System.out.println(people2);
        System.out.println(people3);
        System.out.println(people4);

        System.out.println(arbitrary.nullsLast().sortedCopy(persons));
    }
}
