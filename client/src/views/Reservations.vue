<template>
    <v-container>
        <v-row>
            <v-col
                cols=12
                sm=6
                lg=4 
                v-for="reservation in reservations" 
                :key="reservation.id">
                <TicketCard :ticket="reservation.ticket" :flight="reservation.flight" @action="removeReservation(reservation.id)"/>
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
        ...mapActions(['GET_RESERVATIONS', 'REMOVE_RESERVATION']),
        removeReservation: function (reservationId) {
            if (this.token == null || this.user == null) {
                alert("Log in first")
            } else {
                const user = JSON.parse(this.user)
                const payload = { user: user.id, reservation: reservationId }
                this.REMOVE_RESERVATION(payload)
            }
        }
    },
    created() {
        const user = JSON.parse(this.user)
        this.GET_RESERVATIONS(user.id)
    }
}
</script>