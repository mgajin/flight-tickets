<template>
    <div id="flight-ticket">
        <div class="flight-title">
            <h5 class="color-main">{{flight.origin.name}} - {{flight.destination.name}}</h5>
            <label class="color-light">{{ticket.company.name}}</label>
        </div>
        <div class="flight-info">
            <div>
                <label class="color-light">Departure</label>
                <p class="color-main">6.6.2020</p>
                <label class="color-light">Return</label>
                <p class="color-main">16.6.2020</p>
            </div>
            <div class="d-flex flex-column-reverse justify-content-between">
                <button class="btn">Book flight</button>
            </div>
        </div>
    </div>
</template>

<script>
export default {
    name: 'FlightTicket',
    props: ['ticket', 'flight']
}
</script>
