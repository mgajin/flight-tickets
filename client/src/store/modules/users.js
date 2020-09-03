import Axios from 'axios'
const URL = 'http://localhost:3000/app/users'

const state = {
    users: []
}

const getters = {
    getUsers: state => state.users
}

const mutations = {
    set_users: (state, users) => state.users = users
}

const actions = {

    async GET_USERS({ commit }) {
        Axios.get(URL)
            .then(response => {
                const { users } = response.data
                commit('set_users', users)
            })
            .catch(err => {
                alert(err)
            })
    },

    async UPDATE_USER({ commit }, user) {
        Axios.put(`${URL}/${user.id}`, user)
            .then(response => {
                const { users } = response.data
                commit('set_users', users)
            })
            .catch(err => {
                alert(err)
            })
    },

    async DELETE_USER({ commit }, payload) {
        Axios.delete(`${URL}/${payload.id}`)
            .then(response => {
                const { users } = response.data
                commit('ser_users', users)
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