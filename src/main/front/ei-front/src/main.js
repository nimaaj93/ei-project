import Vue from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import axios from 'axios'
import tether from 'tether'
import VeeValidate from 'vee-validate'
import Notifications from 'vue-notification'
import Message from './message'
// import moment from 'moment-jalaali';

window.Tether = tether;

Vue.config.productionTip = false

Vue.use(VeeValidate);
Vue.use(Notifications);

// router navigation guard
router.beforeEach((to, from, next) => {
    store.commit('SET_LAYOUT',to.meta.layout);

    if (to.meta.authenticated && store.state.authenticated) {
        next();
    } else if (to.meta.authenticated && !store.state.authenticated) {
        next("/login");
    } else if (!to.meta.authenticated) {
        next();
    }
});

// font files
require('./assets/css/fonts/fontawesome-webfont.eot')
require('./assets/css/fonts/fontawesome-webfont.svg')
require('./assets/css/fonts/fontawesome-webfont.ttf')
require('./assets/css/fonts/fontawesome-webfont.woff')
require('./assets/css/fonts/fontawesome-webfont.woff2')
require('./assets/css/fonts/FontAwesome.otf')
require('./assets/css/fonts/Sahel-Black-FD.eot')
require('./assets/css/fonts/Sahel-Black-FD.ttf')
require('./assets/css/fonts/Sahel-Black-FD.woff')
require('./assets/css/fonts/Sahel-Bold-FD.eot')
require('./assets/css/fonts/Sahel-Bold-FD.ttf')
require('./assets/css/fonts/Sahel-Bold-FD.woff')
require('./assets/css/fonts/Sahel-FD.eot')
require('./assets/css/fonts/Sahel-FD.ttf')
require('./assets/css/fonts/Sahel-FD.woff')
require('./assets/css/fonts/Simple-Line-Icons.eot')
require('./assets/css/fonts/Simple-Line-Icons.svg')
require('./assets/css/fonts/Simple-Line-Icons.ttf')
require('./assets/css/fonts/Simple-Line-Icons.woff')
require('./assets/css/fonts/Simple-Line-Icons.woff2')

// CSS files
require('./assets/css/font-style/font-awesome.min.css');
require('./assets/css/font-style/simple-line-icons.min.css');
require('./assets/css/coreui/style.css');
require('./assets/css/transition/transitions.css');
require('./assets/css/lib/autocomplete.css');
require('./assets/css/lib/vuebar.css');
require('./assets/css/custom.css');
// require('vue2-animate/dist/vue2-animate.min.css')

// Additional JS files
require('./assets/js/lib/jquery.min');
require('./assets/js/lib/bootstrap.min');
require('./assets/js/lib/pace.min');
require('./assets/js/lib/Chart.min');
require('./assets/js/coreui/app');
require('./assets/js/coreui/main');

// filters

Vue.filter('message', (value) => {
    if (Message[value]) {
        return Message[value]
    }
    return ''
});

const vueInstance = new Vue({
  router,
  store,
  render: h => h(App)
});

axios.interceptors.request.use(
    function (request) {

        if (request.url && store.state.accessToken) {
            request.headers['Authorization'] = 'Bearer ' + store.state.accessToken
        }

        return request;
    }
)

axios.interceptors.response.use(
    function (response) {

        if (response.status === 401) {
            router.replace('/login');
        }
        return response;
    },
    function (error) {
        if (error.response) {
            if (error.response.status && error.response.status === 401) {
                router.replace('/login');
            } else {
                if (error.response.data.key) {
                    vueInstance.$notify({
                        group: 'error',
                        title: vueInstance.$options.filters.message('common.error.header'),
                        text: vueInstance.$options.filters.message(error.response.data.key)
                    });
                }
            }
        }
        return Promise.reject(error.response);
    }
)

vueInstance.$mount('#app');