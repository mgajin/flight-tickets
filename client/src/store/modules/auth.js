import Axios from 'axios'
const URL = "http://localhost:3000/v1/auth"

const state = {
    user: window.localStorage.getItem('user'),
    token: window.localStorage.getItem('token'),
    loading: false,
    error: null
}

const getters = {
    getUser: state => state.user,
    getToken: state => state.token,
    getLoading: state => state.loading,
    getError: state => state.error
}

const mutations = {
    set_user: (state, user) => {
        state.user = user
        const json = JSON.stringify(user)
        localStorage.setItem('user', json)
    },
    set_token: (state, token) => {
        state.token = token
        localStorage.setItem('token', token)
    },
    clear_token: state => {
        state.token = null
        state.user = null
        localStorage.clear()
    },
    set_loading: (state, payload) => state.loading = payload,
    set_error: (state, payload) => state.error = payload,
    clear_error: state => state.error = null
}

const actions = {
    async SIGN_IN({ commit }, payload) {
        Axios.get(`${URL}/login`, payload)
            .then(response => {
                const { user, token } = response.data
                commit('set_user', user)
                commit('set_token', token)
            })
            .catch(err => {
                const { message } = err.response.message
                alert(message)
            })
    },

    async SIGN_UP({ commit }, payload) {
        Axios.post(`${URL}/register`, payload)
            .then(response => {
                const { user, token } = response.data
                commit('set_user', user)
                commit('set_token', token)
            })
            .catch(err => {
                const { message } = err.response.message
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