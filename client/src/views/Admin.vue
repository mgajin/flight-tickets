<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <NewTicketForm />
            </v-col>
            <v-col cols=12>
                <NewFlightForm />
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import NewTicketForm from '../components/tickets/NewTicketForm'
import NewFlightForm from '../components/flights/NewFlightForm'

export default {
    name: 'Admin',
    components: {
        NewTicketForm,
        NewFlightForm
    }
}
</script>