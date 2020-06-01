import Axios from 'axios'

const state = {
    flights: [],
    tickets: []
};

const getters = {
    getFlights: state => state.flights,
    getTickets: state => state.tickets
};

const actions = {

    // Get all flights from server
    async GET_FLIGHTS({ commit }) {
        try {
            const response = await Axios.get('http://localhost:3000/app/flights');

            commit('set_flights', response.data);
        } catch (error) {
            alert(error.message);
        }
    },

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
    }
};

const mutations = {
    set_flights: (state, flights) => (state.flights = flights),
    set_tickets: (state, tickets) => (state.tickets = tickets)
};

export default {
    state, 
    getters, 
    actions,
    mutations
};