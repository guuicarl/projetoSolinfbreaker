<template>
  <div class="py-40 flex w-full md:w-auto">
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-5/12 mr-14 ml-20">
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
      <div class="p-8">
        <div class="mt-10 sm:mt-0">
          <div class="mt-5 md:mt-0 md:col-span-2">
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
                    >
                      <option>---- Select a stock ----</option>
                      <option v-for="stock in stocksName" :key="stock">{{stock.stock_name}}</option>
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
                      v-money="money"
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
                  @click="getMoeda()"
                >
                  BUY
                </button>
              </div>
          </div>
        </div>
      </div>
    </div>

    <div class="bg-white rounded-lg shadow-2x1 w-5/12 ml-20">
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
        SELL STOCK
      </header>
      <div class="p-8">
                <div class="mt-10 sm:mt-0">
          <div class="mt-5 md:mt-0 md:col-span-2">
            <form>
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
                      v-model="name1"
                    >
                      <option>---- Select a stock ----</option>
                      <option v-for="stock in stocksName" :key="stock">{{stock.stock_name}}</option>
                    </select>
                  </div>

                  <div class="col-span-6">
                    <label
                      for="last-name"
                      class="block text-sm font-medium text-gray-700"
                      >Ask Price:
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
                >
                  SELL
                </button>
              </div>
            </form>
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
      volume: '',
      total: '',
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
      this.mostrar = true
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.get(`http://localhost:8085/${this.name}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.teste = response.data;
          console.log("id: " + this.teste[0].id + " Symbol: " + this.teste[0].stock_symbol + " Nome: " + this.name + " Volume: " + this.volume  + " Preço: " + this.bid) 
        } catch (error) {
          this.walletUser = `${error}`;
        }
      }
    },
    async criarOrdem() {
      this.mostrar = true
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        try {
          let response = await axios.post(`http://localhost:8082/orders`, 
          {id_user: this.usuario, id_stock: this.teste[0].id, stock_symbol: this.teste[0].stock_symbol, stock_name: this.name, volume: this.volume, price: this.bid, type: 1, status: 1},
          {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.teste = response.data;
          console.log(this.teste[0].id)
        } catch (error) {
          this.walletUser = `${error}`;
        }
      }
    }
  },
        directives: {money: VMoney}
};
</script>