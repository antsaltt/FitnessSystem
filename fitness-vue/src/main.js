// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import App from './App'
import router from './router'
//import ElementUI
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import locale from 'element-ui/lib/locale/lang/en'
//add axios
var axios = require('axios')
Vue.prototype.$axios = axios
// send request to http://localhost:8088/api
axios.defaults.baseURL = 'http://localhost:8088/api'
//import store
import store from './store'

// ✅ import vue2-google-maps
import * as VueGoogleMaps from 'vue2-google-maps'

// ✅ register Google Maps plugin
Vue.use(VueGoogleMaps, {
  load: {
    key: 'AIzaSyCQWYKAeIFJA_ncdoNCNDivxbNBUAb79U4',
    libraries: 'places',
  }
})

Vue.config.productionTip = false

Vue.use(ElementUI, { locale })

//hook
router.beforeEach((to, from, next) => {
  if (to.meta.requireStaffAuth) {
    if (store.state.token) {
      if (store.state.isStaff === false) {
        Vue.prototype.$message.error("Error: Please login to your staff account.");
        next({
          path: 'loginstaff',
          query: { redirect: to.fullPath }
        })
      } else {
        next()
      }
    } else {
      Vue.prototype.$message.error("Error: Please login to your staff account.");
      next({
        path: 'loginstaff',
        query: { redirect: to.fullPath }
      })
    }
  } else if (to.meta.requireUserAuth) {
    if (store.state.token && store.state.isStaff === false) {
      next()
    } else {
      Vue.prototype.$message.error("Error: Please login to your customer account.");
      next({
        path: 'login',
        query: { redirect: to.fullPath }
      })
    }
  } else {
    next()
  }
})


new Vue({
  el: '#app',
  router,
  store,
  components: { App },
  template: '<App/>'
})
