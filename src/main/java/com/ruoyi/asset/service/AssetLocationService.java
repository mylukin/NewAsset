package com.ruoyi.asset.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.asset.domain.entity.AssetLocation;
import com.ruoyi.asset.mapper.AssetLocationMapper;
import com.ruoyi.common.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Asset Location Service
 *
 * @author ruoyi
 * @date 2025-12-15
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class AssetLocationService extends ServiceImpl<AssetLocationMapper, AssetLocation> {

    /**
     * Get hierarchical tree structure for a project
     */
    public List<Map<String, Object>> getTreeByProject(Long projectId) {
        List<AssetLocation> locations = baseMapper.selectByProjectId(projectId);
        return buildTree(locations);
    }

    /**
     * Add new location (building/floor/unit)
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean addLocation(AssetLocation location) {
        validateLocation(location);
        return baseMapper.insert(location) > 0;
    }

    /**
     * Update existing location
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean updateLocation(AssetLocation location) {
        validateLocation(location);
        return baseMapper.updateById(location) > 0;
    }

    /**
     * Delete location (check for children and linked assets)
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteLocation(Long id) {
        // Check for children
        List<AssetLocation> children = baseMapper.selectChildrenByParentId(id);
        if (!children.isEmpty()) {
            throw new ServiceException("Cannot delete location with children");
        }

        // Check for linked assets
        // TODO: Check if any assets are linked to this location

        // Soft delete
        AssetLocation location = new AssetLocation();
        location.setId(id);
        location.setDelFlag("2");
        return baseMapper.updateById(location) > 0;
    }

    /**
     * Validate location hierarchy
     */
    private void validateLocation(AssetLocation location) {
        if (location.getLevel() == null) {
            throw new ServiceException("Location level is required");
        }

        switch (location.getLevel()) {
            case 1: // Building
                if (location.getParentId() != null && location.getParentId() != 0) {
                    throw new ServiceException("Building cannot have a parent");
                }
                location.setParentId(0L);
                break;
            case 2: // Floor
                if (location.getParentId() == null || location.getParentId() == 0) {
                    throw new ServiceException("Floor must have a building parent");
                }
                // Verify parent is level 1
                AssetLocation parent = baseMapper.selectById(location.getParentId());
                if (parent == null || parent.getLevel() != 1) {
                    throw new ServiceException("Floor parent must be a building");
                }
                break;
            case 3: // Unit
                if (location.getParentId() == null || location.getParentId() == 0) {
                    throw new ServiceException("Unit must have a floor parent");
                }
                // Verify parent is level 2
                AssetLocation floorParent = baseMapper.selectById(location.getParentId());
                if (floorParent == null || floorParent.getLevel() != 2) {
                    throw new ServiceException("Unit parent must be a floor");
                }
                break;
            default:
                throw new ServiceException("Invalid location level: " + location.getLevel());
        }
    }

    /**
     * Build hierarchical tree from flat list
     */
    private List<Map<String, Object>> buildTree(List<AssetLocation> locations) {
        Map<Long, List<AssetLocation>> childrenMap = locations.stream()
            .filter(loc -> loc.getParentId() != null && loc.getParentId() != 0)
            .collect(Collectors.groupingBy(AssetLocation::getParentId));

        List<AssetLocation> roots = locations.stream()
            .filter(loc -> loc.getParentId() == null || loc.getParentId() == 0)
            .sorted(Comparator.comparing(AssetLocation::getSortOrder))
            .collect(Collectors.toList());

        return roots.stream()
            .map(root -> buildNode(root, childrenMap))
            .collect(Collectors.toList());
    }

    /**
     * Build tree node recursively
     */
    private Map<String, Object> buildNode(AssetLocation location, Map<Long, List<AssetLocation>> childrenMap) {
        Map<String, Object> node = new HashMap<>();
        node.put("id", location.getId());
        node.put("code", location.getCode());
        node.put("name", location.getName());
        node.put("level", location.getLevel());

        List<AssetLocation> children = childrenMap.get(location.getId());
        if (children != null && !children.isEmpty()) {
            node.put("children", children.stream()
                .sorted(Comparator.comparing(AssetLocation::getSortOrder))
                .map(child -> buildNode(child, childrenMap))
                .collect(Collectors.toList()));
        }

        return node;
    }
}
