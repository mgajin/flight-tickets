const state = {
    flightDialog: false,
    ticketDialog: false,
    userDialog: false,
    companyDialog: false
}

const getters = {
    getFlightDialog: state => state.flightDialog,
    getTicketDialog: state => state.ticketDialog,
    getUserDialog: state => state.userDialog,
    getCompanyDialog: state => state.companyDialog
}

const mutations = {
    show_flight_dialog: state => state.flightDialog = true,
    hide_flight_dialog: state => state.flightDialog = false,
    show_ticket_dialog: state => state.ticketDialog = true,
    hide_ticket_dialog: state => state.ticketDialog = false,
    show_user_dialog: state => state.userDialog = true,
    hide_user_dialog: state => state.userDialog = false,
    show_company_dialog: state => state.companyDialog = true,
    hide_company_dialog: state => state.companyDialog = false
}

export default {
    state,
    getters,
    mutations
}