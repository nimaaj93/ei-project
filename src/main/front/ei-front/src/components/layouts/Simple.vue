<template>
    <div>
        <notifications-panel></notifications-panel>
        <transition name="fade">
            <router-view/>
        </transition>
    </div>
</template>
<script>

    import NotificationsPanel from '@/components/sections/NotificationsPanel'

    export default {
        name: 'simple',
        components: {
            NotificationsPanel
        }
    }
</script>