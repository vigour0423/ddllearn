package com.ddl.guava.entity;

/**
 * @author dongdongliu
 * @version 1.0
 */
public class City {
    private String cityName;

    private Integer population;

    private Double averageRainfall;

    public City(String cityName, Integer population, Double averageRainfall) {
        this.cityName = cityName;
        this.population = population;
        this.averageRainfall = averageRainfall;
    }

    public String getCityName() { return cityName; }

    public void setCityName(String cityName) { this.cityName = cityName; }

    public Integer getPopulation() { return population; }

    public void setPopulation(Integer population) { this.population = population; }

    public Double getAverageRainfall() { return averageRainfall; }

    public void setAverageRainfall(Double averageRainfall) { this.averageRainfall = averageRainfall; }
}