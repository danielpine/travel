<template>
  <div class="container">
    <div class="topic-section">
      <editor></editor>
    </div>
  </div>
</template>

<script>
import Editor from '../components/editor/Editor'
import { mapGetters } from 'vuex'

export default {
  components: {
    Editor
  },
  data () {
    return {}
  },
  computed: {
    ...mapGetters(['id'])
  },
  mounted () {},
  methods: {
    // 获取当前页
    handleCurrentChange (val) {
      this.currentPage = val
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/container.scss';
</style>
