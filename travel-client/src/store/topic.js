const topic = {
  state: {
    topic: {}
  },
  getters: {
    topic: state => state.topic
  },
  mutations: {
    setTopic (state, topic) {
      state.topic = topic
    }
  },
  actions: {}
}

export default topic
