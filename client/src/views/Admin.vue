<template>
  <v-container>
    <v-row>
      <v-col cols="6" md="4" lg="3">
        <StatusCard :title="'Flights'" :status="totalFlights" :color="'primary'" />
      </v-col>
      <v-col cols="6" md="4" lg="3">
        <StatusCard :title="'Tickets'" :status="totalTickets" :color="'purple'" />
      </v-col>
      <v-col cols="6" md="4" lg="3">
        <StatusCard :title="'Companies'" :status="totalCompanies" :color="'orange'" />
      </v-col>
      <v-col cols="6" md="4" lg="3">
        <StatusCard :title="'Users'" :status="totalUsers" :color="'pink'" />
      </v-col>
    </v-row>
    <v-divider class="mt-3 mb-3"></v-divider>
    <v-row>
      <v-col cols="12">
        <FlightsDataTable />
      </v-col>
      <v-col cols="12">
        <TicketsDataTable />
      </v-col>
      <v-col cols="12">
        <UsersDataTable />
      </v-col>
      <v-col cols="12">
        <CompaniesDataTable />
      </v-col>
    </v-row>
  </v-container>
</template>

<script>
import StatusCard from "../components/custom/StatusCard";
import TicketsDataTable from "../components/tickets/TicketsDataTable";
import FlightsDataTable from "../components/flights/FlightsDataTable";
import UsersDataTable from "../components/users/UsersDataTable";
import CompaniesDataTable from "../components/companies/CompanyDataTable";

import { mapActions, mapGetters } from "vuex";

export default {
  name: "Admin",
  components: {
    StatusCard,
    TicketsDataTable,
    FlightsDataTable,
    UsersDataTable,
    CompaniesDataTable,
  },
  computed: {
    ...mapGetters([
      "getFlights",
      "getTickets",
      "getCompanies",
      "getUsers",
      "getUser",
      "getToken",
    ]),
    isAdmin: function () {
      const user = JSON.parse(this.getUser);
      return user.type == "ADMIN";
    },
    totalFlights: function () {
      return this.getFlights.length;
    },
    totalTickets: function () {
      return this.getTickets.length;
    },
    totalCompanies: function () {
      return this.getCompanies.length;
    },
    totalUsers: function () {
      return this.getUsers.length;
    },
  },
  methods: {
    ...mapActions(["GET_FLIGHTS", "GET_TICKETS", "GET_COMPANIES", "GET_USERS"]),
    authorize: function () {
      if (this.getToken == null) {
        this.$router.push("/login");
      } else if (!this.isAdmin) {
        this.$router.push("/");
      }
    },
  },
  created() {
    this.authorize();
    this.GET_FLIGHTS();
    this.GET_TICKETS();
    this.GET_COMPANIES();
    this.GET_USERS();
  },
};
</script>