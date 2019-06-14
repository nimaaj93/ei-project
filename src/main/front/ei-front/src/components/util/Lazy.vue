<template>
    <div class="lazy-container" :style="{ minHeight: minHeight + 'px'}">
        <transition name="fade">
            <div v-if="loaded">
                <slot>

                </slot>
            </div>
            <span v-else class="my-spinner">
      </span>
        </transition>
    </div>
</template>
<script>
    export default {
        name: 'Lazy',
        props: {
            loaded: {
                type: Boolean,
                required: true
            },
            minHeight: {
                type: Number,
                required: false,
                default: 500
            }
        }
    }
</script>