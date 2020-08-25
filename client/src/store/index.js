import Vue from 'vue'
import Vuex from 'vuex'
import auth from './modules/auth'
import tickets from './modules/tickets'
import companies from './modules/companies'
import flights from './modules/flights'
import cities from './modules/cities'
import dialogs from './helpers/dialogs'

Vue.use(Vuex)

export default new Vuex.Store({
    modules: {
        auth,
        tickets,
        companies,
        flights,
        cities,
        dialogs
    }
})