import Axios from 'axios'
const URL = 'http://localhost:3000/app/flights'

const state = {
    flights: []
}

const getters = {
    getFlights: state => state.flights
}

const mutations = {
    set_flights: (state, flights) => state.flights = flights
}

const actions = {

    async GET_FLIGHTS({ commit }) {
        Axios.get(URL)
            .then(response => {
                const { flights } = response.data
                commit('set_flights', flights)
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