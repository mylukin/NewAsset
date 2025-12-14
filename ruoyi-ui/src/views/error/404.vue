<template>
  <div class="error-page">
    <div class="error-content">
      <h1 class="error-code">404</h1>
      <h2 class="error-message">Page Not Found</h2>
      <p class="error-description">
        The page you are looking for might have been removed,
        had its name changed, or is temporarily unavailable.
      </p>
      <el-button type="primary" @click="goHome">
        <i class="el-icon-house"></i>
        Back to Home
      </el-button>
    </div>
  </div>
</template>

<script>
export default {
  name: 'NotFound',
  methods: {
    goHome() {
      this.$router.push('/')
    }
  }
}
</script>

<style scoped>
.error-page {
  height: 100vh;
  display: flex;
  align-items: center;
  justify-content: center;
  background: #f0f2f5;
}

.error-content {
  text-align: center;
}

.error-code {
  font-size: 120px;
  font-weight: 700;
  color: #409EFF;
  margin: 0;
  line-height: 1;
}

.error-message {
  font-size: 24px;
  color: #303133;
  margin: 20px 0 10px;
}

.error-description {
  color: #909399;
  margin-bottom: 30px;
  max-width: 400px;
}
</style>
