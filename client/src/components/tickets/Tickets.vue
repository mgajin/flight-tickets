<template>
<v-container>
    <v-row>
        <v-col
            cols=12
            sm=6
            lg=4 
            v-for="ticket in getTickets" 
            :key="ticket.id">
            <TicketCard :ticket="ticket" :flight="ticket.flight" @action="reserve" />
        </v-col>
    </v-row>
    <v-row>
        <v-col cols=12>
            <v-pagination
                v-model="page"
                :length="totalPages"
            ></v-pagination>
        </v-col>
    </v-row>
</v-container>
</template>

<script>

import TicketCard from './TicketCard'
import { mapGetters, mapActions } from 'vuex'

export default {
    name: 'TicketsTable',
    data: () => ({
        page: 1,
    }),
    components: {
        TicketCard
    },
    computed: {
        ...mapGetters(['getTickets', 'getFlights', 'getPageInfo', 'getUser', 'getToken']),
        totalPages: function () {
            return this.getPageInfo.totalPages
        },
        pageQuery: function () {
            return `?page=${this.page}`
        }
    },
    watch: {
        page: function () {
            this.GET_TICKETS(this.pageQuery)
        }
    },
    methods: {
        ...mapActions(['GET_TICKETS', 'ADD_RESERVATION']),
        reserve: function () {
            if (this.getToken == null || this.getUser == null) {
                alert("Log in first")
            } else {
                const user = JSON.parse(this.getUser)
                const payload = { user: user.id, ticket: this.ticket.id, flight: this.flight.id }
                this.ADD_RESERVATION(payload)
            }
        }
    },
    created() {
        this.GET_TICKETS(this.pageQuery)
    }
}
</script>

<style>

</style>