import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex);

export default new Vuex.Store({
  state: {
    user: null,
    token: null,
    permissions: [],
    roles: []
  },
  mutations: {
    SET_USER(state, user) {
      state.user = user;
    },
    SET_TOKEN(state, token) {
      state.token = token;
    },
    SET_PERMISSIONS(state, permissions) {
      state.permissions = permissions;
    },
    SET_ROLES(state, roles) {
      state.roles = roles;
    }
  },
  actions: {
    async login({ commit }, credentials) {
      try {
        // TODO: Implement actual login API call
        const response = await this.$http.post('/auth/login', credentials);
        const { token, user } = response.data;

        commit('SET_TOKEN', token);
        commit('SET_USER', user);
        commit('SET_PERMISSIONS', user.permissions || []);
        commit('SET_ROLES', user.roles || []);

        return response;
      } catch (error) {
        throw error;
      }
    },
    logout({ commit }) {
      commit('SET_TOKEN', null);
      commit('SET_USER', null);
      commit('SET_PERMISSIONS', []);
      commit('SET_ROLES', []);
    }
  },
  getters: {
    isAuthenticated: state => !!state.token,
    user: state => state.user,
    permissions: state => state.permissions,
    roles: state => state.roles,
    hasPermission: state => permission => {
      return state.permissions.includes(permission);
    }
  }
});
