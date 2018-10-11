package com.ddl.guava.collection.multi;

import com.ddl.guava.entity.Person;
import com.google.common.collect.HashMultiset;
import com.google.common.collect.Lists;
import com.google.common.collect.Multiset;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class MultisetTest {

    @Test
    public void hashMultisetTest() {
        String strWorld = "wer|dfd|dd|dfd|dda|de|dr";
        String[] words = strWorld.split("\\|");
        List<String> wordList = new ArrayList<>(Arrays.asList(words));

        //可以很方便的实现计数功能
        Multiset<String> wordsMultiset = HashMultiset.create();

        //自定义设置数量，不可以为负数
        wordsMultiset.setCount("dd", 3);
        wordsMultiset.addAll(wordList);


        System.out.println("多重集合大大小:" + wordsMultiset.size());
        for (String key : wordsMultiset.elementSet()) {
            System.out.println(key + " count：" + wordsMultiset.count(key));
        }

        //增加给定元素在 Multiset 中的计数
        wordsMultiset.add("dd", 1);
        System.out.println("========================================");
        for (Multiset.Entry<String> entry : wordsMultiset.entrySet()) {
            System.out.println(entry.getElement() + " count：" + entry.getCount());
        }

        wordsMultiset.remove("de", 1);


    }


}
