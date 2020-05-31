<template>
    <div class="row">
        <div class="col-lg-6">
            <Flight />
        </div>
    </div>    
</template>

<script>

import Flight from '../items/FlightTicket'

export default {
    name: 'FlightTickets',
    components: {
        Flight
    }
}
</script>

<style>

</style>