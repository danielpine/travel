<template>
  <div>
    <div class="section" v-for="(item, index) in contentList" :key="index">
      ::{{ item.text }}
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'

export default {
  name: 'content-list',
  mixins: [mixin],
  props: {
    contentList: Array,
    path: String
  },
  methods: {
    goAblum (item) {
      this.$store.commit('setTempList', item)
      this.$router.push({ path: `/${this.path}/${item.id}` })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/content-list.scss';
</style>
