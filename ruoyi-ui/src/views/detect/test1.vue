
<!-- 文件名：App.vue -->
<template>
  <div>
    <h1>折线图示例</h1>
    <img :src="image" alt="折线图">
    <button @click="showPlot">生成折线图</button>
  </div>
</template>

<script>
import axios from 'axios'

export default {
  name: 'App',
  data() {
    return {
      image: ''
    }
  },
  methods: {
    showPlot() {
      axios.get('/test/plot')
        .then(response => {
          this.image = response.data.image
        })
        .catch(error => {
          console.log(error)
        })
    }
  }
}
</script>
<!--<template>-->
<!--  <div>-->
<!--    <form @submit.prevent="handleSubmit">-->
<!--      <label for="input1">Input 1:</label>-->
<!--      <input type="text" id="input1" v-model="input1" />-->
<!--      <br />-->
<!--      <label for="input2">Input 2:</label>-->
<!--      <input type="text" id="input2" v-model="input2" />-->
<!--      <br />-->
<!--      <button type="submit">Submit</button>-->
<!--    </form>-->
<!--    <img :src="imageUrl" v-if="imageUrl" />-->
<!--    <p v-else>No image to display</p>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--import axios from 'axios';-->

<!--export default {-->
<!--  data() {-->
<!--    return {-->
<!--      input1: '',-->
<!--      input2: '',-->
<!--      imageUrl: '',-->
<!--    };-->
<!--  },-->
<!--  methods: {-->
<!--    async handleSubmit() {-->
<!--      const response = await axios.post('/test/add', {-->
<!--        input1: this.input1,-->
<!--        input2: this.input2,-->
<!--      });-->
<!--      this.imageUrl = `data:image/png;base64,${response.data}`;-->
<!--    },-->
<!--  },-->
<!--};-->
<!--</script>-->
