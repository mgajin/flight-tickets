<template>
  <div class="home">

    <div id="landing">
      <div class="img-holder"></div>
      <div class="overlay">
        <BookingForm />
      </div>
    </div>

    <section class="container">
      <h5 class="title">Flights</h5>
      <FlightTable :flights="getFlights"/>
      <button class="btn-submit" @click="this.seeOffers">see all offers</button>
    </section>

  </div>
</template>

<script>

import BookingForm from '../components/forms/BookingForm'; 
import FlightTable from '../components/flights/FlightTable';

import { mapActions, mapGetters } from "vuex";

export default {
  name: 'Home',
  components: {
    BookingForm,
    FlightTable
  },
  methods: {
    ...mapActions(['GET_FLIGHTS']),
    seeOffers() {
      this.$router.push('flights');
    }
  },
  computed: mapGetters(['getFlights']),
  created() {
    this.GET_FLIGHTS();
  }
}
</script>
