<template>
    <v-dialog v-model="ticketDialog" persistent max-width="700" >
        <TicketForm />
    </v-dialog>
</template>

<script>

import TicketForm from './TicketForm'
import { mapGetters } from 'vuex'

export default {
    components: {
        TicketForm
    },
    computed: mapGetters({ ticketDialog: 'getTicketDialog' })
    
}
</script>