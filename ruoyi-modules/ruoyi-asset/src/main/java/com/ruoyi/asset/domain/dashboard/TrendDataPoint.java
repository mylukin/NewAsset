package com.ruoyi.asset.domain.dashboard;

import java.math.BigDecimal;

/**
 * Trend Data Point for charts
 *
 * @author ruoyi
 */
public class TrendDataPoint {

    /** Date string (yyyy-MM-dd) */
    private String date;

    /** Value */
    private BigDecimal value;

    /** Label */
    private String label;

    public TrendDataPoint() {
    }

    public TrendDataPoint(String date, BigDecimal value) {
        this.date = date;
        this.value = value;
    }

    public TrendDataPoint(String date, BigDecimal value, String label) {
        this.date = date;
        this.value = value;
        this.label = label;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public BigDecimal getValue() {
        return value;
    }

    public void setValue(BigDecimal value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }
}
