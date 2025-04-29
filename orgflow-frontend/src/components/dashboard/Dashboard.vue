<template>
    <div class="dashboard">
        <h2 class="page-title">工作台</h2>

        <div class="dashboard-content">
            <a-row :gutter="16">
                <a-col :span="6">
                    <a-card title="员工总数" class="statistic-card">
                        <template #extra>
                            <UserOutlined />
                        </template>
                        <a-statistic :value="1234" />
                    </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card title="部门数量" class="statistic-card">
                        <template #extra>
                            <TeamOutlined />
                        </template>
                        <a-statistic :value="32" />
                    </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card title="本月新增" class="statistic-card">
                        <template #extra>
                            <RiseOutlined />
                        </template>
                        <a-statistic :value="87" />
                    </a-card>
                </a-col>
                <a-col :span="6">
                    <a-card title="系统消息" class="statistic-card">
                        <template #extra>
                            <BellOutlined />
                        </template>
                        <a-statistic :value="12" />
                    </a-card>
                </a-col>
            </a-row>

            <a-card class="chart-card" title="员工情况概览" style="margin-top: 16px;">
                <div style="height: 320px; display: flex; align-items: center; justify-content: center; color: #999;">
                    此处展示统计图表（实际项目中可集成Echarts等图表库）
                </div>
            </a-card>
        </div>
    </div>
</template>

<script setup lang="ts">
import { UserOutlined, TeamOutlined, RiseOutlined, BellOutlined } from '@ant-design/icons-vue'
</script>

<style scoped>
.dashboard {
    width: 100%;
}

.page-title {
    margin-bottom: 24px;
    color: rgba(0, 0, 0, 0.85);
}

.statistic-card :deep(.ant-statistic-content) {
    font-size: 24px;
    font-weight: bold;
}

/* 响应式调整 */
@media (max-width: 768px) {
    .statistic-card {
        margin-bottom: 16px;
    }
}
</style>