<template>
  <div class="login-container">
    <div class="login-box">
      <div class="login-header">
        <h1>Asset Management System</h1>
        <p>Please sign in to continue</p>
      </div>

      <el-form
        ref="loginForm"
        :model="loginForm"
        :rules="rules"
        class="login-form"
        @submit.prevent="handleLogin"
      >
        <el-form-item prop="username">
          <el-input
            v-model="loginForm.username"
            placeholder="Username"
            prefix-icon="el-icon-user"
            size="large"
          ></el-input>
        </el-form-item>

        <el-form-item prop="password">
          <el-input
            v-model="loginForm.password"
            type="password"
            placeholder="Password"
            prefix-icon="el-icon-lock"
            size="large"
            @keyup.enter.native="handleLogin"
          ></el-input>
        </el-form-item>

        <el-form-item>
          <el-button
            type="primary"
            size="large"
            style="width: 100%"
            :loading="loading"
            @click="handleLogin"
          >
            Sign In
          </el-button>
        </el-form-item>
      </el-form>

      <div class="login-footer">
        <p>Demo Credentials</p>
        <p>Username: admin | Password: admin123</p>
      </div>
    </div>
  </div>
</template>

<script>
export default {
  name: 'Login',
  data() {
    return {
      loading: false,
      loginForm: {
        username: '',
        password: ''
      },
      rules: {
        username: [
          { required: true, message: 'Please input username', trigger: 'blur' }
        ],
        password: [
          { required: true, message: 'Please input password', trigger: 'blur' }
        ]
      }
    };
  },
  methods: {
    async handleLogin() {
      this.$refs.loginForm.validate(async valid => {
        if (valid) {
          this.loading = true;
          try {
            await this.$store.dispatch('login', this.loginForm);
            this.$router.push('/');
          } catch (error) {
            this.$message.error('Login failed. Please check your credentials.');
          } finally {
            this.loading = false;
          }
        }
      });
    }
  }
};
</script>

<style lang="scss" scoped>
.login-container {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
}

.login-box {
  width: 400px;
  padding: 40px;
  background: #fff;
  border-radius: 10px;
  box-shadow: 0 10px 40px rgba(0, 0, 0, 0.2);

  .login-header {
    text-align: center;
    margin-bottom: 30px;

    h1 {
      font-size: 24px;
      color: #303133;
      margin-bottom: 10px;
    }

    p {
      color: #909399;
      font-size: 14px;
    }
  }

  .login-form {
    margin-bottom: 20px;
  }

  .login-footer {
    text-align: center;
    font-size: 12px;
    color: #909399;

    p {
      margin: 5px 0;
    }
  }
}
</style>
