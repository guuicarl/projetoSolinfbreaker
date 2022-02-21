<template>
    <div class="bg-gray-300 py-40 flex justify-center">
    <!-- card vem aqui -->
    <div class="bg-white rounded-lg shadow-2x1 w-1/2">
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
        WALLET
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
                      v-model="teste"
                    >
                      <option selected>---- Select a stock ----</option>
                      <option v-for="wallet in wallets" :key="wallet" v-on:click="getMoeda()">{{wallet.stock_name}}</option>
                    </select>
                  </div>



                  <div class="col-span-6">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Volume:
                    </label>
                    <input
                    v-model="walletUser[0].volume"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                    />
                  </div>

                  <div class="col-span-1">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Bid min:
                    </label>
                    <input
                    v-model="stock[0].bid_min"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                      v-money="money"
                    />
                  </div>

                  <div class="col-span-1">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Bid max:
                    </label>
                    <input
                    v-model="stock[0].bid_max"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                      v-money="money"
                    />
                  </div>

                  <div class="col-span-1">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Ask min:
                    </label>
                    <input
                    v-model="stock[0].ask_min"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                      v-money="money"
                    />
                  </div>

                  <div class="col-span-1">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Ask max:
                    </label>
                    <input
                    v-model="stock[0].ask_max"
                    v-money="money"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                    />
                  </div>

                  <div class="col-span-1">
                    <label
                      for="email-address"
                      class="block text-sm font-medium text-gray-700"
                      v-if="mostrar"
                      >Valor estimado: 
                    </label>
                    <input
                      v-model="valor"
                      type="text"
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
                      v-if="mostrar"
                      readonly
                      v-money="money"
                    />
                  </div>

                  

                  <div class="col-span-6">
                    <label
                      for="street-address"
                      class="block text-sm font-medium text-gray-700"
                      >Dollar balance:
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
                      v-model="this.dollar"
                      v-money="money"
                      readonly
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
                  @click="getMoeda"
                >
                  GET
                </button>
              </div>
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
      users:[],
      id: "",
      stock: [{
      }],
      valor: "",
      dollar: "",
      teste : "---- Select a stock ----",
      wallets: [{
		"id": {
			"id_stock": 0,
			"user": {
				"dollar_balance": 0,
			}
		},
		"stock_name": "",
		"volume": 0,
	}],
      walletUser: [	{
		"id": {
			"id_stock": 0,
			"user": {
				"dollar_balance": 0,
			}
		},
		"stock_name": "",
		"volume": 0,
	}],
      mostrar: false,
      money: {
          decimal: ',',
          thousands: '.',
          prefix: 'R$ ',
          precision: 0,
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
        let response = await axios.get(
          `http://localhost:8082/users`,

          {
            headers: { Authorization: "Bearer " + accessToken },
          }
        );
        this.users = response.data;
        console.log("olha pra baixo");
        console.log(this.users);
      } catch (error) {
        this.users = `${error}`;
      }
        try {
          console.log(this.claims.name)
          console.log(this.users)
          var id = 0;
          for(var i = 0; i < this.users.length;i++){
            if(this.claims.name == this.users[i].username){
              id = this.users[i].id
              this.dollar = this.users[i].dollar_balance
              console.log('aqui eu passo' + this.users[i].dollar_balance)
            }
          }
          let response = await axios.get(`http://localhost:8082/wallet/${id}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.wallets = response.data;
          console.log(this.wallets)
          console.log(this.claims.name)
        } catch (error) {
          this.wallets = `${error}`;
        }
      }
    },
    async getMoeda() {
      this.mostrar = true
      if (this.$root.authenticated) {
        this.claims = await this.$auth.getUser();
        let accessToken = this.$auth.getAccessToken();
        var id = 0;
          for(var i = 0; i < this.users.length;i++){
            if(this.claims.name == this.users[i].username){
              id = this.users[i].id
              this.dollar = this.users[i].dollar_balance
              console.log('aqui eu passo' + this.users[i].dollar_balance)
            }
          }
        try {
          let response = await axios.get(`http://localhost:8082/${id}/${this.teste}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.walletUser = response.data;
          console.log(this.walletUser)
          console.log('bananaaaaaas' )
        } catch (error) {
          this.walletUser = `${error}`;
        }
        try {
          let response = await axios.get(`http://localhost:8085/${this.teste}`, {
            headers: { Authorization: "Bearer " + accessToken },
          });
          this.stock = response.data;
          console.log("id: " + this.stock[0].id + " Symbol: " + this.stock[0].stock_symbol + " Nome: " + this.stock[0].stock_name) 
        } catch (error) {
          this.stock = `${error}`;
        }
      }
      this.valor = this.walletUser[0].volume * this.stock[0].ask_min
      console.log(this.claims.name)
    }
  },
      directives: {money: VMoney}
}
</script>