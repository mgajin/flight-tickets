<template>
    <div>
        <DataTable 
            :title="title" 
            :headers="headers" 
            :items="tickets" 
            @new="openTicketDialog"
            @edit="openTicketDialog" 
            @delete="deleteTicket"
        />
        <TicketDialog :ticket="ticket"/>
    </div>
</template>

<script>

import DataTable from '../custom/DataTable'
import TicketDialog from './TicketDialog'
import {mapGetters } from 'vuex'

export default {
    components: {
        DataTable,
        TicketDialog
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
        ticket: null
    }),
    computed: {
        ...mapGetters({ ticketDialog: 'getTicketDialog', tickets: 'getTickets', defTicket: 'getDefaultTicket' })
    },
    watch: {
        ticketDialog: function (value) {
            if (!value) {
                this.ticket = null 
            }
        },
        defTicket :function () {
            this.$store.commit('change_ticket_form') 
        }
    },
    methods: {
        openTicketDialog: function (ticket = null) {
            this.$store.commit('set_default_ticket', ticket)
            this.$store.commit('show_ticket_dialog')
        },
        deleteTicket: function (ticket) {
            const payload = { id: ticket.id, token: '' }
            this.$store.dispatch('DELETE_TICKET', payload)
        }
    }
}
</script>