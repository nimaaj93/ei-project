<template>
    <div class="row">
        <div class="col-lg-12">

            <div class="card">
                <div class="card-block">

                    <div class="row">
                        <ul class="nav nav-tabs">
                            <li :class="{'active' : tabIndex === 0}"><a
                                    @click="changeTab(0)">{{'user.tickets.created' | message}}</a></li>
                            <li :class="{'active' : tabIndex === 1}"><a
                                    @click="changeTab(1)">{{'user.tickets.assigned' | message}}</a></li>
                        </ul>
                    </div>


                    <div class="row">
                        <lazy :loaded="loaded" :min-height="400">
                            <table class="table table-bordered table-striped table-condensed">
                                <thead>
                                <tr>
                                    <th class="text-xs-center">{{'common.row' | message }}</th>
                                    <th class="text-xs-center">{{'common.title' | message }}</th>
                                    <th class="text-xs-center">{{'ticket.creator' | message }}</th>
                                    <th class="text-xs-center">{{'ticket.assignee' | message }}</th>
                                    <th class="text-xs-center">{{'common.status' | message}}</th>
                                    <th class="text-xs-center">{{'common.actions' | message }}</th>
                                </tr>
                                </thead>

                                <tbody v-if="tickets.content && tickets.content.length > 0">
                                <tr v-for="(ticket,index) in tickets.content" :key="ticket.id">
                                    <td class="text-xs-center">{{((searchParams.page) * 10) + index + 1}}</td>
                                    <td class="text-xs-center">
                                        {{ticket.ticketTitle}}
                                    </td>
                                    <td>
                                        {{ticket.creatorName}} - {{ticket.creatorUserType}}
                                    </td>
                                    <td>
                                        {{ticket.assigneeName}} - {{ticket.assigneeUserType}}
                                    </td>
                                    <td class="text-xs-center">
                                        {{ticket.ticketStatus}}
                                    </td>
                                    <td class="text-xs-center">
                                    </td>
                                </tr>
                                </tbody>

                                <tbody v-else>
                                <tr>
                                    <td colspan="6">
                                        {{'common.empty.table.text' | message}}
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                            <nav>
                                <ul class="pagination">
                                    <li class="page-item" :class="{'disabled' : !tickets.content || tickets.first}"><a class="page-link"
                                                                                                           @click="previousPage">{{'common.previous' | message}}</a>
                                    </li>
                                    <li v-for="n in tickets.totalPages" :key="n" :class="{active : searchParams.page == n}">
                                        <a @click="setPage(n)">{{n}}</a>
                                    </li>
                                    <li class="page-item" :class="{'disabled' : !tickets.content || tickets.last}"><a class="page-link"
                                                                                                                      @click="nextPage">{{'common.next' | message}}</a>
                                    </li>
                                </ul>
                            </nav>

                        </lazy>
                    </div>



                </div>
            </div>

        </div>
    </div>
</template>
<script>
    import axios from 'axios'
    import Lazy from '@/components/util/Lazy'

    export default {
        name: 'UserTickets',
        components: {
            Lazy
        },
        data() {
            return {
                searchParams: {
                    page: 0,
                    size: 15
                },
                loaded: true,
                tabIndex: 0,
                tickets: {},
            }
        },
        created() {
            this.loadData();
        },
        methods: {
            loadData() {
                if (this.tabIndex === 0) {
                    this.loadCreatedTickets();
                } else {
                    this.loadAssignedTickets();
                }
            },
            loadCreatedTickets() {
              this.loaded = false;
              axios.get("/api/v1/ticket/user-created",{
                  params: {
                      page: this.searchParams.page,
                      size: this.searchParams.size,
                  }
              })
                  .then(response => {
                      this.tickets = response.data
                      this.loaded = true;
                  }).catch(() => {
                      this.loaded = true;
                  })
            },
            loadAssignedTickets() {
                this.loaded = false;
                axios.get("/api/v1/ticket/user-assigned",{
                    params: {
                        page: this.searchParams.page,
                        size: this.searchParams.size,
                    }
                })
                    .then(response => {
                        this.tickets = response.data
                        this.loaded = true;
                    }).catch(() => {
                        this.loaded = true;
                    })
            },
            changeTab(index) {
                this.tabIndex = index;
                this.searchParams.page = 0;
                this.loadData();
            },
            previousPage() {
                this.searchParams.page--;
                this.loadData();
            },
            nextPage() {
                this.searchParams.page++;
                this.loadData();
            },
            setPage(newPage) {
                this.searchParams.page = newPage - 1;
                this.loadData();
            }
        }
    }
</script>