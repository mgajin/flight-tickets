import Vue from 'vue'
import Vuex from 'vuex'
import flights from './modules/flights';

import styles from '../assets/theme/main.scss';

Vue.use(styles)
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    flights
  }
})
