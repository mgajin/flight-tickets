const state = {
    flightDialog: false,
    ticketDialog: false
}

const getters = {
    getFlightDialog: state => state.flightDialog,
    getTicketDialog: state => state.ticketDialog
}

const mutations = {
    show_flight_dialog: state => state.flightDialog = true,
    hide_flight_dialog: state => state.flightDialog = false,
    show_ticket_dialog: state => state.ticketDialog = true,
    hide_ticket_dialog: state => state.ticketDialog = false
}

export default {
    state,
    getters,
    mutations
}