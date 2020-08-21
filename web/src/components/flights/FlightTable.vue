<template>
    <div class="row" id="flight-table">
        <div v-for="flight in flights" :key="flight"  class="col-12 col-md-6 col-lg-4">
            <TableItem :flight="flight"/>
        </div>
    </div>
</template>

<script>

import TableItem from '../items/Flight'

export default {
    name: "FlightTable",
    components: {
        TableItem
    },
    props: ['flights']
}
</script>
