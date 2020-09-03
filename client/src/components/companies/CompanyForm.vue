<template>
    <v-card>
        <v-form>
            <v-container>
                <v-row>
                    <v-col cols=12 class="d-flex justify-center">
                        <v-card-title v-text="'Create New Company'" class="headline"></v-card-title>
                    </v-col>    
                    <v-col cols=12>
                        <v-text-field
                            v-model="company"
                            label="Company name"
                            prepend-icon="mdi-domain"
                    ></v-text-field>
                    </v-col>
                    <v-col cols=12>
                        <v-card-actions class="d-flex justify-center">
                            <v-btn dark @click="validateForm" v-text="'submit'"></v-btn>
                            <v-btn @click="close" v-text="'cancel'"></v-btn>
                        </v-card-actions>
                    </v-col>
                </v-row>
            </v-container>
        </v-form>
    </v-card>
</template>

<script>

export default {
    name: 'CompanyForm',
    methods: {
        validateForm: function () {
            if (this.company == null) {
                alert('Form not valid')
            } else {
                this.createCompany(this.company)
            }
        },
        createCompany: function (company) {
            const payload = { name: company, token: '' }
            this.$store.dispatch('ADD_COMPANY', payload)
            this.close()
        },
        close: function () {
            this.$store.commit('hide_company_dialog')
        }
    }
}
</script>