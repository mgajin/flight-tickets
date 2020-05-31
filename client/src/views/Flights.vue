<template>
    <div class="container">
        <div class="row border">
            <div class="col-3">
                <FilterForm />
            </div>
            <div class="col-9">
                <FlightTickets />
            </div>
        </div>
    </div>
</template>

<script>

import FilterForm from '../components/forms/FilterForm'
import FlightTickets from '../components/flights/FlightTickets'

export default {
    name: 'Flights',
    components: {
        FilterForm,
        FlightTickets
    }
}
</script>
