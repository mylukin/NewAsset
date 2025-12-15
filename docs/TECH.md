# 1. 文档说明

* 文档名称：资产管理系统（MVP）技术设计说明书
* 版本：V1.0
* 适用范围：后端开发、前端开发、架构师、测试、运维

---

# 2. 总体架构设计

## 2.1 架构风格

* 前后端分离、单体应用、分层架构。
* 后端：基于 RuoYi-Vue 的 Spring Boot 单体应用，采用 Controller-Service-Repository/Mapper 分层。
* 前端：基于 Vue2 + Element UI 的后台管理系统。
* 数据库：MySQL（或兼容型关系型数据库）。

## 2.2 技术选型

后端：

* 语言与运行环境：Java 8+, Java EE 8 规范
* Web 框架：Spring Boot 2.2.x、Spring MVC（随 Spring Framework 5.2.x）
* 安全框架：Spring Security 5.2.x + Jwt（使用 RuoYi 现有方案）
* ORM / 持久化：MyBatis 3.5.x
* 校验：Hibernate Validation 6.0.x
* 数据源与连接池：Alibaba Druid 1.2.x
* 定时任务：RuoYi 的 Quartz 模块
* 日志：SLF4J + Logback + RuoYi @Log 审计框架

前端：

* Vue 2.6.x
* Vue Router
* Axios 0.21.x
* Element UI 2.15.x

基础平台：RuoYi-Vue

* 使用其用户、角色、部门、数据权限、日志、定时任务、字典等基础能力。
* 不使用其“代码生成器”业务，避免与“资产编码生成”概念混淆。

## 2.3 应用架构层次

后端主要分层：

1. 接口层（Controller）

   * 提供 RESTful API，与前端交互。
   * 负责参数接收、基础校验、权限控制（注解）、结果封装。

2. 应用层（Service）

   * 编排领域操作，处理业务流程。
   * 调用领域服务和仓储接口。

3. 领域层（Domain）

   * 领域对象（实体 DO）、值对象（VO）、领域服务（如资产编码生成、状态机等）。

4. 基础设施层（Infrastructure）

   * MyBatis Mapper、数据访问实现、外部系统集成（如未来的合同系统）。

前端分层：

* 视图层（Vue 页面组件）
* 业务组件层（表单组件、资产选择组件、工单组件等）
* 接口层（/src/api 下的 axios 封装）
* 公共工具层（字典、权限指令、工具类等）

---

# 3. 模块划分与包结构

## 3.1 后端模块划分

在原 RuoYi-Vue 项目结构基础上新增业务模块：

* `ruoyi-asset`（推荐新建模块）
  或在 `ruoyi-system` 中新增 `com.ruoyi.asset` 包（若不想拆 module）。

推荐模块 → 包结构（示例）：

```text
com.ruoyi.asset
 ├─ config            // 资产模块相关配置（如 Mapper 扫描、状态机、缓存等）
 ├─ domain
 │   ├─ entity        // DO，与数据库表一一映射
 │   ├─ vo            // VO / DTO：列表、详情、导出使用
 │   └─ enums         // 枚举，如资产类型、工单状态
 ├─ mapper            // MyBatis Mapper 接口
 ├─ service
 │   ├─ impl
 │   └─ rule          // 资产编码规则、状态机等领域服务
 ├─ controller        // REST 控制器
 ├─ util              // 工具类，如 AssetCodeGenerator
 └─ constant          // 常量定义（模块内）
```

### 3.2 领域模块划分

逻辑上拆为 7 个领域模块（代码层仍在同一 module 内）：

1. `asset-core`：

   * 核心资产实体（基础字段统一）
   * 资产状态机
   * 资产附件
   * 项目维度聚合

2. `asset-house`：房源资产扩展字段及服务

3. `asset-parking`：车库/车位资产扩展字段及服务

4. `asset-facility`：住宅配套资产扩展字段及服务

5. `asset-venue`：场馆资产扩展字段及服务

6. `asset-office`：办公资产扩展字段及服务

7. `asset-maintenance`：资产维护工单、计划与记录

---

# 4. 数据库与领域模型设计

以下为关键表和核心字段（命名仅示例，可按你习惯微调）。

## 4.1 核心资产表

### 4.1.1 `t_asset`（资产基础表）

统一存放所有资产的通用字段，每条记录代表一个资产实例。

关键字段示例：

