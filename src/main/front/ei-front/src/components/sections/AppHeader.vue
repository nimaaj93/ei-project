<template>
    <header class="navbar">
        <div class="container-fluid">
            <button class="navbar-toggler mobile-toggler hidden-lg-up" type="button">&#9776;</button>
            <a class="navbar-brand"></a>
            <ul class="nav navbar-nav hidden-md-down">
                <li class="nav-item">
                    <a class="nav-link navbar-toggler layout-toggler">&#9776;</a>
                </li>
            </ul>
            <ul class="nav navbar-nav pull-left hidden-md-down">
                <li class="nav-item">
                    {{$store.state.userInfo.sub}}
                </li>
                <li class="nav-item">
                    <a class="nav-link" @click="logout"><i class="fa fa-lock"></i> خروج</a>
                </li>
            </ul>
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
