<template>
    <v-container>
        <v-row>
            <v-col cols=6 md=4 lg=3>
                <StatusCard :title="'Flights'" :status="totalFlights" :color="'primary'" />
            </v-col>
            <v-col cols=6 md=4 lg=3>
                <StatusCard :title="'Tickets'" :status="totalTickets" :color="'purple'" />
            </v-col>
            <v-col cols=6 md=4 lg=3>
                <StatusCard :title="'Companies'" :status="totalCompanies" :color="'orange'" />
            </v-col>
            <v-col cols=6 md=4 lg=3>
                <StatusCard :title="'Users'" :status="totalUsers" :color="'pink'" />
            </v-col>
        </v-row>
        <v-divider class="mt-3 mb-3"></v-divider>
        <FlightDialog />
        <TicketDialog />
        <v-row>
            <v-col cols=12>
                <FlightsDataTable />
            </v-col>
            <v-col cols=12>
                <TicketsDataTable />
            </v-col>
            <v-col cols=12>
                <UsersDataTable />
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import StatusCard from '../components/custom/StatusCard'
import FlightDialog from '../components/flights/FlightDialog'
import TicketDialog from '../components/tickets/TicketDialog'
import TicketsDataTable from '../components/tickets/TicketsDataTable'
import FlightsDataTable from '../components/flights/FlightsDataTable'
import UsersDataTable from '../components/users/UsersDataTable'

import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'Admin',
    components: {
        StatusCard,
        FlightDialog,
        TicketDialog,
        TicketsDataTable,
        FlightsDataTable,
        UsersDataTable
    },
    computed: {
        ...mapGetters(['getFlights', 'getTickets', 'getCompanies', 'getUsers']),
        totalFlights: function () {
            return this.getFlights.length
        },
        totalTickets: function ()  {
            return this.getTickets.length
        },
        totalCompanies: function () {
            return this.getCompanies.length
        },
        totalUsers: function () {
            return this.getUsers.length
        }
    },
    methods: {
        ...mapActions([
            'GET_FLIGHTS', 
            'GET_TICKETS', 
            'GET_COMPANIES', 
            'GET_USERS'
        ]),
        openCompanyDialog: function () {
            alert('Company dialog not implemented')
        }
    },
    created() {
        this.GET_FLIGHTS()
        this.GET_TICKETS()
        this.GET_COMPANIES()
        this.GET_USERS()
    }
}
</script>