<template>
  <v-data-table :headers="headers" :items="getTickets" class="elevation-1">
    <template v-slot:top>
      <v-toolbar flat color="white">
        <v-toolbar-title>Tickets</v-toolbar-title>
        <v-divider
          class="mx-4"
          inset
          vertical
        ></v-divider>
        <v-spacer></v-spacer>
      </v-toolbar>
    </template>
    <template v-slot:item.actions="{ item }">
      <v-icon
        small
        class="mr-2"
        @click="editItem(item)"
      >
        mdi-pencil
      </v-icon>
      <v-icon
        small
        @click="deleteItem(item)"
      >
        mdi-delete
      </v-icon>
    </template>
    <template v-slot:no-data>
      <v-btn color="primary" @click="initialize">Reset</v-btn>
    </template>
  </v-data-table>
</template>

<script>

import { mapGetters } from 'vuex'

export default {
    data: () => ({
        headers: [
            { text: 'Ticket ID', value: 'id' },
            { text: 'Flight ID', value: 'flightId' },
            { text: 'Depart', value: 'departDate' },
            { text: 'Return', value: 'returnDate' },
            { text: 'One-Way', value: 'oneWay' },
            { text: 'Company', value: 'companyName' },
            { text: 'Actions', value: 'actions', sortable: false }
        ]
    }),
    computed: mapGetters(['getTickets']),
    methods: {
        
    }
}
</script>