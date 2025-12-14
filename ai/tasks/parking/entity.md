---
id: parking.entity
module: parking
priority: 1
status: passing
version: 1
origin: manual
dependsOn: []
supersedes: []
tags: []
tddGuidance:
  generatedAt: '2025-12-14T04:52:00.471Z'
  generatedBy: codex
  forVersion: 1
  suggestedTestFiles:
    unit:
      - >-
        ruoyi-modules/ruoyi-asset/src/test/java/com/ruoyi/asset/parking/domain/ParkingAssetTest.java
    e2e: []
  unitTestCases:
    - name: 'should create ParkingAsset with zone, type, and rental info'
      assertions:
        - 'assertEquals("Z1", parkingAsset.getZoneCode())'
        - 'assertEquals(ParkingType.COVERED, parkingAsset.getType())'
        - >-
          assertEquals(new BigDecimal("20.00"),
          parkingAsset.getRentalInfo().getDailyRate())
        - 'assertEquals("CNY", parkingAsset.getRentalInfo().getCurrency())'
    - name: should reject creation when zone is blank
      assertions:
        - >-
          assertThrows(IllegalArgumentException.class, () ->
          ParkingAsset.create(" ", ParkingType.COVERED, rentalInfo))
    - name: should reject creation when type is null
      assertions:
        - >-
          assertThrows(NullPointerException.class, () ->
          ParkingAsset.create("Z1", null, rentalInfo))
    - name: >-
        should mark asset as rented only when rental info indicates an active
        rental period
      assertions:
        - assertTrue(parkingAsset.isRented())
        - >-
          assertEquals(LocalDate.parse("2025-01-01"),
          parkingAsset.getRentalInfo().getStartDate())
        - >-
          assertEquals(LocalDate.parse("2025-01-31"),
          parkingAsset.getRentalInfo().getEndDate())
        - >-
          assertThrows(IllegalArgumentException.class, () -> RentalInfo.of(new
          BigDecimal("20.00"), "CNY", LocalDate.parse("2025-02-01"),
          LocalDate.parse("2025-01-31")))
  e2eScenarios: []
  frameworkHint: junit-jupiter
---
# Create parking asset entity with zone, type, rental info