* `id` (bigint, PK)
* `asset_code` (varchar, 唯一) — 统一资产编码
* `asset_name` (varchar)
* `asset_type` (varchar) — 枚举：HOUSE / PARKING / FACILITY / VENUE / OFFICE
* `project_id` (bigint) — 所属项目
* `building` (varchar) — 楼栋/区域编码
* `floor` (varchar)
* `room_no` (varchar)
* `location_desc` (varchar) — 位置描述
* 权属/使用信息：

  * `ownership_type` (varchar)
  * `owner_org` (varchar)
  * `use_dept_id` (bigint)
  * `duty_user_id` (bigint)
* 状态：

  * `status` (varchar) — 统一状态机编码
* 价值与折旧：

  * `purchase_date` (date)
  * `start_use_date` (date)
  * `original_value` (decimal)
  * `depreciation_method` (varchar)
  * `depreciation_amount` (decimal)
  * `net_value` (decimal)
* 品牌与供应商：

  * `brand` (varchar)
  * `model` (varchar)
  * `supplier` (varchar)
  * `warranty_expire_date` (date)
* 其他：

  * `remark` (varchar)
  * 公共审计字段：`create_by`, `create_time`, `update_by`, `update_time`, `del_flag`

为保证查询性能：

* 建议在 `(project_id, asset_type, status)` 和 `asset_code` 上建索引。

### 4.1.2 资产扩展表

以一对一关系与 `t_asset` 关联（通过 `asset_id`）。

1. `t_asset_house`（房源扩展）

* `asset_id` (PK, FK → t_asset.id)
* `building_area` (decimal)
* `inner_area` (decimal)
* `house_type` (varchar) — 户型
* `house_usage` (varchar) — 房屋用途
* `current_usage` (varchar) — 当前用途（自用/出租/空置等）
* `current_user` (varchar)
* `contract_no` (varchar)
* `rent_unit_price` (decimal)
* `rent_total` (decimal)

2. `t_asset_parking`（车位扩展）

* `asset_id`
* `parking_no` (varchar)
* `parking_zone` (varchar) — 区域
* `parking_type` (varchar) — 固定/临停
* `area` (decimal)
* `current_user` (varchar)
* `plate_no` (varchar)
* `rent_price` (decimal)
* `contract_no` (varchar)

3. `t_asset_facility`（住宅配套扩展）

* `asset_id`
* `facility_type` (varchar)
* `install_position` (varchar)
* `equipment_no` (varchar)
* `maint_org` (varchar)
* `maint_phone` (varchar)

4. `t_asset_venue`（场馆扩展）

* `asset_id`
* `venue_type` (varchar)
* `capacity` (int)
* `use_mode` (varchar) — 免费/收费等
* `open_time_desc` (varchar)
* `current_venue_status` (varchar) — 子状态（可预约/占用等）

5. `t_asset_office`（办公扩展）

* `asset_id`
* `office_type` (varchar) — 家具/IT设备/其他
* `workplace_no` (varchar) — 工位号/房间号
* `use_user` (varchar)
* `serial_no` (varchar) — 序列号/标签号
* `os_info` (varchar)
* `config_desc` (varchar)

### 4.1.3 资产附件表 `t_asset_attachment`

* `id` (PK)
* `asset_id`
* `file_name`
* `file_path`
* `file_type`
* `file_size`
* `upload_by`
* `upload_time`
* `remark`

## 4.2 维护模块表设计

### 4.2.1 维护工单表 `t_asset_maint_order`

* `id` (PK)
* `order_no` (varchar, 唯一)
* `asset_id` (FK → t_asset.id)
* `project_id`
* `order_type` (varchar) — 故障/报修/巡检/维保
* `fault_type` (varchar)
* `title` (varchar)
* `description` (text)
* `priority` (varchar) — 一般/重要/紧急
* `require_finish_time` (datetime)
* `status` (varchar) — 待派单/待接单/处理中/待验收/已关闭/已取消
* `origin_asset_status` (varchar) — 发起时资产状态
* `current_handler_id` (bigint) — 当前处理人（运维）
* `requester_id` (bigint) — 发起人
* `create_by/create_time/update_by/update_time`

索引建议：

* `(project_id, status)`
* `(current_handler_id, status)`

### 4.2.2 维护工单过程/日志表 `t_asset_maint_log`

* `id`
* `order_id`
* `from_status`
* `to_status`
* `operator_id`
* `operation_type`（派单/接单/开始处理/处理完成/验收/取消等）
* `content`（备注内容，如处理说明）
* `op_time`

可与审计日志配合使用，在工单详情页展示。

### 4.2.3 维护工单附加信息表（可选）

如果希望单独记录耗材、人工费用，可建立 `t_asset_maint_cost` 等表，MVP 可先放在 log.content 中或 JSON 字段。

