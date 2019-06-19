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
                                    <th class="text-xs-center">{{'common.fullname' | message }}</th>
                                    <th class="text-xs-center">{{'common.email' | message }}</th>
                                    <th class="text-xs-center">{{'user.type' | message }}</th>
                                    <th class="text-xs-center">{{'user.activated' | message }}</th>
                                    <th class="text-xs-center">{{'common.status' | message}}</th>
                                    <th class="text-xs-center">{{'common.actions' | message }}</th>
                                </tr>
                                </thead>

                                <tbody v-if="users.content && users.content.length > 0">
                                <tr v-for="(user,index) in users.content" :key="user.id">
                                    <td class="text-xs-center">{{((searchParams.page) * 10) + index + 1}}</td>
                                    <td class="text-xs-center">
                                        {{ user.fullname }}
                                    </td>
                                    <td class="text-xs-center">
                                        {{ user.email }}
                                    </td>
                                    <td class="text-xs-center">
                                        {{ user.userType }}
                                    </td>
                                    <td class="text-xs-center">
                                        {{ user.activated }}
                                    </td>
                                    <td class="text-xs-center">
                                        {{ user.userStatus }}
                                    </td>
                                    <td class="text-xs-center">
                                        <button type="button" class="btn btn-sm btn-success " @click="activateUser(user.id)">
                                            {{'user.activate' | message}}
                                        </button>
                                    </td>
                                </tr>
                                </tbody>

                                <tbody v-else>
                                <tr>
                                    <td colspan="7">
                                        {{'common.empty.table.text' | message}}
                                    </td>
                                </tr>
                                </tbody>
                            </table>

                            <nav>
                                <ul class="pagination">
                                    <li class="page-item" :class="{'disabled' : !users.content || users.first}"><a class="page-link"
                                                                                                                       @click="previousPage">{{'common.previous' | message}}</a>
                                    </li>
                                    <li v-for="n in users.totalPages" :key="n" :class="{active : searchParams.page == n}">
                                        <a @click="setPage(n)">{{n}}</a>
                                    </li>
                                    <li class="page-item" :class="{'disabled' : !users.content || users.last}"><a class="page-link"
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
        name: 'UnconfirmedUsers',
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
                users: {},
            }
        },
        created() {
            this.loadData();
        },
        methods: {
            loadData() {
                this.loaded = false;
                axios.get("/api/v1/user-management/unactivated",{
                    params: {
                        page: this.searchParams.page,
                        size: this.searchParams.size,
                    }
                })
                    .then(response => {
                        this.users = response.data
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
            activateUser(id) {
                axios.patch("/api/v1/user-management/" + id + "/activate")
                    .then(() => {
                        this.$notify({
                            group: 'success',
                            title: this.$options.filters.message('common.success.header'),
                            text: this.$options.filters.message('user.activate.success')
                        });
                        this.loadData();
                    }).catch(() => {
                    })
            }
        }
    }
</script>