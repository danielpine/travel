<template>
  <div class="setting">
    <div class="leftCol">
      <div class="settingsMainHeader">设置</div>
      <ul class="setting-aside" style="width:130px">
        <li
          v-for="(item, index) in settingList"
          :key="index"
          :class="{ activeColor: activeName === item.name }"
          @click="handleClick(item)"
        >
          <i :class="item.icon"> {{ item.name }}</i>
        </li>
      </ul>
    </div>
    <div class="contentCol">
      <component :is="componentSrc"></component>
    </div>
  </div>
</template>

<script>
import Info from '../components/Info'
import Upload from '../components/Upload'
import Favorite from '../components/Favorite'
import MyMoments from '../components/MyMoments'
import MyTopic from '../components/MyTopic'

export default {
  name: 'setting',
  components: {
    Info,
    Upload,
    Favorite,
    MyMoments,
    MyTopic
  },
  data () {
    return {
      activeName: '我的收藏',
      componentSrc: 'Favorite',
      settingList: [
        {
          icon: 'el-icon-star-off',
          name: '我的收藏',
          path: 'Favorite'
        },
        {
          icon: 'el-icon-collection-tag',
          name: '我的动态',
          path: 'MyMoments'
        },
        {
          icon: 'el-icon-connection',
          name: '我的话题',
          path: 'MyTopic'
        },
        {
          icon: 'el-icon-message',
          name: '系统消息',
          path: 'Info'
        },
        {
          icon: 'el-icon-user',
          name: '个人信息',
          path: 'Info'
        },
        {
          icon: 'el-icon-camera',
          name: '修改头像',
          path: 'Upload'
        }
      ]
    }
  },
  mounted () {},
  methods: {
    handleClick (item) {
      this.activeName = item.name
      this.componentSrc = item.path
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/setting.scss';
</style>
