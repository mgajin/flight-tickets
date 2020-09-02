<template>
  <v-card id="ticket-card">
      <v-card-title class="d-flex justify-space-between">
          <h4 class="primary--text"><v-icon color=primary style="transform: rotate(45deg);">mdi-airplane</v-icon> {{ ticket.companyName }}</h4>
            <v-btn text color=primary @click="reserve"><v-icon>mdi-ticket</v-icon></v-btn>
      </v-card-title>
      <v-card-text class="d-flex flex-column">
          <div class="d-flex justify-space-between pb-1">
            <p>From <br> <span class="text-uppercase">{{ flight.origin.name }}</span></p>
            <p>Depart <br> <span class="text-uppercase">{{ ticket.departDate }}</span></p>
          </div>
          <div class="d-flex justify-space-between pt-1">
            <p>To <br> <span class="text-uppercase">{{ flight.destination.name }}</span></p>
            <p>Return <br> <span class="text-uppercase">{{ this.returnDate }}</span></p>
          </div>
      </v-card-text>
  </v-card>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
    name: 'TicketCard',
    props: ['ticket', 'flights'],
    computed: {
        ...mapGetters(['getToken', 'getUser']),
        flight: function () {
            return this.getFlight(this.ticket.flightId)
        },
        returnDate: function () {
            return this.ticket.oneWay ? 'one-way' : this.ticket.returnDate
        }
    },
    methods: {
        getFlight(id) {
            let ticketFlight = null
            this.flights.forEach(flight => {
                if (flight.id == id) {
                    ticketFlight = flight
                }
            })
            return ticketFlight
        },
        reserve: function () {
            if (this.getToken == null || this.getUser == null) {
                alert("Log in first")
            } else {
                const user = JSON.parse(this.getUser)
                const payload = { user: user.id, ticket: this.ticket.id, flight: this.flight.id }

                // alert(JSON.stringify(payload))

                this.$store.dispatch('ADD_RESERVATION', payload)
            }
        }
    }
}
</script>

<style lang="scss">

#ticket-card {
    p {
        margin: 0;
    }
}

</style>