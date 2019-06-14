<template>
    <div>
        <notifications-panel></notifications-panel>
        <app-header></app-header>
        <side-bar></side-bar>
        <!-- Main content -->
        <main class="main">

            <!-- Breadcrumb -->
            <ol class="breadcrumb">
                <router-link tag="li" class="breadcrumb-item" to="/home">خانه</router-link>

                <!-- Breadcrumb Menu-->
                <li class="breadcrumb-menu">
                    <div class="btn-group" role="group" aria-label="Button group with nested dropdown">
                        <router-link to="/home" class="btn btn-secondary"><i class="icon-graph"></i> &nbsp;داشبرد</router-link>
                    </div>
                </li>
            </ol>

            <div class="container-fluid">
                <transition name="fade">
                    <router-view></router-view>
                </transition>
            </div>

        </main>

        <app-footer></app-footer>
    </div>
</template>
<script>
    import AppHeader from '@/components/sections/AppHeader'
    import SideBar from '@/components/sections/SideBar'
    import AppFooter from '@/components/sections/AppFooter'
    import NotificationsPanel from '@/components/sections/NotificationsPanel'

    export default {
        name: 'Default',
        components: {
            AppHeader,
            SideBar,
            AppFooter,
            NotificationsPanel
        }
    }
</script>