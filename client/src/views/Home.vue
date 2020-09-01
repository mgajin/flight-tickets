<template>
  <div class="home">
    <Tickets />
    <Flights />
  </div>
</template>

<script>

import Tickets from '../components/tickets/Tickets'
import Flights from '../components/flights/Flights'

import { mapActions } from 'vuex' 

export default {
  name: 'Home',
  components: {
    Tickets,
    Flights
  },
  methods: {
    ...mapActions(['GET_FLIGHTS', 'GET_TICKETS'])
  },
  created() {
    this.GET_FLIGHTS()
  }
}
</script>