<template>
  <div class="home">
    <div class="section" style="padding-top:30px;width:100%">
      <el-row
        ><h1>{{ topic.title }}</h1></el-row
      >
      <el-row class="section-content" style="padding-top:30px">
        <el-col :span="1">
          <div class="section-user">
            <img :src="attachImageUrl(avator)" alt="" />
          </div>
        </el-col>
        <el-col :span="21" :offset="1">
          <el-row
            ><el-col :span="2">来自 :</el-col
            ><el-col :span="6">
              <h4>
                {{ username }}
              </h4>
            </el-col></el-row
          >
          <el-row class="section-timestamp">{{ topic.postTime }}</el-row>
          <el-row>
            <div class="ql-container ql-snow">
              <div class="ql-editor" v-html="topic.text"></div>
            </div>
          </el-row>
          <el-row style="margin-top:30px">#{{ topic.topic }}#</el-row>
        </el-col>
      </el-row>
    </div>
  </div>
</template>

<script>
import mixin from '../mixins'
import { mapGetters } from 'vuex'
export default {
  mixins: [mixin],
  props: ['topic'],
  computed: {
    ...mapGetters(['username', 'avator'])
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/home.scss';
</style>
