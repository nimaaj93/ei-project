<template>
    <div class="container" style="padding-top: 100px;">
        <div class="row">
            <div class="col-lg-4 col-md-6 m-x-auto pull-xs-none">
                <div class="card-group">
                    <div class="card p-a-2 login-card">
                        <div class="card-block text-xs-center">
                            <h1>{{'sign.up.title' | message}}</h1>
                            <hr/>
                            <div class="input-group m-b-1"
                                 :class="{'has-danger' : errors.has('fullname')}">

                                <input type="text" autocomplete="off"
                                       id="fullname" name="fullname"
                                       v-model="user.fullname"
                                       v-validate="'required'"
                                       :class="{'form-control-danger': errors.has('fullname')}"
                                       maxlength="40"
                                       class="form-control"
                                       :placeholder="'common.fullname' | message">
                            </div>
                            <div class="input-group m-b-1"
                                 :class="{'has-danger' : errors.has('userType')}">
                                <select v-model="user.userType" name="userType" id="userType"
                                        class="form-control"
                                        :class="{'form-control-danger': errors.has('userType')}"
                                        v-validate="'required'">
                                    <option disabled value="">{{'user.type' | message}}</option>
                                    <option value="ADMIN">ADMIN</option>
                                    <option value="STUDENT">STUDENT</option>
                                    <option value="EMPLOYEE">EMPLOYEE</option>
                                    <option value="PROF">PROF</option>
                                </select>
                            </div>
                            <div class="input-group m-b-1"
                                 :class="{'has-danger' : errors.has('username')}">

                                <input type="text" autocomplete="off"
                                       id="username" name="username"
                                       v-model="user.username"
                                       v-validate="'required'"
                                       :class="{'form-control-danger': errors.has('username')}"
                                       maxlength="20"
                                       class="form-control"
                                       :placeholder="'common.username' | message">
                            </div>
                            <div class="input-group m-b-2"
                                 :class="{'has-danger' : errors.has('password')}">
                                <input type="password" autocomplete="off"
                                       id="password" name="password"
                                       v-model="user.password"
                                       v-validate="'required'"
                                       :class="{'form-control-danger': errors.has('password')}"
                                       maxlength="30"
                                       class="form-control"
                                       :placeholder="'common.password' | message">
                            </div>
                            <div class="row">
                                <div class="col-xs-6 m-x-auto pull-xs-none">
                                    <button type="button" class="btn btn-success p-x-2" @click="doSignup">
                                        <i class="icon-login"></i>
                                        {{'common.register' | message}}
                                    </button>
                                </div>
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
        name: 'Login',
        data() {
            return {
                user: {
                    userType: "",
                }
            }
        },
        methods: {
            doSignup() {
                this.$validator.validateAll().then((result) => {
                    if (result) {

                        axios.post('/api/v1/user', this.user)
                            .then(() => {
                                this.$notify({
                                    group: 'success',
                                    title: this.$options.filters.message('common.success.header'),
                                    text: this.$options.filters.message('user.create.success')
                                });
                                this.$router.push({path: '/login'})
                            })
                            .catch(() => {
                            })
                    } else {
                        this.$notify({
                            group: 'warning',
                            title: this.$options.filters.message('common.submit.error'),
                            text: this.$options.filters.message('common.validation.failed')
                        });
                    }
                })

            }
        }
    }
</script>