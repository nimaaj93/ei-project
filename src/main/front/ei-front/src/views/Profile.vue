<template>
    <div class="row">
        <div class="col-md-8">
            <lazy :loaded="loaded">
                <div class="card">
                <div class="card-header">
                    <strong>{{'menu.profile' | message}} </strong>
                </div>
                <div class="card-block">

                    <div class="row">
                        <div class="col-md-8 col-xs-12">

                            <div class="row">
                                <span class="col-xs-6 col-md-4 col-lg-3">
                                  <strong>{{'common.email' | message}}</strong>
                                </span>
                                <span class="col-xs-6 col-md-4 col-lg-3">
                                  {{ profile.email }}
                                </span>
                            </div>

                            <div class="row">
                                <span class="col-xs-6 col-md-4 col-lg-3">
                                  <strong>{{'user.type' | message}}</strong>
                                </span>
                                <span class="col-xs-6 col-md-4 col-lg-3">
                                  {{ profile.userType }}
                                </span>
                            </div>

                            <hr/>

                            <div class="row">
                                <div class="col-xs-12">
                                    <div class="form-group" :class="{'has-danger' : errors.has('fullname')}">
                                        <label for="fullname">{{'common.fullname' | message}}</label>
                                        <input type="text" class="form-control"
                                               name="fullname"
                                               maxlength="40"
                                               :class="{'form-control-danger': errors.has('fullname')}"
                                               id="fullname" :placeholder="'common.required' | message"
                                               v-validate="'required'"
                                               v-model="profile.fullname"/>
                                    </div>
                                </div>
                            </div>

                            <div class="row">
                                <button type="button" class="btn btn-primary p-x-2" @click="updateProfile">
                                    {{'common.submit' | message}}
                                </button>
                            </div>

                        </div>
                    </div>

                </div>
            </div>

                <div class="card">
                    <div class="card-header">
                        <strong>{{'reset.password' | message}} </strong>
                    </div>
                    <div class="card-block">

                        <div class="row">
                            <div class="col-md-8 col-xs-12">

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group" :class="{'has-danger' : errors.has('password')}">
                                            <label for="password">{{'current.password' | message}}</label>
                                            <input type="password" class="form-control"
                                                   name="password"
                                                   maxlength="40"
                                                   :class="{'form-control-danger': errors.has('password')}"
                                                   id="password" :placeholder="'common.required' | message"
                                                   v-validate="'required'"
                                                   v-model="passwordUpdate.currentPassword"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-xs-12">
                                        <div class="form-group" :class="{'has-danger' : errors.has('newPassword')}">
                                            <label for="newPassword">{{'new.password' | message}}</label>
                                            <input type="password" class="form-control"
                                                   name="newPassword"
                                                   maxlength="40"
                                                   :class="{'form-control-danger': errors.has('newPassword')}"
                                                   id="newPassword" :placeholder="'common.required' | message"
                                                   v-validate="'required'"
                                                   v-model="passwordUpdate.newPassword"/>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <button type="button" class="btn btn-primary p-x-2" @click="updatePassword">
                                        {{'common.submit' | message}}
                                    </button>
                                </div>

                            </div>

                        </div>

                    </div>
                </div>

            </lazy>
        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import Lazy from '@/components/util/Lazy'

    export default {
        name: 'Profile',
        components: {
            Lazy
        },
        data() {
          return {
              profile: {},
              passwordUpdate: {},
              loaded: true,
          }
        },
        created() {
          this.loadProfile();
        },
        methods: {
            loadProfile() {
                this.loaded = false;
                axios.get("/api/v1/user/profile")
                    .then(response => {
                        this.profile = response.data;
                        this.loaded = true;
                    }).catch(e => {
                        this.loaded = true;
                    })
            },
            updateProfile() {

                if (this.profile.fullname && this.profile.fullname.length > 0) {
                    axios.put("/api/v1/user", {
                        name: this.profile.fullname
                    }).then(response => {
                        this.$notify({
                            group: 'success',
                            title: this.$options.filters.message('common.success.header'),
                            text: this.$options.filters.message('profile.update.success')
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
            },
            updatePassword() {

                if (this.passwordUpdate.currentPassword &&
                    this.passwordUpdate.newPassword &&
                    this.passwordUpdate.currentPassword.length > 0 &&
                    this.passwordUpdate.newPassword.length > 0) {

                    axios.put("/api/v1/user/password-update",
                        this.passwordUpdate).then(response => {
                        this.$notify({
                            group: 'success',
                            title: this.$options.filters.message('common.success.header'),
                            text: this.$options.filters.message('password.update.success')
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

            }


        }
    }
</script>