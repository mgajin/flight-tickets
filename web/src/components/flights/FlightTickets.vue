<template>
    <div class="row" id="tickets">
        <div v-for="ticket in tickets" :key="ticket" class="col-lg-6 p-0">
            <Flight :ticket="ticket" :flight="getFlight(ticket.flight)"/>
        </div>
    </div>    
</template>

<script>

import Flight from '../items/FlightTicket'

export default {
    name: 'FlightTickets',
    components: {
        Flight
    },
    props: ['tickets', 'flights'],
    methods: {
        getFlight(id) {
            let flight;

            this.flights.forEach(f => {
                if (f.id === id) {
                    flight = f
                }
            });

            return flight;
        }
    }
}
</script>

<style>

</style>