<template>
    <div class="bg-white rounded-lg shadow-2x1 w-1/2 justify-center ml-auto mr-auto mt-10">
      <!-- header -->
      <header
        class="
          bg-gray-800
          rounded-t-lg
          py-3
          px-8
          text-xl
          font-extrabold
          text-gray-100
        "
      >
        GRÁFICO
      </header>
      <div class="">
        <div class="mt-20 sm:mt-0">
          <div class="mt-5 md:mt-0 md:col-span-3">
            <!-- <form @submit.prevent="criarOrdem()"> -->
              <div class="px-4 py-5 bg-white sm:p-6">
                <div class="grid grid-cols-6 gap-6">
                  <div class="col-span-6">
                    <label
                      for="country"
                      class="block text-sm font-medium text-gray-700"
                      >Nome da stock:
                    </label>
                    <div class="flex justify-between">
                    <select
                      id="country"
                      name="country"
                      autocomplete="country-name"
                      class="
                        mt-1
                        block
                        w-3/4
                        py-2
                        border border-gray-700
                        bg-white
                        rounded-md
                        shadow-sm
                        focus:outline-none
                        focus:ring-indigo-500
                        focus:border-indigo-500
                        sm:text-sm
                      "
                      v-model="name"
                    >
                      <option selected>---- Selecione uma stock ----</option>
                      <option v-for="stock in stocksName" :key="stock">{{stock.stock_name}}</option>
                    </select>
                    <button class="py-5 px-5 bg-gray-700 hover:text-gray-400 text-gray-100 rounded transition duration-300 no-underline" @click="setup">Buscar</button></div>
                  </div>
                </div>
              </div>

              <!-- </form> -->
          </div>
        </div>
      </div>
      <div id="chart-demo" class=" w-auto mx-3" v-if="mostrar">
    <DxChart
      id="zoomedChart"
      :data-source="dataSource"
      :title="this.teste[0].stock_name + ' stock prices'"
    >
      <DxSeries
        type="candlestick"
        open-value-field="aberto"
        high-value-field="high"
        low-value-field="low"
        close-value-field="fechado"
        argument-field="created_on"
      >
        <DxAggregation :enabled="true"/>
      </DxSeries>
      <DxArgumentAxis
        :visual-range="visualRange"
        :value-margins-enabled="false"
        argument-type="datetime"
      >
        <DxGrid :visible="true"/>
        <DxLabel :visible="false"/>
      </DxArgumentAxis>
      <DxValueAxis value-type="numeric"/>
      <DxMargin :right="10"/>
      <DxLegend :visible="false"/>
      <DxTooltip :enabled="true"/>
    </DxChart>
    <DxRangeSelector
      :data-source="dataSource"
      v-model:value="visualRange"
    >
      <DxSize :height="120"/>
      <DxRsChart>
        <DxRsValueAxis value-type="numeric"/>
        <DxRsSeries
          type="line"
          value-field="aberto"
          argument-field="created_on"
        >
          <DxRsAggregation :enabled="true"/>
        </DxRsSeries>
      </DxRsChart>
      <DxScale
        :placeholder-height="1"
        minor-tick-interval="month"
        tick-interval="month"
        value-type="datetime"
        aggregation-interval="week"
      />
      <DxBehavior
        :snap-to-ticks="false"
        call-value-changed="onMoving"
      />
    </DxRangeSelector>

  </div>
    </div>
</template>
<script>
import axios from "axios";
import DxChart, {
  DxSeries,
  DxAggregation,
  DxArgumentAxis,
  DxGrid,
  DxLabel,
  DxValueAxis,
  DxMargin,
  DxLegend,
  DxTooltip,
} from 'devextreme-vue/chart';

import DxRangeSelector, {
  DxSize,
  DxScale,
  DxChart as DxRsChart,
  DxValueAxis as DxRsValueAxis,
  DxSeries as DxRsSeries,
  DxAggregation as DxRsAggregation,
  DxBehavior,
} from 'devextreme-vue/range-selector';


export default {
  components: {
    DxChart,
    DxSeries,
    DxAggregation,
    DxArgumentAxis,
    DxGrid,
    DxLabel,
    DxValueAxis,
    DxMargin,
    DxLegend,
    DxTooltip,
    DxRangeSelector,
    DxSize,
    DxScale,
    DxRsChart,
    DxRsValueAxis,
    DxRsSeries,
    DxRsAggregation,
    DxBehavior,
  },
  data() {
    return {
      dataSource: [],
      visualRange: [],
      stocksName: [],
      name: '---- Selecione uma stock ----',
      mostrar: false,
    };
  },

    created() {
    this.stocksGrafico();
  },

  methods: {
     async setup() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get(`http://localhost:8085/${this.name}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.teste = response.data;
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid, "tipo: " + this.type) 
        } catch (error) {
          this.walletUser = `${error}`;
        }
        try {
        let response = await axios.get(
          `http://localhost:8085/historico/${this.teste[0].id}`,

          {
            headers: { Authorization: "Bearer " + accessToken },
          }
        );
        this.dataSource = response.data
        console.log(this.dataSource)
      } catch (error) {
        this.id = `${error}`;
      }  
      }
      this.mostrar = true
    },
    async stocksGrafico() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get("http://localhost:8085/stocks", {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.stocksName = response.data;
          console.log('teste')
        } catch (error) {
          this.stocksName = `${error}`;
        }
      }
    },
  }
  
  
};
</script>
<style>
/* #chart-demo {
  height: 450px;
}

#zoomedChart {
  height: 315px;
  margin: 0 0 15px;
}

#chart-demo > div:not(#zoomedChart) {
  height: 12px;
} */
</style>