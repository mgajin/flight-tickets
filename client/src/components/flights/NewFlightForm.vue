<template>
    <v-card>
        <v-form>
            <v-container>
                <v-row>
                    <v-col cols=12 class="d-flex justify-center">
                        <v-card-title v-text="'Create New Flight'" class="headline"></v-card-title>
                    </v-col>    
                    <v-col cols=12 sm=6>
                        <v-select
                            v-model="origin"
                            :items="getCities"
                            item-value="name"
                            item-text="name"
                            :rules="[v => !!v || 'Origin city is required']"
                            label="Origin city"
                            required
                            prepend-icon="mdi-city"
                        ></v-select>
                    </v-col>
                    <v-col cols=12 sm=6>
                        <v-select
                            v-model="destination"
                            :items="getCities"
                            item-value="name"
                            item-text="name"
                            :rules="[v => !!v || 'Destination city is required']"
                            label="Destination city"
                            required
                            prepend-icon="mdi-city"
                        ></v-select>
                    </v-col>
                    <v-col cols=12>
                        <v-card-actions class="d-flex justify-center">
                            <v-btn dark @click="validateForm" v-text="'submit'"></v-btn>
                            <v-btn @click="close" v-text="'cancel'"></v-btn>
                        </v-card-actions>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </v-card>
</template>

<script>

import { mapGetters } from 'vuex'

export default {
    name: 'NewFlightForm',
    computed: mapGetters(['getCities']),
    methods: {
        validateForm: function () {
            if (this.origin == undefined || this.destination == undefined) {
                const message = 'Form is not valid!'
                alert(message)
            } else if (this.origin == this.destination) {
                const message = 'Origin and destination can not be the same city!'
                alert(message)
            } else {
                this.newFlight()
            }
        },
        newFlight: function () {
            const flight = {
                origin: this.origin,
                destination: this.destination 
            }
            this.createFlight(flight)
        },
        createFlight: function (flight) {
            const paylaod = JSON.stringify(flight)
            this.$store.dispatch('CREATE_FLIGHT', paylaod)
            this.close()
        },
        close: function () {
            this.$store.commit('hide_flight_dialog')
        }
    },
    created() {
        this.$store.dispatch('GET_CITIES')
    }
}
</script>