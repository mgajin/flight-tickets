<template>
  <v-container>
    <v-row>
      <v-col cols="6" md="4">
        <v-select solo v-model="oneWay" :items="filterList" label="Ticket type"></v-select>
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12" sm="6" lg="4" v-for="ticket in getTickets" :key="ticket.id">
        <TicketCard :ticket="ticket" :flight="ticket.flight" @action="reserve" />
      </v-col>
    </v-row>
    <v-row>
      <v-col cols="12">
        <v-pagination v-model="page" :length="totalPages"></v-pagination>
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import TicketCard from "./TicketCard";
import { mapGetters, mapActions } from "vuex";

export default {
  name: "TicketsTable",
  data: () => ({
    page: 1,
    oneWay: null,
    filterList: [
      { text: "One way", value: true },
      { text: "Round trip", value: false },
      { text: "All", value: null },
    ],
  }),
  components: {
    TicketCard,
  },
  computed: {
    ...mapGetters([
      "getTickets",
      "getFlights",
      "getPageInfo",
      "getUser",
      "getToken",
    ]),
    totalPages: function () {
      return this.getPageInfo.totalPages;
    },
    pageQuery: function () {
      return `?page=${this.page}`;
    },
    filterQuery: function () {
      return this.oneWay != null ? `&oneWay=${this.oneWay}` : "";
    },
    query: function () {
      return `${this.pageQuery}${this.filterQuery}`;
    },
  },
  watch: {
    page: function () {
      this.loadTickets(this.query);
    },
    query: function (value) {
      this.loadTickets(value);
    },
  },
  methods: {
    ...mapActions({
      loadTickets: "GET_TICKETS",
      addReservation: "ADD_RESERVATION",
    }),
    reserve: function () {
      if (this.getToken == null || this.getUser == null) {
        alert("Log in first");
      } else {
        const user = JSON.parse(this.getUser);
        const payload = {
          user: user.id,
          ticket: this.ticket.id,
          flight: this.flight.id,
        };
        this.addReservation(payload);
      }
    },
  },
  created() {
    this.loadTickets(this.pageQuery);
  },
};
</script>