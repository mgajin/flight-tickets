<template>
  <v-card>
    <v-form>
      <v-container>
        <v-row>
          <v-col cols="12" class="d-flex justify-center">
            <v-card-title v-text="title" class="headline"></v-card-title>
          </v-col>
          <v-col cols="12" sm="6">
            <v-select
              v-model="company"
              :items="companies"
              item-value="id"
              item-text="name"
              :rules="[v => !!v || 'Company is required']"
              label="Company"
              required
              prepend-icon="mdi-domain"
            ></v-select>
          </v-col>
          <v-col cols="12" sm="6">
            <v-select
              v-model="flight"
              :items="flights"
              item-value="id"
              :item-text="item => `${item.origin.name} - ${item.destination.name}`"
              :rules="[v => !!v || 'Flight is required']"
              label="Flight"
              required
              prepend-icon="mdi-airplane"
            ></v-select>
          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
              v-model="departMenu"
              :close-on-content-click="false"
              :nudge-right="30"
              lazy
              transition="scale-transition"
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  label="Depart date"
                  prepend-icon="mdi-calendar-range"
                  readonly
                  :value="departDate"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                locale="en-in"
                v-model="departDate"
                no-title
                @input="departMenu = false"
              ></v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12" sm="6">
            <v-menu
              v-model="returnMenu"
              :close-on-content-click="false"
              :nudge-right="30"
              lazy
              transition="scale-transition"
              max-width="290px"
              min-width="290px"
            >
              <template v-slot:activator="{ on }">
                <v-text-field
                  label="Return date"
                  prepend-icon="mdi-calendar-range"
                  readonly
                  :value="returnDate"
                  v-on="on"
                ></v-text-field>
              </template>
              <v-date-picker
                v-model="returnDate"
                :min="departDate"
                no-title
                @input="returnMenu = false"
              ></v-date-picker>
            </v-menu>
          </v-col>
          <v-col cols="12">
            <v-card-actions class="d-flex align-center justify-center">
              <v-btn dark @click="validateForm" v-text="'submit'"></v-btn>
              <v-btn @click="close" v-text="'cancel'"></v-btn>
            </v-card-actions>
          </v-col>
        </v-row>
      </v-container>
    </v-form>
  </v-card>
</template>

<script>
import { mapGetters } from "vuex";

export default {
  name: "NewTicketForm",
  data: () => ({
    departMenu: false,
    returnMenu: false,
    id: null,
  }),
  computed: {
    ...mapGetters({
      flights: "getFlights",
      companies: "getCompanies",
      ticket: "getDefaultTicket",
    }),
    title: function () {
      return this.ticket ? "Edit Ticket" : "Create New Ticket";
    },
  },
  watch: {
    ticket: function (val) {
      this.setValues(val);
    },
  },
  methods: {
    setValues: function (ticket) {
      if (ticket) {
        this.id = ticket.id;
        this.flight = ticket.flight.id;
        this.company = ticket.company.id;
      } else {
        this.id = null;
        this.flight = null;
        this.company = null;
        this.departDate = null;
        this.returnDate = null;
      }
    },
    validateForm: function () {
      alert;
      if (
        this.flight == null ||
        this.company == null ||
        this.departDate == null
      ) {
        const message = "Form is not valid!";
        alert(message);
      } else {
        this.newTicket();
      }
    },
    newTicket: function () {
      const oneWay = this.returnDate == null ? true : false;
      const returnDate = this.returnDate == null ? "" : this.returnDate;
      const ticket = {
        id: this.id,
        company: this.company,
        flightId: this.flight,
        departDate: this.departDate,
        returnDate: returnDate,
        oneWay: oneWay,
        count: 5,
      };
      this.formAction(ticket);
    },
    formAction: function (ticket) {
      if (this.ticket) {
        this.editTicket(ticket);
      } else {
        this.createTicket(ticket);
      }
      this.close();
    },
    createTicket: function (ticket) {
      const payload = JSON.stringify(ticket);
      this.$store.dispatch("CREATE_TICKET", payload);
    },
    editTicket: function (ticket) {
      const payload = JSON.stringify(ticket);
      this.$store.dispatch("UPDATE_TICKET", payload);
    },
    close: function () {
      this.$store.commit("hide_ticket_dialog");
      this.$store.commit("set_default_ticket", null);
    },
  },
  created() {
    this.setValues(this.ticket);
  },
};
</script>