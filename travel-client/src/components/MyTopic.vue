<template>
  <div class="info">
    <p class="title">我的话题</p>
    <hr />
    <div class="personal">
      <el-table :data="posts" style="width: 100%">
        <el-table-column prop="title" label="标题">
          <template slot-scope="scope">
            <el-link
              @click="
                $router.push({
                  path: `/topic-view/${scope.row.id}`
                })
              "
              type="primary"
              >{{ scope.row.title }}</el-link
            >
          </template>
        </el-table-column>
        <el-table-column prop="topic" label="话题"></el-table-column>
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
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-popconfirm
              placement="top"
              confirm-button-text="删除"
              cancel-button-text="点错了"
              icon="el-icon-info"
              icon-color="red"
              title="确定删除吗？"
              @confirm="deletePost(scope.row)"
            >
              <el-button type="text" size="small" slot="reference"
                >删除</el-button
              >
            </el-popconfirm>
          </template>
        </el-table-column>
      </el-table>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'

export default {
  name: 'info',
  mixins: [mixin],
  data: function () {
    return {
      posts: []
    }
  },
  computed: {
    ...mapGetters(['userId'])
  },
  created () {},
  mounted () {
    this.getPosts()
  },
  methods: {
    deletePost (item) {
      this.$http.delete(`/posts/${item.id}`).then(
        response => {
          this.getPosts()
        },
        err => {
          console.log(err)
          this.notify('操作失败', 'error')
        }
      )
    },
    getPosts () {
      if (this.loginIn) {
        this.$http
          .get(
            `/posts/search/findByPostTypeAndUserId?postType=topic&userId=${this.userId}`
          )
          .then(
            response => {
              console.log(response.data)
              this.posts = response.data._embedded.posts
            },
            err => {
              console.log(err)
            }
          )
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    goback () {
      this.$router.go(-1)
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
