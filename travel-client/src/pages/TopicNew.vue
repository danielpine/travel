<template>
  <div class="container">
    <div class="topic-section">
      <editor></editor>
    </div>
    <!-- <div class="section song-list">
      <ul class="song-list-header">
        <li
          v-for="(item, index) in songStyle"
          :key="index"
          :class="{ active: item.name === activeName }"
          @click="handleChangeView(item.name)"
        >
          {{ item.name }}
        </li>
      </ul>
      <div class="song-content">
        <content-list :contentList="data" path="song-list-album"></content-list>
        <div class="pagination">
          <el-pagination
            @current-change="handleCurrentChange"
            background
            layout="total, prev, pager, next"
            :current-page="currentPage"
            :page-size="pageSize"
            :total="albumDatas.length"
          >
          </el-pagination>
        </div>
      </div>
    </div> -->
  </div>
</template>

<script>
import ContentList from '../components/ContentList'
import Editor from '../components/editor/Editor'
import { mapGetters } from 'vuex'
import { songStyle } from '../assets/data/songList'

export default {
  name: 'song-list',
  components: {
    ContentList,
    Editor
  },
  data () {
    return {
      songStyle: [], // 歌单导航栏类别
      activeName: '全部歌单',
      pageSize: 15, // 页数
      currentPage: 1, // 当前页
      albumDatas: [] // 歌单
    }
  },
  computed: {
    ...mapGetters(['songsList']),
    // 计算当前表格中的数据
    data () {
      return this.albumDatas.slice(
        (this.currentPage - 1) * this.pageSize,
        this.currentPage * this.pageSize
      )
    }
  },
  mounted () {
    this.songStyle = songStyle
  },
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
