```
// 0. If using a module system (e.g. via vue-cli), import Vue and VueRouter
// and then call `Vue.use(VueRouter)`.

// 1. Define route components.
// These can be imported from other files
const Foo = { template: '<div>foo</div>' }
const Bar = { template: '<div>bar</div>' }

// 2. Define some routes
// Each route should map to a component. The "component" can
// either be an actual component constructor created via
// `Vue.extend()`, or just a component options object.
// We'll talk about nested routes later.
const routes = [
  { path: '/foo', component: Foo },
  { path: '/bar', component: Bar }
]

// 3. Create the router instance and pass the `routes` option
// You can pass in additional options here, but let's
// keep it simple for now.
const router = new VueRouter({
  routes // short for `routes: routes`
})

// 4. Create and mount the root instance.
// Make sure to inject the router with the router option to make the
// whole app router-aware.
const app = new Vue({
  router
}).$mount('#app')

// Now the app has started!
```
第三步 3. Create the router instance and pass the `routes` option
```
const router = new VueRouter({
  routes // short for `routes: routes`
})
```
这里面的 route是必须命名为 ‘routes’, 如果不命名为 ‘routes’ 则需要用 routes : xxxx 的方式命名

同理 第四步的 Create and mount the root instance.
```
const app = new Vue({
  router
}).$mount('#app')
```
这里的变量也需要命名为 router, 其他命名或者匿名写法都是不行的
