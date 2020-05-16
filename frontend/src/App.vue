<template>
  <div id="app">
    <div id="nav">
      <router-link to="/">Home</router-link> |
       <router-link to="/employee">Employee</router-link> |
      <router-link to="/callservice">Service</router-link> |
      <router-link :to="{name:'Bootstrap'}">Bootstrap</router-link> |
     <router-link to="/about">About</router-link> |
      <!-- <router-link to="/user">User</router-link> |-->
      <router-link to="/login">Login</router-link> |
      <router-link to="/protected">Protected</router-link>
    </div>
    <router-view/>
    <version/>
  </div>
</template>

<script>
import Version from '@/components/Version'
export default {
  components: { Version }
}
</script>

<style lang="scss">
#app {
  font-family: Avenir, Helvetica, Arial, sans-serif;
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: #2c3e50;
}

#nav {
  padding: 30px;

  a {
    font-weight: bold;
    color: #2c3e50;

    &.router-link-exact-active {
      color: #42b983;
    }
  }
}
</style>
