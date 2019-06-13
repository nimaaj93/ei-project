import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";

Vue.use(Vuex)

export default new Vuex.Store({
  plugins: [createPersistedState()],
  state: {
      layout: 'simple',
      userInfo: {},
      authenticated: false,
      accessToken: null,
      authorities: [],
  },
  mutations: {
      AUTHENTICATE (state, payload) {
          state.authenticated = true;
          state.accessToken = payload.access_token;
          state.authorities = payload.authorities;
      },
      REFRESH_TOKEN (state, payload) {

      },
      LOGOUT (state) {
          state.authenticated = false;
          state.userInfo = {};
          state.authorities = [];
          state.accessToken = null;
      },
  },
  actions: {
      authenticate(context,payload) {
          context.commit('AUTHENTICATE', payload);
      },
  }
})