<template>
    <DataTable 
        :title="title" 
        :headers="headers" 
        :items="flights" 
        @new="openFlightDialog"
        @edit="openFlightDialog" 
        @delete="deleteFlight"
    />
</template>

<script>
export default {
    data: () => ({
        title: 'Users',
        headers: [
            { text: 'User ID', value: 'id' },
            { text: 'Username', value: 'username' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
    }),
    computed: {
        flights: function () {
            return this.$store.getters('getUsers')
        }
    }
}
</script>