### 4.2.4 维保计划表（P1）`t_asset_maint_plan`

* `id`
* `plan_name`
* `project_id`
* `asset_type` / `asset_category`
* `cycle_type`（每月/季度/年）
* `next_generate_time`
* `status`（启用/停用）

关联表 `t_asset_maint_plan_asset` 用于指定具体资产。

## 4.3 资产编码规则表设计

### 4.3.1 规则定义表 `t_asset_code_rule`

* `id` (PK)
* `asset_type` (varchar) — 资产类型
* `rule_name` (varchar)
* `pattern` (varchar) — 模板，例如：`{PROJECT}-{TYPE}-{BUILDING}-{FLOOR}-{SEQ}`
* `separator` (varchar) — 分隔符，如 `-`
* `seq_length` (int) — 序号位数，如 4 位补零
* `reset_scope` (varchar) — 序号重置范围：NONE / BY_PROJECT / BY_PROJECT_YEAR
* `enabled` (tinyint) — 是否启用
* `remark`
* `create_by/create_time/update_by/update_time`

### 4.3.2 序列号表 `t_asset_code_seq`

* `id`
* `rule_id`
* 维度字段（视 reset_scope 决定）：

  * `project_id` (nullable)
  * `year` (nullable)
* `current_seq` (bigint)
* `version` (int) — 乐观锁版本号

在生成资产编码时，通过对 `t_asset_code_seq` 的更新获取新序列号。

---

# 5. 核心技术方案设计

## 5.1 资产编码生成方案

### 5.1.1 设计目标

* 按配置规则为不同资产类别生成唯一编码；
* 保证高并发下不重复、不空洞过多；
* 可按项目/年度重置序号。

### 5.1.2 组件设计

核心类结构示例：

* `AssetCodeRuleService`

  * 读取 `t_asset_code_rule`，提供规则查询与缓存。
* `AssetCodeSequenceRepository`

  * 对 `t_asset_code_seq` 表提供增量更新操作。
* `AssetCodeGenerator`

  * 对外暴露 `generate(AssetType type, Long projectId, LocationInfo locationInfo)` 方法。

`LocationInfo` 可封装楼栋、楼层等信息，用于渲染 pattern 中的 `{BUILDING}`、`{FLOOR}` 等占位符。

### 5.1.3 并发控制

采用数据库乐观锁或行级锁方案：

* 方式一：乐观锁

  1. 根据 `(rule_id, project_id, year)` 查询 sequence 行；
  2. 将 `current_seq + 1` 更新回去，where 条件带 `version = old_version`；
  3. 若更新条数为 0，则说明冲突，重试；
  4. 将新序号渲染到最终编码中。

* 方式二：`SELECT ... FOR UPDATE`

  * 在事务内锁定对应 sequence 行，更新 current_seq。

在 Spring 事务中使用，保证生成编码与插入资产记录在同一事务内。

### 5.1.4 异常处理

* 编码唯一性依赖 `t_asset.asset_code` 的唯一索引；
* 如极端并发导致重复，插入失败时捕获异常，重新生成编码（最多重试 N 次，N 可配置）；
* 如无可用规则，抛出业务异常，前端给予提示。

## 5.2 统一资产状态机实现

### 5.2.1 状态枚举

在 `com.ruoyi.asset.domain.enums` 中定义统一状态枚举 `AssetStatusEnum`，例如：

* `UNDER_CONSTRUCTION` — 在建/未投入使用
* `AVAILABLE_SELF` — 可用-自用
* `AVAILABLE_RENT` — 可用-对外出租
* `AVAILABLE_IDLE` — 可用-空置
* `TEMP_CLOSED` — 暂停使用
* `FAULT` — 故障/停用
* `MAINTAINING` — 维修中
* `TO_BE_SCRAPPED` — 计划报废
* `SCRAPPED` — 已报废

字段 `t_asset.status` 存储枚举编码（字符串或 smallint）。

### 5.2.2 状态变更入口

* 通过资产编辑接口变更状态；
* 通过维护工单流转触发状态变更（如工单创建 → 设置为 FAULT / MAINTAINING；工单关闭 → 状态恢复）。

设计领域服务 `AssetStatusService`：

* `changeStatus(Long assetId, AssetStatusEnum newStatus, String reason, Long operatorId)`

  * 更新 `t_asset.status`；
  * 记录到自定义的资产状态变更日志表（可选）；
  * 支持校验不合法的状态转换（如已经 SCRAPPED 不允许改回 AVAILABLE）。

## 5.3 维护工单流程实现

