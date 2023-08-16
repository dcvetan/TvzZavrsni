<template>
  <apexchart :options="options" :series="series"  type="donut" />
</template>

<script>
import { ref } from 'vue';
import VueApexCharts from 'vue3-apexcharts';

export default {
  components: {
    apexchart: VueApexCharts,
  },
  props: {
    chartOptions: Object,
    chartSeries: Array
  },
  setup(props) {
    const options = ref(props.chartOptions);
    const series = ref(props.chartSeries);

    return {
      options,
      series,
    };
  },
};
</script>
