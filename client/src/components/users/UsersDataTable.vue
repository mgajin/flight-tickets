<template>
    <div>
        <DataTable 
            :title="title" 
            :headers="headers" 
            :items="users"
            @edit="openUserDialog"
        />
        <UserDialog :user="user"/>
    </div>
</template>

<script>

import DataTable from '../custom/DataTable'
import UserDialog from './UserDialog'

export default {
    components: {
        DataTable,
        UserDialog
    },
    data: () => ({
        title: 'Users',
        headers: [
            { text: 'User ID', value: 'id' },
            { text: 'Username', value: 'username' },
            { text: 'Actions', value: 'actions', sortable: false }
        ],
        user: null
    }),
    computed: {
        users: function () {
            return this.$store.getters.getUsers
        }
    },
    methods: {
        openUserDialog: function (user = null) {
            this.user = user
            this.$store.commit('show_user_dialog')
        },
        deleteUser: function (user) {
            const payload = { id: user.id, token: '' }
            alert(`User to delete: ${JSON.stringify(payload)}`)
            // this.$store.dispatch('DELETE_FLIGHT', payload)
        }
    }
}
</script>