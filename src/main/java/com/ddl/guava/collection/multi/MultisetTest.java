package com.ddl.guava.collection.multi;

import com.google.common.collect.HashMultiset;
import com.google.common.collect.Multiset;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class MultisetTest {

    private List<String> wordList;

    @Before
    public void init() {
        String strWorld = "wer|dfd|dd|dfd|dda|de|dr";
        String[] words = strWorld.split("\\|");
        wordList = new ArrayList<>(Arrays.asList(words));
    }

    @Test
    public void hashMultisetTest() {
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

    @Test
    public void mapCount() {
        Map<String, Integer> counts = new HashMap<>(10);
        for (String word : wordList) {
            //1.8后可通过merge进行操作
            counts.merge(word, 1, (a, b) -> a + b);
        }

        for (String word : wordList) {
            Integer count = counts.get(word);
            if (count == null) {
                counts.put(word, 1);
            } else {
                counts.put(word, count + 1);
            }
        }
    }

}
