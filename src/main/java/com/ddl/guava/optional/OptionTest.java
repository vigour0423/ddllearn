package com.ddl.guava.optional;

import com.ddl.guava.entity.Person;
import com.google.common.base.Optional;
import com.google.common.base.Supplier;
import org.junit.Test;

import java.util.Set;


/**
 * @author dongdongliu
 * @version 1.0
 */
public class OptionTest {

    public Integer sum(Optional<Integer> a, Optional<Integer> b) {
        return a.get() + b.get();
    }

    public Integer sum(Integer a, Integer b) {
        return a + b;
    }

    @Test
    public void optionTest1() {
        OptionTest guavaTester = new OptionTest();
        Integer a = null;
        Integer b = 10;
        System.out.println(guavaTester.sum(a, b));


        Optional<Integer> optionala = Optional.of(a);
        Optional<Integer> optionalb = Optional.of(b);

        System.out.println(guavaTester.sum(optionala, optionalb));

    }

    @Test
    public void optionTest2() {
        //创建Optional实例，也可以通过方法返回值得到。
        Optional<Integer> of = Optional.of(5);

        //创建没有值的Optional实例，例如值为'null'
        Optional<Object> empty = Optional.fromNullable(null);

        //判断是否存在
        boolean present = of.isPresent();
        if (present) {
            //调用get()返回Optional值。
            System.out.println(of.get());
        }

        //返回所包含的实例(如果存在);否则返回null。
        Integer integer = of.orNull();

        //返回一个不可变的单集的唯一元素所包含的实例(如果存在);否则为一个空的不可变的集合。
        Set<Integer> integers = of.asSet();
        Set<Object> emptys = empty.asSet();
        try {
            System.out.println("存在实列返回的集合大小:" + integers.size() + "  不存在实列返回的集合大小:" + emptys.size());
            integers.add(3);
        } catch (UnsupportedOperationException e) {
            System.out.println("不可变的操作异常");
        }


        //如果为空返回默认值
        Integer integer1 = (Integer) Optional.fromNullable(null).or(1);
        Integer integer2 = Optional.fromNullable(5).or(1);
        System.out.println("实列为空返回默认值:" + integer1 + "  实列不为空返回实列:" + integer2);

        Person eee = new Person("lisi", 22);
        Person person = getPerson(null);


        System.out.println(person);


    }

    private Person getPerson(Person person) {

        Person p = Optional.fromNullable(person).or(new Supplier<Person>() {
            @Override
            public Person get() {
                return new Person("eee2", 222);
            }
        });
        //lambda写法
        p = Optional.fromNullable(person).or(() -> new Person("zhangsang", 222));
        return p;
    }


}
