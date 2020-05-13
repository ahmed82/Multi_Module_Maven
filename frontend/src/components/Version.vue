<template>
    <div id=footer >
        &copy; {{apiversion}} Ahmed AlSalih
    </div>
</template>

<script>
import axios from '../config/axios'
// const instance = axios.create({
//   auth: {
//     username: 'admin',
//     password: '123'
//   }
// })
export default {
  data () {
    return {
      apiversion: ''
    }
  },
  async mounted () {
    const responds = await axios.get('/util/version')
    this.apiversion = responds.data
  }
}
</script>

<style scoped>
  /* html, body {
  height: 100%;
}*/

#footer {
  font-size: 0.8em;
  /* position: absolute;
  bottom: 0; */
  width: 100%;
  height: 2.0rem;            /* Footer height */
}
</style>
