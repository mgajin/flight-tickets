<template>
  <v-card>
    <v-card-title class="primary--text">
      <h4>{{ flight.destination.name }}</h4>
    </v-card-title>
    <v-card-subtitle v-text="flight.origin.name"></v-card-subtitle>
    <v-card-actions>
      <v-btn block color=primary><v-icon class="mr-2">mdi-ticket</v-icon> book flight</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
    name: 'FlightCard',
    props: ['flight']
}
</script>

<style>

</style>