### 5.3.1 状态流转

`MaintOrderStatusEnum`：

* `WAIT_ASSIGN`
* `WAIT_ACCEPT`
* `PROCESSING`
* `WAIT_CONFIRM`
* `CLOSED`
* `CANCELED`

配合操作类型 `MaintOperationType`：CREATE、ASSIGN、ACCEPT、START_HANDLE、COMPLETE、CONFIRM、REJECT、CANCEL 等。

在 Service 层封装流转方法：

* `createOrder`
* `assignOrder`
* `acceptOrder`
* `completeOrder`
* `confirmOrder`
* `cancelOrder`

每次流转：

1. 校验当前状态是否合法；
2. 更新 `t_asset_maint_order` 状态字段；
3. 写一条 `t_asset_maint_log`；
4. 必要时调用 `AssetStatusService` 更新资产状态。

### 5.3.2 与资产状态联动逻辑

配置方式建议：

* 在系统配置表或字典中定义开关：

  * `asset.maint.changeStatusOnCreate`
  * `asset.maint.restoreStatusOnClose`

实现：

* 创建工单时，如果开关开启且勾选“影响资产状态”，则：

  * 记录资产当前状态到 `origin_asset_status`；
  * 将资产状态更新为 `FAULT` 或 `MAINTAINING`。

* 工单关闭时，如果开关开启，则：

  * 将资产状态恢复为 `origin_asset_status`（或通过规则映射恢复）。

## 5.4 权限与数据权限实现

### 5.4.1 菜单与按钮权限

* 按 RuoYi 标准：`@PreAuthorize("@ss.hasPermi('asset:house:list')")` 等；
* 每个模块对应菜单编码：

  * 房源：`asset:house:*`
  * 配套：`asset:facility:*`
  * 车库：`asset:parking:*`
  * 场馆：`asset:venue:*`
  * 办公：`asset:office:*`
  * 维护：`asset:maint:*`

### 5.4.2 数据权限（按项目）

* 在 `t_asset`、`t_asset_maint_order` 中统一引入 `project_id` 字段；
* 基于 RuoYi 原有的 `@DataScope` 注解，扩展数据范围到 `project_id`：

  * 可在自定义 DataScope 里，将“部门”或“自定义范围”映射到项目维度。
* 查询 SQL 中需带上项目过滤条件，例如：

```sql
SELECT ... FROM t_asset a
LEFT JOIN sys_project p ON a.project_id = p.id
WHERE a.del_flag = '0'
  AND ${params.dataScope}
```

---

# 6. 接口设计规范与示例

## 6.1 RESTful 规范

* 列表：`GET /asset/house/list`
* 详情：`GET /asset/house/{id}`
* 新增：`POST /asset/house`
* 修改：`PUT /asset/house`
* 删除：`DELETE /asset/house/{ids}`
* 导出：`POST /asset/house/export`

维护工单类似：`/asset/maint/order/...`。

## 6.2 通用响应格式

沿用 RuoYi：

```json
{
  "code": 200,
  "msg": "操作成功",
  "data": { ... }
}
```

列表返回：

```json
{
  "code": 200,
  "msg": "操作成功",
  "rows": [ ... ],
  "total": 123
}
```

## 6.3 错误码与异常处理

* 使用 RuoYi 统一异常处理机制。
* 业务异常抛出 `ServiceException`，前端展示 message。
* 资产编码生成、状态流转等业务错误统一为业务异常。

## 6.4 典型接口示例（以房源资产为例）

Controller 包结构：`com.ruoyi.asset.controller.AssetHouseController`

主要接口：

1. `GET /asset/house/list`

   * 请求参数：项目、楼栋、楼层、状态等；
   * 返回：分页列表 VO。

2. `GET /asset/house/{id}`

   * 返回：房源详情 VO，包含基础资产信息 + 房源扩展字段 + 维护记录概要。

3. `POST /asset/house`

   * 请求体：

     * `AssetHouseCreateDTO`：

       * 通用资产字段
       * 房源扩展字段
   * 流程：

     1. 参数校验（Hibernate Validator）。
     2. 调用 `AssetCodeGenerator` 生成编码。
     3. 插入 `t_asset` 与 `t_asset_house`。

4. `PUT /asset/house`

   * 请求体：`AssetHouseUpdateDTO`；
   * 校验后更新 `t_asset` 与扩展表；
   * 记录修改日志（使用 @Log + 自定义变更记录）。

5. `DELETE /asset/house/{ids}`

   * 逻辑删除（`del_flag = '2'`），保留数据。

