<template>
  <el-popover
    placement="bottom"
    width="350"
    trigger="manual"
    v-model="item.visible"
    @blur="item.visible = !item.visible"
  >
    <div>
      <h4 class="mod-title">请选择举报的类型</h4>
      <br />
      <el-radio
        v-for="(l, i) in labels"
        :key="i"
        v-model="item.radio"
        :label="l"
        >{{ l }}</el-radio
      >
      <br />
      <br />
      <div align="right">
        <el-button
          type="primary"
          plain
          size="small"
          style="margin-right:10px;"
          @click="item.visible = !item.visible"
          >取消</el-button
        >
        <el-button
          type="warning"
          size="small"
          :disabled="item.radio === undefined"
          @click="report(item)"
          >提交</el-button
        >
      </div>
    </div>
    <el-button
      slot="reference"
      type="text"
      icon="el-icon-warning-outline"
      style="color:#808080"
      @click="item.visible = !item.visible"
      >举报</el-button
    >
  </el-popover>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'

export default {
  mixins: [mixin],
  props: ['item'],
  data: function () {
    return {
      labels: ['垃圾营销', '涉黄信息', '人身攻击', '有害信息', '违法信息']
    }
  },
  computed: {
    ...mapGetters(['userId'])
  },
  methods: {
    report (item) {
      if (this.loginIn) {
        this.$http
          .post('/reports', {
            user: `http://localhost:8080/users/${this.userId}`,
            post: `http://localhost:8080/posts/${item.id}`,
            reportType: item.radio
          })
          .then(
            response => {
              this.notify('举报成功', 'success')
            },
            err => {
              console.log(err)
              this.notify('请勿重复举报', 'error')
            }
          )
        item.visible = false
      } else {
        this.notify('请先登录', 'warning')
      }
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
