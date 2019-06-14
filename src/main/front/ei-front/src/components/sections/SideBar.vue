<template>
    <div class="sidebar">
        <nav class="sidebar-nav">
            <ul class="nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/home"><i class="icon-speedometer"></i> داشبرد</router-link>
                </li>
                <li class="nav-item">
                    <router-link class="nav-link" to="/profile"><i class="icon-speedometer"></i> داشبرد</router-link>
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
            hasOneOf(authorityList) {
                for (let authority of authorityList) {
                    if (this.hasAuthority(authority)) {
                        return true;
                    }
                }
                return false;
            },
            hasAuthority(authorityVal) {
//                if (this.$store.state.authorities.find((item) => {
//                        return item.authorityName == authorityVal
//                    } )) {
//                    return true;
//                }
//                return false;
                return true;
            }
        }
    }
</script>
