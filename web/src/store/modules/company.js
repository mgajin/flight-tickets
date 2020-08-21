import Axios from 'axios'

const state = {
    companies: []
}

const getters = {
    getCompanies: state => state.companies
}

const actions = {

    async GET_COMPANIES({commit}) {
        try {
            const response = await Axios.get('http://localhost:3000/app/companies');

            commit('set_companies', response.data);
        } catch (error) {
            alert(error.message);
        }
    }

}

const mutations = {
    set_companies: (state, companies) => state.companies = companies
}

export default {
    state, 
    getters, 
    actions,
    mutations
}