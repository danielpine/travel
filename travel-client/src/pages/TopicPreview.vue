<template>
  <div class="container">
    <div class="topic-section">
      <topic-view :topic="topic"></topic-view>
    </div>
    <fixed-button :fun="goTopicEdit"></fixed-button>
  </div>
</template>

<script>
import TopicView from '../components/TopicView'
import FixedButton from '../components/FixedButton'
import { mapGetters } from 'vuex'

export default {
  components: {
    TopicView,
    FixedButton
  },
  data () {
    return {
      // topic: {}
    }
  },
  computed: {
    ...mapGetters(['topic'])
  },
  mounted () {
    // this.topic = { title: '我爱喜茶🥳', topic: '2', text: '<p>222</p>' }
  },
  methods: {
    goTopicEdit () {
      this.$router.push({ path: '/topic-new' })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/container.scss';
</style>
