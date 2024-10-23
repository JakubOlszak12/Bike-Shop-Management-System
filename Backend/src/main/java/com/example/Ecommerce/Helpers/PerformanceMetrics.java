package com.example.Ecommerce.Helpers;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PerformanceMetrics {
    private double median;
    private double min;
    private double max;
    private double average;
    private double stdDev;

    public PerformanceMetrics(double median, double min, double max, double average, double stdDev) {
        this.median = median;
        this.min = min;
        this.max = max;
        this.average = average;
        this.stdDev = stdDev;
    }
}
