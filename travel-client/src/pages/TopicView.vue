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
      topic: {}
    }
  },
  computed: {
    ...mapGetters([
      'id',
      'userId', // 用户ID
      'index', // 列表中的序号
      'loginIn', // 用户是否登录
      'avator' // 用户头像
    ])
  },
  mounted () {
    this.getPost()
  },
  methods: {
    goTopicEdit () {
      this.$router.push({ path: '/topic-new' })
    },
    getPost () {
      this.$http
        .get(
          `/posts/${this.$route.params.postId}?projection=postProjection&sort=postTime,desc&postType=moments`
        )
        .then(
          response => {
            console.log(response.data)
            this.topic = response.data
            // this.postList = response.data._embedded.posts
            // this.postList.forEach(item => {
            //   this.isFavorite(item)
            // })
          },
          err => {
            console.log(err)
          }
        )
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/container.scss';
</style>
