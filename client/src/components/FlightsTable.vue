<template>
<v-container>
    <v-row>
        <v-col
            cols=12
            sm=6
            md=4 
            v-for="flight in getFlights" 
            :key="flight.id">
            <FlightCard :flight="flight" />
        </v-col>
    </v-row>
</v-container>
</template>

<script>

import FlightCard from './FlightCard'
import { mapGetters } from 'vuex'

export default {
    name: 'FlightsTable',
    components: {
        FlightCard
    },
    computed: mapGetters(['getFlights'])
}
</script>

<style>

</style>