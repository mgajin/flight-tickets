import Axios from 'axios'
const URL = "http://localhost:3000/app/companies"

const state = {
    companies: []
}

const getters = {
    getCompanies: state => state.companies
}

const mutations = {
    set_companies: (state, companies) => state.companies = companies
}

const actions = {

    async GET_COMPANIES({ commit }) {
        Axios.get(URL)
            .then(response => {
                const { companies } = response.data
                commit('set_companies', companies)
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