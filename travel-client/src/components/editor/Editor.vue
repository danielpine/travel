<template>
  <div>
    {{ postForm }}
    <el-form
      :model="postForm"
      :rules="rules"
      ref="postForm"
      label-width="0"
      size="mini"
    >
      <div class="editor-title">
        <el-row type="flex" justify="space-between">
          <el-col :span="20"
            ><el-form-item prop="title"
              ><el-input
                placeholder="添加标题"
                v-model="postForm.title"
                style="font-size: 1.3333em; font-weight: 600;"
              ></el-input></el-form-item
          ></el-col>
          <el-col :span="2"
            ><el-button
              type="text"
              size="small"
              class="sub-btn pull-right"
              @click="preview()"
              >预览</el-button
            ></el-col
          ><el-col :span="2">
            <el-button
              type="success"
              size="small"
              class="sub-btn pull-right"
              @click="submitForm('postForm')"
              >发表</el-button
            ></el-col
          >
        </el-row>
        <el-row type="flex" justify="space-between">
          <el-col :span="24"
            ><el-form-item prop="topic"
              ><el-input
                placeholder="添加话题"
                v-model="postForm.topic"
              ></el-input></el-form-item
          ></el-col>
        </el-row>
      </div>
      <el-divider></el-divider>
      <el-form-item prop="text">
        <quill-editor
          ref="editor"
          v-model="postForm.text"
          :options="editorOption"
          @blur="onEditorBlur($event)"
          @focus="onEditorFocus($event)"
          @ready="onEditorReady($event)"
          @change="onEditorChange($event)"
        >
        </quill-editor
      ></el-form-item>
    </el-form>
  </div>
</template>
<script>
import { mapGetters } from 'vuex'
import mixin from '../../mixins'

export default {
  mixins: [mixin],
  data () {
    return {
      postForm: this.initPostForm('moments'),
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'underline'], // 加粗 斜体 下划线 删除线 , 'italic', 'underline', 'strike'
            // ['blockquote', 'code-block'], // 引用  代码块
            [{ header: 1 }, { header: 2 }], // 1、2 级标题
            [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
            // [{ script: 'sub' }, { script: 'super' }], // 上标/下标
            // [{ indent: '-1' }, { indent: '+1' }], // 缩进
            // [{'direction': 'rtl'}],                         // 文本方向
            // [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
            // [// { header: [1, 2, false] } // // { size: ['small', false, 'large', 'huge'] }
            // ], // 标题
            [{ color: [] }], // 字体颜色、字体背景颜色 , { background: [] }
            // [{ font: [] }], // 字体种类
            [{ align: [] }], // 对齐方式
            // ['clean'], // 清除文本格式
            ['image'] // 链接、图片、视频'link', 'video'
          ] // 工具菜单栏配置
        },
        placeholder: '请在这里输入文字', // 提示
        readyOnly: false, // 是否只读
        theme: 'snow', // 主题 snow/bubble
        syntax: true // 语法检测
      },
      rules: {
        title: [{ required: true, message: '请输入标题', trigger: 'blur' }],
        topic: [{ required: true, message: '请输入话题', trigger: 'blur' }],
        text: [{ required: true, message: '请输入内容', trigger: 'change' }]
      }
    }
  },
  methods: {
    submitForm (formName) {
      this.$refs[formName].validate(valid => {
        if (valid) {
          alert('submit!')
        } else {
          console.log('error submit!!')
          return false
        }
      })
    },
    // 失去焦点
    onEditorBlur (editor) {},
    // 获得焦点
    onEditorFocus (editor) {},
    // 开始
    onEditorReady (editor) {},
    // 值发生变化
    onEditorChange (editor) {
      this.$set(this.postForm, 'text', editor.html)
      this.$refs['postForm'].validate(() => {})
      console.log(editor)
    }
  },
  computed: {
    editor () {
      return this.$refs.editor.quillEditor
    },
    ...mapGetters([
      'id',
      'userId', // 用户ID
      'index', // 列表中的序号
      'loginIn', // 用户是否登录
      'avator' // 用户头像
    ])
  },
  mounted () {
    console.log('this is my editor', this.editor)
  }
}
</script>

<style lang="scss" scoped>
@import '../../assets/css/editor.scss';
</style>

<style lang="css" scoped>
.pull-right {
  float: right;
}
</style>
