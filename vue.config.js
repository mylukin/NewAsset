const { defineConfig } = require('@vue/cli-service');

module.exports = defineConfig({
  transpileDependencies: true,
  lintOnSave: process.env.NODE_ENV !== 'production',

  // Dev server configuration
  devServer: {
    port: 8081,
    open: true,
    proxy: {
      '/asset': {
        target: 'http://localhost:8080',
        changeOrigin: true,
        pathRewrite: {
          '^/asset': '/asset'
        }
      }
    }
  },

  // Output configuration
  outputDir: 'dist',
  assetsDir: 'static',

  // PWA configuration
  pwa: {
    name: 'Asset Management System',
    themeColor: '#409EFF',
    msTileColor: '#409EFF',
    appleMobileWebAppCapable: 'yes',
    appleMobileWebAppStatusBarStyle: 'black-translucent'
  },

  // Webpack configuration
  configureWebpack: {
    resolve: {
      alias: {
        '@': require('path').resolve(__dirname, 'src')
      }
    }
  },

  // Production source maps
  productionSourceMap: false,

  // CSS configuration
  css: {
    loaderOptions: {
      sass: {
        additionalData: `@import "@/styles/variables.scss";`
      }
    }
  }
});
