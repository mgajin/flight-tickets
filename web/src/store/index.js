import Vue from 'vue'
import Vuex from 'vuex'
import tickets from './modules/tickets'
import flights from './modules/flights'
import auth from './modules/auth'
import company from './modules/company'

import styles from '../assets/theme/main.scss'

Vue.use(styles)
Vue.use(Vuex)

export default new Vuex.Store({
  modules: {
    flights,
    tickets,
    auth,
    company
  }
})
