<template>
        <div class="booking-card container">
            <h5>Book your flight!</h5>
            <form>
                <div class="form-row">
                    <div class="col-6 col-lg form-group">
                        <label for="from">From</label>
                        <input type="text" id="from" class="form-control" placeholder="From">
                    </div>
                    <div class="col-6 col-lg form-group">
                        <label for="to">To</label>
                        <input type="text" id="to" class="form-control" placeholder="To">
                    </div>
                    <div class="col-6 col-lg form-group">
                        <label for="departureDate">Departure date</label>
                        <input type="date" id="departureDate" class="form-control" placeholder="Departure date">
                    </div>
                    <div class="col-6 col-lg form-group">
                        <label for="returnDate">Return date</label>
                        <input type="date"  class="form-control" placeholder="Return date">
                    </div>
                    <div class="col-12 form-group">
                        <button class="btn">search</button>
                    </div>
                </div>
            </form>
        </div>
</template>

<script>
export default {
    name: "BookingForm"
}
</script>
