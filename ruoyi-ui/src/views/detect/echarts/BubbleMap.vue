<template>
  <div class="bubble-map">
    <div class="chart-container" ref="chart"></div>
  </div>
</template>

<script>
import * as echarts from 'echarts';
import axios from 'axios';
import _ from 'lodash';

export default {
  name: 'BubbleMap',

  data() {
    return {
      chart: null,
      option: {
        geo: {
          map: 'world',
          roam: true,
          silent: true,
          zoom: 1.2,
          label: {
            emphasis: {
              show: false
            }
          },
          itemStyle: {
            normal: {
              areaColor: '#323c48',
              borderColor: '#111'
            },
            emphasis: {
              areaColor: '#2a333d'
            }
          }
        },
        series: []
      },
      bubbleData: []
    };
  },

  methods: {
    async fetchData() {
      const response = await axios.get('https://api.covid19api.com/summary');
      const data = response.data;
      const countries = data.Countries;
      const maxConfirmed = _.maxBy(countries, 'TotalConfirmed').TotalConfirmed;

      this.bubbleData = _.map(countries, (country) => {
        return {
          name: country.Country,
          value: [country.CountryCode, country.TotalConfirmed / maxConfirmed, country.TotalDeaths / country.TotalConfirmed]
        };
      });

      this.renderBubbleMap();
    },

    renderBubbleMap() {
      this.option.series.push({
        name: 'COVID-19 Cases',
        type: 'scatter',
        coordinateSystem: 'geo',
        symbolSize: function(val) {
          return val[2] * 60;
        },
        label: {
          formatter: '{b}',
          position: 'right',
          show: false
        },
        itemStyle: {
          color: '#ddb926'
        },
        data: this.bubbleData
      });

      this.chart.setOption(this.option);
    }
  },

  mounted() {
    this.chart = echarts.init(this.$refs.chart);
    this.fetchData();
  },

  beforeDestroy() {
    this.chart.dispose();
  }
};
</script>

<style scoped>
.bubble-map {
  height: 100vh;
}

.chart-container {
  height: 100%;
}
</style>
