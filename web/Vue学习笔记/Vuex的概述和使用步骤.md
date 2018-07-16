安装
```
npm install --save vuex
```

### create a store
```
import Vue from 'vue';
import Vuex from 'vuex';

Vue.use(Vuex); // 注册插件 install方法


// Make sure to call Vue.use(Vuex) first if using a module system

const store = new Vuex.Store({
  state: {
    count: 0
  },
  mutations: {// 唯一可以改变state里面的值的方法
    increment (state) {
      state.count++
    }
  }
})

export default store
```

#### mutations
The only way to actually change state in a Vuex store is by committing a mutation
```
const store = new Vuex.Store({
    state: {
        count:0
    },
    mutations: {
        // 加1
        INCREMENT(state) {
            state.count++;
        },
        // 减1
        DECREMENT(state) {
            state.count--
        }
    }
})
```

#### Action
Action 类似于 mutation，不同在于：

* Action 提交的是 mutation，而不是直接变更状态。
* Action 可以包含任意异步操作。mutations必须是同步的

##### 分发 Action
Action 通过 store.dispatch 方法触发：
```
store.dispatch('increment')
```
