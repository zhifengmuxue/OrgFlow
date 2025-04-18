### **实体关系表格描述**

#### 1. 部门表（department）

| 字段名        | 类型         | 必填 | 说明                | 关联关系 |
| ------------- | ------------ | ---- | ------------------- | -------- |
| dept_id       | BIGINT(PK)   | ✓    | 部门 ID             | 自增主键 |
| dept_name     | VARCHAR(50)  | ✓    | 部门名称            |          |
| dept_describe | VARCHAR(255) |      | 部门描述            |
| parent_id     | BIGINT       |      | 父部门 ID           |          |
| leader_id     | BIGINT       |      | 负责人 ID           |          |
| status        | TINYINT      | ✓    | 状态(0 停用/1 启用) |          |
| created_at    | DATETIME     | ✓    | 创建时间            | 自动填充 |
| updated_at    | DATETIME     | ✓    | 最后修改时间        | 自动更新 |
| created_by    | VARCHAR(32)  | ✓    | 创建人 ID           |          |
| updated_by    | VARCHAR(32)  | ✓    | 最后修改人 ID       |          |

#### 2. 员工表（employee）

| 字段名      | 类型        | 必填 | 说明               | 关联关系         |
| ----------- | ----------- | ---- | ------------------ | ---------------- |
| employee_id | BIGINT(PK)  | ✓    | 员工 ID            | 自增主键         |
| dept_id     | BIGINT      | ✓    | 所属部门           | 外键->department |
| name        | VARCHAR(20) | ✓    | 姓名               |                  |
| gender      | CHAR(1)     |      | 性别(M/F)          |                  |
| id_number   | VARCHAR(18) |      | 身份证号(加密存储) |                  |
| entry_date  | DATETIME    |      | 入职日期           |                  |
| created_at  | DATETIME    | ✓    | 创建时间           | 自动填充         |
| updated_at  | DATETIME    | ✓    | 最后修改时间       | 自动更新         |
| created_by  | VARCHAR(32) | ✓    | 创建人 ID          |                  |
| updated_by  | VARCHAR(32) | ✓    | 最后修改人 ID      |                  |

#### 3. 账号表（account）

| 字段名      | 类型            | 必填 | 说明                | 关联关系       |
| ----------- | --------------- | ---- | ------------------- | -------------- |
| account_id  | VARCHAR(32)(PK) | ✓    | 账号 ID             | UUID 生成      |
| employee_id | BIGINT          | ✓    | 关联员工 ID         | 外键->employee |
| username    | VARCHAR(30)     | ✓    | 登录名              | 唯一索引       |
| password    | VARCHAR(100)    | ✓    | 加密密码            |                |
| status      | TINYINT         | ✓    | 状态(0 禁用/1 启用) |                |
| created_at  | DATETIME        | ✓    | 创建时间            | 自动填充       |
| updated_at  | DATETIME        | ✓    | 最后修改时间        | 自动更新       |
| created_by  | VARCHAR(32)     | ✓    | 创建人 ID           |                |
| updated_by  | VARCHAR(32)     | ✓    | 最后修改人 ID       |                |
| status      | TINYINT         | ✓    | 状态(0 停用/1 启用) |                |

#### 4. 角色表（role）

| 字段名      | 类型            | 必填 | 说明                | 关联关系 |
| ----------- | --------------- | ---- | ------------------- | -------- |
| role_id     | VARCHAR(20)(PK) | ✓    | 角色 ID             | 自增主键 |
| role_name   | VARCHAR(50)     | ✓    | 角色名称            |          |
| description | VARCHAR(200)    |      | 描述                |          |
| status      | TINYINT         | ✓    | 状态(0 停用/1 启用) |          |
| created_at  | DATETIME        | ✓    | 创建时间            | 自动填充 |
| updated_at  | DATETIME        | ✓    | 最后修改时间        | 自动更新 |
| created_by  | VARCHAR(32)     | ✓    | 创建人 ID           |          |
| updated_by  | VARCHAR(32)     | ✓    | 最后修改人 ID       |          |

#### 5. 账号-角色关联表（account_role）

| 字段名     | 类型        | 必填 | 说明    | 关联关系 |
| ---------- | ----------- | ---- | ------- | -------- |
| id         | BIGINT(PK)  | ✓    | 关联 ID | 自增主键 |
| account_id | VARCHAR(32) | ✓    | 账号 ID |          |
| role_id    | VARCHAR(20) | ✓    | 角色 ID |          |

#### 6. 权限表（permission）

| 字段名          | 类型            | 必填 | 说明                | 关联关系      |
| --------------- | --------------- | ---- | ------------------- | ------------- |
| permission_id   | VARCHAR(50)(PK) | ✓    | 权限 ID             | 主键          |
| permission_name | VARCHAR(100)    | ✓    | 权限名称            |               |
| sort_order      | INT             | ✓    | 排序号              |               |
| status          | TINYINT         | ✓    | 状态(0 停用/1 启用) |               |
| created_at      | DATETIME        | ✓    | 创建时间            | 自动填充      |
| updated_at      | DATETIME        | ✓    | 最后修改时间        | 自动更新      |
| created_by      | VARCHAR(32)     | ✓    | 创建人 ID           |            |
| updated_by      | VARCHAR(32)     | ✓    | 最后修改人 ID       |            |

#### 7. 角色-权限关联表（role_permission）

| 字段名        | 类型        | 必填 | 说明    | 关联关系         |
| ------------- | ----------- | ---- | ------- | ---------------- |
| id            | BIGINT(PK)  | ✓    | 关联 ID | 自增主键         |
| role_id       | VARCHAR(20) | ✓    | 角色 ID |                  |
| permission_id | VARCHAR(50) | ✓    | 权限 ID |                  |
