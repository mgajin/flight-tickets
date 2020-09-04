<template>
    <v-container>
        <v-row>
            <v-col>
                <v-card>
                    <v-card-title class="d-flex justify-space-between primary--text">
                        <h1 class="">{{ company.name }}</h1>
                        <h4>Total: {{ tickets.length }}</h4>
                    </v-card-title>
                </v-card>
            </v-col>
        </v-row>
        <v-row>
            <v-col
                cols=12
                sm=6
                lg=4 
                v-for="ticket in tickets" 
                :key="ticket.id">
                <TicketCard :ticket="ticket" :flight="ticket.flight" />
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import TicketCard from '../components/tickets/TicketCard'
import { mapActions, mapGetters } from 'vuex'

export default {
    props: ['id'],
    components: {
        TicketCard
    },
    computed: {
        ...mapGetters({
            tickets: 'getCompanyTickets',
            company: 'getCompany'
        })
    },
    methods: { 
        ...mapActions({
            getCompany: 'GET_COMPANY',
            getTickets: 'GET_COMPANY_TICKETS'
        })
    },
    created() {
        this.getCompany(this.id)
        this.getTickets(this.id)
    }
}
</script>

<style>

</style>