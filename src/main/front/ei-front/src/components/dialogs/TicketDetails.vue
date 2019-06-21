<template>
    <div id="taskDetails" class="modal fade col-xs-12 col-md-10 m-x-auto pull-xs-none" role="dialog"
         style="top: 3%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">{{'ticket.details' | message}}</h4>
            </div>

            <div class="modal-body">
                <lazy :loaded="loaded">

                    <div class="row">
                      <span class="col-xs-6 col-md-4 col-lg-3">
                        <strong>{{'common.title' | message }}</strong>
                      </span>
                      <span class="col-xs-6 col-md-4 col-lg-3">
                        {{ ticket.ticketTitle }}
                      </span>
                    </div>

                    <div class="row">
                      <span class="col-xs-6 col-md-4 col-lg-3">
                        <strong>{{'ticket.creator' | message }}</strong>
                      </span>
                        <span class="col-xs-6 col-md-4 col-lg-3">
                        {{ticket.creatorName}} - {{ticket.creatorUserType}}
                      </span>
                    </div>
                    <div class="row">
                      <span class="col-xs-6 col-md-4 col-lg-3">
                        <strong>{{'ticket.assignee' | message }}</strong>
                      </span>
                        <span class="col-xs-6 col-md-4 col-lg-3">
                        {{ticket.assigneeName}} - {{ticket.assigneeUserType}}
                      </span>
                    </div>
                    <div class="row">
                      <span class="col-xs-6 col-md-4 col-lg-3">
                        <strong>{{'common.status' | message }}</strong>
                      </span>
                        <span class="col-xs-6 col-md-4 col-lg-3">
                        {{ticket.ticketStatus}}
                      </span>
                    </div>

                    <hr/>

                    <ul class="list-group">
                        <li v-for="(comment,index) in ticket.comments" class="list-group-item">
                            <strong>{{ comment.creatorName }} - {{ comment.creatorUserType }}</strong>
                            <br/>
                            <span>
                                {{ comment.commentBody }}
                            </span>
                        </li>
                    </ul>

                    <hr/>

                    <textarea class="form-control"
                              name="comment"
                              rows="6"
                              maxlength="1000"
                              :class="{'form-control-danger': errors.has('comment')}"
                              id="comment" :placeholder="'common.required' | message"
                              v-validate="'required'"
                              v-model="comment.commentBody">
                    </textarea>

                    <div class="modal-footer">
                        <button class="btn btn-success" @click="submit">{{'common.submit' | message}}</button>
                    </div>

                </lazy>
            </div>
        </div>
    </div>
</template>

<script>
    import axios from 'axios'
    import Lazy from '@/components/util/Lazy'

    export default {
        name: 'TicketDetails',
        components: {
            Lazy,
        },
        data() {
          return {
              loaded: true,
              comment: {},
              ticket: {},
          }
        },
        props: {
            ticketid: {
                required: false
            }
        },
        methods: {
            load() {
                this.loaded = false;
                axios.get("/api/v1/ticket/" + this.ticketid)
                    .then(response => {
                        this.ticket = response.data;
                        this.comment.ticketId = this.ticket.id;
                        this.comment.commentBody = "";
                        this.loaded = true;
                    })
                    .catch(() => {
                        this.loaded = true;
                    })
            },
            submit() {
                this.$validator.validateAll().then((result) => {
                    if (result) {
                        axios.post('/api/v1/ticket/comment', this.comment)
                            .then(() => {
                                this.$notify({
                                    group: 'success',
                                    title: this.$options.filters.message('common.success.header'),
                                    text: this.$options.filters.message('comment.add.success')
                                });
                                this.load();
                            }).catch((e) => {
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