<template>
  <div class="container">
    <div v-if="topic.text" class="topic-section">
      <topic-view :topic="topic"></topic-view>
      <el-row class="section-bottom" type="flex" justify="space-between">
        <el-col :span="6"
          ><el-button
            type="text"
            :icon="topic.favorite ? 'el-icon-star-on' : 'el-icon-star-off'"
            class="no-text-decoration"
            :style="{ color: topic.favorite ? 'coral' : '#808080' }"
            @click="toggleFavorite(topic, topic.id)"
          >
            {{ topic.favorite ? '已收藏' : '收藏' }}</el-button
          ></el-col
        >
        <el-col :span="6"
          ><el-button type="text" icon="el-icon-close">举报</el-button></el-col
        >
        <el-col :span="6"
          ><el-button
            type="text"
            icon="el-icon-chat-dot-square"
            @click="toggleComment(topic, 'main')"
            >{{
              topic.comment && topic.comment.length > 0
                ? topic.comment.length
                : '评论'
            }}</el-button
          ></el-col
        >
        <el-col :span="6"
          ><el-button
            type="text"
            icon="el-icon-thumb"
            :style="getThumbColor(topic)"
            @click="toggleThumb(topic, topic.id)"
            >{{
              topic.thumb && topic.thumb.length > 0 ? topic.thumb.length : '赞'
            }}</el-button
          ></el-col
        >
      </el-row>
      <el-divider></el-divider>
      <el-row
        style="background-color:aliceblue;padding-top:3px;padding-right:5px"
      >
        <div class="container">
          <!-- v-show="commentShow['main' + topic.id]" -->
          <div>
            <el-row style="margin:12px 0">
              <el-row>
                <el-col :span="2">
                  <div class="section-user-comment">
                    <img :src="attachImageUrl(avator)" alt="用户头像" />
                  </div>
                </el-col>
                <el-col :span="22">
                  <el-input
                    class="comment-input"
                    :ref="'main' + topic.id"
                    type="textarea"
                    :placeholder="
                      '回复:' + (topic.user && topic.user.nickname) ||
                        (topic.user && topic.user.name) ||
                        '用户'
                    "
                    :rows="1"
                    v-model="commentForm['main' + topic.id]"
                  >
                  </el-input
                ></el-col>
              </el-row>
              <el-row style="margin: 10px 0">
                <el-button
                  type="warning"
                  size="mini"
                  class="sub-btn pull-right"
                  @click="
                    postComment(
                      topic, //topic
                      topic.id, //commentId
                      toPostLocation(topic.id), //post
                      topic.user.id, //toUser
                      'main', //commentType
                      null //commentRoot
                    )
                  "
                  >评论</el-button
                >
              </el-row>
            </el-row>
            <el-divider></el-divider>
            <el-row v-for="comment in topic.comment" :key="comment.id">
              <el-row>
                <el-col :span="2">
                  <div class="section-user-comment">
                    <img
                      :src="attachImageUrl(comment.user.avator)"
                      alt="用户头像"
                    />
                  </div>
                </el-col>
                <el-col :span="22">
                  <el-row>
                    <span class="name"
                      >{{ comment.user.nickname || comment.user.name }}:</span
                    >
                    <span class="content">
                      {{ comment.content }}
                    </span>
                  </el-row>
                  <div
                    @mouseover="$set(comment, 'showDelete', true)"
                    @mouseout="$set(comment, 'showDelete', false)"
                  >
                    <el-row
                      class="section-bottom"
                      type="flex"
                      justify="space-between"
                    >
                      <el-col :span="2" style="text-align:left" class="date">
                        {{ getDateDuration(new Date(comment.createTime)) + '' }}
                      </el-col>
                      <el-col
                        :span="4"
                        style="width:200px;text-align:right;margin-right:3px"
                      >
                        <el-button
                          type="text"
                          size="mini"
                          v-show="
                            comment.user.id == userId && comment.showDelete
                          "
                          @click="delComment(topic, comment.id)"
                          >删除</el-button
                        ><el-button
                          type="text"
                          size="mini"
                          @click="toggleComment(comment, 'sub')"
                          >回复</el-button
                        ></el-col
                      >
                    </el-row>
                  </div>
                  <el-row
                    v-show="commentShow['sub' + comment.id]"
                    style="margin:12px 0"
                  >
                    <el-row>
                      <el-col :span="24">
                        <el-input
                          class="comment-input"
                          :ref="'sub' + comment.id"
                          type="textarea"
                          :placeholder="
                            '回复:' + (comment.user && comment.user.nickname) ||
                              (comment.user && comment.user.name) ||
                              '用户'
                          "
                          :rows="1"
                          v-model="commentForm['sub' + comment.id]"
                        >
                        </el-input></el-col
                    ></el-row>
                    <el-row style="margin:12px 0">
                      <el-button
                        type="warning"
                        size="mini"
                        class="sub-btn pull-right-2"
                        @click="
                          postComment(
                            topic, //item
                            comment.id, //commentId
                            null, //post
                            comment.user.id, //toUser
                            'sub', //commentType
                            toCommentLocation(comment.id) //commentRoot
                          )
                        "
                        >评论</el-button
                      >
                    </el-row>
                  </el-row>
                  <el-divider></el-divider>
                  <!-- Sub Comment -->
                  <el-row
                    v-for="subcomment in comment.sub"
                    :key="subcomment.id"
                    style="background-color:azure;padding-left:5px;"
                  >
                    <el-row>
                      <span class="name">{{
                        subcomment.user.nickname || subcomment.user.name
                      }}</span>
                      <span
                        v-if="
                          subcomment.toUser &&
                            subcomment.user.id !== subcomment.toUser.id
                        "
                      >
                        <span class="reply">回复</span>
                        <span class="name">{{
                          subcomment.toUser.nickname ||
                            subcomment.ustoUserer.name
                        }}</span>
                      </span>
                      :
                      <span class="content">
                        {{ subcomment.content }}
                      </span>
                    </el-row>
                    <div
                      @mouseover="$set(subcomment, 'showDelete', true)"
                      @mouseout="$set(subcomment, 'showDelete', false)"
                    >
                      <el-row
                        class="section-bottom"
                        type="flex"
                        justify="space-between"
                      >
                        <el-col :span="2" style="text-align:left" class="date">
                          {{ getDateDuration(new Date(subcomment.createTime)) }}
                        </el-col>
                        <el-col
                          :span="4"
                          style="width:200px;text-align:right;margin-right:3px"
                        >
                          <el-button
                            type="text"
                            size="mini"
                            v-show="
                              subcomment.user.id == userId &&
                                subcomment.showDelete
                            "
                            @click="
                              delComment(topic, subcomment._links.self.href)
                            "
                            >删除</el-button
                          ><el-button
                            type="text"
                            size="mini"
                            @click="toggleComment(subcomment, 'next')"
                            >回复</el-button
                          ></el-col
                        >
                      </el-row>
                    </div>
                    <el-row
                      v-show="commentShow['next' + subcomment.id]"
                      style="margin:12px 0"
                    >
                      <el-row>
                        <el-col :span="24">
                          <el-input
                            class="comment-input"
                            :ref="'next' + subcomment.id"
                            type="textarea"
                            :placeholder="
                              '回复:' +
                                (subcomment.user && subcomment.user.nickname) ||
                                (subcomment.user && subcomment.user.name) ||
                                '用户'
                            "
                            :rows="1"
                            v-model="commentForm['next' + subcomment.id]"
                          >
                          </el-input></el-col
                      ></el-row>
                      <el-row style="margin:12px 0">
                        <el-button
                          type="warning"
                          size="mini"
                          class="sub-btn pull-right-2"
                          @click="
                            postComment(
                              topic, //item
                              subcomment.id, //commentId
                              null, //post
                              subcomment.user.id, //toUser
                              'next', //commentType
                              toCommentLocation(comment.id) //commentRoot
                            )
                          "
                          >评论</el-button
                        >
                      </el-row>
                    </el-row>
                    <el-divider></el-divider>
                  </el-row>
                </el-col>
              </el-row>
              <el-divider></el-divider>
            </el-row>
          </div>
        </div>
      </el-row>
    </div>
    <!-- <fixed-button :fun="goTopicEdit"></fixed-button> -->
  </div>
