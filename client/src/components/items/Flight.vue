<template>
    <div class="table-item">
        <div>
            <p class="color-light">{{flight.type}}</p>
            <h5 class="color-main">{{flight.to}}</h5>
            <p class="color-main">From {{flight.from}}</p>
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
    props: ['flight']

}
</script>
