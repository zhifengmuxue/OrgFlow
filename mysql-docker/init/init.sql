-- 创建部门表
CREATE TABLE `department` (
  `dept_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '部门 ID',
  `dept_name` VARCHAR(50) NOT NULL COMMENT '部门名称',
  `dept_describe` VARCHAR(255) COMMENT '部门描述',
  `parent_id` BIGINT COMMENT '父部门 ID',
  `leader_id` BIGINT COMMENT '负责人 ID',
  `status` TINYINT NOT NULL COMMENT '状态(0 停用/1 启用)',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- 创建员工表
CREATE TABLE `employee` (
  `employee_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '员工 ID',
  `dept_id` BIGINT NOT NULL COMMENT '所属部门',
  `name` VARCHAR(20) NOT NULL COMMENT '姓名',
  `gender` CHAR(1) COMMENT '性别(M/F)',
  `id_number` VARCHAR(18) COMMENT '身份证号(加密存储)',
  `entry_date` DATETIME COMMENT '入职日期',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`employee_id`),
  CONSTRAINT `fk_employee_department` FOREIGN KEY (`dept_id`) REFERENCES `department` (`dept_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='员工表';

-- 创建账号表
CREATE TABLE `account` (
  `account_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '账号 ID',
  `employee_id` BIGINT NOT NULL COMMENT '关联员工 ID',
  `username` VARCHAR(30) NOT NULL COMMENT '登录名',
  `password` VARCHAR(100) NOT NULL COMMENT '加密密码',
  `status` TINYINT NOT NULL COMMENT '状态(0 禁用/1 启用)',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`account_id`),
  UNIQUE KEY `uk_account_username` (`username`),
  CONSTRAINT `fk_account_employee` FOREIGN KEY (`employee_id`) REFERENCES `employee` (`employee_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号表';

-- 创建角色表
CREATE TABLE `role` (
  `role_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '角色 ID',
  `role_name` VARCHAR(50) NOT NULL COMMENT '角色名称',
  `description` VARCHAR(200) COMMENT '描述',
  `status` TINYINT NOT NULL COMMENT '状态(0 停用/1 启用)',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色表';

-- 创建账号-角色关联表（添加时间和创建人信息）
CREATE TABLE `account_role` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联 ID',
  `account_id` BIGINT NOT NULL COMMENT '账号 ID',
  `role_id` BIGINT NOT NULL COMMENT '角色 ID',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_account_role_account` FOREIGN KEY (`account_id`) REFERENCES `account` (`account_id`),
  CONSTRAINT `fk_account_role_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号-角色关联表';

-- 创建权限表 
CREATE TABLE `permission` (
  `permission_id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '权限 ID',
  `permission_code` VARCHAR(50) NOT NULL COMMENT '权限编码',
  `permission_name` VARCHAR(100) NOT NULL COMMENT '权限名称',
  `sort_order` INT NOT NULL COMMENT '排序号',
  `status` TINYINT NOT NULL COMMENT '状态(0 停用/1 启用)',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`permission_id`),
  UNIQUE KEY `uk_permission_code` (`permission_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='权限表';


-- 创建角色-权限关联表
CREATE TABLE `role_permission` (
  `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT '关联 ID',
  `role_id` BIGINT NOT NULL COMMENT '角色 ID',
  `permission_id` BIGINT NOT NULL COMMENT '权限 ID',
  `created_at` DATETIME NOT NULL COMMENT '创建时间',
  `updated_at` DATETIME NOT NULL COMMENT '最后修改时间',
  `created_by` BIGINT NOT NULL COMMENT '创建人 ID',
  `updated_by` BIGINT NOT NULL COMMENT '最后修改人 ID',
  PRIMARY KEY (`id`),
  CONSTRAINT `fk_role_permission_role` FOREIGN KEY (`role_id`) REFERENCES `role` (`role_id`),
  CONSTRAINT `fk_role_permission_permission` FOREIGN KEY (`permission_id`) REFERENCES `permission` (`permission_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色-权限关联表';


-- 部门表数据 - 注意：系统初始化时，先将created_by/updated_by设置为1，对应将要创建的admin账号
INSERT INTO department (dept_id, dept_name, dept_describe, parent_id, leader_id, status, created_at, updated_at, created_by, updated_by) VALUES
(1, '董事会', '公司最高决策机构', NULL, 1, 1, NOW(), NOW(), 1, 1),
(2, '技术部', '负责产品研发', 1, 2, 1, NOW(), NOW(), 1, 1),
(3, '人力资源部', '负责人才管理', 1, 3, 1, NOW(), NOW(), 1, 1);

-- 员工表数据
INSERT INTO employee (employee_id, dept_id, name, gender, id_number, entry_date, created_at, updated_at, created_by, updated_by) VALUES
(1, 1, '张董事长', 'M', '11010119800101001X', '2020-01-01', NOW(), NOW(), 1, 1),
(2, 2, '李技术总监', 'M', '11010119810202002X', '2020-01-01', NOW(), NOW(), 1, 1),
(3, 3, '王HR总监', 'F', '11010119820303003X', '2020-01-01', NOW(), NOW(), 1, 1);

-- 账号表数据
INSERT INTO account (employee_id, username, password, status, created_at, updated_at, created_by, updated_by) VALUES
(1, 'admin', '$2a$10$xJwL5v5zJz6Zz6Zz6Zz6Z.6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Z', 1, NOW(), NOW(), 1, 1),
(2, 'tech_leader', '$2a$10$yKvL5v5zJz6Zz6Zz6Zz6Z.6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Z', 1, NOW(), NOW(), 1, 1),
(3, 'hr_leader', '$2a$10$zLwL5v5zJz6Zz6Zz6Zz6Z.6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Zz6Z', 1, NOW(), NOW(), 1, 1);

-- 角色表数据
INSERT INTO role (role_name, description, status, created_at, updated_at, created_by, updated_by) VALUES
('超级管理员', '拥有系统所有权限', 1, NOW(), NOW(), 1, 1),
('管理员', '拥有系统大部分权限', 1, NOW(), NOW(), 1, 1),
('普通用户', '基本系统使用权限', 1, NOW(), NOW(), 1, 1);

-- 权限表数据
INSERT INTO permission (permission_code, permission_name, sort_order, status, created_at, updated_at, created_by, updated_by) VALUES
('system:manage', '系统管理', 1, 1, NOW(), NOW(), 1, 1),
('user:manage', '用户管理', 2, 1, NOW(), NOW(), 1, 1),
('dept:manage', '部门管理', 3, 1, NOW(), NOW(), 1, 1);


-- 账号-角色关联（设置超级管理员）- 添加时间和创建人信息
INSERT INTO role_permission (role_id, permission_id, created_at, updated_at, created_by, updated_by) VALUES
(1, 1, NOW(), NOW(), 1, 1), -- 超级管理员 - 系统管理
(1, 2, NOW(), NOW(), 1, 1), -- 超级管理员 - 用户管理 
(1, 3, NOW(), NOW(), 1, 1), -- 超级管理员 - 部门管理
(2, 2, NOW(), NOW(), 1, 1), -- 管理员 - 用户管理
(2, 3, NOW(), NOW(), 1, 1); -- 管理员 - 部门管理

-- 角色-权限关联 - 添加时间和创建人信息
INSERT INTO role_permission (role_id, permission_id, created_at, updated_at, created_by, updated_by) VALUES
(1, 'system:manage', NOW(), NOW(), 1, 1), -- 超级管理员 - 系统管理
(1, 'user:manage', NOW(), NOW(), 1, 1),   -- 超级管理员 - 用户管理 
(1, 'dept:manage', NOW(), NOW(), 1, 1),   -- 超级管理员 - 部门管理
(2, 'user:manage', NOW(), NOW(), 1, 1),   -- 管理员 - 用户管理
(2, 'dept:manage', NOW(), NOW(), 1, 1);   -- 管理员 - 部门管理