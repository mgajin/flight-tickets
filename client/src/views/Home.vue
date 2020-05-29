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
      <FlightTable :flights="flights"/>
      <button class="btn-submit">see all offers</button>
    </section>

  </div>
</template>

<script>

import BookingForm from '../components/forms/BookingForm'; 
import FlightTable from '../components/flights/FlightTable';

export default {
  name: 'Home',
  components: {
    BookingForm,
    FlightTable
  },
  data() {
    return {
      flights: []
    }
  },
  methods: {
    getFlights() {

      let list = Array();
      for (let i=0; i<12; i++) {

        let flight = {
          type: 'One Way',
          to: 'New York',
          from: 'Belgrade'
        }

        list[i] = flight
      }

      return list;
    }
  },
  created() {
    this.flights = this.getFlights();
  }
}
</script>
