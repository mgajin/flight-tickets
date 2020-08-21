<template>
    <div class="container card">
        <h5>Create new Ticket</h5>
        <form class="form-row">
            <div class="col-12">
                <label for="">Company</label>
                <select v-model="company" class="custom-select">
                    <option v-for="company in getCompanies" :key="company" :value="company">{{company.name}}</option>
                </select>
            </div>
            <div class="col-12">
                <label for="">Flight</label>
                <select v-model="flight" class="custom-select">
                    <option v-for="flight in getFlights" :key="flight" :value="flight">{{flight.origin.name}} - {{flight.destination.name}}</option>
                </select>
            </div>
            <div class="col-12">
                <label for="">Type</label>
                <select v-model="type" class="custom-select">
                    <option value="one-way">One Way</option>
                    <option value="round-trip">Round Trip</option>
                </select>
            </div>
            <div class="col-6 form-group">
                <label for="from">Depart</label>
                <input type="date" id="to" class="form-control">
            </div>
            <div class="col-6 form-group">
                <label for="from">Return</label>
                <input type="date" id="to" class="form-control">
            </div>
            
            <div class="col-12">
                <button  @click="createTicket" class="btn">create</button>
            </div>
        </form>
    </div>
</template>

<script>

import {mapActions, mapGetters} from 'vuex'

export default {
    name: "TicketForm",
    methods: {
        ...mapActions(['GET_FLIGHTS', 'GET_COMPANIES', 'CREATE_TICKET']),
        createTicket() {
            let oneWay;
            if (this.type == 'one-way') {
                oneWay = true
            } else {
                oneWay = false
            }

            let payload = JSON.stringify({
                companyName: this.company.name,
                flightId: this.flight.id,
                oneWay: oneWay,
                count: 30
            })
            
            this.CREATE_TICKET(payload)
        }
    },
    computed: mapGetters(['getFlights', 'getCompanies']),
    created() {
        this.GET_FLIGHTS()
        this.GET_COMPANIES()
    }
}
</script>
