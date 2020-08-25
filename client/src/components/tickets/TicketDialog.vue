<template>
    <v-dialog v-model="getTicketDialog" persistent max-width="700" >
        <NewTicketForm />
    </v-dialog>
</template>

<script>

import NewTicketForm from './NewTicketForm'
import { mapGetters } from 'vuex'

export default {
    components: {
        NewTicketForm
    },
    computed: mapGetters(['getTicketDialog'])
}
</script>