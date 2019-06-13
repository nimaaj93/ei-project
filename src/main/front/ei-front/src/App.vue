<template>
  <component :is="$store.state.layout"></component>
</template>

<script>
    import Default from '@/components/layouts/Default'
    import Simple from '@/components/layouts/Simple'

    export default {
        name: 'App',
        components: {
            'default': Default,
            'simple' : Simple
        }
    }
</script>