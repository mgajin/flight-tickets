<template>
    <v-container>
        <v-row>
            <v-col cols=12>
                <NewTicketForm />
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import NewTicketForm from '../components/tickets/NewTicketForm'

export default {
    name: 'Admin',
    components: {
        NewTicketForm
    }
}
</script>