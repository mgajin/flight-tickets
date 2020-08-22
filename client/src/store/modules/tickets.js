import Axios from 'axios'
const URL = 'http://localhost:3000/app/tickets'

const state = {
    tickets: []
}

const getters = {
    getTickets: state => state.tickets
}

const mutations = {
    set_tickets: (state, tickets) => state.tickets = tickets
}

const actions = {

    async GET_TICKETS({ commit }, query = '') {
        Axios.get(URL + query)
            .then(response => {
                const tickets = response.data
                commit('set_tickets', tickets)
            })
            .catch(err => {
                const { message } = err.response.data
                alert(message)
            })
    },

    async CREATE_TICKET({ commit }, payload) {
        const body = {}
        const headers = { Authorization: `Bearer ${payload.token}` }

        Axios.post(URL, body, { headers })
            .then(response => {
                const tickets = response.data
                commit('set_tickets', tickets)
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