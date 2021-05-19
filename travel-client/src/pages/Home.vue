<template>
  <div class="home">
    <!--轮播图-->
    <!-- <swiper :swiperList="swiperList" /> -->
    <div class="section">
      <el-row style="margin:12px 0">
        <br />
        <h4>有什么新发现告诉大家？</h4>
      </el-row>
      <!-- {{ fileList }} -->
      <el-row>
        <el-input
          class="comment-input"
          type="textarea"
          placeholder="打卡新地点..."
          :rows="2"
          v-model="postForm.text"
        >
        </el-input>
      </el-row>
      <el-row style="margin:12px 0">
        <el-popover placement="bottom" trigger="hover">
          <div style="max-width:480px;padding-left:20px;">
            <el-upload
              :action="uploadUrl()"
              :on-preview="handlePreview"
              :on-remove="handleRemove"
              :on-success="handleAvatarSuccess"
              :on-error="handleError"
              :before-upload="beforeAvatarUpload"
              :limit="9"
              list-type="picture-card"
              ref="imgupload"
            >
              <i class="el-icon-plus"></i>
              <div slot="tip" class="el-upload__tip">
                只能上传jpg/png文件，且不超过500kb
              </div>
            </el-upload>
          </div>
          <el-button
            size="small"
            type="primary"
            plain
            slot="reference"
            icon="el-icon-picture"
            >图片</el-button
          >
        </el-popover>
        <el-popover placement="bottom" width="600" trigger="hover">
          <div>
            <map-search :visible="visible" :map-form="location"></map-search>
          </div>
          <el-button
            size="small"
            type="success"
            plain
            slot="reference"
            icon="el-icon-location-outline"
            >{{ location.address ? location.address : '地点' }}</el-button
          >
        </el-popover>
        <el-button
          type="warning"
          size="small"
          class="sub-btn pull-right"
          @click="putPost()"
          >发表</el-button
        >
      </el-row>
    </div>

    <div class="section">
      <el-menu
        :default-active="activeIndex"
        class="section-el-menu"
        mode="horizontal"
        @select="handleSelect"
      >
        <el-menu-item index="1">最新</el-menu-item>
        <el-menu-item index="2">热门</el-menu-item>
        <el-menu-item index="3">推荐</el-menu-item>
        <el-menu-item index="4">猜你喜欢</el-menu-item>
      </el-menu>
    </div>

    <div class="section" v-for="(item, index) in postList" :key="index">
      <el-row class="section-content">
        <el-col :span="1">
          <div class="section-user">
            <img
              :src="attachImageUrl((item.user && item.user.avator) || avator)"
              alt=""
            />
          </div>
        </el-col>
        <el-col :span="21" :offset="1">
          <el-row
            ><h4>
              {{
                (item.user && item.user.nickname) ||
                  (item.user && item.user.name) ||
                  '用户'
              }}
            </h4></el-row
          >
          <el-row class="section-timestamp">{{ item.postTime }}</el-row>
          <el-row>{{ item.text }}</el-row>
          <el-row v-if="item.image" style="width:612px">
            <el-image
              v-for="(img, index) in item.image"
              :key="index"
              style="margin:2px 2px 2px 2px"
              :style="getImageWidthHeightDynamic(item.image.length)"
              :src="attachImageUrl(img.url)"
              :preview-src-list="attachImageUrlList(item.image)"
            >
            </el-image>
          </el-row>
          <el-row v-if="item.location">
            <el-link icon="el-icon-location-outline">{{
              item.location.address
            }}</el-link>
          </el-row>
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <el-row class="section-bottom" type="flex" justify="space-between">
        <el-col :span="6"
          ><el-button
            type="text"
            :icon="item.favorite ? 'el-icon-star-on' : 'el-icon-star-off'"
            class="no-text-decoration"
            :style="{ color: item.favorite ? 'coral' : '#808080' }"
            @click="toggleFavorite(item, item._links.self.href)"
          >
            {{ item.favorite ? '已收藏' : '收藏' }}</el-button
          ></el-col
        >
        <el-col :span="6"
          ><el-button type="text" icon="el-icon-close">举报</el-button></el-col
        >
        <el-col :span="6"
          ><el-button
            type="text"
            icon="el-icon-chat-dot-square"
            @click="toggleComment(item, 'main')"
            >{{
              item.comment && item.comment.length > 0
                ? item.comment.length
                : '评论'
            }}</el-button
          ></el-col
        >
        <el-col :span="6"
          ><el-button
            type="text"
            icon="el-icon-thumb"
            :style="getThumbColor(item)"
            @click="
              toggleThumb(item, item._links.self.href, item._links.thumb.href)
            "
            >{{
              item.thumb && item.thumb.length > 0 ? item.thumb.length : '赞'
            }}</el-button
          ></el-col
        >
      </el-row>
      <el-divider></el-divider>
      <el-row
        style="background-color:aliceblue;padding-top:3px;padding-right:5px"
      >
        <div class="container">
          <div v-show="commentShow['main' + item.id]">
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
                    :ref="'main' + item.id"
                    type="textarea"
                    :placeholder="
                      '回复:' + (item.user && item.user.nickname) ||
                        (item.user && item.user.name) ||
                        '用户'
                    "
                    :rows="1"
                    v-model="commentForm['main' + item.id]"
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
                      item, //item
                      item.id, //commentId
                      item._links.self.href, //post
                      item.user.id, //toUser
                      'main', //commentType
                      null //commentRoot
                    )
                  "
                  >评论</el-button
                >
              </el-row>
            </el-row>
            <el-divider></el-divider>
            <el-row v-for="comment in item.comment" :key="comment.id">
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
                          @click="delComment(item, comment._links.self.href)"
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
                            item, //item
                            comment.id, //commentId
                            null, //post
                            comment.user.id, //toUser
                            'sub', //commentType
                            comment._links.self.href //commentRoot
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
                              delComment(item, subcomment._links.self.href)
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
                              item, //item
                              subcomment.id, //commentId
                              null, //post
                              subcomment.user.id, //toUser
                              'next', //commentType
                              comment._links.self.href //commentRoot
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
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
// import { swiperList } from '../assets/data/swiper'
import Swiper from '../components/Swiper'
import MapSearch from '../components/amap/MapSearch'
// import { getpostList } from '../api/index'

