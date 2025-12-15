package com.ruoyi.asset.domain.enums;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * AssetStatusEnum Transition Test
 *
 * Tests for AssetStatusEnum state transitions and validation
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@RunWith(MockitoJUnitRunner.class)
public class AssetStatusEnumTransitionTest {

    @Test
    public void shouldDefineAllRequiredStatusValuesInAssetStatusEnum() {
        // Test that all required status values exist
        List<AssetStatusEnum> statusValues = Arrays.asList(
            AssetStatusEnum.UNDER_CONSTRUCTION,
            AssetStatusEnum.AVAILABLE_SELF,
            AssetStatusEnum.AVAILABLE_RENT,
            AssetStatusEnum.AVAILABLE_IDLE,
            AssetStatusEnum.TEMP_CLOSED,
            AssetStatusEnum.FAULT,
            AssetStatusEnum.MAINTAINING,
            AssetStatusEnum.TO_BE_SCRAPPED,
            AssetStatusEnum.SCRAPPED
        );

        assertNotNull("UNDER_CONSTRUCTION status should exist", AssetStatusEnum.UNDER_CONSTRUCTION);
        assertNotNull("AVAILABLE_SELF status should exist", AssetStatusEnum.AVAILABLE_SELF);
        assertNotNull("AVAILABLE_RENT status should exist", AssetStatusEnum.AVAILABLE_RENT);
        assertNotNull("AVAILABLE_IDLE status should exist", AssetStatusEnum.AVAILABLE_IDLE);
        assertNotNull("TEMP_CLOSED status should exist", AssetStatusEnum.TEMP_CLOSED);
        assertNotNull("FAULT status should exist", AssetStatusEnum.FAULT);
        assertNotNull("MAINTAINING status should exist", AssetStatusEnum.MAINTAINING);
        assertNotNull("TO_BE_SCRAPPED status should exist", AssetStatusEnum.TO_BE_SCRAPPED);
        assertNotNull("SCRAPPED status should exist", AssetStatusEnum.SCRAPPED);

        assertEquals("Should have 9 status values", 9, statusValues.size());
    }

    @Test
    public void shouldHaveTransitionValidationMapConfiguredForEachStatus() {
        // Test that each status has valid transitions configured
        for (AssetStatusEnum status : AssetStatusEnum.values()) {
            assertNotNull("Status " + status + " should have valid transitions",
                status.getValidTransitions());
        }

        // SCRAPPED should have no valid transitions (terminal state)
        assertNotNull("SCRAPPED should have valid transitions (empty list)",
            AssetStatusEnum.SCRAPPED.getValidTransitions());
        assertTrue("SCRAPPED should have empty valid transitions",
            AssetStatusEnum.SCRAPPED.getValidTransitions().isEmpty());
    }

    @Test
    public void shouldReturnTrueForValidStateTransitionsViaCanTransitionTo() {
        // Test valid transitions
        assertTrue("UNDER_CONSTRUCTION should transition to AVAILABLE_SELF",
            AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF));
        assertTrue("AVAILABLE_SELF should transition to MAINTAINING",
            AssetStatusEnum.AVAILABLE_SELF.canTransitionTo(AssetStatusEnum.MAINTAINING));
        assertTrue("AVAILABLE_RENT should transition to FAULT",
            AssetStatusEnum.AVAILABLE_RENT.canTransitionTo(AssetStatusEnum.FAULT));
        assertTrue("AVAILABLE_IDLE should transition to TEMP_CLOSED",
            AssetStatusEnum.AVAILABLE_IDLE.canTransitionTo(AssetStatusEnum.TEMP_CLOSED));
        assertTrue("MAINTAINING should transition to AVAILABLE_SELF",
            AssetStatusEnum.MAINTAINING.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF));
        assertTrue("TO_BE_SCRAPPED should transition to SCRAPPED",
            AssetStatusEnum.TO_BE_SCRAPPED.canTransitionTo(AssetStatusEnum.SCRAPPED));
    }

    @Test
    public void shouldReturnFalseForInvalidStateTransitionsViaCanTransitionTo() {
        // Test invalid transitions
        assertFalse("SCRAPPED should not transition to AVAILABLE_SELF",
            AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.AVAILABLE_SELF));
        assertFalse("SCRAPPED should not transition to UNDER_CONSTRUCTION",
            AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.UNDER_CONSTRUCTION));
        assertFalse("SCRAPPED should not transition to MAINTAINING",
            AssetStatusEnum.SCRAPPED.canTransitionTo(AssetStatusEnum.MAINTAINING));
        assertFalse("UNDER_CONSTRUCTION should not transition to SCRAPPED directly",
            AssetStatusEnum.UNDER_CONSTRUCTION.canTransitionTo(AssetStatusEnum.SCRAPPED));
    }

    @Test
    public void shouldReturnCorrectSetOfValidTransitionsViaGetValidTransitions() {
        // Test getValidTransitions method
        assertNotNull("AVAILABLE_SELF should have valid transitions",
            AssetStatusEnum.AVAILABLE_SELF.getValidTransitions());
        assertTrue("AVAILABLE_SELF should have valid transitions",
            AssetStatusEnum.AVAILABLE_SELF.getValidTransitions().size() > 0);

        assertNotNull("SCRAPPED should have valid transitions",
            AssetStatusEnum.SCRAPPED.getValidTransitions());
        assertEquals("SCRAPPED should have empty valid transitions",
            0, AssetStatusEnum.SCRAPPED.getValidTransitions().size());

        // Verify that valid transitions include expected statuses
        assertTrue("AVAILABLE_SELF transitions should include MAINTAINING",
            AssetStatusEnum.AVAILABLE_SELF.getValidTransitions().contains(AssetStatusEnum.MAINTAINING));
        assertTrue("MAINTAINING transitions should include AVAILABLE_SELF",
            AssetStatusEnum.MAINTAINING.getValidTransitions().contains(AssetStatusEnum.AVAILABLE_SELF));
    }

    @Test
    public void shouldHaveCorrectStatusCodeAndDescription() {
        // Test that each status has code and description
        for (AssetStatusEnum status : AssetStatusEnum.values()) {
            assertNotNull("Status " + status + " should have code",
                status.getCode());
            assertNotNull("Status " + status + " should have description",
                status.getDescription());
            assertFalse("Status " + status + " code should not be empty",
                status.getCode().isEmpty());
            assertFalse("Status " + status + " description should not be empty",
                status.getDescription().isEmpty());
        }
    }

    @Test
    public void shouldFindStatusByCode() {
        // Test fromCode method
        assertEquals("Should find UNDER_CONSTRUCTION by code",
            AssetStatusEnum.UNDER_CONSTRUCTION, AssetStatusEnum.fromCode("under_construction"));
        assertEquals("Should find AVAILABLE_SELF by code",
            AssetStatusEnum.AVAILABLE_SELF, AssetStatusEnum.fromCode("available_self"));
        assertEquals("Should find SCRAPPED by code",
            AssetStatusEnum.SCRAPPED, AssetStatusEnum.fromCode("scrapped"));
    }

    @Test
    public void shouldReturnNullForInvalidStatusCode() {
        // Test fromCode with invalid code
        assertNull("Should return null for invalid code",
            AssetStatusEnum.fromCode("INVALID_STATUS"));
        assertNull("Should return null for null code",
            AssetStatusEnum.fromCode(null));
        assertNull("Should return null for empty code",
            AssetStatusEnum.fromCode(""));
    }
}
