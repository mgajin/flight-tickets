<template>
    <div class="container">
        <div class="row">
            <div class="col-12 col-md-4 col-lg-3">
                <FilterMenu />
            </div>
            <div class="col-12 col-md-8 col-lg-9">
                <FlightTickets :tickets="getTickets" :flights="getFlights"/>
            </div>
        </div>
    </div>
</template>

<script>

import FlightTickets from '../components/flights/FlightTickets'
import FilterMenu from '../components/forms/SideMenu'

import { mapActions, mapGetters } from "vuex"

export default {
    name: 'Flights',
    components: {
        FlightTickets,
        FilterMenu
    },
    methods: {
        ...mapActions(['GET_TICKETS', 'GET_FLIGHTS'])
    },
    computed: mapGetters(['getTickets', 'getFlights']),
    created() {
        this.GET_TICKETS();
        this.GET_FLIGHTS();
    }
}
</script>
