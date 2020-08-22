<template>
    <v-card>
        <v-form>
            <v-container>
                <v-row>
                    <v-col cols=6>
                        <v-select
                            v-model="company"
                            :items="getCompanies"
                            item-value="name"
                            item-text="name"
                            :rules="[v => !!v || 'Company is required']"
                            label="Company"
                            required
                        ></v-select>
                    </v-col>
                    <v-col cols=6>
                        <v-select
                            v-model="flight"
                            :items="getFlights"
                            item-value="id"
                            :item-text="item => `${item.origin.name} - ${item.destination.name}`"
                            :rules="[v => !!v || 'Flight is required']"
                            label="Flight"
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

import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'NewTicketForm',
    computed: {
        ...mapGetters(['getFlights', 'getCompanies']),    
    },
    methods: {
        ...mapActions(['GET_FLIGHTS', 'GET_COMPANIES', 'CREATE_TICKET']),
        validateForm: function () {
            if (this.flight != undefined && this.company != undefined) {
                this.newTicket()
            } else {
                const message = 'Form is not valid!'
                alert(message)
            }
        },
        newTicket: function () {
            const ticket = {
                companyName: this.company,
                flightId: this.flight,
                oneWay: false,
                count: 5
            }
            this.createTicket(ticket)
        },
        createTicket: function (ticket) {
            const payload = JSON.stringify(ticket)
            this.CREATE_TICKET(payload)
        }
    },
    created() {
        this.GET_COMPANIES()
        this.GET_FLIGHTS()
    }
}
</script>