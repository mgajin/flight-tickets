<template>
<div id="booking-collapse">
    <button class="btn collapse-btn" type="button" data-toggle="collapse" data-target="#collapseExample" aria-expanded="false" aria-controls="collapseExample"><i class="fas fa-chevron-down"></i> Search Flight</button>
    <div class="collapse" id="collapseExample">
        <div class="form-row">
            <div class="col-6 form-group">
                <label for="from">From:</label>
                <input type="text" id="to" class="form-control">
            </div>
            <div class="col-6 form-group">
                <label for="from">To</label>
                <input type="text" id="to" class="form-control">
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
                <button class="btn">search</button>
            </div>
        </div>
    </div>
</div>
</template>

<script>
export default {
    name: 'BookingCollapse'
}
</script>
