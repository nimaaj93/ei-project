<template>
  <div class="container" style="padding-top: 100px;">
    <div class="row">
      <div class="col-lg-8 col-md-10 m-x-auto pull-xs-none">
        <div class="card-group">
          <div class="card p-a-2 col-lg-6 col-xs-12 login-card">
            <div class="card-block text-xs-center">
              <h1>{{'login.top.title' | message}}</h1>
              <p class="text-muted">{{'login.top.text' | message}}</p>
              <div class="input-group m-b-1"
                   :class="{'has-danger' : errors.has('username')}">
                                <span class="input-group-addon"><i class="icon-user"></i>
                                </span>
                <input type="text" autocomplete="off"
                       id="username" name="username"
                       v-model="credentials.username"
                       v-validate="'required'"
                       :class="{'form-control-danger': errors.has('username')}"
                       maxlength="20"
                       class="form-control en ltr"
                       @keyup.enter="login"
                       :placeholder="'common.username' | message">
              </div>
              <div class="input-group m-b-2"
                   :class="{'has-danger' : errors.has('password')}">
                                <span class="input-group-addon"><i class="icon-lock"></i>
                                </span>
                <input type="password" autocomplete="off"
                       id="password" name="password"
                       v-model="credentials.password"
                       v-validate="'required'"
                       :class="{'form-control-danger': errors.has('password')}"
                       @keyup.enter="login"
                       maxlength="30"
                       class="form-control en ltr"
                       :placeholder="'common.password' | message">
              </div>
              <div class="row">
                <div class="col-xs-6 m-x-auto pull-xs-none">
                  <button type="button" class="btn btn-primary p-x-2" @click="login">
                    <i class="icon-login"></i>
                    {{'common.login' | message}}
                  </button>
                </div>
              </div>
            </div>
          </div>
          <div class="card card-inverse card-primary p-y-3 col-lg-6 col-xs-12 login-card logo-box">
            <div class="card-block text-xs-center">
              <div>
                <!--<img src="@/assets/img/"-->
                     <!--alt="logo" class="fill-keep-aspect"/>-->
              </div>
              <div>
                {{'login.sublogo.text' | message}}
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
                credentials: {}
            }
        },
        methods: {
            login() {
                this.$validator.validateAll().then((result) => {
                    if (result) {

                        axios.post('/api/v1/user/authenticate', {
                            grant_type: 'password',
                            username: this.credentials.username,
                            password: this.credentials.password
                        })
                            .then((response) => {
                                this.$store.dispatch('authenticate', response.data)
                                this.$router.push({path: '/home'})
                            })
                            .catch((e) => {
                                if (e.status == 400) {
                                    this.$notify({
                                        group: 'warning',
                                        title: this.$options.filters.message('common.error.header'),
                                        text: this.$options.filters.message('authentication.failed')
                                    });
                                } else if (e.data.error_description) {
                                    this.$notify({
                                        group: 'warning',
                                        title: this.$options.filters.message('common.error.header'),
                                        text: this.$options.filters.message(e.data.error_description)
                                    });
                                }
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