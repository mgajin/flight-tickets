<template>
  <v-app>
    <TheNavbar />
    <v-main>
    </v-main>
  </v-app>
</template>

<script>
import TheNavbar from './layouts/TheNavbar'

export default {
  name: 'App',

  components: {
    TheNavbar,
  },

  data: () => ({
    //
  }),
};
</script>
