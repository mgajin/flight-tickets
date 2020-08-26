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
                <DataTable 
                    :title="'Flights'" 
                    :headers="flightsTableHeaders" 
                    :items="getFlights" 
                    @new="openFlightDialog" 
                    @delete="deleteFlight" 
                />
            </v-col>
            <v-col cols=12>
                <DataTable 
                    :title="'Tickets'" 
                    :headers="ticketsTableHeaders" 
                    :items="getTickets" 
                    @new="openTicketDialog" 
                    @delete="deleteTicket"
                />
            </v-col>
            <v-col cols=12>
                <DataTable 
                    :title="'Users'" 
                    :headers="usersTableHeaders" 
                    :items="getUsers" 
                />
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import StatusCard from '../components/custom/StatusCard'
import FlightDialog from '../components/flights/FlightDialog'
import TicketDialog from '../components/tickets/TicketDialog'
import DataTable from '../components/custom/DataTable'
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'Admin',
    components: {
        StatusCard,
        FlightDialog,
        TicketDialog,
        DataTable
    },
    data: () => ({
        flightsTableHeaders: [
            { text: 'Flight ID', value: 'id' },
            { text: 'Origin', value: 'origin.name' },
            { text: 'Destination', value: 'destination.name' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
        ticketsTableHeaders: [
            { text: 'Ticket ID', value: 'id' },
            { text: 'Flight ID', value: 'flightId' },
            { text: 'Depart', value: 'departDate' },
            { text: 'Return', value: 'returnDate' },
            { text: 'One-Way', value: 'oneWay' },
            { text: 'Company', value: 'companyName' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
        usersTableHeaders: [
            { text: 'User ID', value: 'id' },
            { text: 'Username', value: 'username' },
            { text: 'Actions', value: 'actions', sortable: false }
        ]
    }),
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
            'GET_USERS', 
            'DELETE_FLIGHT', 
            'DELETE_TICKET'
        ]),
        openFlightDialog: function () {
            this.$store.commit('show_flight_dialog')
        },
        openTicketDialog: function () {
            this.$store.commit('show_ticket_dialog')
        },
        openCompanyDialog: function () {
            alert('Company dialog not implemented')
        },
        deleteFlight: function (flight) {
            const payload = { id: flight.id, token: '' }
            this.DELETE_FLIGHT(payload)
        },
        deleteTicket: function (ticket) {
            const payload = { id: ticket.id, token: '' }
            this.DELETE_TICKET(payload)
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