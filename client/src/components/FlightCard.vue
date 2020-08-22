<template>
  <v-card>
      <v-card-title v-text="flight.destination.name"></v-card-title>
      <v-card-subtitle v-text="flight.origin.name"></v-card-subtitle>
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