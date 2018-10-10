package com.ddl.guava.ordering;


import com.ddl.guava.entity.City;
import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;
import com.google.common.primitives.Ints;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class CityByPopluation implements Comparator<City> {
    @Override
    public int compare(City city1, City city2) { return Ints.compare(city1.getPopulation(), city2.getPopulation()); }

    public static void main(String[] args) {
        CityByPopluation cityByPopluation = new CityByPopluation();
        CityByRainfall cityByRainfall = new CityByRainfall();

        //返回基于现有的比较实例进行排序。
        Ordering<City> from = Ordering.from(cityByRainfall);

        // 根据第二个参数排序
        City city1 = new City("Beijing", 100000, 55.0);
        City city2 = new City("Shanghai", 100000, 45.0);
        City city3 = new City("ShenZhen", 100000, 33.8);
        List<City> cities = Lists.newArrayList(city1, city2, city3);

        // 排序反转
        Ordering<City> firstOrdering = from.reverse();
        Collections.sort(cities, firstOrdering);

        Iterator<City> cityByRainfallIterator = cities.iterator();
        while (cityByRainfallIterator.hasNext()) {
            System.out.println(cityByRainfallIterator.next().getCityName());
        }
        System.out.println("==============================");


        Collections.sort(cities, Ordering.from(cityByPopluation));

        for (City city : cities) {
            System.out.println(city.getCityName());
        }
        System.out.println("使用compound合成另一个比较器后");

        //(多参数排序)compound合成另一个比较器，以处理当前排序器中的相等情况。

        /*注：用 compound 方法包装排序器时，就不应遵循从后往前读的原则。为了避免理解上的混乱，请不要把 com
        pound 写在一长串链式调用的中间，你可以另起一行，在链中最先或最后调用 compound。*/
        Ordering<City> secondaryOrdering = Ordering.from(cityByPopluation).compound(cityByRainfall);
        Collections.sort(cities, secondaryOrdering);

        for (City city : cities) {
            System.out.println(city.getCityName());
        }


        // 降雨量最高的2个城市
        List<City> topTwo = from.greatestOf(cities, 2);
        for (City aTopTwo : topTwo) {
            System.out.println("降雨量最高城市" + aTopTwo.getCityName());
        }
        // 降雨量最低的一个城市
        List<City> bottomOne = from.leastOf(cities, 1);
        for (City aBottomOne : bottomOne) {
            System.out.println("降雨量最低的城市" + aBottomOne.getCityName());
        }
    }

}
