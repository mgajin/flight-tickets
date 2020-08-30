<template>
    <div>
        <DataTable 
            :title="title" 
            :headers="headers" 
            :items="flights" 
            @new="openFlightDialog" 
            @delete="deleteFlight"
        />
        <FlightDialog />
    </div>
</template>

<script>

import DataTable from '../custom/DataTable'
import FlightDialog from './FlightDialog'
import { mapGetters } from 'vuex'

export default {
    components: {
        DataTable,
        FlightDialog
    },
    data: () => ({
        title: 'Flights',
        headers: [
            { text: 'Flight ID', value: 'id' },
            { text: 'Origin', value: 'origin.name' },
            { text: 'Destination', value: 'destination.name' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
    }),
    computed: mapGetters({ flights: 'getFlights' }),
    methods: {
        openFlightDialog: function () {
            this.$store.commit('show_flight_dialog')
        },
        deleteFlight: function (flight) {
            const payload = { id: flight.id, token: '' }
            this.$store.dispatch('DELETE_FLIGHT', payload)
        }
    }
}
</script>