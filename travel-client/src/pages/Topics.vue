<template>
  <div class="container">
    <div class="topic-section">
      <div>
        <el-button
          class="pull-right-20"
          size="mini"
          type="primary"
          plain
          icon="el-icon-edit"
          @click="$router.push({ path: '/topic-new' })"
        >
          发表
        </el-button>
      </div>
      <div class="section">
        <el-menu
          :default-active="activeIndex"
          class="section-el-menu"
          mode="horizontal"
          @select="handleSelect"
        >
          <el-menu-item index="latest">最新</el-menu-item>
          <!-- <el-menu-item index="2">热门</el-menu-item> -->
          <el-menu-item index="recommend">推荐</el-menu-item>
          <!-- <el-menu-item index="4">猜你喜欢</el-menu-item> -->
        </el-menu>
      </div>
      <el-table :data="postList" style="width: 100%">
        <el-table-column prop="title" label="标题">
          <template slot-scope="scope">
            <el-link
              @click="$router.push({ path: `/topic-view/${scope.row.id}` })"
              type="primary"
              >{{ scope.row.title }}</el-link
            >
          </template>
        </el-table-column>
        <el-table-column prop="topic" label="话题"></el-table-column>
        <el-table-column label="作者">
          <template slot-scope="scope">
            <span>{{ scope.row.user.nickname || scope.row.user.name }}</span>
          </template>
        </el-table-column>
        <el-table-column label="回复/点赞" align="center">
          <template slot-scope="scope">
            <span
              >{{
                scope.row.comment.length === 0 ? '-' : scope.row.comment.length
              }}/{{
                scope.row.thumb.length === 0 ? '-' : scope.row.thumb.length
              }}</span
            >
          </template>
        </el-table-column>
        <el-table-column prop="postTime" label="发表时间"></el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'

export default {
  data () {
    return {
      postList: [],
      activeIndex: 'latest'
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
    this.getLatestPosts()
  },
  methods: {
    handleSelect (key, keyPath) {
      console.log(key, keyPath)
      if (key === 'recommend') {
        this.getRecommendPosts()
      } else {
        this.getLatestPosts()
      }
    },
    getLatestPosts () {
      this.$http
        .get(
          '/posts/search/findByPostType?projection=postProjection&sort=postTime,desc&postType=topic'
        )
        .then(
          response => {
            console.log(response.data)
            this.postList = response.data._embedded.posts
          },
          err => {
            console.log(err)
          }
        )
    },
    getRecommendPosts () {
      this.$http
        .get(`/posts/recommend?userId=${this.userId}&postType=topic`)
        .then(
          response => {
            console.log(response.data)
            this.postList = response.data.data
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
