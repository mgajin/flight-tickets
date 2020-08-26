<template>
  <v-card :color="data.color" dark>
    <v-card-title v-text="data.title" class="headline"></v-card-title>
    <v-card-subtitle v-text="`Total: ${data.status}`"></v-card-subtitle>
    <v-card-actions>
    <v-btn text block @click="btnAction"><v-icon>mdi-plus</v-icon> add new</v-btn>
    </v-card-actions>
  </v-card>
</template>

<script>
export default {
    props: ['data'],
    methods: {
        btnAction: function () {
            this.$emit('action')
        }
    }
}
</script>