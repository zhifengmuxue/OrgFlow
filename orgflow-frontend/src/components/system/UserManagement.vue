<template>
    <div class="user-management">
        <h2 class="page-title">用户管理</h2>

        <a-card class="user-table-card">
            <template #extra>
                <a-button type="primary">
                    <template #icon>
                        <PlusOutlined />
                    </template>
                    新增用户
                </a-button>
            </template>
            <a-table :columns="columns" :data-source="userData" rowKey="id" :pagination="{ pageSize: 10 }">
                <template #bodyCell="{ column, record }">
                    <template v-if="column.key === 'action'">
                        <a-space>
                            <a @click="editUser(record)">编辑</a>
                            <a-divider type="vertical" />
                            <a-popconfirm title="确定要删除该用户吗?" ok-text="是" cancel-text="否" @confirm="deleteUser(record)">
                                <a class="danger-link">删除</a>
                            </a-popconfirm>
                        </a-space>
                    </template>
                </template>
            </a-table>
        </a-card>
    </div>
</template>

<script setup lang="ts">
import { ref } from 'vue'
import { PlusOutlined } from '@ant-design/icons-vue'

// 表格列定义
const columns = [
    { title: '用户ID', dataIndex: 'id', key: 'id' },
    { title: '用户名', dataIndex: 'username', key: 'username' },
    { title: '姓名', dataIndex: 'name', key: 'name' },
    { title: '所属部门', dataIndex: 'department', key: 'department' },
    { title: '角色', dataIndex: 'role', key: 'role' },
    { title: '状态', dataIndex: 'status', key: 'status' },
    { title: '创建时间', dataIndex: 'createTime', key: 'createTime' },
    { title: '操作', key: 'action' }
]

// 模拟数据
const userData = ref([
    { id: 1, username: 'admin', name: '管理员', department: '技术部', role: '超级管理员', status: '启用', createTime: '2023-01-01' },
    { id: 2, username: 'user1', name: '张三', department: '人力资源部', role: '普通用户', status: '启用', createTime: '2023-01-02' },
    { id: 3, username: 'user2', name: '李四', department: '财务部', role: '部门管理员', status: '停用', createTime: '2023-01-03' },
])

// 操作方法
const editUser = (record: any) => {
    console.log('编辑用户', record)
}

const deleteUser = (record: any) => {
    console.log('删除用户', record)
    userData.value = userData.value.filter(item => item.id !== record.id)
}
</script>

<style scoped>
.page-title {
    margin-bottom: 24px;
    color: rgba(0, 0, 0, 0.85);
}

.danger-link {
    color: #ff4d4f;
}
</style>