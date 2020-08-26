<template>
  <v-card :color="data.color" dark>
    <v-card-title v-text="data.title" class="headline"></v-card-title>
    <v-card-subtitle v-text="`Total: ${data.status}`"></v-card-subtitle>
  </v-card>
</template>

<script>
export default {
  props: ['data']
}
</script>