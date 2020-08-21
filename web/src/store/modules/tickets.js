import Axios from 'axios'

const state = {
    tickets: []
}

const getters = {
    getTickets: state => state.tickets
}

const actions = {

    // Get all tickets from server
    async GET_TICKETS({ commit }) {
        try {
            const response = await Axios.get('http://localhost:3000/app/tickets');

            commit('set_tickets', response.data);
        } catch (error) {
            alert(error.message);
        }
    },


    // Get filtered tickets from server
    async FILTER_TICKETS({ commit }, filter) {
        try {
            const response = await Axios.get(`http://localhost:3000/app/tickets?oneWay=${filter}`);

            commit('set_tickets', response.data);
        } catch (error) {
            alert(error.message);
        }
    },

    async CREATE_TICKET(payload) {
        try {
            await Axios.post('http:localhost:3000/app/tickets', payload)
        } catch (error) {
            alert(error.message)
        }
    }


}

const mutations = {
    set_tickets: (state, tickets) => (state.tickets = tickets),
    add_ticket: (state, ticket) => state.tickets.push(ticket)
}

export default {
    state,
    getters,
    actions,
    mutations
}