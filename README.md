# OrgFlow 员工管理系统

<div align="center">
  <img src="./docs/logo.png" alt="OrgFlow Logo" style="width: 200px; max-width: 40%; height: auto; margin: 20px 0;">
</div>

OrgFlow 是一个基于现代 Web 技术的企业级员工管理系统，提供完整的部门架构管理和员工信息维护解决方案。系统采用前后端分离架构，旨在帮助中小型企业高效管理组织架构、人员信息和权限分配，实现人力资源数字化转型。该系统具备灵活的部门层级管理、丰富的员工档案维护、完善的权限控制以及直观的数据可视化功能，能有效提升HR团队工作效率，助力管理层进行数据驱动决策。OrgFlow支持多种部署方式，既可本地部署也可云端托管，满足不同规模企业的实际需求。

## 技术架构

### 后端技术栈
- **核心框架**: SpringBoot 
- **缓存**: Redis 
- **消息队列**: 
  - RabbitMQ (业务异步处理)
  - Kafka  (实时事件通知)
- **数据库**: MySQL  
- **安全框架**: Spring Security + JWT

### 前端技术栈
- **框架**: Vue.js  
- **UI组件库**: Ant Design Vue  
- **状态管理**: Pinia
- **构建工具**: Vite  

## 功能模块

### 1. 部门管理
- 多层级部门树形结构
- 部门增删改查操作
- 部门人员分布统计图表
- 部门负责人设置

### 2. 员工账号
- 基于角色的权限系统(RBAC)
- 账号激活/禁用管理
- 登录历史记录
- 密码强度策略

### 3. 员工信息
- 完整个人信息档案
- 工作经历时间轴
- 证件/证书管理
- 紧急联系人信息


## 前端特色实现

### 1. 界面设计
- 采用 Ant Design Pro 布局风格
- 响应式设计适配多端
- 主题色定制能力

### 2. 关键技术点
1. **动态表单** - 基于JSON Schema的员工信息表单
2. **可视化组织架构** - 使用D3.js实现的交互式部门树
3. **实时通知** - WebSocket集成消息中心
4. **Excel导入导出** - 基于SheetJS实现


## 部署方案

### 开发环境
```bash
# 前端
npm install
npm run dev

# 后端
mvn spring-boot:run
```

### 生产部署
```markdown
1. 前端构建静态资源
   npm run build
   
2. 使用Docker编排:
   - Nginx (前端)
   - SpringBoot应用容器
   - Redis容器
   - MySQL容器
   - RabbitMQ容器
```

## 项目结构

```
orgflow/
├── orgflow-backend/            # SpringBoot后端
├── orgflow-frontend/           # Vue前端
│   ├── public/         # 静态资源
│   ├── src/
│   │   ├── api/        # 接口定义
│   │   ├── assets/     # 静态资源
│   │   ├── components/ # 公共组件
│   │   ├── router/     # 路由配置
│   │   ├── stores/     # Pinia状态
│   │   └── views/      # 页面组件
├── docs/              # 项目文档
└── docker/            # 容器配置
```