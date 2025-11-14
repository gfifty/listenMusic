<template>
  <svg :width="size" :height="size" class="svg-icon">
    <use :href="`${spritePath}#${name}`"></use>
  </svg>
</template>

<script setup>
const props = defineProps({
  name: { type: String, required: true },
  size: { type: Number, default: 24 },
  spritePath: { type: String, default: '/static/icons/icon.svg' }
})
</script>

<style>
.svg-icon { display: inline-block; vertical-align: middle; }
</style>