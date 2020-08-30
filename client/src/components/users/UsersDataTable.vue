<template>
    <DataTable 
        :title="title" 
        :headers="headers" 
        :items="users"
    />
</template>

<script>

import DataTable from '../custom/DataTable'

export default {
    components: {
        DataTable
    },
    data: () => ({
        title: 'Users',
        headers: [
            { text: 'User ID', value: 'id' },
            { text: 'Username', value: 'username' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
    }),
    computed: {
        users: function () {
            return this.$store.getters.getUsers
        }
    }
}
</script>