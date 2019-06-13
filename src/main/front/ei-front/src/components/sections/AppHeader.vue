<template>
    <header class="navbar">
        <div class="container-fluid">
            <button class="navbar-toggler mobile-toggler hidden-lg-up" type="button">&#9776;</button>
            <a class="navbar-brand"></a>
            <ul class="nav navbar-nav hidden-md-down">
                <li class="nav-item">
                    <a class="nav-link navbar-toggler layout-toggler">&#9776;</a>
                </li>

                <!--<li class="nav-item p-x-1">-->
                    <!--{{ currentDate | jalaaliDof}} {{ currentDate }}-->
                <!--</li>-->
                <!--<li class="nav-item p-x-1">-->
                    <!--<clock/>-->
                <!--</li>-->
                <!--
                <li class="nav-item p-x-1">
                    <a class="nav-link" href="#">Settings</a>
                </li>-->
            </ul>
            <!--<ul class="nav navbar-nav pull-left hidden-md-down">-->
                <!--&lt;!&ndash;<li class="nav-item">&ndash;&gt;-->
                <!--&lt;!&ndash;<a class="nav-link aside-toggle"><i class="icon-bell"></i><span class="tag tag-pill tag-danger">5</span></a>&ndash;&gt;-->
                <!--&lt;!&ndash;</li>&ndash;&gt;-->
                <!--<li class="nav-item">-->
                    <!--<a class="nav-link dropdown-toggle nav-link" data-toggle="dropdown"-->
                       <!--@click="loadNewMessages()"-->
                       <!--role="button" aria-haspopup="true" aria-expanded="false">-->
                        <!--<i class="icon-envelope-letter"></i>-->
                        <!--<span class="badge badge-pill badge-info new-messages-badge"-->
                              <!--v-if="$store.state.newMessagesCount > 0">{{$store.state.newMessagesCount}}</span>-->
                    <!--</a>-->
                    <!--<div class="dropdown-menu dropdown-menu-right new-messages-dropdown">-->
                        <!--<div class="dropdown-header text-xs-center" v-if="!messagesLoaded">-->
                            <!--<i class="fa fa-spinner fa-spin" style="color: #000000; font-weight: bold"></i>-->
                        <!--</div>-->
                        <!--<div class="dropdown-header text-xs-center" v-else-if="$store.state.newMessagesCount > 0">-->
                            <!--<small style="font-weight: bold; color: #000000">-->
                                <!--{{$store.state.newMessagesCount}} {{'new.message.dropdown.header' | message}}-->
                            <!--</small>-->
                        <!--</div>-->
                        <!--<div class="dropdown-header text-xs-center" v-else>-->
                            <!--<strong>{{'no.new.message.dropdown.header' | message}}</strong>-->
                        <!--</div>-->
                        <!--<div class="dropdown-item" v-for="item in newMessages" @click="$router.push('/view-message')"-->
                             <!--v-if="messagesLoaded">-->
                            <!--<div class="message">-->
                                <!--<div class="text-truncate font-weight-bold">-->
                                    <!--{{item.messageTitle}}-->
                                <!--</div>-->
                                <!--<div>-->
                                    <!--<small class="text-muted">{{item.senderUserName}}</small>-->
                                <!--</div>-->
                                <!--<div class="small text-muted text-truncate" v-html="item.messageBody">-->
                                <!--</div>-->
                            <!--</div>-->
                        <!--</div>-->
                        <!--<a @click="$router.push('/view-message')" class="dropdown-item text-xs-center"><i-->
                                <!--class="fa fa-arrow-circle-down"></i>-->
                        <!--</a>-->
                    <!--</div>-->
                <!--</li>-->
                <!--<li class="nav-item">-->

                <!--</li>-->
                <!--<li class="nav-item dropdown">-->
                    <!--<a class="nav-link dropdown-toggle nav-link" data-toggle="dropdown" role="button" aria-haspopup="true"-->
                       <!--aria-expanded="false">-->
                        <!--<span class="hidden-md-down" v-if="$store.state.userInfo">{{$store.state.userInfo.nameFa}}</span>-->
                        <!--&lt;!&ndash;<span class="hidden-md-down" v-if="$store.state.userInfo">{{$store.state.userInfo.roleTitle}}</span>&ndash;&gt;-->
                    <!--</a>-->
                    <!--<div class="dropdown-menu dropdown-menu-right">-->
                        <!--<div class="dropdown-header text-xs-center">-->
                            <!--<strong>تنظیمات</strong>-->
                        <!--</div>-->
                        <!--<router-link class="dropdown-item" to="/profile"><i class="fa fa-user"></i> پروفایل</router-link>-->
                        <!--<router-link class="dropdown-item" to="/resetpass"><i class="fa fa-usd"></i> {{'change.password' | message}}-->
                        <!--</router-link>-->
                        <!--<div class="divider"></div>-->
                        <!--<a class="dropdown-item" @click="logout"><i class="fa fa-lock"></i> خروج</a>-->
                    <!--</div>-->
                <!--</li>-->
                <!--&lt;!&ndash;<li class="nav-item">&ndash;&gt;-->
                <!--&lt;!&ndash;<a class="nav-link navbar-toggler aside-toggle">&#9776;</a>&ndash;&gt;-->
                <!--&lt;!&ndash;</li>&ndash;&gt;-->

            <!--</ul>-->
        </div>
    </header>
</template>

<script>
    /* eslint-disable */
    import axios from 'axios'

    export default {
        name: 'AppHeader',
        components: {
        },
        data() {
            return {
            }
        },
        created() {
        },
        mounted() {
            this.loadSidebar();
        },
        watch: {
            '$router': function () {
                this.loadSidebar();
            }
        },
        methods: {
            loadSidebar() {
                require('@/assets/js/lib/jquery.min');
                $('.navbar-toggler').click(function () {

                    var bodyClass = localStorage.getItem('body-class');

                    if ($(this).hasClass('layout-toggler') && $('body').hasClass('sidebar-off-canvas')) {
                        $('body').toggleClass('sidebar-opened').parent().toggleClass('sidebar-opened');
                        //resize charts
                        this.resizeBroadcast();

                    } else if ($(this).hasClass('layout-toggler') && ($('body').hasClass('sidebar-nav') || bodyClass == 'sidebar-nav')) {
                        $('body').toggleClass('sidebar-nav');
                        localStorage.setItem('body-class', 'sidebar-nav');
                        if (bodyClass == 'sidebar-nav') {
                            localStorage.clear();
                        }
                        //resize charts
                        this.resizeBroadcast();
                    } else {
                        $('body').toggleClass('mobile-open');
                    }
                });

                $('.aside-toggle').click(function () {
                    $('body').toggleClass('aside-menu-open');

                    //resize charts
                    this.resizeBroadcast();
                });

                $('.sidebar-close').click(function () {
                    $('body').toggleClass('sidebar-opened').parent().toggleClass('sidebar-opened');
                });
            },
            resizeBroadcast() {

                var timesRun = 0;
                var interval = setInterval(function () {
                    timesRun += 1;
                    if (timesRun === 5) {
                        clearInterval(interval);
                    }
                    window.dispatchEvent(new Event('resize'));
                }, 62.5);
            },
            logout() {
                this.$store.commit('LOGOUT');
                this.$router.push({path: "/login"})
            },
        }
    }
</script>
