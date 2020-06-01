import Axios from 'axios';

const state = {
    user: {}
};

const getters = {
    getUser: state => state.user,
    isLogged: () => {
        return localStorage.getItem('token') !== 'null' ? true : false;
    }
};

const actions = {

    // Login user
    async SIGN_IN({ commit }, payload) {
        try {
            const response = await Axios.post(
                `http://localhost:3000/v1/auth/login`,
                {
                    username: payload.username,
                    password: payload.password
                }
            );

            if (response.data.auth) {
                localStorage.setItem('token', response.data.token);
                commit('set_user', response.data.user);
            }
        } catch (error) {
            alert(error.message);
        }
    },

    // Register new user
    async SIGN_UP({ commit }, payload) {
        try {
            const response = await Axios.post(
                `http://localhost:3000/v1/auth/register`,
                {
                    username: payload.username,
                    email: payload.email,
                    password: payload.password,
                    password2: payload.password2
                }
            );

            if (response.data.auth) {
                localStorage.setItem('token', response.data.token);

                commit('set_user', response.data.user);
            }
        } catch (error) {
            alert(error.message);
        }
    },
};

const mutations = {
    set_user: (state, user) => {
        state.user = user;
    }
};

export default {
    state,
    getters,
    actions,
    mutations
};
