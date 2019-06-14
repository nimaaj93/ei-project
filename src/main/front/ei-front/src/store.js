import Vue from 'vue'
import Vuex from 'vuex'
import createPersistedState from "vuex-persistedstate";
import jwt_decode from 'jwt-decode';

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
          state.accessToken = payload.idToken;

          let decoded = jwt_decode(payload.idToken)

          let auhorities = [];

          for (let auth of decoded.auth) {
              auhorities.push(auth.authority)
          }
          state.userInfo = decoded;
          state.authorities = auhorities;
      },
      REFRESH_TOKEN (state, payload) {

      },
      LOGOUT (state) {
          state.authenticated = false;
          state.userInfo = {};
          state.authorities = [];
          state.accessToken = null;
      },
      SET_LAYOUT(state, payload) {
          state.layout = payload;
      }
  },
  actions: {
      authenticate(context,payload) {
          context.commit('AUTHENTICATE', payload);
      },
  }
})