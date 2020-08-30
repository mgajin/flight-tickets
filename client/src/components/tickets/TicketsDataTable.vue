<template>
    <DataTable 
        :title="title" 
        :headers="headers" 
        :items="tickets" 
        @new="openTicketDialog"
        @edit="openTicketDialog" 
        @delete="deleteTicket"
    />
</template>

<script>

import DataTable from '../custom/DataTable'

export default {
    components: {
        DataTable
    },
    data: () => ({
        title: 'Tickets',
        headers: [
            { text: 'Ticket ID', value: 'id' },
            { text: 'Flight ID', value: 'flightId' },
            { text: 'Depart', value: 'departDate' },
            { text: 'Return', value: 'returnDate' },
            { text: 'One-Way', value: 'oneWay' },
            { text: 'Company', value: 'companyName' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
    }),
    computed: {
        tickets: function () {
            return this.$store.getters.getTickets
        }
    },
    methods: {
        openTicketDialog: function () {
            this.$store.commit('show_ticket_dialog')
        },
        deleteTicket: function (ticket) {
            const payload = { id: ticket.id, token: '' }
            this.$store.dispatch('DELETE_TICKET', payload)
        }
    }
}
</script>