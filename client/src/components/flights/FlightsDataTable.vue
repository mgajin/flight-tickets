<template>
    <DataTable 
        :title="title" 
        :headers="headers" 
        :items="flights" 
        @new="openFlightDialog" 
        @delete="deleteFlight"
    />
</template>

<script>

import DataTable from '../custom/DataTable'

export default {
    components: {
        DataTable
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
    computed: {
        flights: function () {
            return this.$store.getters.getFlights
        }
    },
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