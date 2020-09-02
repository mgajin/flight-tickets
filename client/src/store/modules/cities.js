import Axios from 'axios'
const URL = 'http://localhost:3000/app/cities'

const state = {
    cities: []
}

const getters = {
    getCities: state => state.cities
}

const mutations = {
    set_cities: (state, cities) => state.cities = cities
}

const actions = {

    async GET_CITIES({ commit }) {
        Axios.get(URL)
            .then(response => {
                const { cities } = response.data
                commit('set_cities', cities)
            })
            .catch(err => {
                const { message } = err.response.data
                alert(message)
            })
    },

    async ADD_CITY({ commit }, payload) {
        Axios.post(URL, payload)
            .then(response => {
                const { cities } = response.data
                commit('set_cities', cities)
            })
            .catch(err => {
                const { message } = err.response.data
                alert(message)
            })
    }
}

export default {
    state,
    getters,
    mutations,
    actions
}