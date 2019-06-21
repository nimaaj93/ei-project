<template>
    <div class="sidebar">
        <nav class="sidebar-nav">
            <ul class="nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/home"><i class="icon-speedometer"></i> {{'menu.dashboard' | message}} </router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/profile"><i class="fa fa-user"></i> {{'menu.profile' | message}} </router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/create-ticket"><i class="fa fa-ticket"></i> {{'menu.create.ticket' | message}} </router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/user-tickets"><i class="fa fa-tags"></i> {{'menu.manage.user.ticket' | message}} </router-link>
                </li>
                <li class="nav-item" v-if="hasAuthority('ADMIN')">
                    <router-link class="nav-link" to="/user-list"><i class="fa fa-users"></i> {{'menu.user.list' | message}} </router-link>
                </li>
                <li class="nav-item" v-if="hasAuthority('ADMIN')">
                    <router-link class="nav-link" to="/unconfirmed-users"><i class="fa fa-users"></i> {{'menu.unconfirmed.user.lists' | message}} </router-link>
                </li>
                <li class="nav-item" v-if="hasAuthority('ADMIN')">
                    <router-link class="nav-link" to="/tickets-management"><i class="fa fa-list"></i> {{'menu.tickets.management' | message}} </router-link>
                </li>
            </ul>
        </nav>
    </div>
</template>

<script>
    /* eslint-disable */
    export default{
        name:'SideBar',
        mounted() {
            $(".nav-dropdown-toggle").click(function() {
                var el = $(this);
                el.parent().toggleClass('open');
            })
        },
        methods: {
            hasAuthority(authorityVal) {
                if (this.$store.state.authorities.find((item) => {
                        return item == authorityVal
                    } )) {
                    return true;
                }
                return false;
            }
        }
    }
</script>
