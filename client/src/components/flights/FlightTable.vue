<template>
    <div class="row" id="flight-table">
        <div v-for="flight in flights" :key="flight" v-bind="flight"  class="col-6 col-md-4">
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
