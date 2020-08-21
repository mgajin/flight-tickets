<template>
    <div class="table-item">
        <div>
            <p class="color-light">One Way</p>
            <h5 class="color-main">{{flight.destination.name}}</h5>
            <p class="color-main">From {{flight.origin.name}}</p>
        </div>
        <div>
            <p class="color-light">EUR</p>
            <h5 class="color-blue">200</h5>
        </div>
    </div>  
</template>

<script>
export default {
    name: 'Flight',
    props: {
        flight: {}
    }

}
</script>
