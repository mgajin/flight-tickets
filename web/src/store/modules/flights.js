import Axios from 'axios'

const state = {
    flights: []
};

const getters = {
    getFlights: state => state.flights
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