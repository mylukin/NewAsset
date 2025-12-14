package com.ruoyi.common.datascope.aspect;

import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.datascope.annotation.DataScope;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * Data scope aspect for filtering data by project/dept/user
 *
 * Data scope types:
 * 1. ALL - No filtering, access all data
 * 2. PROJECT_ONLY - Filter by user's assigned projects
 * 3. DEPT_ONLY - Filter by user's department
 * 4. DEPT_AND_CHILD - Filter by user's department and sub-departments
 * 5. SELF_ONLY - Filter by user ID only
 *
 * SQL patterns applied via ${params.dataScope} placeholder in mapper XMLs:
 * - PROJECT_ONLY: AND {alias}.project_id IN (user's project list)
 * - DEPT_ONLY: AND {alias}.use_dept_id = (user's dept_id)
 * - SELF_ONLY: AND {alias}.duty_user_id = (user_id)
 */
@Aspect
@Component
public class DataScopeAspect {

    /**
     * Data scope type constants
     */
    public static final String DATA_SCOPE_ALL = "1";
    public static final String DATA_SCOPE_PROJECT = "2";
    public static final String DATA_SCOPE_DEPT = "3";
    public static final String DATA_SCOPE_DEPT_AND_CHILD = "4";
    public static final String DATA_SCOPE_SELF = "5";

    /**
     * Placeholder for data scope SQL in mapper XMLs
     */
    public static final String DATA_SCOPE_PARAM = "dataScope";

    @Before("@annotation(dataScope)")
    public void doBefore(JoinPoint point, DataScope dataScope) {
        clearDataScope(point);
        handleDataScope(point, dataScope);
    }

    /**
     * Handle data scope filtering
     */
    protected void handleDataScope(JoinPoint joinPoint, DataScope dataScope) {
        // Get current user info from security context
        // In real implementation, this would get from SecurityUtils
        String dataScopeType = getCurrentUserDataScope();
        Long userId = getCurrentUserId();
        Long deptId = getCurrentUserDeptId();

        if (DATA_SCOPE_ALL.equals(dataScopeType)) {
            return; // No filtering needed
        }

        StringBuilder sqlString = new StringBuilder();
        String projectAlias = dataScope.projectAlias();
        String deptAlias = dataScope.deptAlias();
        String userAlias = dataScope.userAlias();

        if (DATA_SCOPE_PROJECT.equals(dataScopeType)) {
            // Filter by project - user can only see assets from assigned projects
            if (!"".equals(projectAlias)) {
                sqlString.append(String.format(
                        " AND %s.project_id IN (SELECT project_id FROM sys_user_project WHERE user_id = %d)",
                        projectAlias, userId));
            }
        } else if (DATA_SCOPE_DEPT.equals(dataScopeType)) {
            // Filter by department only
            if (!"".equals(deptAlias)) {
                sqlString.append(String.format(" AND %s.use_dept_id = %d", deptAlias, deptId));
            }
        } else if (DATA_SCOPE_DEPT_AND_CHILD.equals(dataScopeType)) {
            // Filter by department and child departments
            if (!"".equals(deptAlias)) {
                sqlString.append(String.format(
                        " AND %s.use_dept_id IN (SELECT dept_id FROM sys_dept WHERE dept_id = %d OR find_in_set(%d, ancestors))",
                        deptAlias, deptId, deptId));
            }
        } else if (DATA_SCOPE_SELF.equals(dataScopeType)) {
            // Filter by self only
            if (!"".equals(userAlias)) {
                sqlString.append(String.format(" AND %s.duty_user_id = %d", userAlias, userId));
            } else if (!"".equals(deptAlias)) {
                sqlString.append(" AND 1=0"); // No access if no user field defined
            }
        }

        // Apply the data scope SQL to the query parameters
        if (sqlString.length() > 0) {
            Object params = joinPoint.getArgs()[0];
            if (params instanceof BaseEntity) {
                BaseEntity baseEntity = (BaseEntity) params;
                baseEntity.getParams().put(DATA_SCOPE_PARAM, sqlString.toString());
            }
        }
    }

    /**
     * Clear any existing data scope before applying new one
     */
    private void clearDataScope(JoinPoint joinPoint) {
        Object params = joinPoint.getArgs()[0];
        if (params instanceof BaseEntity) {
            BaseEntity baseEntity = (BaseEntity) params;
            baseEntity.getParams().put(DATA_SCOPE_PARAM, "");
        }
    }

    /**
     * Get current user's data scope type
     * TODO: Implement with actual security context
     */
    private String getCurrentUserDataScope() {
        // In real implementation: SecurityUtils.getLoginUser().getDataScope()
        return DATA_SCOPE_ALL;
    }

    /**
     * Get current user ID
     * TODO: Implement with actual security context
     */
    private Long getCurrentUserId() {
        // In real implementation: SecurityUtils.getUserId()
        return 1L;
    }

    /**
     * Get current user's department ID
     * TODO: Implement with actual security context
     */
    private Long getCurrentUserDeptId() {
        // In real implementation: SecurityUtils.getDeptId()
        return 1L;
    }
}
