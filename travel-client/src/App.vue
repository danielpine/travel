<template>
  <div id="app" class="app">
    <the-header />
    <router-view class="travel-content" />
    <scroll-top />
  </div>
</template>

<script>
import ScrollTop from './components/ScrollTop'
import TheHeader from './components/TheHeader'
import TheFooter from './components/TheFooter'
import TheAside from './components/TheAside'

export default {
  name: 'App',
  components: {
    ScrollTop,
    TheHeader,
    TheFooter,
    TheAside
  }
}
</script>

<style lang="scss" scoped>
@import './assets/css/app.scss';
.app {
  min-height: 100vh;
}
</style>
