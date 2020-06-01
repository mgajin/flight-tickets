<template>
    <div class="container">
        <div class="row">
            <div class="col-3" id="filter-form">
                <FilterForm />
            </div>
            <div class="col-9">
                <FlightTickets :tickets="getTickets" :flights="getFlights"/>
            </div>
        </div>
    </div>
</template>

<script>

import FilterForm from '../components/forms/FilterForm'
import FlightTickets from '../components/flights/FlightTickets'

import { mapActions, mapGetters } from "vuex"

export default {
    name: 'Flights',
    components: {
        FilterForm,
        FlightTickets
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
