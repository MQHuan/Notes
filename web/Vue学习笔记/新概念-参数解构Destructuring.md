#### 参数解构 Destructuring
```
const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {
    increment (state) {
      state.count++
    }
  },
  actions: {
    increment (context) {
      context.commit('increment')
    }
  }
})
```
中的actions 解构写法为：
```
actions: {
  increment ({ commit }) {
    commit('increment')
  }
}
```
