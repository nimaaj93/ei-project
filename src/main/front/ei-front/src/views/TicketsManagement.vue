<template>
    <div class="row">
        <div class="col-lg-12">

            <div class="card">
                <div class="card-block">

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
                                        <button class="btn btn-secondary btn-sm" @click="openDetails(ticket)">
                                            {{ 'ticket.details' | message}}
                                        </button>
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

            <ticket-details ref="ticketDetailsDlg" :ticketid="selectedTicket.id">
            </ticket-details>

        </div>
    </div>
</template>
<script>

    /* eslint-disable */

    import axios from 'axios'
    import Lazy from '@/components/util/Lazy'
    import TicketDetails from '@/components/dialogs/TicketDetails'

    export default {
        name: 'TicketsManagement',
        components: {
            Lazy,
            TicketDetails,
        },
        data() {
            return {
                searchParams: {
                    page: 0,
                    size: 15
                },
                loaded: true,
                tickets: {},
                selectedTicket: {
                },
            }
        },
        created() {
            this.loadData();
        },
        methods: {
            loadData() {
                this.loaded = false;
                axios.get("/api/v1/ticket",{
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
            },
            openDetails(ticket) {
                this.selectedTicket = ticket;
                this.$nextTick(() => {
                    this.$refs.ticketDetailsDlg.load();
                    $('#taskDetails').modal('show');
                });
            },
        }
    }
</script>