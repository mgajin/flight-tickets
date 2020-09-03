<template>
    <v-dialog v-model="dialog" persistent max-width="700">
        <CompanyForm />
    </v-dialog>
</template>

<script>

import CompanyForm from './CompanyForm'
import { mapGetters } from 'vuex'

export default {
    components: {
        CompanyForm
    },
    computed: mapGetters({ dialog: 'getCompanyDialog' })
}
</script>