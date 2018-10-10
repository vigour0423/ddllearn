package com.ddl.guava.ordering;

import com.ddl.guava.entity.City;
import com.google.common.primitives.Doubles;

import java.util.Comparator;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class CityByRainfall implements Comparator<City> {
    @Override
    public int compare(City city1, City city2) {
        return Doubles.compare(city1.getAverageRainfall(), city2.getAverageRainfall());

    }
}