6. `POST /asset/house/{id}/maint`

   * 功能：从房源详情发起维护工单；
   * 内部调用维护模块 Service 创建工单。

---

# 7. 前端架构设计

## 7.1 目录结构（基于 RuoYi-Vue）

```text
src
 ├─ api
 │   └─ asset
 │       ├─ house.js
 │       ├─ parking.js
 │       ├─ facility.js
 │       ├─ venue.js
 │       ├─ office.js
 │       └─ maintOrder.js
 └─ views
     └─ asset
         ├─ house
         │   └─ index.vue
         ├─ parking
         │   └─ index.vue
         ├─ facility
         │   └─ index.vue
         ├─ venue
         │   └─ index.vue
         ├─ office
         │   └─ index.vue
         └─ maint
             ├─ orderList.vue
             └─ orderDetail.vue
```

## 7.2 页面与组件设计

各资产模块统一采用「搜索区 + 表格 + 抽屉详情」模式：

* 顶部检索条件：项目、状态、位置、关键字；
* 中间表格：显示资产编码、名称、项目、状态、位置、责任人等；
* 行操作：

  * 查看详情（右侧抽屉或弹窗）
  * 编辑
  * 发起维护
* 详情页：

  * Tab1：基础信息（通用字段 + 专属字段）
  * Tab2：维护记录列表
  * Tab3：附件列表

维护工单页面：

* 我的待处理工单
* 全部工单列表
* 工单详情（时间线 + 状态流转 + 处理记录 + 附件）

## 7.3 接口调用约定

在 `/src/api/asset/house.js` 中定义：

```js
import request from '@/utils/request'

export function listHouse(query) {
  return request({
    url: '/asset/house/list',
    method: 'get',
    params: query
  })
}

export function getHouse(id) {
  return request({
    url: '/asset/house/' + id,
    method: 'get'
  })
}

export function addHouse(data) {
  return request({
    url: '/asset/house',
    method: 'post',
    data: data
  })
}

// 其他 update/delete 等
```

## 7.4 字典与权限

* 使用 RuoYi 的字典组件加载：

  * 资产类型、资产状态、房屋用途、车位类型、场馆类型等；
* 按钮显示/隐藏使用指令 `v-hasPermi`，如：`v-hasPermi="['asset:house:add']"`。

---

# 8. 日志、审计与监控设计

## 8.1 操作日志

* 对资产新增、修改、删除、导出、维护工单流转等操作增加 `@Log` 注解；
* 日志包括：操作模块、操作类型、操作人、请求参数、返回结果摘要。

## 8.2 资产关键字段变更记录（可选）

设计表 `t_asset_change_log`：

* `id`
* `asset_id`
* `field_name`
* `old_value`
* `new_value`
* `operator_id`
* `op_time`

在 Service 层对比更新前后对象，记录关键字段（状态、项目、价值、责任人等）的变更。

## 8.3 监控

* 使用 Druid 监控页面（RuoYi 已集成）查看 SQL 性能；
* 使用 APM（如 SkyWalking/Pinpoint 等）可在后续接入，不在 MVP 必须范围。

---

# 9. 配置与部署设计

## 9.1 环境划分

* 开发环境：本地 + dev 库
* 测试环境：test
* 生产环境：prod

配置文件（示例）：

* `application-dev.yml`
* `application-test.yml`
* `application-prod.yml`

## 9.2 数据源与连接池

Druid 配置关键项：

* `initialSize`, `minIdle`, `maxActive` 按环境调优；
* 开启 SQL 监控与慢 SQL 记录；
* 配置连接泄露监控。

## 9.3 部署架构

典型生产部署：

* Nginx（反向代理 + 静态资源）
* Spring Boot（Tomcat 内嵌）若干实例（2~3）
* MySQL 主从或高可用集群
* Redis（可选，用于缓存字典、规则等）

---

# 10. 开发与测试建议

## 10.1 开发迭代顺序（建议）

1. 搭建 `ruoyi-asset` 模块骨架 + 通用支持（资产实体、编码生成组件）。
2. 房源资产模块（后端 + 前端），打通从新增资产 → 维护工单全流程。
3. 复制模式扩展到：车库资产、办公资产。
4. 再扩展住宅配套资产、场馆资产。
5. 补充统计视图与首页概览。
6. P1 功能：维保计划、完好率统计等。

## 10.2 测试重点

* 资产编码唯一性与并发场景；
* 状态机流转合法性（非法状态转换应有明确错误）；
* 数据权限（不同角色/项目的视图差异）；
* 维护工单与资产状态联动是否符合预期；
* 导出数据的完整性与正确性。

