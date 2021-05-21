<template>
  <div class="fixed-button">
    <el-button type="text" @click="fun">
      返回编辑
    </el-button>
  </div>
</template>

<script>
export default {
  props: ['fun']
}
</script>

<style lang="scss" scoped>
@import '../assets/css/fixed-button.scss';
</style>
