package com.ruoyi.asset.mapper;

import com.ruoyi.asset.domain.entity.AssetVenueUsage;
import com.ruoyi.asset.domain.vo.VenueUsageStatisticsVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Venue Usage Mapper Interface
 */
@Mapper
public interface AssetVenueUsageMapper {

    /**
     * Query venue usage list
     *
     * @param query query parameters
     * @return list of venue usage records
     */
    List<AssetVenueUsage> selectVenueUsageList(VenueUsageQuery query);

    /**
     * Query venue usage by ID
     *
     * @param id usage record ID
     * @return venue usage record
     */
    AssetVenueUsage selectVenueUsageById(@Param("id") Long id);

    /**
     * Insert venue usage record
     *
     * @param venueUsage venue usage entity
     * @return affected rows
     */
    int insertVenueUsage(AssetVenueUsage venueUsage);

    /**
     * Update venue usage record
     *
     * @param venueUsage venue usage entity
     * @return affected rows
     */
    int updateVenueUsage(AssetVenueUsage venueUsage);

    /**
     * Delete venue usage by ID
     *
     * @param id usage record ID
     * @return affected rows
     */
    int deleteVenueUsageById(@Param("id") Long id);

    /**
     * Batch delete venue usage records
     *
     * @param ids usage record IDs
     * @return affected rows
     */
    int deleteVenueUsageByIds(@Param("ids") Long[] ids);

    /**
     * Get overall usage statistics
     *
     * @param venueId   optional venue filter
     * @param startDate optional start date
     * @param endDate   optional end date
     * @return overall statistics
     */
    VenueUsageStatisticsVO selectOverallStatistics(
            @Param("venueId") Long venueId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    /**
     * Get time slot statistics
     *
     * @param venueId   optional venue filter
     * @param startDate optional start date
     * @param endDate   optional end date
     * @return time slot statistics
     */
    List<VenueUsageStatisticsVO.TimeSlotStatVO> selectTimeSlotStatistics(
            @Param("venueId") Long venueId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    /**
     * Get usage trend
     *
     * @param venueId   optional venue filter
     * @param startDate optional start date
     * @param endDate   optional end date
     * @return usage trend by date
     */
    List<VenueUsageStatisticsVO.UsageTrendVO> selectUsageTrend(
            @Param("venueId") Long venueId,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    /**
     * Get top organizers
     *
     * @param venueId   optional venue filter
     * @param limit     number of top organizers
     * @param startDate optional start date
     * @param endDate   optional end date
     * @return top organizers
     */
    List<VenueUsageStatisticsVO.OrganizerStatVO> selectTopOrganizers(
            @Param("venueId") Long venueId,
            @Param("limit") Integer limit,
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate);

    /**
     * Query parameters class for venue usage
     */
    class VenueUsageQuery {
        private Long venueId;
        private Date startDate;
        private Date endDate;
        private String timeSlot;
        private String usageType;
        private String organizer;
        private String status;

        public Long getVenueId() {
            return venueId;
        }

        public void setVenueId(Long venueId) {
            this.venueId = venueId;
        }

        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }

        public String getTimeSlot() {
            return timeSlot;
        }

        public void setTimeSlot(String timeSlot) {
            this.timeSlot = timeSlot;
        }

        public String getUsageType() {
            return usageType;
        }

        public void setUsageType(String usageType) {
            this.usageType = usageType;
        }

        public String getOrganizer() {
            return organizer;
        }

        public void setOrganizer(String organizer) {
            this.organizer = organizer;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }
    }
}
