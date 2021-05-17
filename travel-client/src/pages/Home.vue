<template>
  <div class="home">
    <!--轮播图-->
    <!-- <swiper :swiperList="swiperList" /> -->
    <div class="section">
      <el-row>
        <br />
        <h4>有什么新发现告诉大家？</h4>
      </el-row>
      <!-- {{ fileList }} -->
      <el-row>
        <el-input
          class="comment-input"
          type="textarea"
          placeholder="打卡新景点..."
          :rows="2"
          v-model="postForm.text"
        >
        </el-input>
      </el-row>
      <el-row>
        <el-popover placement="bottom" width="400" trigger="click">
          <div>
            上传图片
          </div>
          <el-button
            size="small"
            type="success"
            plain
            slot="reference"
            icon="el-icon-position"
            >景点</el-button
          >
        </el-popover>
        <el-backtop target=".travel-content"></el-backtop>
        <el-popover placement="bottom" trigger="click">
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
            type="danger"
            plain
            slot="reference"
            icon="el-icon-picture"
            >图片</el-button
          >
        </el-popover>
        <el-popover placement="bottom" width="400" trigger="click">
          <div></div>
          <el-button
            size="small"
            type="primary"
            plain
            slot="reference"
            icon="el-icon-third-jinghao"
            >主题</el-button
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
    <div class="section" v-for="(item, index) in songList" :key="index">
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
        </el-col>
      </el-row>
      <el-divider></el-divider>
      <!-- {{ item }} -->
      <el-row class="section-bottom" type="flex" justify="space-between">
        <el-col :span="6"
          ><el-link icon="el-icon-star-off" class="no-text-decoration"
            >收藏</el-link
          ></el-col
        >
        <el-col :span="6"><el-link icon="el-icon-share">转发</el-link></el-col>
        <el-col :span="6"
          ><el-link icon="el-icon-chat-dot-square">评论</el-link></el-col
        >
        <el-col :span="6"
          ><el-link
            icon="el-icon-thumb"
            :style="getThumbColor(item)"
            @click="
              toggleThumb(item, item._links.self.href, item._links.thumb.href)
            "
            >{{
              item.thumb && item.thumb.length > 0 ? item.thumb.length : '赞'
            }}</el-link
          ></el-col
        >
      </el-row>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import { swiperList } from '../assets/data/swiper'
import Swiper from '../components/Swiper'
// import { getSongList } from '../api/index'

export default {
  name: 'home',
  mixins: [mixin],
  components: {
    Swiper
  },
  data () {
    return {
      swiperList: [], // 轮播图列表
      songList: [], // 歌单列表
      singerList: [], // 歌手列表
      posts: '',
      postForm: {
        text: '',
        image: []
      },
      fileList: []
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
  created () {
    this.swiperList = swiperList
    // 获取歌单列表
    this.getPosts()
  },
  methods: {
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

          this.$http.post('/posts', this.postForm).then(
            response => {
              console.log(response.data)
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
            this.songList = response.data._embedded.posts
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
