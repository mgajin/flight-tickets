<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <v-card>
                    <v-row>
                        <v-col cols=12>
                            <v-tabs grow>
                                <v-tab>
                                    <v-icon>mdi-login-variant</v-icon>
                                </v-tab>
                                <v-tab>
                                    <v-icon>mdi-account-plus</v-icon>
                                </v-tab>
                                <v-tab-item>
                                    <SignInForm />
                                </v-tab-item>
                                <v-tab-item>
                                    <SignUpForm />
                                </v-tab-item>
                            </v-tabs>
                        </v-col>
                    </v-row>
                </v-card>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import SignInForm from '../components/users/SignInForm'
import SignUpForm from '../components/users/SignUpForm'

export default {
    name: 'Login',
    data: () => ({
        tab: null
    }),
    components: {
        SignInForm,
        SignUpForm
    },
    watch: {
        token: function (value) {
            if (value) this.$router.push('/')
        }
    },
    computed: {
        token: function () {
            return this.$store.getters.getToken
        }
    }
}
</script>