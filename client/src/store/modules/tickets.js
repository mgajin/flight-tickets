import Axios from 'axios'
const URL = 'http://localhost:3000/app/tickets'

const state = {
    tickets: [],
    defaultTicket: null,
    editTicketForm: false
}

const getters = {
    getTickets: state => state.tickets,
    getDefaultTicket: state => state.defaultTicket,
    isEditTicketForm: state => state.editTicketForm
}

const mutations = {
    set_tickets: (state, tickets) => state.tickets = tickets,
    set_default_ticket: (state, ticket) => state.defaultTicket = ticket,
    change_ticket_form: state => state.editTicketForm = !state.editTicketForm
}

const actions = {

    async GET_TICKETS({ commit }, query = '') {
        Axios.get(URL + query)
            .then(response => {
                const { tickets } = response.data
                commit('set_tickets', tickets)
            })
            .catch(err => {
                const { message } = err.response.data
                alert(message)
            })
    },

    async CREATE_TICKET({ commit }, payload) {
        Axios.post(URL, payload)
            .then(response => {
                const { tickets } = response.data
                commit('set_tickets', tickets)
            })
            .catch(err => {
                const { message } = err.response.data
                alert(message)
            })
    },

    async UPDATE_TICKET({ commit }, payload) {
        Axios.put(`${URL}/${payload.id}`, payload)
            .then(response => {
                const { tickets } = response.data
                commit('set_tickets', tickets)
            })
            .catch(err => {
                const message = err.response.data
                alert(message)
            })
    },

    async DELETE_TICKET({ commit }, payload) {
        Axios.delete(`${URL}/${payload.id}`)
            .then(response => {
                const { tickets } = response.data
                commit('set_tickets', tickets)
            })
            .catch(err => {
                alert(err)
            })
    }
}

export default {
    state,
    getters,
    mutations,
    actions
}