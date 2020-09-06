import Axios from 'axios'
const URL = 'http://localhost:3000/app/reservations'

const state = {
  reservations: [],
}

const getters = {
  getReservations: (state) => state.reservations,
}

const mutations = {
  set_reservations: (state, reservations) =>
    (state.reservations = reservations),
}

const actions = {
  async ADD_RESERVATION({ commit }, payload) {
    alert(JSON.stringify(payload))
    Axios.post(URL, payload)
      .then((response) => {
        const { reservations } = response.data
        commit('set_reservations', reservations)
      })
      .catch((err) => {
        const { message } = err.response.data
        alert(message)
      })
  },

  async GET_RESERVATIONS({ commit }, userId) {
    Axios.get(`${URL}/${userId}`)
      .then((response) => {
        const { reservations } = response.data
        commit('set_reservations', reservations)
      })
      .catch((err) => {
        const { message } = err.response.data
        alert(message)
      })
  },

  async REMOVE_RESERVATION({ commit }, payload) {
    Axios.delete(URL, payload)
      .then((response) => {
        const { reservations } = response.data
        commit('set_reservations', reservations)
      })
      .catch((err) => {
        const { message } = err.response.data
        alert(message)
      })
  },
}

export default {
  state,
  getters,
  mutations,
  actions,
}
