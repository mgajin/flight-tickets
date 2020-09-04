import Axios from 'axios'
const URL = 'http://localhost:3000/app/companies'

const state = {
  companies: [],
  company: {},
  companyTickets: [],
}

const getters = {
  getCompanies: (state) => state.companies,
  getCompany: (state) => state.company,
  getCompanyTickets: (state) => state.companyTickets,
}

const mutations = {
  set_companies: (state, companies) => (state.companies = companies),
  set_company: (state, company) => (state.company = company),
  set_company_tickets: (state, tickets) => (state.companyTickets = tickets),
}

const actions = {
  async GET_COMPANIES({ commit }) {
    Axios.get(URL)
      .then((response) => {
        const { companies } = response.data
        commit('set_companies', companies)
      })
      .catch((err) => {
        const { message } = err.response.data
        alert(message)
      })
  },

  async GET_COMPANY({ commit }, id) {
    Axios.get(`${URL}/${id}`).then((respomse) => {
      const { company, tickets } = respomse.data
      commit('set_company', company)
      commit('set_company_tickets', tickets)
    })
  },

  async ADD_COMPANY({ commit }, payload) {
    Axios.post(URL, payload)
      .then((response) => {
        const { companies } = response.data
        commit('set_companies', companies)
      })
      .catch((err) => {
        const { message } = err.response.data
        alert(message)
      })
  },

  async DELETE_COMPANY({ commit }, payload) {
    Axios.delete(`${URL}/${payload.id}`)
      .then((response) => {
        const { companies } = response.data
        commit('set_companies', companies)
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
