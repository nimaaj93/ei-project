<template>
    <div class="row">
        <div class="col-md-8">
                <div class="card">
                    <div class="card-header">
                        <strong>{{'menu.create.ticket' | message}} </strong>
                    </div>
                    <div class="card-block">

                        <div class="row">
                            <div class="col-md-8 col-xs-12">

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group" :class="{'has-danger' : errors.has('title')}">
                                            <label for="title">{{'common.title' | message}}</label>
                                            <input type="text" class="form-control"
                                                   name="title"
                                                   maxlength="40"
                                                   :class="{'form-control-danger': errors.has('title')}"
                                                   id="title" :placeholder="'common.required' | message"
                                                   v-validate="'required'"
                                                   v-model="ticket.ticketTitle"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group" :class="{'has-danger' : errors.has('ticketBody')}">
                                            <label for="ticketBody">{{'ticket.body' | message}}</label>
                                            <textarea class="form-control"
                                                   name="ticketBody"
                                                   rows="6"
                                                   maxlength="1000"
                                                   :class="{'form-control-danger': errors.has('ticketBody')}"
                                                   id="ticketBody" :placeholder="'common.required' | message"
                                                   v-validate="'required'"
                                                   v-model="ticket.ticketBody">
                                            </textarea>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group" :class="{'has-danger' : errors.has('assigneeUser')}">
                                            <label for="assigneeUser">{{'assignee.user' | message}}</label>
                                            <select v-model="ticket.assigneeUserId" name="assigneeUser" id="assigneeUser"
                                                    class="form-control"
                                                    :class="{'form-control-danger': errors.has('assigneeUser')}"
                                                    v-validate="'required'">
                                                <option disabled value="">انتخاب کنید</option>
                                                <option :value="user.id"
                                                        :key="user.id"
                                                        v-for="user in users">{{ user.fullname }} - {{ user.userType }}</option>
                                            </select>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <button type="button" class="btn btn-primary p-x-2" @click="submitTicket">
                                        {{'common.submit' | message}}
                                    </button>
                                </div>

                            </div>
                        </div>

                    </div>
                </div>

        </div>
    </div>
</template>
<script>
    import axios from 'axios'

    export default {
        name: 'CreateTicket',
        data() {
            return {
                ticket: {
                    assigneeUserId: ""
                },
                users: [],
            }
        },
        created() {
            this.loadUsers();
        },
        methods: {
            loadUsers() {
                axios.get("/api/v1/user")
                    .then(response => {
                        this.users = response.data
                    }).catch(e => {})
            },
            submitTicket() {
                this.$validator.validateAll().then((result) => {
                    if (result) {
                        axios.post("/api/v1/ticket",this.ticket)
                            .then(response => {
                                this.$notify({
                                    group: 'success',
                                    title: this.$options.filters.message('common.success.header'),
                                    text: this.$options.filters.message('ticket.submit.success')
                                });
                                this.$router.push({path: '/home'})
                            }).catch(e => {
                            })

                    } else {
                        this.$notify({
                            group: 'warning',
                            title: this.$options.filters.message('common.submit.error'),
                            text: this.$options.filters.message('common.validation.failed')
                        });
                    }
                })
            },

        }
    }
</script>