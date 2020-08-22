<template>
  <div class="home">
    <TicketsTable />
    <FlightsTable />
  </div>
</template>

<script>

import TicketsTable from '../components/TicketsTable'
import FlightsTable from '../components/FlightsTable'
import { mapActions } from 'vuex' 

export default {
  name: 'Home',
  components: {
    TicketsTable,
    FlightsTable
  },
  methods: {
    ...mapActions(['GET_FLIGHTS', 'GET_TICKETS'])
  },
  created() {
    this.GET_FLIGHTS()
    this.GET_TICKETS()
  }
}
</script>
