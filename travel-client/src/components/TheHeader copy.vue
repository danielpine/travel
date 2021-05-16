<template>
  <div class="the-header" align="center">
    <el-menu
      :default-active="activeIndex"
      class="el-menu-demo"
      mode="horizontal"
      @select="handleSelect"
    >
      <el-menu-item
        ><div class="header-logo" @click="goHome">
          <svg class="icon" aria-hidden="true">
            <use xlink:href="#icon-music"></use>
          </svg>
          {{ musicName }}
        </div></el-menu-item
      >
      <el-menu-item
        ref="change"
        :class="{ active: item.name === activeName }"
        v-for="item in navMsg"
        :key="item.path"
        @click="goPage(item.path, item.name)"
      >
        {{ item.name }}
      </el-menu-item>
      <el-menu-item>
        <div class="header-search">
          <input
            type="text"
            placeholder="请输入搜索关键字"
            @keyup.enter="goSearch()"
            v-model="keywords"
          />
          <div class="search-btn" @click="goSearch()">
            <svg class="icon" aria-hidden="true">
              <use xlink:href="#icon-sousuo"></use>
            </svg>
          </div>
        </div>
      </el-menu-item>
      <el-menu-item v-show="!loginIn">
        <ul class="navbar" style="float:right">
          <li
            v-show="!loginIn"
            :class="{ active: item.name === activeName }"
            v-for="item in loginMsg"
            :key="item.type"
            @click="goPage(item.path, item.name)"
          >
            {{ item.name }}
          </li>
        </ul>
      </el-menu-item>
      <el-menu-item v-show="loginIn">
        <div class="header-right" v-show="loginIn">
          <div id="user">
            <img :src="attachImageUrl(avator)" alt="" />
          </div>
          <div class="menu">
            <li
              v-for="(item, index) in menuList"
              :key="index"
              @click="goMenuList(item.path)"
            >
              {{ item.name }}
            </li>
          </div>
        </div>
      </el-menu-item>
    </el-menu>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import { navMsg, loginMsg, menuList } from '../assets/data/header'

export default {
  name: 'the-header',
  mixins: [mixin],
  data () {
    return {
      musicName: '网红景点打卡平台',
      navMsg: [], // 左侧导航栏
      loginMsg: [], // 右侧导航栏
      menuList: [], // 用户下拉菜单项
      keywords: ''
    }
  },
  computed: {
    ...mapGetters(['userId', 'activeName', 'avator', 'username', 'loginIn'])
  },
  created () {
    this.navMsg = navMsg
    this.loginMsg = loginMsg
    this.menuList = menuList
  },
  mounted () {
    document.querySelector('#user').addEventListener(
      'click',
      function (e) {
        document.querySelector('.menu').classList.add('show')
        e.stopPropagation() // 关键在于阻止冒泡
      },
      false
    )
    // 点击“菜单”内部时，阻止事件冒泡。(这样点击内部时，菜单不会关闭)
    document.querySelector('.menu').addEventListener(
      'click',
      function (e) {
        e.stopPropagation()
      },
      false
    )
    document.addEventListener(
      'click',
      function () {
        document.querySelector('.menu').classList.remove('show')
      },
      false
    )
  },
  methods: {
    goHome () {
      this.$router.push({ path: '/' })
    },
    goPage (path, value) {
      document.querySelector('.menu').classList.remove('show')
      this.changeIndex(value)
      if (!this.loginIn && path === '/my-music') {
        this.notify('请先登录', 'warning')
      } else {
        this.$router.push({ path: path })
      }
    },
    changeIndex (value) {
      this.$store.commit('setActiveName', value)
    },
    goMenuList (path) {
      if (path === 0) {
        this.$store.commit('setIsActive', false)
      }
      document.querySelector('.menu').classList.remove('show')
      if (path) {
        this.$router.push({ path: path })
      } else {
        this.$store.commit('setLoginIn', false)
        this.$router.go(0)
      }
    },
    goSearch () {
      this.$store.commit('setSearchword', this.keywords)
      this.$router.push({ path: '/search', query: { keywords: this.keywords } })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/the-header.scss';
.el-menu-item {
  float: inherit;
  height: 60px;
  line-height: 60px;
  margin: 0;
  border-bottom: 2px solid transparent;
  color: #909399;
}
</style>
