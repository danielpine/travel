<template>
  <div class="info">
    <p class="title">编辑个人资料</p>
    <hr />
    <div class="personal">
      <el-form :model="registerForm" class="demo-ruleForm" label-width="80px">
        <el-form-item prop="name" label="用户名">
          <el-input v-model="registerForm.name" placeholder="用户名"></el-input>
        </el-form-item>
        <el-form-item prop="nickname" label="昵称">
          <el-input
            v-model="registerForm.nickname"
            placeholder="昵称"
          ></el-input>
        </el-form-item>
        <!-- <el-form-item prop="password" label="密码">
          <el-input
            type="password"
            placeholder="密码"
            v-model="registerForm.password"
          ></el-input>
        </el-form-item> -->
        <el-form-item label="性别">
          <el-radio-group v-model="registerForm.sex">
            <el-radio label="女">女</el-radio>
            <el-radio label="男">男</el-radio>
          </el-radio-group>
        </el-form-item>
        <el-form-item prop="mobile" label="手机">
          <el-input placeholder="手机" v-model="registerForm.mobile"></el-input>
        </el-form-item>
        <el-form-item prop="email" label="邮箱">
          <el-input v-model="registerForm.email" placeholder="邮箱"></el-input>
        </el-form-item>
        <!-- <el-form-item prop="birth" label="生日">
          <el-date-picker
            type="date"
            placeholder="选择日期"
            v-model="registerForm.birth"
            style="width: 100%;"
          ></el-date-picker>
        </el-form-item>
        <el-form-item prop="introduction" label="签名">
          <el-input
            type="textarea"
            placeholder="签名"
            v-model="registerForm.introduction"
          ></el-input>
        </el-form-item>
        <el-form-item prop="location" label="地区">
          <el-select
            v-model="registerForm.location"
            placeholder="地区"
            style="width:100%"
          >
            <el-option
              v-for="item in cities"
              :key="item.value"
              :label="item.label"
              :value="item.value"
            >
            </el-option>
          </el-select>
        </el-form-item> -->
      </el-form>
      <div class="btn">
        <div @click="saveMsg()">保存</div>
        <div @click="goback">取消</div>
      </div>
    </div>
  </div>
</template>

<script>
import { mapGetters } from 'vuex'
import mixin from '../mixins'
import { cities } from '../assets/data/form'
import { getUserOfId } from '../api/index'

export default {
  name: 'info',
  mixins: [mixin],
  data: function () {
    return {
      registerForm: {
        // 注册
        name: '',
        nickname: '',
        // password: '',
        sex: '',
        mobile: '',
        email: ''
        // birth: '',
        // introduction: '',
        // location: ''
      },
      cities: []
    }
  },
  computed: {
    ...mapGetters(['userId'])
  },
  created () {
    this.cities = cities
  },
  mounted () {
    this.getMsg(this.userId)
  },
  methods: {
    getMsg (id) {
      getUserOfId(id)
        .then(res => {
          console.log(res)
          this.registerForm.name = res.name
          this.registerForm.nickname = res.nickname
          // this.registerForm.password = res.password
          this.registerForm.sex = res.sex
          this.registerForm.mobile = res.mobile
          this.registerForm.email = res.email
          // this.registerForm.birth = res[0].birth
          // this.registerForm.introduction = res[0].introduction
          // this.registerForm.location = res[0].location
          this.registerForm.avator = res.avator
        })
        .catch(err => {
          console.log(err)
        })
    },
    goback () {
      this.$router.go(-1)
    },
    saveMsg () {
      let _this = this
      this.$http.patch(`/users/${this.userId}`, this.registerForm).then(
        response => {
          console.log(response.data)
          this.getMsg(this.userId)
          this.showError = false
          this.showSuccess = true
          this.$store.commit('setUsername', this.registerForm.name)
          this.$notify.success({
            title: '编辑成功',
            showClose: true
          })
          setTimeout(function () {
            _this.$router.go(-1)
          }, 2000)
        },
        err => {
          console.log(err)
          this.showSuccess = false
          this.showError = true
          this.$notify.error({
            title: '编辑失败',
            showClose: true
          })
        }
      )

      // updateUserMsg(params)
      //   .then(res => {
      //     if (res.code === 1) {
      //       this.showError = false
      //       this.showSuccess = true
      //       this.$store.commit('setUsername', this.registerForm.username)
      //       this.$notify.success({
      //         title: '编辑成功',
      //         showClose: true
      //       })
      //       setTimeout(function () {
      //         this.$router.go(-1)
      //       }, 2000)
      //     } else {
      //       this.showSuccess = false
      //       this.showError = true
      //       this.$notify.error({
      //         title: '编辑失败',
      //         showClose: true
      //       })
      //     }
      //   })
      //   .catch(err => {
      //     console.log(err)
      //   })
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/info.scss';
</style>
