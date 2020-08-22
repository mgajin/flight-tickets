<template>
  <v-card id="ticket-card">
      <v-card-title>
          <h4 class="primary--text"><v-icon color=primary style="transform: rotate(45deg);">mdi-airplane</v-icon> {{ ticket.companyName }}</h4>
      </v-card-title>
      <v-card-text class="d-flex flex-column">
          <div class="d-flex justify-space-between pb-1">
            <p>From <br> <span class="text-uppercase">{{ flight.origin.name }}</span></p>
            <p>Depart <br> <span class="text-uppercase">some date</span></p>
          </div>
          <div class="d-flex justify-space-between pt-1">
            <p>To <br> <span class="text-uppercase">{{ flight.destination.name }}</span></p>
            <p>Return <br> <span class="text-uppercase">some date</span></p>
          </div>
      </v-card-text>
  </v-card>
</template>

<script>

export default {
    name: 'TicketCard',
    props: ['ticket', 'flights'],
    computed: {
        flight: function () {
            return this.getFlight(this.ticket.flightId)
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
        }
    },
    created() {
        // this.flight = this.getFlight(this.ticket.flightId)
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