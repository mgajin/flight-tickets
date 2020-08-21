<template>
<div id="filter-menu">
    <div class="row">
        <div class="p-0 col-12">
            <BookingForm />
        </div>
        <div class="p-0 col-12">
            <select v-model="filter" @change="filterTickets" class="custom-select" id="filter">
                <option value="all">All tickets</option>
                <option value="one-way">One Way</option>
                <option value="round-trip">Round Trip</option>
            </select>
        </div>
    </div>
</div>
</template>

<script>

import BookingForm from './BookingCollapse'

import { mapActions } from "vuex"

export default {
    name: 'FilterMenu',
    components: {
        BookingForm
    },
    methods: {
        ...mapActions(['FILTER_TICKETS', 'GET_TICKETS']),
        filterTickets() {

            if (this.filter == 'one-way') {
                this.FILTER_TICKETS('true');
            } else if (this.filter == 'round-trip') {
                this.FILTER_TICKETS('false');
            } else {
                this.GET_TICKETS();
            }
        }
    }
}   
</script>
