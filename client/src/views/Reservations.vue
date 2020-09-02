<template>
    <v-container>
        <v-row>
            <v-col
                cols=12
                sm=6
                lg=4 
                v-for="reservation in reservations" 
                :key="reservation.id">
                <TicketCard :ticket="reservation.ticket" :flight="reservation.flight"/>
            </v-col>
        </v-row>
    </v-container>
</template>

<script>

import TicketCard from '../components/tickets/TicketCard'
import { mapActions, mapGetters } from 'vuex'

export default {
    name: 'Reservations',
    components: {
        TicketCard
    },
    computed: {
        ...mapGetters({
            token: 'getToken',
            user: 'getUser',
            reservations: 'getReservations'
        })
    },
    watch: {
        token: function(value) {
            if (value == null) {
                this.$router.push('/login')
            }
        }
    },
    methods: {
        ...mapActions(['GET_RESERVATIONS', 'DELETE_RESERVATION'])
    },
    created() {
        const user = JSON.parse(this.user)
        this.GET_RESERVATIONS(user.id)
    }
}
</script>