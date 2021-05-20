<template>
  <div>
    <div class="editor-title">
      <textarea
        class="editor-input"
        tabindex="1"
        maxlength="70"
        placeholder="添加标题"
        rows="1"
        style="resize: none; overflow: hidden; height: 22px;margin:10px 0"
      ></textarea>
    </div>
    <el-divider></el-divider>
    <quill-editor
      class="editor"
      ref="myTextEditor"
      v-model="content"
      :options="editorOption"
      @blur="onEditorBlur($event)"
      @focus="onEditorFocus($event)"
      @ready="onEditorReady($event)"
      @change="onEditorChange($event)"
    >
    </quill-editor>
  </div>
</template>
<script>
export default {
  data () {
    return {
      content: null,
      editorOption: {
        modules: {
          toolbar: [
            ['bold', 'italic', 'underline', 'strike'], // 加粗 斜体 下划线 删除线
            ['blockquote', 'code-block'], // 引用  代码块
            [{ header: 1 }, { header: 2 }], // 1、2 级标题
            [{ list: 'ordered' }, { list: 'bullet' }], // 有序、无序列表
            [{ script: 'sub' }, { script: 'super' }], // 上标/下标
            [{ indent: '-1' }, { indent: '+1' }], // 缩进
            // [{'direction': 'rtl'}],                         // 文本方向
            [{ size: ['small', false, 'large', 'huge'] }], // 字体大小
            [{ header: [1, 2, 3, 4, 5, 6, false] }], // 标题
            [{ color: [] }, { background: [] }], // 字体颜色、字体背景颜色
            [{ font: [] }], // 字体种类
            [{ align: [] }], // 对齐方式
            ['clean'], // 清除文本格式
            ['link', 'image', 'video'] // 链接、图片、视频
          ] // 工具菜单栏配置
        },
        placeholder: '请在这里输入文字', // 提示
        readyOnly: false, // 是否只读
        theme: 'snow', // 主题 snow/bubble
        syntax: true // 语法检测
      }
    }
  },
  methods: {
    // 失去焦点
    onEditorBlur (editor) {},
    // 获得焦点
    onEditorFocus (editor) {},
    // 开始
    onEditorReady (editor) {},
    // 值发生变化
    onEditorChange (editor) {
      this.content = editor.html
      console.log(editor)
    }
  },
  computed: {
    editor () {
      return this.$refs.myTextEditor.quillEditor
    }
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
.editor-input {
  padding: 0 10px;
  box-sizing: border-box;
  font-size: 0.928em;
  background: #fff;
  -webkit-appearance: none;
  width: 100%;
  padding: 0 10px;
  font-size: 1.3333em;
  font-weight: 600;
  line-height: 1.42;
  border: 0;
  background-color: none;
}

textarea {
  outline: none;
}
</style>
