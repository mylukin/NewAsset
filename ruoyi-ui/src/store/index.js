import Vue from 'vue'
import Vuex from 'vuex'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    sidebar: {
      opened: true
    },
    user: {
      name: 'Admin',
      avatar: ''
    }
  },
  mutations: {
    TOGGLE_SIDEBAR(state) {
      state.sidebar.opened = !state.sidebar.opened
    },
    SET_USER(state, user) {
      state.user = user
    }
  },
  actions: {
    toggleSidebar({ commit }) {
      commit('TOGGLE_SIDEBAR')
    },
    setUser({ commit }, user) {
      commit('SET_USER', user)
    }
  },
  getters: {
    sidebarOpened: state => state.sidebar.opened,
    userName: state => state.user.name
  }
})
