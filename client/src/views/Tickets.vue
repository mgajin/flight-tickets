<template>
  <v-container>
      <TicketsTable />
  </v-container>
</template>

<script>

import TicketsTable from '../components/tickets/TicketsTable'
import { mapActions } from 'vuex'

export default {
    name: 'Tickets',
    components: {
        TicketsTable
    },
    methods: {
        ...mapActions(['GET_TICKETS'])
    },
    created() {
        this.GET_TICKETS()
    }
}
</script>