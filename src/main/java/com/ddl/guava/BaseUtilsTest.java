package com.ddl.guava;

import com.ddl.guava.entity.Person;
import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * description: 该文件说明
 * @author dongdongliu
 * @version 1.0
 * @date 2018-10-09 13:38:26
 */
public class BaseUtilsTest {
    @Test
    public void optionalTest() {


        /*Optional<Object> objectOptional = Optional.fromNullable(null);
        Object o = absent.get();*/

    }

    @Test
    public void objectTest() {
        System.out.println(Objects.equal("a", "a"));
        System.out.println(Objects.equal(null, "a"));
        System.out.println(Objects.equal("a", null));
        System.out.println(Objects.equal(null, null));
    }

    @Test
    public void compareTo() {
        Person ddl = new Person("a", 14);
        Person ldd = new Person("b", 14);
        int result = ComparisonChain.start()
                //.compare(ddl.getAge(), ldd.getAge())
                .compare(ddl.getName(), ldd.getName(), Ordering.natural().nullsLast().reverse())
                .result();
        System.out.println(result);
    }

    @Test
    public void orderTest() {
        List<Person> persons = Lists.newArrayList(
                new Person("chang", 15),
                new Person("ahang", 16),
                new Person("bee", 18)
        );
        Ordering<Person> ordering = Ordering.natural().nullsFirst().onResultOf(new Function<Person, String>() {
            @Override
            public String apply(Person foo) {
                return foo.getName();
            }
        });
        List<Person> people = ordering.sortedCopy(persons);
        List<Person> people1 = ordering.greatestOf(persons, 2);
        Person people2 = ordering.min(persons);

    }
}
