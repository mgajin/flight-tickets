<template>
    <v-dialog v-model="dialog" persistent max-width="700">
        <FlightForm />
    </v-dialog>
</template>

<script>

import FlightForm from './FlightForm'
import { mapGetters } from 'vuex'

export default {
    components: {
        FlightForm
    },
    computed: mapGetters({ dialog: 'getFlightDialog' })
}
</script>