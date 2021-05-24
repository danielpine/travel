<template>
  <div class="info">
    <p class="title">收藏的动态</p>
    <hr />
    <div class="personal">
      <el-table :data="momentsPostList" style="width: 100%">
        <el-table-column label="作者">
          <template slot-scope="scope">
            <span>{{
              scope.row._embedded.post.user.nickname ||
                scope.row._embedded.post.user.name
            }}</span>
          </template>
        </el-table-column>
        <el-table-column
          prop="_embedded.post.text"
          label="内容"
        ></el-table-column>
        <el-table-column label="回复/点赞" align="center">
          <template slot-scope="scope">
            <span
              >{{
                scope.row._embedded.post.comment.length === 0
                  ? '-'
                  : scope.row._embedded.post.comment.length
              }}/{{
                scope.row._embedded.post.thumb.length === 0
                  ? '-'
                  : scope.row._embedded.post.thumb.length
              }}</span
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="_embedded.post.postTime"
          label="发表时间"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="cancelFavoriteMoments(scope.row)"
              type="text"
              size="small"
            >
              取消收藏
            </el-button>
          </template>
        </el-table-column>
      </el-table>
    </div>
    <br />
    <br />
    <br />
    <p class="title">收藏的话题</p>
    <hr />
    <div class="personal">
      <el-table :data="topicPostList" style="width: 100%">
        <el-table-column prop="_embedded.post.title" label="标题">
          <template slot-scope="scope">
            <el-link
              @click="
                $router.push({
                  path: `/topic-view/${scope.row._embedded.post.id}`
                })
              "
              type="primary"
              >{{ scope.row._embedded.post.title }}</el-link
            >
          </template>
        </el-table-column>
        <!-- <el-table-column prop="topic" label="话题"></el-table-column> -->
        <el-table-column label="作者">
          <template slot-scope="scope">
            <span>{{
              scope.row._embedded.post.user.nickname ||
                scope.row._embedded.post.user.name
            }}</span>
          </template>
        </el-table-column>
        <el-table-column label="回复/点赞" align="center">
          <template slot-scope="scope">
            <span
              >{{
                scope.row._embedded.post.comment.length === 0
                  ? '-'
                  : scope.row._embedded.post.comment.length
              }}/{{
                scope.row._embedded.post.thumb.length === 0
                  ? '-'
                  : scope.row._embedded.post.thumb.length
              }}</span
            >
          </template>
        </el-table-column>
        <el-table-column
          prop="_embedded.post.postTime"
          label="发表时间"
        ></el-table-column>
        <el-table-column fixed="right" label="操作" width="120">
          <template slot-scope="scope">
            <el-button
              @click.native.prevent="cancelFavoriteTopic(scope.row)"
              type="text"
              size="small"
            >
              取消收藏
            </el-button>
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
      momentsPostList: [],
      topicPostList: []
    }
  },
  computed: {
    ...mapGetters(['userId'])
  },
  created () {},
  mounted () {
    this.getFavoriteMomentsPosts()
    this.getFavoritTopicPosts()
  },
  methods: {
    cancelFavoriteTopic (item) {
      this.$confirm('确定要取消收藏该话题吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$http.delete(`/favorites/${item.id}`).then(
            response => {
              this.getFavoritTopicPosts()
            },
            err => {
              console.log(err)
              this.notify('操作失败', 'error')
            }
          )
        })
        .catch(() => {})
    },
    cancelFavoriteMoments (item) {
      this.$confirm('确定要取消收藏该动态吗?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning'
      })
        .then(() => {
          this.$http.delete(`/favorites/${item.id}`).then(
            response => {
              this.getFavoriteMomentsPosts()
            },
            err => {
              console.log(err)
              this.notify('操作失败', 'error')
            }
          )
        })
        .catch(() => {})
    },
    getFavoriteMomentsPosts () {
      if (this.loginIn) {
        this.$http
          .get(
            `/favorites/search/findByUserIdAndPostType?userId=${this.userId}&postType=moments`
          )
          .then(
            response => {
              console.log(response.data)
              this.momentsPostList = response.data._embedded.favorites
            },
            err => {
              console.log(err)
            }
          )
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    getFavoritTopicPosts () {
      if (this.loginIn) {
        this.$http
          .get(
            `/favorites/search/findByUserIdAndPostType?userId=${this.userId}&postType=topic`
          )
          .then(
            response => {
              console.log(response.data)
              this.topicPostList = response.data._embedded.favorites
              // this.$$set(this,'momentsPostList',response.data._embedded.favorites)
            },
            err => {
              console.log(err)
            }
          )
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
