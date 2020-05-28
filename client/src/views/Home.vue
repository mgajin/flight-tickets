<template>
  <div class="home">
    <div id="landing">
      <div class="img-holder"></div>
      <div class="overlay">
          <BookingForm />
      </div>
    </div>
  </div>
</template>

<script>

import BookingForm from '../components/forms/BookingForm'; 

export default {
  name: 'Home',
  components: {
    BookingForm
  }
  
}
</script>
