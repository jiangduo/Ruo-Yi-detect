<template>
  <div>
    <form @submit.prevent="generateGraph">
      <input type="number" v-model.number="x" required>
      <input type="number" v-model.number="y" required>
      <button type="submit">Generate Graph</button>
    </form>
    <div v-if="image">
      <img :src="image" alt="Graph">
    </div>
  </div>
</template>

<script>
export default {
  data() {
    return {
      x: null,
      y: null,
      image: null
    };
  },
  methods: {
    async generateGraph() {
      const response = await fetch('/direct/graph', {
        method: 'POST',
        headers: {
          'Content-Type': 'application/json'
        },
        body: JSON.stringify({ x: this.x, y: this.y })
      });
      const result = await response.json();
      this.image = result.image;
    }
  }
};
</script>
