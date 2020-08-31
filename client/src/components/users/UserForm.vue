<template>
    <v-card>
        <v-form>
            <v-container>
                <v-card-title>Edit User</v-card-title>
                <v-card-subtitle class="headline" v-text="`${user.username} - ${user.type}`"></v-card-subtitle>
                <v-card-text>
                    <v-select
                        v-model="type"
                        :items="userTypes"
                        :rules="[v => !!v || 'User type is required']"
                        label="User type"
                        required
                    ></v-select>
                </v-card-text>
                <v-card-actions>
                    <v-btn dark v-text="'submit'" @click="validate"></v-btn>
                    <v-btn v-text="'cancel'" @click="close"></v-btn>
                </v-card-actions>
            </v-container>
        </v-form>
    </v-card>
</template>

<script>
export default {
    props: ['user'],
    data: () => ({
        userTypes: ['ADMIN', 'USER' ]
    }),
    methods: {
        validate: function () {
            if (this.type == null) {
                alert('Select User Type')
            } else if (this.user.type == this.type) {
                alert(`${this.user.username} is already ${this.type}`)
            } else {
                this.user.type = this.type
                this.updateUser()
            }
        },
        updateUser: function () {
            this.$store.dispatch('UPDATE_USER', this.user)
            this.close()
        },
        close: function () {
            this.type = null
            this.$store.commit('hide_user_dialog')
        }
    }
}
</script>