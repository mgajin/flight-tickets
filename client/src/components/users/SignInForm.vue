<template>
    <v-form>
        <v-container>
            <v-row>
                <v-col cols=12>
                    <v-text-field
                        v-model="username"
                        label="Username"
                        prepend-icon="mdi-account"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols=12>
                    <v-text-field
                        v-model="password"
                        label="Password"
                        prepend-icon="mdi-lock"
                        type="password"
                        required
                    ></v-text-field>
                </v-col>
                <v-col cols=12>
                    <LoadingButton 
                        :text="'sign in'" 
                        :loading=isLoading 
                        @clicked="login"
                    />
                </v-col>
            </v-row>
        </v-container>
    </v-form>
</template>

<script>

import { mapGetters } from 'vuex'
import LoadingButton from '../custom/LoadingButton'

export default {
    name: 'SignInForm',
    data: () => ({
        username: '',
        password: ''
    }),
    components: {
        LoadingButton
    },
    computed: mapGetters(['isLoading']),
    methods: {
        login() {
            const user = { username: this.username, password: this.password }
            this.$store.dispatch('SIGN_IN', user)
        }
    }
}
</script>