</template>

<script>
import TopicView from '../components/TopicView'
import FixedButton from '../components/FixedButton'
import { mapGetters } from 'vuex'
import mixin from '../mixins'

export default {
  mixins: [mixin],
  components: {
    TopicView,
    FixedButton
  },
  data () {
    return {
      topic: {},
      commentForm: {},
      commentShow: {}
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
    toggleComment (item, t) {
      this.$set(this.commentShow, t + item.id, !this.commentShow[t + item.id])
      let vm = this
      setTimeout(function () {
        vm.activeReplyCommentId = t + item.id
      }, 5)
      setTimeout(function () {
        console.log(vm.$refs[t + item.id])
        if (t === 'main') {
          vm.$refs[t + item.id].focus()
        } else {
          vm.$refs[t + item.id][0].focus()
        }
      }, 10)
    },
    delComment (item, location) {
      this.$http
        .delete(location.replace('{?projection}', ''))
        .then(response => {
          console.log(response)
          this.getComment(item)
        })
        .catch(error => {
          console.log(error)
        })
    },
    getComment (item) {
      let location = `http://localhost:8080/comments/search/findByPostIdAndCommentType?postId=${item.id}&commentType=main`
      this.$http
        .get(location)
        .then(response => {
          let t = response.data._embedded.comments.reverse()
          t.forEach(s => {
            s.sub = s.sub.reverse()
          })
          item.comment = t
        })
        .catch(error => {
          console.log(error)
        })
    },
    postComment (item, id, post, toUserId, commentType, commentRoot) {
      console.log(arguments)
      if (this.loginIn) {
        this.$http
          .post('comments', {
            post: post,
            user: `http://localhost:8080/users/${this.userId}`,
            toUser: `http://localhost:8080/users/${toUserId}`,
            content: this.commentForm[commentType + id],
            commentType: commentType,
            root: commentRoot ? commentRoot.replace('{?projection}', '') : null
          })
          .then(res => {
            console.log(res)
            this.commentForm[commentType + id] = ''
            if (commentType !== 'main') {
              this.$set(this.commentShow, commentType + id, false)
            }
            console.log(item)
            this.getComment(item)
          })
          .catch(error => {
            console.log(error)
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    toPostLocation (postId) {
      return `http://localhost:8080/posts/${postId}`
    },
    toCommentLocation (commentId) {
      return `http://localhost:8080/comments/${commentId}`
    },
    toggleThumb (item, postId) {
      if (this.loginIn) {
        let thumblocation = `http://localhost:8080/posts/${postId}/thumb`
        let post = this.toPostLocation(postId)
        if (item.thumbed) {
          this.$http.delete(item.userThumb).then(
            response => {
              console.log(thumblocation)
              this.getThumb(item, thumblocation)
            },
            err => {
              console.log(err)
              this.notify('操作失败', 'error')
            }
          )
        } else {
          this.$http
            .post('/thumbs', {
              user: `http://localhost:8080/users/${this.userId}`,
              post: post
            })
            .then(
              response => {
                this.getThumb(item, thumblocation)
              },
              err => {
                console.log(err)
                this.notify('操作失败', 'error')
              }
            )
        }
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    toggleFavorite (item, postId) {
      if (this.loginIn) {
        if (item.favorite) {
          this.$http
            .delete(`http://localhost:8080/favorites/${item.favorite.id}`)
            .then(
              response => {
                this.isFavorite(item)
              },
              err => {
                console.log(err)
                this.notify('操作失败', 'error')
              }
            )
        } else {
          this.$http
            .post('/favorites', {
              user: `http://localhost:8080/users/${this.userId}`,
              post: `http://localhost:8080/posts/${postId}`
            })
            .then(
              response => {
                this.isFavorite(item)
              },
              err => {
                console.log(err)
                this.notify('操作失败', 'error')
              }
            )
        }
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    goTopicEdit () {
      this.$router.push({ path: '/topic-new' })
    },
    getPost () {
      this.$http
        .get(
          `/posts/search/findByIdAndPostType?id=${this.$route.params.postId}&postType=topic&projection=postProjection`
        )
        .then(
          response => {
            console.log('====', response.data)
            this.topic = response.data
            this.isFavorite(this.topic)
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
