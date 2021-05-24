<template>
  <div class="search">
    <nav class="searchList-nav" ref="change">
      <span
        :class="{ isActive: toggle === 'search' }"
        @click="handleChangeView('search', 0)"
        >1</span
      >
      <span
        :class="{ isActive: toggle === 'searchLists' }"
        @click="handleChangeView('searchLists', 1)"
        >2</span
      >
    </nav>
    <component :is="currentView"></component>
  </div>
</template>

<script>
import search from '../components/search/Search'
import searchLists from '../components/search/SearchLists'
import { mapGetters } from 'vuex'
import mixin from '../mixins'

export default {
  name: 'search',
  mixins: [mixin],
  components: {
    search,
    searchLists
  },
  data () {
    return {
      toggle: 'search',
      currentView: 'search'
    }
  },
  computed: {
    ...mapGetters(['searchword'])
  },
  watch: {
    searchword: function () {
      this.getSong()
    }
  },
  methods: {
    handleChangeView: function (component) {
      this.currentView = component
      this.toggle = component
    }
  }
}
</script>

<style lang="scss" scoped>
@import '../assets/css/search.scss';
</style>
