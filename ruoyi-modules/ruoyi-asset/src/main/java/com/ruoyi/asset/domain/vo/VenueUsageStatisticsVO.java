package com.ruoyi.asset.domain.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * Venue usage statistics view object
 */
public class VenueUsageStatisticsVO implements Serializable {

    private static final long serialVersionUID = 1L;

    // Overall statistics
    private Integer totalUsageCount;
    private Integer totalAttendance;
    private Integer totalBookings;
    private BigDecimal avgAttendance;
    private BigDecimal avgUsageDuration;
    private BigDecimal averageUtilization;

    // Usage frequency
    private Integer usageFrequencyDaily;
    private Integer usageFrequencyWeekly;
    private Integer usageFrequencyMonthly;

    // Popular time slots
    private List<TimeSlotStatVO> popularTimeSlots;

    // Usage by type breakdown
    private Map<String, Integer> usageByType;

    // Usage trend (by date)
    private List<UsageTrendVO> usageTrend;

    // Top organizers
    private List<OrganizerStatVO> topOrganizers;

    // Getters and Setters

    public Integer getTotalUsageCount() {
        return totalUsageCount;
    }

    public void setTotalUsageCount(Integer totalUsageCount) {
        this.totalUsageCount = totalUsageCount;
    }

    public Integer getTotalAttendance() {
        return totalAttendance;
    }

    public void setTotalAttendance(Integer totalAttendance) {
        this.totalAttendance = totalAttendance;
    }

    public Integer getTotalBookings() {
        return totalBookings;
    }

    public void setTotalBookings(Integer totalBookings) {
        this.totalBookings = totalBookings;
    }

    public BigDecimal getAverageUtilization() {
        return averageUtilization;
    }

    public void setAverageUtilization(BigDecimal averageUtilization) {
        this.averageUtilization = averageUtilization;
    }

    public BigDecimal getAvgAttendance() {
        return avgAttendance;
    }

    public void setAvgAttendance(BigDecimal avgAttendance) {
        this.avgAttendance = avgAttendance;
    }

    public BigDecimal getAvgUsageDuration() {
        return avgUsageDuration;
    }

    public void setAvgUsageDuration(BigDecimal avgUsageDuration) {
        this.avgUsageDuration = avgUsageDuration;
    }

    public Integer getUsageFrequencyDaily() {
        return usageFrequencyDaily;
    }

    public void setUsageFrequencyDaily(Integer usageFrequencyDaily) {
        this.usageFrequencyDaily = usageFrequencyDaily;
    }

    public Integer getUsageFrequencyWeekly() {
        return usageFrequencyWeekly;
    }

    public void setUsageFrequencyWeekly(Integer usageFrequencyWeekly) {
        this.usageFrequencyWeekly = usageFrequencyWeekly;
    }

    public Integer getUsageFrequencyMonthly() {
        return usageFrequencyMonthly;
    }

    public void setUsageFrequencyMonthly(Integer usageFrequencyMonthly) {
        this.usageFrequencyMonthly = usageFrequencyMonthly;
    }

    public List<TimeSlotStatVO> getPopularTimeSlots() {
        return popularTimeSlots;
    }

    public void setPopularTimeSlots(List<TimeSlotStatVO> popularTimeSlots) {
        this.popularTimeSlots = popularTimeSlots;
    }

    public Map<String, Integer> getUsageByType() {
        return usageByType;
    }

    public void setUsageByType(Map<String, Integer> usageByType) {
        this.usageByType = usageByType;
    }

    public List<UsageTrendVO> getUsageTrend() {
        return usageTrend;
    }

    public void setUsageTrend(List<UsageTrendVO> usageTrend) {
        this.usageTrend = usageTrend;
    }

    public List<OrganizerStatVO> getTopOrganizers() {
        return topOrganizers;
    }

    public void setTopOrganizers(List<OrganizerStatVO> topOrganizers) {
        this.topOrganizers = topOrganizers;
    }

    /**
     * Time slot statistics
     */
    public static class TimeSlotStatVO implements Serializable {
        private String timeSlot;
        private String timeSlotLabel;
        private Integer usageCount;
        private BigDecimal percentage;

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }

        public String getTimeSlotLabel() {
            return timeSlotLabel;
        }

        public void setTimeSlotLabel(String timeSlotLabel) {
            this.timeSlotLabel = timeSlotLabel;
        }

        public Integer getUsageCount() {
            return usageCount;
        }

        public void setUsageCount(Integer usageCount) {
            this.usageCount = usageCount;
        }

        public BigDecimal getPercentage() {
            return percentage;
        }

        public void setPercentage(BigDecimal percentage) {
            this.percentage = percentage;
        }
    }

    /**
     * Usage trend by date
     */
    public static class UsageTrendVO implements Serializable {
        private String date;
        private Integer usageCount;
        private Integer attendanceCount;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public Integer getUsageCount() {
            return usageCount;
        }

        public void setUsageCount(Integer usageCount) {
            this.usageCount = usageCount;
        }

        public Integer getAttendanceCount() {
            return attendanceCount;
        }

        public void setAttendanceCount(Integer attendanceCount) {
            this.attendanceCount = attendanceCount;
        }
    }

    /**
     * Organizer statistics
     */
    public static class OrganizerStatVO implements Serializable {
        private String organizer;
        private Integer usageCount;
        private Integer totalAttendance;

        public String getOrganizer() {
            return organizer;
        }

        public void setOrganizer(String organizer) {
            this.organizer = organizer;
        }

        public Integer getUsageCount() {
            return usageCount;
        }

        public void setUsageCount(Integer usageCount) {
            this.usageCount = usageCount;
        }

        public Integer getTotalAttendance() {
            return totalAttendance;
        }

        public void setTotalAttendance(Integer totalAttendance) {
            this.totalAttendance = totalAttendance;
        }
    }
}
