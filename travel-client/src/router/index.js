const router = {
  routes: [
    {
      path: '*',
      redirect: '/404'
    },
    {
      path: '/404',
      component: () => import('@/pages/404.vue')
    },
    {
      path: '/login-in',
      name: 'login-in',
      component: () => import('@/pages/LoginIn')
    },
    {
      path: '/sign-up',
      name: 'sign-up',
      component: () => import('@/pages/SignUp')
    },
    {
      path: '/',
      name: 'home',
      component: () => import('@/pages/Home')
    },
    {
      path: '/topics',
      name: 'topics',
      component: () => import('@/pages/Topics')
    },
    {
      path: '/topic-new',
      name: 'topic-new',
      component: () => import('@/pages/TopicNew')
    },
    {
      path: '/topic-preview',
      name: 'topic-preview',
      component: () => import('@/pages/TopicPreview')
    },
    {
      path: '/topic-view/:postId',
      name: 'topic-view',
      component: () => import('@/pages/TopicView')
    },
    {
      path: '/search',
      name: 'search',
      component: () => import('@/pages/Search')
    },
    {
      path: '/setting',
      name: 'setting',
      component: () => import('@/pages/Setting')
    }
  ],
  scrollBehavior (to, from, savedPosition) {
    return { x: 0, y: 0 }
  }
}

export default router
