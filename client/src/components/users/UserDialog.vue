<template>
    <v-dialog v-model="dialog" persistent max-width="700" >
        <UserForm :user="user"/>
    </v-dialog>
</template>

<script>

import UserForm from './UserForm'
import { mapGetters } from 'vuex'

export default {
    props: ['user'],
    components: {
        UserForm
    },
    computed: mapGetters({ dialog: 'getUserDialog' })
    
}
</script>