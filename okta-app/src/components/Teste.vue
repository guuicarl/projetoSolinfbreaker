<template>
  <div class="py-40 md:w-auto">
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-1/2 justify-center ml-auto mr-auto">
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
        BUY STOCK
      </header>
      <div class="p-6">
        <div class="mt-20 sm:mt-0">
          <div class="mt-5 md:mt-0 md:col-span-3">
            <!-- <form @submit.prevent="criarOrdem()"> -->
              <div class="px-4 py-5 bg-white sm:p-6">
                <div class="grid grid-cols-6 gap-6">
                  <div class="col-span-6">
                    <label
                      for="country"
                      class="block text-sm font-medium text-gray-700"
                      >Stock Name:
                    </label>
                    <select
                      id="country"
                      name="country"
                      autocomplete="country-name"
                      class="
                        mt-1
                        block
                        w-full
                        py-2
                        px-3
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
                      @click="getMoeda"
                    >
                      <option>---- Select a stock ----</option>
                      <option v-for="stock in stocksName" :key="stock">{{stock.stock_name}}</option>
                    </select>
                  </div>

                  <div class="col-span-6">
                    <label
                      for="country"
                      class="block text-sm font-medium text-gray-700"
                      >Type of transaction
                    </label>
                    <select
                      id="country"
                      name="country"
                      autocomplete="country-name"
                      class="
                        mt-1
                        block
                        w-full
                        py-2
                        px-3
                        border border-gray-700
                        bg-white
                        rounded-md
                        shadow-sm
                        focus:outline-none
                        focus:ring-indigo-500
                        focus:border-indigo-500
                        sm:text-sm
                      "
                      v-model="type"
                    >
                      <option>---- Select a type ----</option>
                      <option value="0">Compra</option>
                      <option value="1">Venda</option>
                    </select>
                  </div>

                  <div class="col-span-6">
                    <label
                      for="last-name"
                      class="block text-sm font-medium text-gray-700"
                      >Bid Price:
                    </label>
                    <input
                      type="text"
                      name="last-name"
                      id="last-name"
                      autocomplete="family-name"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                      v-model="bid"
                    />
                  </div>

                  <div class="col-span-6">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      >Volume:
                    </label>
                    <input
                      type="text"
                      name="email-address"
                      id="email-address"
                      autocomplete="email"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                      v-model="volume"
                    />
                  </div>

                  <div class="col-span-6">
                    <label
                      for="street-address"
                      class="block text-sm font-medium text-gray-700"
                      >Total:
                    </label>
                    <input
                      type="text"
                      name="street-address"
                      id="street-address"
                      autocomplete="street-address"
                      class="
                        mt-1
                        focus:ring-indigo-500 focus:border-indigo-500
                        block
                        w-full
                        shadow-sm
                        lg:text-lg
                        border border-gray-500
                        rounded-md
                      "
                    />
                  </div>
                </div>
              </div>
              <div class="px-4 py-3 bg-gray-50 text-right sm:px-6">
                <button
                  type="submit"
                  class="
                    inline-flex
                    justify-center
                    py-2
                    px-4
                    border border-transparent
                    shadow-sm
                    text-sm
                    font-medium
                    rounded-md
                    text-white
                    bg-indigo-600
                    hover:bg-indigo-700
                    focus:outline-none
                    focus:ring-2
                    focus:ring-offset-2
                    focus:ring-indigo-500
                  "
                  @click="criarOrdem()"
                >
                  BUY
                </button>
              </div>
              <!-- </form> -->
          </div>
        </div>
      </div>
    </div>
    
  </div>
</template>

<script>
import axios from "axios";
import {VMoney} from 'v-money'


export default {
  data: function () {
    return {
      stocksName: [],
      name: '',
      name1: '',
      bid: '',
      sell: '',
      volume: '',
      volume2: '',
      total: '',
      type: '',
      teste: [],
      usuario: 1,
      money: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          precision: 2,
          masked: false /* doesn't work with directive */
        }
    };
  },
  created() {
    this.setup();
    this.getMoeda;
  },
  methods: {
    async setup() {
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
    async getMoeda() {
      console.log("to aquiiiiiiiiii")
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
      }
    },
    async criarOrdem() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          await axios.post(`http://localhost:8082/orders`, 
          {id_user: this.usuario, id_stock: this.teste[0].id, stock_symbol: this.teste[0].stock_symbol, stock_name: this.name, volume: this.volume, price: this.bid, type: this.type, status: 1, remaining_value: this.volume},
          {
            headers: { Authorization: "Bearer " + accessToken },
          })
          .then(() => {
            window.alert("Cadastrado com sucesso")
          })
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid) 
        } catch (error) {
          console.log(error);
        }
      }
    },

    async criarOrdemVenda() {
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          await axios.post(`http://localhost:8082/orders`, 
          {id_user: this.usuario, id_stock: this.teste[0].id, stock_symbol: this.teste[0].stock_symbol, stock_name: this.name, volume: this.volume, price: this.bid, type: 0, status: 1, remaining_value: this.volume},
          {
            headers: { Authorization: "Bearer " + accessToken },
          })
          .then(() => {
            window.alert("Cadastrado com sucesso")
          })
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid) 
        } catch (error) {
          console.log(error);
        }
      }
    }
  },
        directives: {money: VMoney}
};
</script>