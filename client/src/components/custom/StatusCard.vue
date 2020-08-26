<template>
  <v-card :color="color" dark>
    <v-card-title v-text="title" class="headline"></v-card-title>
    <v-card-subtitle v-text="`Total: ${status}`"></v-card-subtitle>
  </v-card>
</template>

<script>
export default {
  props: ['title', 'status', 'color']
}
</script>