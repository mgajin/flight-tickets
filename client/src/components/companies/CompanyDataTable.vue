<template>
    <div>
        <DataTable 
            :title="title" 
            :headers="headers" 
            :items="companies" 
            @new="openCompanyDialog"
            @edit="openCompanyDialog" 
            @delete="deleteCompany"
        />
        <CompanyDialog />
    </div>
</template>

<script>

import DataTable from '../custom/DataTable'
import CompanyDialog from './CompanyDialog'
import { mapGetters } from 'vuex'

export default {
    components: {
        DataTable,
        CompanyDialog
    },
    data: () => ({
        title: 'Companies',
        headers: [
            { text: 'Company ID', value: 'id' },
            { text: 'Name', value: 'name' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
    }),
    computed: mapGetters({ companies: 'getCompanies' }),
    methods: {
        openCompanyDialog: function () {
            this.$store.commit('show_company_dialog')
        },
        deleteCompany: function (company) {
            const payload = { id: company.id, token: '' }
            this.$store.dispatch('DELETE_COMPANY', payload)
        }
    }
}
</script>