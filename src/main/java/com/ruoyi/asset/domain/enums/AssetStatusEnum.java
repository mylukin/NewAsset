package com.ruoyi.asset.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.*;

/**
 * Asset Status Enum - 9-state status machine
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Getter
@AllArgsConstructor
public enum AssetStatusEnum {

    /**
     * Under construction/not yet operational
     */
    UNDER_CONSTRUCTION("under_construction", "在建/未投入"),

    /**
     * Available for self-use
     */
    AVAILABLE_SELF("available_self", "可用-自用"),

    /**
     * Available for rent to external parties
     */
    AVAILABLE_RENT("available_rent", "可用-对外出租"),

    /**
     * Available but idle/not in use
     */
    AVAILABLE_IDLE("available_idle", "可用-空置"),

    /**
     * Temporarily closed/suspended
     */
    TEMP_CLOSED("temp_closed", "暂停使用"),

    /**
     * Faulty/disabled
     */
    FAULT("fault", "故障/停用"),

    /**
     * Under maintenance
     */
    MAINTAINING("maintaining", "维修中"),

    /**
     * Planned to be scrapped
     */
    TO_BE_SCRAPPED("to_be_scrapped", "计划报废"),

    /**
     * Already scrapped (terminal state)
     */
    SCRAPPED("scrapped", "已报废");

    private final String code;
    private final String description;

    private static final Map<AssetStatusEnum, Set<AssetStatusEnum>> TRANSITION_MAP = new HashMap<>();

    static {
        // UNDER_CONSTRUCTION can only go to AVAILABLE_* states
        TRANSITION_MAP.put(UNDER_CONSTRUCTION, new HashSet<>(Arrays.asList(
            AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE
        )));

        // AVAILABLE_* states can transition to maintenance, fault, temp_closed, or to_be_scrapped
        Set<AssetStatusEnum> availableTransitions = new HashSet<>(Arrays.asList(
            MAINTAINING, FAULT, TEMP_CLOSED, TO_BE_SCRAPPED
        ));
        TRANSITION_MAP.put(AVAILABLE_SELF, availableTransitions);
        TRANSITION_MAP.put(AVAILABLE_RENT, availableTransitions);
        TRANSITION_MAP.put(AVAILABLE_IDLE, availableTransitions);

        // TEMP_CLOSED can go back to AVAILABLE_* states
        TRANSITION_MAP.put(TEMP_CLOSED, new HashSet<>(Arrays.asList(
            AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE
        )));

        // FAULT can go back to AVAILABLE_* states or TO_BE_SCRAPPED
        TRANSITION_MAP.put(FAULT, new HashSet<>(Arrays.asList(
            AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE, TO_BE_SCRAPPED
        )));

        // MAINTAINING can go back to original status (AVAILABLE_* states)
        TRANSITION_MAP.put(MAINTAINING, new HashSet<>(Arrays.asList(
            AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE
        )));

        // TO_BE_SCRAPPED can go to SCRAPPED or back to AVAILABLE_* states
        TRANSITION_MAP.put(TO_BE_SCRAPPED, new HashSet<>(Arrays.asList(
            SCRAPPED, AVAILABLE_SELF, AVAILABLE_RENT, AVAILABLE_IDLE
        )));

        // SCRAPPED is terminal - no valid transitions out
        TRANSITION_MAP.put(SCRAPPED, new HashSet<>());
    }

    /**
     * Check if transition to target status is valid
     *
     * @param target target status
     * @return true if transition is valid, false otherwise
     */
    public boolean canTransitionTo(AssetStatusEnum target) {
        if (target == null) {
            return false;
        }
        Set<AssetStatusEnum> validTransitions = TRANSITION_MAP.get(this);
        return validTransitions != null && validTransitions.contains(target);
    }

    /**
     * Get all valid transitions from this status
     *
     * @return Set of valid target statuses
     */
    public Set<AssetStatusEnum> getValidTransitions() {
        Set<AssetStatusEnum> validTransitions = TRANSITION_MAP.get(this);
        return validTransitions != null ? new HashSet<>(validTransitions) : Collections.emptySet();
    }
}
