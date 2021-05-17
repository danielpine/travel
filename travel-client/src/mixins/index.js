import {
  getSongOfSingerName,
  getCollectionOfUser,
  updateSongHistory
} from '../api/index'
import { mapGetters } from 'vuex'

const mixin = {
  computed: {
    ...mapGetters(['userId', 'loginIn', 'url'])
  },
  methods: {
    // 提示信息
    notify (title, type) {
      this.$notify({
        title: title,
        type: type
      })
    },
    getImageWidthHeightDynamic (length) {
      switch (length) {
        case 1:
          return { width: '400px', height: '400px' }
        case 2:
        case 4:
          return { width: '300px', height: '300px' }
        default:
          return { width: '200px', height: '200px' }
      }
    },
    // 获取图片信息
    attachImageUrlList (image) {
      let l = []
      image.forEach(e => {
        l.push(this.attachImageUrl(e.url))
      })
      return l
    },
    attachImageUrl (srcUrl) {
      return srcUrl
        ? this.$store.state.configure.HOST + srcUrl || '../assets/img/user.jpg'
        : ''
    },
    attachBirth (val) {
      let birth = String(val).match(/[0-9-]+(?=\s)/)
      return Array.isArray(birth) ? birth[0] : birth
    },
    // 得到名字后部分
    replaceFName (str) {
      let arr = str.split('-')
      return arr[1]
    },
    // 得到名字前部分
    replaceLName (str) {
      let arr = str.split('-')
      return arr[0]
    },
    // 播放
    toplay: function (id, url, pic, index, name, lyric) {
      this.$store.commit('setListIndex', index)
      this.play(id, url, pic, name, lyric)
      if (this.loginIn) {
        this.$store.commit('setIsActive', false)
        getCollectionOfUser(this.userId)
          .then(res => {
            for (let item of res) {
              if (item.songId === id) {
                this.$store.commit('setIsActive', true)
                break
              }
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
    },
    play: function (id, url, pic, name, lyric) {
      url = this.$store.state.configure.HOST + url
      console.log(url, this.url)
      this.$store.commit('setIsPlay', true)
      if (this.loginIn && url && url !== this.url) {
        var params = new URLSearchParams()
        params.append('userId', this.userId)
        params.append('songId', id)
        updateSongHistory(params)
          .then(res => {
            if (res.code === 1) {
              this.notify(res.msg, 'success')
            } else {
              this.$notify.error({
                title: '更新失败',
                showClose: false
              })
            }
          })
          .catch(err => {
            console.log(err)
          })
      }
      this.$store.commit('setId', id)
      this.$store.commit('setUrl', url)
      this.$store.commit('setpicUrl', this.$store.state.configure.HOST + pic)
      this.$store.commit('setTitle', this.replaceFName(name))
      this.$store.commit('setArtist', this.replaceLName(name))
      this.$store.commit('setLyric', this.parseLyric(lyric))
    },
    // 解析歌词
    parseLyric (text) {
      let lines = text.split('\n')
      let pattern = /\[\d{2}:\d{2}.(\d{3}|\d{2})\]/g
      let result = []

      // 对于歌词格式不对的特殊处理
      if (!/\[.+\]/.test(text)) {
        return [[0, text]]
      }

      while (!pattern.test(lines[0])) {
        lines = lines.slice(1)
      }

      lines[lines.length - 1].length === 0 && lines.pop()
      for (let item of lines) {
        let time = item.match(pattern) // 存前面的时间段
        let value = item.replace(pattern, '') // 存歌词
        for (let item1 of time) {
          let t = item1.slice(1, -1).split(':')
          if (value !== '') {
            result.push([parseInt(t[0], 10) * 60 + parseFloat(t[1]), value])
          }
        }
      }
      result.sort(function (a, b) {
        return a[0] - b[0]
      })
      return result
    },
    // 搜索音乐
    getSong () {
      if (!this.$route.query.keywords) {
        this.$store.commit('setListOfSongs', [])
        return
      }
      getSongOfSingerName(this.$route.query.keywords)
        .then(res => {
          if (!res.length) {
            this.$store.commit('setListOfSongs', [])
            this.notify('系统暂无该歌曲', 'warning')
          } else {
            this.$store.commit('setListOfSongs', res)
          }
        })
        .catch(err => {
          console.log(err)
        })
    }
  }
}

export default mixin
