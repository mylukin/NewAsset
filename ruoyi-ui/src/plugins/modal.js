import { Message, MessageBox, Notification, Loading } from 'element-ui'

let loadingInstance = null

export const modal = {
  // Success message
  msgSuccess(content) {
    Message.success(content)
  },

  // Error message
  msgError(content) {
    Message.error(content)
  },

  // Warning message
  msgWarning(content) {
    Message.warning(content)
  },

  // Info message
  msg(content) {
    Message.info(content)
  },

  // Confirm dialog
  confirm(content, title = 'Confirm') {
    return MessageBox.confirm(content, title, {
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel',
      type: 'warning'
    })
  },

  // Alert dialog
  alert(content, title = 'Alert') {
    return MessageBox.alert(content, title)
  },

  // Prompt dialog
  prompt(content, title = 'Input') {
    return MessageBox.prompt(content, title, {
      confirmButtonText: 'Confirm',
      cancelButtonText: 'Cancel'
    })
  },

  // Loading
  loading(content = 'Loading...') {
    loadingInstance = Loading.service({
      lock: true,
      text: content,
      spinner: 'el-icon-loading',
      background: 'rgba(0, 0, 0, 0.7)'
    })
  },

  // Close loading
  closeLoading() {
    if (loadingInstance) {
      loadingInstance.close()
    }
  },

  // Notification
  notify(content, title = 'Notice', type = 'info') {
    Notification({
      title,
      message: content,
      type
    })
  },

  // Success notification
  notifySuccess(content, title = 'Success') {
    Notification.success({
      title,
      message: content
    })
  },

  // Error notification
  notifyError(content, title = 'Error') {
    Notification.error({
      title,
      message: content
    })
  }
}

export default modal
