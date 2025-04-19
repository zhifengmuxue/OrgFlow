<template>
    <div class="login-container">
        <div class="login-box">
            <div class="login-header">
                <div class="logo-container">
                    <img src="../assets/logo.png" alt="OrgFlow Logo" class="logo-image" />
                </div>
                <h1>OrgFlow</h1>
                <p>员工管理系统</p>
            </div>

            <div class="login-form">
                <a-form ref="loginFormRef" :model="loginForm" :rules="loginRules" @finish="handleLogin">
                    <a-form-item name="username">
                        <a-input v-model:value="loginForm.username" placeholder="用户名" size="large" allow-clear>
                            <template #prefix><user-outlined /></template>
                        </a-input>
                    </a-form-item>

                    <a-form-item name="password">
                        <a-input-password v-model:value="loginForm.password" placeholder="密码" size="large" allow-clear>
                            <template #prefix><lock-outlined /></template>
                        </a-input-password>
                    </a-form-item>

                    <a-form-item>
                        <div class="login-options">
                            <a-checkbox v-model:checked="loginForm.remember">记住我</a-checkbox>
                            <a class="forget-link">忘记密码?</a>
                        </div>
                    </a-form-item>

                    <a-form-item>
                        <a-button type="primary" :loading="loading" class="login-button" html-type="submit"
                            size="large">
                            {{ loading ? '登录中...' : '登录' }}
                        </a-button>
                    </a-form-item>
                </a-form>
            </div>

            <div class="login-footer">
                <p>© {{ year }} OrgFlow. 版权所有</p>
            </div>
        </div>
    </div>
</template>

<script setup lang="ts">
import { ref, reactive, computed } from 'vue'
import { useRouter } from 'vue-router'
import { message } from 'ant-design-vue'
import { UserOutlined, LockOutlined } from '@ant-design/icons-vue'
import type { FormInstance } from 'ant-design-vue'

// 路由实例
const router = useRouter()

// 登录表单引用
const loginFormRef = ref<FormInstance>()

// 数据响应式
const loginForm = reactive({
    username: '',
    password: '',
    remember: false
})

const loading = ref(false)
const year = computed(() => new Date().getFullYear())

// 表单验证规则
const loginRules = {
    username: [
        { required: true, message: '请输入用户名', trigger: 'blur' },
        { min: 3, message: '用户名不能少于3个字符', trigger: 'blur' }
    ],
    password: [
        { required: true, message: '请输入密码', trigger: 'blur' },
        { min: 6, message: '密码不能少于6个字符', trigger: 'blur' }
    ]
}

// 登录方法
const handleLogin = () => {
    loading.value = true

    // 模拟API登录请求
    setTimeout(() => {
        message.success('登录成功')
        router.push('/dashboard') // 跳转到首页
        loading.value = false
    }, 1500)
}
</script>
<style scoped>
.login-container {
    display: flex;
    justify-content: center;
    align-items: center;
    height: 100vh;
}

.login-box {
    /* 增加宽度，更适合PC端显示 */
    width: 450px;
    padding: 50px;
    background-color: #fff;
    border-radius: 8px; /* 稍微增加圆角 */
    box-shadow: 0 4px 16px rgba(0, 0, 0, 0.15); /* 增强阴影效果 */
}

.login-header {
    text-align: center;
    margin-bottom: 40px; /* 增加间距 */
}

.logo-container {
    width: 100px;
    height: 100px;
    margin: 0 auto 20px;
    display: flex;
    justify-content: center;
    align-items: center;
}

.logo-image {
    width: 100%;
    height: auto;
    max-height: 100px;
    object-fit: contain;
}


.login-header h1 {
    font-size: 32px; /* 增大标题 */
    font-weight: 600;
    color: rgba(0, 0, 0, 0.85);
    margin: 0;
    margin-bottom: 12px;
}

.login-header p {
    font-size: 16px; /* 增大副标题 */
    color: rgba(0, 0, 0, 0.45);
    margin: 0;
}

.login-form {
    margin-bottom: 30px;
}

.login-options {
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-bottom: 10px; /* 添加底部间距 */
}

.forget-link {
    font-size: 14px;
    color: #1890ff;
    cursor: pointer;
}

.forget-link:hover {
    color: #40a9ff;
}

.login-button {
    width: 100%;
    height: 45px; /* 增高按钮 */
    font-size: 18px; /* 增大字体 */
}

.login-footer {
    text-align: center;
    color: rgba(0, 0, 0, 0.45);
    font-size: 14px;
    margin-top: 10px; /* 添加顶部间距 */
}

/* 调整响应式断点，使PC端体验更好 */
@media (max-width: 992px) {
    .login-box {
        width: 400px;
        padding: 40px;
    }
}

/* 平板设备 */
@media (max-width: 768px) {
    .login-box {
        width: 90%;
        padding: 35px;
    }
    
    .logo-container {
        width: 90px;
        height: 90px;
    }
}

/* 手机设备 */
@media (max-width: 576px) {
    .login-box {
        width: 95%;
        padding: 30px;
    }
    
    .logo-container {
        width: 80px;
        height: 80px;
    }
    
    .login-header h1 {
        font-size: 28px;
    }
}
</style>