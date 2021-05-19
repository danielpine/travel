<template>
  <div class="form-info">
    <base-map
      ref="mapSearch"
      :city="mapForm.city"
      :value="mapForm.address"
      :longitude="mapForm.lng"
      :latitude="mapForm.lat"
      :isEdit="isEdit"
      @updateLocation="updateLocation"
    />
    <!-- <el-form
      :model="mapForm"
      ref="mapForm"
      :rules="rules"
      size="small"
      label-width="110px"
    >
      <el-row>
        <el-col :span="12">
          <el-form-item prop="addLon" label="经度">
            <el-input
              v-model.number="mapForm.addLon"
              :maxlength="15"
              placeholder="请输入经度"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12" class="right-label-form-item">
          <el-form-item prop="addLat" label="纬度">
            <el-input
              v-model.number="mapForm.addLat"
              :maxlength="15"
              placeholder="请输入纬度"
            ></el-input>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form> -->
  </div>
</template>
<script>
import BaseMap from './BaseMap'
export default {
  props: ['visible', 'isEdit', 'detail', 'mapForm'],
  components: {
    BaseMap
  },
  data () {
    return {
      title: '添加地址',
      rules: {
        address: [
          {
            required: true,
            message: '请输入地址',
            trigger: ['blur', 'change']
          }
        ],
        addLat: [
          {
            required: true,
            message: '请输入纬度',
            trigger: ['blur', 'change']
          }
        ],
        addLon: [
          {
            required: true,
            message: '请输入经度',
            trigger: ['blur', 'change']
          }
        ]
      }
    }
  },
  created () {
    if (this.isEdit) {
      this.initForm()
    }
  },
  methods: {
    // 初始化表单
    initForm () {
      this.title = '修改地址'
      if (this.detail) {
        this.form = { ...this.detail }
      }
    },
    // 地图搜索选址
    updateLocation (lng, lat, address) {
      console.log(arguments)
      this.mapForm.lng = lng
      this.mapForm.lat = lat
      this.mapForm.address = address
    },
    handleClose () {
      this.$emit('update:visible', false)
    }
  }
}
</script>
