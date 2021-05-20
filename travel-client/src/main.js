// The Vue build version to load with the `import` command
// (runtime-only or standalone) has been set in webpack.base.conf with an alias.
import Vue from 'vue'
import Router from 'vue-router'
import Vuex from 'vuex'
import App from './App'
import router from './router'
import store from './store'
import './assets/css/index.scss'
import './assets/iconfont/iconfont.css'
import ElementUI from 'element-ui'
import 'element-ui/lib/theme-chalk/index.css'
import '@/assets/js/iconfont.js'
import '@/assets/js/iconfont1.js'
import '@/assets/js/iconfont2.js'
import '@/assets/js/iconfont3.js'
import axios from 'axios'
import VueAMap from 'vue-amap'
import VueQuillEditor from 'vue-quill-editor'
import 'quill/dist/quill.core.css'
import 'quill/dist/quill.snow.css'
import 'quill/dist/quill.bubble.css'

Vue.use(Router)
Vue.use(Vuex)
Vue.use(ElementUI)
Vue.use(VueAMap)
Vue.use(VueQuillEditor)

Vue.prototype.$http = axios

Vue.config.productionTip = false

VueAMap.initAMapApiLoader({
  key: '73c60401533f65f0bd074a506ea85c2f',
  plugin: [
    'AMap.Autocomplete',
    'AMap.PlaceSearch',
    'AMap.Scale',
    'AMap.OverView',
    'AMap.ToolBar',
    'AMap.MapType',
    'AMap.PolyEditor',
    'AMap.CircleEditor',
    'AMap.Geocoder',
    'AMap.Geolocation'
  ],
  // 默认高德 sdk 版本为 1.4.4
  v: '1.4.4',
  uiVersion: '1.1'
})

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router: new Router(router),
  store: new Vuex.Store(store),
  components: { App },
  template: '<App/>'
})
