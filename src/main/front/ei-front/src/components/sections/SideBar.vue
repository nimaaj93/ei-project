<template>
    <div class="sidebar">
        <nav class="sidebar-nav ps ps--active-y">
            <ul class="nav">
                <li class="nav-item">
                    <router-link class="nav-link" to="/home"><i class="icon-speedometer"></i> داشبرد</router-link>
                </li>

                <!-- messaging -->
                <li class="nav-item nav-dropdown">
                    <a class="nav-link nav-dropdown-toggle">
                        <i class="icon-speech"></i>
                        {{'menu.messaging' | message}}
                    </a>
                    <ul class="nav-dropdown-items">
                        <li class="nav-item">
                            <router-link class="nav-link" to="/home"> <i class="icon-pencil"></i> {{'menu.send.message' | message}}</router-link>
                        </li>
                    </ul>
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
