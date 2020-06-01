<template>
    <form>
        <div class="form-row border">
            <div class="col-6 form-group">
                <label for="from">From</label>
                <input type="text" value="Belgrade" id="to" class="form-control">
            </div>
            <div class="col-6 form-group">
                <label for="to">To</label>
                <select class="custom-select">
                    <option>Miami</option>
                    <option>New York</option>
                    <option>Los Angeles</option>
                    <option>London</option>
                    <option>Boston</option>
                </select>
            </div>
            <div class="col-12 form-group" id="radio-group">
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" checked="true" id="radioOneWay">
                    <label class="form-check-label" for="radioOneWay">All</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="radioOneWay">
                    <label class="form-check-label" for="radioOneWay">One Way</label>
                </div>
                <div class="form-check">
                    <input class="form-check-input" type="radio" name="inlineRadioOptions" id="radioRound">
                    <label class="form-check-label" for="radioRound">Round trip</label>
                </div>
            </div>
            <div class="col-6 form-group">
                <label for="departureDate">Departure date</label>
                <input type="date" id="departureDate" class="form-control">
            </div>
            <div class="col-6 form-group">
                <label for="returnDate">Return date</label>
                <input type="date"  class="form-control">
            </div>
            <div class="col-12">
                <button class="btn">submit</button>
            </div>
        </div>
    </form>
</template>

<script>
export default {
    name: 'FilterForm'

}
</script>
