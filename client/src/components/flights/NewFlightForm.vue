<template>
    <v-card>
        <v-card-title v-text="'Create New Flight'"></v-card-title>
        <v-form>
            <v-container>
                <v-row>
                    <v-col cols=6>
                        <v-select
                            v-model="origin"
                            :items="getCities"
                            item-value="name"
                            item-text="name"
                            :rules="[v => !!v || 'Origin city is required']"
                            label="Origin city"
                            required
                        ></v-select>
                    </v-col>
                    <v-col cols=6>
                        <v-select
                            v-model="destination"
                            :items="getCities"
                            item-value="name"
                            item-text="name"
                            :rules="[v => !!v || 'Destination city is required']"
                            label="Destination city"
                            required
                        ></v-select>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
        <v-card-actions>
            <v-btn block dark @click="validateForm">submit</v-btn>
        </v-card-actions>
    </v-card>
</template>

<script>

import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'NewFlightForm',
    computed: mapGetters(['getCities']),
    methods: {
        ...mapActions(['GET_CITIES', 'CREATE_FLIGHT']),
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
            this.CREATE_FLIGHT(paylaod)
        }
    },
    created() {
        this.GET_CITIES()
    },
}
</script>