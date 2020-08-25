<template>
    <v-container>
        <v-row>
            <v-col cols=6 md=4 lg=3>
                <ColorCard :data="flightsCardData" @action="openFlightDialog" />
            </v-col>
            <v-col cols=6 md=4 lg=3>
                <ColorCard :data="ticketsCardData" @action="openTicketDialog" />
            </v-col>
            <v-col cols=6 md=4 lg=3>
                <ColorCard :data="companiesCardData" @action="openCompanyDialog" />
            </v-col>
        </v-row>
        <FlightDialog />
        <TicketDialog />
    </v-container>
</template>

<script>

import ColorCard from '../components/custom/ColorCard'
import FlightDialog from '../components/flights/FlightDialog'
import TicketDialog from '../components/tickets/TicketDialog'
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'Admin',
    components: {
        ColorCard,
        FlightDialog,
        TicketDialog
    },
    computed: {
        ...mapGetters(['getFlights', 'getTickets', 'getCompanies']),
        flightsCardData: function () {
            const data = { 
                title: 'Flights',
                status: this.getFlights.length, 
                color: 'primary' 
            }
            return data
        },
        ticketsCardData: function () {
            const data = { 
                title: 'Tickets',
                status: this.getTickets.length, 
                color: 'purple' 
            }
            return data
        },
        companiesCardData: function () {
            const data = { 
                title: 'Companies',
                status: this.getCompanies.length, 
                color: 'orange' 
            }
            return data
        }
    },
    methods: {
        ...mapActions(['GET_FLIGHTS', 'GET_TICKETS', 'GET_COMPANIES']),
        openFlightDialog: function () {
            this.$store.commit('show_flight_dialog')
        },
        openTicketDialog: function () {
            this.$store.commit('show_ticket_dialog')
        },
        openCompanyDialog: function () {
            alert('Company dialog not implemented')
        }
    },
    created() {
        this.GET_FLIGHTS()
        this.GET_TICKETS()
        this.GET_COMPANIES()
    }
}
</script>