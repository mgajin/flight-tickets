<template>
    <v-dialog v-model="getFlightDialog" persistent max-width="700">
        <NewFlightForm />
    </v-dialog>
</template>

<script>

import NewFlightForm from './NewFlightForm'
import { mapGetters } from 'vuex'

export default {
    components: {
        NewFlightForm
    },
    computed: mapGetters(['getFlightDialog'])
}
</script>