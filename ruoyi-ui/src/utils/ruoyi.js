/**
 * Parse time to formatted string
 * @param {Date|string|number} time - Time value
 * @param {string} cFormat - Format pattern
 * @returns {string|null}
 */
export function parseTime(time, cFormat) {
  if (!time) {
    return null
  }
  const format = cFormat || '{y}-{m}-{d} {h}:{i}:{s}'
  let date
  if (typeof time === 'object') {
    date = time
  } else {
    if (typeof time === 'string') {
      if (/^[0-9]+$/.test(time)) {
        time = parseInt(time)
      } else {
        time = time.replace(/-/g, '/')
      }
    }
    if (typeof time === 'number' && time.toString().length === 10) {
      time = time * 1000
    }
    date = new Date(time)
  }
  const formatObj = {
    y: date.getFullYear(),
    m: date.getMonth() + 1,
    d: date.getDate(),
    h: date.getHours(),
    i: date.getMinutes(),
    s: date.getSeconds(),
    a: date.getDay()
  }
  const timeStr = format.replace(/{([ymdhisa])+}/g, (result, key) => {
    const value = formatObj[key]
    if (key === 'a') {
      return ['Sun', 'Mon', 'Tue', 'Wed', 'Thu', 'Fri', 'Sat'][value]
    }
    return value.toString().padStart(2, '0')
  })
  return timeStr
}

/**
 * Reset form data
 * @param {string} refName - Form ref name
 */
export function resetForm(refName) {
  if (this.$refs[refName]) {
    this.$refs[refName].resetFields()
  }
}

/**
 * Add date range to query params
 * @param {Object} params - Query params
 * @param {Array} dateRange - Date range array
 * @param {string} propName - Property name prefix
 * @returns {Object}
 */
export function addDateRange(params, dateRange, propName) {
  const search = params
  search.params = typeof search.params === 'object' && search.params !== null ? search.params : {}
  dateRange = Array.isArray(dateRange) ? dateRange : []
  if (propName) {
    search.params['begin' + propName] = dateRange[0]
    search.params['end' + propName] = dateRange[1]
  } else {
    search.params.beginTime = dateRange[0]
    search.params.endTime = dateRange[1]
  }
  return search
}

/**
 * Get dict label from dict options
 * @param {Array} dicts - Dict options
 * @param {string} value - Dict value
 * @returns {string}
 */
export function selectDictLabel(dicts, value) {
  if (!dicts || !value) {
    return ''
  }
  const dict = dicts.find(d => d.value === value || d.dictValue === value)
  return dict ? (dict.label || dict.dictLabel) : value
}

/**
 * Format currency
 * @param {number} value - Number value
 * @returns {string}
 */
export function formatCurrency(value) {
  if (!value) return '0'
  return new Intl.NumberFormat('zh-CN', {
    style: 'currency',
    currency: 'CNY',
    minimumFractionDigits: 0,
    maximumFractionDigits: 0
  }).format(value)
}
