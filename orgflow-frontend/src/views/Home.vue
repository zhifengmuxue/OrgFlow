<template>
    <a-layout class="layout">
        <!-- 顶部导航栏 -->
        <a-layout-header class="header">
            <div class="logo">OrgFlow</div>
            <div class="header-right">
                <a-dropdown>
                    <a class="ant-dropdown-link" @click.prevent>
                        <a-avatar :size="32" :src="avatar">
                            <template #icon>
                                <UserOutlined />
                            </template>
                        </a-avatar>
                        <span class="username">{{ username }}</span>
                        <DownOutlined />
                    </a>
                    <template #overlay>
                        <a-menu>
                            <a-menu-item key="0">
                                <UserOutlined />
                                个人信息
                            </a-menu-item>
                            <a-menu-item key="1">
                                <SettingOutlined />
                                设置
                            </a-menu-item>
                            <a-menu-divider />
                            <a-menu-item key="3" @click="handleLogout">
                                <LogoutOutlined />
                                退出登录
                            </a-menu-item>
                        </a-menu>
                    </template>
                </a-dropdown>
            </div>
        </a-layout-header>

        <a-layout>
            <!-- 左侧菜单 -->
            <a-layout-sider v-model:collapsed="collapsed" :trigger="null" collapsible width="256" class="sider">
                <a-menu v-model:selectedKeys="selectedKeys" v-model:openKeys="openKeys" mode="inline" theme="light"
                    @click="handleMenuClick">
                    <a-menu-item key="dashboard">
                        <template #icon>
                            <DashboardOutlined />
                        </template>
                        <span>工作台</span>
                    </a-menu-item>

                    <a-sub-menu key="system">
                        <template #icon>
                            <SettingOutlined />
                        </template>
                        <template #title>系统管理</template>
                        <a-menu-item key="user">用户管理</a-menu-item>
                        <a-menu-item key="role">角色管理</a-menu-item>
                        <a-menu-item key="permission">权限管理</a-menu-item>
                    </a-sub-menu>

                    <a-sub-menu key="org">
                        <template #icon>
                            <TeamOutlined />
                        </template>
                        <template #title>组织管理</template>
                        <a-menu-item key="department">部门管理</a-menu-item>
                        <a-menu-item key="employee">员工管理</a-menu-item>
                    </a-sub-menu>
                </a-menu>
            </a-layout-sider>

            <!-- 主内容区 - 替换为router-view -->
            <a-layout-content class="content">
                <router-view></router-view>
            </a-layout-content>
        </a-layout>
    </a-layout>
</template>

<script setup lang="ts">
import { ref, onMounted, watch } from 'vue'
import { useRouter, useRoute } from 'vue-router'
import { message } from 'ant-design-vue'
import {
    UserOutlined,
    SettingOutlined,
    LogoutOutlined,
    DashboardOutlined,
    TeamOutlined,
    DownOutlined,
    RiseOutlined,
    BellOutlined
} from '@ant-design/icons-vue'
import axios from 'axios'

const router = useRouter()
const route = useRoute()

// 菜单状态 - 默认选中仪表盘
const collapsed = ref(false)
const selectedKeys = ref(['dashboard']) // 默认选中dashboard
const openKeys = ref([''])

// 用户信息
const username = ref('管理员')
const avatar = ref('')

// 初始化
onMounted(() => {
    // 检查登录状态
    const token = localStorage.getItem('token')
    if (!token) {
        message.warning('您尚未登录或登录已过期')
        router.push('/')
        return
    }

    if (route.path === '/') {
        router.push({ name: 'dashboard' })
        return
    }

    // 根据当前路由设置选中的菜单项
    const pathKey = route.name as string
    if (pathKey) {
        selectedKeys.value = [pathKey]
        // 设置展开的子菜单
        if (pathKey === 'user' || pathKey === 'role' || pathKey === 'permission') {
            openKeys.value = ['system']
        } else if (pathKey === 'department' || pathKey === 'employee') {
            openKeys.value = ['org']
        }
    } else {
        // 如果没有路由名称，默认选中仪表盘
        selectedKeys.value = ['dashboard']
    }
})

// 监听路由变化，更新选中的菜单项
watch(() => route.path, (newPath) => {
    const pathKey = route.name as string
    if (pathKey) {
        selectedKeys.value = [pathKey]
    } else {
        // 如果没有路由名称，默认选中仪表盘
        selectedKeys.value = ['dashboard']
    }
}, { immediate: true })

// 处理菜单点击事件
const handleMenuClick = ({ key }: { key: string }) => {
    router.push({ name: key })
}

// 退出登录
const handleLogout = async () => {
    try {
        // 调用登出API
        await axios.post('/api/auth/logout')

        // 清除所有token存储
        localStorage.removeItem('token')
        localStorage.removeItem('remember')
        localStorage.removeItem('satoken')
        sessionStorage.removeItem('satoken_session')
        
        message.success('已退出登录')

        // 跳转到登录页
        window.location.href = '/login'
    } catch (error) {
        console.error('退出登录失败:', error)
        message.error('退出失败，请重试')
    }
}
</script>

<style scoped>
.layout {
    min-height: 100vh;
}

.header {
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 0 24px;
    background: #fff;
    box-shadow: 0 1px 4px rgba(0, 21, 41, 0.08);
    z-index: 1;
    position: relative;
}

.logo {
    font-size: 20px;
    font-weight: bold;
    color: #1890ff;
    margin-right: 30px;
}

.header-right {
    display: flex;
    align-items: center;
}

.username {
    margin: 0 8px;
    color: rgba(0, 0, 0, 0.65);
}

.sider {
    box-shadow: 2px 0 8px 0 rgba(29, 35, 41, 0.05);
    background-color: #fff;
    border-right: 1px solid #f0f0f0;
}

.content {
    padding: 24px;
    background: #f0f2f5;
    min-height: 280px;
}

.ant-dropdown-link {
    display: flex;
    align-items: center;
    cursor: pointer;
}
</style>