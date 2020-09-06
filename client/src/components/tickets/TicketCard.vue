<template>
  <v-card id="ticket-card">
    <v-card-title class="d-flex justify-space-between">
      <h4 @click="companyPage" class="primary--text">
        <v-icon color="primary" style="transform: rotate(45deg);">mdi-airplane</v-icon>
        {{ ticket.company.name }}
      </h4>
      <v-btn text color="primary" @click="action">
        <v-icon>mdi-ticket</v-icon>
      </v-btn>
    </v-card-title>
    <v-card-text class="d-flex flex-column">
      <div class="d-flex justify-space-between pb-1">
        <p>
          From
          <br />
          <span class="text-uppercase">{{ flight.origin.name }}</span>
        </p>
        <p>
          Depart
          <br />
          <span class="text-uppercase">{{ ticket.departDate }}</span>
        </p>
      </div>
      <div class="d-flex justify-space-between pt-1">
        <p>
          To
          <br />
          <span class="text-uppercase">{{ flight.destination.name }}</span>
        </p>
        <p>
          Return
          <br />
          <span class="text-uppercase">{{ this.returnDate }}</span>
        </p>
      </div>
    </v-card-text>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "TicketCard",
  props: ["ticket", "flight"],
  computed: {
    ...mapGetters(["getToken", "getUser"]),
    returnDate: function () {
      return this.ticket.oneWay ? "one-way" : this.ticket.returnDate;
    },
  },
  methods: {
    action: function () {
      this.$emit("action");
    },
    companyPage: function () {
      const { id } = this.ticket.company;
      this.$router.push(`/companies/${id}`);
    },
  },
};
</script>

<style lang="scss">
#ticket-card {
  p {
    margin: 0;
  }
}
</style>