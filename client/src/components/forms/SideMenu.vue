<template>
<div id="filter-menu">
    <div class="row">
        <div class="p-0 col-12">
            <BookingForm />
        </div>
        <div class="p-0 col-12">
            <div class="filters">
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="check1">
                    <label class="form-check-label" for="check1">Round trip</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="checkbox" value="" id="check2">
                    <label class="form-check-label" for="check2">One Way</label>
                </div>
            </div>
        </div>
    </div>
</div>
</template>

<script>

import BookingForm from './BookingCollapse'

export default {
    name: 'SideMenu',
    components: {
        BookingForm
    }
}   
</script>
