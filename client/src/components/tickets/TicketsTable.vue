<template>
<v-container>
    <v-row>
        <v-col
            cols=12
            sm=6
            lg=4 
            v-for="ticket in getTickets" 
            :key="ticket.id">
            <TicketCard :ticket="ticket" :flights="getFlights" />
        </v-col>
    </v-row>
</v-container>
</template>

<script>

import TicketCard from './TicketCard'
import { mapGetters } from 'vuex'

export default {
    name: 'TicketsTable',
    components: {
        TicketCard
    },
    computed: mapGetters(['getTickets', 'getFlights'])
}
</script>

<style>

</style>