export default {
  name: 'home',
  mixins: [mixin],
  components: {
    Swiper,
    MapSearch
  },
  data () {
    return {
      postList: [],
      posts: '',
      postForm: this.initPostForm(),
      location: {
        address: '',
        lng: '',
        lat: ''
      },
      commentForm: {},
      commentShow: {},
      fileList: [],
      activeIndex: '1',
      visible: true
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
  created () {},
  mounted () {
    this.getPosts()
  },
  methods: {
    handleSelect () {},
    toggleComment (item, t) {
      this.$set(this.commentShow, t + item.id, !this.commentShow[t + item.id])
      let vm = this
      setTimeout(function () {
        vm.activeReplyCommentId = t + item.id
      }, 5)
      setTimeout(function () {
        vm.$refs[t + item.id][0].focus()
      }, 10)
    },
    replyBlur (e) {
      console.log(e)
      this.activeReplyCommentId = -1
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
      this.$http
        .get(`posts/${item.id}/comment?projection=commentProjection`)
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
            this.getComment(item)
          })
          .catch(error => {
            console.log(error)
          })
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    isFavorite (item) {
      if (this.loginIn) {
        this.$http
          .get(
            `favorites/search/findByPostIdAndUserId?postId=${item.id}&userId=${this.userId}`
          )
          .then(
            response => {
              console.log(response)
              this.$set(item, 'favorite', response.data)
            },
            err => {
              console.log(err)
              this.$set(item, 'favorite', null)
            }
          )
      }
    },
    getThumb (item, thumblocation) {
      this.$http.get(thumblocation).then(
        response => {
          console.log(response)
          item.thumb = response.data._embedded.thumbs
        },
        err => {
          console.log(err)
          this.notify('操作失败', 'error')
        }
      )
    },
    toggleThumb (item, post, thumblocation) {
      if (this.loginIn) {
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
    toggleFavorite (item, post) {
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
              post: post
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
    uploadUrl () {
      return `${this.$store.state.configure.HOST}/file/upload?id=${this.userId}&type=post`
    },
    handleAvatarSuccess (res, file, fileList) {
      if (res.code === 1) {
        this.fileList = fileList
        this.$message({
          message: '上传成功',
          type: 'success'
        })
      } else {
        this.notify('上传失败', 'error')
      }
    },
    handleError (err, file, fileList) {
      console.log(err)
      this.notify('上传失败', 'error', err.message.message)
    },
    beforeAvatarUpload (file) {
      const isJPG = file.type === 'image/jpeg'
      const isLt2M = file.size / 1024 / 1024 < 10
      if (!isJPG) {
        this.$message.error('上传图片只能是 JPG 格式!')
      }
      if (!isLt2M) {
        this.$message.error('上传图片大小不能超过 10MB!')
      }
      return isJPG && isLt2M
    },
    handleRemove (file, fileList) {
      this.fileList = fileList
    },
    handlePreview (file) {
      console.log(file)
    },
    putPost () {
      if (this.loginIn) {
        if (
          this.postForm.text.length !== 0 &&
          this.postForm.text !== '' &&
          this.postForm.text !== undefined
        ) {
          let that = this
          this.postForm.user = `http://localhost:8080/users/${this.userId}`
          this.fileList.forEach(e => {
            console.log(e)
            if (e.response.code === 1) {
              that.postForm.image.push({
                url: e.response.data.url
              })
            }
          })

          if (
            this.location.address !== undefined &&
            this.location.address !== ''
          ) {
            this.postForm.location = JSON.parse(JSON.stringify(this.location))
          }

          this.$http.post('/posts', this.postForm).then(
            response => {
              console.log(response.data)
              this.postForm = this.initPostForm()
              this.location = {}
              this.getPosts()
              this.$refs.imgupload.clearFiles()
            },
            err => {
              console.log(err)
            }
          )
        } else {
          this.notify('内容不能为空', 'warning')
        }
      } else {
        this.notify('请先登录', 'warning')
      }
    },
    getPosts () {
      if (this.loginIn) {
      }
      this.$http
        .get(
          '/posts?projection=postProjection&sort=postTime,desc',
          this.postForm
        )
        .then(
          response => {
            console.log(response.data)
            this.postList = response.data._embedded.posts
            this.postList.forEach(item => {
              // item.favorite = false
              this.isFavorite(item)
            })
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
@import '../assets/css/home.scss';
.el-row {
  margin-bottom: 2px;
  &:last-child {
    margin-bottom: 0;
  }
}
.el-col {
  border-radius: 4px;
}

.el-divider--horizontal {
  display: block;
  height: 1px;
  width: 100%;
  margin: 2px 0;
}
</style>
